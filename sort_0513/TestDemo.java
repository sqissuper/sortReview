package sort_0513;

import java.util.Arrays;

/**
 * ClassName:TestDemo
 * Package:sort_0513
 * Description:
 *
 * @Author:HP
 * @date:2021/5/13 8:55
 */
public class TestDemo {
    //直接插入排序
    public static void insertSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int tmp = arr[i];
            int j;
            for(j = i - 1; j >= 0; j--) {
                if(arr[j] > tmp) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = tmp;
        }
    }

    //希尔排序
    public static void hellSort(int[] arr) {
        int[] gap = {5,3,1};
        for (int i = 0; i < gap.length; i++) {
            insertSortGap(arr,gap[i]);
        }
    }
    public static void insertSortGap(int[] arr,int gap) {
        for (int i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            int j;
            for (j = i - gap; j >= 0 ; j -= gap) {
                if(arr[j] > tmp) {
                    arr[j + gap] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + gap] = tmp;
        }
    }

    //选择排序
    public static void selectSort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }
    
    //冒泡排序
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean f = true;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if(arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    f  =false;
                }
            }
            if(f) break;
        }
    }


    //堆排序  （大根堆）
    public static void heap(int[] arr) {
        for(int i = (arr.length - 1 - 1) / 2; i >= 0; i--) {
            heapSort(arr,i,arr.length);
        }
    }
    public static void heapSort(int[] arr,int parent,int len) {
        //孩子节点 = 父母节点 * 2 + 1
        int child = parent * 2 + 1;
        while(child < len) {
            //child + 1  判断是否有右节点
            if(child + 1 < len && arr[child] < arr[child + 1]) {
                child++;
            }
            if(arr[child] > arr[parent]) {
                int tmp = arr[child];
                arr[child] = arr[parent];
                arr[parent] = tmp;
                parent = child;
                child = parent * 2 +1;
            } else {
                //因为是从最后一颗子树建堆的，所以上边建好下边一定是大根堆，直接跳出。
                break;
            }
        }
    }

    //快速排序
    public static void quickSort(int[] arr,int left,int right) {
        if(left < right) {
            //拿到基准值
            int piv = getPivot(arr,left,right);
            //递归左边
            quickSort(arr,left,piv - 1);
            //递归右边
            quickSort(arr,piv + 1,right);
        }
    }
    public static int getPivot(int[] arr,int left,int right) {
        int tmp = arr[left];
        while(left < right) {
            //找右边比tmp小的
            while(left < right && arr[right] > tmp) {
                right--;
            }
            arr[left] = arr[right];

            //找左边比tmp大的
            while(left < right && arr[left] < tmp) {
                left++;
            }
            arr[right] = arr[left];
        }
        //当left = right
        arr[left] = tmp;
        //返回基准值
        return arr[left];
    }

    //归并排序
    public static void mergeSort(int[] arr,int left, int right) {
        if(left >= right) return;
        int mid = (right + left) / 2;
        mergeSort(arr,left,mid);
        mergeSort(arr,mid + 1,right);

        //进行合并
        merge(arr,left,mid,right);
    }

    public static void merge(int[] arr,int start,int mid,int end) {
        int s1 = start;
        int s2 = mid + 1;
        //定义一个新数组合并原数组
        int[] ret = new int[end - start + 1];
        int k = 0;
        while(s1 <= mid && s2 <= end) {
            if(arr[s1] <= arr[s2]) {
                ret[k++] = arr[s1++];
            } else {
                ret[k++] = arr[s2++];
            }
        }
        //有可能第一段还有数据
        while(s1 <= mid) {
            ret[k++] = arr[s1++];
        }
        //有可能第二段还有数据
        while(s2 <= end) {
            ret[k++] = arr[s2++];
        }

        //将ret中的数据接在a原数组中
        for (int i = 0; i < ret.length; i++) {
            arr[i + start] = ret[i];
        }
    }
    public static void main(String[] args) {
        int[] arr = {10,6,7,1,3,9,4,2};
//        insertSort(arr);
//        hellSort(arr);
//        selectSort(arr);
//        bubbleSort(arr);
//        heap(arr);
//        quickSort(arr,0,arr.length - 1);
        mergeSort(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
