package app.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the vozac database table.
 * 
 */
@Entity
@Table(name="vozac")
@NamedQuery(name="Vozac.findAll", query="SELECT v FROM Vozac v")
public class Vozac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idVozac;

	@Column(nullable=false, length=45)
	private String ime;
	
	@Column(nullable=false, length=45)
	private String prezime;
	
	@Column(nullable=true, length=45)
	private String adresa;
	
	@Column(nullable=true, length=45)
	private String telefon;
	
	@Column(nullable=false, length=45)
	private int godineIskustva;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date datumPridruzivanja;
	
	@Column(nullable=false)
	private int satiVoznje;

	@Column(nullable=false)
	private int km;

	@Column(length=100)
	private String slika;

	//bi-directional many-to-one association to Ugovor
	@JsonIgnore
	@OneToMany(mappedBy="vozac")
	private List<Ugovor> ugovors;

	//bi-directional many-to-one association to Kamion
	@JsonIgnoreProperties(ignoreUnknown=true, value={"hibernateLazyInitializer", "handler"})
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Kamion_idKamion")
	private Kamion kamion;

	//bi-directional many-to-one association to Prevoznik
	@JsonIgnoreProperties(ignoreUnknown=true, value={"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Prevoznik_idPrevoznik", nullable=false)
	private Prevoznik prevoznik;

	//bi-directional many-to-one association to Prikolica
	@JsonIgnoreProperties(ignoreUnknown=true, value={"hibernateLazyInitializer", "handler"})
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Prikolica_idPrikolica")
	private Prikolica prikolica;

	public Vozac() {
	}

	public int getIdVozac() {
		return this.idVozac;
	}

	public void setIdVozac(int idVozac) {
		this.idVozac = idVozac;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public int getKm() {
		return this.km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public int getSatiVoznje() {
		return this.satiVoznje;
	}

	public void setSatiVoznje(int satiVoznje) {
		this.satiVoznje = satiVoznje;
	}

	public String getSlika() {
		return this.slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public List<Ugovor> getUgovors() {
		return this.ugovors;
	}

	public void setUgovors(List<Ugovor> ugovors) {
		this.ugovors = ugovors;
	}

	public Ugovor addUgovor(Ugovor ugovor) {
		getUgovors().add(ugovor);
		ugovor.setVozac(this);

		return ugovor;
	}

	public Ugovor removeUgovor(Ugovor ugovor) {
		getUgovors().remove(ugovor);
		ugovor.setVozac(null);

		return ugovor;
	}

	public Kamion getKamion() {
		return this.kamion;
	}

	public void setKamion(Kamion kamion) {
		this.kamion = kamion;
	}

	public Prevoznik getPrevoznik() {
		return this.prevoznik;
	}

	public void setPrevoznik(Prevoznik prevoznik) {
		this.prevoznik = prevoznik;
	}

	public Prikolica getPrikolica() {
		return this.prikolica;
	}

	public void setPrikolica(Prikolica prikolica) {
		this.prikolica = prikolica;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public int getGodineIskustva() {
		return godineIskustva;
	}

	public void setGodineIskustva(int godineIskustva) {
		this.godineIskustva = godineIskustva;
	}

	public Date getDatumPridruzivanja() {
		return datumPridruzivanja;
	}

	public void setDatumPridruzivanja(Date datumPridruzivanja) {
		this.datumPridruzivanja = datumPridruzivanja;
	}

	@Override
	public String toString() {
		return "Vozac [idVozac=" + idVozac + ", ime=" + ime + ", adresa=" + adresa + ", telefon=" + telefon
				+ ", godineIskustva=" + godineIskustva + ", datumPridruzivanja=" + datumPridruzivanja + ", km=" + km
				+ ", prezime=" + prezime + ", satiVoznje=" + satiVoznje + ", slika=" + slika + ", kamion=" + kamion
				+ ", prevoznik=" + prevoznik + ", prikolica=" + prikolica + "]";
	}

	

	
}