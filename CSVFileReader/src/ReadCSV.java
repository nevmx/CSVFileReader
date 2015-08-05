import java.util.*;
import java.io.*;

public class ReadCSV {
	public static void main(String[] args) {
		try {
			CSVFile file = CSVUtil.readCSVFile("google_transit/stops.txt");
			
			CSVUtil.writeCSVFile(file, "hello.txt");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}