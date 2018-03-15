
public class Domino {
	private int first;
	private int second;
	
	public Domino(int first, int second) {
		this.first = first;
		this.second = second;
	}
	
	public int first() {
		return this.first;
	}
	
	public int second() {
		return this.second;
	}
	
	public String toString() {
		return "(" + this.first + "|" + this.second + ")";
	}
}
