package Boundary;
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

import AlgorithmArrangement.ArrangingAlgorithm;
import Entity.ContainerBox;
import Entity.SortingObject;
public class FormContainerArrangement extends JFrame  implements ActionListener {
   private static final long serialVersionUID = 1L;
   public static final Color BOX_COLOR = Color.BLACK;
   public static final Color CANVAS_BACKGROUND = Color.CYAN;
   private int startxpos = 50; 
   private int startypos = 60;
   private int edgelenght = 60 ; 
   private boolean s= false,sort= false;
   private Task task;
   private JProgressBar progressBar,progressBarpart;
   private JLabel outputTextArea;
   private int  inputdata[][] ;
   private int row = 0,col = 0;
   private int a,b,c,d;
   private int l=0,m=0,k=0;
   private float count = 0;
   private boolean status = false;
   private ContainerBox[][] r;
   private SortingObject[][] nr;
   private DrawCanvas canvas;
   JButton btnStart,btnLoad,btnSort;
   public FormContainerArrangement() {
	  //int rawinputdata[][] =  {{0,6,1,45,30},{22,23,13,19,17},{25,29,67,43,33},{12,11,5,7,4},{10,2,18,14,36}};
	  int rawinputdata[][] =  {{0,6,1,45},{23,13,19,17},{12,5,7,4},{10,2,14,36}};
	  // int  rawinputdata[][] = {{6,7,1,15},{32,9,4,23},{30,12,11,19}};
	  // int  rawinputdata[][] = {{6,7,1,15,2},{40,32,9,4,23},{8,30,12,11,19}};
	  inputdata = rawinputdata;
	  row  = inputdata.length;
	  col = inputdata[inputdata.length-1].length;
	  r = new ContainerBox[row][col];
	  nr = new SortingObject[row][col];
      JPanel bottomPanel = new JPanel(new FlowLayout());
      btnStart = new JButton("Start");
      btnStart.addActionListener(this);
      btnStart.setEnabled(false);
      btnSort = new JButton("Sort");
      btnSort.addActionListener(this);
      btnSort.setEnabled(false);
      btnLoad = new JButton("Load");
      btnLoad.addActionListener(this);
	  progressBar = new JProgressBar(0, 100);
	  progressBar.setValue(0);
	  progressBar.setStringPainted(true);
	  progressBarpart = new JProgressBar(0, 100);
	  progressBarpart.setValue(0);
	  progressBarpart.setStringPainted(true);
	  outputTextArea = new  JLabel("Done");
	  outputTextArea.setVisible(false);
	  bottomPanel.add(btnLoad);
	  bottomPanel.add(btnSort);
	  
	  bottomPanel.add(btnStart);
      bottomPanel.add(progressBar);
      bottomPanel.add(progressBarpart);
      bottomPanel.add(outputTextArea);

      canvas = new DrawCanvas(); 
      canvas.setPreferredSize(new Dimension(1200,800));
      Container cp = getContentPane();
      cp.setLayout(new BorderLayout());
      cp.add(canvas, BorderLayout.CENTER);
      cp.add(bottomPanel, BorderLayout.SOUTH);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
      setTitle("Arranging Container Boxes");
      pack();
      setVisible(true); 
 	   
   }
   @Override
   public void actionPerformed(ActionEvent e) {
	   if(e.getActionCommand() == "Sort") {

		
		   ArrangingAlgorithm  n = new ArrangingAlgorithm();
		   n.printMyList(r);
     	   //n.arrangeMyList(r, nr);
     	 n.arrangeMyListSquare(r, nr);
       	   n.printMyList(nr);
       	btnStart.setEnabled(true);
		}
	   else if (e.getActionCommand() == "Load")
	   {
			  sort = true;
	     	  canvas.repaint();
	     	  btnSort.setEnabled(true);
	   }
	   else if (e.getActionCommand() == "Start")
	   {
		  // task = new Task(); 
    	   sort = true;	
    	   c= r.length-1;
    	  // task.start();
        Timer timer = new Timer(2, new ActionListener() {
		 @Override
       public void actionPerformed(ActionEvent e) {
		   for (int a = 0 ; a < r.length ; a++) {  
			for(int b =  0; b < r.length ; b++) {
		     if (c < 0 && d >= r.length-1)
		          {  
			             outputTextArea.setVisible(true);
		        		 ((Timer)e.getSource()).stop();
		          }
		  else {
		  if(nr[c][d].getN() != 0 ){
		   
		   if(r[a][b].getN() == nr[c][d].getN()){
			   
			  if (l < edgelenght + (edgelenght * a) ) {
	        	 l = l + 1;
	        	 r[a][b].setLocation(r[a][b].x  , r[a][b].y -1); 
	             canvas.repaint();}
      	  else if (m < edgelenght*3 + edgelenght * (r.length-1 - b) + edgelenght * d ) {
	        	 m = m + 1;
	        	 r[a][b].setLocation(r[a][b].x +1 , r[a][b].y ); 
	             canvas.repaint();}
	          else if (k < edgelenght  + edgelenght * c  && m >= edgelenght*3 ){
	        	 k = k + 1;
	        	 r[a][b].setLocation(r[a][b].x  , r[a][b].y +1 ); 
	             canvas.repaint();}    
	          else{
                  count++;
	        	  l = 0;
	        	  m = 0;
	        	  k = 0;
	        	  c--;
	        	  if(c < 0 && d < r.length-1 ){
	      		  c = r.length-1;
	      		  d++;}
	          }
		  }
			}
			else {
				count++;
				  c--;
	        	  if(c < 0 && d < r.length-1 ){
	      		  c = r.length-1;
	      		  d++;}
			      }
			}
			}
	      }
		 }
		 }); 
           timer.start();
	   }
   }

