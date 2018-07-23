package com.capgemini.paytm.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.paytm.beans.Customer;
import com.capgemini.paytm.beans.Transaction;
import com.capgemini.paytm.beans.Wallet;
import com.capgemini.paytm.exception.InsufficientBalanceException;
import com.capgemini.paytm.exception.InvalidInputException;
import com.capgemini.paytm.repo.WalletRepo;
import com.capgemini.paytm.repo.WalletRepo2;

@Component(value="walletService")
public class WalletServiceImpl implements WalletService {

	@Autowired
	WalletRepo repo;
	
	@Autowired
	WalletRepo2 repo2;
	
	public Customer createAccount(Customer customer) {
				
		return repo.save(customer);
		}

	public Customer showBalance(String mobileNo) {
		
		Customer customer=repo.findOne(mobileNo);		
		if(customer!=null)
			return customer;
		else
			throw new InvalidInputException("Invalid mobile no ");
	}

	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) {	
		

		if(sourceMobileNo.equals(targetMobileNo))
		{
			throw new InvalidInputException("Source and target mobile numbers cannot be same");
		}
		amount= validate(amount);
		Customer scust=new Customer();
		Customer tcust=new Customer();
		scust=repo.findOne(sourceMobileNo);
		tcust=repo.findOne(targetMobileNo);
		Transaction strans=new Transaction();
		Transaction ttrans=new Transaction();
		
		Wallet sw=scust.getWallet();
		Wallet tw=tcust.getWallet();
		
		strans.setMobileNo(sourceMobileNo);
		ttrans.setMobileNo(targetMobileNo);
		strans.setTransaction_amount(amount.floatValue());
		ttrans.setTransaction_amount(amount.floatValue());
		strans.setTransactionDate(new Date().toString());
		ttrans.setTransactionDate(new Date().toString());
		strans.setTransaction_type("Fund Transfer");
		ttrans.setTransaction_type("Fund Transfer");
		
		
		if(scust!=null && tcust!=null )
		{	
			if(scust.getWallet().getBalance().compareTo(amount)==1)
			{
			BigDecimal amtSub=scust.getWallet().getBalance();
			BigDecimal diff=amtSub.subtract(amount);
			sw.setBalance(diff);
			scust.setWallet(sw);
			
			BigDecimal amtAdd=tcust.getWallet().getBalance();
			BigDecimal sum=amtAdd.add(amount);			
			tw.setBalance(sum);
			tcust.setWallet(tw);
			
		
			
			strans.setTransaction_status("successfull");
			ttrans.setTransaction_status("successfull");
			
			tcust.setMobileNo(targetMobileNo);
			repo.save( tcust);
			scust.setMobileNo(sourceMobileNo);
			repo.save( scust);
			
			}
			else
				{
				strans.setTransaction_status("failed");
				ttrans.setTransaction_status("failed");
				
				throw new InsufficientBalanceException("Amount is more than available balance");
				
				}
		}
		else
		{
			throw new InvalidInputException("Account does not exists");
		}
		
		
		repo2.save(strans);
		
		repo2.save(ttrans);
		
		return tcust;
	}

	public Customer depositAmount(String mobileNo, BigDecimal amount) {
		amount= validate(amount);
		Customer cust=new Customer();
		cust=repo.findOne(mobileNo);
		Wallet wallet=cust.getWallet();
		Transaction strans=new Transaction();
		
		
		
		
		strans.setMobileNo(mobileNo);
		strans.setTransaction_amount(amount.floatValue());
		strans.setTransactionDate(new Date().toString());
		strans.setTransaction_type("Deposit");
		
		
		
		if(cust!=null)
		{
			strans.setTransaction_amount(amount.floatValue());
			
			
			BigDecimal amtAdd=cust.getWallet().getBalance().add(amount);
			wallet.setBalance(amtAdd);
			cust.setWallet(wallet);
			strans.setTransaction_status("success");
			
			repo2.save(strans);
			cust.setMobileNo(mobileNo);
			repo.save(cust);
			 
			 
			
		}
		else
		
			
			throw new InvalidInputException("Account does not exists");
		
		return cust;
		
	}

	public Customer withdrawAmount(String mobileNo, BigDecimal amount) {
		
		amount= validate(amount);
		Customer cust=new Customer();
		cust=repo.findOne(mobileNo);
		Wallet wallet=cust.getWallet();
		
		Transaction strans=new Transaction();
		
		strans.setMobileNo(mobileNo);
		strans.setTransaction_amount(amount.floatValue());
		strans.setTransactionDate(new Date().toString());
		strans.setTransaction_type("Withdraw");
		
		
		if(cust!=null)
		{
			if(cust.getWallet().getBalance().compareTo(amount)==1)
			{
			BigDecimal amtSub=cust.getWallet().getBalance().subtract(amount);
			wallet.setBalance(amtSub);
			cust.setWallet(wallet);
			cust.setMobileNo(mobileNo);
			repo.save( cust);
		
			strans.setTransaction_status("success");
			//obj.getData().put(mobileNo, cust);
			}
			else
				{
				strans.setTransaction_status("failed");
				throw new InsufficientBalanceException("Sorry cannot withdraw,amount to be withdrawn is more than available balance");
				}
		}
		else
			throw new InvalidInputException("Account does not exists");
		
		repo2.save(strans);
		
		return cust;
	}
	
	
	public List<Transaction> printTransaction(String mobileNo)
	{
		
		
		return repo2.findByMobileNo(mobileNo);
		
	}
	
	public boolean validate(String name,String phoneno,Customer cust)  {
		Scanner sc=new Scanner(System.in);
		while(true)
		{Pattern pa=Pattern.compile("[a-zA-Z]+\\.?");
		Matcher ma=pa.matcher(name);
		if(ma.matches())
		{
			break;
		}
		else
		{
			System.err.println("Enter valid name: ");
			name=sc.next();
		}
			
		}
		
		 //check if phone no is valid
		while(true)
		{Pattern p=Pattern.compile("(0/91)?[7-9][0-9]{9}");
		Matcher m=p.matcher(phoneno);
		if(m.matches())
		{
			break;
		}
		else
		{
			System.err.println("Enter valid 10 digit phone no: ");
			phoneno=sc.next();
		}
			
		}
		cust.setMobileNo(phoneno);
		cust.setName(name);
		return true;
	}
	public boolean validate(BigDecimal amount,Customer cust)
	{
		Scanner sc=new Scanner(System.in);
		while(true)
		{
			if(Math.abs(amount.floatValue())==amount.floatValue())
			{
				break;
			}
			else
			{
				System.err.println("Enter positive amount: ");
				amount=sc.nextBigDecimal();
			}
				
		}
		cust.setWallet(new Wallet(amount));
		
		return true;
		
	}
	public BigDecimal validate(BigDecimal amount)
	{
		Scanner sc=new Scanner(System.in);
		while(true)
		{
			if(Math.abs(amount.floatValue())==amount.floatValue())
			{
				break;
			}
			else
			{
				System.err.println("Enter positive amount: ");
				amount=sc.nextBigDecimal();
			}
				
		}
		
		return amount;
		
	}
}
