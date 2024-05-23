from flask import Flask
from flask import jsonify
from flask import request
from flask_cors import CORS
from flask_jwt_extended import create_access_token, verify_jwt_in_request
from flask_jwt_extended import get_jwt_identity
from flask_jwt_extended import jwt_required
from flask_jwt_extended import JWTManager
import json
from waitress import serve
import datetime
import requests
import re

app=Flask(__name__)
cors = CORS(app)
app.config["JWT_SECRET_KEY"]="super-secret"
jwt = JWTManager(app)

@app.route("/login", methods=["POST"])
def create_token():
    data = request.get_json()
    headers = {"Content-Type": "application/json; charset=utf-8"}
    url = dataConfig["url-backend-security"]+'/users/validate'
    response = requests.post(url, json=data, headers=headers)
    if response.status_code == 200:
        user = response.json()
        expires = datetime.timedelta(seconds=60 * 60*24)
        access_token = create_access_token(identity=user, expires_delta=expires)
        return jsonify({"token": access_token, "user_id": user["_id"]})
    else:
        return jsonify({"msg": "Bad username or password"}), 401
    
###################   MiddleWare   #################
@app.before_request
def before_request_callback():
    endPoint=limpiarURL(request.path)
    excludedRoutes=["/login"]
    if excludedRoutes.__contains__(request.path):
        pass
    elif verify_jwt_in_request():
        user = get_jwt_identity()
        if user["rol"]is not None:
            hasPermission=validatePermission(endPoint,request.method,user["rol"]["_id"])
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
def validatePermission(endPoint,method,id_rol):
    url=dataConfig["url-backend-security"]+"/validate_permit/rol/"+str(id_rol)
    Allowed=False
    headers = {"Content-Type": "application/json; charset=utf-8"}
    body={
        "url":endPoint,
        "method":method
    }
    response = requests.get(url, json=body, headers=headers)
    try:
        data=response.json()
        if("_id" in data):
            Allowed=True
    except:
        pass
    return Allowed
####################################################
###################   Redirection   #################

####################################################

@app.route("/",methods=['GET'])
def test():
    json = {}
    json["message"]="Server running ..."
    return jsonify(json)

def loadFileConfig():
    with open('config.json') as f:
        data = json.load(f)
    return data

if __name__=='__main__':
    dataConfig = loadFileConfig()
    print("Server running : "+"http://"+dataConfig["url-backend"]+":" + str(dataConfig["port"]))
    serve(app,host=dataConfig["url-backend"],port=dataConfig["port"])