   private class Task extends Thread {    
	      public Task(){
	      }
	      public void run(){ 	
	 	  while(status == false){
	    		float temp  = l + m + k;
	    		float percentresultpart  =  temp/(float)(edgelenght + (edgelenght * a)+ edgelenght*3 + edgelenght * (r.length-1 - b) + edgelenght * d + edgelenght  + edgelenght * c )*100;
	    		float total = r.length*r.length;
	    		float percentresult  = (float)count/total *100;
	    		final int progress = (int) percentresult;
	    		final int progresspart = (int) percentresultpart;
	    		
	            SwingUtilities.invokeLater(new Runnable() {
	               public void run() {
	            	   progressBar.setValue(progress);
	            	   if(progress >= 100)
	   	    		  {
	   	    			progressBarpart.setValue(progress);
	   	    			status = true;		
	   	    		  }
	            	   else {
	            	   
	                  progressBarpart.setValue(progresspart);
	            	   }
	            	   
	                  //outputTextArea.setText(String.format("Completed %d%% of task.\n", progress));
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
	int xpos = startxpos, ypos =  startypos;
	@Override
      public void paintComponent(Graphics g) {
         super.paintComponent(g);
         setBackground(CANVAS_BACKGROUND);
         g.setColor(BOX_COLOR);
         
      if(sort == true) {
        if (r[0][0] == null)
        {
    	  createRec(inputdata);
    	  s = true;
         }
           for (int i = 0; i < inputdata.length; i++) {
    		int[] row = inputdata[i];
			for (int j = 0; j < row.length; j++) { 
				if(r[i][j].getN() != 0)
				{
		         g.drawRect(r[i][j].x, r[i][j].y, edgelenght, edgelenght); 
		         drawCenteredString(g,String.valueOf(row[j]),r[i][j],g.getFont());
		         xpos = xpos + edgelenght;
				}
				else {
			     xpos = xpos + edgelenght;
				}
			}
			xpos = startxpos;
			ypos = startypos + edgelenght;
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
      public  void createRec(int inputdata[][]) {
      int k=0;
      for (int i = 0; i < inputdata.length; i++) {
   		int[] row = inputdata[i];
   		for (int j = 0; j < row.length; j++) { 
   			 r[i][j] = new ContainerBox(xpos, ypos, edgelenght, edgelenght , i , j, row[j]);
   			 nr[i][j] = new SortingObject(i,j,0);
   			 xpos = xpos + edgelenght;
   			 k = k+1;
   		}
   		xpos = startxpos;
		ypos = ypos + edgelenght;
      }
   }
   }
   

   public static void main(String[] args) {
      // Run GUI codes on the Event-Dispatcher Thread for thread safety
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {

            new FormContainerArrangement(); 
         }
      });
   }



}