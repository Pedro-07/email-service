package br.gov.ma.suinf.email.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.ma.suinf.email.model.ClienteAutorizado;

public interface ClienteAutorizadoRepository extends JpaRepository<ClienteAutorizado, String> {
    Optional<ClienteAutorizado> findByClientIdAndAtivoTrue(String clientId);
}
