package app.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ugovor database table.
 * 
 */
@Entity
@Table(name="ugovor")
@NamedQuery(name="Ugovor.findAll", query="SELECT u FROM Ugovor u")
public class Ugovor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idUgovor;
	
	//bi-directional many-to-one association to Proizvodjac
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Proizvodjac_idProizvodjac", nullable=false)
	private Proizvodjac proizvodjac;
		
	//bi-directional many-to-one association to Prevoznik
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Prevoznik_idPrevoznik", nullable=false)
	private Prevoznik prevoznik;

	//bi-directional one-to-one association to Isporuka
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Isporuka_idIsporuka", nullable=false)
	private Isporuka isporuka;

	//bi-directional many-to-one association to Vozac
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Vozac_idVozac", nullable=false)
	private Vozac vozac;

	//bi-directional many-to-one association to Kamion
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Kamion_idKamion", nullable=false)
	private Kamion kamion;
	
	//bi-directional many-to-one association to Prikolica
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Prikolica_idPrikolica")
	private Prikolica prikolica;
	

	public Ugovor() {
	}

	public int getIdUgovor() {
		return idUgovor;
	}

	public void setIdUgovor(int idUgovor) {
		this.idUgovor = idUgovor;
	}

	public Isporuka getIsporuka() {
		return this.isporuka;
	}

	public void setIsporuka(Isporuka isporuka) {
		this.isporuka = isporuka;
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

	public Proizvodjac getProizvodjac() {
		return this.proizvodjac;
	}

	public void setProizvodjac(Proizvodjac proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	public Vozac getVozac() {
		return this.vozac;
	}

	public void setVozac(Vozac vozac) {
		this.vozac = vozac;
	}

	@Override
	public String toString() {
		return "Ugovor [id=" + idUgovor + ", isporuka=" + isporuka + ", kamion=" + kamion + ", prevoznik=" + prevoznik
				+ ", prikolica=" + prikolica + ", proizvodjac=" + proizvodjac + ", vozac=" + vozac + "]";
	}

}