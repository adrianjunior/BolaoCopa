package br.com.fbd.control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.fbd.jdbc.ConnectionFactory;
import br.com.fbd.model.Bet;

public class BetDAO {
	
	private Connection connection;
	 
    public BetDAO() {
	   this.connection= new ConnectionFactory().getConnection(); 
    }
    
    public void createBetTable() {
		   String sql = "CREATE TABLE aposta ("
		            + "BID INT NOT NULL,"
		            + "UID INT NOT NULL,"
		            + "MID INT NOT NULL,"
		            + "T1GOALS INT NOT NULL,"
		            + "T2GOALS INT NOT NULL,"
		            + "VALUE INT NOT NULL,"
		            + "RATIO DOUBLE PRECISION NOT NULL,"
		            + "PRIMARY KEY (BID),"
		            + "FOREIGN KEY (UID) REFERENCES usuario(UID),"
		            + "FOREIGN KEY (MID) REFERENCES partida(MID))";
		   
		   try {
			   PreparedStatement stmt = connection.prepareStatement(sql);
			   
			   stmt.execute();
			   stmt.close();
			   
		   } catch (SQLException e) {
			   System.out.println(e.getMessage());
		   }
	   }
  
    public void addBet(Bet bet) {
	    String sql = "insert into aposta values (?,?,?,?,?,?,?)";

	    try {
	    	
	        PreparedStatement stmt = connection.prepareStatement(sql);

	        stmt.setInt(1,bet.getBid());
	        stmt.setInt(2,bet.getUid());
	        stmt.setInt(3,bet.getMid());
	        stmt.setInt(4,bet.getT1goals());
	        stmt.setInt(5,bet.getT2goals());
	        stmt.setInt(6,bet.getValue());
	        stmt.setDouble(7,bet.getRatio());
	        
	        stmt.execute();
	        stmt.close();
	        System.out.println("Inseriu com sucesso em aposta!");
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	 }
 
    public void deleteBet(int betId) {
	    String sql = "delete from aposta where bid=?";

	    try {
	    	PreparedStatement stmt = connection.prepareStatement(sql);
	    	
	    	stmt.setInt(1, betId);
	    	
	    	stmt.execute();
	        stmt.close();
	        System.out.println("Deletou com sucesso a partida " + betId + "!");
	    	
	    } catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
    }
 	
    public void updateBet(Bet bet) {
	    String sql = "update aposta set uid=?, mid=?, t1goals=?, t2goals=?, value=?, ratio=? where bid=? ";

	    try {
	    	PreparedStatement stmt = connection.prepareStatement(sql);
	    	
	    	stmt.setInt(1,bet.getUid());
	    	stmt.setInt(2,bet.getMid());
	    	stmt.setInt(3,bet.getT1goals());
	    	stmt.setInt(4,bet.getT2goals());
	    	stmt.setInt(5,bet.getValue());
	    	stmt.setDouble(6,bet.getRatio());
	    	stmt.setInt(7,bet.getBid());

	    	stmt.execute();
	        stmt.close();
	        System.out.println("Atualizou com sucesso a aposta " + bet.getBid() + "!");
	    } catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
    }
 	 
    public void readBet(int betId) {
	    String sql = "select * from aposta where bid=?";

	    try {
	    	PreparedStatement stmt = connection.prepareStatement(sql);
	    
	    	stmt.setInt(1, betId);
	    	try (ResultSet rs = stmt.executeQuery();) {
	    		while(rs.next()) {
	    			System.out.println("ID da Aposta = " + rs.getString(1));
		    		System.out.println("ID do Usuário = " + rs.getString(2));
		    		System.out.println("ID do Partida = " + rs.getString(3));
		    		System.out.println("Placar Apostado do Time 1 = " + rs.getString(4));
		    		System.out.println("Placar Apostado do Time 2 = " + rs.getString(5));
		    		System.out.println("Valor = " + rs.getString(6));
		    		System.out.println("Proporção = " + rs.getString(7));
	    		}
	    	}
	    } catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
    }
    
    public void readCorrectBets(int matchId) {
    	String sql = "select P.t1goals, P.t2goals, T.name, T2.name, U.nome " 
    				 + "from usuario U, aposta A, partida P, time T, time T2 "
    				 + "where P.mid=? and P.mid=A.mid and P.t1goals=A.t1goals and P.t2goals=A.t2goals "
    				 + "and A.uid=U.uid and T.tid=P.t1id and T2.tid=P.t2id";
    	
    	try {
	    	PreparedStatement stmt = connection.prepareStatement(sql);
	    
	    	stmt.setInt(1, matchId);
	    	try (ResultSet rs = stmt.executeQuery();) {
	    		while(rs.next()) {
	    			System.out.println(rs.getString(5) + " => " + rs.getString(3) + " " + rs.getString(1) + " x " + rs.getString(2) + " " + rs.getString(4) );
	    		}
	    	}
	    } catch (SQLException e) {
	    	System.out.println(e.getMessage());
	    }
    }
}
