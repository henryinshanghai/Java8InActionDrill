package com.henry.catalog.chapter03_lambda_expression;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteAround {

    // 版本1 打印指定文件的第一行文本
    public static String processFile() throws IOException {
        // 这里编译器使用Java5编译时，无法识别出Java7的语法
        // 解决手段：在pom.xml文件中使用 plugin标签 来指定项目编译时使用的Java版本
        // issue: resources\chapter03\data.txt (系统找不到指定的路径。)
        try (BufferedReader br =
                     new BufferedReader(new FileReader("E:/develop/JavaInAction_Drill/src/main/resources/chapter03/data.txt"))) { //
            return br.readLine();
        }
    }

    // 版本2 把预期执行的行为 参数化传递给processFile()方法
    // tips：先写lambda表达式，再写函数式接口
    /*
        step1 写出预期的lambda表达式的签名
        作用：打印两行文本的lambda表达式
        processFile((BufferedReader br -> br.readLine() + br.readLine()))

        根据lambda表达式来创建函数式接口
        interface BufferedReaderProcessor{}
     */
    // step2 为预期的lambda表达式 编写 函数式接口
    interface BufferedReaderProcessor {
        // 函数描述符    方法名可以随意设定
        String process(BufferedReader b) throws IOException;
    }

    // step3 重写 processFile 来 通过参数接收行为
    public static String processFile(BufferedReaderProcessor processor) throws IOException {
        // 接收到传入的行为参数后，具体执行此行为
        // 手段：调用抽象方法即可
        try (BufferedReader br = new BufferedReader(new FileReader("E:/develop/JavaInAction_Drill/src/main/resources/chapter03/data.txt"))) {
            return processor.process(br);
        }
    }

    public static void main(String[] args) throws IOException {
//        String s = processFile();
//        System.out.println(s);

        // step4 使用lambda表达式 来 向方法中传入行为
        // 手段：1 以内联的方式 为抽象方法提供具体实现；2 把lambda表达式 作为函数式接口的一个实例
        // 处理一行
        String s1 = processFile((BufferedReader br) -> br.readLine());
        System.out.println("s1: " + s1);

        // 处理两行
        String s2 = processFile((BufferedReader br) -> br.readLine() + br.readLine());
        System.out.println("s2: " + s2);

    }
}

/*
启示：
    应用环绕执行模式所使用的四个步骤：step1 + step2 + step3 + step4；

    注：这里定义自己的接口不太明了，因为需要先知道自己要使用的lambda表达式的签名。
 */
