package Entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InputReader {
	private  File file;
	private  BufferedReader br;
    
	public void CreateReader(String link) throws FileNotFoundException {
		file = new File(link);
	    br = new BufferedReader(new FileReader(file));
	}
	public void CloseReader() throws IOException {
	    br.close();
	}
	public String getAllContent(String link) throws IOException {
		CreateReader(link);
		String ac = "";
		  String st = ""; 
		  while ((st = br.readLine()) != null) {
			  ac = ac + st + "\n";
		  }	  
		  return ac;
	}
	public Integer getHeight(String link) throws IOException {
              CreateReader(link);
			  String st; 
			  while ((st = br.readLine()) != null) {
				    if(st.endsWith("//height"))
				    {
				    	String temp[] = st.split("//height");
				    	CloseReader();
				    	return Integer.parseInt(temp[0]);
				    }
			  }
		    return 0;	
	}
	public Integer getcontainernumber(String link) throws IOException {
        CreateReader(link);
		  String st; 
		  while ((st = br.readLine()) != null) {
			    if(st.endsWith("//containers"))
			    {
			    	String temp[] = st.split("//containers");
			    	CloseReader();
			    	return Integer.parseInt(temp[0]);
			    }
		  }
	    return 0;	
}
	public Integer getheighyard(String link) throws IOException {
        CreateReader(link);
		  String st; 
		  int flag = 0;
		  while ((st = br.readLine()) != null) {
			    
			    if(st.endsWith("//height") && flag >= 1)
			    {
			    	String temp[] = st.split("//height");
			    	CloseReader();
			    	return Integer.parseInt(temp[0]);
			    }
			    if(st.endsWith("//height"))
			    {
			    	flag++;
			    }
		  }
	    return 0;	
}
	public String getPath(String link) throws IOException {
        CreateReader(link);
		  String st; 
		  while ((st = br.readLine()) != null) {
			    	CloseReader();
			    	return st;
		  }
         return "";
	
}
	public  Integer getColumns(String link) throws IOException {
          CreateReader(link);
		  String st; 
		  while ((st = br.readLine()) != null) {
			    if(st.endsWith("//columns"))
			    {
			    	String temp[] = st.split("//columns");
			    	CloseReader();
			    	return Integer.parseInt(temp[0]);
			    }
		  }

	return 0;
	
}
public  String[][] input(String link,String[][] number) throws IOException
 {
	  CreateReader(link); 
	  String[] list = null;
	  int listposition =0 ;
	  String firstpartposition = null;
	  try {
		  String st; 
		  while ((st = br.readLine()) != null) {
				if(st.startsWith("r"))
				{
			     list = st.split("\\s+", 2);
			     firstpartposition = list[0];
			     listposition = Integer.parseInt(firstpartposition.substring(1, firstpartposition.length()));
			     number[listposition-1] =  list[1].split("\\s+");
				}
			}
	   } 
	catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	 catch (IOException e) {
		e.printStackTrace();
	} 
	CloseReader();
	return number;
 }
}
