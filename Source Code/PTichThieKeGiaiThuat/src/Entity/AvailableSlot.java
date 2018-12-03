package Entity;

public class AvailableSlot {
	   private  int row;
	   private int col;
	   private boolean checked = false;
  public AvailableSlot(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
public int getRow() {
	return row;
}
public void setRow(int row) {
	this.row = row;
}
public int getCol() {
	return col;
}
public void setCol(int col) {
	this.col = col;
}
public boolean isChecked() {
	return checked;
}
public void setChecked(boolean checked) {
	this.checked = checked;
}
}
