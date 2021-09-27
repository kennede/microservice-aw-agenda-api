package com.github.sepulvida.agendaapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.sepulvida.agendaapi.model.entity.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long>{

}
