package Model;

import java.io.Serializable;

/**
 * 此 Buy_Data物件為一JavaBean型態的Class, 用來儲存使用者資料.
 */
public class Sell_Data implements Serializable{

	private String item_name;
	private String item_sell_number;
	private String item_discription;
	private String item_seller;
	private String item_cost;
	
	
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getItem_sell_number() {
		return item_sell_number;
	}
	public void setItem_sell_number(String item_sell_number) {
		this.item_sell_number = item_sell_number;
	}
	public String getItem_discription() {
		return item_discription;
	}
	public void setItem_discription(String item_discription) {
		this.item_discription = item_discription;
	}
	public String getItem_seller() {
		return item_seller;
	}
	public void setItem_seller(String item_seller) {
		this.item_seller = item_seller;
	}
	public String getItem_cost() {
		return item_cost;
	}
	public void setItem_cost(String item_cost) {
		this.item_cost = item_cost;
	}

}
