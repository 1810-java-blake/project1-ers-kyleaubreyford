package com.revature.dto;

import java.io.Console;
import javax.print.attribute.standard.PrinterLocation;

public class ReimbursementTable {

	private int id;
	String authorusername;
	int amount;
	String description;
	String type;
	String submitted;
	String receipt;
	String status;
	String resolverusername;
	String resolved;
	
	
	public ReimbursementTable() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public ReimbursementTable(int id, String authorusername, int amount, String description, String type,
			String submitted, String receipt, String status, String resolverusername, String resolved) {
		super();
		this.id = id;
		this.authorusername = authorusername;
		this.amount = amount;
		this.description = description;
		this.type = type;
		this.submitted = submitted;
		this.receipt = receipt;
		this.status = status;
		this.resolverusername = resolverusername;
		this.resolved = resolved;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthorusername() {
		return authorusername;
	}
	public void setAuthorusername(String authorusername) {
		this.authorusername = authorusername;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSubmitted() {
		return submitted;
	}
	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getResolverusername() {
		return resolverusername;
	}
	public void setResolverusername(String resolverusername) {
		this.resolverusername = resolverusername;
	}
	public String getResolved() {
		return resolved;
	}
	public void setResolved(String resolved) {
		this.resolved = resolved;
	} 
	
	
}