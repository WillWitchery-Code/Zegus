from flask import Flask
from flask import jsonify
from flask import request
from flask_cors import CORS
import pymongo
import json
from waitress import serve

app=Flask(__name__)
cors = CORS(app)

#[Controllers Imports]
from Controllers.PersonalInfoController import ControlPersonalInformation
PIController=ControlPersonalInformation()



#*************************[SERVER]*************************#
@app.route("/", methods=['GET'])
def test():
    json = {}
    json["message"] = "Server running ..."
    return jsonify(json)


#***********************[Personal_Information]*******************
@app.route("/PersonalInfo",methods=['GET'])
def getPI():
    json=PIController.index()
    return jsonify(json)
@app.route("/PersonalInfo",methods=['POST'])
def createPI():
    data = request.get_json()
    json=PIController.create(data)
    return jsonify(json)
@app.route("/PersonalInfo/<string:id>",methods=['GET'])
#def getPI(id):
#    json=PIController.show(id)
#    return jsonify(json)

@app.route("/PersonalInfo/<string:id>",methods=['PUT'])
def modifyPI(id):
    data = request.get_json()
    json=PIController.update(id,data)
    return jsonify(json)
@app.route("/PersonalInfo<string:id>",methods=['DELETE'])
def deletePI(id):
    json=PIController.delete(id)
    return jsonify(json)

#***********************[Server Respone]*******************
def loadFileConfig():
    with open('config.json') as f:
        data = json.load(f)
    return data

if __name__=='__main__':
    dataConfig = loadFileConfig()
    print("Server running : "+"http://"+dataConfig["url-backend"]+":" + str(dataConfig["port"]))
    serve(app,host=dataConfig["url-backend"],port=dataConfig["port"])
