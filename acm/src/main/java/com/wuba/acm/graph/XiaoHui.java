package com.wuba.acm.graph;

import java.util.LinkedList;

/**
 * desc : https://mp.weixin.qq.com/s/WA5hQXkcACIarcdVnRnuiw
 * date : 2019/3/25
 *
 * @author : dongSen
 */
public class XiaoHui {


    /**
     * 图的顶点
     */
    private static class Vertex {
        int data;

        Vertex(int data) {
            this.data = data;
        }
    }

    /**
     * 图（邻接表形式）
     */
    private static class Graph {
        private int size;
        private Vertex[] vertexes;
        private LinkedList<Integer>[] lists;

        Graph(int size) {
            this.size = size;
            //初始化顶点和邻接矩阵
            vertexes = new Vertex[size];
            lists = new LinkedList[size];
            for (int i = 0; i < size; i++) {
                vertexes[i] = new Vertex(i);
                lists[i] = new LinkedList<>();
            }
        }
    }

    /**
     * 深度优先遍历
     */
    private static void dfs(Graph graph, int start, boolean[] visited) {
        System.out.println(graph.vertexes[start].data);//打印该结点
        visited[start] = true;//用数组并不会影响，因为它访问的是某个结点，这些结点的index都是唯一的
        for (int index : graph.lists[start]) {
            if (!visited[index]) {
                dfs(graph, index, visited);
            }
        }
    }

    private static void dfs1(Graph graph, int start, boolean[] visited) {
        System.out.println(graph.vertexes[start].data);
        visited[start] = true;
        for (int index : graph.lists[start]) {
            if (!visited[start]) {
                dfs1(graph, index, visited);
            }
        }
    }

    /**
     * 广度优先遍历
     */
    private static void bfs(Graph graph, int start, boolean[] visited, LinkedList<Integer> queue) {
        queue.offer(start);

        while (!queue.isEmpty()) {
            int front = queue.poll();
            if (visited[front]) {
                continue;
            }

            System.out.println(graph.vertexes[front].data);
            visited[front] = true;
            for (int index : graph.lists[front]) {
                queue.offer(index);
            }
        }
    }

    private static void bfs1(Graph graph, int start, boolean[] visited, LinkedList<Integer> queue) {
        queue.offer(start);

        while (!queue.isEmpty()) {
            int front = queue.poll();
            if (visited[front]) {
                continue;
            }

            System.out.println(graph.vertexes[front].data);
            visited[front] = true;
            for (int i = 0; i < graph.lists[front].size(); i++) {
                queue.offer(graph.lists[front].get(i));
            }
        }
    }


    public static void main(String[] args) {
        Graph graph = new Graph(6);
        graph.lists[0].add(1);
        graph.lists[0].add(2);
        graph.lists[0].add(3);
        graph.lists[1].add(0);
        graph.lists[1].add(3);
        graph.lists[1].add(4);
        graph.lists[2].add(0);
        graph.lists[3].add(0);
        graph.lists[3].add(1);
        graph.lists[3].add(4);
        graph.lists[3].add(5);
        graph.lists[4].add(1);
        graph.lists[4].add(3);
        graph.lists[4].add(5);
        graph.lists[5].add(3);
        graph.lists[5].add(4);

        System.out.println("图的深度优先遍历：");
        dfs(graph, 0, new boolean[graph.size]);
        System.out.println("图的广度优先遍历：");
        bfs(graph, 0, new boolean[graph.size], new LinkedList<Integer>());
    }

}
