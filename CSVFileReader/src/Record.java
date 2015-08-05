//This is one record, i.e. one line from a list of CSV entries with the
//actual values

public class Record {
	String[] values;
	
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
}