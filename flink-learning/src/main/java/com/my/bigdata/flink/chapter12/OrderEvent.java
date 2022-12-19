package com.my.bigdata.flink.chapter12;



public class OrderEvent {
    public String userId;
    public String orderId;
    public String eventType;
    public Long timestamp;

    public OrderEvent() {
    }

    public OrderEvent(String userId, String orderId, String eventType, Long timestamp) {
        this.userId = userId;
        this.orderId = orderId;
        this.eventType = eventType;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "OrderEvent{" +
                "userId='" + userId + '\'' +
                "orderId='" + orderId + '\'' +
                ", eventType='" + eventType + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}

