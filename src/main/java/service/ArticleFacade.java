package service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import entity.Article;
import entity.Commande;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ArticleFacade extends AbstractFacade<Article> implements Serializable {

	// Creation objet Session
	static Session sessionObj = buildSessionFactory().openSession();

	// declaration des variables
	private String id;
	private String nom;
	private String desc;
	private String prix;
	private String qt;
	private Map<String, String> countryOptionsMap;

	public Map<String, String> getCountryOptionsMap() {
		return countryOptionsMap;
	}

	public void setCountryOptionsMap(Map<String, String> countryOptionsMap) {
		this.countryOptionsMap = countryOptionsMap;
	}

	// creation object recoit l'element de ligne selectionee
	private Article selectedArticlee = new Article();

	// list pour ajouter l'article
	private List<Article> art = null;

	// constructeur
	public ArticleFacade() {

		super(Article.class);
		art = new ArrayList<Article>();
		art = this.getAllArticles();
		countryOptionsMap = new LinkedHashMap();
		for (Article aa : this.getAllArticles()) {
			countryOptionsMap.put(aa.getNomArt(), "" + aa.getCode_Art());
		}

	}

	// getters et setters
	public Article getSelectedArticlee() {
		return selectedArticlee;
	}

	public void setSelectedArticlee(Article selectedArticlee) {
		this.selectedArticlee = selectedArticlee;
	}

	public List<Article> getArt() {
		return art;
	}

	public void setArt(List<Article> art) {
		this.art = art;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getPrix() {
		return prix;
	}

	public void setPrix(String prix) {
		this.prix = prix;
	}

	public String getQt() {
		return qt;
	}

	public void setQt(String qt) {
		this.qt = qt;
	}

	// **********************les Methodes*******************

	public void selected(Article ar) {

		System.out.println("selected" + ar);

		selectedArticlee = ar;

	}
	// Selectionnee tous les Articles

	public List<Article> getAllArticles() {

		List<Article> articleList = new ArrayList<Article>();
		sessionObj = buildSessionFactory().openSession();
		articleList = sessionObj.createQuery("from Article").list();

		return articleList;
	}

	// modifier Article
	public void update(int cod, String name, String des, int p, int q) {

		Article valueUpdate = new Article(cod, name, des, p, q);
		this.editArticle(valueUpdate);

	}

	// methode permet de enregestrie Un Articles
	public void saveArticle(Article a) {
		sessionObj = buildSessionFactory().openSession();
		sessionObj.beginTransaction();
		sessionObj.save(a);
		sessionObj.getTransaction().commit();

		sessionObj.clear();
		sessionObj.close();
		FacesMessage msg = new FacesMessage("Article ajoute avec success");
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}
	
	public long countCommande() {
		
		List<Commande> commandeList = new ArrayList<Commande>();
		sessionObj = buildSessionFactory().openSession();
		commandeList = sessionObj.createQuery("from Commande").list();
		long row = commandeList.size();

		return row;
	}

	public long countArticle() {
		List<Article> articleList = new ArrayList<Article>();
		sessionObj = buildSessionFactory().openSession();
		articleList = sessionObj.createQuery("from Article").list();
		long row = articleList.size();

		return row;
	}

	public int maxPrix() {
		sessionObj = buildSessionFactory().openSession();
		Integer maxPrix = 0;

		Criteria criteria = sessionObj.createCriteria(Article.class).setProjection(Projections.max("prixArt"));
		maxPrix = (Integer) criteria.uniqueResult();
		System.out.println("Max Prix = " + maxPrix);

		return maxPrix;
	}
	
	public int minPrix() {
		sessionObj = buildSessionFactory().openSession();
		Integer minPrix = 0;

		Criteria criteria = sessionObj.createCriteria(Article.class).setProjection(Projections.min("prixArt"));
		minPrix = (Integer) criteria.uniqueResult();
		System.out.println("Max Prix = " + minPrix);

		return minPrix;
		
	}

	// methode pour ajouter articles
	public void add() {

		if (prix.equals("")) {
			prix = "0";
		}

		if (qt.equals("")) {
			qt = "0";
		}

		int pp = Integer.parseInt(prix);
		int qq = Integer.parseInt(qt);
		Article A = new Article(nom, desc, pp, qq);
		this.saveArticle(A);

	}

	// Selectionnee un seule utilisateur

	public Article getArticleById(int code) {
		Article article = new Article();
		List<Article> list = new ArrayList<Article>();
		sessionObj.beginTransaction();
		list = sessionObj.createQuery("SELECT a from Article a where  a.code_Art='" + code + "'").list();
		for (int i = 0; i < list.size(); i++) {
			article = list.get(i);
		}
		sessionObj.getTransaction().commit();
		return article;

	}

//	public Article test() {
//
//		Article article = new Article();
//		List<Article> list = new ArrayList<Article>();
//		sessionObj.beginTransaction();
//		list = sessionObj.createQuery("SELECT a from Article a where  a.code_Art='" + this.getId() + "'").list();
//		for (int i = 0; i < list.size(); i++) {
//
//			article = list.get(i);
//		}
//		sessionObj.getTransaction().commit();
//		System.out.println("***************** +" + article.getCode_Art());
//		return article;
//	}

	public Article getArticleByName(String name) {
		Article article = new Article();
		List<Article> list = new ArrayList<Article>();
		sessionObj.beginTransaction();
		list = sessionObj.createQuery("SELECT a from Article a where  a.nomArt='" + name + "'").list();
		for (int i = 0; i < list.size(); i++) {
			article = list.get(i);
		}
		sessionObj.getTransaction().commit();
		return article;
	}

	public void editArticle(Article article) {
		sessionObj = buildSessionFactory().openSession();
		sessionObj.beginTransaction();
		sessionObj.clear();
		sessionObj.evict(article);
		sessionObj.update(article);
		sessionObj.flush();
		sessionObj.getTransaction().commit();

		sessionObj.close();
		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("Successful", "modifier avec succes "));

	}

	// Supprimer un Articles
	public void deleteArticle(Article a) {
		CommandeFacade commandeFacade = new CommandeFacade();
		commandeFacade.deleteCommandesByArticle(a);
		String query = "DELETE Article  WHERE code_Art='" + a.getCode_Art() + "'";
		sessionObj.beginTransaction();
		sessionObj.createQuery(query).executeUpdate();
		sessionObj.getTransaction().commit();

		FacesContext context = FacesContext.getCurrentInstance();

		context.addMessage(null, new FacesMessage("Successful", "supprimer avec succes "));

	}

}
