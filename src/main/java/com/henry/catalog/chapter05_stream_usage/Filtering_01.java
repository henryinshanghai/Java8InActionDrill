package com.henry.catalog.chapter05_stream_usage;



import com.henry.catalog.chapter04_data_process.Dish;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
// 引入其他类中的静态字段
import static com.henry.catalog.chapter04_data_process.Dish.menu;

public class Filtering_01 {

    public static void main(String...args){

        // 用法1：Filtering_01 with predicate 使用谓语来筛选
        List<Dish> vegetarianMenu =
            menu.stream()
                .filter(Dish::isVegetarian)
                .collect(toList());

        vegetarianMenu.forEach(System.out::println);

        // 用法2：Filtering_01 unique elements 筛选出独一无二的元素 aka 去重
        System.out.println("========== Filtering_01 unique elements👇 ===========");
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
               .filter(i -> i % 2 == 0)
               .distinct()
               .forEach(System.out::println);

        // 用法3：Truncating a stream 截断一个流
        System.out.println("========== Truncating a stream 👇 ===========");
        List<Dish> dishesLimit3 =
            menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(toList());

        dishesLimit3.forEach(System.out::println);

        // 用法4：Skipping elements 跳过一些个元素
        System.out.println("========== Skipping elements 👇 ===========");
        List<Dish> dishesSkip2 =
            menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2) // 跳过流中的前两个元素
                .collect(toList());

        dishesSkip2.forEach(System.out::println);
    }
}
