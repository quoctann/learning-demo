# Tập tin này dùng để cấu hình các thuộc tính hiển thị trên trang admin như ghi
# đè template tùy chỉnh các các form cho phép chỉnh sửa model

from django.contrib import admin
from django import forms
from django.db.models import Count
from django.template.response import TemplateResponse
from django.utils.html import mark_safe
from django.urls import path
from .models import *
from ckeditor_uploader.widgets import CKEditorUploadingWidget


# Sử dụng model form của django.models, custom lại edit form mặc định (gắn thêm
# bộ ckeditor), class này sẽ được sử dụng ở phần dưới
class LessonsForm(forms.ModelForm):
    content = forms.CharField(widget=CKEditorUploadingWidget)

    class Meta:
        model = Lesson
        fields = '__all__'


# Khai báo để sử dụng trong quan hệ n-n bên dưới, hiển thị khóa ngoại như một
# thuộc tính inline (vd Tag và Lesson)
class LessonTagInline(admin.TabularInline):
    model = Lesson.tags.through


# Cấu hình hiển thị model Lesson sử dụng các lớp phía trên
class LessonAdmin(admin.ModelAdmin):
    # Khai báo các tập tin css js có sử dụng, thuộc tính dạng tuple
    class Media:
        css = {
            'all': ('/static/css/main.css',)
        }
        js = ('/static/js/main.js',)

    # Sử dụng cấu hình form nào
    form = LessonsForm
    # Hiển thị những trường nào ra form
    list_display = ['id', 'subject', 'active', 'course']
    # Tạo thanh tìm kiếm dựa trên những trường/khóa ngoại nào
    search_fields = ['subject', 'course__subject']
    # Trường trạng thái chỉ đọc, có thể tùy chỉnh lại bên dưới
    readonly_fields = ['custom_field']
    # Trường inline (khóa ngoại)
    inlines = [LessonTagInline, ]

    # Tùy chỉnh lại để trường hiển thị hình ảnh render ra ảnh luôn thay vì chỉ
    # là đường dẫn tập tin
    def custom_field(self, lesson):
        # Trả về một thẻ html được sanitizer tránh việc bị tấn công xss
        return mark_safe("<img src='/static/{img_url}' alt='{alt}' width='150px' />"
                         .format(img_url=lesson.image.name, alt=lesson.subject))


# Thuộc tính inline hiển thị dạng stack giữa Course & Lesson (1-n)
class LessonInline(admin.StackedInline):
    model = Lesson
    pk_name = 'course'


# Nhúng class inline vào hiển thị trong model Course
class CourseAdmin(admin.ModelAdmin):
    inlines = (LessonInline,)


# Ghi đè lại trang admin mặc định, cấu hình một số thuộc tính
class CourseAppAdminSite(admin.AdminSite):
    # Phần header Django Administrator
    site_header = 'Hệ thống quản lý khóa học'

    # Ghi đè lại url của mình nhưng vẫn sd phần cha mặc định của django
    def get_urls(self):
        return [
                   path('course-stats/', self.course_stats)
               ] + super().get_urls()

    # Định nghĩa lại view của mình
    def course_stats(self, request):
        course_count = Course.objects.count()
        stats = Course.objects.annotate(
            lesson_count=Count('lessons')).values('id', 'subject', 'lesson_count')
        # Phần này django sẽ tự xử lý static file do đã được khai báo, cấu trúc
        # thư mục tương tự như như trong venv
        return TemplateResponse(request, 'admin/course-stats.html', {
            'course_count': course_count,
            'stats': stats
        })


# Nếu sử dụng view tự custom như trên thì khai báo như này:
# admin_site = CourseAppAdminSite('mycourse')
# admin_site.register(Category)
# admin_site.register(Course, CourseAdmin)
# admin_site.register(Lesson, LessonAdmin)

# Nếu sử dụng mặc định của django thì add view như thế này:
admin.site.register(Category)
admin.site.register(Course, CourseAdmin)
admin.site.register(Lesson, LessonAdmin)
