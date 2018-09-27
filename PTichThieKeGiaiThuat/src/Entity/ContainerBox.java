package Entity;
import javax.swing.JComponent;

public class ContainerBox extends JComponent {
    public ContainerBox(int x, int y, int n) {
		super();
		this.x = x;
		this.y = y;
		this.n = n;
	}
	private static final long serialVersionUID = 1L;
    private int x;
    private int y;
    public int width = 50;
    public int height = 50;
    public int n ; 
    public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
}