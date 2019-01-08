package com.wuba.acm.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * desc : 邻接表，两种搜索方式
 * date : 2018/12/25
 * 来源 : https://time.geekbang.org/column/article/70891
 *
 * @author : dongSen
 * <p>
 * vertex : 顶点
 * Edge : 边
 */
public class Graph { // 无向图

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 4);
        graph.addEdge(3, 4);
        graph.addEdge(1, 2);
        graph.addEdge(2, 5);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(4, 6);
        graph.addEdge(6, 7);
        graph.addEdge(5, 7);

        graph.bfs(0, 6);
        graph.dfs(0, 6);
    }

    private int v; // 顶点的个数
    private LinkedList<Integer> adj[]; // 邻接表

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }

    /**
     * 添加边
     *
     * @param s 顶点s
     * @param t 顶点t
     */
    public void addEdge(int s, int t) { // 无向图一条边存两次
        adj[s].add(t);
        adj[t].add(s);
    }

    /**
     * 广度优先搜索(Breadth-First-Search)
     *
     * @param s
     * @param t
     */
    public void bfs(int s, int t) {
        if (s == t) return;
        boolean[] visited = new boolean[v];
        visited[s] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        int[] prev = new int[v];
        for (int i = 0; i < v; ++i) {
            prev[i] = -1;
        }
        while (queue.size() != 0) {
            int w = queue.poll();
            for (int i = 0; i < adj[w].size(); ++i) {
                int q = adj[w].get(i);
                if (!visited[q]) {
                    prev[q] = w;
                    if (q == t) {
                        print(prev, s, t);
                        System.out.println();
                        return;
                    }
                    visited[q] = true;
                    queue.add(q);
                }
            }
        }
    }


    /**
     * 深度优先搜索
     * depth-first-search
     */
    private boolean found = false; // 全局变量或者类成员变量

    public void dfs(int s, int t) {
        found = false;
        boolean[] visited = new boolean[v];
        int[] prev = new int[v];
        for (int i = 0; i < v; ++i) {
            prev[i] = -1;
        }
        recurDfs(s, t, visited, prev);
        print(prev, s, t);
        System.out.println();
    }

    private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
        if (found) return;
        visited[w] = true;
        if (w == t) {
            found = true;
            return;
        }
        for (int i = 0; i < adj[w].size(); ++i) {
            int q = adj[w].get(i);
            if (!visited[q]) {
                prev[q] = w;
                recurDfs(q, t, visited, prev);
            }
        }
    }

    /**
     * 打印数据
     *
     * @param prev
     * @param s
     * @param t
     */
    private void print(int[] prev, int s, int t) { // 递归打印 s->t 的路径
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }
}
