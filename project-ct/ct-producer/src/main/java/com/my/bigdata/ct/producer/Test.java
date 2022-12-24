package com.my.bigdata.ct.producer;

import java.util.Random;

public class Test {
    public static void main(String[] args) {

        Random random = new Random(10);

        for ( int i = 0; i < 10; i++ ) {
            System.out.println(random.nextInt(10));
        }
        System.out.println("**********************************");
        Random random1 = new Random(10);

        for ( int i = 0; i < 10; i++ ) {
            System.out.println(random1.nextInt(10));
        }

    }
}
