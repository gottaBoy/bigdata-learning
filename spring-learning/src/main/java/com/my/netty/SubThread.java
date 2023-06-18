package com.my.netty;

public class SubThread extends Thread {
    private RequestFuture request;

    public SubThread(RequestFuture request) {
        this.request = request;
    }

    @Override
    public void run() {
        Response response = new Response();
        response.setId(request.getId());
        response.setResult("server response" + Thread.currentThread().getId());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RequestFuture.completeSuccess(response);
    }
}
