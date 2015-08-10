import java.util.*;
import java.io.*;

public class ReadCSV {
	public static void main(String[] args) {
		try {
			CSVFile file = CSVUtil.readCSVFile("google_transit/stops.txt");
			file.print();
			
			Record stop_one = file.seek("LVL2C");
			Record stop_one_copy = file.seek("LVL2C");
			Record stop_two = file.seek("LVL2A");
			
			System.out.println(stop_one.compareTo(stop_one_copy));
			System.out.println(stop_one.compareTo(stop_two));
			
			stop_one.print();
			
			CSVFile file2 = file.copy();
			file2.print();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
