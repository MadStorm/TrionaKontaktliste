package com.triona.kontaktliste;

import java.io.IOException;

import model.Contact;
import model.Storage;

public class Controller {

	private Contact model;
	private View view;
	private Storage saveAndLoad;
	
	public Controller(Contact model, View view){
		this.model = model;
		this.view = view;
		saveAndLoad = new Storage();
	}
	
	public String getName() {
		return model.getName();
	}

	public void setName(String name) {
		model.setName(name);
	}

	public String getAddress() {
		return model.getAddress();
	}

	public void setAddress(String address) {
		model.setAddress(address);
	}

	public String getPhone() {
		return model.getPhone();
	}

	public void setPhone(String phone) {
		model.setPhone(phone);
	}
	
	public void updateView(){
		view.printContactDetails(model.getName(), model.getAddress(), model.getPhone());
	}
	
	public void addContact(String name, String address, String phone){
		setName(name);
		setAddress(address);
		setPhone(phone);
	}
	
	public void loadContacts() throws IOException{
		saveAndLoad.readFromFile(this);
	}
	
}
