package app.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the teret database table.
 * 
 */
@Entity
@Table(name="teret")
@NamedQuery(name="Teret.findAll", query="SELECT t FROM Teret t")
public class Teret implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idTeret;
	
	@Column(nullable=false)
	private int tezina;

	@Column(nullable=false, length=45)
	private String tip;

	@Column(nullable=false, length=45)
	private String opis;

	//bi-directional one-to-one association to Isporuka
	@JsonIgnore
	@OneToOne(mappedBy="teret")
	private Isporuka isporuka;

	//bi-directional many-to-one association to Proizvodjac
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Proizvodjac_idProizvodjac", nullable=false)
	private Proizvodjac proizvodjac;

	public Teret() {
	}

	public int getIdTeret() {
		return this.idTeret;
	}

	public void setIdTeret(int idTeret) {
		this.idTeret = idTeret;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getTezina() {
		return this.tezina;
	}

	public void setTezina(int tezina) {
		this.tezina = tezina;
	}

	public String getTip() {
		return this.tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Isporuka getIsporuka() {
		return isporuka;
	}

	public void setIsporuka(Isporuka isporuka) {
		this.isporuka = isporuka;
	}

	public Proizvodjac getProizvodjac() {
		return this.proizvodjac;
	}

	public void setProizvodjac(Proizvodjac proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	@Override
	public String toString() {
		return "Teret [idTeret=" + idTeret + ", opis=" + opis + ", tezina=" + tezina + ", tip=" + tip + ", proizvodjac="
				+ proizvodjac + "]";
	}

}