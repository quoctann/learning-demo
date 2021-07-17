package com.quoctan.testproject;


public class Account {
    private String name;
    private double amount;

    public Account(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }
    
    // synchronized nó sẽ cho phép một thời điểm chỉ một thread được chạy thôi,
    // trong trường hợp có nguy cơ tranh chấp tài nguyên
    public synchronized void withdraw(double a) throws InterruptedException {
        if (this.getAmount() >= a) {
            Thread.sleep(1000);
            this.setAmount(this.getAmount() - a);
            System.out.println(this.getAmount());
        } else {
            System.err.println("Not enough money");
        }
    }

    public double getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }   

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
