package service;



import org.apache.log4j.Logger;



public class AppMain {

	public final static Logger logger = Logger.getLogger(AppMain.class);

	public static void main(String[] args) {
		
		UserFacade userFacade = new UserFacade();
		
		
		userFacade.getAllUsers();
		
		
	}
}