package service;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;

import entity.Article;
import entity.User;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class UserFacade extends AbstractFacade<User> {
	static Session sessionObj = buildSessionFactory().openSession();
	private String nom;
	private String prenom;
	private String username;
	private String password;
	private String rpassword;
	private int size;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRpassword() {
		return rpassword;
	}

	public void setRpassword(String rpassword) {
		this.rpassword = rpassword;
	}

	public UserFacade() {
		super(User.class);
	}
	
	public void singin() {
		this.getUserByUsername(username);
		
		if(size!=0) {
			System.out.println(this.getUserByUsername(username).getUserName());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "userName deja exixst"));
		}
		else 
			{if(password.equals(rpassword)) {
		User u = new User(nom,prenom,username,password);
		this.saveUser(u);
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", "mot de passe pas identique"));
		}
			}
	}
	
public User getUserByUsername(String userN) {
	sessionObj = buildSessionFactory().openSession();
	User uu = new User();
	List<User> list = new ArrayList<User>();
	sessionObj.beginTransaction();
	list = sessionObj.createQuery("SELECT a from User a where  a.userName='" + userN + "'").list();
	for (int i = 0; i < list.size(); i++) {
		uu = list.get(i);
	}
	size = list.size();
	sessionObj.getTransaction().commit();
	sessionObj.clear();
    sessionObj.close();
	return uu;
}
	// methode permet d'ajouter un utilisateur
	public void saveUser(User u) {
		sessionObj = buildSessionFactory().openSession();
		sessionObj.beginTransaction();
		sessionObj.save(u);
		sessionObj.getTransaction().commit();
		
		sessionObj.clear();
        sessionObj.close();
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes", "Compte ajouter avec succes"));
    
	}

	// ajouter plusieur utilisateur

	public void saveUsers(List<User> list) {
		for (User user2 : list) {
			saveUser(user2);
		}
	}


	// Selectionnee tous les utilisateur

	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<User>();
		sessionObj = buildSessionFactory().openSession();
		userList = sessionObj.createQuery("from User").list();
		for (User i : userList) {

			System.out.println(i.getNom());
		}
		return userList;
	}

	// Selectionnee un seule utilisateur

	public User getUserById(long id) {
		sessionObj.beginTransaction();
		User user = (User) sessionObj.load(User.class, id);
		sessionObj.getTransaction().commit();
		return user;
	}

	public User getUserByName(String name) {
		User user = new User();
		List<User> list = new ArrayList<User>();
		sessionObj.beginTransaction();
		list = sessionObj.createQuery("SELECT u from User u where  u.nom='" + name + "'").list();
		for (int i = 0; i < list.size(); i++) {
			user = list.get(i);
		}
		
		sessionObj.getTransaction().commit();
		return user;
	}

	// Modifier User

	public void editUser(User oldUser, User newUser) {
		User user = getUserByName(oldUser.getNom());
		user.setNom(newUser.getNom());
		user.setPrenom(newUser.getPrenom());
		user.setUserName(newUser.getUserName());
		user.setPassword(newUser.getPassword());
		sessionObj.beginTransaction();
		sessionObj.update(user);
		sessionObj.getTransaction().commit();
	}

	// Supprimer Tous les utilisateur
	public boolean deleteAllUsers() {
		boolean deleteTest = false;
		String query = "DELETE  From User";
		sessionObj.beginTransaction();
		Query queryDelete = sessionObj.createQuery(query);
		int test = queryDelete.executeUpdate();
		sessionObj.getTransaction().commit();
		if (test == 1) {
			deleteTest = true;
		} else {
			deleteTest = false;
		}
		return deleteTest;
	}

	// Supprimer un utilisateur
	public boolean deleteUser(User u) {
		boolean deleteTest = false;
		String query = "DELETE User  WHERE username='" + u.getUserName() + "' and nom ='" + u.getNom() + "' ";
		sessionObj.beginTransaction();
		Query queryDelete = sessionObj.createQuery(query);
		int test = queryDelete.executeUpdate();
		sessionObj.getTransaction().commit();
		if (test == 1) {
			deleteTest = true;
		} else {
			deleteTest = false;
		}
		return deleteTest;
	}
}
