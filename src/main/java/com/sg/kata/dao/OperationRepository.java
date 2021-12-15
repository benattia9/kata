package com.sg.kata.dao;

import java.util.List;
import com.sg.kata.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

	@Query("select o from Operation o where o.compte.codeCompte =:x order by o.dateOperation desc")
	public List<Operation> listOperation(@Param("x") String codeCompte);

}
