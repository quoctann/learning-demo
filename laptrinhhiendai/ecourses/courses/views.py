from django.shortcuts import render
from django.http import HttpResponse
from django.views import View
# Bắt đầu demo rest framework
from rest_framework import viewsets, permissions, status
from rest_framework.decorators import action
from rest_framework.response import Response

from .models import Course, Lesson
from .serializers import *


class CourseViewSet(viewsets.ModelViewSet):
    queryset = Course.objects.filter(active=True)
    serializer_class = CourseSerializer
    # Sau 2 3 dòng trên thì ta có 2 endpoint với 5 cái api rỗng để sử dụng rồi
    # permission_classes = [permissions.IsAuthenticated]

    # Để custom permission:
    def get_permissions(self):
        if self.action == 'list':
            return [permissions.AllowAny()]
        return [permissions.IsAuthenticated()]


class LessonViewSet(viewsets.ModelViewSet):
    queryset = Lesson.objects.filter(active=True)
    serializer_class = LessonSerializer

    # Ẩn một lesson (custom), tạo thêm 1 api: /lessons/{pk}/hide_lesson
    @action(methods=['post'], detail=True, url_path='hide-lesson', url_name='hide-lesson')
    def hide_lessons(self, request, pk):
        try:
            l = Lesson.objects.get(pk=pk)
            l.active = False
            l.save()
        except Lesson.DoesNotExist:
            return Response(status=status.HTTP_400_BAD_REQUEST)
        # Pass context request vào để nó tự resolve domain trường image
        return Response(data=LessonSerializer(l, context={'request': request}).data, status=status.HTTP_200_OK)


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
        return HttpResponse("Test extend view class")

    def post(self, request):
        pass
