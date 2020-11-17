package Model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PizzaToppingDB {

	private EntityManager em;

	public PizzaToppingDB() {
		EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("Bestellungsdatenbank");
		em = emf.createEntityManager();
	}

	public void createOrderEntry(PizzaToppingDTO DTO)
	{
		em.getTransaction().begin();
		em.persist(DTO);
		em.getTransaction().commit();
	}

	public PizzaToppingDTO getOrderById(int Id)
	{
		return em.find(PizzaToppingDTO.class, Id);
	}

}
