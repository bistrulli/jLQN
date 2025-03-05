package Entity;

//si assume che un activity puo fare una chiamata ad una sola altra entry
public class Activity{
	
	Call call = null;
	AsyncCall asyncCall = null;
	String name = null;
	Double demand = null;
	
	public Activity(String name, Double demand) {
		this.setName(name);
		this.setDemand(demand);
	}

	public Call getCall() {
		return call;
	}

	public void setCall(Call call) {
		this.call = call;
	}

	public AsyncCall getAsyncCall() {
		return asyncCall;
	}

	public void setAsyncCall(AsyncCall asyncCall) {
		this.asyncCall = asyncCall;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getDemand() {
		return demand;
	}

	public void setDemand(Double demand) {
		this.demand = demand;
	}
}
