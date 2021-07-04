package com.henry.catalog.chapter02;

public class ParamAction_RunnableDemo {
    public static void main(String[] args) {
        // 编写多线程代码时，需要告诉特定线程它需要执行的是那些代码；
        // 手段：使用Runnable接口

        // 在创建线程时，指定线程需要执行的具体代码
        // 1 创建线程 - 手段：new Thread()
        // 2 指定线程需要具体执行的代码 - 手段：Runnable接口
        Thread t = new Thread(new Runnable() { // 由于Runnable是一个接口，所以当直接new时，编译器会创建一个匿名类型
            @Override
            public void run() {
                System.out.println("Hello Henry from Runnable");
            }
        });

        // 手段2：使用lambda表达式
        Thread t2 = new Thread(() -> System.out.println("Hello Henry from lambda"));

//        t.start();
        t2.start();
    }
}
