package ateam.user.db;

import ateam.user.model.entity.User;

import javax.persistence.*;

public class UserDatabase {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("User");

	public User createUser(User user) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Query usernameQuery = em.createQuery("SELECT count(u) FROM User u WHERE u.username = :userName");
			usernameQuery.setParameter("userName", user.getUsername());
			if(usernameQuery.getFirstResult() > 0) {
				throw new IllegalArgumentException("A user with this username already exists!");
			}
			Query emailQuery = em.createQuery("SELECT count(u) FROM User u WHERE u.email = :email");
			emailQuery.setParameter("email", user.getEmail());
			if(emailQuery.getFirstResult() > 0) {
				throw new IllegalArgumentException("A user with this email already exists!");
			}
			em.persist(user);
			em.getTransaction().commit();
			return user;
		} finally {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		}

	}

	public User loadUser(int userId) {
		EntityManager em = emf.createEntityManager();
		return em.find(User.class, userId);
	}

	public User loadUser(String userName) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :userName", User.class);
		query.setParameter("userName", userName);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
