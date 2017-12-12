package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 *Klassen er selve modellen for kontakter
 *Her opprettes og valideres all kontaktinformasjon
 * @author Luka
 *
 */


@XmlRootElement(name = "Contacts")
public class Contact {

	private String name;
	private String address;
	private String phone;

	/**
	 * Metoden returener navnet til kontakten
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Metoden setter navnet til kontakten
	 * If metoden gjør en sjekk om input inneholder bare bokstaver
	 * @param name
	 * @return
	 */
	public boolean setName(String name) {
		if(isLettersOnly(name)){
			this.name = name;
			return true;
		}
		return false;
	}

	/**
	 * Metoden returnerer addressen til kontakten
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Metoden setter addressen til kontakten
	 * If metoden gjør en sjekk om input inneholder bokstaver og tall
	 * @param address
	 * @return
	 */
	public boolean setAddress(String address) {
		if(isLettersAndNumbers(address)){
			this.address = address;
			return true;
		}
		return false;
	}

	/**
	 * Metoden returnerer telefonnummeret til kontakten
	 * @return
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Metoden setter telefonnummeret til kontakten
	 * If metoden gjør en sjekk om input inneholder bare tall og at input består av 8 siffer.
	 * @param phone
	 * @return
	 */
	public boolean setPhone(String phone) {
		if(isNumber(phone)&& isNumberFormat(phone)){
			this.phone = phone;
			return true;
		}
		return false;
	}

	/**
	 * Metoden sjekker med en RegEx om input inneholder bare tall
	 * Om den feiler gir den en feilmelding i console
	 * @param string
	 * @return
	 */
	public boolean isNumber(String string){
		if(string.matches("^\\d+$")){
			return true;
		}
		else{	
			System.out.println("Error 404: Phone number does not only contain numbers");
			return false;
		}
	}

	/**
	 * Metoden sjekker med en RegEx om input inneholder 8 siffer 
	 * Om den feiler gir den en feilmelding i console
	 * @param string
	 * @return
	 */
	public boolean isNumberFormat(String string){
		if(string.matches("^\\d{8}")){
			return true;
		}
		else {
			System.out.println("Error 404: Phone number is more or less than 8 digits");	
			return false;
		}
	}

	/**
	 * Metoden sjekker med RegEx-er om input inneholder bokstaver og tall 
	 * Om den feiler så gir den en feilmelding i console
	 * @param string
	 * @return
	 */
	public boolean isLettersAndNumbers(String string){
		String numberRegex = ".*[0-9].*";
		String lettersRegex = ".*[A-Za-zÆØÅæøå].*";

		if (string.matches(numberRegex) && string.matches(lettersRegex)) {
			return true;
		}
		else{	
			System.out.println("Error 404: Text must contain letters and numbers");
			return false;
		}
	}

	/**
	 * Metoden sjekker med en RegEx om input innheholder bare bokstaver.
	 * Om den feiler så gir den en feilmelding i console
	 * @param string
	 * @return
	 */
	public boolean isLettersOnly(String string){
		String lettersRegex = ".*[A-Za-zÆØÅæøå].*";
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
