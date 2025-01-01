package main.DS.sort;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class MySort {
    /**
     * 名称：直接插入排序
     * 时间复杂度：
     *          最坏时： O(n^2)
     *          最好时： O(n)
     * 空间复杂度：O(1)
     * 稳定性：稳定
     */
    public void insertSort(int[] nums){
        for(int i = 1; i < nums.length; i++){
            if(nums[i] > nums[i-1]){
                continue;
            }
            int tmp = nums[i];
            int j = i - 1;
            for(;j >= 0 && nums[j] > tmp; j--){
                nums[j+1] = nums[j];
            }
            nums[j+1] = tmp;
        }
    }
    public void insertSort(int[] nums, int start, int end){
        if(start >= nums.length || start > end){
            throw new UnsupportedOperationException("error start");
        }
        if(end >= nums.length){
            end = nums.length - 1;
        }
        for(int i = start + 1; i <= end; i++){
            if(nums[i] > nums[i-1]){
                continue;
            }
            int tmp = nums[i];
            int j = i - 1;
            for(;j >= start && nums[j] > tmp; j--){
                nums[j+1] = nums[j];
            }
            nums[j+1] = tmp;
        }
    }
    public void halfInsertSort(int[] nums){
        for(int i = 1; i < nums.length; i++){
            if(nums[i] > nums[i-1]){
                continue;
            }
            int left = 0, right = i - 1;
            while(left < right){
                int mid = (left + right) / 2;
                if(nums[mid] < nums[i]){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
            int tmp = nums[i];
            for(int j = i; j > left; j--){
                nums[j] = nums[j-1];
            }
            nums[left] = tmp;
        }
    }
    /**
     * 名称：选择排序
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * 稳定性：不稳定，有跳跃式变化
     */
    public void shellSort(int[] nums){
        for(int gap = nums.length / 2; gap >= 1; gap = gap / 2){
            for(int i = gap; i < nums.length; i = i + gap){
                if(nums[i] > nums[i - gap]){
                    continue;
                }
                int tmp = nums[i];
                int j = i - gap;
                for(; j >= 0 && nums[j] > tmp; j = j - gap){
                    nums[j + gap] = nums[j];
                }
                nums[j + gap] = tmp;
            }
        }
    }
    public void swap(int[] nums, int i, int j){
        int tmp=nums[i];
        nums[i]=nums[j];
        nums[j]=tmp;
    }
    /**
     * 名称：冒泡排序
     * 时间复杂度：
     *            最坏时：O(n^2)
     *            最好时：O(n)
     * 空间复杂度：O(1)
     * 稳定性：稳定
     */
    public void bubbleSort(int[] nums){
        for(int i = 0; i < nums.length; i++){
            boolean isSwap = false;
            for(int j = nums.length - 1; j > i; j--){
                if(nums[j] > nums[j-1]){
                    continue;
                }
                isSwap = true;
                swap(nums, j - 1, j);
            }
            if(!isSwap){
                break;
            }
        }
    }
    /**
     * 名称：选择排序
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     * 稳定性：不稳定，有跳跃式变化
     */
    public void selectSort(int[] nums){
        for(int i = 0; i < nums.length; i++){
            int min = i;
            for(int j = i+1; j < nums.length; j++){
                if(nums[j] < nums[min]){
                    min = j;
                }
            }
            swap(nums, i, min);
        }
    }

    /**
     * 1、建堆
     * 2、进行调整
     * 名称：堆排序
     * 时间复杂度:O(n*log2(n))
     * 空间复杂度:O(1)
     * 稳定性：不稳定
     */
    public void heapSort(int[] nums){
        for(int i = nums.length / 2-1; i >= 0; i--){
            adjustDown(nums, i, nums.length);
        }
        int end = nums.length - 1;
        while(end > 0){
            swap(nums, 0, end);
            adjustDown(nums, 0, end - 1);
            end--;
        }
    }
    private void adjustDown(int[] nums, int root, int end){
        int parent = root;
        int child = 2 * root + 1;
        while(child < end){
            if(child + 1 < end && nums[child + 1] > nums[child]){
                child++;
            }
            if(nums[parent] > nums[child]){
                break;
            }
            swap(nums, parent, child);
            parent = child;
            child = 2* child + 1;
        }
    }
    /**
     * 1、快速排序
     * 2、递归和非递归两种写法
     * 3、主要实现有挖坑填坑法以及霍尔法
     * 4、优化：固定下标，随机下标，三数取中法
     * 5、当数据量较低时可以进行直接插入排序
     * 5、时间复杂都：
     *              最好时：O(n*log2(n))
     *              最坏时：O(n^2)
     * 6、空间复杂度：
     *              最好时：O(log2(n))
     *              最坏时：O(n)
     * 稳定性：不稳定
     */
    public void quickSort(int[] nums, int mode){
        if(mode == 1){
            quickSort1(nums, 0, nums.length - 1);
        } else if (mode == 2) {
            quickSort2(nums, 0, nums.length - 1);
        } else if(mode == 3){
            quickSort3(nums);
        }
    }
    private void selectPivotMidOfThree(int[] nums,int start,int end){
        //nums[mid] <= nums[start] <= nums[end]
        int mid = (start + end) / 2;
        if(nums[mid] > nums[start]) {
            swap(nums, mid, start);
        }
        if(nums[mid] > nums[end]) {
            swap(nums, mid, end);
        }
        if(nums[mid] > nums[start]) {
            swap(nums, mid, start);
        }
    }
    // 挖坑填坑法
    public void quickSort1(int[] nums, int left, int right){
        if(left >= right) return;
        // 数据量少可以使用其他算法

        int i = left, j = right;
        int pivot = nums[i];
        while(i < j){
            while(j > i && nums[j] >= pivot){
                j--;
            }
            nums[i] = nums[j];
            while(j > i && nums[i] <= pivot){
                i++;
            }
            nums[j] = nums[i];
        }
        nums[i] = pivot;
        quickSort1(nums, left, i);
        quickSort1(nums,i + 1, right);
    }
    public void quickSort2(int[] nums, int left, int right){
        if(left >= right) {
            return;
        }
        // 数据量少可以使用其他算法
        int i = left;
        int j = right;
        int pivot = nums[left];
        while(i < j){
            while(i < j && nums[j] >= pivot){
                j--;
            }
            while(i < j && nums[i] <= pivot){
                i++;
            }
            swap(nums, i, j);
        }
        swap(nums, i, left);
        quickSort2(nums, left, i);
        quickSort2(nums,i + 1, right);
    }
    public void quickSort3(int[] nums){
        Stack<Integer> stack=new Stack<>();
        stack.push(0);
        stack.push(nums.length - 1);
        while(!stack.empty()){
            int end = stack.pop();
            int start = stack.pop();
//            selectPivotMidOfThree(nums, start, end);
            int pivot = partition(nums, start, end);
            if(start < pivot - 1){
                stack.push(start);
                stack.push(pivot-1);
            }
            if(end > pivot + 1){
                stack.push(pivot+1);
                stack.push(end);
            }
        }
    }
    public int partition(int[] nums, int left, int right){
        int tmp = nums[left];
        while(left < right){
            while(left < right && nums[right] >= tmp){
                right--;
            }
            nums[left] = nums[right];
            while(left < right && nums[left] <= tmp){
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = tmp;
        return left;
    }
    public void mergeSort(int[] nums, int num){
        if(num == 1) {
            mergeInterval(nums,0, nums.length - 1);
        }
        if(num == 2) {
            mergeSort(nums);
        }
    }
    private void mergeInterval(int[] nums,int left,int right){
        if(left == right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeInterval(nums, left, mid);
        mergeInterval(nums,mid + 1, right);
        mergeAnd(nums, left, right);
    }

    private void mergeAnd(int[] nums, int left, int right){
        int s1 = left;
        int e1 = (left + right) / 2;
        int s2 = e1 + 1;
        int e2 = right;
        int[] tmp = new int[right - left + 1];
        int k = 0;
        while(s1 <= e1 && s2 <= e2){
            if(nums[s1] < nums[s2]){
                tmp[k++] = nums[s1++];
            }else{
                tmp[k++] = nums[s2++];
            }
        }
        while(s1 <= e1){
            tmp[k++] = nums[s1++];
        }
        while(s2 <= e2){
            tmp[k++] = nums[s2++];
        }
        for(int i = 0; i < tmp.length; i++){
            nums[i + left] = tmp[i];
        }
    }
    private void mergeSort(int[] nums){
        for(int i = 1; i< nums.length; i *= 2){
            mergeAnd(nums, i);
        }
    }
    private void mergeAnd(int[] nums,int gap){
        int[] tmp = new int[nums.length];
        int s1 = 0;
        int e1 = s1 + gap - 1;
        int s2 = e1 + 1;
        int e2 = s2 + gap - 1 >= nums.length ? nums.length - 1: s2 + gap - 1;
        int k = 0;
        while(s2 < nums.length){
            while(s1 <= e1 && s2 <= e2){
                tmp[k++] = nums[s1] <= nums[s2]? nums[s1++]: nums[s2++];
            }
            while(s1 <= e1){
                tmp[k++] = nums[s1++];
            }
            while(s2 <= e2){
                tmp[k++] = nums[s2++];
            }
            s1 = e2 + 1;
            e1 = s1 + gap - 1;
            s2 = e1 + 1;
            e2 = s2 + gap - 1 >= nums.length? nums.length-1: s2+gap-1;
        }
        while(s1 < nums.length){
            tmp[k++] = nums[s1++];
        }
        for(int i = 0; i < nums.length; i++){
            nums[i] = tmp[i];
        }
    }

    public static void main(String[] args) {
        MySort mySort=new MySort();
        Random random = new Random();
        int[] nums1=new int[30];
        for(int i=0;i< nums1.length;i++){
            nums1[i]= random.nextInt(100);
        }
        int[] nums2 = nums1.clone();
        int[] nums3 = nums1.clone();
        int[] nums4 = nums1.clone();
        int[] nums5 = nums1.clone();
        int[] nums6 = nums1.clone();
        int[] nums7 = nums1.clone();
        int[] nums8 = nums1.clone();
        int[] nums9 = nums1.clone();
        int[] nums10 = nums1.clone();
        long start, end;

        start = System.currentTimeMillis();
        mySort.insertSort(nums1);
        end = System.currentTimeMillis();
        System.out.println(Arrays.toString(nums1));
        System.out.println("插入排序：" + (end - start));

        start = System.currentTimeMillis();
        mySort.shellSort(nums2);
        end = System.currentTimeMillis();
        System.out.println(Arrays.toString(nums2));
        System.out.println("希尔排序："+ (end - start));

        start = System.currentTimeMillis();
        mySort.bubbleSort(nums3);
        end = System.currentTimeMillis();
        System.out.println(Arrays.toString(nums3));
        System.out.println("冒泡排序："+ (end - start));

        start = System.currentTimeMillis();
        mySort.selectSort(nums4);
        end = System.currentTimeMillis();
        System.out.println(Arrays.toString(nums4));
        System.out.println("选择排序1："+ (end - start));


        start = System.currentTimeMillis();
        mySort.heapSort(nums5);
        end = System.currentTimeMillis();
        System.out.println(Arrays.toString(nums5));
        System.out.println("堆排序：" + (end - start));

        start = System.currentTimeMillis();
        mySort.quickSort(nums6,1);
        end = System.currentTimeMillis();
        System.out.println(Arrays.toString(nums6));
        System.out.println("快速排序1："+(end - start));

        start = System.currentTimeMillis();
        mySort.quickSort(nums7,2);
        end = System.currentTimeMillis();
        System.out.println(Arrays.toString(nums7));
        System.out.println("快速排序2："+ (end - start));

        start = System.currentTimeMillis();
        mySort.quickSort(nums8,3);
        end = System.currentTimeMillis();
        System.out.println(Arrays.toString(nums8));
        System.out.println("快速排序3：" + (end - start));

        start = System.currentTimeMillis();
        mySort.mergeSort(nums9,1);
        end = System.currentTimeMillis();
        System.out.println(Arrays.toString(nums9));
        System.out.println("归并排序1：" + (end - start));

        start = System.currentTimeMillis();
        mySort.mergeSort(nums10,2);
        end = System.currentTimeMillis();
        System.out.println(Arrays.toString(nums10));
        System.out.println("归并排序2：" + (end - start));
    }

}
