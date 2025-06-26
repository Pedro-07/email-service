package br.gov.ma.suinf.email.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ClienteAutorizado {
	
	@Id
	private String clientId;
	
	private String apiKeyHash;
	private boolean ativo = true;
	
	private String nomeApp;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getApiKeyHash() {
		return apiKeyHash;
	}

	public void setApiKeyHash(String apiKeyHash) {
		this.apiKeyHash = apiKeyHash;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getNomeApp() {
		return nomeApp;
	}

	public void setNomeApp(String nomeApp) {
		this.nomeApp = nomeApp;
	}

	
}
