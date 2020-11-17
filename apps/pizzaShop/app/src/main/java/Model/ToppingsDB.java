package Model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ToppingsDB {
	private EntityManager em;

	public ToppingsDB() {
		EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("Bestellungsdatenbank");
		em = emf.createEntityManager();
	}

	public void createOrderEntry(ToppingsDTO DTO)
	{
		em.getTransaction().begin();
		em.persist(DTO);
		em.getTransaction().commit();
	}

	public ToppingsDTO getOrderById(int Id)
	{
		return em.find(ToppingsDTO.class, Id);
	}

}
