package Model;

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

	public void createOrderEntry(PizzasDTO DTO)
	{

		try (PreparedStatement statement=connection.prepareStatement("insert into pizzas (id, size_id) VALUES (?,?)")){
			statement.setInt(1,DTO.getID());
			statement.setInt(2,DTO.getSizeId());
		}catch(SQLException e)
		{
			System.out.println("Unable to execute Satement:"+e.getCause());
		}
	}

	public PizzasDTO getPizzaById(int Id)
	{

		try {
			Statement stmt = connection.createStatement();

			String Querry = "select * from pizzas where pizzaID equals "+Id;

			ResultSet rs = stmt.executeQuery(Querry);

			return new PizzasDTO(rs.getInt(1),
				rs.getInt(2));
		}
		catch(SQLException e)
		{
			System.out.println("Unable to execute Satement:"+e.getCause());
			return null;
		}
	}

	public PizzasDTO getPizzaBySizeId(int Id)
	{

		try {
			Statement stmt = connection.createStatement();

			String Querry = "select * from pizzas where size_id equals "+Id;

			ResultSet rs = stmt.executeQuery(Querry);

			return new PizzasDTO(rs.getInt(1),
				rs.getInt(2));
		}
		catch(SQLException e)
		{
			System.out.println("Unable to execute Satement:"+e.getCause());
			return null;
		}
	}
}
