package br.gov.ma.suinf.email.service;

import br.gov.ma.suinf.email.dto.EmailRequestDTO;

public interface EmailService {

	void enviarEmail (EmailRequestDTO dto);
}
