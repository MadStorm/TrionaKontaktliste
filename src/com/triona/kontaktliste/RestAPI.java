package com.triona.kontaktliste;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import controller.Controller;
import model.Contact;
import view.View;

/**
 * RestAPI klasse
 */

@Path("/v1/user")
public class RestAPI {
	/**
	 * RestAPI snakker bare gjennom kontroller
	 */
	private static Controller controller;

	/**
	 * Main metoden n�r RestAPI klassen startes
	 * Den initialiserer kontrolleren og alle andre instansene
	 */
	public RestAPI() throws IOException {
		controller = new Controller(new Contact(), new View());
	}

	/**
	 * POST metoden som kalles p� n�r man trykker p� "Lagre kontakt" knappen fra front end
	 * 
	 * POST metoden mottar variablene name, address og phone fra front end
	 * Deretter sendes de til controlleren.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void addContact( 
			@FormParam("name") String name, 
			@FormParam("address") String address, 
			@FormParam("phone") String phone) throws IOException {

		controller.addContact(name, address, phone);	
	}	
	
	/**
	 * GET metoden som blir kalt p� n�r man trykke p� "Oppdater Kontaktliste" fra front end
	 * Metoden henter da kontaktlisten fra back end i et List<Contact> format. 
	 * 
	 * Metoden loadContacts kj�rer f�rst for � sjekke at listen er oppdatert f�r den henter kontaktlisten.
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON})  
	public List<Contact> getAllMessages() throws IOException {
		loadContacts();
		return controller.getContactListFormated();
	}

	/**
	 * Metoden ber kontrolleren om � lese kontaktlisten fra filen.
	 */
	public void loadContacts() throws IOException{
		controller.readFromFile();
	}
}
