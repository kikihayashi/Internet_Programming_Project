package Model;

import java.io.Serializable;

/**
 * 此 Buy_Data物件為一JavaBean型態的Class, 用來儲存使用者資料.
 */
public class Shop_Data implements Serializable{

	private String item_NO;
	private String item_name;
	private String item_number;
	private String item_description;
	private String item_seller;
	private String item_cost;
	
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
	public String getItem_number() {
		return item_number;
	}
	public void setItem_number(String item_number) {
		this.item_number = item_number;
	}
	public String getItem_description() {
		return item_description;
	}
	public void setItem_description(String item_description) {
		this.item_description = item_description;
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
