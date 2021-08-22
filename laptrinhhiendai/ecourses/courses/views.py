from django.shortcuts import render
from django.http import HttpResponse
from django.views import View
from drf_yasg.utils import swagger_auto_schema
from rest_framework import viewsets, permissions, status, generics
from rest_framework.decorators import action
from rest_framework.response import Response
from rest_framework.parsers import MultiPartParser
from rest_framework.pagination import PageNumberPagination
from .serializers import *


# Phân trang phía server, sử dụng lại bên dưới
class CoursePagination(PageNumberPagination):
    page_size = 2


# Tạo một viewset sử dụng ModelViewSet để nó cấu hình sẵn các endpoint với các
# url để sử dụng
class CourseViewSet(viewsets.ModelViewSet):
    pagination_class = CoursePagination
    # Cấu hình câu truy vấn để lấy dữ liệu ra
    queryset = Course.objects.filter(active=True)
    # Khai báo lớp serializer để chuyển đổi dữ liệu
    serializer_class = CourseSerializer
    # Khai báo để ko hiện ra trên swagger
    # swagger_schema = None
    # Sau 2 3 dòng trên thì ta có 2 endpoint với 5 cái api rỗng để sử dụng rồi
    # Gắn lớp này thì bắt buộc phải được chứng thực mới có thể get dữ liệu
    # permission_classes = [permissions.IsAuthenticated]

    # Để custom permission là lấy nếu ds dữ liệu thôi thì vẫn trả ra bình thường
    # còn những thao tác khác thì bắt buộc phải chứng thực mới được thực hiện:
    def get_permissions(self):
        if self.action == 'list':
            return [permissions.AllowAny()]
        return [permissions.IsAuthenticated()]


class LessonViewSet(viewsets.ModelViewSet):
    queryset = Lesson.objects.filter(active=True)
    serializer_class = LessonSerializer

    # Cấu hình thiết lập các thuộc tính hiển thị ra của swagger
    @swagger_auto_schema(
        operation_description='API này dùng để ẩn một bài viết từ phía client',
        responses={
            status.HTTP_200_OK: LessonSerializer()
        }
    )
    # Ẩn một lesson (custom), tạo thêm 1 api: /lessons/{pk}/hide_lesson
    @action(methods=['post'], detail=True,
            url_path='hide-lesson', url_name='hide-lesson')
    def hide_lessons(self, request, pk):
        try:
            ls = Lesson.objects.get(pk=pk)
            ls.active = False
            ls.save()
        except Lesson.DoesNotExist:
            return Response(status=status.HTTP_400_BAD_REQUEST)
        # Pass context request vào để nó tự resolve domain trường image
        return Response(data=LessonSerializer(
            ls,
            context={'request': request}).data,
            status=status.HTTP_200_OK)


# Các cách xử lý request và trả về response cơ bản
def index(request):
    return render(request, template_name='index.html', context={
        'name': 'Quoc Tan'
    })


def welcome(request, year):
    return HttpResponse("Hello " + str(year))


def welcome2(request, year):
    return HttpResponse("Hello " + str(year))


class TestView(View):
    # Tùy loại http req mà get hoặc post
    def get(self, request):
        return render(request, template_name='index.html')

    def post(self, request):
        pass


# VD Đăng ký user (sau khi tích hợp oauth2)
# Sử dụng ViewSet để tự cấu hình thay vì các lớp view được hiển thị sẵn
# Sd lớp generics.CreateAPIView để hiện thực các pthức create của viewset
# generics.RetrieveAPIView để lấy thông tin một User ra, để ràng buộc chỉ user
# đã đăng nhập mới lấy thông tin được ta ghi đè lại get_permission
class UserViewSet(viewsets.ViewSet,
                  generics.CreateAPIView,
                  generics.RetrieveAPIView):
    queryset = User.objects.filter(is_active=True)
    serializer_class = UserSerializer
    parser_classes = [MultiPartParser, ]

    def get_permissions(self):
        if self.action == 'retrieve':
            return [permissions.IsAuthenticated()]
        return [permissions.AllowAny()]


# Nhắc lại viewset là nó cung cấp mọi thứ tập hợp có sẵn trong đó chỉ cần hiện
# thực xài thôi
class CategoryViewSet(viewsets.ViewSet, generics.ListAPIView):
    queryset = Category.objects.all()
    serializer_class = CategorySerializer
    pagination_class = None
