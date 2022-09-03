package com.lagou.com.lagou.task;

import com.lagou.bean.ConsumerComponent;

public class MethodTask implements Runnable{

    private ConsumerComponent service;

    public static int count =0;

    public MethodTask(ConsumerComponent service) {
        this.service = service;
    }

    @Override
    public void run() {

        service.methodA();
        count++;
        service.methodB();
        count++;
        service.methodC();
        count++;
    }
}
