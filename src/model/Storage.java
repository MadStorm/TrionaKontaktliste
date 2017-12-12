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

/**
 * Denne klassen tar seg av alt logikk rundt lesing og skriving av kontakter til og fra fil
 *
 * @author Luka
 *
 */

public class Storage {

	/**
	 * Kontaktlisten blir lagret i "kontakter.txt"
	 */
	private String fileName = "kontakter.txt";
	private FileWriter filewriter;
	private PrintWriter writer;
	private File file;
	private List<String> contactList = new ArrayList<String>();
	private Controller controller;

	/**
	 * Konstruktøren tar inn kontrolleren når den initialiseres.
	 * @param controller
	 */
	public Storage(Controller controller){
		this.controller = controller;
	}

	/**
	 * Metoden mottar en String Array med all kontaktinformasjon: Navn, Adresse og Telefon. 
	 * Array-et itereres og sender navn, addresse og telefon separat til selve metodden "writeToFile" som er den endelige metoden som skriver til filen.
	 * @param stringArray
	 */
	public void writeToFileStringArray(String [] stringArray){
		for(int i = 0; i<stringArray.length; i++){
			writeToFile(stringArray[i]);
		}
	}
	
	/**
	 * Metoden er den som skriver input til selve filen.
	 * Finnes ikke filen så får man en feilmelding i console
	 * @param string
	 */
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
	
	/**
	 * Metoden leser fra fil og henter kontakter og oppdater kontaktlisten i den globale variablen contactList
	 * @return
	 * @throws IOException
	 */
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

	/**
	 * Metoden sletter all innhold i filen
	 * @throws FileNotFoundException
	 */
	public void deleteStorage() throws FileNotFoundException{
		PrintWriter writer = new PrintWriter(fileName);
		writer.print("");
		writer.close();
	}

	/**
	 * Metoden initialiserer den gloabale variablen contactList
	 * @param contactList
	 */
	public void setContactList(List<String> contactList){
		this.contactList = contactList;
	}

	/**
	 * Metoden returnerer kontaktlisten
	 * @return
	 */
	public List<String> getContactList(){
		return contactList;
	}

	/**
	 * metoden returnerer kontaktlistens størrelse
	 * If metoden sjekker om kontaktlisten er null eller tom før kontaktlistens størrelse blir hentet.
	 * @return
	 */
	public int getContactListSize(){
		if( contactList != null && contactList.size() != 0)
			return contactList.size();
		else
			return 0;
	}

	/**
	 * Metoden sjekker om filen er tom. 
	 * boolean fileEmpty er true ved start og returnerer false om filen inneholder noe.
	 * @return
	 */
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
	/**
	 * Metoden henter ut kontaktene fra den ikke formaterte String kontaktlisten 
	 * og oppretter dem og så lagres de i en formatert ArrayList kontaktliste som blir returnert
	 * @return
	 * @throws IOException
	 */
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
