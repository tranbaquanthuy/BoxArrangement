package AlgorithmArrangement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import Boundary.FormContainerArrangement;
import Entity.AvailableSlot;
import Entity.ContainerBox;
import Entity.InputReader;
import Entity.SortingObject;
public class ArrangingAlgorithm {
	
	public static void main(String[] args) {
		 List<SortingObject> orderList =  new ArrayList<SortingObject>();;
		  ContainerBox[][] r;
		  int  inputdata[][] ;
		  int row = 0,col = 0,heightyard =0;
		 String  fileName = "C:\\Users\\HP\\Downloads\\Môn học\\Phân tích  và thiết kế giải thuật\\Instance_10-50_3.txt";
		  try {
			  String[][] inputdata2;
			  InputReader r2  = new InputReader();
			  if(fileName != null) { 
				   inputdata2 = new String[r2.getHeight(fileName)][r2.getColumns(fileName)];
				   inputdata2 = r2.input(fileName, inputdata2);
				  ArrangingAlgorithm  n = new ArrangingAlgorithm();
				  n.exchangeint(inputdata2);
				  inputdata = n.convertint(inputdata2);
				  row  = inputdata.length;
				  col = inputdata[inputdata.length-1].length;
				  r = new ContainerBox[row][col];
				  for (int i = 0; i < inputdata.length; i++) {
				   		int[] row1 = inputdata[i];
					for (int j = 0; j < row1.length; j++) {
			   			  r[i][j] = new ContainerBox(0, 0, 20, 20 , i , j, row1[j]);
			   		}
				  }
				  heightyard  = r2.getheighyard(fileName);
				  ArrangingAlgorithm  n1 = new ArrangingAlgorithm();
		     	   n1.arrangeMyList2(r,orderList,heightyard);
			   } 
			   }
			   catch (IOException e1) {
				e1.printStackTrace();
			}
	}

	public static void setElementCheckList(int inputdata[][],int checkList[][]) 
	{
		for (int i = 0; i < inputdata.length; i++) {
    		for (int j = 0; j < inputdata[inputdata.length-1].length; j++) {
				if(inputdata[i][j] != 0)
				{
					checkList[i][j]  = 0;
				}
				else 
				{
					
					checkList[i][j]  = 1;
				}
			}
		}
	}
    


