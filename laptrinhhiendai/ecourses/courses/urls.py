# Tập tin này chứa các cấu hình route, xử lý các url request lên (sau khi vào file
# urls của project thì nó mới vào đến urls của app này)

from django.contrib import admin
from django.urls import path, re_path, include
from rest_framework.routers import DefaultRouter
from . import views
# Nếu custom lại view của admin thì cần phải import sử dụng nó tại đây
# from .admin import admin_site

# Các lệnh này sẽ tự sinh route api không cần cấu hình thủ công, default router sẽ
# render ra trang api của django mà ko cần xử lý gì thêm
router = DefaultRouter()
router.register('course', views.CourseViewSet, basename='course')
router.register('lessons', views.LessonViewSet, basename='lessons')
router.register('user', views.UserViewSet, basename='user')
router.register('categories', views.CategoryViewSet, basename='categories')


urlpatterns = [
    # Khi sử dụng rest framework thì khai báo route của nó, sau đó thì nó sẽ tự sinh
    # 2 endpoint và 5 url như sau:
    # /courses/ - GET
    # /courses/ - POST
    # /courses/{course_id} - GET
    # /courses/{course_id} - PUT
    # /courses/{course_id} - DELETE
    path('', include(router.urls)),
    # Cách xử lý một request gửi lên url cơ bản
    path('welcome/<int:year>', views.welcome, name='welcome'),
    # Tạo view bằng một class kế thừa từ django.view
    path('test/', views.TestView.as_view()),
    # Biểu thức chính quy re_path: bắt đầu r'NỘI DUNG'$ với NỘI DUNG có dạng
    # (?P<year>[0-9]{4}) nghĩa là biến truyền vào phải là số từ 0-9 và có 4 chữ số
    re_path(r'welcome2/(?P<year>[0-9]{4})$', views.welcome2, name='welcome2'),
    path('admin/', admin.site.urls)
    # Nếu là trang admin tự mình custom thì gọi admin_site.
]
