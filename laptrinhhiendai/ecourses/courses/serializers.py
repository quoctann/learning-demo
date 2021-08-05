# Tập tin này dùng để cấu hình các trường serializer - chuyển các kiểu dữ liệu
# json thành lớp đối tượng và ngược lại để tương tác trên môi trường internet

from rest_framework.serializers import ModelSerializer
from .models import Course, Tag, Lesson, User


# Mỗi lớp model khi được sử dụng trong api sẽ có một lớp serializer
class UserSerializer(ModelSerializer):
    class Meta:
        # Model sẽ sử dụng để serialize
        model = User
        # Các trường sẽ được trả ra dạng json
        fields = ['id', 'first_name', 'last_name', 'email',
                  'username', 'password', 'avatar']
        # Trường password không nên trả ra trong api, chỉ sử dụng 1 lần khi ghi
        # vào csdl thôi
        extra_kwargs = {
            'password': {'write_only': 'true'}
        }

    # Ghi đè lại bộ dữ liệu nhận từ client, cụ thể ghi đè lại password dạng băm
    def create(self, validated_data):
        # Sd ** để nó tự động parse bộ dữ liệu như mặc định, sau đó cần ghi đè
        # trường nào thì khai báo (cách này tránh lặp code)
        user = User(**validated_data)
        # Ghi đè lại trường password (sử dụng các này để set toàn bộ bộ dữ liệu tuy
        # nhiên sẽ lặp code nhiều nếu dữ liệu lớn
        user.set_password(validated_data['password'])
        user.save()
        return user


# Liên kết model với serializer để chuyển dữ liệu phức tạp <-> json
class CourseSerializer(ModelSerializer):
    class Meta:
        model = Course
        fields = ['id', 'subject', 'image', 'created_date', 'category']


class TagSerializer(ModelSerializer):
    class Meta:
        model = Tag
        fields = ['id', 'name']


class LessonSerializer(ModelSerializer):
    # Bật trường này đối với các quan hệ 1-n hoặc n-n mà muốn hiển thị tất cả
    # các trường khóa ngoại
    tags = TagSerializer(many=True)

    class Meta:
        model = Lesson
        fields = ['id', 'subject', 'content', 'created_date', 'course', 'tags']
