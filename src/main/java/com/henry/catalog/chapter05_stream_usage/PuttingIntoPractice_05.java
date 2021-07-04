package com.henry.catalog.chapter05_stream_usage;

import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class PuttingIntoPractice_05 {
    public static void main(String...args){
        // 交易员列表
        Trader_05 raoul = new Trader_05("Raoul", "Cambridge");
        Trader_05 mario = new Trader_05("Mario","Milan");
        Trader_05 alan = new Trader_05("Alan","Cambridge");
        Trader_05 brian = new Trader_05("Brian","Cambridge");

        // 交易记录
		List<Transaction_05> transactions = Arrays.asList(
            new Transaction_05(brian, 2011, 300), 
            new Transaction_05(raoul, 2012, 1000),
            new Transaction_05(raoul, 2011, 400),
            new Transaction_05(mario, 2012, 710),	
            new Transaction_05(mario, 2012, 700),
            new Transaction_05(alan, 2012, 950)
        );	
        
        
        // Query 1: Find all transactions from year 2011 and sort them by value (small to high).
        // 找到所有在2011年发生的所有交易 并且按照交易额从小到大排序
        List<Transaction_05> tr2011 = transactions.stream()
                                               .filter(transaction -> transaction.getYear() == 2011)
                                               .sorted(comparing(Transaction_05::getValue)) // 传入排序使用的函数式接口
                                               .collect(toList());
        System.out.println(tr2011);
        
        // Query 2: What are all the unique cities where the traders work?
        // 交易员所在的城市(不重复)
        List<String> cities = 
            transactions.stream()
                        .map(transaction -> transaction.getTrader().getCity())
                        .distinct()
                        .collect(toList());
        System.out.println(cities);

        // Query 3: Find all traders from Cambridge and sort them by name.
        // 找到所有来自于剑桥的交易员 并 按照名字排序
        List<Trader_05> traders = 
            transactions.stream()
                        .map(Transaction_05::getTrader)
                        .filter(trader -> trader.getCity().equals("Cambridge"))
                        .distinct()
                        .sorted(comparing(Trader_05::getName))
                        .collect(toList());
        System.out.println(traders);
        
        
        // Query 4: Return a string of all traders’ names sorted alphabetically.
        // 返回所有交易员的姓名所组成的单一字符串(字母表排序)
        System.out.println("---------- 返回所有交易员的姓名所组成的单一字符串(字母表排序)👇 ----------");
        String traderStr = 
            transactions.stream()
                        .map(transaction -> transaction.getTrader().getName())
                        .distinct()
                        .sorted()
                        .reduce("", (n1, n2) -> n1 + n2);
        System.out.println(traderStr);
        
        // Query 5: Are there any trader based in Milan?
        // 是否有来自于米兰的交易员
        boolean milanBased =
            transactions.stream()
                        .anyMatch(transaction -> transaction.getTrader()
                                                            .getCity()
                                                            .equals("Milan")
                                 );
        System.out.println(milanBased);
        
        
        // Query 6: Update all transactions so that the traders from Milan are set to Cambridge.
        // 把所有交易记录中 Milan的交易员 更新成为 Cambridge
        transactions.stream()
                    .map(Transaction_05::getTrader)
                    .filter(trader -> trader.getCity().equals("Milan"))
                    .forEach(trader -> trader.setCity("Cambridge"));
        System.out.println(transactions);
        
        
        // Query 7: What's the highest value in all the transactions?
        // 查询所有交易中 交易额最大的交易
        int highestValue = 
            transactions.stream()
                        .map(Transaction_05::getValue)
                        .reduce(0, Integer::max);
        System.out.println(highestValue);      
    }
}