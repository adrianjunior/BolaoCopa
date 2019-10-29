package br.com.fbd.control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.fbd.jdbc.ConnectionFactory;
import br.com.fbd.model.Team;

public class TeamDAO {
	
	private Connection connection;
	 
    public TeamDAO() {
	   this.connection= new ConnectionFactory().getConnection(); 
    }
    
    public void createTeamTable() {
		   String sql = "CREATE TABLE time ("
		            + "TID INT NOT NULL,"
		            + "NAME VARCHAR(45) NOT NULL,"
		            + "PRIMARY KEY (TID))";
		   
		   try {
			   PreparedStatement stmt = connection.prepareStatement(sql);
			   
			   stmt.execute();
			   stmt.close();
			   
		   } catch (SQLException e) {
			   System.out.println(e.getMessage());
		   }
	   }
    
    public void addTeam(Team team) {
	    String sql = "insert into time values (?,?)";

	    try {
	    	
	        PreparedStatement stmt = connection.prepareStatement(sql);

	        stmt.setInt(1,team.getTid());
	        stmt.setString(2,team.getName());

	        stmt.execute();
	        stmt.close();
	        System.out.println("Inseriu com sucesso em time!");
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	}
   
    public void deleteTeam(int teamId) {
	    String sql = "delete from time where tid=?";

	    try {
	    	PreparedStatement stmt = connection.prepareStatement(sql);
	    	
	    	stmt.setInt(1, teamId);
	    	
	    	stmt.execute();
	        stmt.close();
	        System.out.println("Deletou com sucesso o time " + teamId + "!");
	    	
	    } catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
   	 }
   	
   	 public void updateTeam(Team team) {
	    String sql = "update time set name=? where tid=? ";

	    try {
	    	PreparedStatement stmt = connection.prepareStatement(sql);
	    	
	    	stmt.setString(1,team.getName());
	    	stmt.setInt(2,team.getTid());
	    	
	    	stmt.execute();
	        stmt.close();
	        System.out.println("Atualizou com sucesso o time " + team.getName() + "!");
	    } catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
   	 }
   	 
   	 public void readTeam(int teamId) {
	    String sql = "select * from time where tid=?";

	    try {
	    	PreparedStatement stmt = connection.prepareStatement(sql);
	    
	    	stmt.setInt(1, teamId);
	    	try (ResultSet rs = stmt.executeQuery();) {
	    		while(rs.next()) {
	    			System.out.println("ID do Time = " + rs.getString(1));
		    		System.out.println("Nome do Time = " + rs.getString(2));
	    		}
	    	}
	    } catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
   	 }
	   
}
