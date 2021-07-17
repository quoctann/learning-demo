package com.quoctan.testproject;

public class MyClient {
    public static void main(String[] agrs) {
//        // SOLID sử dụng lớp cha để khai báo và new bằng lớp con
//        // nên chỉ phụ thuộc vào lớp cha mà thôi
//        Runnable task1 = new MyTask(190, "A");
//        Thread t1 = new Thread(task1);
//        Runnable task2 = new MyTask(190, "B");
//        Thread t2 = new Thread(task2);
//        
//        t1.start();
//        t2.start();


        // Demo thread pool
        Account a = new Account("A", 15);
        
        Runnable task1 = new WithdrawTask(a);
        Thread th1 = new Thread(task1);
        Runnable task2 = new WithdrawTask(a);
        Thread th2 = new Thread(task2);
        
        th1.start();
        th2.start();
    }
}

// Nếu sử dụng extend thread thì không cần khai báo Runable nữa
// Nên phân biệt giữa implement và extend để cân nhắc sử dụng
