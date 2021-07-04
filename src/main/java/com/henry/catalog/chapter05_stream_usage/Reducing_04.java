package com.henry.catalog.chapter05_stream_usage;


import com.henry.catalog.chapter04_data_process.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.henry.catalog.chapter04_data_process.Dish.menu;

public class Reducing_04 {

    public static void main(String...args){

        // 用法1：求流中所有元素的和
        /*
            语法：
                <流对象>.reduce(<initial_value>, <lambda_to_express_operation>)
                reduce(0, (a, b) -> a + b)
         */
        List<Integer> numbers = Arrays.asList(3,4,5,1,2);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        // 优化：使用方法引用 代替 lambda表达式
        int sum2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum2);

        // 用法2：求最值
        /*
            语法：
                <流对象>.reduce(<initial_value>, <lambda_to_express_operation>)
            归约的特征：
                会拿着当前操作的结果 与 序列中的下一个元素 继续执行操作
         */
        int max = numbers.stream().reduce(0, (a, b) -> Integer.max(a, b));
        System.out.println(max);


        // 最小值
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(System.out::println);

        // 计算菜单中所有菜肴的总卡路里
        int calories = menu.stream()
                           .map(Dish::getCalories)
                           .reduce(0, Integer::sum);
        System.out.println("Number of calories:" + calories);
    }
}