    public void arrangeMyListSquare(ContainerBox[][] r,SortingObject[][] nr , List<SortingObject> orderList)
    {
    	int currentrowpos = 0;
    	int row  = r.length;	
    	List<ContainerBox> tempList = new ArrayList<ContainerBox>();
     	for(int i=0;i < r[row-1].length;i++)
    	{
    		for(int j=0;j < row;j++)
    		{
    		   tempList.add(r[i][j]);
  			   if(tempList.size() == r[row-1].length)
  			   {
  				Collections.sort(tempList);	
  				for(int k = 0 ; k < tempList.size();k++)
  				{
  					nr[k][currentrowpos].setN(tempList.get(k).getN());
  					orderList.add(new SortingObject(i,j,tempList.get(k).getN()));
  				}
  				   currentrowpos +=1; 
      			   tempList.removeAll(tempList);
      			  
  			   }
    		}
    	}
    	
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public void arrangeMyList2(ContainerBox[][] r,List<SortingObject> orderList,int heightyard)
    {
    	int currentstackend = 0;
    	ContainerBox tempBox = null;
    	List<ContainerBox> tempList = new ArrayList<ContainerBox>();
    	List<Stack> liststack = new ArrayList<Stack>();
    	List<Queue> endstack = new ArrayList<Queue>();
    	Queue<ContainerBox> temps = new LinkedList<>();
    	//Stack<ContainerBox> temps = new Stack<>();
    	endstack.add(temps);
        for(int i = 0 ; i < r[0].length  ; i++)
        {
        	Stack<ContainerBox> tempstack = new Stack<ContainerBox>();
        	for(int j = r.length-1 ; j >= 0;j--)
        	{
        		tempstack.push(r[j][i]);
        	}
        	liststack.add(tempstack);
        }
        int pos = -1;
        int countstack = liststack.size();
        boolean start = false;
        while(countstack > 0)
        {
          if (tempList.size() == 0 && start == false) {
        	for(int i=0; i<liststack.size() ; i++)
        	{
        		 ContainerBox s = (ContainerBox)liststack.get(i).pop();
        		 while(s.getN() == 0 && liststack.get(i).size() > 0)
        		 {
        			s =  (ContainerBox) liststack.get(i).pop();
        		 }	
        		 tempList.add(s);//(ContainerBox)liststack.get(i).pop());	 		
        	}
        	start = true;
        /*	for(int i=0; i<liststack.size() ; i++)
        	{
        		System.out.print(tempList.get(i).getN());	
        		System.out.print("\t");
        	}
        	System.out.print("\n");	 */
          }
          else if(tempList.size() < countstack && liststack.get(pos).isEmpty() == false )
          {
        	  if(liststack.get(pos).isEmpty())
      		  {
      			countstack--;
      		  }
        	  else
        	  {
        	  tempBox = (ContainerBox)liststack.get(pos).pop();
        	  while(tempBox.getN() == 0 && liststack.get(pos).size() > 0)
        	  {
        		  tempBox = (ContainerBox)liststack.get(pos).pop();
        	  }
        	  tempList.add(tempBox);
        	  }  
          } 
          if(tempList.size() >  0)
          {
          Collections.sort(tempList);
          int max = (int) tempList.get(tempList.size()-1).getN();
          pos = (int) tempList.get(tempList.size()-1).getColpos();
          if(max !=  0)
          {
        	  if( endstack.get(currentstackend).isEmpty() == false)
        	  {
        		 if( ((ContainerBox) endstack.get(currentstackend).peek()).getN() < max )
        		 {
        			// if(currentstackend == endstack.size())
        			// {
        			 Queue<ContainerBox> temps1 = new LinkedList<>();//new Stack<ContainerBox>();
           	         endstack.add(temps1);
           	         currentstackend++;
           	        // endstack.get(currentstackend+1).push((ContainerBox)tempList.get(tempList.size()-1));
        			// }
        		 }
        	  }
        	  if(endstack.get(currentstackend).size() == 4  )
        	  {
        		  Queue<ContainerBox> temps1 = new LinkedList<>();//new Stack<ContainerBox>();
        	      endstack.add(temps1);
        		  currentstackend++;
        	  }
              endstack.get(currentstackend).add((ContainerBox)tempList.get(tempList.size()-1));//push((ContainerBox)tempList.get(tempList.size()-1));
              orderList.add(new SortingObject(heightyard-endstack.get(currentstackend).size()+1,currentstackend+1,max));
             // System.out.println(max);
          }
          tempList.remove(tempList.size()-1);
          }
          else {
        	  countstack--;
          }
        }
        FormContainerArrangement.columnyard = endstack.size();
        FormContainerArrangement.endstack = endstack;
       
      /*  
        for(int i = 0 ; i < endstack.size() ; i ++)
        {
        	while(endstack.get(i).size() > 0)
           {
        	   System.out.print(((ContainerBox) endstack.get(i).poll()).getN());
        	   System.out.print("\t");
           }
           System.out.println();
        }
        //System.out.println(endstack.size());
        for(int j  = 0; j < orderList.size();j ++)
      	{
        	System.out.println(orderList.get(j).getRowpos() + " - "  + orderList.get(j).getColpos() + " - "  + orderList.get(j).getN());
      	}  */
     
      	/* for(int j= 0 ;j< tempList.size();j++)
        {
      	  System.out.print(tempList.get(j).getN() + "\t");
        } */
  	  //System.out.println(pos);
  	  //System.out.println("pos : " + pos);
    }
    public void arrangeMyList(ContainerBox[][] r,SortingObject[][] nr,List<SortingObject> orderList)
    {
    	int currentcolpos = 0;
    	int row  = r.length;	
    	int currentrowpos = r.length-1;
    	int previouscol=0;
    	int previousrow=0;
    	ContainerBox tempBox = null;
    	boolean stack = false,add = false;
    	List<ContainerBox> tempList = new ArrayList<ContainerBox>();
        boolean[] status = new boolean[r[row-1].length];
        Arrays.fill(status, false);
        AvailableSlot[] availableslotarray = new  AvailableSlot[r[row-1].length+1];
        for(int l  = 0; l < r[row-1].length ; l++ )
        {
        	availableslotarray[l] = new AvailableSlot(r.length-1,l);
        	availableslotarray[l].setCol(l);
        	availableslotarray[l].setRow(r.length-1);
        }
        int numberofcol = r[row-1].length;
     	for(int i=0;i < row;i++)
    	{
     		
    		for(int j=0;j <r[row-1].length ;j++)
    		{   
    		   if(status[j]  == false)
    		   {
    			   if(i   <  0  )
     			   {
     				  i++;
     			   }
    			   if  (r[i][j].isChecked() == false ) {
    				   tempList.add(r[i][j]);
    	    		   status[j] = true;
    			   }
    		   }
    		   if(tempList.size() == numberofcol && tempList.size() > 0)
  			   {
    			 
    			 Collections.sort(tempList);
    			 int max = tempList.get(tempList.size()-1).getN();
    			for(int k  = 0; k < r[row-1].length ; k++ )
    		     {
    					if(stack == true && availableslotarray[k].getRow() >= 0 && availableslotarray[k].isChecked() == false)
      				    {
      				    	 int tempN =   nr[availableslotarray[k].getRow()+1][availableslotarray[k].getCol()].getN();
      				    	//int tempRow =   (int) nr[availableslotarray[k].getRow()+1][availableslotarray[k].getCol()].getRowpos();
      				    	//int tempCol =(int) nr[availableslotarray[k].getRow()+1][availableslotarray[k].getCol()].getColpos();
      				    	 int tempRow =   (int) nr[availableslotarray[k].getRow()+1][availableslotarray[k].getCol()].getOrirowpos();
      				    	 int tempCol =(int) nr[availableslotarray[k].getRow()+1][availableslotarray[k].getCol()].getOricolpos();
      				    	 tempBox = new ContainerBox(tempRow,tempCol,tempN,false);
      				    	 add = true;
      				         orderList.add(new SortingObject(tempRow,tempCol,tempN,true));
      				    	 currentcolpos = availableslotarray[k].getCol();
		        			 currentrowpos = availableslotarray[k].getRow()+1;
		        			 stack = false;
      				    	 break;
      				    }
    		        	if(availableslotarray[k].getRow() < r.length-1 && availableslotarray[k].isChecked() == false )
    		        	{
    		        		if ( max  < nr[availableslotarray[k].getRow()+1][availableslotarray[k].getCol()].getN() ) 
    		        		{
    		        			currentcolpos = availableslotarray[k].getCol();
    		        			currentrowpos = availableslotarray[k].getRow();
    		        		    availableslotarray[k].setRow(availableslotarray[k].getRow()-1);
    		    			 if (availableslotarray[k].getRow() < 0)
    		    			 {
    		    				 availableslotarray[k].setChecked(true);
    		    			 }
    		        			break;
    		        		}
    		        	}
    		         	if(availableslotarray[k].getRow() == r.length-1 && availableslotarray[k].getRow() > 0 )
    		        	{
    		        		currentcolpos = availableslotarray[k].getCol();
    		        		currentrowpos = availableslotarray[k].getRow();	 
		        			availableslotarray[k].setRow(availableslotarray[k].getRow()-1);
    		        		break;
    		        	}
    		         	if(k == r[row-1].length-1 && previouscol == currentcolpos && previousrow == currentrowpos && stack == false)
    		        	{
    		        		stack = true;
    		        		k = -1;
    		        	}
    		     }
    			 previouscol = currentcolpos;
	        	 previousrow = currentrowpos; 
    			 nr[currentrowpos][currentcolpos].setN(max);
    			 System.out.println(nr[currentrowpos][currentcolpos].getN());
    			 nr[currentrowpos][currentcolpos].setColpos(currentcolpos);
    			 nr[currentrowpos][currentcolpos].setRowpos(currentrowpos);
    			 nr[currentrowpos][currentcolpos].setMoved(tempList.get(tempList.size()-1).isMoved());;
    			 nr[currentrowpos][currentcolpos].setOricolpos((int)tempList.get(tempList.size()-1).getColpos());
    			 nr[currentrowpos][currentcolpos].setOrirowpos((int)tempList.get(tempList.size()-1).getRowpos());
    			 orderList.add(new SortingObject(currentrowpos,currentcolpos,max,tempList.get(tempList.size()-1).isMoved()));
    			 if(tempList.get(tempList.size()-1).getRowpos() >= 0) {
    			 r[(int) tempList.get(tempList.size()-1).getRowpos()][(int) tempList.get(tempList.size()-1).getColpos()].setChecked(true);
    			 status[(int) tempList.get(tempList.size()-1).getColpos()] = false;
    			 }
    			 if(tempList.get(tempList.size()-1).getRowpos() == row-1 && add == false)
      			 { 
      				numberofcol--;			
      			 }
      			tempList.remove(tempList.size()-1);
      			if(add == true)
      			{
      		    //orderList.add(new SortingObject((int)tempBox.getRowpos(),(int)tempBox.getColpos(),tempBox.getN()));
      			tempList.add(tempBox);
      			
      			add = false;
      			}
      			i = -1;
  			   }
    		}
    		}
     	  for(int i = 0; i<=orderList.size()-1 ; i++)
    	   {
    		  System.out.println(orderList.get(i).getN());
     		 System.out.println(orderList.get(i).isMoved());
    	   }
    }
    public void printMyList(SortingObject[][] nr) {
    	for (int i = 0; i < nr.length; i++) {
			for (int j = 0; j < nr[nr.length-1].length; j++) {
				System.out.print(nr[i][j].getN() + " ");
			}
			System.out.println();
		}
    	
    }
    public void printMyList(ContainerBox[][] r) {
    	for (int i = 0; i < r.length; i++) {
    		ContainerBox[] row = r[i];
			for (int j = 0; j < row.length; j++) {
				System.out.print(row[j].getN() + " ");
			}
			System.out.println();
		}
    	
    }
    public void printMyList(String[][] r) {
    	for (int i = 0; i < r.length; i++) {
    		String[] row = r[i];
			for (int j = 0; j < row.length; j++) {
				System.out.print(row[j] + " ");
			}
			System.out.println();
		}
    	
    }
    public void printMyList(int[][] r) {
    	for (int i = 0; i < r.length; i++) {
    		int[] row = r[i];
			for (int j = 0; j < row.length; j++) {
				System.out.print(row[j] + " ");
			}
			System.out.println();
		}
    	
    }
    public void exchangeint(String[][] r) {
    	for (int i = 0; i < r.length; i++) {
    		String[] row = r[i];
			for (int j = 0; j < row.length; j++) {
				if(row[j].equals("x"))
				{
					row[j] = "0";
				}
			}
	
		}
    	
    }
    public int[][] convertint(String[][] r) {
    	int[][] n = new int[r.length][r[0].length];
    	for (int i = 0; i < r.length; i++) {
    		String[] row = r[i];
			for (int j = 0; j < row.length; j++) {
				n[i][j] = Integer.parseInt(row[j]);
			}
	
		}
		return n;
    	
    }
    public int[][] removezeroline(String[][] r) {
    	int[][] n = new int[r.length][r[0].length];
    	for (int i = 0; i < r.length; i++) {
    		String[] row = r[i];
			for (int j = 0; j < row.length; j++) {
				n[i][j] = Integer.parseInt(row[j]);
			}
	
		}
		return n;
    }
    public int[][] arrangeArray(int[][] r) {
    	int[][] n = new int[r.length][r[0].length];
    	int rowl = r[0].length;
    	for (int i = 0; i < rowl-1 ; i++) {
			for (int j = 0; j < r.length; j++) {
				if(r[i][j] > r[i+1][j] && r[i+1][j] == 0)
				{
					
				}
			}
		}
		return n;
    	
    }
}
