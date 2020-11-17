package Model;

import javax.persistence.*;


public class PizzasDB {
	private EntityManager em;

	public PizzasDB() {
		EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("Bestellungsdatenbank");
		em = emf.createEntityManager();
	}

	public void createOrderEntry(PizzasDTO DTO)
	{
		em.getTransaction().begin();
		em.persist(DTO);
		em.getTransaction().commit();
	}

	public PizzasDTO getOrderById(int Id)
	{
		return em.find(PizzasDTO.class, Id);
	}
}
