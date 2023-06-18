package com.my.netty;

import java.util.ArrayList;
import java.util.List;

public class FutureMain {
    public static void main(String[] args) {
        List<RequestFuture> reqs = new ArrayList<>();
        for (long i = 0; i < 100; i++) {
            RequestFuture requestFuture = new RequestFuture();
            requestFuture.setId(i);
            requestFuture.setRequest("client request" + i);
            RequestFuture.addFuture(requestFuture);
            reqs.add(requestFuture);
            sentMsg(requestFuture);
            SubThread thread = new SubThread(requestFuture);
            thread.start();
        }
        for (RequestFuture req : reqs) {
            Object obj = req.get();
            System.out.println(obj);
        }
    }

    private static void sentMsg(RequestFuture requestFuture) {
        System.out.println("发送消息ID：" + requestFuture.getId());
    }
}
