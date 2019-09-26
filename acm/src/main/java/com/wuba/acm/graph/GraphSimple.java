package com.wuba.acm.graph;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * desc : 遍历ViewGroup中的所有子元素
 * link : https://mp.weixin.qq.com/s/6DAUZYU7i9Hv01sxul20dw
 * date : 2019-09-26 11:23
 *
 * @author : dongSen
 */
public class GraphSimple {

    private static class View {
        String name;

        View(String name) {
            this.name = name;
        }
    }

    private static class ViewGroup extends View {
        ArrayList<View> viewList;

        ViewGroup(String name, ArrayList<View> viewList) {
            super(name);
            this.viewList = viewList;
        }
    }

    private void printView(View view) {
        System.out.print(view.name + " ");
    }

    private void recursionPrint(View root) {
        printView(root);
        if (root instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) root;
            ArrayList<View> viewList = group.viewList;
            for (View childView : viewList) {
                recursionPrint(childView);
            }
        }

    }

    private void breadthFirst(View root) {
        LinkedList<View> queue = new LinkedList<>();
        queue.push(root);
        while (!queue.isEmpty()) {
            View view = queue.pollLast();
            if (view == null) {
                continue;
            }
            printView(view);
            if (view instanceof ViewGroup) {
                ViewGroup group = (ViewGroup) view;
                for (View child : group.viewList) {
                    queue.addFirst(child);
                }
            }
        }
    }

    private void depthFirst(View root) {
        LinkedList<View> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            View view = stack.pollFirst();
            if (view == null) {
                continue;
            }
            printView(view);
            if (view instanceof ViewGroup) {
                ViewGroup group = (ViewGroup) view;
                for (View child : group.viewList) {
                    stack.addFirst(child);
                }
            }
        }
    }

    public static void main(String[] args) {
        View childE = new View("E");
        View childF = new View("F");
        View childG = new View("G");
        ArrayList<View> listB = new ArrayList<>();
        listB.add(childE);
        listB.add(childF);
        listB.add(childG);

        ViewGroup groupB = new ViewGroup("B", listB);

        View childC = new View("C");

        View childH = new View("H");
        View childI = new View("I");
        ArrayList<View> listD = new ArrayList<>();
        listD.add(childH);
        listD.add(childI);

        ViewGroup groupD = new ViewGroup("D", listD);

        ArrayList<View> listA = new ArrayList<>();
        listA.add(groupB);
        listA.add(childC);
        listA.add(groupD);
        ViewGroup root = new ViewGroup("A", listA);

        GraphSimple graphSimple = new GraphSimple();
        graphSimple.recursionPrint(root);
        System.out.println();
        graphSimple.breadthFirst(root);
        System.out.println();
        graphSimple.depthFirst(root);
    }

}
