package Entity;

import java.net.URI;
import java.util.ArrayList;

public class Function {
	String name = null;
	URI Url = null;
	Integer Concurrency = null;
	String CPU = null;
	String MEM = null;

	ArrayList<Activity> activities = null;
	ArrayList<DecisionNode> dnodes = null;
	String mainAct = null;

	public Function(String name) {
		this.setName(name);
		this.dnodes = new ArrayList<DecisionNode>();
		this.activities = new ArrayList<Activity>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public URI getUrl() {
		return Url;
	}

	public void setUrl(URI url) {
		Url = url;
	}

	public Integer getConcurrency() {
		return Concurrency;
	}

	public void setConcurrency(Integer concurrency) {
		Concurrency = concurrency;
	}

	public String getCPU() {
		return CPU;
	}

	public void setCPU(String cPU) {
		CPU = cPU;
	}

	public String getMEM() {
		return MEM;
	}

	public void setMEM(String mEM) {
		MEM = mEM;
	}

	public ArrayList<DecisionNode> getDnodes() {
		return dnodes;
	}

	public void setDnodes(ArrayList<DecisionNode> dnodes) {
		this.dnodes = dnodes;
	}

	public ArrayList<Activity> getActivities() {
		return activities;
	}

	public void setActivities(ArrayList<Activity> activities) {
		this.activities = activities;
	}

	public Activity getActivityByName(String name) throws Exception {
		Activity tgt = null;
		for (Activity act : activities) {
			if (act.getName().equals(name)) {
				tgt = act;
				break;
			}
		}
		if (tgt == null) {
			throw new Exception(String.format("Acticity %s not found for funtion %s", name, this.getName()));
		}
		return tgt;
	}

	public String getMainAct() {
		return mainAct;
	}

	public void setMainAct(String mainAct) {
		this.mainAct = mainAct;
	}
}
