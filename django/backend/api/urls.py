from django.conf.urls import url
from rest_framework.routers import DefaultRouter
from api import views
# from .mongodb import users

router = DefaultRouter(trailing_slash=False)
router.register(r"stores", views.StoreViewSet, basename="stores")
#router.register('users',views.UserViewSet)
urlpatterns = router.urls

