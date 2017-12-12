package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import model.Contact;
import model.Storage;
import view.View;

public class Controller {

	private Contact model;
	private View view;
	private Storage storage;

	public Controller(Contact model, View view){
		this.model = model;
		this.view = view;
		this.storage= new Storage(this);
	}

	public String getName() {
		return model.getName();
	}

	public boolean setName(String name) {
		return model.setName(name);
	}

	public String getAddress() {
		return model.getAddress();
	}

	public boolean setAddress(String address) {
		return model.setAddress(address);
	}

	public String getPhone() {
		return model.getPhone();
	}

	public boolean setPhone(String phone) {
		return model.setPhone(phone);
	}

	public void addContact(String name, String address, String phone){
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

	public void loadContacts() throws IOException{
		if(!storage.isFileEmpty()){
			storage.readFromFile();
			updateView();
		}
		else{
			System.out.println("FILE IS EMPTY");
		}	
	}
	
	public void readFromFile() throws IOException{
		if(!storage.isFileEmpty()){
			storage.readFromFile();
		}
		else{
			System.out.println("FILE IS EMPTY");
		}
			
	}
	
	public void writeToFile(String [] stringArray){
		storage.writeToFileStringArray(stringArray);
	}

	public List<String> getContactList(){
		return storage.getContactList();
	}
		
	public void updateView() {
		System.err.println("STORAGE:" + !storage.isFileEmpty());
		if(!storage.isFileEmpty()){
			view.printContactList(storage.getContactList(),storage.getContactListSize());		
		}
	}
	
	public void deleteContentInFile() throws FileNotFoundException{
		storage.deleteStorage();
	}
	
	public void saveModel(){
		storage.writeToFileStringArray(new String []{this.getName(),this.getAddress(), this.getPhone()});
	}
	
	public List<Contact> getContactListFormated() throws IOException{
		return storage.getContactListFormated();
	}
	
}
