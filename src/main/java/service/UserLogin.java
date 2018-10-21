package service;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.hibernate.Session;

import entity.User;
import utile.SessionUtil;

@ManagedBean(name = "userlogin", eager = true)
@RequestScoped
public class UserLogin extends AbstractFacade<User> {

	private String message;
	private String username;
	private String password;
	static Session sessionObj = buildSessionFactory().openSession();
	private String userCo;
	private boolean loggedIn;

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public UserLogin() {
		super(User.class);
	}
	

	public String login() {
      System.out.println("hhhhhhhhhhhhhhhhhhhhh"+username);
		if (username != null || password != null) {
			User user = new User();
			List<User> list = new ArrayList<User>();
			sessionObj.beginTransaction();
			list = sessionObj.createQuery("SELECT u From User u where u.userName='" + username + "' and password='"+password+"'").list();
			sessionObj.getTransaction().commit();
			for (User user2 : list) {
				user = user2;
			}
			System.out.println("+++++++++++++++++++++++++++++++++++++"+user.toString());

			if (user.getUserName().equalsIgnoreCase(username) && user.getPassword().equalsIgnoreCase(password)) {
				message = "Successfully logged-in.";
				userCo = user.getNom() + " " + user.getPrenom();
				loggedIn = true;
				SessionUtil.setAttribute("connectedUser", user);
				return "index";
			} else if (user.getUserName().equalsIgnoreCase(username) || user.getPassword().equalsIgnoreCase(password)) {
				message = "Wrong credentials.";
				return "login";
			} else {
				return "login";
			}
		} else {
			return "login";
		}

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public String getUserCo() {
		return userCo;
	}

	public void setUserCo(String userCo) {
		this.userCo = userCo;
	}

}
