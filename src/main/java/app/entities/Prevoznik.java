package app.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the prevoznik database table.
 * 
 */
@Entity
@Table(name="prevoznik", schema = "forwarding_agent")
@NamedQuery(name="Prevoznik.findAll", query="SELECT p FROM Prevoznik p")
public class Prevoznik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idPrevoznik;

	@Column(nullable=false, length=45)
	private String naziv;
	
	@Column(nullable=false, length=45)
	private String sediste;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date datumOsnivanja;

	@Column(nullable=false)
	private int prihod;
	
	@Column(nullable=false)
	private int uspesneIsporuke;
	
	@Column(nullable=false)
	private int neuspesneIsporuke;

	@Column(length=100)
	private String slika;
	
	@Column(length=45)
	private String email;
	
	@Column(length=45)
	private String telefon;

	//bi-directional many-to-one association to Kamion
	@JsonIgnore
	@OneToMany(mappedBy="prevoznik")
	private List<Kamion> kamions;

	//bi-directional many-to-one association to Prikolica
	@JsonIgnore
	@OneToMany(mappedBy="prevoznik")
	private List<Prikolica> prikolicas;

	//bi-directional many-to-one association to Ugovor
	@JsonIgnore
	@OneToMany(mappedBy="prevoznik")
	private List<Ugovor> ugovors;

	//bi-directional many-to-one association to Vozac
	@JsonIgnore
	@OneToMany(mappedBy="prevoznik")
	private List<Vozac> vozacs;

	public Prevoznik() {
	}

	public int getIdPrevoznik() {
		return this.idPrevoznik;
	}

	public void setIdPrevoznik(int idPrevoznik) {
		this.idPrevoznik = idPrevoznik;
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

	public List<Kamion> getKamions() {
		return this.kamions;
	}

	public void setKamions(List<Kamion> kamions) {
		this.kamions = kamions;
	}

	public Kamion addKamion(Kamion kamion) {
		getKamions().add(kamion);
		kamion.setPrevoznik(this);

		return kamion;
	}

	public Kamion removeKamion(Kamion kamion) {
		getKamions().remove(kamion);
		kamion.setPrevoznik(null);

		return kamion;
	}

	public List<Prikolica> getPrikolicas() {
		return this.prikolicas;
	}

	public void setPrikolicas(List<Prikolica> prikolicas) {
		this.prikolicas = prikolicas;
	}

	public Prikolica addPrikolica(Prikolica prikolica) {
		getPrikolicas().add(prikolica);
		prikolica.setPrevoznik(this);

		return prikolica;
	}

	public Prikolica removePrikolica(Prikolica prikolica) {
		getPrikolicas().remove(prikolica);
		prikolica.setPrevoznik(null);

		return prikolica;
	}

	public List<Ugovor> getUgovors() {
		return this.ugovors;
	}

	public void setUgovors(List<Ugovor> ugovors) {
		this.ugovors = ugovors;
	}

	public Ugovor addUgovor(Ugovor ugovor) {
		getUgovors().add(ugovor);
		ugovor.setPrevoznik(this);

		return ugovor;
	}

	public Ugovor removeUgovor(Ugovor ugovor) {
		getUgovors().remove(ugovor);
		ugovor.setPrevoznik(null);

		return ugovor;
	}

	public List<Vozac> getVozacs() {
		return this.vozacs;
	}

	public void setVozacs(List<Vozac> vozacs) {
		this.vozacs = vozacs;
	}

	public Vozac addVozac(Vozac vozac) {
		getVozacs().add(vozac);
		vozac.setPrevoznik(this);

		return vozac;
	}

	public Vozac removeVozac(Vozac vozac) {
		getVozacs().remove(vozac);
		vozac.setPrevoznik(null);

		return vozac;
	}

	public Date getDatumOsnivanja() {
		return datumOsnivanja;
	}

	public void setDatumOsnivanja(Date datumOsnivanja) {
		this.datumOsnivanja = datumOsnivanja;
	}

	public int getUspesneIsporuke() {
		return uspesneIsporuke;
	}

	public void setUspesneIsporuke(int uspesneIsporuke) {
		this.uspesneIsporuke = uspesneIsporuke;
	}

	public int getNeuspesneIsporuke() {
		return neuspesneIsporuke;
	}

	public void setNeuspesneIsporuke(int neuspesneIsporuke) {
		this.neuspesneIsporuke = neuspesneIsporuke;
	}

	@Override
	public String toString() {
		return "Prevoznik [idPrevoznik=" + idPrevoznik + ", naziv=" + naziv + ", sediste=" + sediste
				+ ", datumOsnivanja=" + datumOsnivanja + ", prihod=" + prihod + ", uspesneIsporuke=" + uspesneIsporuke
				+ ", neuspesneIsporuke=" + neuspesneIsporuke + ", slika=" + slika + ", email=" + email + ", telefon="
				+ telefon + "]";
	}

	

	

}
