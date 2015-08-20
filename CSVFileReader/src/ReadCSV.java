import java.util.*;
import java.io.*;

public class ReadCSV {
	public static void main(String[] args) {
		try {
			CSVFile file = CSVUtil.readCSVFile("google_transit/stops.txt");
			
			Record[] records_one = file.seekAll("zone_id", "6");
			CSVUtil.print(records_one);
			
			System.out.println();
			
			records_one = CSVUtil.removeRecord(records_one, 0);
			records_one = CSVUtil.removeRecord(records_one, 0);
			CSVUtil.print(records_one);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
