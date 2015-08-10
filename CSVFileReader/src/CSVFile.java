import java.util.ArrayList;
import java.util.List;

// This is one single CSVFile
public class CSVFile {
	private String[] fields;
	private Record[] records;
	
	//Constructor
	public CSVFile(String[] fields_constr, Record[] records_constr) {
		this.fields = fields_constr;
		this.records = records_constr;
	}
	
	//Getter
	public String[] getFields() {
		return fields;
	}
	
	public Record[] getRecords() {
		return records;
	}
	
	public int getIdx(String field) {
		//Hold the index of field we're looking for
		int field_idx = -1;
		
		//Find index of the field that's looked for
		for (int i = 0; i < this.fields.length; i++) {
			if (this.fields[i].equals(field)) {
				field_idx = i;
			}
		}
		
		//return it
		return field_idx;
	}
	
	//Methods
	
	//Find a value - any field - return first occurrence
	public Record seek(String value) {
		//To hold values of one record
		String[] values;
		
		//Cycle through all records of this file
		for (int i = 0; i < this.records.length; i++) {
			//Store values of one record into values
			values = records[i].getValues();
			
			//Cycle through all values
			for (int j = 0; j < values.length; j++) {
				
				//If it is what we're looking for, return records[i]
				if (value.equals(values[j])) {
					return records[i];
				}
			}
		}
		
		return null;
	}
	
	//Find a value - any field - find all occurrences
	public Record[] seekAll(String value) {
		//To hold values of one record
		String[] values;
		
		//To hold temporarily all the results - the list needs to grow
		List<Record> needles = new ArrayList<Record>();
		
		//Cycle through all records of this file
		for (int i = 0; i < this.records.length; i++) {
			//Store values of one record into values
			values = records[i].getValues();
			
			//Cycle through all values
			for (int j = 0; j < values.length; j++) {
				
				//If it is what we're looking for, add records[i] to needles
				if (value.equals(values[j])) {
					needles.add(records[i]);
				}
			}
		}
		
		if (needles.size() == 0) {
			return null;
		}
		
		//Create an array of records and copy the List into it
		Record[] ret = new Record[needles.size()];
		needles.toArray(ret);
		
		//Return it
		return ret;
	}
	
	//Find a value - in field field - first occurrence (overloaded method)
	public Record seek(String field, String value) {
		//To hold values of one record
		String[] values;
		
		//Get index of the field we're looking for
		int field_idx = this.getIdx(field);
		
		//Field not found - return -1
		if (field_idx == -1)
			return null;
		
		//Cycle through all records of this file
		for (int i = 0; i < this.records.length; i++) {
			//Store values of one record into values
			values = records[i].getValues();
			
			//If it is what we're looking for, return records[i]
			if (value.equals(values[field_idx])) {
				return records[i];
			}
			
		}
		
		return null;
	}
	
	//Find a value - in field field - find all occurrences (overloaded)
	public Record[] seekAll(String field, String value) {
		//To hold values of one record
		String[] values;
		
		//To hold temporarily all the results - the list needs to grow
		List<Record> needles = new ArrayList<Record>();
		
		//Get index of the field we're looking for
		int field_idx = this.getIdx(field);
		
		//Field not found - return -1
		if (field_idx == -1)
			return null;
		
		//Cycle through all records of this file
		for (int i = 0; i < this.records.length; i++) {
			//Store values of one record into values
			values = records[i].getValues();
			
			//If it is what we're looking for, add records[i] to needles
			if (value.equals(values[field_idx])) {
				needles.add(records[i]);
			}
			
		}
		
		if (needles.size() == 0) {
			return null;
		}
		
		//Create an array of records and copy the List into it
		Record[] ret = new Record[needles.size()];
		needles.toArray(ret);
		
		//Return it
		return ret;
	}
	
	//print out the contents of the file - temporary as it works with the console
	public void print() {
		System.out.println(CSVUtil.toFormattedString(fields));
		for (int i = 0; i < this.records.length; i++) {
			System.out.println(this.records[i].toString());
		}
	}
	
	//copy this file and return the new file
	public CSVFile copy() {
		CSVFile new_file = new CSVFile(this.fields.clone(), this.records.clone());
		return new_file;
	}
}
