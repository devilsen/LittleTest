package com.wuba.acm.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * desc : 无权图最短路径
 * date : 2019/4/8
 *
 * @author : dongSen
 */
public class UnWeightMinDistance {

    private static List<Graph> graphMap = new LinkedList<>();

    static class Graph {
        String name;
        int dist;
        LinkedList<Graph> adj = new LinkedList<>();
        Graph latest;

        Graph(String name) {
            this.name = name;
        }
    }

    private static void unWeight(Graph s) {
        LinkedList<Graph> queue = new LinkedList<>();
        s.dist = 0;
        queue.push(s);

        while (queue.size() != 0) {
            Graph start = queue.poll();
            for (Graph sadj : start.adj) {
                if (sadj.dist == Integer.MAX_VALUE) {
                    sadj.dist = start.dist + 1;
                    sadj.latest = start;
                    queue.push(sadj);
                }
            }
        }
    }

    public static Graph find(String name) {
        for (Graph graph : graphMap) {
            if (graph.name.equals(name))
                return graph;
        }
        return null;
    }

    private static void printPath(String graphname) {
        Graph graph = find(graphname);
        if (graph.latest != null) {
            printPath(graph.latest.name);
            System.out.print("to");
        }
        System.out.println(graph.name);
    }

    public static void main(String[] args) {
        Graph v1 = new Graph("v1");
        Graph v2 = new Graph("v2");
        Graph v3 = new Graph("v3");
        Graph v4 = new Graph("v4");
        Graph v5 = new Graph("v5");
        Graph v6 = new Graph("v6");
        Graph v7 = new Graph("v7");

        graphMap.add(v1);
        graphMap.add(v2);
        graphMap.add(v3);
        graphMap.add(v4);
        graphMap.add(v5);
        graphMap.add(v6);
        graphMap.add(v7);

        for (Graph g : graphMap) {
            g.dist = Integer.MAX_VALUE;
            g.latest = null;
        }

        v1.adj.add(v2);
        v1.adj.add(v4);

        v2.adj.add(v4);
        v2.adj.add(v5);

        v3.adj.add(v1);
        v3.adj.add(v6);

        v4.adj.add(v3);
        v4.adj.add(v5);
        v4.adj.add(v6);
        v4.adj.add(v7);

        v5.adj.add(v7);

        v7.adj.add(v6);

        List<Graph> vs = new ArrayList<>();
        vs.add(v1);
        vs.add(v2);
        vs.add(v3);
        vs.add(v4);
        vs.add(v5);
        vs.add(v6);
        vs.add(v7);
        unWeight(v3);
        System.out.println("The shortest path from v3:");
        for (int i = 1; i <= 7; i++) {
            printPath("v" + i);
        }
    }
}
