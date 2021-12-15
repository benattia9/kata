package com.sg.kata.metier;

import java.util.List;

import com.sg.kata.entities.Compte;
import com.sg.kata.entities.Operation;

import exception.ResourceNotFoundException;

public interface IBanqueMetier {
	public Compte consulterCompte(String codeCpte) throws ResourceNotFoundException;

	public void verser(String codeCompte, double montan) throws ResourceNotFoundException;

	public void retirer(String codeCompte, double montant) throws ResourceNotFoundException;

	public List<Operation> listOperation(String codeCompte) throws ResourceNotFoundException;

}
