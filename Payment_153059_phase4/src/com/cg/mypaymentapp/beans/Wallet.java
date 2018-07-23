package com.cg.mypaymentapp.beans;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Wallet {
private BigDecimal balance;
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int id;

public Wallet() {
	super();
}

public Wallet(BigDecimal amount) {
	this.balance=amount;
}

public BigDecimal getBalance() {
	return balance;
}

public void setBalance(BigDecimal balance) {
	this.balance = balance;
}


public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public Wallet(int id) {
	super();
	this.id = id;
}

@Override
	public String toString() {
	return "balance : "+balance;
}

}
