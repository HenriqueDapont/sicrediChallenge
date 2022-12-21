package com.sicredi.challenge.repository;

import com.sicredi.challenge.model.AgendaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends JpaRepository<AgendaModel, Long> {
}
