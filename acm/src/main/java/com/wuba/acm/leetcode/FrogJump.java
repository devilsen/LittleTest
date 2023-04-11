package com.wuba.acm.leetcode;

/**
 * Created by dongSen on 2023/3/2
 * <p>
 * 青蛙跳台阶问题汇总
 * https://blog.51cto.com/u_15641791/5298981
 */
public class FrogJump {

    /**
     * 1. 一只青蛙一次可以跳上 1 级台阶，也可以跳上2 级。求该青蛙跳上一个n 级的台阶总共有多少种跳法。
     *
     * @param target
     * @return
     */
    public int jumpFloor(int target) {
        if (target <= 0) {
            return -1;
        } else if (target == 1) {
            return 1;
        } else if (target == 2) {
            return 2;
        } else {
            return jumpFloor(target - 1) + jumpFloor(target - 2);
        }

    }

    /**
     * 2.一只青蛙一次可以跳上1级台阶，也可以跳上2 级…它也可以跳上n 级，此时该青蛙跳上一个n级的台阶总共有多少种跳法？
     *
     * @param target
     * @return
     */
    public int jumpFloorII(int target) {
        if (target <= 1) {
            return 1;
        }
        return 2 * jumpFloorII(target - 1);
    }

    /**
     * 3.一只青蛙一次可以跳上1级台阶，也可以跳上2级…它也可以跳上n级。求该青蛙跳上一个m级的台阶总共有多少种跳法。
     *
     * @param n
     * @param m
     * @return
     */
    public static int jumpFloorII(int n, int m) {
        //当大于m的时候是上面的公式
        if (n > m) {
            return 2 * jumpFloorII(n - 1, m) - jumpFloorII(n - 1 - m, m);
        }
        //当小于等于m的时候就是和n级的相同了
        if (n <= 1) {
            return 1;
        } else {
            return 2 * jumpFloorII(n - 1, n);
        }
    }


}
