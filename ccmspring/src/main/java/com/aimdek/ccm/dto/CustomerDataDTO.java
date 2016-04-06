package com.aimdek.ccm.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aimdek.ccm.document.Address;
import com.aimdek.ccm.document.CreditCard;
import com.aimdek.ccm.document.Geographic;
import com.aimdek.ccm.document.User;

/**
 * The Class CustomerDataDTO.
 *
 * @author aimdek.team
 */
public class CustomerDataDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2041554384632480308L;

	/** The profile picture. */
	private MultipartFile profilePicture;

	/** The user. */
	private User user = new User();

	/** The address. */
	private Address address = new Address();

	/** The geographic. */
	private Geographic geographic = new Geographic();

	/** The credit card. */
	private CreditCard creditCard = new CreditCard();

	/** The today. */
	private Date today = new Date();

	/** The country list. */
	private List<String> countryList = new ArrayList<String>();

	/** The state list. */
	private List<String> stateList = new ArrayList<String>();

	/** The city list. */
	private List<String> cityList = new ArrayList<String>();

	/** The zip code. */
	private String zipCode;

	/** The credit cards. */
	private List<CreditCard> creditCards = new ArrayList<CreditCard>();

	/**
	 * Gets the profile picture.
	 *
	 * @return the profilePicture
	 */
	public MultipartFile getProfilePicture() {
		return profilePicture;
	}

	/**
	 * Sets the profile picture.
	 *
	 * @param profilePicture
	 *            the profilePicture to set
	 */
	public void setProfilePicture(MultipartFile profilePicture) {
		this.profilePicture = profilePicture;
	}

	/**
	 * Gets the zip code.
	 *
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * Sets the zip code.
	 *
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}


	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address
	 *            the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * Gets the geographic.
	 *
	 * @return the geographic
	 */
	public Geographic getGeographic() {
		return geographic;
	}

	/**
	 * Sets the geographic.
	 *
	 * @param geographic
	 *            the geographic to set
	 */
	public void setGeographic(Geographic geographic) {
		this.geographic = geographic;
	}

	/**
	 * Gets the credit card.
	 *
	 * @return the creditCard
	 */
	public CreditCard getCreditCard() {
		return creditCard;
	}

	/**
	 * Sets the credit card.
	 *
	 * @param creditCard
	 *            the creditCard to set
	 */
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	/**
	 * Gets the today.
	 *
	 * @return the today
	 */
	public Date getToday() {
		return today;
	}

	/**
	 * Sets the today.
	 *
	 * @param today
	 *            the today to set
	 */
	public void setToday(Date today) {
		this.today = today;
	}

	/**
	 * Gets the country list.
	 *
	 * @return the countryList
	 */
	public List<String> getCountryList() {
		return countryList;
	}

	/**
	 * Sets the country list.
	 *
	 * @param countryList
	 *            the countryList to set
	 */
	public void setCountryList(List<String> countryList) {
		this.countryList = countryList;
	}

	/**
	 * Gets the state list.
	 *
	 * @return the stateList
	 */
	public List<String> getStateList() {
		return stateList;
	}

	/**
	 * Sets the state list.
	 *
	 * @param stateList
	 *            the stateList to set
	 */
	public void setStateList(List<String> stateList) {
		this.stateList = stateList;
	}

	/**
	 * Gets the city list.
	 *
	 * @return the cityList
	 */
	public List<String> getCityList() {
		return cityList;
	}

	/**
	 * Sets the city list.
	 *
	 * @param cityList
	 *            the cityList to set
	 */
	public void setCityList(List<String> cityList) {
		this.cityList = cityList;
	}

	/**
	 * Gets the credit cards.
	 *
	 * @return the creditCards
	 */
	public List<CreditCard> getCreditCards() {
		return creditCards;
	}

	/**
	 * Sets the credit cards.
	 *
	 * @param creditCards
	 *            the creditCards to set
	 */
	public void setCreditCards(List<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
}
