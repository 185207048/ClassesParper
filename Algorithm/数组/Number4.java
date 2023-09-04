import java.util.Arrays;

public class Number4 {
//    给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
//    算法的时间复杂度应该为 O(log (m+n)) 。
//
//
//    示例 1：
//
//    输入：nums1 = [1,3], nums2 = [2]
//    输出：2.00000
//    解释：合并数组 = [1,2,3] ，中位数 2
//    示例 2：
//
//    输入：nums1 = [1,2], nums2 = [3,4]
//    输出：2.50000
//    解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
/*
      1、合并
      2、排序
      3、取中计算
**
 */

    public static void main(String[] args) {
        int nums1[] = {1,2};
        int nums2[] = {-1,3};
        int result[] = new int[nums1.length + nums2.length];

        // 1、合并
        for(int i = 0 ; i < result.length ; i++){
            if(i < nums1.length){
                result[i] = nums1[i];
            }else if(i >= nums1.length){
                result[i] = nums2[i - nums1.length];
            }
        }
        Arrays.stream(result).forEach(System.out::println);
        System.out.println();

        // 2、正向排序
//        for (int i = 0 ; i < result.length - 1 ; i++){
//            result = sort(result, i, i+1);
//        }


        // 3、计算中位数
        // 获取中间位置:
        result = Arrays.stream(result).sorted().toArray();
        Arrays.stream(result).forEach(System.out::println);
        // 偶数：
        if(result.length % 2 == 0){
            double dr1 =  result[result.length/2 -1 ];
            double dr2 =  result[result.length/2];
            System.out.println((dr1 + dr2)/2);
//            return (result[result.length/2 -1 ] + result[result.length/2])/2;
        }else{
            System.out.println(result[result.length/2]);
//            return result[result.length/2 - 1]
        }
    }

    // into 是源数组，flag_postion是第一个下标，number_postion是第二个下标，由于数组要从小到大排序，所以flag_postion一定小于number_postion
    public static int[] sort(int[] into, int flag_postion, int number_postion){
        if(into[number_postion] == into[flag_postion]){
            if(number_postion + 1 >= into.length){
                return into;
            }
            sort(into, number_postion, number_postion + 1);
            return into;
        }
        if(into[flag_postion] > into[number_postion]){
            int flag = into[flag_postion];
            into[flag_postion] = into[number_postion];
            into[number_postion] = flag;
            if(number_postion + 1 >= into.length){
                return into;
            }
            sort(into, number_postion, number_postion + 1);
            return into;
        }else{
            if(number_postion + 1 >= into.length){
                return into;
            }
            sort(into, number_postion, number_postion + 1);
            return into;
        }
    }

}
