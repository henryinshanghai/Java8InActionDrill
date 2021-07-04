package com.henry.catalog.chapter05_stream_usage;


import com.henry.catalog.chapter04_data_process.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.henry.catalog.chapter04_data_process.Dish.menu;

public class NumericStreams_06 {

    public static void main(String...args){
    
        List<Integer> numbers = Arrays.asList(3,4,5,1,2);

        Arrays.stream(numbers.toArray()).forEach(System.out::println);

        // 用法1：把数据映射到数值流(避免拆装箱成本)
        System.out.println("======= 把数据映射到数值流(避免拆装箱成本) 👇 =======");
        int calories = menu.stream()
                           .mapToInt(Dish::getCalories)
                           .sum(); // 求和操作
        System.out.println("Number of calories:" + calories);


        // max and OptionalInt
        // 计算菜肴的最大卡路里
        OptionalInt maxCalories = menu.stream()
                                      .mapToInt(Dish::getCalories)
                                      .max(); // 求最值操作

        int max;
        if(maxCalories.isPresent()){
            max = maxCalories.getAsInt();
        }
        else {
            // we can choose a default value
            max = 1;
        }
        System.out.println(max);


        // 用法2：把数值流转换回 对象流 - IntStream.boxed()
        // 用法3：使用数值流 来 生成数据范围 - IntStream.range() 、rangeClosed() 【also LongStream】
        // numeric ranges
        System.out.println("==== 1-100返回内所有的偶数的个数（包含100）👇 ===");
        IntStream evenNumbers = IntStream.rangeClosed(1, 100) // 右闭
                                 .filter(n -> n % 2 == 0);

        System.out.println(evenNumbers.count()); // 对数值流进行计数


        // 求毕达哥拉斯三元数 - 一个Stream<T> 其中T为int[]
        // todo this need a more close look.
        System.out.println("========= （0， 100）中的数字所能组成的三元数列表(不重复) 👇 ===========");
        Stream<int[]> pythagoreanTriples =
               IntStream.rangeClosed(1, 100).boxed() // step4 准备a的值 - 手段：数值流的数据范围方法
                       // 考虑到()中的操作会为每个a创建出一个流，所以我们会得到一个元素为流的流【Not wanted】 - 解决手段：使用flatMap() 扁平化得到一个单一的流
                        .flatMap(a -> IntStream.rangeClosed(a, 100) // step3 使用数据范围来提供数字b的值     注：为了避免重复的三元数，这里b的范围是 [a, 100]
                                               .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0) // step1 筛选条件：(a*a + b*b)的平方根没有小数部分
                                               .boxed() // 这里把数值流 转换成为 对象流 - 因为在下面的map()操作中，我们预期得到一个Stream<int[]>【数值流只能得到Stream<int>】
                                               .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})); // step2 创建勾股三元数

        pythagoreanTriples
                .limit(5)
                .forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

    }
   
    public static boolean isPerfectSquare(int n){
        return Math.sqrt(n) % 1 == 0;
    }

}
/*
启示：
    this is a lot.
    但使用数值流能够完成对数字的一些复杂操作；
    1 注意操作后返回的流是什么样子的 - 是否需要使用flatMap()进行扁平化处理；
    2 注意你预期某个方法的返回结果是什么样子的 - 是应该在数值流上调用map() 还是 在对象流上调用map()；
 */