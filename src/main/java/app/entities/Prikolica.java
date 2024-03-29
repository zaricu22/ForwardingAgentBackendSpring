package app.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the prikolica database table.
 * 
 */
@Entity
@Table(name="prikolica", schema = "forwarding_agent")
@NamedQuery(name="Prikolica.findAll", query="SELECT p FROM Prikolica p")
public class Prikolica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idPrikolica;

	@Column(nullable=false, length=45)
	private String model;
	
	@Column(nullable=false, length=45)
	private String tip;
	
	@Column(nullable=false)
	private int godinaProizvodnje;

	@Column(nullable=false)
	private int nosivost;
	
	@Column(nullable=true)
	private int km; 

	@Column(length=100)
	private String slika;

	//bi-directional many-to-one association to Prevoznik
	@JsonIgnoreProperties(ignoreUnknown=true, value={"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Prevoznik_idPrevoznik", nullable=false)
	private Prevoznik prevoznik;

	//bi-directional many-to-one association to Ugovor
	@JsonIgnore
	@OneToMany(mappedBy="prikolica")
	private List<Ugovor> ugovors;

	//bi-directional many-to-one association to Vozac
	@JsonIgnore
	@OneToOne(mappedBy="prikolica")
	private Vozac vozac;

	public Prikolica() {
	}

	public int getIdPrikolica() {
		return this.idPrikolica;
	}

	public void setIdPrikolica(int idPrikolica) {
		this.idPrikolica = idPrikolica;
	}

	public int getKm() {
		return this.km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getNosivost() {
		return this.nosivost;
	}

	public void setNosivost(int nosivost) {
		this.nosivost = nosivost;
	}

	public String getSlika() {
		return this.slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public String getTip() {
		return this.tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Prevoznik getPrevoznik() {
		return this.prevoznik;
	}

	public void setPrevoznik(Prevoznik prevoznik) {
		this.prevoznik = prevoznik;
	}

	public List<Ugovor> getUgovors() {
		return this.ugovors;
	}

	public void setUgovors(List<Ugovor> ugovors) {
		this.ugovors = ugovors;
	}

	public Ugovor addUgovor(Ugovor ugovor) {
		getUgovors().add(ugovor);
		ugovor.setPrikolica(this);

		return ugovor;
	}

	public Ugovor removeUgovor(Ugovor ugovor) {
		getUgovors().remove(ugovor);
		ugovor.setPrikolica(null);

		return ugovor;
	}

	public Vozac getVozac() {
		return vozac;
	}

	public void setVozac(Vozac vozac) {
		this.vozac = vozac;
	}

	public int getGodinaProizvodnje() {
		return godinaProizvodnje;
	}

	public void setGodinaProizvodnje(int godinaProizvodnje) {
		this.godinaProizvodnje = godinaProizvodnje;
	}

	@Override
	public String toString() {
		return "Prikolica [idPrikolica=" + idPrikolica + ", km=" + km + ", model=" + model + ", godinaProizvodnje="
				+ godinaProizvodnje + ", nosivost=" + nosivost + ", slika=" + slika + ", tip=" + tip + ", prevoznik="
				+ prevoznik + "]";
	}

	

}
