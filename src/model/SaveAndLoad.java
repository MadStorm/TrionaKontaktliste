package model;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class SaveAndLoad {

	private String fileName = "TrionaKontaktliste.txt";
	private FileWriter filewriter;
	private PrintWriter writer;
	private File file;


	public void writeToFile(String name, String address, String phone) {
		try {
			file = new File(fileName);
			file.createNewFile();
			filewriter = new FileWriter(file, true);
			writer = new PrintWriter(filewriter);
			String fileInput = "Test"; 
//				  name + "\n"
//				+ address + "\n"
//				+ phone + "\n";

			writer.println(fileInput);

			writer.close();
			System.out.println("WRITE CONCTACT: \n"+fileInput);
		} catch (Exception e) {
			System.out.println("Saving the team to file failed!");
		}

	}	
}
