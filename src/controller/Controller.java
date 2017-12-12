package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import model.Contact;
import model.Storage;
import view.View;

/**
 * Kontrolleren inneholder all logikken til hele systemet
 */
public class Controller {

	/**
	 * Her initialiseres alle klassene
	 */
	private Contact model;
	private View view;
	private Storage storage;

	/**
	 * Konstruktør som blir kalt når kontrolleren initialieres
	 * @param model
	 * @param view
	 */
	public Controller(Contact model, View view){
		this.model = model;
		this.view = view;
		this.storage= new Storage(this);
	}

	/**
	 * getName henter navn fra modellen Contact.
	 * @return
	 */
	public String getName() {
		return model.getName();
	}

	/**
	 * setName setter navn i modellen Contact
	 * returnerer en boolean for senere validering av input
	 * @param name
	 * @return
	 */
	public boolean setName(String name) {
		return model.setName(name);
	}

	/**
	 * getAddress henter address fra modellen Contact.
	 * @return
	 */
	public String getAddress() {
		return model.getAddress();
	}

	/**
	 * setAddress setter address i modellen Contact
	 * returnerer en boolean for senere validering av input
	 * @param address
	 * @return
	 */
	public boolean setAddress(String address) {
		return model.setAddress(address);
	}

	/**
	 * getPhone henter phone fra modellen Contact.
	 * @return
	 */
	public String getPhone() {
		return model.getPhone();
	}

	/**
	 * setPhone setter phone i modellen Contact
	 * returnerer en boolean for senere validering av input
	 * @param phone
	 * @return
	 */
	public boolean setPhone(String phone) {
		return model.setPhone(phone);
	}

	/**
	 * Metoden brukes når en kontakt skal legges inn i modellen Contact.
	 * Metoden setter inn navn, addresse og telefon i modellen for så og få en validering tilbake i form av boolean.
	 * If metoden sjekker om all input er godkjent og lagrer da dette i modellen med metoden saveModel().
	 * Til slutt kalles update view hvor resultatet printes i console.
	 * Om valideringen ikke går igjennom skrives det ut en feilmelding i console.
	 * 
	 * @param name
	 * @param address
	 * @param phone
	 * @throws IOException
	 */
	public void addContact(String name, String address, String phone) throws IOException{
		boolean nameValid = setName(name);
		boolean addressValid = setAddress(address);
		boolean phoneValid = setPhone(phone);
		if(nameValid && addressValid && phoneValid){
			saveModel();
			updateView();	
		}
		else{
			System.out.println("ERROR 404: NAME, ADDRESS OR PHONE WERE NOT VALID");
		}
	}

	/**
	 * Metoden laster opp kontakter fra fil 
	 * If metoden sjekker om filen er tom eller ikke
	 * Er filen ikke tom så leses filen og resultatet printes i console
	 * Er filen tom så skrives det en feilmelding
	 * 
	 * @throws IOException
	 */
	public void loadContacts() throws IOException{
		if(!storage.isFileEmpty()){
			storage.readFromFile();
			updateView();
		}
		else{
			System.out.println("FILE IS EMPTY");
		}	
	}
	
	/**
	 * Metoden laster opp kontakter fra fil 
	 * If metoden sjekker om filen er tom eller ikke
	 * Er filen ikke tom så leses filen.
	 * Er filen tom så skrives det en feilmelding
	 * @throws IOException
	 */
	public void readFromFile() throws IOException{
		if(!storage.isFileEmpty()){
			storage.readFromFile();
		}
		else{
			System.out.println("FILE IS EMPTY");
		}

	}

	/**
	 * Metoden sender en kontakt(Navn, addresse og telefon) til klassen Storage for lagring
	 * @param stringArray
	 */
	public void writeToFile(String [] stringArray){
		storage.writeToFileStringArray(stringArray);
	}

	/**
	 * Metoden henter ut kontaktlisten fra fil.
	 * @return List<String>
	 */
	public List<String> getContactList(){
		return storage.getContactList();
	}

	/**
	 * UpdateView oppdaterer kontaktlisten i console
	 * If metoden sjekker først om filen er tom
	 * Om filen ikke er tom sendes en oppdatert kontaktliste og størrelsen på kontaktlisten. 
	 * @throws IOException
	 */
	public void updateView() throws IOException {
		if(!storage.isFileEmpty()){
			readFromFile();
			view.printContactList(storage.getContactList(),storage.getContactListSize());		
		}
	}

	/**
	 * Metoden her sletter alle kontakter filen
	 * @throws FileNotFoundException
	 */
	public void deleteContentInFile() throws FileNotFoundException{
		storage.deleteStorage();
	}

	/**
	 * Metoden henter inn kontaktinformasjonen navn, addresse og telfon og sender det som et Array for lagring til fil.
	 */
	public void saveModel(){
		storage.writeToFileStringArray(new String []{this.getName(),this.getAddress(), this.getPhone()});
	}

	/**
	 * Metoden henter en formatert kontaktliste hvor kontakter kan enkelt hentes ut.
	 * @return
	 * @throws IOException
	 */
	public List<Contact> getContactListFormated() throws IOException{
		return storage.getContactListFormated();
	}

}
