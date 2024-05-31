from flask import Flask
from flask import jsonify
from flask import request
from flask_cors import CORS
from flask_jwt_extended import create_access_token, verify_jwt_in_request
from flask_jwt_extended import get_jwt_identity


from flask_jwt_extended import jwt_required
from flask_jwt_extended import JWTManager
from flask_jwt_extended.exceptions import NoAuthorizationError
from werkzeug.security import generate_password_hash


import json
import os

from waitress import serve
import datetime
import requests
import re
import logging

app = Flask(__name__)
cors = CORS(app,  resources={r"/*": {"origins": "*"}})
app.config["JWT_SECRET_KEY"] = "super-secret"
jwt = JWTManager(app)


#################[Lgin]##################


@app.route("/login", methods=["POST"])
def create_token():
    global dataConfig
    data = request.get_json()

    token = dataConfig.get("token")


    headers = {"Content-Type": "application/json; charset=utf-8",
               "Authorization": "Bearer " + dataConfig.get("token")}

    url = dataConfig["url-backend-security"] + '/users/validate'
    response = requests.post(url, json=data, headers=headers)

   

    if response.status_code == 200:
        user = response.json()
        expires = datetime.timedelta(seconds=60 * 60 * 24)
        access_token = create_access_token(identity=user, expires_delta=expires)
        with open('config.json', 'r+') as f:
            dataConfig = json.load(f)
            dataConfig['token'] = access_token
            f.seek(0)
            json.dump(dataConfig, f, indent=4)
            f.truncate()
        return jsonify({"token": access_token, "user_id": user["_id"], "msg": "Token Created"})

    if token is None:
        return jsonify({"msg": "Token not found in configuration"}), 500
    else:
        return jsonify({"msg": "Bad username or password"}), 401


@app.route('/register', methods=['POST'])
def register_user():
    data = request.json
    username = data.get('username')
    password = data.get('password')
    headers = {'Content-Type': 'application/json'}
    print(data)

    # Forward the request to the Java backend
    java_backend_url = 'http://127.0.0.1:8080/users'
    java_response = requests.post(
        java_backend_url,
        json={'username': username,
              'password': password,
              'rol': "",},
        headers=headers)


    if java_response.status_code == 200:
        return jsonify(java_response.json()), 200

    else:
        return jsonify({'msg': 'Registration failed', 'error': java_response.text}), java_response.status_code


###################   MiddleWare   #################
@app.before_request
def before_request_callback():
    endPoint = limpiarURL(request.path)
    excludedRoutes = ["/login"]
    if excludedRoutes.__contains__(request.path):
        pass
    elif verify_jwt_in_request():
        user = get_jwt_identity()
        if user["rol"] is not None:
            hasPermission = validatePermission(endPoint, request.method, user["rol"]["_id"])
            if not hasPermission:
                return jsonify({"message": "Permission Denied"}), 401
        else:
            return jsonify({"message": "Permission Denied"}), 401


def limpiarURL(url):
    partes = url.split("/")
    for laParte in partes:
        if re.search('\\d', laParte):
            url = url.replace(laParte, "?")
    return url


def validatePermission(endPoint, method, id_rol):
    url = dataConfig["url-backend-security"] + "/validate_permit/rol/" + str(id_rol)
    Allowed = False
    headers = {"Content-Type": "application/json; charset=utf-8"}
    body = {
        "url": endPoint,
        "method": method
    }
    response = requests.get(url, json=body, headers=headers)
    try:
        data = response.json()
        if ("_id" in data):
            Allowed = True
    except:
        pass
    return Allowed


####################################################
###################   Redirection   #################

####################################################

@app.route("/", methods=['GET'])
def test():
    json = {}
    json["message"] = "Server running ..."
    return jsonify(json)


def loadFileConfig():
    with open('config.json') as f:
        data = json.load(f)
    return data

if __name__ == '__main__':
    logging.basicConfig(filename='app.log', level=logging.ERROR)
    dataConfig = loadFileConfig()
    print("Server running : " + "http://" + dataConfig["url-backend"] + ":" + str(dataConfig["port"]))
    serve(app, host=dataConfig["url-backend"], port=dataConfig["port"])


########################[Erros]############################
