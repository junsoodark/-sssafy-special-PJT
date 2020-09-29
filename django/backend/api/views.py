from api import models, serializers
from rest_framework import viewsets
from rest_framework.pagination import PageNumberPagination
# from .models import User
from django.http import HttpResponse
import json
 
from musicD.MongoDbManager import MongoDbManager

class SmallPagination(PageNumberPagination):
    page_size = 10
    page_size_query_param = "page_size"
    max_page_size = 50


class StoreViewSet(viewsets.ModelViewSet):
    serializer_class = serializers.StoreSerializer
    pagination_class = SmallPagination

    def get_queryset(self):
        name = self.request.query_params.get("name", "")
        queryset = (
            models.Store.objects.all().filter(store_name__contains=name).order_by("id")
        )
        return queryset


def all_users( request):
    def get():
        dbUserData = MongoDbManager().get_users_from_collection({})

        userData = []
        for user in dbUserData:
            del user['_id']
            userData.append(user)

        template = loader.get_template('user.html')
        return HttpResponse(template.render({'userData': userData}, request))
 
    if request.method == 'GET':
        return get()
    else:
        return HttpResponse( status=405)


# class UserViewSet(viewsets.ModelViewSet):
#     queryset = User.objects.all()
#     serializer_class = serializers.UserSerializer


# def read_post(request, id):
#     post = User.objects.get(_id=ObjectId(id))
    