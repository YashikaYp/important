package com.cg.mypaymentapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cg.mypaymentapp.beans.Customer;

@Controller
public class URIController {

	
	

	@RequestMapping(value="/")
	public String getIndexPage() {
		return "HomePage";
	}
	
	@RequestMapping(value="/saveCustomer")
	public String getRegistrationPage() {
		return "savecustomer";
	}
	
	@RequestMapping(value="/showBalance")
	public String getBalancePage() {
		return "showBalance";
	}
	
	@RequestMapping(value="/deposit")
	public String getDepositPage() {
		return "depositAmount";
	}
	
	@RequestMapping(value="/withdraw")
	public String getWithdrawPage() {
		return "withdraw";
	}
	
	@RequestMapping(value="/fundTransfer")
	public String getFundTransferPage() {
		return "fundTransfer";
	}
	
	@ModelAttribute("customer")
	public Customer getCustomer()
	{
		return new Customer();
	}
}
