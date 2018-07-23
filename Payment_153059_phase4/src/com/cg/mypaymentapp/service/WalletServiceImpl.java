
package com.cg.mypaymentapp.service;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.repo.WalletRepoDao;

@Component(value = "walletService")
public class WalletServiceImpl implements WalletService {

	@Autowired
	WalletRepoDao repo1;
	@Autowired
	WalletRepoDao repo2;

	@Override
	public Customer createAccount(Customer customer) {

		return repo1.save(customer);
	}

	@Override
	public Customer showBalance(String mobileno) throws InvalidInputException {
		Customer customer = repo1.findOne(mobileno);
		if (customer != null) {
			return customer;
		} else
			throw new InvalidInputException("somethis went wrong while processing your request");
	}

	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) {
		Customer c1 = repo1.findOne(sourceMobileNo);
		try {
			if (c1 != null) {
				Customer c2 = repo1.findOne(targetMobileNo);
				if (c2 != null) {
					c2 = depositAmount(targetMobileNo, amount);
					if (c2 != null) {
						c1 = withdrawAmount(sourceMobileNo, amount);
					} else {
						throw new InsufficientBalanceException("Balance Insufficient");
					}

				} else
					throw new InvalidInputException("Targer account holder not found");

			} else
				throw new InvalidInputException("Source account holder not found");
		}

		catch (InsufficientBalanceException e) {
			e.getMessage();

		} catch (InvalidInputException e) {
			e.getMessage();

		}

		return c1;

	}

	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount) throws InvalidInputException {
		Customer customer = new Customer();
		customer = repo1.findOne(mobileNo);
		Wallet wallet = customer.getWallet();
		if (customer != null) {
			BigDecimal amt = customer.getWallet().getBalance();
			amt = amt.add(amount);

			wallet.setBalance(amt);
			customer.setWallet(wallet);
			repo1.save(customer);

		} else

			throw new InvalidInputException("Details not valid");

		return customer;
	}

	@Override
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) {
		Customer cust = repo1.findOne(mobileNo);
		BigDecimal prevAmt = null;
		Wallet wallet = cust.getWallet();
		if (cust != null)
			prevAmt = cust.getWallet().getBalance();
		int result = prevAmt.compareTo(amount);
		if (result == 1 || result == 0)

		{
			prevAmt = prevAmt.subtract(amount);
			wallet.setBalance(prevAmt);
			cust.setWallet(wallet);

			repo1.save(cust);
		}
		return cust;
	}


		
	}

