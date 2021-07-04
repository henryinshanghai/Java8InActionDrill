package com.henry.catalog.chapter02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EverchangingDemand {

    // demand01: filter out the green apple
    public static List<Apple> filterGreenApple(List<Apple> inventory) { // 商品目录
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if ("green".equals(apple.getColor())) {
                result.add(apple);
            }
        }

        return result;
    }

    // demand02: filter out apple with given color
    public static List<Apple> filterAppleByColor(List<Apple> inventory, String color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor().equals(color)) {
                result.add(apple);
            }
        }

        return result;
    }

    // demand03: filter out apples that heavier than given weight
    public static List<Apple> filterAppleByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getWeight() > weight) {
                result.add(apple);
            }
        }

        return result;
    }

    /* demand04: filter out apples that meet composite conditions  👇 */
    // approach_01: 使用一个标识变量 来 表示当前属性会不会被用作筛选条件 - 这是一种糟糕的实现方式
    public static List<Apple> filterApplesApproach01(List<Apple> inventory,
                                           String color,
                                           int weight,
                                           boolean flag) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if ((flag && apple.getColor().equals(color)) ||
                    (!flag && apple.getWeight() > weight)) {
                result.add(apple);
            }
        }

        return result;
    }

    /*
        approach_02:
            使用一个接口类型来表示筛选行为，再使用一组实现类来封装具体的筛选条件；
            然后在filter()方法中使用接口类型作为参数。

        特征：
            1 filterApples()方法的行为 会根据传入的参数而有所不同；
            2 接口实现类中出现了样板/啰嗦代码； - 英文表达中有类似的现象，就是那些构成语法的词
            3 filterApples()方法调用时，不是很容易 - 因为需要new出实现类的对象
     */
    // 这其实是对 “策略模式”的一种应用 - 不同的实现类就是不同的 选择苹果的策略
    class AppleHeavyWeightPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }
    }

    class AppleGreenColorPredicate implements ApplePredicate {
        @Override
        public boolean test(Apple apple) {
            // 应用于单个item的代码
            return "green".equals(apple.getColor());
        }
    }

    // filterApplesApproach02()方法具体的行为 取决于 参数predicate所表示的代码逻辑  aka 方法的行为被参数化了
    public static List<Apple> filterApplesApproach02(List<Apple> inventory,
                                                     ApplePredicate predicate) { // 这里真正传入的实现类对象中，封装了具体的筛选条件
        List<Apple> result = new ArrayList<>();
        // 用于迭代的代码
        for (Apple apple : inventory) {
            if (predicate.test(apple)) { // 如果当前的apple对象能够 通过predicate所表示的筛选条件
                result.add(apple);
            }
        }

        return result;
    }

    // 使用filterApplesApproach02()方法的代码...

    // approach_02的用法优化_01: 不再专门准备很多的实现类，而是 - 使用匿名类，在声明的同时实例化具体类型
    // 注：在GUI应用程序中，会经常用到这种手段。
    // 特征：1 使用匿名类时，虽然省掉了声明的代码，但其实仍旧有很多样板代码； 2 由于没有名字，所以会让人费解
    // 使用优化后的方法的代码  参考：ANCHOR01


    // approach_03的用法扩展：把List中的类型参数 从Apple变得抽象化
    interface Predicate<T> { // 接受类型参数
        // 返回布尔值的方法 - 谓语接口的特征
        boolean test(T t);
    }

    // 在filter方法中使用扩展后的Predicate - 注：泛型方法需要在 返回值类型 之前添加 对类型参数的说明（<T>）
    public static <T> List<T> filterExtend(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();

        for (T e : list) {
            if (predicate.test(e)) {
                result.add(e);
            }
        }

        return result;
    }

    // 扩展后的filter方法的用法 - 参考：ANCHOR02


    public static void main(String[] args) {
        // 准备苹果目录
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        // ANCHOR01 approach_02的用法优化_02: 使用lambda表达式 来 替代匿名类
        // lambda表达式 会丢掉样板代码，只保留关键的信息 - 筛选条件是什么？
        List<Apple> result = filterApplesApproach02(inventory, (Apple apple) -> "red".equals(apple.getColor()));


        // ANCHOR02
        // 准备数字集合
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        // 筛选出其中所有的偶数
        List<Integer> evenNumber = filterExtend(numbers, (Integer i) -> i % 2 == 0);

    }
}
/*
启示：
    1 行为参数化灵活、lambda表达式简洁 - 绝佳的小伙伴；
    2 把行为参数化，能够帮助代码很好地 以开闭原则来应对变化的需求；
    3 行为参数化的做法 类似于 策略模式；

    行为参数化的具体应用： 1 排序； 2 执行代码块； 3 GUI事件处理。
 */