package com.henry.catalog.chapter04_data_process;

import java.util.List;
import java.util.stream.Collectors;

public class StreamOperations {
    public static void main(String[] args) {
        List<Dish> menu = Dish.menu;

        List<String> names = menu.stream()
                .filter(d -> {
                    System.out.println("filtering " + d.getName());
                    return d.getCalories() > 300;
                })
                .map(d -> {
                    System.out.println("Mapping " + d.getName());
                    return d.getName();
                })
                .limit(3)
                .collect(Collectors.toList());

        System.out.println(names);
    }
}
/*
启示：
    1 由于limit操作的存在，所以stream流并没有完整地遍历所有的菜肴；
    2 虽然filter 与 map是两个独立的操作，但是在执行时 它们被合并到了一起

使用流的三部曲：
1 源：对某个数据源执行一个查询；
2 中间操作链：查询操作组成一条流的流水线；
3 终端操作： 执行流水线 & 生成结果。
    forEach()、count()、collect()
 */
