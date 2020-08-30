package com.revature.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ers_reimbursement")
public class Reimbursement implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="reimb_id")
	private int reimbId;
	
	@Column(name="reimb_amount", nullable=false)
	private double reimbAmt;
	
	@Column(name="reimb_submitted", nullable=false)
	private Timestamp submitted;
	
	@Column(name="reimb_resolved")
	private Timestamp resolved;
	
	@Column(name="reimb_description")
	private String description;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="reimb_author", nullable=false)
	private User reimbAuthorId;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="reimb_resolver")
	private User reimbResolverId;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="reimb_status_id_fk", nullable=false)
	private ReimbStatus reimbStatusId;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="reimb_type_id_fk", nullable=false)
	private ReimbType reimbTypeId;
	
	public Reimbursement() {
		super();
	}

	public Reimbursement(int reimbId, double reimbAmt, Timestamp submitted, Timestamp resolved, String description,
			User reimbAuthorId, User reimbResolverId, ReimbStatus reimbStatusId, ReimbType reimbTypeId) {
		super();
		this.reimbId = reimbId;
		this.reimbAmt = reimbAmt;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.reimbAuthorId = reimbAuthorId;
		this.reimbResolverId = reimbResolverId;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
	}

	public Reimbursement(double reimbAmt, Timestamp submitted, Timestamp resolved, String description,
			User reimbAuthorId, User reimbResolverId, ReimbStatus reimbStatusId, ReimbType reimbTypeId) {
		super();
		this.reimbAmt = reimbAmt;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.reimbAuthorId = reimbAuthorId;
		this.reimbResolverId = reimbResolverId;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
	}

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public double getReimbAmt() {
		return reimbAmt;
	}

	public void setReimbAmt(double reimbAmt) {
		this.reimbAmt = reimbAmt;
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

	public User getReimbAuthorId() {
		return reimbAuthorId;
	}

	public void setReimbAuthorId(User reimbAuthorId) {
		this.reimbAuthorId = reimbAuthorId;
	}

	public User getReimbResolverId() {
		return reimbResolverId;
	}

	public void setReimbResolverId(User reimbResolverId) {
		this.reimbResolverId = reimbResolverId;
	}

	public ReimbStatus getReimbStatusId() {
		return reimbStatusId;
	}

	public void setReimbStatusId(ReimbStatus reimbStatusId) {
		this.reimbStatusId = reimbStatusId;
	}

	public ReimbType getReimbTypeId() {
		return reimbTypeId;
	}

	public void setReimbTypeId(ReimbType reimbTypeId) {
		this.reimbTypeId = reimbTypeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		long temp;
		temp = Double.doubleToLongBits(reimbAmt);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((reimbAuthorId == null) ? 0 : reimbAuthorId.hashCode());
		result = prime * result + reimbId;
		result = prime * result + ((reimbResolverId == null) ? 0 : reimbResolverId.hashCode());
		result = prime * result + ((reimbStatusId == null) ? 0 : reimbStatusId.hashCode());
		result = prime * result + ((reimbTypeId == null) ? 0 : reimbTypeId.hashCode());
		result = prime * result + ((resolved == null) ? 0 : resolved.hashCode());
		result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
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
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (Double.doubleToLongBits(reimbAmt) != Double.doubleToLongBits(other.reimbAmt))
			return false;
		if (reimbAuthorId == null) {
			if (other.reimbAuthorId != null)
				return false;
		} else if (!reimbAuthorId.equals(other.reimbAuthorId))
			return false;
		if (reimbId != other.reimbId)
			return false;
		if (reimbResolverId == null) {
			if (other.reimbResolverId != null)
				return false;
		} else if (!reimbResolverId.equals(other.reimbResolverId))
			return false;
		if (reimbStatusId == null) {
			if (other.reimbStatusId != null)
				return false;
		} else if (!reimbStatusId.equals(other.reimbStatusId))
			return false;
		if (reimbTypeId == null) {
			if (other.reimbTypeId != null)
				return false;
		} else if (!reimbTypeId.equals(other.reimbTypeId))
			return false;
		if (resolved == null) {
			if (other.resolved != null)
				return false;
		} else if (!resolved.equals(other.resolved))
			return false;
		if (submitted == null) {
			if (other.submitted != null)
				return false;
		} else if (!submitted.equals(other.submitted))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", reimbAmt=" + reimbAmt + ", submitted=" + submitted
				+ ", resolved=" + resolved + ", description=" + description + ", reimbAuthorId=" + reimbAuthorId.getUserId()
				+ ", reimbResolverId=" + reimbResolverId.getUserId() + ", reimbStatusId=" + reimbStatusId.getStatusId() + ", reimbTypeId="
				+ reimbTypeId.getTypeId() + "]";
	}
	
	
}
