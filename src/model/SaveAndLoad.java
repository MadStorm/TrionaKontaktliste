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

public class SaveAndLoad {

	private String fileName = "kontakter.txt";
	private FileWriter filewriter;
	private PrintWriter writer;
	private File file;


	public void writeToFile(String name, String address, String phone) {
		try {
			file = new File(fileName);
			file.createNewFile();
			filewriter = new FileWriter(file, true);
			writer = new PrintWriter(filewriter);
			String fileInput = 
				  name + ","
				+ address + ","
				+ phone + ",";

			writer.println(fileInput);

			writer.close();
			System.out.println("WRITE CONCTACT: \n"+fileInput);
		} catch (Exception e) {
			System.out.println("Saving the team to file failed!");
		}

	}
	
    public void readFromFile(Controller controller) throws IOException {
        String data = " ";
        String[] formatedData;
        int size = 0;

        File file = new File(fileName);
        try {
            Scanner countSize = new Scanner(file);
            while (countSize.hasNext()) {
                countSize.nextLine();
                size = size + 1;
                System.out.println("PRINT SIZE: "+size);
            }

            formatedData = new String[size];
            
            Scanner inputData = new Scanner(file);
            for (int i = 0; i < size; i++) {
               
                data += inputData.nextLine();
                //formatedData[i] = inputData.nextLine();
                formatedData = data.split(",");
            //System.out.println("PRINT DATA: "+formatedData[i]);
            System.out.println("PRINT CONTACT DATA: "+ formatedData);
            }

            List<String> contactData = Arrays.asList(formatedData);
            System.out.println("PRINT CONTACT FORMATED DATA: "+ contactData);
            List<String> contact = new ArrayList<String>();
            
            int count = 0;
            for (int i = 0; i < contactData.size(); i++) {
                if(count < 4){
                    contact.add(contactData.get(i));
                    count++;
                }
                else if(count == 4){
                    System.out.println("PRINT CONTACT NAME: "+ contact.get(1));
                    System.out.println("PRINT CONTACT ADDRESS: "+ contact.get(2));
                    System.out.println("PRINT CONTACT PHONE: "+ contact.get(3));
                    
                    controller.addContact(contact.get(1), contact.get(2), contact.get(3));
                    contact.clear();
                    count = 0;       
                    
                }
            }
            
            System.out.println("CONTACT DATA PRINT: "+contactData);
                
        
        } catch (FileNotFoundException e) {
            System.err.format("File not found");
        }
    }
}
