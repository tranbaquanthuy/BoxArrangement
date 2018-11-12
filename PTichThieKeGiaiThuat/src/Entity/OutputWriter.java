package Entity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OutputWriter {
	private  File file;
	private  BufferedWriter bw;
	 
	public void CreateWriter(String link) throws IOException {
			file = new File(link);
		    bw = new BufferedWriter(new FileWriter(file));
		}
	public void CloseWriter() throws IOException {
	    bw.close();
	}
	public void WriteWriter(String text) throws IOException {
		bw.write(text);
	}
}
