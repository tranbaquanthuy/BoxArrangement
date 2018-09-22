package Entity;
import javax.swing.JComponent;

class ContainerBox extends JComponent {
    public ContainerBox(int x, int y, String n) {
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
    public String n ; 
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
	
	public String getN() {
		return n;
	}
	public void setN(String n) {
		this.n = n;
	}
}