package br.gov.ma.suinf.email.dto;

public class EmailRequestDTO {
	
	private String smtpHost;
    private int smtpPort;
    private String smtpUsername;
    private String smtpPassword;

    // Dados do e-mail
    private String from;
    private String to;
    private String subject;
    private String bodyHtml;
	public String getSmtpHost() {
		return smtpHost;
	}
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}
	public int getSmtpPort() {
		return smtpPort;
	}
	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}
	public String getSmtpUsername() {
		return smtpUsername;
	}
	public void setSmtpUsername(String smtpUsername) {
		this.smtpUsername = smtpUsername;
	}
	public String getSmtpPassword() {
		return smtpPassword;
	}
	public void setSmtpPassword(String smtpPassword) {
		this.smtpPassword = smtpPassword;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBodyHtml() {
		return bodyHtml;
	}
	public void setBodyHtml(String bodyHtml) {
		this.bodyHtml = bodyHtml;
	}


    
}
