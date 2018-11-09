package Entity;

public class SortingObject   {
	public SortingObject(int rowpos, int colpos, int n) {
		super();
		this.rowpos = rowpos;
		this.colpos = colpos;
		this.n = n;
	}
	public SortingObject(int i, int j, int k,boolean _moved) {
		super();
		this.rowpos = i;
		this.colpos = j;
		this.n = k;
		this.setMoved(_moved);
	}
	private int rowpos ;
	private int colpos ;
	private int n ;
	private int orirowpos;
	private int oricolpos ;
	private boolean moved = false;
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
	public boolean isMoved() {
		return moved;
	}
	public void setMoved(boolean moved) {
		this.moved = moved;
	}
	public int getOricolpos() {
		return oricolpos;
	}
	public void setOricolpos(int oricolpos) {
		this.oricolpos = oricolpos;
	}
	public int getOrirowpos() {
		return orirowpos;
	}
	public void setOrirowpos(int orirowpos) {
		this.orirowpos = orirowpos;
	}
	
}
