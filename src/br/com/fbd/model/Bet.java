package br.com.fbd.model;

public class Bet {
	  private int bid;
	  private int uid;
	  private int mid;
	  private int t1goals;
	  private int t2goals;
	  private int value;
	  private double ratio;
	  
	  public Bet(int bid, int uid, int mid, int t1goals, int t2goals, int value, double ratio) {
		  super();
		  this.bid = bid;
		  this.uid = uid;
		  this.mid = mid;
		  this.t1goals = t1goals;
		  this.t2goals = t2goals;
		  this.value = value;
		  this.ratio = ratio;
	  }

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public double getRatio() {
		return ratio;
	}

	public void setRatio(int ratio) {
		this.ratio = ratio;
	}
	  
	  
	  
}
