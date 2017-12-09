package model;

import java.util.regex.Pattern;

public class Contact {

	private String name;
	private String address;
	private String phone;
	
	public Contact() {
	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(isLettersOnly(name)){
			this.name = name;
		}
		else{
			System.out.println("Name is not a String");
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		if(isLettersAndNumbers(address)){
			this.address = address;
		}
		else{
			System.out.println("Address is not a String");
		}
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		if(isNumber(phone)&& isNumberFormat(phone)){
			this.phone = phone;
		}
	}
	
	public boolean isNumber(String string){
		if(string.matches("^\\d+$")){
			return true;
		}
		else{	
			System.out.println("Error 404: Phone number does not only containt numbers");
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
		String lettersRegex = ".*[∆ÿ≈Ê¯ÂA-Za-z].*";

		if (string.matches(numberRegex) && string.matches(lettersRegex)) {
		    return true;
		}
		else{	
			System.out.println("Error 404: Text must contain letters and numbers");
			return false;
		}
	}
	
	public boolean isLettersOnly(String string){
		String lettersRegex = ".*[∆ÿ≈Ê¯ÂA-Za-z].*";
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
