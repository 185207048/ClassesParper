import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Restructure2rowbinary {
    // 思路一
    // 目前的思路就是爆破
    // 首先裂解colsum,找错colsum所有的可能情况，然后把和累加起来看是否满足upper或lower。
    // 思路二
    // 首先裂解colsum,出一系列一定满足lower,然后反过来计算upper

    // 范围
    // 通过观察可以发现，
    // 1、可以发现colsum数组中，如果第i个位置是0，则upper数组和和lower数组在这个位置也一定是0
    // 2、然后如果colsum数组中第i个位置是2，则upper和lower数组在这个位置一定是1
    // 3、由于位置上不是0就是1，所以类似于第二种情况，upper+lower一定等于colsum所有位置之和，所以通过这个规则可以直接取消
    // 通过上面这两个规则可以简单排除一些位置，只用遍历剩下的位置即可
    // 先试思路二
    public int getsum(List<Integer> list){
        int result = list.stream().mapToInt(l -> l).sum();
        return result;
    }
    public List<List<Integer>> getResult(int upper, int lower, List<List<Integer>> lists, List<Integer> position){
        boolean flag = false; // false是没加到， ture是加到了
        // 可以先把upper所有的内容都加成1，直到到达upper，然后将剩下的lower的位置至1
        for (Integer pos : position) {
            // 先判断list[0]想加完是不是等于upper,如果等于了upper直接切换falg，将lower后面的pos至为1
            if(getsum(lists.get(0)) == upper){
                flag = true;
            }
            if(flag){
                lists.get(1).set(pos, 1);
            }else{
                lists.get(0).set(pos, 1);
            }
        }
        return lists;
    }
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        // 初始化
        List<List<Integer>> lists = new ArrayList<>();
        // 计算colsum的和
        int sum = 0;
        for (int i : colsum) {
            sum += i;
        }
        if(upper + lower != sum){
            return lists;
        }
        // 记录位置
        ArrayList<Integer> position = new ArrayList<>();
        lists.add(Arrays.stream(new int[colsum.length]).boxed().collect(Collectors.toCollection(ArrayList::new)));  // upper
        lists.add(Arrays.stream(new int[colsum.length]).boxed().collect(Collectors.toCollection(ArrayList::new)));  // lower
        // 找出0和2的位置,并将内容对应到upper和lower中
        for (int i = 0; i < colsum.length; i++) {
            // 当前数据
            int currentdata = colsum[i];
            if(currentdata == 0){
                lists.get(0).set(i, 0);
                lists.get(1).set(i, 0);
            }else if(currentdata == 2){
                lists.get(0).set(i, 1);
                lists.get(1).set(i, 1);
            }else{
                // 单独获取位置是1的位置
                position.add(i);
            }
        }
        // 如果是1则需要列举出所有的情况进行分类讨论，但是这样的话复杂度过高，所以应该使用递归
        // 这里单独计算是1的情况
        lists = this.getResult(upper, lower, lists, position);
        // return 之前在进行最后一次检查
        if(this.getsum(lists.get(0)) == upper && this.getsum(lists.get(1)) == lower){
            return lists;
        }else{
            lists.remove(0);  // upper
            lists.remove(0);  // lower
            return lists;
        }

    }

    // chatgpt的答案
    public List<List<Integer>> reconstructMatrix_chatgpt(int upper, int lower, int[] colsum) {
        int n = colsum.length;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> up = new ArrayList<>();
        List<Integer> low = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (colsum[i] == 2) {
                up.add(1);
                low.add(1);
                upper--;
                lower--;
            } else {
                up.add(0);
                low.add(0);
            }
        }
        for (int i = 0; i < n; i++) {
            if (colsum[i] == 1) {
                if (upper > 0) {
                    up.set(i, 1);
                    upper--;
                } else {
                    low.set(i, 1);
                    lower--;
                }
            }
        }
        if (upper == 0 && lower == 0) {
            res.add(up);
            res.add(low);
            return res;
        } else {
            return new ArrayList<>();
        }
    }


    public static void main(String[] args) {
        Restructure2rowbinary restructure2rowbinary = new Restructure2rowbinary();
        int[] col = new int[]{0,1,2,0,0,0,0,0,2,1,2,1,2};
        List<List<Integer>> result = restructure2rowbinary.reconstructMatrix(49908,49852, col);
        System.out.println(result);
    }

}
