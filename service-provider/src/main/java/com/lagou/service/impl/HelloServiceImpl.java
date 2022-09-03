package com.lagou.service.impl;

import com.lagou.service.HelloService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name, int timeToWait) {
        return "hello:"+name;
    }

    @Override
    public String methodA() {

        sleepCommon();
        return "方法A";
    }

    @Override
    public String methodB() {

        sleepCommon();
        return "方法B";
    }

    @Override
    public String methodC() {

        sleepCommon();
        return "方法C";
    }

    private void sleepCommon(){

        int time = (int) (Math.random() * 100) + 1;
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
