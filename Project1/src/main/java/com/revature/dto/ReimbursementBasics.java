package com.revature.dto;

public class ReimbursementBasics {

	private int amount;
	private String description;
	private String receipt;
	private String type;
	public ReimbursementBasics() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReimbursementBasics(int amount, String description, String receipt, String type) {
		super();
		this.amount = amount;
		this.description = description;
		this.receipt = receipt;
		this.type = type;
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
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public int getTypeID() {
		switch (type) {
		case "LODGING":
			return 1;

		case "TRAVEL":
			return 2;

		case "FOOD":
			return 3;
			
		case "OTHER":
			return 4;

		default:
			return 0;
		}
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((receipt == null) ? 0 : receipt.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		ReimbursementBasics other = (ReimbursementBasics) obj;
		if (amount != other.amount)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (receipt == null) {
			if (other.receipt != null)
				return false;
		} else if (!receipt.equals(other.receipt))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ReimbursementBasics [amount=" + amount + ", description=" + description + ", receipt=" + receipt
				+ ", type=" + type + "]";
	}
	
	
}
