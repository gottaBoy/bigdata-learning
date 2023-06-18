package com.my.netty;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class RequestFuture implements Serializable {
    public static Map<Long, RequestFuture> futures = new ConcurrentHashMap<>();

    private static final AtomicLong aid = new AtomicLong(1);
    private Long id;
    private Object request;
    private Object result;
    private long timeout = 5000;

    public RequestFuture() {
        id = aid.getAndIncrement();
    }

    public static void addFuture(RequestFuture request) {
        futures.put(request.getId(), request);
    }

    public Object get() {
        synchronized (this) {
            while (this.result == null) {
                try {
                    this.wait(timeout);
                } catch (InterruptedException e) {
                   e.printStackTrace();
                }
            }
        }
        return this.result;
    }

    public static void completeSuccess(Response response) {
        RequestFuture future = futures.remove(response.getId());
        if (future != null) {
            future.setResult(response.getResult());
        }
        synchronized (future) {
            future.notify();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Object getRequest() {
        return request;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
