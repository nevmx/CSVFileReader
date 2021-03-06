import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;

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
		Record[] entries = new Record[file.size()-1];
		for (int i = 1; i < file.size(); i++) {
			entries[i-1] = new Record(file.get(i).split(","));
		}
		
		//Create the file
		CSVFile csvfile = new CSVFile(fields, entries);
				
		//return the file that was created
		return csvfile;
	}
	
	//write a CSVFile object to a file
	public static int writeCSVFile(CSVFile csv_file, String file_path) throws IOException {
		//open things
		FileOutputStream fos = new FileOutputStream(file_path);
		OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
		Writer writer = new BufferedWriter(osw);
		
		//write the fields string
		writer.write(toFormattedString(csv_file.getFields()) + System.lineSeparator());
		
		//write all the records
		Record[] records = csv_file.getRecords();
		//for each record call its toString method
		for (int i = 0; i < records.length; i++) {
			writer.write(records[i].toString() + System.lineSeparator());
		}
		
		//close
		writer.close();
		osw.close();
		fos.close();
		return 0;
	}
	
	//find a Record in a Record[] and return the index (BY REFERENCE)
	public static int seekRecord(Record[] haystack, Record needle) {
		for (int i = 0; i < haystack.length; i++) {
			if (haystack[i] == needle)
				return i;
		}
		
		//not found
		return -1;
	}
	
	//join two Record[]'s together
	public static Record[] join(Record[] a, Record[] b) {
		Record[] c = new Record[a.length + b.length];
		int i;
		
		//first copy a
		for (i = 0; i < a.length; i++) {
			c[i] = a[i];
		}
		
		//then copy b
		for (; i < a.length + b.length; i++) {
			c[i] = b[i - a.length];
		}
		
		return c;
	}
	
	//format a string for proper CSV output
	public static String toFormattedString(String[] str) {
		String ret = "";
		int i;
		for (i = 0; i < str.length - 1; i++) {
			ret += str[i] + ", ";
		}
		ret += str[i];
		return ret;
	}
	
	//remove a record from an array of records by index
	public static Record[] removeRecord(Record[] array, int idx) {
		//simply remove the element at index idx
		return ArrayUtils.remove(array, idx);
	}
	
	//remove a record from an array of records by Record reference
	public static void removeRecord(Record[] array, Record to_be_removed) {
		array = ArrayUtils.remove(array, seekRecord(array, to_be_removed));
	}
	
	//print out an array of records
	public static void print(Record[] records) {
		for (int i = 0; i < records.length; i++) {
			records[i].print();
		}
	}
}
