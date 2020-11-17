package Model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SizesDB {

	private EntityManager em;

	public SizesDB() {
		EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("Bestellungsdatenbank");
		em = emf.createEntityManager();
	}

	public void createOrderEntry(SizesDTO DTO)
	{
		em.getTransaction().begin();
		em.persist(DTO);
		em.getTransaction().commit();
	}

	public SizesDTO getOrderById(int Id)
	{
		return em.find(SizesDTO.class, Id);
	}

}
