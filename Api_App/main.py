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

#################[Register]##################
@app.route('/register', methods=['POST'])
def register_user():
    global dataConfig
    data = request.json
    headers = {'Content-Type': 'application/json'}
    url = dataConfig["url-backend-security"] + '/users/add'
    response = requests.post(url, json=data, headers=headers)
    if response.status_code == 201:
       return jsonify({"msg": "User created"}), 201
    if response.status_code == 409:
        return jsonify({"msg": "User already created"}), 409
    else:
        return jsonify({'msg': 'Registration failed', 'error': response.text}), response.status_code

###################   MiddleWare   #################
def limpiarURL(url):
    partes = url.split("/")
    for laParte in partes:
        if re.search('\\d', laParte):
            url = url.replace(laParte, "?")
    return url
###################   Redirection   #################
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