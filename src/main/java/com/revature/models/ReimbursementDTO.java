package com.revature.models;

public class ReimbursementDTO {
	
	public int id;
	public double amt;
	public String description;
	public int rAuthorId;
	public int rResolverId;
	public String rStatus;
	public String rType;
	
	public ReimbursementDTO() {
		super();
	}

	public ReimbursementDTO(double amt, String description, int rAuthorId, String rStatus, String rType) {
		super();
		this.amt = amt;
		this.description = description;
		this.rAuthorId = rAuthorId;
		this.rStatus = rStatus;
		this.rType = rType;
	}

	public ReimbursementDTO(int id, double amt, String description, int rAuthorId, String rStatus, String rType) {
		super();
		this.id = id;
		this.amt = amt;
		this.description = description;
		this.rAuthorId = rAuthorId;
		this.rStatus = rStatus;
		this.rType = rType;
	}

	public ReimbursementDTO(int id, double amt, String description, int rAuthorId, int rResolverId, String rStatus,
			String rType) {
		super();
		this.id = id;
		this.amt = amt;
		this.description = description;
		this.rAuthorId = rAuthorId;
		this.rResolverId = rResolverId;
		this.rStatus = rStatus;
		this.rType = rType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmt() {
		return amt;
	}

	public void setAmt(double amt) {
		this.amt = amt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getrAuthorId() {
		return rAuthorId;
	}

	public void setrAuthorId(int rAuthorId) {
		this.rAuthorId = rAuthorId;
	}

	public int getrResolverId() {
		return rResolverId;
	}

	public void setrResolverId(int rResolverId) {
		this.rResolverId = rResolverId;
	}

	public String getrStatus() {
		return rStatus;
	}

	public void setrStatus(String rStatus) {
		this.rStatus = rStatus;
	}

	public String getrType() {
		return rType;
	}

	public void setrType(String rType) {
		this.rType = rType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amt);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + rAuthorId;
		result = prime * result + rResolverId;
		result = prime * result + ((rStatus == null) ? 0 : rStatus.hashCode());
		result = prime * result + ((rType == null) ? 0 : rType.hashCode());
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
		ReimbursementDTO other = (ReimbursementDTO) obj;
		if (Double.doubleToLongBits(amt) != Double.doubleToLongBits(other.amt))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (rAuthorId != other.rAuthorId)
			return false;
		if (rResolverId != other.rResolverId)
			return false;
		if (rStatus == null) {
			if (other.rStatus != null)
				return false;
		} else if (!rStatus.equals(other.rStatus))
			return false;
		if (rType == null) {
			if (other.rType != null)
				return false;
		} else if (!rType.equals(other.rType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReimbursementDTO [id=" + id + ", amt=" + amt + ", description=" + description + ", rAuthorId="
				+ rAuthorId + ", rResolverId=" + rResolverId + ", rStatus=" + rStatus + ", rType=" + rType + "]";
	}
	
}