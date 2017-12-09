package com.triona.kontaktliste;

import java.io.IOException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import model.Contact;

@Path("/v1/restapi")
public class RestAPI {

	private static Controller controller;
	
	/*public static void main(String[] args) {
		Contact model = new Contact();
		View view = new View();
			
		controller = new Controller(model, view);
		
		controller.updateView();

	}*/
	/*
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle(){
		System.out.println("It is working");
		return "<p>Java Web Services </p>";
	}*/
	/*
	@POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String hello( 
    		@FormParam("name") String name, 
    		@FormParam("address") String address, 
    		@FormParam("phone") int phone) {
		return 
				"<html>" 
				+"<head>"
				+ "<title>" 
				+ "Hello Jersey" 
				+ "</title>" 
				+ "</head>"
				+ "<body>"
				+ name + "</br>"
				+ address + "</br>"
				+ phone + "</br>"
				+ "</body>" 
				+ "</html> ";
    }	*/
	
	@POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void hello( 
    		@FormParam("name") String name, 
    		@FormParam("address") String address, 
    		@FormParam("phone") String phone) throws IOException {
		
		Controller controller = new Controller(new Contact(), new View());
		controller.addContact(name, address, phone);
		
		controller.updateView();
		
		controller.loadContacts();
    }	
}
