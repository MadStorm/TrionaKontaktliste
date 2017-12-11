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

import com.triona.kontaktliste.Controller;

public class Storage {

	private String fileName = "kontakter.txt";
	private FileWriter filewriter;
	private PrintWriter writer;
	private File file;


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
			System.out.println("WRITE: \n"+fileInput);

		} catch (Exception e) {
			System.out.println("Saving the team to file failed!");
		}

	}

	public void readFromFile(Controller controller) throws IOException {
		String input = "";
		String[] formatedData;
		int size = 0;

		File file = new File(fileName);
		try {
			Scanner countSize = new Scanner(file);
			while (countSize.hasNext()) {
				countSize.nextLine();
				size = size + 1;
			}
			System.out.println("PRINT SIZE: "+size);

			formatedData = new String[size];

			Scanner scanner = new Scanner(file);
			for (int i = 0; i < size; i++) {
				input += scanner.nextLine();
			}

			formatedData = input.split(","); 

			List<String> contacts = Arrays.asList(formatedData);
			System.out.println("PRINT CONTACT FORMATED DATA: "+ contacts);
       
			for (int i = 0; i < contacts.size(); i++) {
				if( i%3 == 0){
					System.out.println("READ CONTACT NAME: "+ contacts.get(i));	
					controller.setName(contacts.get(i));
				}
				else if( i%3 == 1){
					System.out.println("READ CONTACT ADDRESS: "+ contacts.get(i));		
					controller.setAddress(contacts.get(i));
				}	
				else if( i%3 == 2){
					System.out.println("READ CONTACT PHONE NUMBER: "+ contacts.get(i));
					controller.setPhone(contacts.get(i));
				}	
			}		        
		} catch (FileNotFoundException e) {
			System.err.format("File not found");
		}
	}
	
	public void deleteStorage(){
		
	}
	
}
