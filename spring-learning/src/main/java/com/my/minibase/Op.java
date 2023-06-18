package com.my.minibase;

public enum Op {
    PUT((byte)0),
    DELETE((byte)0);

    private byte op;

    Op(byte op) {
        this.op = op;
    }

    public static Op valueOf(byte op) {
        switch (op) {
            case 0:
                return PUT;
            case 1:
                return DELETE;
            default:
                throw new IllegalArgumentException("Unknow code: " + op);
        }
    }

    public byte getOp() {
        return op;
    }
}
