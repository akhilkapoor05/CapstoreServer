package com.capgemini.storeserver.controllers;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.storeserver.beans.Coupon;
import com.capgemini.storeserver.beans.Customer;
import com.capgemini.storeserver.beans.Merchant;
import com.capgemini.storeserver.beans.Product;
import com.capgemini.storeserver.services.AdminServices;

@RestController	
public class AdminActionController {
	

	@Autowired
	private AdminServices adminService;
	
	//Working
	@RequestMapping(value="/addMerchant", method=RequestMethod.POST)
	public void addMerchant(@RequestBody Merchant merchant) {
		
		adminService.addMerchant(merchant);
	}
	
	@RequestMapping(value="/removeMerchant")
	public void removeMerchant(int merchantId) {
		
		adminService.removeMerchant(merchantId);
	}
	@RequestMapping(value = "/getAllProduct", method = RequestMethod.GET)
	public List<Product> getAllProduct() {
		List<Product> product = adminService.viewAllProducts();
		Iterator<Product> iterator = product.iterator();
		while (iterator.hasNext())
			System.out.println(iterator.next());
		return adminService.viewAllProducts();
	}

	@RequestMapping(value = "/updateCategory", method = RequestMethod.POST)
	public void updateCategory(@RequestParam("categoryId") int categoryId,
			@RequestParam("categoryName") String categoryName, @RequestParam("type") String type) {
		adminService.updateCategory(categoryId, categoryName, type);
	}
	
	@RequestMapping(value="/addCoupon", method=RequestMethod.POST)
	public void addCoupon(@RequestBody Coupon coupon) {
		
		adminService.addCoupon(coupon);
	}
	
	@RequestMapping(value="/removeCoupon")
	public void removeCoupon(int couponId) {
		adminService.removeCoupon(couponId);
	}
	
	@RequestMapping(value="/getAllCustomer", method=RequestMethod.GET)
	public List<Customer>getAllCustomer()
	{
		List<Customer> cust = adminService.viewAllCustomer();
				Iterator<Customer> it=cust.iterator();
		while(it.hasNext())
			System.out.println(it.next());
		return adminService.viewAllCustomer();
	}
	
}
