package com.sg.kata.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("R")
public class Retrait extends Operation {

	/**
	 * 
	 */
	public Retrait() {
		super();
	}

	/**
	 * @param dateOperation
	 * @param montant
	 * @param compte
	 */
	public Retrait(Date dateOperation, double montant, Compte compte) {
		super(dateOperation, montant, compte);
	}

}
