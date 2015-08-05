import java.util.*;
import java.io.*;

public class ReadCSV {
	public static void main(String[] args) {
		try {
			CSVFile file = CSVUtil.readCSVFile("google_transit/stops.txt");
			
			Record[] zone_six = file.seekAll("zone_id", "6");
			Record[] zone_five = file.seekAll("zone_id", "5");
			
			Record[] both = CSVUtil.join(zone_five, zone_six);
			
			/*
			for (int i = 0; i < both.length; i++) {
				System.out.println(both[i]);
			}
			*/
			
			String[] fields = file.getFields();
			CSVFile new_file = new CSVFile(fields, both);
			
			//write the new_file to a text file
			CSVUtil.writeCSVFile(new_file, "hello2.txt");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
