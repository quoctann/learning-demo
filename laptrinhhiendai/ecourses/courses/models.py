from django.contrib.auth.models import AbstractUser
from django.db import models


class User(AbstractUser):
    avatar = models.ImageField(upload_to='uploads/%Y/%m')


# Bảng này sẽ được tạo trong db dưới tên: course_category
class Category(models.Model):
    name = models.CharField(max_length=100, null=False, unique=True)

    def __str__(self):
        return self.name


class ItemBase(models.Model):
    # Sử dụng lớp con Meta để báo cho hệ thống đây là lớp kế thừa
    class Meta:
        abstract = True

    subject = models.CharField(max_length=255, null=False)
    # Tự upload hình ảnh vào thư mục năm/ngày
    image = models.ImageField(upload_to='course/%Y/%m', default=None)
    created_date = models.DateField(auto_now_add=True)
    updated_date = models.DateTimeField(auto_now=True)
    active = models.BooleanField(default=True)

    def __str__(self):
        return self.subject


class Course(ItemBase):
    class Meta:
        unique_together = ('subject', 'category')
        ordering = ["id"]

    description = models.TextField(null=True, blank=True)
    category = models.ForeignKey(Category, on_delete=models.SET_NULL, null=True)


class Lesson(ItemBase):
    content = models.TextField()
    course = models.ForeignKey(Course, on_delete=models.CASCADE)
    tags = models.ManyToManyField('Tag', blank=True, null=True)


class Tag(models.Model):
    name = models.TextField()

    def __str__(self):
        return self.name
