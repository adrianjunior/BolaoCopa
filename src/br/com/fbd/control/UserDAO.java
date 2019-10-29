package br.com.fbd.control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.fbd.jdbc.ConnectionFactory;
import br.com.fbd.model.User;

public class UserDAO {
		// a conexï¿½o com o banco de dados
	   private Connection connection;
	 
	   public UserDAO() {
		   this.connection= new ConnectionFactory().getConnection(); 
	   }
	   
	   public void createUserTable() {
		   String sql = "CREATE TABLE usuario ("
		            + "UID INT NOT NULL,"
		            + "NOME VARCHAR(45) NOT NULL,"
		            + "IDADE INT NOT NULL,"
		            + "PRIMARY KEY (UID))";
		   
		   try {
			   PreparedStatement stmt = connection.prepareStatement(sql);
			   
			   stmt.execute();
			   stmt.close();
			   
		   } catch (SQLException e) {
			   System.out.println(e.getMessage());
		   }
	   }
	   
	   public void addUser(User user) {
		    String sql = "insert into usuario values (?,?,?)";

		    try {
		        // prepared statement para inserï¿½ï¿½o
		        PreparedStatement stmt = connection.prepareStatement(sql);

		        // seta os valores

		        stmt.setInt(1,user.getID());
		        stmt.setString(2,user.getName());
		        stmt.setInt(3,user.getAge());

		        // executa
		        stmt.execute();
		        stmt.close();
		        System.out.println("Inseriu com sucesso em usuario!");
		    } catch (SQLException e) {
		        System.out.println(e.getMessage());
		    }
		}
	   
	   public void deleteUser(int userId) {
		    String sql = "delete from usuario where uid=?";

		    try {
		    	PreparedStatement stmt = connection.prepareStatement(sql);
		    	
		    	stmt.setInt(1, userId);
		    	
		    	stmt.execute();
		        stmt.close();
		        System.out.println("Deletou com sucesso o usuário " + userId + "!");
		    	
		    } catch (SQLException e) {
		    	System.out.println(e.getMessage());
		    }
	   	}
	   	
	   	public void updateUser(User user) {
		    String sql = "update usuario set nome=?, idade=? where uid=? ";

		    try {
		    	PreparedStatement stmt = connection.prepareStatement(sql);
		    	
		    	stmt.setString(1,user.getName());
		    	stmt.setInt(2,user.getAge());
		    	stmt.setInt(3,user.getID());
		    	
		    	stmt.execute();
		        stmt.close();
		        System.out.println("Atualizou com sucesso o usuário " + user.getID() + "!");
		    } catch (SQLException e) {
		    	System.out.println(e.getMessage());
		    }
	   	}
	   	
	   	public void readUser(int userId) {
		    String sql = "select * from usuario where uid=?";

		    try {
		    	PreparedStatement stmt = connection.prepareStatement(sql);
		    
		    	stmt.setInt(1, userId);
		    	try (ResultSet rs = stmt.executeQuery();) {
		    		while(rs.next()) {
		    			System.out.println("ID do Usuário = " + rs.getString(1));
			    		System.out.println("Nome do Usuário = " + rs.getString(2));
			    		System.out.println("Idade do Usuário = " + rs.getString(3));
		    		}
		    	}
		    } catch (SQLException e) {
		    	System.out.println(e.getMessage());
		    }
	   	}
}