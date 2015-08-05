import java.io.*;
import java.util.ArrayList;
import java.util.List;

//Various useful functions, this is to avoid polluting other classes
public class CSVUtil {
	public static CSVFile readCSVFile(String file_path) throws IOException {
		
		//open reader
		FileInputStream fis = new FileInputStream(file_path);
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		//Read first character. If it is 0xfeff, skip it
		br.mark(1);
		if (br.read() == 0xFEFF)
			br.mark(1);
		else 
			br.reset();
		
		//Cache all the contents of the file in a list
		List<String> file = new ArrayList<String>();
		String line;
		line = br.readLine();
		
		while (line != null) {
			file.add(line);
			line = br.readLine();
		}
		
		//Close everything, not needed anymore
		br.close();
		isr.close();
		fis.close();
				
		//Read first line, those are the fields
		String[] fields;
		fields = file.get(0).split(",");
		
		//TODO: Implement values reading
		Entry[] entries = new Entry[file.size()-1];
		for (int i = 1; i < file.size(); i++) {
			entries[i-1] = new Entry(file.get(i).split(","));
		}
		
		//Create the file
		CSVFile csvfile = new CSVFile(fields, entries);
				
		//return the file that was created
		return csvfile;
	}
	
	public int seekEntry(Entry[] haystack, Entry needle) {
		for (int i = 0; i < haystack.length; i++) {
			if (haystack[i].toString().equals(needle.toString())) {
				return i;
			}
		}
		return -1;
	}
}