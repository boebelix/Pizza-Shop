package Model;

import ateam.model.entity.Pizzas;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class PizzasDB {
	//private EntityManager em;

	Connection connection;

	public PizzasDB(Connection con) {
		/*EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("Bestellungsdatenbank");
		em = emf.createEntityManager();*/
		connection=con;
	}

	public int createPizzaEntry(Pizzas DTO) throws SQLException {

		PreparedStatement statement=connection.prepareStatement("insert into pizzas ( order_id,size_id) VALUES (?,?)");
		statement.setInt(1,DTO.getOrderId());
		statement.setInt(2,DTO.getSizeId());
		statement.executeUpdate();

		return statement.getGeneratedKeys().getInt(1);
	}

	public List<Pizzas> getPizzaByOrderId(int orderId) throws SQLException {
		Statement stmt = connection.createStatement();

		String Querry = "select * from pizzas where order_id equals "+orderId;

		ResultSet rs = stmt.executeQuery(Querry);

		List<Pizzas> pizzen=new LinkedList<>();

		while(rs.next())
		{
			pizzen.add(new Pizzas(rs.getInt("id"),
				rs.getInt("size_id"),
				rs.getInt("order_id")));
		}

		return pizzen;
	}

	public Pizzas getPizzaBySizeId(int Id) throws SQLException {

		Statement stmt = connection.createStatement();

		String Querry = "select * from pizzas where size_id equals "+Id;

		ResultSet rs = stmt.executeQuery(Querry);

		return new Pizzas(rs.getInt("id"),
			rs.getInt("size_id"),
			rs.getInt("order_id"));
	}
}
