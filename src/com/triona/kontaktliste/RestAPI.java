package com.triona.kontaktliste;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import controller.Controller;
import model.Contact;
import model.DatabaseAzure;
import model.Storage;
import view.View;




@Path("/v1/user")
public class RestAPI {
	
	private static Controller controller;
	
	
	public RestAPI() throws IOException {
		controller = new Controller(new Contact(), new View());
		//loadContacts();
		//new DatabaseAzure();
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void addContact( 
			@FormParam("name") String name, 
			@FormParam("address") String address, 
			@FormParam("phone") String phone) throws IOException {

		controller.addContact(name, address, phone);	
	}	
	
	@GET
    @Produces({MediaType.APPLICATION_JSON})  
    public List<Contact> getAllMessages() throws IOException {
        loadContacts();
		
//        List<Contact> contacts = new ArrayList<>();
//        Contact c;
//        
//        List<String> file = controller.getContactList();
//        for(int i = 0; i<file.size(); i+=3){
//        	c = new Contact();
//        	c.setName(file.get(i));
//        	c.setAddress(file.get(i+1));
//        	c.setPhone(file.get(i+2));
//        	contacts.add(c);
//        }

        return controller.getContactListFormated();
    }
	
	public void loadContacts() throws IOException{
		controller.readFromFile();
	}
}
