package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import controller.Controller;


public class Storage {

	private String fileName = "kontakter.txt";
	private FileWriter filewriter;
	private PrintWriter writer;
	private File file;
	private List<String> contactListSize = new ArrayList<String>();
	private Controller controller;
	
	public Storage(Controller controller){
		this.controller = controller;
	}

	public void writeToFileStringArray(String [] stringArray){
		for(int i = 0; i<stringArray.length; i++){
			writeToFile(stringArray[i]);
		}
	}
	public void writeToFile(String string) {
		try {
			file = new File(fileName);
			file.createNewFile();

			filewriter = new FileWriter(file, true);
			writer = new PrintWriter(filewriter);
			String fileInput = 
					string + ",";

			writer.println(fileInput);

			writer.close();
			System.out.println("WRITE TO FILE: \n"+fileInput);

		} catch (Exception e) {
			System.out.println("Saving contacts to file failed!");
		}

	}

	public boolean readFromFile() throws IOException {
		String input = "";
		String[] inputArray;
		int size = 0;

		File file = new File(fileName);
		try {
			Scanner countSize = new Scanner(file);
			while (countSize.hasNext()) {
				countSize.nextLine();
				size++;
			}

			inputArray = new String[size];

			Scanner scanner = new Scanner(file);
			for (int i = 0; i < size; i++) {
				input += scanner.nextLine();
			}

			inputArray = input.split(","); 

			List<String> contactList = Arrays.asList(inputArray);
			setContactList(contactList);
			
			for (int i = 0; i < contactList.size(); i++) {

				
				if( i%3 == 0){
					if(controller.setName(contactList.get(i))){
						controller.setName(contactList.get(i));
					}
				}
				else if( i%3 == 1){
					if(controller.setAddress(contactList.get(i))){
						controller.setAddress(contactList.get(i));
					}
				}	
				else if( i%3 == 2){
					if(controller.setPhone(contactList.get(i))){
						controller.setPhone(contactList.get(i));					
					}
				}	
			}
			return true;
		} catch (FileNotFoundException e) {
			System.err.format("File not found");
		}
		return false;
	}

	public void deleteStorage() throws FileNotFoundException{
		PrintWriter writer = new PrintWriter(fileName);
		writer.print("");
		writer.close();
	}

	public void setContactList(List<String> contactListTest){
		this.contactListSize = contactListTest;
	}

	public List<String> getContactList(){
		return contactListSize;
	}

	public int getContactListSize(){
		if( contactListSize != null && contactListSize.size() != 0)
			return contactListSize.size();
		else
			return 0;
	}

	public boolean isFileEmpty(){
		int size = 0;
		boolean fileEmpty = true;
		
		File file = new File(fileName);
		try {
			Scanner countSize = new Scanner(file);
			while (countSize.hasNext()) {
				countSize.nextLine();
				size++;
			}
			if(size != 0){
				fileEmpty = false;
			}
			countSize.close();
		} catch (FileNotFoundException e) {
			System.err.format("File not found");
		}
		return fileEmpty;
	}

	public List<Contact> getContactListFormated() throws IOException{
	
		List<Contact> contacts = new ArrayList<>();
		Contact c;
		
        for(int i = 0; i< getContactListSize(); i+=3){
        	c = new Contact();
        	c.setName(getContactList().get(i));
        	c.setAddress(getContactList().get(i+1));
        	c.setPhone(getContactList().get(i+2));
        	contacts.add(c);
        }
	
		return contacts;
	}
}
