package com.revature.models;

import java.sql.Timestamp;

import com.revature.dto.ReimbursementBasics;

public class Reimbursement {
	private int id;
	private int amount;
	private Timestamp submitted;
	private Timestamp resolved;
	private String description;
	private String receipt;
	private int author;
	private int resolver;
	private int statusId;
	private int typeId;

	
	
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Reimbursement(int id, int amount, Timestamp submitted, Timestamp resolved, String description,
			String receipt, int author, int resolver, int statusId, int typeId) {
		super();
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.statusId = statusId;
		this.typeId = typeId;
	}

	
	public Reimbursement(ReimbursementBasics r, int username) {
		super();
		this.id = 0;
		this.amount = r.getAmount();
	
		this.submitted = new Timestamp(System.currentTimeMillis());
		this.resolved = null;
		this.description = r.getDescription();
		this.receipt = r.getReceipt();
		this.author = username;
		this.resolver = 0;
		this.statusId = 1;
		this.typeId = r.getTypeID();
		
	}
	


	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getAmount() {
		return amount;
	}



	public void setAmount(int amount) {
		this.amount = amount;
	}



	public Timestamp getSubmitted() {
		return submitted;
	}



	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}



	public Timestamp getResolved() {
		return resolved;
	}



	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getReceipt() {
		return receipt;
	}



	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}



	public int getAuthor() {
		return author;
	}



	public void setAuthor(int author) {
		this.author = author;
	}



	public int getResolver() {
		return resolver;
	}



	public void setResolver(int resolver) {
		this.resolver = resolver;
	}



	public int getStatusId() {
		return statusId;
	}



	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}



	public int getTypeId() {
		return typeId;
	}



	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + author;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((receipt == null) ? 0 : receipt.hashCode());
		result = prime * result + ((resolved == null) ? 0 : resolved.hashCode());
		result = prime * result + resolver;
		result = prime * result + statusId;
		result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
		result = prime * result + typeId;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (amount != other.amount)
			return false;
		if (author != other.author)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (receipt == null) {
			if (other.receipt != null)
				return false;
		} else if (!receipt.equals(other.receipt))
			return false;
		if (resolved == null) {
			if (other.resolved != null)
				return false;
		} else if (!resolved.equals(other.resolved))
			return false;
		if (resolver != other.resolver)
			return false;
		if (statusId != other.statusId)
			return false;
		if (submitted == null) {
			if (other.submitted != null)
				return false;
		} else if (!submitted.equals(other.submitted))
			return false;
		if (typeId != other.typeId)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", description="
				+ description + ", resolved=" + resolved + ", receipt=" + receipt + ", author=" + author + ", resolver="
				+ resolver + ", statusId=" + statusId + ", typeId=" + typeId + "]";
	}

}
