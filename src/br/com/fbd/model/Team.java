package br.com.fbd.model;

public class Team {
	  private int tid;
	  private String name;

	  public Team(int id, String name) {
		  this.tid = id;
		  this.name = name;
	  }

	public int getTid() {
		return tid;
	}

	public void setTid(int id) {
		this.tid = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	  
	  
}
