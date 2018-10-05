package AlgorithmArrangement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import Entity.AvailableSlot;
import Entity.ContainerBox;
import Entity.SortingObject;
public class ArrangingAlgorithm {


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
    


    public void arrangeMyListSquare(ContainerBox[][] r,SortingObject[][] nr  )
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
  				}
  				   currentrowpos +=1; 
      			   tempList.removeAll(tempList);
      			  
  			   }
    		}
    	}
    	
    }
    public void arrangeMyList(ContainerBox[][] r,SortingObject[][] nr)
    {
    	int currentcolpos = 0;
    	int row  = r.length;	
    	int currentrowpos = r.length-1;
    	int previouscol=0;
    	int previousrow=0;
    	ContainerBox tempBox = null;
    	boolean stack = false,add = false;
    	List<ContainerBox> tempList = new ArrayList<ContainerBox>();
    	List<SortingObject> orderList = new ArrayList<SortingObject>();
        boolean[] status = new boolean[r[row-1].length];
        Arrays.fill(status, false);
        AvailableSlot[] availableslotarray = new  AvailableSlot[r[row-1].length];
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
    			   if  (r[i][j].isChecked() == false) {
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
      				    	 int tempRow =   (int) nr[availableslotarray[k].getRow()+1][availableslotarray[k].getCol()].getRowpos();
      				    	 int tempCol =(int) nr[availableslotarray[k].getRow()+1][availableslotarray[k].getCol()].getColpos();
      				    	 tempBox = new ContainerBox(tempRow,tempCol,tempN,true);
      				    	 add = true;
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
    			 nr[currentrowpos][currentcolpos].setColpos(currentcolpos);
    			 nr[currentrowpos][currentcolpos].setRowpos(currentrowpos);
    			 nr[currentrowpos][currentcolpos].setMoved(tempList.get(tempList.size()-1).isMoved());;
    			orderList.add(nr[currentrowpos][currentcolpos]);
    			r[(int) tempList.get(tempList.size()-1).getRowpos()][(int) tempList.get(tempList.size()-1).getColpos()].setChecked(true);
    			status[(int) tempList.get(tempList.size()-1).getColpos()] = false;
    			if(tempList.get(tempList.size()-1).getRowpos() == row-1 && add == false)
      			{
      				numberofcol--;			
      			}
      			tempList.remove(tempList.size()-1);
      			if(add == true)
      			{
      			tempList.add(tempBox);
      			System.out.println(tempList.get(tempList.size()-1).isMoved());
      			add = false;
      			}
      			i = -1;
  			   }
    		}
    		}
     	for(int h  = 0 ; h <orderList.size() ;h ++)
     	{
     		System.out.println(orderList.get(h).isMoved());
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
}
