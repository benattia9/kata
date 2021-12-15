package com.sg.kata.dao;

import com.sg.kata.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CompteRepository extends JpaRepository<Compte, String> {

}
