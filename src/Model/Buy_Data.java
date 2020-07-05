package Model;

import java.io.Serializable;

/**
 * 此 Buy_Data物件為一JavaBean型態的Class, 用來儲存使用者資料.
 */
public class Buy_Data implements Serializable{

	private String item_NO;
	private String item_name;
	private String item_buy_number;
	private String item_cost;
	private String item_buyer;
	
	public String getItem_NO() {
		return item_NO;
	}
	public void setItem_NO(String item_NO) {
		this.item_NO = item_NO;
	}	
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getItem_buy_number() {
		return item_buy_number;
	}
	public void setItem_buy_number(String item_number) {
		this.item_buy_number = item_number;
	}	
	public String getItem_cost() {
		return item_cost;
	}
	public void setItem_cost(String item_cost) {
		this.item_cost = item_cost;
	}
	public String getItem_buyer() {
		return item_buyer;
	}
	public void setItem_buyer(String item_buyer) {
		this.item_buyer = item_buyer;
	}

}
