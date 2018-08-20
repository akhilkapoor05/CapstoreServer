package com.capgemini.storeserver.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.storeserver.beans.Address;
import com.capgemini.storeserver.beans.Cart;
import com.capgemini.storeserver.beans.Category;
import com.capgemini.storeserver.beans.Customer;
import com.capgemini.storeserver.beans.Product;
import com.capgemini.storeserver.exceptions.CustomerNotFoundException;
import com.capgemini.storeserver.exceptions.InvalidInputException;
import com.capgemini.storeserver.exceptions.ProductUnavailableException;
import com.capgemini.storeserver.services.CustomerServices;

@RestController
public class CustomerActionController {

	public Customer customer;

	@Autowired
	private CustomerServices customerService; 


	// Customer SignUp
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public void signUp(@RequestBody Customer customer) {
		customerService.signUp(customer);
	}

	// CustomerSignIn
	@RequestMapping(value = "/customerSignIn")
	public ResponseEntity<String> customerSignIn(String email, String password) throws InvalidInputException {
		customer = customerService.customerSignIn(email, password);
		String name = customer.getCustomerName();
		return new ResponseEntity<String>(name, HttpStatus.OK);
	}

	// getCustomerDetails
	@RequestMapping(value = "/getCustomerDetails")
	public Customer getCustomerDetails(String phoneNumber)
			throws InvalidInputException {
		customer = customerService.getCustomerDetails(phoneNumber);
		return  customer;
	}

	// getAllProducts
	@RequestMapping(value = "/getAllProducts")
	public List getAllProductsFromDB()
			throws InvalidInputException {
		List <Product> products = customerService.getAllProducts();
		return  products;
	}

	@RequestMapping(value = "/getProductById")
	public Product getProductById(int productId) throws InvalidInputException {
		Product product = customerService.getProductById(productId);
		return  product;
	}

	@RequestMapping(value = "/getProductByCategory")
	public List<Product> getProductByCategory(Category category ) throws InvalidInputException {
		List<Product> products = customerService.getProductByCategory(category);
		return  products;
	}

	@RequestMapping(value = "/getDeliveryStatus")
	public String getDeliveryStatus(int orderId) throws InvalidInputException {
		String status = customerService.getDeliveryStatus(orderId);
		return  status;
	}
	//gvk
	@RequestMapping(value="/updateSecurityQuestion")
	public boolean updateSecurityQuestion(String phoneNumber,String securityQuestion) throws InvalidInputException{
		return  customerService.updateSecurityQuestion(phoneNumber, securityQuestion);
	}

	@RequestMapping(value="/updateSecurityAnswer")
	public boolean updateSecurityAnswer(String phoneNumber,String securityAnswer){
		try {
			return  customerService.updateSecurityAnswer(phoneNumber, securityAnswer);
		} catch (InvalidInputException e) {
			e.printStackTrace();
			return false;
		}
	}
	@RequestMapping(value="/updateCardNumber")
	public boolean updateCardNumber(String phoneNumber,String cardNumber){
		try {
			return  customerService.updateCardNumber(phoneNumber, cardNumber);
		} catch (InvalidInputException e) {
			e.printStackTrace();
			return false;
		}
	}
	@RequestMapping(value="/updateCustomerName")
	public boolean updateCustomerName(String phoneNumber,String customerName){
		try {
			return  customerService.updateCustomerName(phoneNumber, customerName);
		} catch (InvalidInputException e) {
			e.printStackTrace();
			return false;
		}
	}
	//pavani
	@RequestMapping(value = "/addAddress")
	public void addAddressDetails(Address address)
	{
		customerService.addAddress(address);
	}
	@RequestMapping(value = "/viewAddressDetails")
	public Address getAddressDetails(int addressId)
	{
		Address address;

		address = customerService.getAddress(addressId);

		return  address;
	}
	@RequestMapping(value = "/getCoupon")
	public double getCouponDetails(int cartId)
	{
		double price =  customerService.applyCoupon(cartId);
		return  price;
	}
	//aksh
	@RequestMapping(value= "/getWishlist")
	public List<Product> getWishlist(String phoneNumber) throws InvalidInputException {
		return customerService.getWishlist(phoneNumber);
	}

	@RequestMapping(value= "/addProductToWishlist")
	public boolean addProductToWishlist(String phoneNumber, int productId) throws InvalidInputException {
		return customerService.addProductToWishlist(phoneNumber, productId);
	}

	@RequestMapping(value= "/removeProductFromWishlist")
	public boolean removeProductFromWishlist(String phoneNumber,int productId) throws InvalidInputException {
		return customerService.removeProductFromWishlist(phoneNumber, productId);
	}


	@RequestMapping(value= "/setReview")
	public void setReview(String phoneNumber,int rating,String comments,int productId) throws InvalidInputException {
		customerService.setReviewMethod(phoneNumber, rating, comments, productId);
	}

	@RequestMapping(value= "/securityQuestion")
	public String securityQuestion(String phoneNumber, String securityAnswer) throws InvalidInputException {
		return customerService.securityQuestion(phoneNumber, securityAnswer);
	}

	@RequestMapping(value= "/applyDiscount")
	public double applyDiscount(int productId) throws InvalidInputException {
		return customerService.applyDiscount(productId);
	}
	@RequestMapping(value= "/forgotPassword")
	public String forgotPassword(String phoneNumber) throws InvalidInputException, CustomerNotFoundException {
		return customerService.forgotPassword(phoneNumber);
	}
	@RequestMapping(value= "/onlinePayment")
	public void onlinePayment(String phoneNumber,String cardNumber) throws InvalidInputException {
		customerService.onlinePayment(cardNumber, phoneNumber);
	}
	@RequestMapping(value= "/addProductToNewCart")
	public Cart addProductToNewCart(String phoneNumber,int quantity, int productId) throws InvalidInputException, ProductUnavailableException {
		return customerService.addProductToNewCart(phoneNumber, productId, quantity);
	}
	@RequestMapping(value= "/updateCart")
	public Cart updateCart(String phoneNumber,int quantity, int productId) throws InvalidInputException, ProductUnavailableException {
		return customerService.updateCart(phoneNumber, productId, quantity);
	}
	@RequestMapping(value= "/removeFromCart")
	public Cart removeFromCart(String phoneNumber,int productId, int quantity) throws InvalidInputException {
		return customerService.removeProductFromCart(phoneNumber, productId);
	}
	@RequestMapping(value= "/getCart")
	public List<Product> getCart(String phoneNumber) throws InvalidInputException {
		return customerService.getAllProductsFromCart(phoneNumber);
	}
	@RequestMapping(value="/changePassword")
	public boolean changePassword(String phoneNumber, String newPassword) throws InvalidInputException, CustomerNotFoundException {
		return customerService.changePassword(phoneNumber, newPassword);
	}

	@RequestMapping(value="/successReturn")
	public boolean successReturn(int orderId) {
		return customerService.returnGoods(orderId);
	}

	@RequestMapping(value= "/sortByAsc")
	public List<Product> sortByAsc(String categoryName) throws InvalidInputException, ProductUnavailableException {
		return customerService.getProductsByPriceAsc(categoryName);
	}
	
	@RequestMapping(value= "/sortByDesc")
	public List<Product> sortByDesc(String categoryName) throws InvalidInputException, ProductUnavailableException {
		return customerService.getProductsByPriceDesc(categoryName);
	}
}
