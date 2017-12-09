package com.youtube.rest.status;

import model.Contact;

public class Controller {

	private Contact model;
	private View view;
	
	public Controller(Contact model, View view){
		this.model = model;
		this.view = view;
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
	
}
