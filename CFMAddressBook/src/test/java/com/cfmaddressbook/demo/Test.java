package com.cfmaddressbook.demo;

import java.util.ArrayList;
import java.util.List;


import com.cfmaddressbook.demo.entities.AddressBooks;
import com.cfmaddressbook.demo.entities.User;
import com.cfmaddressbook.demo.services.AddressBooksService;
import com.cfmaddressbook.demo.services.UserService;

public class Test {

	public static void main(String[] args) {
		String[] s = {"Arizona", "Chicago", "New Yorl", "Chicago", "Chicago","Texas", "Texas"};
		int maxCount = 0;
		String result = "";
		for(int i =0; i < s.length; i++){
			int count = 0;
			String str = s[i];
			for(int j=0; j<s.length;j++) {
				if(str.equals(s[j])) {
					count++;
				}
			}
			if(count > maxCount) {
				maxCount = count;
				result = str;
			}
		}
		System.out.println(result);
		
	}
}
