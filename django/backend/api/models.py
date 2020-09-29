from django.utils import timezone
from django.db import models
#from djongo import models  as model

class Store(models.Model):
    id = models.IntegerField(primary_key=True)
    store_name = models.CharField(max_length=50)
    branch = models.CharField(max_length=20, null=True)
    area = models.CharField(max_length=50, null=True)
    tel = models.CharField(max_length=20, null=True)
    address = models.CharField(max_length=200, null=True)
    latitude = models.FloatField(max_length=10, null=True)
    longitude = models.FloatField(max_length=10, null=True)
    category = models.CharField(max_length=200, null=True)

    @property
    def category_list(self):
        return self.category.split("|") if self.category else []


class User(model.Model):
    _id= model.ObjectIdField(primary_key=True)
    email= model.CharField(max_length=20, null=True)
    password= model.CharField(max_length=20, null=True)
    nickname = model.CharField(max_length=20, null=True)


