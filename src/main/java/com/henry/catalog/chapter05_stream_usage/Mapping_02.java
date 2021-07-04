package com.henry.catalog.chapter05_stream_usage;


import com.henry.catalog.chapter04_data_process.Dish;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static com.henry.catalog.chapter04_data_process.Dish.menu;

public class Mapping_02 {

    public static void main(String...args){

        // 用法1： map     把方法 应用到 流中的每一个元素上：
        // 获取每一道菜的名字
        System.out.println("======== 获取每一道菜的名字 👇=========");
        List<String> dishNames = menu.stream()
                                     .map(Dish::getName)
                                     .collect(toList());
        System.out.println(dishNames);

        // map
        // 获取到每一个单词的长度
        System.out.println("======== 获取到每一个单词的长度 👇=========");
        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths = words.stream()
                                         .map(String::length)
                                         .collect(toList());
        System.out.println(wordLengths);

        // 用法2： flatMap     把 每个元素得到的流 扁平化为一个单一的流；
        // 接收一个数组，并产生一个新的流； - 手段：Arrays.stream(<array>)
        // 作用：得到字符串列表中所有的单个字符(不重复)
        // flatMap
        System.out.println("======== 得到字符串列表中所有的单个字符(不重复)👇 ========");
        words.stream()
                 .flatMap((String line) -> Arrays.stream(line.split("")))
                 .distinct()
                 .forEach(System.out::println);

        // flatMap
        // 使生成的流扁平化 aka 把 由流元素组成的流 转化成为 单一的流，且item的类型不变
        List<Integer> numbers1 = Arrays.asList(1,2,3,4,5);
        List<Integer> numbers2 = Arrays.asList(6,7,8);
        System.out.println("======== 返回两个数字列表可能组成的所有 能够被3整除的双数数字对 👇 ========");
        List<int[]> pairs =
                        numbers1.stream()
                                .flatMap((Integer i) -> numbers2.stream()
                                                       .map((Integer j) -> new int[]{i, j})
                                 )// 此处会得到 由整数数组构成的流
                                .filter(pair -> (pair[0] + pair[1]) % 3 == 0) // 筛选条件：数对的和能够被3整除
                                .collect(toList());
        pairs.forEach(pair -> System.out.println("(" + pair[0] + ", " + pair[1] + ")"));
    }
}
