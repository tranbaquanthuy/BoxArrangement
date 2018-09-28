package Entity;
import java.awt.Rectangle;

public class ContainerBox extends Rectangle {
	private static final long serialVersionUID = 1L;
	public ContainerBox(double width, double height, int n) {
		super();
		this.width = width;
		this.height = height;
		this.n = n;
	}
	public ContainerBox(int i, int j, int k, int l, int m, int o, int p) {
		super.height = i;
		super.width = j;
		super.x =  k;
		super.y = l;
		this.width = m;
		this.height = 0;
		this.n = p;
	}
	public double width = 50;
    public double height = 50;
    public int n ;
	public double getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	} 
}