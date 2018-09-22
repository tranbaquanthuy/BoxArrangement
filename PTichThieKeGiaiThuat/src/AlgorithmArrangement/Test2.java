package AlgorithmArrangement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Test2 {

	public static void main(String[] args) {
	   int  inputdata[][] = {{0,1,6},{21,7,8},{5,11,2}};
	   List<Integer> myList = new ArrayList<Integer>();
//	   myList = exchangeDimension(inputdata);
       myList = Arrays.stream(inputdata)
    		   .flatMapToInt(array -> Arrays.stream(array).map(Integer::valueOf))
    		   .mapToObj(i->i).collect(Collectors.toList());   
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
       System.out.println("test thuật toán : ");
       int  outputdata[][] = moveElement(inputdata);
       printMyList(outputdata);
	}
    public static   int[][] moveElement(int inputdata[][])
    {
    	int  myList[][] = new int [inputdata.length][inputdata[inputdata.length-1].length] ;
    	int currentrowpos = inputdata.length-1;
    	int currentcolpos = 0;
    	for(int i=0;i < inputdata.length;i++)
    	{
    		for(int j=0;j < inputdata[i].length;j++)
    		{
    		   if(inputdata[i][j]!= 0)
    		   {
    			   myList[currentrowpos][currentcolpos] = inputdata[i][j];
    			   currentcolpos+=1;
    			   if(currentcolpos >= inputdata[inputdata.length-1].length )
    			   {
    				   currentcolpos = 0;
    				   currentrowpos -=1; 
    			   }
    		   }
    		}
    	}
    	return myList;
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
    private static void printMyList(int inputdata[][]) {
    	for (int i = 0; i < inputdata.length; i++) {
    		int[] row = inputdata[i];
			for (int j = 0; j < row.length; j++) {
				System.out.print(row[j] + " ");
			}
			System.out.println();
		}
    	
    }
}
