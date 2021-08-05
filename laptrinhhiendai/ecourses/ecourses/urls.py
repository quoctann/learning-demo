"""ecourses URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/3.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
# Tập tin này dùng để cấu hình các url root (đã được khai báo ở setting) khi
# client gửi request thì nó sẽ được gọi đầu tiên
import debug_toolbar
from django.contrib import admin
from django.urls import path, include, re_path
from rest_framework import permissions
from drf_yasg.views import get_schema_view
from drf_yasg import openapi

# Bổ trợ sinh api document
schema_view = get_schema_view(
    openapi.Info(
        title="Course API",
        default_version='v1',
        description="APIs for CourseApp",
        contact=openapi.Contact(email="1851050127tan@ou.edu.vn"),
        license=openapi.License(name="Trần Quốc Tấn@2021"),
    ),
    public=True,
    permission_classes=(permissions.AllowAny,),
)

# Khai báo các route/path dẫn đến các app được sử dụng
urlpatterns = [
    path('', include('courses.urls')),
    path('admin/', admin.site.urls),
    # Cấu hình oauth2, /o là gì cũng được
    path('o/', include('oauth2_provider.urls', namespace='oauth2_provider')),
    # Cấu hình bộ uploader của ckeditor (phải chính xác)
    re_path(r'^ckeditor/', include('ckeditor_uploader.urls')),
    # Chỉ dán vào xài thôi cũng được để sinh document, truy cập /swagger hoặc
    # /redoc để xem thêm
    re_path(r'^swagger(?P<format>\.json|\.yaml)$',schema_view.without_ui(cache_timeout=0), name='schema-json'),
    re_path(r'^swagger/$', schema_view.with_ui('swagger', cache_timeout=0), name='schema-swagger-ui'),
    re_path(r'^redoc/$', schema_view.with_ui('redoc', cache_timeout=0), name='schema-redoc'),
    # Debug toolbar url, load trang là tự động hiện
    path('__debug__/', include(debug_toolbar.urls))
]
