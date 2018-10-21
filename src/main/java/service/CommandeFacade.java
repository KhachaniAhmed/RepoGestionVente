package service;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Session;

import entity.Article;
import entity.Commande;
import entity.User;
import utile.SessionUtil;

@ManagedBean(name = "commandeFacade")
@SessionScoped
public class CommandeFacade extends AbstractFacade<Commande> implements Serializable {

	static Session sessionObj = buildSessionFactory().openSession();
	private Commande selectedCommande = new Commande();

	private String code_cmd;
	private String nom_client;
	private String qt;
	private String prix;
	private Date date = new Date();
	private String cod;
	private String nomArticle;
	private float montant;
	private int prixint;
	private int qan;

	public int getPrixint() {
		return prixint;
	}

	public void setPrixint(int prixint) {
		this.prixint = prixint;
	}

	public int getQan() {
		return qan;
	}

	public void setQan(int qan) {
		this.qan = qan;
	}

	public float getMontant() {
		return montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public User connectedUser = (User) SessionUtil.getAttribute("connectedUser");

	// private Article aa;

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public User getConnectedUser() {
		return connectedUser;
	}

	public void setConnectedUser(User connectedUser) {
		this.connectedUser = connectedUser;
	}

	public Commande getSelectedCommande() {
		return selectedCommande;

	}

	public void setSelectedCommande(Commande selectedCommande) {
		this.selectedCommande = selectedCommande;
	}

	public String getCode_cmd() {
		return code_cmd;
	}

	public void setCode_cmd(String code_cmd) {
		this.code_cmd = code_cmd;
	}

	public String getNom_client() {
		return nom_client;
	}

	public void setNom_client(String nom_client) {
		this.nom_client = nom_client;
	}

	public String getQt() {
		return qt;
	}

	public void setQt(String qt) {
		this.qt = qt;
	}

	public String getPrix() {
		return prix;
	}

	public void setPrix(String prix) {
		this.prix = prix;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public CommandeFacade() {

		super(Commande.class);

	}

	public Article selectedArticle(int code_article) {
		Article article = new Article();
		List<Article> list = new ArrayList<Article>();
		sessionObj.beginTransaction();
		list = sessionObj.createQuery("SELECT a from Article a where  a.code_Art='" + code_article + "'").list();
		for (int i = 0; i < list.size(); i++) {
			article = list.get(i);
		}
		sessionObj.getTransaction().commit();
		return article;
	}

	public void editCommande(Commande commande) {
		sessionObj = buildSessionFactory().openSession();
		sessionObj.beginTransaction();
		sessionObj.clear();
		sessionObj.evict(commande);
		sessionObj.update(commande);
		sessionObj.flush();
		sessionObj.getTransaction().commit();

		sessionObj.close();
		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("Successful", "modifier avec succes "));

	}

	// Supprimer un Articles
	public void deleteCommande(Commande a) {
		String query = "DELETE Commande  WHERE codecmd='" + a.getCodecmd() + "'";
		sessionObj.beginTransaction();
		sessionObj.createQuery(query).executeUpdate();
		sessionObj.getTransaction().commit();

		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("Successful", "supprimer avec succes "));

	}

	public void deleteCommandesByArticle(Article article) {
		String query = "DELETE Commande  WHERE article.code_Art='" + article.getCode_Art() + "'";
		sessionObj.beginTransaction();
		sessionObj.createQuery(query).executeUpdate();
		sessionObj.getTransaction().commit();

	}

	public void add() {

		int pp = Integer.parseInt(prix);
		int qq = Integer.parseInt(qt);
		int id_art = Integer.parseInt(cod);
		Article aa = new Article();
		aa = this.selectedArticle(id_art);

		Commande A = new Commande(nom_client, qq, pp, date, aa);
		this.saveCommande(A);

	}

	public void saveCommande(Commande a) {
		sessionObj = buildSessionFactory().openSession();
		sessionObj.beginTransaction();
		sessionObj.save(a);
		sessionObj.getTransaction().commit();

		sessionObj.clear();
		sessionObj.close();
		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("Successful", "Ajouter avec succes "));

	}

	public void update(int codee, String name, int quant, Date d, String code_art) {

		int id_art = Integer.parseInt(code_art);
		Article aa = new Article();
		aa = this.selectedArticle(id_art);
		int mont = aa.getPrixArt() * quant;
		System.out.println("voila la date qui sera modifier************************ " + mont);
		Commande valueUpdate = new Commande(codee, name, quant, mont, d, aa);
		this.editCommande(valueUpdate);

	}

	public List<Commande> getAllCommandes() throws ParseException {

		List<Commande> listcommande = new ArrayList<Commande>();
		sessionObj = buildSessionFactory().openSession();
		listcommande = sessionObj.createQuery("from Commande").list();

		return listcommande;
	}

	public void selected(Commande ar) {

		System.out.println("selected" + ar);

		selectedCommande = ar;

	}

	public void print(Commande ar) {
		int cc = ar.getCodecmd();

		nomArticle = this.selectedArticle(cc).getNomArt();
		nom_client = ar.getNomclient();
		prixint = ar.getPrix();
		qan = ar.getQuantite();

		date = ar.getDate();

	}
}
