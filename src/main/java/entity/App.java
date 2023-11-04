package entity;

import java.util.ArrayList;

public class App {
	ArrayList<Function> functions=null;
	String name=null;
	public App(String name) {
		this.setName(name);
		this.functions=new ArrayList<Function>();
	}
	public ArrayList<Function> getFunctions() {
		return functions;
	}
	public void setFunctions(ArrayList<Function> functions) {
		this.functions = functions;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
