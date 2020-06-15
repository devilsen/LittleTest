package com.wuba.acm.sort;

/**
 * desc : 堆排序
 * date : 2018/4/3
 *
 * @author : dongSen
 * <p>
 * https://mp.weixin.qq.com/s/t-GkSN5EEL-lplM65OeSig
 * <p>
 * 时间：O(nlogn)
 * 空间：O(1)
 * 不稳定排序
 */
public class HeapSort {

    public static void main(String[] args) {
        // 初始化一个序列
        int[] array = {
                1, 3, 4, 5, 2, 6, 9, 7, 8, 0
        };

        // 调用快速排序方法
        HeapSort heap = new HeapSort();
        System.out.print("排序前:	");
        heap.printPart(array, 0, array.length - 1);
        heap.heapSort(array);
        System.out.print("排序后:	");
        heap.printPart(array, 0, array.length - 1);
    }

    private void heapSort(int[] list) {
        // 循环建立初始堆
        for (int i = list.length / 2; i >= 0; i--) {
            heapAdjust(list, i, list.length);
        }

        // 进行n-1次循环，完成排序
        for (int i = list.length - 1; i > 0; i--) {
            // 最后一个元素和第一元素进行交换
            int temp = list[i];
            list[i] = list[0];
            list[0] = temp;

            // 筛选 R[0] 结点，得到i-1个结点的堆
            heapAdjust(list, 0, i);
            System.out.format("第 %d 趟: 	", list.length - i);
            printPart(list, 0, list.length - 1);
        }
    }

    private void heapAdjust(int[] array, int parent, int length) {
        int temp = array[parent]; // temp保存当前父节点
        int child = 2 * parent + 1; // 先获得左孩子

        while (child < length) {
            // 如果有右孩子结点，并且右孩子结点的值大于左孩子结点，则选取右孩子结点
            if (child + 1 < length && array[child] < array[child + 1]) {
                child++;
            }

            // 如果父结点的值已经大于孩子结点的值，则直接结束
            if (temp >= array[child])
                break;

            // 把孩子结点的值赋给父结点
            array[parent] = array[child];

            // 选取孩子结点的左孩子结点,继续向下筛选
            parent = child;
            child = 2 * child + 1;
        }

        array[parent] = temp;
    }

    // 打印序列
    private void printPart(int[] list, int begin, int end) {
        for (int i = 0; i < begin; i++) {
            System.out.print("	");
        }
        for (int i = begin; i <= end; i++) {
            System.out.print(list[i] + "	");
        }
        System.out.println();
    }

}
