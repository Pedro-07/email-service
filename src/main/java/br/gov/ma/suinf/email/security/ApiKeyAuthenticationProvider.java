package br.gov.ma.suinf.email.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import br.gov.ma.suinf.email.repository.ClienteAutorizadoRepository;
import br.gov.ma.suinf.recovery.security.ApiKeyAuthentication;
@Component
public class ApiKeyAuthenticationProvider {

    @Autowired
    private ClienteAutorizadoRepository repository;

    public boolean isValid(String clientId, String apiKey) {
        return repository.findByClientIdAndAtivoTrue(clientId)
                .map(cliente -> HashUtil.sha256(apiKey).equals(cliente.getApiKeyHash()))
                .orElse(false);
    }

    public Authentication getAuthentication(String clientId) {
        return new ApiKeyAuthentication(clientId, AuthorityUtils.NO_AUTHORITIES);
    }
}
