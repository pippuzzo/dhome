package org.openstreetart.model;

import jakarta.persistence.*;

import java.util.Date;

/**
 * Simple JavaBean domain object with an id property. Used as a base class for objects
 * needing this property.
 */
@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date datUltMov;
	private String usrUltMov;
	private String cmdUltMov;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatUltMov() {
		return datUltMov;
	}

	public void setDatUltMov(Date datUltMov) {
		this.datUltMov = datUltMov;
	}
}
