package main;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.fbd.control.UserDAO;
import br.com.fbd.control.TeamDAO;
import br.com.fbd.control.MatchDAO;
import br.com.fbd.control.BetDAO;
import br.com.fbd.model.User;
import br.com.fbd.model.Team;
import br.com.fbd.model.Match;
import br.com.fbd.model.Bet;

public class Main {
	 
	  public static void main(String[] args)
	    {   
	        int option;
	        boolean end = false;
	        String name;
	        int id, age, t1Id, t2Id, t1goals, t2goals, value, uid, mid;
	        double ratio;
	        Scanner scanner = new Scanner(System.in);
	        User user;
	        UserDAO userDAO = new UserDAO(); 
	        Team team;
	        TeamDAO teamDAO = new TeamDAO();
	        Match match;
	        MatchDAO matchDAO = new MatchDAO();
	        Bet bet;
	        BetDAO betDAO = new BetDAO();
	        
	        userDAO.createUserTable();
	        teamDAO.createTeamTable();
	        matchDAO.createMatchTable();
	        betDAO.createBetTable();
	        
	        while (!end)
	        {
	            System.out.println("0 - Cadastrar time");
	            System.out.println("1 - Cadastrar partida");
	            System.out.println("2 - Cadastrar usuario");
	            System.out.println("3 - Cadastrar aposta");
	            System.out.println("4 - Consultar partidas que um time participou");
	            System.out.println("5 - Consultar usuarios que acertaram uma partida");
	            System.out.println("6 - Sair");
	            System.out.println("Selecione a opcao:");
	            option = scanner.nextInt();
	            switch (option)
	            {
	                case 0:
	                {
	                	System.out.println("Digite o id do time:");
	                    id = scanner.nextInt();
	                    System.out.println("Digite o nome do time:");
	                    name = scanner.next();
	                    team = new Team(id,name);
	                    teamDAO.addTeam(team);
	                   break;
	                }
	                case 1:
	                {
	                	System.out.println("Digite o id da partida:");
	                    id = scanner.nextInt();
	                    System.out.println("Digite o id do time 1:");
	                    t1Id = scanner.nextInt();
	                    System.out.println("Digite o id do time 2:");
	                    t2Id = scanner.nextInt();
	                    System.out.println("Digite o placar do time 1:");
	                    t1goals = scanner.nextInt();
	                    System.out.println("Digite o placar do time 2:");
	                    t2goals = scanner.nextInt();
	                    match = new Match(id, t1Id, t2Id, t1goals, t2goals);
	                    matchDAO.addMatch(match);
	                   break;
	                }
	                case 2:
	                {
	                	System.out.println("Digite o id do usuario:");
	                    id = scanner.nextInt();
	                    System.out.println("Digite a idade do usuario:");
	                    age = scanner.nextInt();
	                    System.out.println("Digite o nome do usuario:");
	                    name = scanner.next();
	                    user = new User(id,name,age);
	                    userDAO.addUser(user);
	                   break;
	                }
	                case 3:
	                {
	                	System.out.println("Digite o id da aposta:");
	                    id = scanner.nextInt();
	                    System.out.println("Digite o id do usuário da aposta:");
	                    uid = scanner.nextInt();
	                    System.out.println("Digite o id da partida da aposta:");
	                    mid = scanner.nextInt();
	                    System.out.println("Digite o placar do time 1:");
	                    t1goals = scanner.nextInt();
	                    System.out.println("Digite o placar do time 2:");
	                    t2goals = scanner.nextInt();
	                    System.out.println("Digite o valor da aposta:");
	                    value = scanner.nextInt();
	                    System.out.println("Digite a proporção da aposta:");
	                    ratio = scanner.nextDouble();
	                    bet = new Bet(id, uid, mid, t1goals, t2goals, value, ratio);
	                    betDAO.addBet(bet);
	                  break;
	                }
	                case 4:
	                {
	                	System.out.println("Digite o id do time:");
	                	id = scanner.nextInt();
	                	matchDAO.readTeamMatches(id);
	                  break;
	                }
	                case 5:
	                {
	                	System.out.println("Digite o id da partida:");
	                	id = scanner.nextInt();
	                	betDAO.readCorrectBets(id);
	                  break;
	                }
	                case 6:
	                {
	                	end=true;
	                  break;
	                }
	            }
	    	}
	 }
}
