package com.wuba.acm.dynamicprograming;

/**
 * desc : 0-1背包问题
 * date : 2018/12/27
 *
 * @author : dongSen
 */
public class _01package {

    //weight: 物品重量，n: 物品个数，w: 背包可承载重量
    public int knapsack(int[] weight, int n, int w) {
        boolean[][] states = new boolean[n][w + 1]; // 默认值 false
        states[0][0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        states[0][weight[0]] = true;
        for (int i = 1; i < n; ++i) { // 动态规划状态转移
            for (int j = 0; j <= w; ++j) {// 不把第 i 个物品放入背包
                if (states[i - 1][j] == true)
                    states[i][j] = states[i - 1][j];
            }
            for (int j = 0; j <= w - weight[i]; ++j) {// 把第 i 个物品放入背包
                if (states[i - 1][j] == true)
                    states[i][j + weight[i]] = true;
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[n - 1][i] == true)
                return i;
        }
        return 0;
    }

    public static void main(String[] args) {
        _01package pa = new _01package();
        int[] things = new int[]{2, 2, 4, 6, 3};
        int knapsack = pa.knapsack(things, things.length, 9);

        System.out.println(knapsack);
    }


}
