package com.cg.mypaymentapp.beans;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity

public class Customer {

	private String name;
	@Id
	@Size(max=10,message="Enter 10 digit mobile no")
	@Pattern(regexp="(0/91)?[7-9][0-9]{9}",message="Enter valid mobile no")
	private String mobileNo;
	
	@OneToOne(targetEntity=Wallet.class,cascade=CascadeType.ALL)
	@JoinColumn
	private Wallet wallet;

	public Customer(String name2, String mobileNo2, Wallet wallet2) {
		this.name = name2;
		this.mobileNo = mobileNo2;
		this.wallet = wallet2;
	}

	public Customer() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	@Override
	public String toString() {
		return "Customer name :" + name + "\n" + "mobileNo : " + mobileNo + "\n" + wallet;
	}

}
