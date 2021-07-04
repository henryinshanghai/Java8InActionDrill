package com.henry.catalog.chapter05_stream_usage;

import com.henry.catalog.chapter04_data_process.Dish;
import static com.henry.catalog.chapter04_data_process.Dish.menu;

import java.util.Optional;

public class Finding_03 {

    public static void main(String...args){
        if(isVegetarianFriendlyMenu()){
            System.out.println("Vegetarian friendly");
        }

        System.out.println(isHealthyMenu());
        System.out.println(isHealthyMenu2());
        
        Optional<Dish> dish = findVegetarianDish();
        dish.ifPresent(d -> System.out.println(d.getName()));
    }

    // 用法1： 检查列表中的元素 是否至少有一个 匹配给定的条件
    /*
        是否至少有一个 - anyMatch()
        给定的条件 - (Dish::isVegetarian)
     */
    private static boolean isVegetarianFriendlyMenu(){
        return menu.stream().anyMatch(Dish::isVegetarian);
    }

    // 用法2：检查 谓语 是否匹配 列表所有的元素；
    private static boolean isHealthyMenu(){
        return menu.stream().allMatch(d -> d.getCalories() < 1000);
    }
    
    private static boolean isHealthyMenu2(){
        // 用法3：检查 谓语 是否完全不匹配 列表任何的元素；
        return menu.stream().noneMatch(d -> d.getCalories() >= 1000);
    }

    // Optional类型 - 一个容器类； 作用：表示某个值 或者存在、或者不存在
    private static Optional<Dish> findVegetarianDish(){
        // 用法4： 返回当前流中的任一个元素    为什么一直都是 法式薯条？
        return menu.stream().filter(Dish::isVegetarian).findAny();
    }
    
}
