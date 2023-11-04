package entity;

public class Call {
	String source=null;
	String dest=null;
	
	public Call(String source, String dest) {
		this.setSource(source);
		this.setDest(dest);
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDest() {
		return this.dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}
}
