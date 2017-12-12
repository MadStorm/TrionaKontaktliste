package view;

import java.util.List;

/**
 * Klassen her tar seg av printing av kontaktlisten
 * @author Luka
 *
 */
public class View {

	public void printContactList(List<String> contactList, int contactListSize){
		System.out.println("========================================");
		System.out.println("NUMBER OF CONTACTS: "+contactListSize/3);
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
