package Model;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.Metamodel;
import java.util.Map;

public class PizzaOrderDB {
	private EntityManager em;

	public PizzaOrderDB() {
		EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("Bestellungsdatenbank");
		em = emf.createEntityManager();
	}

	public void createEntry(PizzaOrderDTO DTO)
	{
		em.getTransaction().begin();
		em.persist(DTO);
		em.getTransaction().commit();
	}

	public PizzaOrderDTO getOrderById(int Id)
	{
		return em.find(PizzaOrderDTO.class, Id);
	}
}
