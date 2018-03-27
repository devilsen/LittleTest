package com.test.devilsen.test;


import org.json.JSONArray;

import java.util.ArrayList;

public class TestJava {

    public static void main(String[] args)  {
        int value = 10;
//        System.out.println(value << 1);
//        System.out.println(value >> 1);
//        System.out.println(value >>> 1);
//
//
//        Integer a = 1000;
//        Integer b = 1000;
////        System.out.print("10".equals(a));
//        System.out.println(b.equals(a));
//        System.out.println(b == a);//-128~127

//        try {
//            Test test = (Test) Class.forName(Test.class.getName()).newInstance();
//            test.show();
//
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

//        int a = 10;
//        int b = 100;
//
//        int c = (int) ((float)a / b * 100);
//
//        System.out.println("num : " + c);

//        String ia = Integer.toBinaryString(a);
//
//        int b = Integer.highestOneBit(a);

//        ArrayList<String> list = new ArrayList<>(2);
//
//        for (int i = 0; i < 8; i++) {
//            list.add("ss" + i);
//        }
//
//        System.out.println(list.size());

        ArrayList<Movie> list = new ArrayList<>();
        for (int i = 0; i <10; i++) {
            list.add(new Movie("title " + i, "name " + i));
        }

//        JSONObject jsonObject = new JSONObject();
//        jsonObject.toJSONArray();
        JSONArray jsonArray = new JSONArray(list);
        System.out.println(jsonArray.toString());
    }

    static class Movie {
        public String title;
        public String name;

        public Movie(String title, String name) {
            this.title = title;
            this.name = name;
        }
    }

    private static void getHigh() {
        int a = 1001;

        int v = (int) Math.log10(a);

        int b = (int) Math.pow(10, v);

        int high = a / b + 1;

        int num = high * b;

        System.out.println("test " + v);
        System.out.println("test " + b);
        System.out.println("test " + high);
        System.out.println("test " + num);
    }

    public static class Test {

        public void show() {
            System.out.print("test");
        }

    }

}
