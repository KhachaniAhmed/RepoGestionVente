package service;




import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class AbstractFacade<T> {

	static Session sessionObj;
	static SessionFactory sessionFactoryObj;
	private Class<T> entityClass;

	public AbstractFacade(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	protected static SessionFactory buildSessionFactory() {
		Configuration configObj = new Configuration();
		configObj.configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 
		sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
		return sessionFactoryObj;
	}
	//
	//	public void create(T entity ) {
	//		buildSessionFactory().openSession().beginTransaction();
	//		buildSessionFactory().openSession().save(entity);
	//		buildSessionFactory().openSession().getTransaction().commit();
	//		}
	//	public void remove(T entity) {
	//		buildSessionFactory().openSession().beginTransaction();
	//		buildSessionFactory().openSession().delete(entity);
	//		buildSessionFactory().openSession().getTransaction().commit();
	//	}
	//	public void edit(T entity) {
	//		buildSessionFactory().openSession().beginTransaction();
	//		buildSessionFactory().openSession().merge(entity);
	//		buildSessionFactory().openSession().getTransaction().commit();
	//	}

}

