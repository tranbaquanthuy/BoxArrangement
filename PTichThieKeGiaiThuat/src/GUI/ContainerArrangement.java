package GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import AlgorithmArrangement.Test2;
import Entity.ContainerBox;
import Entity.SortingObject;
public class ContainerArrangement extends JFrame {
	private static final long serialVersionUID = 1L;
   public static final int CANVAS_WIDTH = 900;
   public static final int CANVAS_HEIGHT = 800;
   public static final Color BOX_COLOR = Color.BLACK;
   public static final Color CANVAS_BACKGROUND = Color.CYAN;
   private int xpos =50; 
   private int ypos =200;
   private int x2 = 60;
   private int y2 = 60; 
   private int distance= 500;
   private int startxpos,startypos;
   private boolean s= false,sort= false;
   private Task task;
   private JProgressBar progressBar;
   private  JLabel outputTextArea;
   int  inputdata[][] ;
   int row = 0;
   int col = 0;
   int a,b;
   int c,d;
   ContainerBox[][] r;
   SortingObject[][] nr;
   int l = 0,m=0,k=0;
   private DrawCanvas canvas;
   public ContainerArrangement() {
	  int rawinputdata[][] =  {{0,6,15,1},{2,5,7,4},{30,8,21,11},{13,18,14,36}};
	  inputdata = rawinputdata;
	  row  = inputdata.length;
	  col = inputdata[inputdata.length-1].length;
	  r = new ContainerBox[row][col];
	  nr =new SortingObject[row][col];
      JPanel btnPanel = new JPanel(new FlowLayout());
      JButton btnStart = new JButton("Start");
      JButton btnSort = new JButton("Sort");
	  progressBar = new JProgressBar(0, 100);
	  progressBar.setValue(0);
	  progressBar.setStringPainted(true);
	  outputTextArea = new  JLabel("Completed 0% of task.\n");
	  btnPanel.add(btnSort);
	  btnPanel.add(btnStart);
      btnPanel.add(progressBar);
      btnPanel.add(outputTextArea);
      Test2  n = new Test2();

      Timer timer2 = new Timer(2, new ActionListener() {
  		 @Override
           public void actionPerformed(ActionEvent e) {
  		for (int a = 0 ; a < r.length ; a++) {  
  			for(int b =  0; b < r.length ; b++) {
  		  if (c < 0 && d >= r.length-1)
  		          {
  		        		 ((Timer)e.getSource()).stop();
  		          }
  		  else {
  		  if(nr[c][d].getN() != 0 )
  		  {
  		  if(r[a][b].getN() == nr[c][d].getN())
  		  {
  			  if (l < y2 + (y2 * a) ) {
	        	 l = l + 1;
	        	 r[a][b].setLocation(r[a][b].x  , r[a][b].y -1); 
	             canvas.repaint();  
	          }
        	  else if (m < y2*3 + y2 * (r.length - 1 - b) + y2 * d && xpos < CANVAS_HEIGHT) {
	        	 m = m + 1;
	        	r[a][b].setLocation(r[a][b].x +1 , r[a][b].y ); 
	             canvas.repaint();
	          }
	          else if (k < y2  + y2 * c && ypos <CANVAS_WIDTH && m >= y2*3 )
	          {
	        	 k = k + 1;
	        	 r[a][b].setLocation(r[a][b].x  , r[a][b].y +1 ); 
	             canvas.repaint();
	          }    
	          else
	          {
	        	  l  =  0;
	        	  m = 0;
	        	  k = 0;
	        	  c--;
	        	  if(c < 0 && d < r.length-1 )
	      	      {
	      		  c = r.length-1;
	      		  d++;
	      	      }
	          }
  		  }
  			}
  			else {
  				  c--;
	        	  if(c < 0 && d < r.length-1 )
	      	      {
	      		  c = r.length-1;
	      		  d++;
	      	      }
  			}
  			}
  			}
  	  }
  		 }
  		 });
      btnStart.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
     //   	 task = new Task();                
	   //     task.start();
         	 n.arrangeMyListSquare(r, nr);
         	 n.printMyList(nr);
	         startxpos = xpos;
	         startypos = ypos;
      		 sort = true;	
      		 c= r.length-1;
             timer2.start();
         }
      });
      btnSort.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
        	  sort = true;
        	  canvas.repaint();
          }
      });
      canvas = new DrawCanvas();
      canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
      Container cp = getContentPane();
      cp.setLayout(new BorderLayout());
      cp.add(canvas, BorderLayout.CENTER);
      cp.add(btnPanel, BorderLayout.SOUTH);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
      setTitle("Arranging Container Boxes");
      pack();
      setVisible(true); 
   }
   

   private class Task extends Thread {    
	      public Task(){
	      }

	      public void run(){
	    	  int i = 0;
	    	  while(i  < (distance + startxpos)){
	    		float temp  = (xpos-startxpos)+(ypos-startypos)+40;
	    		float percentresult  = temp/((2*distance)+20)*100;
	            final int progress = (int) percentresult;
	            if(i > distance + startxpos)
	            {
	            i = xpos;
	            }
	            else
	            {
	            i= ypos;
	            }
	           SwingUtilities.invokeLater(new Runnable() {
	               public void run() {
	                  progressBar.setValue(progress);
	                  outputTextArea.setText(String.format("Completed %d%% of task.\n", progress));
	               }
	            });
	            try {
	               Thread.sleep(100);
	            } catch (InterruptedException e) {} 
	         } 
	      }
	   }   
   	
   class DrawCanvas extends JPanel {
	private static final long serialVersionUID = 1L;
	@Override
      public void paintComponent(Graphics g) {
         super.paintComponent(g);
         setBackground(CANVAS_BACKGROUND);
         g.setColor(BOX_COLOR);
      if(sort == true) {
        if (r[0][0] == null)
        {
    	  createRec();
    	  s = true;
         }
           for (int i = 0; i < inputdata.length; i++) {
    		int[] row = inputdata[i];
			for (int j = 0; j < row.length; j++) { 
				if(r[i][j].getN() != 0)
				{
		         g.drawRect(r[i][j].x, r[i][j].y, x2, y2); 
		         drawCenteredString(g,String.valueOf(row[j]),r[i][j],g.getFont());
		         xpos = xpos +60;
				}
			}
			xpos = 60;
			ypos = ypos + 60;
		  }
           xpos = 60;
           ypos  = 120;
      }
	}
      public  void createRec() {
      int k=0;
      for (int i = 0; i < inputdata.length; i++) {
   		int[] row = inputdata[i];
   		for (int j = 0; j < row.length; j++) { 
   			 r[i][j] = new ContainerBox(xpos, ypos, x2, y2 , i , j, row[j]);
   			 nr[i][j] = new SortingObject(i,j,row[j]);
   			 xpos = xpos +60;
   			 k = k+1;
   		}
   		xpos = 50;
		ypos = ypos + 60;
      }
   }
   }
   
 
   public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
	    FontMetrics metrics = g.getFontMetrics(font);
	    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
	    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    g.setFont(font);
	    g.drawString(text, x, y);
	}

   public static void main(String[] args) {
      // Run GUI codes on the Event-Dispatcher Thread for thread safety
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            new ContainerArrangement(); 
         }
      });
   }
}