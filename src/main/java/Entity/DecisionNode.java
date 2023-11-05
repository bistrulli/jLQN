package Entity;

import java.util.ArrayList;

public abstract class DecisionNode {
	ArrayList<String> joinList = null;
	ArrayList<String> forkList = null;
	ArrayList<String> orList = null;
	ArrayList<Double> prob = null;

	public DecisionNode() {
		this.joinList = new ArrayList<String>();
		this.forkList = new ArrayList<String>();
		this.orList = new ArrayList<String>();
		this.prob = new ArrayList<Double>();
	}

	public ArrayList<String> getJoinList() {
		return joinList;
	}

	public void setJoinList(ArrayList<String> joinList) {
		this.joinList = joinList;
	}

	public ArrayList<String> getForkList() {
		return forkList;
	}

	public void setForkList(ArrayList<String> forkList) {
		this.forkList = forkList;
	}

	public ArrayList<String> getOrList() {
		return orList;
	}

	public void setOrList(ArrayList<String> orList) {
		this.orList = orList;
	}

	public ArrayList<Double> getProb() {
		return prob;
	}

	public void setProb(ArrayList<Double> prob) {
		this.prob = prob;
	}

	public String getName() {
		String name = this.getClass().getSimpleName();
		for (String act : this.joinList) {
			name += "_" + act;
		}
		return name;
	}

	public String getOriginEvt() {
		String evt = "";
		for (String act : this.getJoinList()) {
			if (act == this.getJoinList().get(0))
				evt += act;
			else
				evt += "&" + act;
		}
		return evt;
	}
}
