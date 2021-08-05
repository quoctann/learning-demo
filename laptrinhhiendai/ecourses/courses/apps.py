# Tập tin cấu hình một app trong django, để sử dụng app này khai báo trong
# settings.py trong thư mục cấu hình project
from django.apps import AppConfig


class CoursesConfig(AppConfig):
    default_auto_field = 'django.db.models.BigAutoField'
    name = 'courses'
