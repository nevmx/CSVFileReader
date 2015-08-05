import java.util.*;
import java.io.*;

public class ReadCSV {
	public static void main(String[] args) {
		try {
			String[] fields;
			CSVFile file = CSVUtil.readCSVFile("google_transit/stops.txt");
			
			fields = file.getFields();
			
			for (int i = 0; i < fields.length; i++) {
				System.out.print(fields[i] + ", ");
			}
			System.out.println();
			
			Entry[] entries = file.getEntries();
			
			for (int i = 0; i < entries.length; i++) {
				System.out.println(entries[i].toString());
			}
			System.out.println();
			//System.out.println(file.seek("stop_id", "MTL59C").toString());
			
			Entry[] entries2 = file.seekAll("zone_id", "3");
			
			for (int i = 0; i < entries2.length; i++) {
				System.out.println(entries2[i].toString());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}