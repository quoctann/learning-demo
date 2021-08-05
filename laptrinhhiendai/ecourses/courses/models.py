# Tập tin này để cấu hình các model ánh xạ xuống csdl trong mô hình MVC

from django.contrib.auth.models import AbstractUser
from django.db import models
from ckeditor.fields import RichTextField


# Sử dụng lớp user của mình nhưng kế thừa lớp user của django để có thể sử dụng
# các chức năng chứng thực của nó
class User(AbstractUser):
    # Thêm một trường avatar cho bảng user có sẵn, cấu hình upload ảnh lên thư
    # mục static của app
    avatar = models.ImageField(upload_to='upload/%Y/%m')


# Bảng này sẽ được tạo trong csdl dưới tên: course_category
class Category(models.Model):
    name = models.CharField(max_length=100, null=False, unique=True)

    # Giống phương thức to string để trả về một chuỗi nếu gọi print
    def __str__(self):
        return self.name


# Lớp cơ sở để các lớp khác kế thừa lại tránh lặp code
class ItemBase(models.Model):
    # Sử dụng lớp con Meta để báo cho hệ thống đây là lớp kế thừa
    class Meta:
        abstract = True

    subject = models.CharField(max_length=255, null=False)
    # Tự upload hình ảnh vào thư mục năm/ngày
    image = models.ImageField(upload_to='upload/%Y/%m', default=None)
    created_date = models.DateField(auto_now_add=True)
    updated_date = models.DateTimeField(auto_now=True)
    active = models.BooleanField(default=True)

    def __str__(self):
        return self.subject


# Course có các trường kế thừa từ ItemBase
class Course(ItemBase):

    class Meta:
        # Các trường này ghép lại với nhau ko được trùng lắp, vd ko thể có 2
        # khóa học giống nhau trong cùng một category
        unique_together = ('subject', 'category')
        # Sắp xếp theo id tăng dần, -id để giảm dần
        ordering = ["id"]

    description = models.TextField(null=True, blank=True)
    category = models.ForeignKey(Category, on_delete=models.SET_NULL, null=True)


class Lesson(ItemBase):
    class Meta:
        unique_together = ('subject', 'course')

    # Trường content sử dụng plugin ckeditor
    content = RichTextField()
    course = models.ForeignKey(Course, related_name='lessons', on_delete=models.CASCADE)
    tags = models.ManyToManyField('Tag', related_name='lessons', blank=True, null=True)


class Tag(models.Model):
    name = models.CharField(max_length=100, unique=True)

    def __str__(self):
        return self.name
