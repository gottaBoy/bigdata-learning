package com.zhangbao.gmall.mock.log;

import com.zhangbao.gmall.mock.log.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;


@Component
public class MockTask {


    @Autowired
    ThreadPoolTaskExecutor poolExecutor;

    public void mainTask( ) {

        for (int i = 0; i < AppConfig.mock_count; i++) {
           poolExecutor.execute(new Mocker());
            System.out.println("active+" + poolExecutor.getActiveCount());
            //  new Mocker().run();
        }
        while(true){
            try {
                Thread.sleep(1000);
                if(poolExecutor.getActiveCount()==0){
                    poolExecutor.destroy();
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }
}
