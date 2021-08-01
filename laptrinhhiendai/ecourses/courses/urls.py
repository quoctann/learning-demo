from django.contrib import admin
from django.urls import path, re_path, include
from . import views
from .admin import admin_site
from rest_framework.routers import DefaultRouter

router = DefaultRouter()
router.register('course', views.CourseViewSet)
router.register('lessons', views.LessonViewSet)

# /courses/ - GET
# /courses/ - POST
# /courses/{course_id} - GET
# /courses/{course_id} - PUT
# /courses/{course_id} - DELETE
urlpatterns = [
    # Chỉnh lại khi có rest_framework, sau đó sẽ có 2 endpoint & 5 url trên
    path('', include(router.urls)),
    path('welcome/<int:year>', views.welcome, name='welcome'),
    # Tạo view bằng một class kế thừa từ django.view
    path('test/', views.TestView.as_view()),
    # Biểu thức chính quy re_path: bắt đầu r'nội dung'$ với nội dung (?P<year>[0-9]{4})
    # Số từ 0-9 và có 4 chữ số
    re_path(r'welcome2/(?P<year>[0-9]{4})$', views.welcome2, name='welcome2'),
    path('admin/', admin_site.urls)
]
