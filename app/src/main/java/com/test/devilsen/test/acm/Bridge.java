package com.test.devilsen.test.acm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * desc :
 * date : 2018/4/3
 *
 * @author : dongSen
 *         1.过河问题：问题描述：在漆黑的夜里，N位旅行者来到了一座狭窄而且没有护栏的桥边。
 *         如果不借助手电筒的话，大家是无论如何也不敢过桥去的。不幸的是，N个人一共只带了一只手电筒，
 *         而桥窄得只够让两个人同时过。如果各自单独过桥的话，N人所需要的时间已知；
 *         而如果两人同时过桥，所需要的时间就是走得比较慢的那个人单独行动时所需的时间。
 *         问题是，如何设计一个方案，让这N人尽快过桥。假设N <= 1000。
 */
public class Bridge {

    private static ArrayList<Integer> star = new ArrayList<>();
    private static ArrayList<Integer> end = new ArrayList<>();

    public static void main(String[] args) {

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            star.add(random.nextInt(100));
//            star.add(i + 1);
        }

        Collections.sort(star);

        int strategies = analysis();
        if (strategies == 1){
            strategies1();
        }else{
            strategies2();
        }
    }

    private static int analysis() {
        if (star.size() > 3) {
            if (2 * star.get(1) > star.get(0) + star.get(star.size() -2)){
                return 1;
            }else{
                return 2;
            }
        }
        return 0;
    }

    private static void strategies1() {
        //找出走的最快的
        int fastest = findFastest(star);

        int size = star.size();
        for (int i = 1; i < size; i++) {
            Integer person = star.get(i);

//            System.out.println("go: " + person + "  " + fastest);

            end.add(person);

            if (i != size - 1) {
                System.out.println("back: " + fastest);
            } else {
                end.add(fastest);
            }
        }
    }

    private static void strategies2() {

    }

    private static int findFastest(ArrayList<Integer> star) {
        return star.get(0);
    }

}
