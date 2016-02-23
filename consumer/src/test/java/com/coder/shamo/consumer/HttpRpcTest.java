/* * Copyright (c) 2014 Qunar.com. All Rights Reserved. */
package com.coder.shamo.consumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.SystemProfileValueSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuefeng.sha  Date: 2016/2/23 Time: 14:44
 * @version $Id$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring-*.xml"})
public class HttpRpcTest {
    private static final Logger logger = LoggerFactory.getLogger(HttpRpcTest.class);

    @Resource
    private UserController userController;

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Test
    public void test() throws InterruptedException{
        final CountDownLatch countDownLatch = new CountDownLatch(10000);
        final ExecutorService executorService = Executors.newFixedThreadPool(50);

        long start = System.currentTimeMillis();
        for(int index = 0; index < 10000; index++){
            Runnable run = new Runnable() {
                public void run() {
                    logger.info(userController.print("shamo", new Random(100).nextInt()));
                    countDownLatch.countDown();
                }
            };
            executorService.submit(run);
        }
        countDownLatch.await();
        System.out.println(System.currentTimeMillis()-start);
        executorService.shutdown();
    }
}
