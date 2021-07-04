package com.henry.catalog.chapter02;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ParamAction_ComparatorDemo {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(90, "red"),
                new Apple(80, "green"),
                new Apple(100, "green")
        );

        // 对列表中的元素进行排序 - 手段：列表对象的sort()方法
        // 用法：需要传入一个比较器匿名对象
        // 手段1：匿名类
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        // 手段2：lambda表达式
//        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

        // 手段3：lambda表达式的优化（IDEA只能提示） - 工具类 + 方法引用
        inventory.sort(java.util.Comparator.comparing(Apple::getWeight));

        printItem(inventory);
    }

    private static void printItem(List<Apple> inventory) {
        for (Apple apple : inventory) {
            System.out.println(apple.toString());
        }
    }

}

// 可以在一个类中定义一个非public的接口 - 这个接口不需要自己定义。java.util中就有
//interface Comparator<T> {
//    public int compare(T o1, T o2);
//}
