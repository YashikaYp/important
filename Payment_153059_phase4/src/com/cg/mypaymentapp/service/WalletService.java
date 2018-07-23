package com.cg.mypaymentapp.service;
import java.math.BigDecimal;

import org.eclipse.jdt.core.compiler.InvalidInputException;

import com.cg.mypaymentapp.beans.Customer;


public interface WalletService {
public Customer createAccount(Customer customer);
public Customer showBalance (String mobileno) throws InvalidInputException; //show the customer details and its balance
public Customer fundTransfer (String sourceMobileNo,String targetMobileNo, BigDecimal amount); // check whether both source and target exists; return source customer object and its balance
public Customer depositAmount (String mobileNo,BigDecimal amount ) throws InvalidInputException; //mobile number exists or not
public Customer withdrawAmount(String mobileNo, BigDecimal amount);

}

