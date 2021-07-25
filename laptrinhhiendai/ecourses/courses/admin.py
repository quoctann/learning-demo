from dataclasses import fields

from django.contrib import admin
from django import forms
from django.db.models import Count
from django.template.response import TemplateResponse
from .models import *
from django.utils.html import mark_safe
from ckeditor_uploader.widgets import CKEditorUploadingWidget
from django.urls import path


class LessonsForm(forms.ModelForm):
    content = forms.CharField(widget=CKEditorUploadingWidget)

    class Meta:
        model = Lesson
        fields = '__all__'


class LessonTagInline(admin.TabularInline):
    model = Lesson.tags.through


class LessonAdmin(admin.ModelAdmin):
    class Media:
        css = {
            'all': ('/static/css/main.css',)
        }
        js = ('/static/js/main.js',)

    form = LessonsForm
    list_display = ['id', 'subject', 'active', 'course']
    search_fields = ['subject', 'course__subject']
    readonly_fields = ['custom_field']
    inlines = [LessonTagInline, ]

    def custom_field(self, obj):
        # Ta có thể khai biến obj là obj luôn cũng được bởi vì nó sẽ lấy đối tượng hiện tại đang tương tác bỏ vào mà ở
        # đây đang là Lesson
        return mark_safe("<img src='/static/{img_url}' alt='{alt}' width='150px' />"
                         .format(img_url=obj.image.name, alt=obj.subject))


class LessonInline(admin.StackedInline):
    model = Lesson
    pk_name = 'course'


class CourseAdmin(admin.ModelAdmin):
    inlines = (LessonInline,)


class CourseAppAdminSite(admin.AdminSite):
    site_header = 'Hệ thống quản lý khóa học'

    # Ghi đè lại url của mình nhưng vẫn sử dụng của phần cha
    def get_urls(self):
        return [
                   path('course-stats/', self.course_stats)
               ] + super().get_urls()

    # Định nghĩa view nên sẽ luôn nhận request
    def course_stats(self, request):
        course_count = Course.objects.count()
        stats = Course.objects.annotate(lesson_count=Count('lessons')).values('id', 'subject', 'lesson_count')

        return TemplateResponse(request, 'admin/course-stats.html', {
            'course_count': course_count,
            'stats': stats
        })


admin_site = CourseAppAdminSite('mycourse')
# Nếu tinh chỉnh admin site rồi thì ko cần dùng mặc định như vầy nữa
# admin.site.register(Category)
# admin.site.register(Course, CourseAdmin)
# admin.site.register(Lesson, LessonAdmin)
admin_site.register(Category)
admin_site.register(Course, CourseAdmin)
admin_site.register(Lesson, LessonAdmin)
