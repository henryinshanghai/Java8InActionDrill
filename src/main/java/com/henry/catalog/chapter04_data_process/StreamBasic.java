package com.henry.catalog.chapter04_data_process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class StreamBasic {

    public static void main(String...args){
        // Java 7
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

        System.out.println("-------- java8 👇 ----------");

        // Java 8
        getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);

    }

    /* 从dish集合中筛选出 低热量的菜肴名称 */
    // approach01: 手动实现迭代 与 筛选
    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes){
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for(Dish d: dishes){ // 遍历
            if(d.getCalories() < 400){ // 筛选
                lowCaloricDishes.add(d); // 累加
            }
        }

        List<String> lowCaloricDishesName = new ArrayList<>();

        // 按照热量对菜肴进行排序 - 手段：匿名类
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            public int compare(Dish d1, Dish d2){
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });

        // 处理排序后的低热量菜肴列表
        for(Dish d: lowCaloricDishes){
            lowCaloricDishesName.add(d.getName());
        }
        return lowCaloricDishesName;
    }

    // approach02: Java8所提供的Stream API
    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes){
        return dishes.stream() // 获取到Stream对象
                .filter(d -> d.getCalories() < 400) // 筛选
                .sorted(comparing(Dish::getCalories)) // 排序
                .map(Dish::getName) // 提取名称
                .collect(toList()); // 保存结果
    }
}
/*
启示：
使用Stream API时几个显而易见的好处：
    1 以声明式的方式编写代码 - 不需要提供具体实现；
    2 能够把几个基础操作串联起来，从而清晰地表现出处理逻辑。
    ---
    3 开发者不需要关系filter()、sorted()等方法的实现 - 它们可能会并发执行或者别的，但你不需要担心线程和锁的问题了

 */
