package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {

	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String ACCOUNTS_PAGE_TITLE = "My Account";
	public static final String ACCOUNTS_HEADER_VALUE = "Your Store";
	public static final int ACCOUNT_SECTIONS = 4;
	public static final List<String> getAccountSectionList(){
		List<String> accountList = new ArrayList<String>();
		accountList.add("My Account");
		accountList.add("My Orders");
		accountList.add("My Affiliate Account");
		accountList.add("Newsletter");
		
		return accountList;
	}
	public static final List<String> subLinksMyAccount(){
		List<String> subLinksInMyAccount = new ArrayList<String>();
		subLinksInMyAccount.add("Edit your account information");
		subLinksInMyAccount.add("Change your password");
		subLinksInMyAccount.add("Modify your address book entries");
		subLinksInMyAccount.add("Modify your wish list");
		
		return subLinksInMyAccount;
	}
	
	public static final String REGISTER_SHEET_NAME = "Registration";
	
	public static final String SUCCESS_MESSAGE = "Your Account Has Been Created!";
	
	
}
