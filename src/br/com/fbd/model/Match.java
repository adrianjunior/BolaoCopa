package br.com.fbd.model;

public class Match {
	  private int mid;
	  private int t1id;
	  private int t2id;
	  private int t1goals;
	  private int t2goals;

	  public Match(int mid, int t1id, int t2id, int t1goals, int t2goals) {
		  this.mid = mid;
		  this.t1id = t1id;
		  this.t2id = t2id;
		  this.t1goals = t1goals;
		  this.t2goals = t2goals;
	  }

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public int getT1id() {
		return t1id;
	}

	public void setT1id(int t1id) {
		this.t1id = t1id;
	}

	public int getT2id() {
		return t2id;
	}

	public void setT2id(int t2id) {
		this.t2id = t2id;
	}

	public int getT1goals() {
		return t1goals;
	}

	public void setT1goals(int t1goals) {
		this.t1goals = t1goals;
	}

	public int getT2goals() {
		return t2goals;
	}

	public void setT2goals(int t2goals) {
		this.t2goals = t2goals;
	}
	  
	  
}
