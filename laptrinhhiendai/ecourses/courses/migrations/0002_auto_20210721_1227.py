# Generated by Django 3.2.5 on 2021-07-21 12:27

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('courses', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='Tag',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('name', models.TextField()),
            ],
        ),
        migrations.AlterModelOptions(
            name='course',
            options={'ordering': ['id']},
        ),
        migrations.AddField(
            model_name='course',
            name='image',
            field=models.ImageField(default=None, upload_to='course/%Y/%m'),
        ),
        migrations.AlterUniqueTogether(
            name='course',
            unique_together={('subject', 'category')},
        ),
        migrations.CreateModel(
            name='Lesson',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('subject', models.CharField(max_length=255)),
                ('image', models.ImageField(default=None, upload_to='course/%Y/%m')),
                ('created_date', models.DateField(auto_now_add=True)),
                ('updated_date', models.DateTimeField(auto_now=True)),
                ('active', models.BooleanField(default=True)),
                ('content', models.TextField()),
                ('course', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='courses.course')),
                ('tags', models.ManyToManyField(blank=True, null=True, to='courses.Tag')),
            ],
            options={
                'abstract': False,
            },
        ),
    ]
