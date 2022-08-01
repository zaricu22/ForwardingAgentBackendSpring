package app.controllers;

public class Token {
	String value;
	String username;
	String role;
	Integer idPreduzeca;
	
	public Token() { }
	
	public Token(String value, String username, String role, Integer idPreduzeca) {
		this.value = value;
		this.username = username;
		this.role = role;
		this.idPreduzeca = idPreduzeca;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getIdPreduzeca() {
		return idPreduzeca;
	}
	public void setIdPreduzeca(Integer idPreduzeca) {
		this.idPreduzeca = idPreduzeca;
	}
	
}
