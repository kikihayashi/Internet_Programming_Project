package Model;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Model.Buy_Data;

/*
 * 此Java Class扮演MVC架構中Model的角色, 會讓Controller來呼叫使用
 */
public class ItemCheck {

	
	public String check_items_money(String[] buy_number,String[] item_money){

		   //得到使用者選擇"幾樣"商品 
           int size=java.lang.reflect.Array.getLength(item_money);
           //先令總價格為0
           int total_money=0; 
           
           //利用迴圈算出總金額
           for(int i=1;i<size;i++){
        	   total_money=total_money+(Integer.parseInt(item_money[i]))*(Integer.parseInt(buy_number[i-1]));		
               }         
           //將總金額轉為字串形式
           String message="總計："+Integer.toString(total_money)+"元";
           //回傳總金額
           return message;
	}	        	
	
	
	public boolean check_sell(String item_sell_number,String item_cost){

		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher sell_number_isNum = pattern.matcher(item_sell_number);
		Matcher item_cost_isNum = pattern.matcher(item_cost);
		
		if  (!sell_number_isNum.matches()||!item_cost_isNum.matches()){
		     return false;
		}	
		else{			
		     if  (Integer.parseInt(item_sell_number)<=0||Integer.parseInt(item_cost)<=0){
		    	  return false;
		     }
             else{
        	      return true;
                 }                  
			}
	}	        	
	
	
    public boolean checkout(String[] buy_number_memory){
    	
    	   //得到使用者選擇"幾樣"商品 
           int size=java.lang.reflect.Array.getLength(buy_number_memory);
           //先令總數量為0
           int total_number=0; 
           //利用迴圈算出總購買數量
         
           for(int i=1;i<=size;i++){
        	 
        	   total_number=total_number+(Integer.parseInt(buy_number_memory[i-1]));		
               }
             
			   //如果購買數量為0
               if  (total_number==0){
            	    //回傳false代表使用者沒買任何東西就結帳
            	    return false;   
                    }
               
               else {
            	    //回傳true代表使用者有買東西
            	    return true;          	   
                    }          
		   }
    
    
    public Shop_Data add_items(String item_NO ,String item_name, String item_number,
			                  String item_description, String item_seller, String item_cost,
			                  HashMap<String, Shop_Data> hashMap){


    	Shop_Data shop_data = new Shop_Data();
    	shop_data.setItem_NO(item_NO);
    	shop_data.setItem_name(item_name);
    	shop_data.setItem_number(item_number);
    	shop_data.setItem_description(item_description);
    	shop_data.setItem_seller(item_seller);
    	shop_data.setItem_cost(item_cost);

		hashMap.put(item_NO,shop_data);
		
		//回傳User物件
		return shop_data;
	}
    
    public Buy_Data user_buy(String item_NO ,String item_name, String item_buy_number,
                             String item_cost,String item_buyer,HashMap<String, Buy_Data> hashMap){


           Buy_Data buy_data = new Buy_Data();
           buy_data.setItem_NO(item_NO);
           buy_data.setItem_name(item_name);
           buy_data.setItem_buy_number(item_buy_number);
           buy_data.setItem_cost(item_cost);
           buy_data.setItem_buyer(item_buyer);
           hashMap.put(item_NO,buy_data);

           //回傳User物件
           return buy_data;
}
    
    public  Sell_Data user_sell(String item_name, String item_sell_number,String item_discription,
                                String item_seller,String item_cost,HashMap<String, Sell_Data> hashMap){

            Sell_Data sell_data = new Sell_Data();
            sell_data.setItem_name(item_name);
            sell_data.setItem_sell_number(item_sell_number);
            sell_data.setItem_discription(item_discription);
            sell_data.setItem_seller(item_seller);
            sell_data.setItem_cost(item_cost);

            hashMap.put(item_seller,sell_data);

            //回傳User物件
            return sell_data;
}
    
}
