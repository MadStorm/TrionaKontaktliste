package view;

import java.util.List;

public class View {

	public void printContactList(List<String> contactList, int contactListSize){
		System.out.println("========================================");
		System.out.println("NUMBER OF CONTACTS: "+contactListSize);
		System.out.println("CONTACT LIST: ");
		System.out.println("========================================");
		for (int i = 0; i < contactList.size(); i++) {
			if( i%3 == 0){
				System.out.println("NAME: "+ contactList.get(i));	
			}
			else if( i%3 == 1){
				System.out.println("ADDRESS: "+ contactList.get(i));		
			}	
			else if( i%3 == 2){
				System.out.println("PHONE NUMBER: "+ contactList.get(i));
				System.out.println("========================================");
			}	
		}

	}
}
