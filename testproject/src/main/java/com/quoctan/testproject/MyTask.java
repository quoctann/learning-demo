package com.quoctan.testproject;


// Lớp kế thừa lớp Runable sẽ cho phép thực thi chương trình đa luồng
public class MyTask implements Runnable{
    private int times;
    private String value;

    public MyTask(int times, String value) {
        this.times = times;
        this.value = value;
    }
    
    // Phương thức này của lớp Runable sẽ tự thực thi khi thread start() và nó
    // sẽ chạy đa luồng luôn
    @Override
    public void run() {
        for (int i = 0; i <= this.times; i++)
            System.out.print(this.value);
    }
}

// Có thể sử dụng extend Thread để thay thế cho implement Runable
// public class MyTask implements Runnable
// Phần phía sau giống như cũ