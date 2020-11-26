package Model;

import ateam.model.entity.Pizzas;

import java.sql.*;


public class PizzasDB {
	//private EntityManager em;

	Connection connection;

	public PizzasDB(Connection con) {
		/*EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("Bestellungsdatenbank");
		em = emf.createEntityManager();*/
		connection=con;
	}

	public int createPizzaEntry(Pizzas DTO)
	{
		try (PreparedStatement statement=connection.prepareStatement("insert into pizzas ( size_id) VALUES (?)")){
			statement.setInt(1,DTO.getSizeId());
			statement.executeUpdate();
			return statement.getGeneratedKeys().getInt(1);

		}catch(SQLException e)
		{
			System.out.println("Unable to execute Satement:"+e.getCause());
		}
		return -1;
	}

	public Pizzas getPizzaById(int Id)
	{

		try {
			Statement stmt = connection.createStatement();

			String Querry = "select * from pizzas where pizzaID equals "+Id;

			ResultSet rs = stmt.executeQuery(Querry);

			return new Pizzas(rs.getInt(1),
				rs.getInt(2));
		}
		catch(SQLException e)
		{
			System.out.println("Unable to execute Satement:"+e.getCause());
			return null;
		}
	}

	public Pizzas getPizzaBySizeId(int Id)
	{

		try {
			Statement stmt = connection.createStatement();

			String Querry = "select * from pizzas where size_id equals "+Id;

			ResultSet rs = stmt.executeQuery(Querry);

			return new Pizzas(rs.getInt(1),
				rs.getInt(2));
		}
		catch(SQLException e)
		{
			System.out.println("Unable to execute Satement:"+e.getCause());
			return null;
		}
	}
}
