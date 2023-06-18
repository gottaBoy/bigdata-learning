package com.my.bigdata;

public class Test extends Person {

    public String grade;

    public Test() {
        super();
//        super.getAge();
        System.out.println("this is a teacher");
    }

    public static void main(String[] args){

        Test p = new Test();
        System.out.println(p.grade);
    }
}

