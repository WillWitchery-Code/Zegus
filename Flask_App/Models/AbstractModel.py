from abc import ABCMeta
class AbstractModel(metaclass=ABCMeta):
    def __init__(self,data):
        for key, valor in data.items():
            setattr(self, key, valor)