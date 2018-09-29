package AlgorithmArrangement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import Entity.ContainerBox;
public class Test2 {

	/*	public static void main(String[] args) {
	   int  inputdata[][] = {{0,6,15},{5,7,4},{30,21,11}};
int inputdata[][] = new int[2000][2000];
		for (int i = 0; i < inputdata.length; i++) {
    		int[] row = inputdata[i];
			for (int j = 0; j < row.length; j++) {
				row[j] = 1;
			}
		}
	     long startTime2 = System.currentTimeMillis();;
	   myList = exchangeDimension2(inputdata);
	   long stopTime2 = System.currentTimeMillis();;
	   long elapsedTime2 = stopTime2 - startTime2;
	   System.out.println("time sau "+elapsedTime2);
	   List<Integer> myList = new ArrayList<Integer>();
		  
	   long startTime = System.currentTimeMillis();;
	   myList = exchangeDimension(inputdata);
	   long stopTime = System.currentTimeMillis();;
	   long elapsedTime = stopTime - startTime;
	   System.out.println("time đầu " + elapsedTime);
	    System.out.println("mảng đầu vào :");
	       printMyList(inputdata);
	       System.out.println(myList);
	       System.out.println("số dòng : ");
	       System.out.println(inputdata.length);
	       System.out.println("số cột : ");
	       int  n = inputdata.length;
	       System.out.println(inputdata[n-1].length);
	       System.out.println("\n");
	       System.out.println("mảng 1 chiều sort :");
	       Collections.sort(myList);
	       System.out.println(myList);
	       System.out.println("\n");
	       System.out.println("mảng check giá trị :");
	       int  checkList[][] = new int [inputdata.length][inputdata[inputdata.length-1].length] ;
	       setElementCheckList(inputdata,checkList);
	       printMyList(checkList);
	       System.out.println("\n");
	       int  myList1[][] = new int [inputdata.length][inputdata[inputdata.length-1].length] ;
	       System.out.println("test thuật toán : ");
	      // int  outputdata[][] =
	       arrangeMyListSquare(inputdata,checkList,myList1);
	       printMyList(checkList);
	       System.out.println("\n");
	       printMyList(myList1); 
	}
  */
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
    /*	int max = 0;
    	int previousposrowmax = -1;
    	int previousposcolmax = -1; */
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
    			   /*  if(previousposrowmax == -1)
    			   {
    				   
    				   max = inputdata[j][i];
    				   System.out.println("-");
    				   System.out.println(max );
    				   checkList[j][i] = 1;
    				   previousposrowmax = j;
    				   previousposcolmax = i;
    			   }
    			   else 
    			   {
    				   max = inputdata[j][i];
    				   System.out.println("--");
    				   System.out.println(max);
    				   checkList[previousposrowmax][previousposrowmax] = 0; 
    				   checkList[j][i] = 1; 	
    				   previousposrowmax = j;
    				   previousposcolmax = i;
    			   }
    			   break;*/
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
    public void arrangeMyListSquare(ContainerBox[][] r,ContainerBox[][] nr  )
    {
    	
    	int currentrowpos = 0;
    //	int currentcolpos = r.length-1;
    	List<Integer> tempList = new ArrayList<Integer>();
    	int row  = r.length;
    	List<Integer> temprowpos = new ArrayList<Integer>();
    	List<Integer> tempcolpos = new ArrayList<Integer>();
    	for(int i=0;i < r[row-1].length;i++)
    	{
    		for(int j=0;j < row;j++)
    		{
    			   tempList.add(r[i][j].getN());
    			   temprowpos.add(i);
    			   tempcolpos.add(j);
    			   if(tempList.size() == row)
    			   {
    				Collections.sort(tempList);
    				for(int k = 0 ; k < tempList.size();k++)
    				{
    					nr[k][currentrowpos].setN(tempList.get(k)); 
    					 
    				}
    				   currentrowpos +=1; 
        			   tempList.removeAll(tempList);
        			   temprowpos.removeAll(temprowpos);
        			   tempcolpos.removeAll(tempcolpos);
        
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
    public static List<Integer> exchangeDimension2(int inputdata[][])
    {
       List<Integer> myList = Arrays.stream(inputdata)
     		   .flatMapToInt(array -> Arrays.stream(array).map(Integer::valueOf))
     		   .mapToObj(i->i).collect(Collectors.toList());
       return myList;
        
    }
    private static void printMyList(int inputdata[][]) {
    	for (int i = 0; i < inputdata.length; i++) {
    		int[] row = inputdata[i];
			for (int j = 0; j < row.length; j++) {
				System.out.print(row[j] + " ");
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
