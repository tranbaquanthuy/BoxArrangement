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
	public ContainerBox(int i, int j, int k, int l, int m, int o) {
		super.x = i;
		super.y = j;
		super.height =  k;
		super.width = l;
		this.rowpos = m;
		this.colpos = o;
	}
	private int rowpos ;
	private int colpos ;
	private int nrowpos ;
	private int ncolpos ;
	private int n ;
	private int numberpickup;
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
	public int getNrowpos() {
		return nrowpos;
	}
	public void setNrowpos(int nrowpos) {
		this.nrowpos = nrowpos;
	}
	public int getNcolpos() {
		return ncolpos;
	}
	public void setNcolpos(int ncolpos) {
		this.ncolpos = ncolpos;
	}
	@Override
	public int compareTo(ContainerBox other) {
	        //multiplied to -1 as the author need descending sort order
	        return   Integer.valueOf(this.n).compareTo(other.n);
	    }
	public int getNumberpickup() {
		return numberpickup;
	}
	public void setNumberpickup(int numberpickup) {
		this.numberpickup = numberpickup;
	}
}