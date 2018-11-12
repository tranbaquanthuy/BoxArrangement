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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import AlgorithmArrangement.ArrangingAlgorithm;
import Entity.ContainerBox;
import Entity.InputReader;
import Entity.OutputWriter;
import Entity.SailBoat;
import Entity.SortingObject;
public class FormContainerArrangement extends JFrame  implements ActionListener {
   private static final long serialVersionUID = 1L;
   public static final Color BOX_COLOR = Color.BLACK;
   public static final Color CANVAS_BACKGROUND = Color.CYAN;
   private int startxpos = 50; 
   private int startypos = 240;
   private int edgelenght = 60 ; 
   
   public static int columnyard = 0;
   public static List<Queue> endstack = new ArrayList<Queue>();
   //Read and Write output 
   private String filePath;
   private String fileName;
   private String parentPath;
   private String contentwriter = "";
   
   
   private boolean sort= false;
   private Task task;
   private JProgressBar progressBar,progressBarpart;
   private JLabel outputTextArea;
   private int  inputdata[][] ;
   private int row = 0,col = 0,heightyard =0;
   private int a,b,c,d;
   private int l=0,m=0,k=0;
   private int g = 0;
   private float count = 0;
   private boolean status = false;
   private SailBoat boat ;
   private List<SortingObject> 	orderList =  new ArrayList<SortingObject>();
   private ContainerBox[][] r;
   private SortingObject[][] nr;
   private DrawCanvas canvas;
   private float totalstep=0;
   JButton btnStart,btnLoad,btnSort,btnSort2;
   public FormContainerArrangement() {
	// int rawinputdata[][] =  {{0,6,1,45,30},{22,23,15,19,17},{25,29,67,43,33},{12,11,5,7,4}};
	 // int rawinputdata[][] =  {{0,6,1,45},{23,13,19,17},{12,5,7,4},{10,2,14,36}};
	// int  rawinputdata[][] = {{6,7,1,15},{32,9,4,23},{30,12,11,19}};
	   int  rawinputdata[][] = {{6,7,1,15,2},{40,32,9,4,23},{8,30,12,11,19}};
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
      btnSort2 = new JButton("Sort 2");
      btnSort2.addActionListener(this);
      btnSort2.setEnabled(false);
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
	   JButton btnbrowse= new JButton("Browse");
	      btnbrowse.addActionListener(this);
	      bottomPanel.add(btnbrowse);
	  bottomPanel.add(btnLoad);
	  bottomPanel.add(btnSort);
	  bottomPanel.add(btnSort2);
	  bottomPanel.add(btnStart);
      bottomPanel.add(progressBar);
      bottomPanel.add(progressBarpart);
      bottomPanel.add(outputTextArea);
   
      canvas = new DrawCanvas(); 
      canvas.setPreferredSize(new Dimension(1600,800));
      Container cp = getContentPane();
      cp.setLayout(new BorderLayout());
      cp.add(canvas, BorderLayout.CENTER);
      cp.add(bottomPanel, BorderLayout.SOUTH);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
      setTitle("Container Arrangement");
      pack();
      setVisible(true); 
 	   
   }
   public  void createRecDimension(int inputdata[][]) {
	      int k=0;
	      for (int i = 0; i < inputdata.length; i++) {
	   		int[] row = inputdata[i];
	   		for (int j = 0; j < row.length; j++) {
	   			//  if(row[j] == 0)
	   			//  {
	   			//	r[i][j] = new ContainerBox(xpos, ypos, edgelenght, edgelenght , i , j, row[j]);
	   			//  }
	   			  r[i][j] = new ContainerBox(0, 0, edgelenght, edgelenght , i , j, row[j]);
	   			//  nr[i][j] = new SortingObject(i,j,0);
	   			  
	   		}
	      }
   }
   @Override
   public void actionPerformed(ActionEvent e) {
	   if(e.getActionCommand() == "Sort") {
            InputReader n2 = new InputReader();
            InputReader r2 = new InputReader();
            try {
				contentwriter = r2.getAllContent(filePath);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		   ArrangingAlgorithm  n = new ArrangingAlgorithm();
		   long startTime = System.nanoTime();	 
		   if(r.length == r[r.length-1].length)
		   {
			   n.arrangeMyListSquare(r, nr);
		   }
		   else {
     	   n.arrangeMyList2(r,orderList,heightyard);
		   }
		   long endTime = System.nanoTime();
		   long duration = (endTime - startTime); 
       	   totalstep = orderList.size();
       	   OutputWriter ow = new OutputWriter();
       	   
       	    //building content
       	   contentwriter = contentwriter + "\n";
       	   contentwriter = contentwriter + "//OUTPUT\n";
       	   contentwriter = contentwriter + (int)totalstep + "//Total Cost of moving\n";
       	   contentwriter = contentwriter + "0//Cost of moving to Temporary Yard\n";
       	   DecimalFormat df = new DecimalFormat("0.00000000000");
           contentwriter = contentwriter + df.format((float)duration/(1000000000)) +"//Time consumed by solving\n";
           contentwriter = contentwriter + "//Containers Position in Main Yard\n";
           String linercolumns ="\t";
          // System.out.println(columnyard);
           for(int i = 1 ; i <= columnyard ; i++)
           {
        	   linercolumns = linercolumns + "c" + String.valueOf(i) + "\t";
        	  
           }
           
           contentwriter =  contentwriter + linercolumns;
           contentwriter = contentwriter + "\n";
           int nr2[][] = new int[columnyard][heightyard];
           for(int i = endstack.size() - 1; i >= 0 ; i --)
           {
            int  j = 0;
           	while(j < heightyard )
              {
           		if(endstack.get(i).size() > 0)
           		{
           			
           	    nr2[i][j] = ((ContainerBox) endstack.get(i).poll()).getN();
           		}
           		else 
           		{
           			nr2[i][j] =  0;
           		}
           	     j++;
              }
           }
           String arrayfield="";
           for(int i =  nr2[0].length-1 ; i >= 0 ; i --)
           {
        	   arrayfield = arrayfield + "r" + (nr2[0].length-i);
        	   arrayfield = arrayfield + "\t";
        	   for (int j = 0  ; j <= nr2.length- 1  ;j++)
        	   {
        		  
        		  arrayfield = arrayfield + nr2[j][i];
        		  arrayfield = arrayfield + "\t";
        	   }
        	   arrayfield = arrayfield+"\n";
           }
         //  n.printMyList(nr2);;
         //  System.out.println(endstack.size());
         // System.out.println(((ContainerBox) endstack.get(0).poll()).getN());
           contentwriter = contentwriter + arrayfield;
           contentwriter = contentwriter + "//Containers Temporary Yard\n";
           contentwriter = contentwriter + "\n";
           contentwriter = contentwriter + "//Schedule of Moving\n";
       	   try {
       		   fileName  = fileName.split(".txt")[0];
			 ow.CreateWriter(parentPath+"/"+fileName+"_HASolved.txt");
			for(int i = 0; i < orderList.size() ; i++)
			{
				contentwriter = contentwriter + "Time " + i + ": "+ orderList.get(i).getN() + "-> "  + (int)orderList.get(i).getColpos() + "\n";
			}
			ow.WriteWriter(contentwriter);
			ow.CloseWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
       	  btnStart.setEnabled(true);
		}
	   else  if(e.getActionCommand() == "Sort 2") {

		    nr = new SortingObject[row][col];
		    for (int i = 0; i < inputdata.length; i++) {
		   		int[] row = inputdata[i];
		   		for (int j = 0; j < row.length; j++) {
		   		 nr[i][j] = new SortingObject(i,j,0);
		   		}
		   		}
		   ArrangingAlgorithm  n = new ArrangingAlgorithm();
		   if(r.length == r[r.length-1].length)
		   {
			   n.arrangeMyListSquare(r, nr);
		   }
		   else {
     	   n.arrangeMyList(r,nr,orderList);
		   }
       	   totalstep = orderList.size();
       	  btnStart.setEnabled(true);
		}
	   else if (e.getActionCommand() == "Load")
	   {
		 if(col < 7) {
			 sort = true;
	     	 canvas.repaint();
		 }
		 else 
		 {
			 createRecDimension(inputdata); 
		 }
	     	btnSort.setEnabled(true);
	     	btnSort2.setEnabled(true);
	   }
	   else if (e.getActionCommand() == "Browse")
	   {
		   try {
		  selectFile();
		  String[][] inputdata2;
		  InputReader r2  = new InputReader();
		  if(filePath != null) { 
			   inputdata2 = new String[r2.getHeight(filePath)][r2.getColumns(filePath)];
			   inputdata2 = r2.input(filePath, inputdata2);
			  ArrangingAlgorithm  n = new ArrangingAlgorithm();
			  n.exchangeint(inputdata2);
			  inputdata = n.convertint(inputdata2);
			  row  = inputdata.length;
			  col = inputdata[inputdata.length-1].length;
			  r = new ContainerBox[row][col];
			  heightyard  = r2.getheighyard(filePath);
			  
		   } 
		   }
		   catch (IOException e1) {
			e1.printStackTrace();
		}
		  }
		   
	   
	   else if (e.getActionCommand() == "Start")
	   {
		   task = new Task(); 
    	   c= r[r.length-1].length-1;
    	  task.start();
        Timer timer = new Timer(0, new ActionListener() {
		 @Override
       public void actionPerformed(ActionEvent e) {
		if(r.length == r[r.length-1].length) {
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
      	     else if (m < edgelenght*6 + edgelenght * (r.length-1 - b) + edgelenght * d ) {
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
		 else {			 
				  for (int a = 0 ; a < r.length ; a++) {  
					for(int b =  0; b < r[r.length-1].length ; b++) {
						 if(orderList.isEmpty() == false) {
							 if(orderList.get(g).getN() == 0 ){
							    orderList.remove(g);
							  }
						
						 if(orderList.get(g).getN() != 0 ){
							   if(r[a][b].getN() == orderList.get(g).getN()){
								   if(orderList.get(g).isMoved())
								   {
									     if (l < edgelenght + (edgelenght * (orderList.get(g).getRowpos()+1)) ) {
								        	 l = l + 1;
								        	 r[a][b].setLocation(r[a][b].x  , r[a][b].y -1); 	        	 
								             canvas.repaint();}
										 else if (m < edgelenght*7 + edgelenght * (r[r.length-1].length-1 - b) + edgelenght * orderList.get(g).getColpos() ) { //r[r.length-1].length-1
									        	 m = m + 1;
									        	 r[a][b].setLocation(r[a][b].x - 1 , r[a][b].y ); 
									             canvas.repaint();}
										  else if (k < edgelenght  + edgelenght * (orderList.get(g).getRowpos()) && m >= edgelenght*3 ){
									        	 k = k + 1;
									        	 r[a][b].setLocation(r[a][b].x  , r[a][b].y +1 ); 
									             canvas.repaint();}
										  else {
											  count++;
											  l = 0;
								        	  m = 0;
								        	  k = 0;
								        	  r[a][b].setRowpos((int)orderList.get(g).getRowpos());
								        	  r[a][b].setColpos((int)orderList.get(g).getColpos());
										      orderList.remove(g);
										      if(orderList.size() == 0) {
										    		 outputTextArea.setVisible(true);
									        		 ((Timer)e.getSource()).stop();
										      }
										      break;
										  }
								  }
								else  {
									
								  if (l < edgelenght + (edgelenght * a) ) {
						        	 l = l + 1;
						        	 r[a][b].setLocation(r[a][b].x  , r[a][b].y -1); 
						             canvas.repaint();}
								 else if (m < edgelenght*6 + edgelenght * (r[r.length-1].length-1 - b) + edgelenght * orderList.get(g).getColpos() ) { //r[r.length-1].length-1
							        	 m = m + 1;
							        	 r[a][b].setLocation(r[a][b].x +1 , r[a][b].y ); 
							             canvas.repaint();}
							      else if (k < edgelenght  + edgelenght * orderList.get(g).getRowpos()  && m >= edgelenght*3 ){
							        	 k = k + 1;
							        	 r[a][b].setLocation(r[a][b].x  , r[a][b].y +1 ); 
							             canvas.repaint();}
								  else {
									  count++;
									  l = 0;
						        	  m = 0;
						        	  k = 0;
								      orderList.remove(g);
								      if(orderList.size() == 0) {
								    		 outputTextArea.setVisible(true);
							        		 ((Timer)e.getSource()).stop();
								      }
								  }
							   }
							   }
						 }
					}
						 else {
							 outputTextArea.setVisible(true);
			        		 ((Timer)e.getSource()).stop();
						 }
					}
			  }	      
			 }
		 
		 }
		 }); 
           timer.start();
	   }
   }
   public void selectFile() throws IOException {
	   String st;
	   ClassLoader cl = getClass().getClassLoader();
       File f = new File(cl.getResource("./Asset/properties.txt").getFile());//"/properties.txt");
       if(f.exists()) { 
    	   BufferedReader br = new BufferedReader(new FileReader(f)); 
    	   st = br.readLine();
    	   br.close();
    	   JFileChooser chooser = new JFileChooser();
    	   chooser.setCurrentDirectory(new File(st));
    	   if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
               File f2 = chooser.getSelectedFile();
               filePath  = f2.getPath();
               fileName  = f2.getName();
               parentPath  = f2.getParent();
           } else {
              
           }
       }
       else
       {
    	   JFileChooser chooser = new JFileChooser();
    	   if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
               File f2 = chooser.getSelectedFile();
               filePath  = f2.getPath();
               fileName  = f2.getName();
           } else {
              
           }
    	   
       }
   }
   private class Task extends Thread {    
	      public Task(){
	      }
	      public void run(){ 	
	 	  while(status == false){
	    		float temp  = l + m + k;
	    	//	float percentresultpart  =    temp/(float)(  edgelenght *( orderList.get(g).getRowpos() +a ) + edgelenght 
	    		//		* ( r.length - 1 -orderList.get(g).getColpos())  + edgelenght * (c+3)  + edgelenght * (r.length-1))*100;
	    		float percentresult  = (float)count/totalstep *100;
	    		final int progress = (int) percentresult;
	    	//	final int progresspart = (int) percentresultpart;
	            SwingUtilities.invokeLater(new Runnable() {
	               public void run() {
	            	   progressBar.setValue(progress);
	            	   if(progress >= 100)
	   	    		   {
	   	    	//		progressBarpart.setValue(progress);
	   	    			status = true;		
	   	    		   }
	            	   else {	   
	               //   progressBarpart.setValue(progresspart);
	            	   }
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
       
      if(sort == true) {
        if (r[0][0] == null)
         {
    	  createRec(inputdata);
         }
        g.setColor(BOX_COLOR);
       // g.drawLine(550, 290, 850, 290);
        
        boat = new SailBoat(25,startypos*inputdata.length/4 + 240, inputdata.length*edgelenght + 400, 400);
        g.drawRect(25 + inputdata.length*60+ 400 + 400, startypos*inputdata.length/4 + 240,inputdata.length * edgelenght, 50);
        boat.draw(g);
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
   			//  if(row[j] == 0)
   			//  {
   			//	r[i][j] = new ContainerBox(xpos, ypos, edgelenght, edgelenght , i , j, row[j]);
   			//  }
   			  r[i][j] = new ContainerBox(xpos, ypos, edgelenght, edgelenght , i , j, row[j]);
   			//  nr[i][j] = new SortingObject(i,j,0);
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