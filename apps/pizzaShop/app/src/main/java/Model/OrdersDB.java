package Model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class OrdersDB {
	private EntityManager em;

	public OrdersDB() {
		EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("Bestellungsdatenbank");
		em = emf.createEntityManager();
	}

	public void createOrderEntry(OrdersDTO ordersDTO)
	{
		em.getTransaction().begin();
		em.persist(ordersDTO);
		em.getTransaction().commit();
	}

	public OrdersDTO getOrderById(int Id)
	{
		return em.find(OrdersDTO.class, Id);
	}
}
