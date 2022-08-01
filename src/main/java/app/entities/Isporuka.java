package app.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;


/**
 * The persistent class for the isporuka database table.
 * 
 */
@Entity
@Table(name="isporuka")
@NamedQuery(name="Isporuka.findAll", query="SELECT i FROM Isporuka i")
public class Isporuka implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idIsporuka;
	
	@Column(nullable=false, length=45)
	private String statusDostave;
	
	@Column(nullable=false, length=45)
	private String statusPlacanja;

	@Column(nullable=false, length=45)
	private String tip;

	@Column(nullable=false)
	private int cena;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date vremeDolaska;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date vremePolaska;

	@Column(nullable=false, length=45)
	private String mestoDolaska;

	@Column(nullable=false, length=45)
	private String mestoPolaska;
	
	@Column(nullable=false)
	private int km;
	
	@Column(nullable=true)
	private int predjeno;

	//bi-directional many-to-one association to Proizvodjac
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Proizvodjac_idProizvodjac", nullable=false)
	private Proizvodjac proizvodjac;

	//bi-directional one-to-one association to Teret
	@JsonIgnoreProperties(ignoreUnknown=true, value={"hibernateLazyInitializer", "handler"})
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Teret_idTeret", nullable=false)
	private Teret teret;

	//bi-directional one-to-one association to Ugovor
	@JsonIgnore
	@OneToOne(mappedBy="isporuka")
	private Ugovor ugovor;

	public Isporuka() {
	}

	public int getIdIsporuka() {
		return this.idIsporuka;
	}

	public void setIdIsporuka(int idIsporuka) {
		this.idIsporuka = idIsporuka;
	}

	public int getCena() {
		return this.cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public int getKm() {
		return this.km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public int getPredjeno() {
		return predjeno;
	}

	public void setPredjeno(int predjeno) {
		this.predjeno = predjeno;
	}

	public String getMestoDolaska() {
		return this.mestoDolaska;
	}

	public void setMestoDolaska(String mestoDolaska) {
		this.mestoDolaska = mestoDolaska;
	}

	public String getMestoPolaska() {
		return this.mestoPolaska;
	}

	public void setMestoPolaska(String mestoPolaska) {
		this.mestoPolaska = mestoPolaska;
	}

	public String getStatusDostave() {
		return statusDostave;
	}

	public void setStatusDostave(String statusDostave) {
		this.statusDostave = statusDostave;
	}

	public String getStatusPlacanja() {
		return statusPlacanja;
	}

	public void setStatusPlacanja(String statusPlacanja) {
		this.statusPlacanja = statusPlacanja;
	}

	public String getTip() {
		return this.tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Date getVremeDolaska() {
		return this.vremeDolaska;
	}

	public void setVremeDolaska(Date vremeDolaska) {
		this.vremeDolaska = vremeDolaska;
	}

	public Date getVremePolaska() {
		return this.vremePolaska;
	}

	public void setVremePolaska(Date vremePolaska) {
		this.vremePolaska = vremePolaska;
	}

	public Proizvodjac getProizvodjac() {
		return this.proizvodjac;
	}

	public void setProizvodjac(Proizvodjac proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	public Teret getTeret() {
		return this.teret;
	}

	public void setTeret(Teret teret) {
		this.teret = teret;
	}

	public Ugovor getUgovor() {
		return ugovor;
	}

	public void setUgovor(Ugovor ugovor) {
		this.ugovor = ugovor;
	}

	@Override
	public String toString() {
		return "Isporuka [idIsporuka=" + idIsporuka + ", cena=" + cena + ", km=" + km + ", predjeno=" + predjeno
				+ ", mestoDolaska=" + mestoDolaska + ", mestoPolaska=" + mestoPolaska + ", statusDostave="
				+ statusDostave + ", statusPlacanja=" + statusPlacanja + ", tip=" + tip + ", vremeDolaska="
				+ vremeDolaska + ", vremePolaska=" + vremePolaska + ", proizvodjac=" + proizvodjac + ", teret=" + teret
				+ ", ugovor=" + ugovor + "]";
	}

	

}