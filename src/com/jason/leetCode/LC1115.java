package com.jason.leetCode;

public class LC1115 {

    public static void main(String[] args) {
        FooBar2 fooBar = new FooBar2(2);
        new Thread(()->{
            try {
                fooBar.foo(()->System.out.print("foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T1").start();

        new Thread(()->{
            try {
                fooBar.bar(()->System.out.print("bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T2").start();
    }
}
//方案2CAS
class FooBar2 {

    private int n;
    long l1,l2,l3,l4,l5,l6,l7;
    public volatile Boolean flag = true;
    long r1,r2,r3,r4,r5,r6,r7;//缓存行填充加速

    public FooBar2(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; ) {
            // printFoo.run() outputs "foo". Do not change or remove this line.
            while (flag){
                printFoo.run();
                flag = false;
                i++;
            }
            Thread.yield();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; ) {
            // printBar.run() outputs "bar". Do not change or remove this line.
            while (!flag){
                printBar.run();
                flag = true;
                i++;
            }
            Thread.yield();
        }
    }
}

//方案1
class FooBar {

    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    public synchronized void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            this.notify();
            this.wait();
        }
    }

    public synchronized void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            // printBar.run() outputs "bar". Do not change or remove this line.
            this.wait();
            printBar.run();
            this.notify();
        }
    }
}