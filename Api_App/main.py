from flask import Flask
from flask import jsonify
from flask import request
from flask_cors import CORS
from flask_jwt_extended import create_access_token, verify_jwt_in_request
from flask_jwt_extended import get_jwt_identity


from flask_jwt_extended import jwt_required
from flask_jwt_extended import JWTManager
from flask_jwt_extended.exceptions import NoAuthorizationError
from sqlalchemy.testing import db
from werkzeug.security import generate_password_hash

from pymongo import MongoClient


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

        find_user_id = user["_id"]
        personal_info_id = user.get("personalInfo", {}).get("_id")
        media_user_id = user.get("user_media", {}).get("_id")

        profile_pic_id = user.get("user_media", {}).get("profilePictureId")
        cover_media_id = user.get("user_media", {}).get("coverPictureId")

        url_user = f"{dataConfig['url-backend-security']}/users/{find_user_id}"
        url_personal_info = f"{dataConfig['url-backend-security']}/personal_info/{personal_info_id}"

        get_profile_pic = f"{dataConfig['url-backend-security']}/user-media/{profile_pic_id}"
        get_cover_pic = f"{dataConfig['url-backend-security']}/user-media/{cover_media_id}"

        put_profile_pic = f"{dataConfig['url-backend-security']}/user-media/ProfilePicture/{media_user_id}"
        put_cover_pic = f"{dataConfig['url-backend-security']}/user-media/CoverPicture/{media_user_id}"
        url_new_shares = f"{dataConfig['url-backend-security']}/sharings"

        sharings = get_sharings()

        print(sharings)








        return jsonify({"token": access_token,
                        "user": user,
                        "url_user": url_user,
                        "url_user_data": url_personal_info,
                        "url_new_shares": url_new_shares,
                        "get_profile_pic": get_profile_pic,
                        "get_cover_pic": get_cover_pic,
                        "set_profile_pic": put_profile_pic,
                        "set_cover_pic": put_cover_pic,
                        "sharings": sharings,


                        "msg": "Token Created"})



    if token is None:
        return jsonify({"msg": "Token not found in configuration"}), 500
    else:
        return jsonify({"msg": "Bad username or password"}), 401

#################[LogOut]##################
@app.route("/logout", methods=["POST"])
def close_session():
    global dataConfig
    headers = {"Content-Type": "application/json; charset=utf-8",
               "Authorization": "Bearer " + dataConfig.get("token")}
    url = dataConfig["url-backend-security"] + '/users/logout'
    response = requests.post(url, headers=headers)

    if response.status_code == 200:
        return jsonify({"msg": "Session closed successfully"})
    else:
        return jsonify({"msg": "Failed to close session"}), response.status_code
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


def get_sharings():
    global dataConfig
    url_sharings = dataConfig["url-backend-security"] + "/sharings"
    try:
        response = requests.get(url_sharings)
        sharings = response.json()

        content = [item["content"] for item in sharings]


        return content

    except Exception as e:
        return {"error": str(e)}

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

