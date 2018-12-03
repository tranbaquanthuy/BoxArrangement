package Entity;

import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

public class SailBoat extends Applet {
    public int x;
    public int y;
    public int boatW;
    public int boatH;
    private GeneralPath polyline;

	  public SailBoat(int xx, int yy, int w, int h) {
	        setBackground(Color.blue);
	        boatW = w;
	        boatH = h;
	        x = xx;
	        y = yy;
	    }
	  public void draw(Graphics g) {
	        Graphics2D g2d = (Graphics2D) g;

	        g2d.setStroke(new BasicStroke(2));
	        // set stroke size 2

	        // boat base
	        int[] xBoat = { x, x + boatW, x + boatW - 25, x + 25, x };
	        // x pos array
	        int[] yBoat = { y, y, y + 50, y + 50, y };
	        // y pos array
	        polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, xBoat.length);
	        polyline.moveTo(xBoat[0], yBoat[0]);
	        // moves polyline to first position of array x.y
	        for (int index = 1; index < yBoat.length; index++) {
	            // for each pos
	            polyline.lineTo(xBoat[index], yBoat[index]);
	            // draw the lines
	        }
	        ; // ends loop
	        polyline.closePath();
	        // closes poly
	        g2d.draw(polyline);
	        // draws it
	        g2d.setColor(Color.white);
	        // colors the boat base white
	        g2d.fill(polyline);
	        // fills the poly shape

	        
	        // cabin
	        g2d.setColor(Color.black);
	        // change color to black
	        // cabins x position is x + 60% of the boats length on -1 y to
	        // compensate for stroke size
	        int[] xCabin = { (int) (x + (boatW * .6)) - 10,
	                (int) (x + (boatW * .6)),
	                (int) ((x + (boatW * .6)) + (boatW * .15)),
	                (int) ((x + (boatW * .6)) + (boatW * .15)) + 10,
	                (int) (x + boatW * .6) };
	        // cabin y pos
	        int[] yCabin = { y - 1, y - 25, y - 25, y - 1, y - 1 };
	        polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, xCabin.length);// gp
	        polyline.moveTo(xCabin[0], yCabin[0]);
	        // move poly line
	        for (int index = 1; index < yCabin.length; index++) {
	            // for each pos
	            polyline.lineTo(xCabin[index], yCabin[index]);
	            // draws lines
	        }
	        ; // ends loop
	        polyline.closePath();
	        // close path
	        g2d.draw(polyline);
	        // draws the poly to g2d
	        g2d.setColor(Color.WHITE);
	        // sets color white
	        g2d.fill(polyline);
	        // fills cabin

	        // mast
	        g2d.setColor(Color.BLACK);
	        // set color black
	        g2d.setStroke(new BasicStroke(4));
	        // set stroke size for mast to 4
	        g2d.drawLine((int) (x + (boatW * .65)), y - 28,
	                (int) (x + (boatW * .65)), (int) (y - boatH + 50));
	        // draws a line from top of cabin to the remainder of boats height
	        // frontsail
	        g2d.setColor(Color.BLACK);
	        g2d.setStroke(new BasicStroke(2));
	        int[] xBSail = { (int) (x + (boatW * .65) + 5),
	                (int) (x + (boatW * .65) + 5), x + boatW,
	                (int) (x + (boatW * .65) + 5) };
	        int[] yBSail = { (y - boatH + 50), y - 35, y - 35, (y - boatH + 50) };
	        polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, xBSail.length);
	        polyline.moveTo(xBSail[0], yBSail[0]);
	        for (int index = 1; index < yBSail.length; index++) {
	            polyline.lineTo(xBSail[index], yBSail[index]);
	        }
	        ; 
	        polyline.closePath();
	        g2d.draw(polyline);
	        g2d.fill(polyline);
	    }
}
