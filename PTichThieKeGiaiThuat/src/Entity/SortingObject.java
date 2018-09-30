package Entity;

public class SortingObject  implements Comparable<SortingObject> {
	public SortingObject(int rowpos, int colpos, int n) {
		super();
		this.rowpos = rowpos;
		this.colpos = colpos;
		this.n = n;
	}
	private int rowpos ;
	private int colpos ;
	private int n ;
	public double getRowpos() {
		return rowpos;
	}
	public void setRowpos(int rowpos) {
		this.rowpos = rowpos;
	}
	public double getColpos() {
		return colpos;
	}
	public void setColpos(int colpos) {
		this.colpos = colpos;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	@Override
	public int compareTo(SortingObject other) {
	        //multiplied to -1 as the author need descending sort order
	        return   Integer.valueOf(this.n).compareTo(other.n);
	    }
}
