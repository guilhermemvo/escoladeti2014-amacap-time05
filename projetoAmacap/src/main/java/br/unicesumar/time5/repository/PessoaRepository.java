package br.unicesumar.time5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unicesumar.time5.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
