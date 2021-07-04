package com.henry.catalog.chapter03_lambda_expression;

import com.henry.catalog.chapter02.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class HowLambdaWorkWithFunctionalInterface {

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<T>();
        for (T item : list) {
            if (p.test(item)) {
                result.add(item);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(90, "red"),
                new Apple(50, "green"),
                new Apple(100, "green")
        );

        // 传入lambda表达式，定制方法的行为
        /*
        类型检查：
            lambda表达式的类型：根据 使用lambda表达式的上下文 推断出来的。
            step1 上下文所预期的类型 - “目标类型”：
                filter(List<T> list, Predicate<T> p) // 目标类型是 Predicate<T>
            step2 目标类型的抽象方法的函数描述符；
                boolean test(T var1);
            step3 比较 函数描述符 与 lambda表达式的签名是否匹配 - 如果匹配，则：类型检查通过。
                (Apple a) -> a.getWeight() > 60
            结果：签名匹配，类型检查通过。 aka 可以在此处使用这个lambda表达式
         */
        List<Apple> setHeavierThan60 = filter(inventory, (Apple a) -> a.getWeight() > 60);
        printList(setHeavierThan60);

        // lambda表达式参数列表中的参数类型可以省略
        // 原理：Java编译器能够根据上下文知道 预期的目标类型，从而知道预期的lambda表达式应该是什么样子
        // 示例
        // 特征：虽然代码更少了，但是好像可读性也差了点意思
        Comparator<Apple> c1 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
        Comparator<Apple> c2 = (a1, a2) -> a1.getWeight().compareTo(a2.getWeight());
    }

    private static <T> void printList(List<T> list) {
        for (T item : list) {
            System.out.println(item);
        }
    }
}

/*
启示：
    1 只要lambda表达式的签名 能够与 抽象方法的函数描述符匹配，一个lambda表达式其实可以兼容多个函数式接口；
    2 Java7中添加了菱形运算符<> 用于从上下文中推断类型；
    3 特例：如果lambda表达式的主体是一个语句表达式(比如 list.add(s);),那么他就能够和 返回void的函数描述符(比如Consumer)相匹配。
        Consumer<String> b = s -> list.add(s);

===
    4 对于类型推断的编译器特性，需要根据情况选择使用。关键是易读性
    5 可以在lambda表达式中使用实例变量、静态变量与局部变量 - 但是使用局部变量时有些限制（局部变量必须被声明为final的）

 */
