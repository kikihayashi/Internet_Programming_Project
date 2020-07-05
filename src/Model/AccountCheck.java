package Model;

import java.util.HashMap;

import Model.User_Data;


/*
 * 此Java Class扮演MVC架構中Model的角色, 會讓Controller來呼叫使用
 */
public class AccountCheck {

	/**
	 * 檢查現有使用者資料中, 此使用者名稱是否已經被註冊使用
	 * 
	 * @param accountName
	 *            使用者所輸入要新增帳號的名稱
	 * @param hashMap
	 *            此Web應用程式用來存放使用者物件的地方, 因為這個作業沒有使用檔案或資料庫來存放資料.
	 *            所以用HashMap資料結構來暫存. 但一旦Web Application重新啟動或關閉, 使用者物件都會遺失.
	 * @return true(代表使用者名稱已經存在)或false(代表此使用者名稱沒有被使用)
	 */
	
	public boolean checkAccountNameExistence(String inputAccountName,
			HashMap<String, User_Data> hashMap) {
	
		// 此hashMap物件為空值
		if  (hashMap == null)
			return false;
	
	    // 如果系統內, 已經有此使用者的帳號名稱
	    if  (hashMap.containsKey(inputAccountName))
	 		return true;
	 		
	 	else
	 		return false;
			}	        	

	/**
	 * 作檢查的功能. 檢查使用者輸入的帳號與密碼是否正確
	 * 
	 * @param accountName
	 *            使用者登錄時所輸入的帳號名稱
	 * @param passwd
	 *            使用者登錄時所輸入的密碼字串
	 * @param hashMap
	 *            此Web應用程式用來存放使用者物件的地方, 因為這個作業沒有使用檔案或資料庫來存放資料.
	 *            所以用HashMap資料結構來暫存. 但一旦Web Application重新啟動或關閉, 使用者資料都會遺失.
	 * @return true(代表密碼正確)或false(代表密碼不正確)
	 */
	public String checkPassword(String inputAccountName, String inputPassword,
			HashMap<String, User_Data> hashMap) {
		
		// hashMap物件為空值，此情況發生在沒有人註冊過，且沒填帳號或密碼，按送出時發生
		if (hashMap == null)
			return "Nothing";
				
		// 此情況發生在沒填帳號，或沒填密碼，按送出時發生
	    if(inputAccountName.equals("") || inputPassword.equals(""))	
			return "Nothing";			
									
		// 取得此使用者名稱所對應的使用者物件
		User_Data user = hashMap.get(inputAccountName);
		
		// 如果系統內,已經有此使用者的帳號名稱
		if (hashMap.containsKey(inputAccountName)){
			
			// 取得使用者帳號所對應的密碼
			String passwordOnRecord = user.getPassword();
			
			// 如果密碼正確的話, 傳回Yes_PWD
			if (inputPassword.equals(passwordOnRecord))
				return "Yes_PWD";
			
			// 如果密碼不正確的話, 傳回No_PWD
			else
				return "No_PWD";
		}
						
		// 如果系統內,尚未有此使用者的帳號名稱
		else{	
			 return "No_ID";
			}
		
	}

	/**
	 * 此方法用來新增一位使用者
	 * 
	 * @param name
	 *            使用者所輸入的名字
	 * @param address
	 *            使用者所輸入的地址
	 * @param phoneNumber
	 *            使用者所輸入的電話號碼
	 * @param education
	 *            使用者所輸入的教育程度
	 * @param accountName
	 *            使用者所輸入的帳戶名稱
	 * @param password
	 *            使用者所輸入的帳戶密碼
	 * @param hashMap
	 *            此Web應用程式用來存放使用者物件的地方, 因為這個作業沒有使用檔案或資料庫來存放資料.
	 *            所以用HashMap資料結構來暫存. 但一旦Web Application重新啟動或關閉, 使用者物件都會遺失.
	 * @return 傳回一個User物件
	 */
	public User_Data addNewUser(String name,String sid,String phone,String mail,String edu,String grade,
			               String accountName,String password, HashMap<String, User_Data> hashMap) {

		User_Data user_data = new User_Data();
		user_data.setName(name);
		user_data.setSid(sid);
		user_data.setPhone(phone);
		user_data.setMail(mail);
		user_data.setEdu(edu);
		user_data.setGrade(grade);
		user_data.setAccountName(accountName);
		user_data.setPassword(password);

		hashMap.put(accountName, user_data);
		
		//回傳User物件
		return user_data;
	}
	
}
