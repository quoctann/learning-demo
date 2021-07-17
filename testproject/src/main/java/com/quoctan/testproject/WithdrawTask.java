package com.quoctan.testproject;


public class WithdrawTask implements Runnable {
    private Account acc;

    public WithdrawTask(Account acc) {
        this.acc = acc;
    }

    @Override
    public void run() {
        try {
            this.acc.withdraw(10);
        } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
