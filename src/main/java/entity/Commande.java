package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Commande implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codecmd;
	private String nomclient;	
	private int quantite;
	private int prix;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@ManyToOne
	@JoinColumn(name="codeArticle")
	private Article article;

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Commande(String nomclient, int quantite, int prix, Date date,Article a) {
		
		
		this.nomclient = nomclient;
		this.quantite = quantite;
		this.prix = prix;
		this.date = date;
		this.article=a; 
	}
	
public Commande(int code,String nomclient, int quantite, int prix, Date date,Article am) {
		
		this.codecmd=code;
		this.nomclient = nomclient;
		this.quantite = quantite;
		this.prix = prix;
		this.date = date;
		this.article =am;
	}
	
	public Commande() {
		
	}


	public int getCodecmd() {
		return codecmd;
	}

	public void setCodecmd(int codecmd) {
		this.codecmd = codecmd;
	}

	public String getNomclient() {
		return nomclient;
	}

	public void setNomclient(String nomclient) {
		this.nomclient = nomclient;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	

}
