//Author: Denzil Stefen Showers

//class for storing information on identifier declarations and uses
//used as a linked list of IDs, which map to hash tables per scope of AST

public class IDInfo {
	String name; // the name of the identifier
	int declLine; // line where this ID is declared
	IDInfo prev; // Previous IDInfo (in list of all IDs found and processed)
	String type; // data type
	int[] usesarr; // int[0]=total use count; int[n]=use count at line n
	
	// constructor
	IDInfo(String n, int l, IDInfo prevID, String t){
		name = n;
		declLine=l;
		prev = prevID;
		type = t;
		usesarr = new int[]{0};
	}
	
	//returns ID use info in the form: line#: identifier(type): use1,use2,...
	public String toString(){
		//first check if there are no identifiers
		if (name==null){
			return "No identifiers declared";
		}
		else{
			//at least one identifier found
			//unpack identifier use info into the uses string
			String uses = "";
			int i = 1;
			while(i < usesarr.length){
				//does nothing if there was nothing done at line i (i=0)
				if(usesarr[i] > 0){
					//if more than one use at line i...
					if(usesarr[i] > 1){
						//display "line#(#ofUses)"
						uses = uses+","+i+"("+usesarr[i]+")";
					}
					else{
						//display "line#"
						uses = uses+","+i;
					}
				}
				i++;
			}
			
			//trim off the leading comma
			if (uses.length() > 0){
				uses = uses.substring(1, uses.length());
			}
			
			String thisLine=declLine+": "+name+"("+type+"): "+uses+"\n";
			if (prev == null) return thisLine;
			//expect global IDInfo to start with the last ID declared, so print 
			//the previous line before thisLine, and so on
			else return prev.toString()+thisLine;
		}
	}
}
