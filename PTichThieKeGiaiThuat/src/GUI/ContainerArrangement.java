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
@SuppressWarnings("serial")
public class ContainerArrangement extends JFrame {
   public static final int CANVAS_WIDTH = 900;
   public static final int CANVAS_HEIGHT = 800;
   public static final Color BOX_COLOR = Color.BLACK;
   public static final Color CANVAS_BACKGROUND = Color.CYAN;
   private int xpos =50; 
   private int ypos =50;
   private int x2 = 60;
   private int y2 = 60; 
   private int distance= 500;
   private int startxpos,startypos;
   private boolean s= false;
   private Task task;
   private JProgressBar progressBar;
   private  JLabel outputTextArea;
   private DrawCanvas canvas; // The custom drawing canvas (an inner class extends JPanel)
   // Constructor to set up the GUI components and event handlers
   public ContainerArrangement() {
      JPanel btnPanel = new JPanel(new FlowLayout());
      JButton btnStart = new JButton("Start");
	  progressBar = new JProgressBar(0, 100);
	  progressBar.setValue(0);
	  progressBar.setStringPainted(true);
	  outputTextArea = new  JLabel("Completed 0% of task.\n");
      btnPanel.add(btnStart);
      btnPanel.add(progressBar);
      btnPanel.add(outputTextArea);
      btnStart.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
        	 task = new Task();                
	         task.start();
	         startxpos = xpos;
	         startypos = ypos;
      		 s = true;
        	 Timer timer = new Timer(5, new ActionListener() {
        		 int i,j,k = 0;
                 @Override
                 public void actionPerformed(ActionEvent e) {
                	  if (k < 20 && ypos > startypos - 20) {
         	        	 k = k + 1;
         	        	 ypos -= 1;
         	             canvas.repaint();
         	          }
                	  else if (i < distance && xpos < CANVAS_HEIGHT) {
         	        	 i = i + 1;
         	        	 xpos += 1;
         	             canvas.repaint();
         	          }
         	          else if (j < distance && ypos <CANVAS_WIDTH && i >= distance )
        	          {
        	        	 j = j + 1;
        	        	 ypos += 1;
        	             canvas.repaint();
        	          }
         	          else
         	          {
         	        	 ((Timer)e.getSource()).stop();
         	          }
                 }
        	 });
             timer.start();     
        	    }
      });
      // Set up a custom drawing JPanel
      canvas = new DrawCanvas();
      canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
      // Add both panels to this JFrame's content-pane
      Container cp = getContentPane();
      cp.setLayout(new BorderLayout());
      cp.add(canvas, BorderLayout.CENTER);
      cp.add(btnPanel, BorderLayout.SOUTH);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
      setTitle("Arranging Container Boxes");
      pack();           // pack all the components in the JFrame
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
      @Override
      public void paintComponent(Graphics g) {
         super.paintComponent(g);
         setBackground(CANVAS_BACKGROUND);
         g.setColor(BOX_COLOR);
         if (s==true)
         {
         Rectangle r = new Rectangle(xpos, ypos, x2, y2);
         g.drawRect(xpos, ypos, x2, y2); 
         drawCenteredString(g,"2",r,g.getFont());
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