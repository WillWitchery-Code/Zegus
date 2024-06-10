from Models.PersonalInfoModel import PersonalInformation
from Repositories.PersonalInfoRep import RepositoriePersonalInfo

class ControlPersonalInformation():
    def __init__(self):
        self.RepositoriePerInfo = RepositoriePersonalInfo()

    def index(self):
        return self.RepositoriePerInfo.findAll()

    def create(self,PersonalInfo):
        newPerInfo = PersonalInformation(PersonalInfo)
        return self.RepositoriePerInfo.save(newPerInfo)

    def show(self,id):
        PerInfo = PersonalInformation(self.RepositoriePerInfo.findById(id))
        return PerInfo.__dict__

    def update(self,id,PersonalInfo):
        PerInfo = PersonalInformation(PersonalInfo)
        return self.RepositoriePerInfo.update(id,PerInfo)

    def delete(self,id):
        return self.RepositoriePerInfo.delete(id)
