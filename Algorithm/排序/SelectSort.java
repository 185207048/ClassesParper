import java.util.Arrays;

// 选择排序,时间复杂度为O(n^2)
public class SelectSort {
    public static int[] selectsort(int[] arr){
        // 把arr[]第一个下标进行记录
        // 然后依次和后面进行比较，如果发现比这个坐标的数据更小的则进行更换
        int flag;
        int num;
        for (int i = 0; i < arr.length; i++) {
            flag = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[flag] > arr[j]){
                    flag = j;
                }
            }
            if (flag != i){
                num = arr[flag];
                arr[flag] = arr[i];
                arr[i] = num;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int sort[] = {1,2,3,0,-1,2,20,900,4,5,80,22291};
        sort = selectsort(sort);
        Arrays.stream(sort).forEach(System.out::println);
    }
}
