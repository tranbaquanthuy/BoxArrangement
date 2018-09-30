package AlgorithmArrangement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Entity.ContainerBox;
import Entity.SortingObject;
public class Test2 {


	public static void setElementCheckList(int inputdata[][],int checkList[][]) 
	{
		for (int i = 0; i < inputdata.length; i++) {
    		int[] row = inputdata[i];
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
    public static void arrangeMyList(int inputdata[][],int checkList[][],int myList1[][])
    {
    	
    	int currentrowpos = inputdata.length-1;
    	int currentcolpos = 0;
    	List<Integer> tempList = new ArrayList<Integer>();
    	int row  = inputdata.length;
    	List<Integer> temprowpos = new ArrayList<Integer>();
    	List<Integer> tempcolpos = new ArrayList<Integer>();
    	for(int i=0;i < inputdata[row-1].length;i++)
    	{
    		 System.out.println("i : "+i);
    		for(int j=0;j < row;j++)
    		{
    			System.out.println("j : "+j);
    		   if(inputdata[j][i] != 0 && checkList[j][i] != 1 )//&& inputdata[j][i] > max)
    		   {
    			  
    			   tempList.add(inputdata[j][i]);
    			   temprowpos.add(j);
    			   tempcolpos.add(i);
    			   System.out.println(tempList);
    			   
    			   if(tempList.size() == row || i ==  inputdata[row-1].length -1 )
    			   {
    				   int n = Collections.max(tempList);
    				   int pos = tempList.indexOf(n);
    				   System.out.println(temprowpos.get(pos));
    				   System.out.println(tempcolpos.get(pos));
    				   System.out.println(inputdata[temprowpos.get(pos)][tempcolpos.get(pos)]);
    				   myList1[currentrowpos][currentcolpos] = inputdata[temprowpos.get(pos)][tempcolpos.get(pos)];
    				   System.out.println(myList1[currentrowpos][currentcolpos]);
    				   checkList[temprowpos.get(pos)][tempcolpos.get(pos)] = 1;
        			   currentcolpos+=1;
        			   if(currentcolpos >= inputdata[inputdata.length-1].length )
        			   {
        				   currentcolpos = 0;
        				   currentrowpos -=1; 
        			   }
        			   tempList.removeAll(tempList);
        			   temprowpos.removeAll(temprowpos);
        			   tempcolpos.removeAll(tempcolpos);
        			   j=0;
        			   i=-1;
    			   }
    			   break;
    			  /* myList[currentrowpos][currentcolpos] = inputdata[i][j];
    			   currentcolpos+=1;
    			   if(currentcolpos >= inputdata[inputdata.length-1].length )
    			   {
    				   currentcolpos = 0;
    				   currentrowpos -=1; 
    			   }*/			   
    		   }
    		}
    	}
    	
    	//return myList;
    }
    public static void arrangeMyListSquare(int inputdata[][],int checkList[][],int myList1[][] )
    {
    	
    	int currentrowpos = 0;
    	int currentcolpos = inputdata.length-1;
    	List<Integer> tempList = new ArrayList<Integer>();
    	int row  = inputdata.length;
    	List<Integer> temprowpos = new ArrayList<Integer>();
    	List<Integer> tempcolpos = new ArrayList<Integer>();
    	for(int i=0;i < inputdata[row-1].length;i++)
    	{
    		 System.out.println("i : "+i);
    		for(int j=0;j < row;j++)
    		{
    			   System.out.println("j : "+j);
    			   tempList.add(inputdata[i][j]);
    			   checkList[i][j]   = 1 ; 			   
    			   temprowpos.add(i);
    			   tempcolpos.add(j);
    			   if(tempList.size() == row)
    			   {
    				Collections.sort(tempList);
    				for(int k = 0 ; k < tempList.size();k++)
    				{
    					System.out.println("k :" +tempList.get(k));
    					myList1[k][currentrowpos] = tempList.get(k) ;
    					 
    				}
    				   currentrowpos +=1; 
        			   tempList.removeAll(tempList);
        			   temprowpos.removeAll(temprowpos);
        			   tempcolpos.removeAll(tempcolpos);
        
    			   }
    			   
    		   
    		}
    	}
    }

    public void arrangeMyListSquare(ContainerBox[][] r,SortingObject[][] nr  )
    {
    	int currentrowpos = 0;
    	int row  = r.length;	
    	List<Integer> tempList = new ArrayList<Integer>();
     	for(int i=0;i < r[row-1].length;i++)
    	{
    		for(int j=0;j < row;j++)
    		{
    		   tempList.add(r[i][j].getN());
  			   if(tempList.size() == row)
  			   {
  				Collections.sort(tempList);	
  				for(int k = 0 ; k < tempList.size();k++)
  				{
  					nr[k][currentrowpos].setN(tempList.get(k));
  				}
  				   currentrowpos +=1; 
      			   tempList.removeAll(tempList);
  			   }
    		}
    	}
    	
    }
    public static List<Integer> exchangeDimension(int inputdata[][])
    {
    	List<Integer> myList = new ArrayList<Integer>();
    	for(int i=0;i < inputdata.length;i++)
    	{
    		for(int j=0;j < inputdata[i].length;j++)
    		{
    		   myList.add(inputdata[i][j]);
    		}
    	}
    	return myList;
    }

    public void printMyList(SortingObject[][] nr) {
    	for (int i = 0; i < nr.length; i++) {
			for (int j = 0; j < nr[nr.length-1].length; j++) {
				System.out.print(nr[i][j].getN() + " ");
			}
			System.out.println();
		}
    	
    }
    public void printMyList2(ContainerBox[][] r) {
    	for (int i = 0; i < r.length; i++) {
    		ContainerBox[] row = r[i];
			for (int j = 0; j < row.length; j++) {
				System.out.print(row[j].getN() + " ");
			}
			System.out.println();
		}
    	
    }
}
