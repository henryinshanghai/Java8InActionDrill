package com.henry.catalog.chapter03_lambda_expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalInterfaceInJava8 {
    // Ⅰ Predicate
    /*
        函数描述符：boolean test(T var1);
        作用：接收一个T类型的对象，返回一个boolean值。

        应用场景：根据参数的某个性质来返回Yes/No的行为。
        使用示例 👇
     */
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();

        for (T s : list) {
            if (predicate.test(s)) {
                result.add(s);
            }
        }

        return result;
    }

    // Ⅱ Consume
    /*
        函数描述符： void accept(T t);
        作用：接收一个T类型的对象，返回void。

        应用场景：根据输入对象，执行没有返回值的行为(如打印行为)。
        使用示例 👇
     */
    public static <T> void forEach(List<T> list,
                                   Consumer<T> c) {
        for (T item : list) {
            c.accept(item); // 消费item，但不返回任何东西
        }
    }


    // Ⅲ Function
    /*
        函数描述符： R apply(T t);
        作用：接收一个T类型的对象，返回一个R类型的对象。

        应用场景：根据输入对象，映射到输出对象的行为。
        使用示例 👇
     */
    public static <T, R> List<R> map(List<T> list,
                                     Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(f.apply(t)); // 像是函数的转换/映射操作
        }

        return result;
    }

    public static void main(String[] args) {
        // Predicate
        System.out.println("************** Predicate *************");
        // 编写一个lambda表达式，并绑定到 Predicate类型的变量上
        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();

        // 把 lambda表达式所封装的行为 参数化传递到方法中
        List<String> strings = Arrays.asList("henry", "jane", "quinta", "", "", "jack");
        List<String> result = filter(strings, nonEmptyStringPredicate);

        printList(result);

        // Consumer
        System.out.println("************** Consumer *************");
        forEach(Arrays.asList(1, 2, 3, 4, 5), (Integer i) -> System.out.println(i));

        // Function
        System.out.println("************** Function *************");
        List<Integer> lengthOfStr = map(Arrays.asList("Java", "8", "In", "Action"), (String s) -> s.length());
        printList(lengthOfStr);
    }

    // 声明类型参数列表时，需要添加<>
    private static <T> void printList(List<T> result) {
        for (T item : result) {
            System.out.println(item);
        }
    }

}
/*
Java8中提供的函数式接口：
1 Predicate - 布尔判断
2 Consumer - 没有返回值的操作
3 Function - 有返回值的操作

另，为了避免自动装箱/拆箱带来的成本。Java8 提供了像是IntPredicate这样的接口。
<原始数据类型>+<通用的函数式接口名称>
 */
