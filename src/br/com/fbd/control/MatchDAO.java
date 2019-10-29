package br.com.fbd.control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.fbd.jdbc.ConnectionFactory;
import br.com.fbd.model.Match;

public class MatchDAO {

	 private Connection connection;
	 
     public MatchDAO() {
	   this.connection= new ConnectionFactory().getConnection(); 
     }
     
     public void createMatchTable() {
		   String sql = "CREATE TABLE partida ("
		            + "MID INT NOT NULL,"
		            + "T1ID INT NOT NULL,"
		            + "T2ID INT NOT NULL,"
		            + "T1GOALS INT NOT NULL,"
		            + "T2GOALS INT NOT NULL,"
		            + "PRIMARY KEY (MID),"
		            + "FOREIGN KEY (T1ID) REFERENCES time(TID),"
		            + "FOREIGN KEY (T2ID) REFERENCES time(TID))";
		   
		   try {
			   PreparedStatement stmt = connection.prepareStatement(sql);
			   
			   
			  //Argumentos
			   
			   stmt.execute();
			   stmt.close();
			   
		   } catch (SQLException e) {
			   System.out.println(e.getMessage());
		   }
	   }
     
     public void addMatch(Match match) {
 	    String sql = "insert into partida values (?,?,?,?,?)";

 	    try {
 	    	
 	        PreparedStatement stmt = connection.prepareStatement(sql);

 	        stmt.setInt(1,match.getMid());
 	        stmt.setInt(2,match.getT1id());
 	        stmt.setInt(3,match.getT2id());
	        stmt.setInt(4,match.getT1goals());
	        stmt.setInt(5,match.getT2goals());
	        
 	        stmt.execute();
 	        stmt.close();
 	        System.out.println("Inseriu com sucesso em partida!");
 	    } catch (SQLException e) {
 	        System.out.println(e.getMessage());
 	    }
 	 }
    
     public void deleteMatch(int matchId) {
 	    String sql = "delete from partida where mid=?";

 	    try {
 	    	PreparedStatement stmt = connection.prepareStatement(sql);
 	    	
 	    	stmt.setInt(1, matchId);
 	    	
 	    	stmt.execute();
 	        stmt.close();
 	        System.out.println("Deletou com sucesso a partida " + matchId + "!");
 	    	
 	    } catch (SQLException e) {
 	    	System.out.println(e.getMessage());
 	    }
     }
    	
     public void updateMatch(Match match) {
 	    String sql = "update partida set t1id=?, t2id=?, t1goals=?, t2goals=? where mid=? ";

 	    try {
 	    	PreparedStatement stmt = connection.prepareStatement(sql);
 	    	
 	    	stmt.setInt(1,match.getT1id());
 	    	stmt.setInt(2,match.getT2id());
 	    	stmt.setInt(3,match.getT1goals());
 	    	stmt.setInt(4,match.getT2goals());
 	    	stmt.setInt(5,match.getMid());

 	    	stmt.execute();
 	        stmt.close();
 	        System.out.println("Atualizou com sucesso a partida " + match.getMid() + "!");
 	    } catch (SQLException e) {
 	    	System.out.println(e.getMessage());
 	    }
     }
    	 
     public void readMatch(int matchId) {
 	    String sql = "select * from partida where mid=?";

 	    try {
 	    	PreparedStatement stmt = connection.prepareStatement(sql);
 	    
 	    	stmt.setInt(1, matchId);
 	    	try (ResultSet rs = stmt.executeQuery();) {
 	    		while(rs.next()) {
 	    			System.out.println("ID da Partida = " + rs.getString(1));
 		    		System.out.println("ID do Time 1 = " + rs.getString(2));
 		    		System.out.println("ID do Time 2 = " + rs.getString(3));
 		    		System.out.println("Placar do Time 1 = " + rs.getString(4));
 		    		System.out.println("Placar do Time 2 = " + rs.getString(5));
 	    		}
 	    	}
 	    } catch (SQLException e) {
 	    	System.out.println(e.getMessage());
 	    }
     }
     
     public void readTeamMatches(int teamId) {
    	 String sql = "select P.t1goals, P.t2goals, T.name, T2.name from partida P, time T, time T2 where T.tid=? and "
    	 		+ "((P.t1id=T.tid and P.t2id=T2.tid) or (P.t1id=T2.tid and P.t2id=T.tid))";
    	 
    	 try {
  	    	PreparedStatement stmt = connection.prepareStatement(sql);
  	    
  	    	stmt.setInt(1,teamId);
  	    	try (ResultSet rs = stmt.executeQuery();) {
  	    		while(rs.next()) {
  		    		System.out.println(rs.getString(3) + " " + rs.getString(1) + " x " + rs.getString(2) + " " + rs.getString(4) );
  	    		}
  	    	}
  	    } catch (SQLException e) {
  	    	System.out.println(e.getMessage());
  	    }
     }
     
}
