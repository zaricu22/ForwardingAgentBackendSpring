package app.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the proizvodjac database table.
 * 
 */
@Entity
@Table(name="proizvodjac", schema = "forwarding_agent")
@NamedQuery(name="Proizvodjac.findAll", query="SELECT p FROM Proizvodjac p")
public class Proizvodjac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idProizvodjac;

	@Column(nullable=false, length=45)
	private String naziv;
	
	@Column(nullable=false, length=45)
	private String sediste;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date datumOsnivanja;

	@Column(nullable=false)
	private int prihod;

	@Column(length=100)
	private String slika;
	
	@Column(length=45)
	private String email;
	
	@Column(length=45)
	private String telefon;

	//bi-directional many-to-one association to Isporuka
	@JsonIgnore
	@OneToMany(mappedBy="proizvodjac")
	private List<Isporuka> isporukas;

	//bi-directional many-to-one association to Teret
	@JsonIgnore
	@OneToMany(mappedBy="proizvodjac")
	private List<Teret> terets;

	//bi-directional many-to-one association to Ugovor
	@JsonIgnore
	@OneToMany(mappedBy="proizvodjac")
	private List<Ugovor> ugovors;

	public Proizvodjac() {
	}

	public int getIdProizvodjac() {
		return this.idProizvodjac;
	}

	public void setIdProizvodjac(int idProizvodjac) {
		this.idProizvodjac = idProizvodjac;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getPrihod() {
		return this.prihod;
	}

	public void setPrihod(int prihod) {
		this.prihod = prihod;
	}

	public String getSediste() {
		return this.sediste;
	}

	public void setSediste(String sediste) {
		this.sediste = sediste;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getSlika() {
		return this.slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public List<Isporuka> getIsporukas() {
		return this.isporukas;
	}

	public void setIsporukas(List<Isporuka> isporukas) {
		this.isporukas = isporukas;
	}

	public Isporuka addIsporuka(Isporuka isporuka) {
		getIsporukas().add(isporuka);
		isporuka.setProizvodjac(this);

		return isporuka;
	}

	public Isporuka removeIsporuka(Isporuka isporuka) {
		getIsporukas().remove(isporuka);
		isporuka.setProizvodjac(null);

		return isporuka;
	}

	public List<Teret> getTerets() {
		return this.terets;
	}

	public void setTerets(List<Teret> terets) {
		this.terets = terets;
	}

	public Teret addTeret(Teret teret) {
		getTerets().add(teret);
		teret.setProizvodjac(this);

		return teret;
	}

	public Teret removeTeret(Teret teret) {
		getTerets().remove(teret);
		teret.setProizvodjac(null);

		return teret;
	}

	public List<Ugovor> getUgovors() {
		return this.ugovors;
	}

	public void setUgovors(List<Ugovor> ugovors) {
		this.ugovors = ugovors;
	}

	public Ugovor addUgovor(Ugovor ugovor) {
		getUgovors().add(ugovor);
		ugovor.setProizvodjac(this);

		return ugovor;
	}

	public Ugovor removeUgovor(Ugovor ugovor) {
		getUgovors().remove(ugovor);
		ugovor.setProizvodjac(null);

		return ugovor;
	}

	public Date getDatumOsnivanja() {
		return datumOsnivanja;
	}

	public void setDatumOsnivanja(Date datumOsnivanja) {
		this.datumOsnivanja = datumOsnivanja;
	}

	@Override
	public String toString() {
		return "Proizvodjac [idProizvodjac=" + idProizvodjac + ", naziv=" + naziv + ", datumOsnivanja=" + datumOsnivanja
				+ ", prihod=" + prihod + ", sediste=" + sediste + ", slika=" + slika + ", email=" + email + ", telefon="
				+ telefon + "]";
	}

	

}
