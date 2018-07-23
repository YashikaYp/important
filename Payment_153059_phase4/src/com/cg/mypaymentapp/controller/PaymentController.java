package com.cg.mypaymentapp.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.mypaymentapp.beans.Customer;

import com.cg.mypaymentapp.service.WalletService;

@Controller
public class PaymentController 
{
@Autowired
WalletService walletServices;





@RequestMapping(value="/save")

public ModelAndView saveCustomer(@Valid @ModelAttribute("customer")Customer customer,BindingResult result) {

	try
	{
	if(result.hasErrors())
		return new ModelAndView("savecustomer");
	customer=walletServices.createAccount(customer);
	}
	catch(Exception e)
	{
		e.printStackTrace();
		return new ModelAndView("errors");
	}
	return new ModelAndView("savesuccess","customer",customer);
}
@RequestMapping(value="/show")

public ModelAndView showBalance(@RequestParam("mobileNo")String mobileno) {
	Customer customer = null;

	try{
		
	
	customer=walletServices.showBalance(mobileno);
	}
	catch(Exception e)
	{
		e.printStackTrace();
		
	}
	return new ModelAndView("balanceSuccess","customer",customer);
}	

@RequestMapping(value="/depositMoney")

public ModelAndView deposit(@RequestParam("mobileNo")String mobileno,@RequestParam("wallet.balance")BigDecimal amount) {
	Customer customer = null;

	try{
		
	
	customer=walletServices.depositAmount(mobileno, amount);
	}
	catch(Exception e)
	{
		e.printStackTrace();
		
	}
	return new ModelAndView("balanceSuccess","customer",customer);
}	

@RequestMapping(value="/withdrawMoney")

public ModelAndView withdraw(@RequestParam("mobileNo")String mobileno,@RequestParam("wallet.balance")BigDecimal amount) {
	Customer customer = null;

	try{
		
	
	customer=walletServices.withdrawAmount(mobileno, amount);
	}
	catch(Exception e)
	{
		e.printStackTrace();
		
	}
	return new ModelAndView("balanceSuccess","customer",customer);
}

@RequestMapping(value="/fund")

public ModelAndView fundTransfer(@RequestParam("mobileNo1")String mobileno1,@RequestParam("mobileNo2")String mobileno2,@RequestParam("wallet.balance")BigDecimal amount) {
	Customer customer = null;

	try{
		
	
	customer=walletServices.fundTransfer(mobileno1,mobileno2, amount);
	}
	catch(Exception e)
	{
		e.printStackTrace();
		
	}
	return new ModelAndView("TransferSuccess","customer",customer);
}	
}
