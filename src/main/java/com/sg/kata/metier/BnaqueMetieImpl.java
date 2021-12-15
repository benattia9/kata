package com.sg.kata.metier;

import java.util.Date;
import java.util.List;
import com.sg.kata.constantes.ConstantesCompte;
import com.sg.kata.dao.CompteRepository;
import com.sg.kata.dao.OperationRepository;
import com.sg.kata.entities.Compte;
import com.sg.kata.entities.CompteCourant;
import com.sg.kata.entities.Operation;
import com.sg.kata.entities.Retrait;
import com.sg.kata.entities.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import exception.ResourceNotFoundException;

@Service
@Transactional

public class BnaqueMetieImpl implements IBanqueMetier {

	@Autowired
	private CompteRepository compteRepository;

	@Autowired
	private OperationRepository operationRepository;

	@Override
	public Compte consulterCompte(String codeCpte) throws ResourceNotFoundException {
		Compte cp = compteRepository.findById(codeCpte).orElse(null);

		if (cp == null)
			throw new ResourceNotFoundException(ConstantesCompte.NOT_FOUND_COMPTE);
		return cp;
	}

	@Override
	public void verser(String codeCompte, double montant) throws ResourceNotFoundException {
		Compte cp = consulterCompte(codeCompte);
		Versement v = new Versement(new Date(), montant, cp);
		operationRepository.save(v);
		cp.setSolde(cp.getSolde() + montant);
		compteRepository.save(cp);

	}

	@Override
	public void retirer(String codeCompte, double montant) throws ResourceNotFoundException {
		double facilitesCaisse = 0;
		Compte cp = consulterCompte(codeCompte);
		if (cp instanceof CompteCourant)
			facilitesCaisse = ((CompteCourant) cp).getDecouvert();
		if (cp.getSolde() + facilitesCaisse < montant)
			throw new ResourceNotFoundException(ConstantesCompte.SOLDE_INSUFISSANT_COMPTE);
		Retrait r = new Retrait(new Date(), montant, cp);
		operationRepository.save(r);
		cp.setSolde(cp.getSolde() - montant);
		compteRepository.save(cp);

	}

	@Override
	public List<Operation> listOperation(String codeCompte) throws ResourceNotFoundException {

		return operationRepository.listOperation(codeCompte);
	}

}
