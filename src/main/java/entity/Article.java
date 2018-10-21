package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Article implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int code_Art;
	private String nomArt;
	private String descArt;
	private int prixArt;
	private int quantite;

	public Article() {

	}

	public Article(String nom, String desc, int prix, int quant) {

		this.nomArt = nom;
		this.descArt = desc;
		this.prixArt = prix;
		this.quantite = quant;

	}

	public Article(int code, String nom, String desc, int prix, int quant) {
		this.code_Art = code;
		this.nomArt = nom;
		this.descArt = desc;
		this.prixArt = prix;
		this.quantite = quant;

	}

	public int getCode_Art() {
		return code_Art;
	}

	public void setCode_Art(int code_Art) {
		this.code_Art = code_Art;
	}

	public String getNomArt() {
		return nomArt;
	}

	public void setNomArt(String nomArt) {
		this.nomArt = nomArt;
	}

	public String getDescArt() {
		return descArt;
	}

	public void setDescArt(String descArt) {
		this.descArt = descArt;
	}

	public int getPrixArt() {
		return prixArt;
	}

	public void setPrixArt(int prixArt) {
		this.prixArt = prixArt;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	@Override
	public String toString() {
		return "Article{" + "name=" + nomArt + ", desc=" + descArt + ", quant=" + quantite + '}';
	}

}
