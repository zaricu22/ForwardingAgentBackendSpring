package app.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the uloga database table.
 * 
 */
@Entity
@Table(name="uloga")
@NamedQuery(name="Uloga.findAll", query="SELECT u FROM Uloga u")
public class Uloga implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idRole;

	@Column(nullable=false, length=45)
	private String naziv;

	//bi-directional many-to-many association to Korisnik
	@JsonIgnore
	@ManyToMany(mappedBy="ulogas")
	private List<Korisnik> korisniks;

	public Uloga() {
	}

	public int getIdRole() {
		return this.idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Korisnik> getKorisniks() {
		return this.korisniks;
	}

	public void setKorisniks(List<Korisnik> korisniks) {
		this.korisniks = korisniks;
	}
	
	public Korisnik addKorisnik(Korisnik korisnik) {
		getKorisniks().add(korisnik);

		return korisnik;
	}

	@Override
	public String toString() {
		return "Uloga [idRole=" + idRole + ", naziv=" + naziv + "]";
	}


}