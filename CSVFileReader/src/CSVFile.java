import java.util.ArrayList;
import java.util.List;

// This is one single CSVFile
public class CSVFile {
	private String[] fields;
	private Entry[] entries;
	
	//Constructor
	public CSVFile(String[] fields_constr, Entry[] entries_constr) {
		this.fields = fields_constr;
		this.entries = entries_constr;
	}
	
	//Getter
	public String[] getFields() {
		return fields;
	}
	
	public Entry[] getEntries() {
		return entries;
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
	public Entry seek(String value) {
		//To hold values of one entry
		String[] values;
		
		//Cycle through all entries of this file
		for (int i = 0; i < this.entries.length; i++) {
			//Store values of one entry into values
			values = entries[i].getValues();
			
			//Cycle through all values
			for (int j = 0; j < values.length; j++) {
				
				//If it is what we're looking for, return entries[i]
				if (value.equals(values[j])) {
					return entries[i];
				}
			}
		}
		
		return null;
	}
	
	//Find a value - any field - find all occurrences
	public Entry[] seekAll(String value) {
		//To hold values of one entry
		String[] values;
		
		//To hold temporarily all the results - the list needs to grow
		List<Entry> needles = new ArrayList<Entry>();
		
		//Cycle through all entries of this file
		for (int i = 0; i < this.entries.length; i++) {
			//Store values of one entry into values
			values = entries[i].getValues();
			
			//Cycle through all values
			for (int j = 0; j < values.length; j++) {
				
				//If it is what we're looking for, add entries[i] to needles
				if (value.equals(values[j])) {
					needles.add(entries[i]);
				}
			}
		}
		
		if (needles.size() == 0) {
			return null;
		}
		
		//Create an array of entries and copy the List into it
		Entry[] ret = new Entry[needles.size()];
		needles.toArray(ret);
		
		//Return it
		return ret;
	}
	
	//Find a value - in field field - first occurrence (overloaded method)
	public Entry seek(String field, String value) {
		//To hold values of one entry
		String[] values;
		
		//Get index of the field we're looking for
		int field_idx = this.getIdx(field);
		
		//Field not found - return -1
		if (field_idx == -1)
			return null;
		
		//Cycle through all entries of this file
		for (int i = 0; i < this.entries.length; i++) {
			//Store values of one entry into values
			values = entries[i].getValues();
			
			//If it is what we're looking for, return entries[i]
			if (value.equals(values[field_idx])) {
				return entries[i];
			}
			
		}
		
		return null;
	}
	
	//Find a value - any field - find all occurrences
	public Entry[] seekAll(String field, String value) {
		//To hold values of one entry
		String[] values;
		
		//To hold temporarily all the results - the list needs to grow
		List<Entry> needles = new ArrayList<Entry>();
		
		//Get index of the field we're looking for
		int field_idx = this.getIdx(field);
		
		//Field not found - return -1
		if (field_idx == -1)
			return null;
		
		//Cycle through all entries of this file
		for (int i = 0; i < this.entries.length; i++) {
			//Store values of one entry into values
			values = entries[i].getValues();
			
			//If it is what we're looking for, add entries[i] to needles
			if (value.equals(values[field_idx])) {
				needles.add(entries[i]);
			}
			
		}
		
		if (needles.size() == 0) {
			return null;
		}
		
		//Create an array of entries and copy the List into it
		Entry[] ret = new Entry[needles.size()];
		needles.toArray(ret);
		
		//Return it
		return ret;
	}
}