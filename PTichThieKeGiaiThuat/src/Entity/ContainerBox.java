package Entity;
import java.awt.Rectangle;

public class ContainerBox extends Rectangle implements Comparable<ContainerBox> {
	private static final long serialVersionUID = 1L;
	public ContainerBox(int i, int j, int k, int l, int m, int o, int p) {
		super.x = i;
		super.y = j;
		super.height =  k;
		super.width = l;
		this.rowpos = m;
		this.colpos = o;
		this.n = p;
	}
	public ContainerBox(int i, int j, int k,boolean _moved) {
		this.rowpos = i;
		this.colpos = j;
		this.n = k;
		this.moved = _moved;
	}
	public ContainerBox(int i, int j, int k, int l, int m, int o) {
		super.x = i;
		super.y = j;
		super.height =  k;
		super.width = l;
		this.rowpos = m;
		this.colpos = o;
	}
	public ContainerBox(int i, int j, int k, int l, int m, int o, int p, boolean n) {
		super.x = i;
		super.y = j;
		super.height =  k;
		super.width = l;
		this.rowpos = m;
		this.colpos = o;
		this.n = p;
		this.moved = n ;
	}
	private int rowpos ;
	private int colpos ;
	private int n ;
	private boolean checked = false ;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int compareTo(ContainerBox o) {
		// TODO Auto-generated method stub
		return   Integer.valueOf(this.n).compareTo(o.n);
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isMoved() {
		return moved;
	}
	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	
}