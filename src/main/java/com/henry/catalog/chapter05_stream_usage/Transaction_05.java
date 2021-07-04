package com.henry.catalog.chapter05_stream_usage;

public class Transaction_05 {

	private Trader_05 trader;
	private int year;
	private int value;

	public Transaction_05(Trader_05 trader, int year, int value)
	{
		this.trader = trader;
		this.year = year;
		this.value = value;
	}

	public Trader_05 getTrader(){
		return this.trader;
	}

	public int getYear(){
		return this.year;
	}

	public int getValue(){
		return this.value;
	}
	
	public String toString(){
	    return "{" + this.trader + ", " +
	           "year: "+this.year+", " +
	           "value:" + this.value +"}";
	}
}