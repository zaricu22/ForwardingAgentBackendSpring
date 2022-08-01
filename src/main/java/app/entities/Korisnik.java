package app.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the korisnik database table.
 * 
 */
@Entity
@Table(name="korisnik")
@NamedQuery(name="Korisnik.findAll", query="SELECT k FROM Korisnik k")
public class Korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false, length=45)
	private String username;

	@Column(nullable=false)
	private int idPreduzeca;

	@Column(nullable=false, length=45)
	private String password;

	//bi-directional many-to-many association to Uloga
	@JsonIgnore
	@ManyToMany
	@JoinTable(
		name="korisnik_uloga"
		, joinColumns={
			@JoinColumn(name="User_username", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="Role_idRole", nullable=false)
			}
		)
	private List<Uloga> ulogas;

	public Korisnik() {
		ulogas = new ArrayList<Uloga>();
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getIdPreduzeca() {
		return this.idPreduzeca;
	}

	public void setIdPreduzeca(int idPreduzeca) {
		this.idPreduzeca = idPreduzeca;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Uloga> getUlogas() {
		return this.ulogas;
	}

	public void setUlogas(List<Uloga> ulogas) {
		this.ulogas = ulogas;
	}

	public Uloga addUloga(Uloga uloga) {
		getUlogas().add(uloga);

		return uloga;
	}

	@Override
	public String toString() {
		return "Korisnik [username=" + username + ", idPreduzeca=" + idPreduzeca + ", password=" + password + "]";
	}

}