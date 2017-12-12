package model;

import java.io.FileNotFoundException;
import java.util.regex.Pattern;

import javax.xml.bind.annotation.XmlRootElement;

import controller.Controller;



@XmlRootElement(name = "Contacts")
public class Contact {

	private String name;
	private String address;
	private String phone;

	public String getName() {
		return name;
	}
	
	public boolean setName(String name) {
		if(isLettersOnly(name)){
			this.name = name;
			return true;
		}
		return false;
	}

	public String getAddress() {
		return address;
	}

	public boolean setAddress(String address) {
		if(isLettersAndNumbers(address)){
			this.address = address;
			return true;
		}
		return false;
	}

	public String getPhone() {
		return phone;
	}

	public boolean setPhone(String phone) {
		if(isNumber(phone)&& isNumberFormat(phone)){
			this.phone = phone;
			return true;
		}
		return false;
	}
	
	public boolean isNumber(String string){
		if(string.matches("^\\d+$")){
			return true;
		}
		else{	
			System.out.println("Error 404: Phone number does not only contain numbers");
			return false;
		}
	}
	
	public boolean isNumberFormat(String string){
		if(string.matches("^\\d{8}")){
			return true;
		}
		else {
			System.out.println("Error 404: Phone number is more or less than 8 digits");	
			return false;
		}
	}
	
	public boolean isLettersAndNumbers(String string){
		String numberRegex = ".*[0-9].*";
		//String lettersRegex = ".*[∆ÿ≈Ê¯ÂA-Za-z].*";
		String lettersRegex = ".*[A-Za-z].*";

		if (string.matches(numberRegex) && string.matches(lettersRegex)) {
		    return true;
		}
		else{	
			System.out.println("Error 404: Text must contain letters and numbers");
			return false;
		}
	}
	
	public boolean isLettersOnly(String string){
		String lettersRegex = ".*[A-Za-z].*";
		//String lettersRegex = ".*[∆ÿ≈Ê¯ÂA-Za-z].*";
		String numberRegex = ".*[0-9].*";
		
		if (string.matches(lettersRegex) && !string.matches(numberRegex)) {
		    return true;
		}
		else{	
			System.out.println("Error 404: Text can only contain letters");
			return false;
		}
	}
	

}
