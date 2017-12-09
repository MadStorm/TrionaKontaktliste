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
		if(isRegex(name)){
			this.name = name;
		}
		else
			System.out.println("Name is not a String");
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		if(isRegex(address)){
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
		else{
			System.out.println("Phone is not a Number");			
		}
	}
	
	public boolean isNumber(String string){
		return string.matches("^\\d+$");
	}
	
	public boolean isRegex(String string){
		boolean isRegex;
		try {
			Pattern.compile(string);
			return isRegex = true;
		} catch (Exception e) {
			return isRegex = false;
		}
	}
	
	public boolean isNumberFormat(String string){
		if(string.matches("^\\d{8}")){
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
}
