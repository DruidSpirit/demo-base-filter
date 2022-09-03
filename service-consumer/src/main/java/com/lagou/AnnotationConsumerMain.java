package com.lagou;

import com.lagou.bean.ConsumerComponent;
import com.lagou.com.lagou.task.MethodTask;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class AnnotationConsumerMain {
    public static void main(String[] args) throws IOException, InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
        context.start();
        ConsumerComponent service = context.getBean(ConsumerComponent.class);

        //  建立定时线程池(每隔1秒执行12次任务，每次任务执行三次方法点用，这样保证每秒36次调用，每分钟2160调用)
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        ExecutorService executorServiceTask = Executors.newFixedThreadPool(6);
        System.out.println("开始任务===");
        executorService.scheduleAtFixedRate(() -> {
            for (int i = 0; i < 12; i++) {
                executorServiceTask.submit(new MethodTask(service));
            }
            System.out.println("执行次数"+MethodTask.count);
        }, 0, 1000, TimeUnit.MILLISECONDS);
    }

    @Configuration
    @PropertySource("classpath:/dubbo-consumer.properties")
    //@EnableDubbo(scanBasePackages = "com.lagou.bean")
    @ComponentScan("com.lagou.bean")
    @EnableDubbo
    static class ConsumerConfiguration {

    }
}
