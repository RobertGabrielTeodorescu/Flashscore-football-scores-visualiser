package repository;
import querries.*;

import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UserRepo {
	
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("flashscore1");
	
	public void insertNewUser(User user) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}

	public void updateUser(User user){
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();
		em.close();
	}

	public void deleteUser(User user){
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.contains(user) ? user : em.merge(user));
		em.getTransaction().commit();
		em.close();
	}

	public User findUserByName(String username) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Query query=em.createQuery(UserQuerries.FIND_BY_USERNAME);
		query.setParameter("user_username",username);
		User user= (User) query.getResultList().get(0);
		em.close();
		return user;
	}

	public User findUserById(String id){
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		Query query=em.createQuery(UserQuerries.FIND_BY_ID);
		query.setParameter("user_id",id);
		User user= (User) query.getResultList().get(0);
		em.close();
		return user;
	}

}
