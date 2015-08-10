//This is one record, i.e. one line from a list of CSV entries with the
//actual values

public class Record {
	private String[] values;
	
	
	//Constructor
	public Record(String[] values_constr) {
		this.values = values_constr;
	}
	
	//Getter
	public String[] getValues() {
		return this.values;
	}
	
	//toString
	public String toString() {
		/*
		String ret = "";
		int i;
		for (i = 0; i < values.length - 1; i++) {
			ret += (values[i] + ", ");
		}
		ret += values[i];
		return ret;
		*/
		return CSVUtil.toFormattedString(this.values);
	}
	
	//print the record to console - temporary
	public void print() {
		System.out.println(this);
	}
	
	//compare this record to another record to find out if they are the same exact record
	public boolean compareTo(Record another_record) {
		//get the other values String[]
		String[] another_values = another_record.getValues();
		
		//if they aren't the same length, they are for sure not the same record
		if (another_values.length != this.values.length)
			return false;
		
		//they are same length, compare all the values
		for (int i = 0; i < this.values.length; i++) {
			if (!this.values[i].equals(another_values[i]))
				return false;
		}
		
		//everything is the same, so return true
		return true;
	}
	
	//copy the object - returns another object
	public Record copy() {
		Record new_record = new Record(this.values.clone());
		return new_record;
	}
}
