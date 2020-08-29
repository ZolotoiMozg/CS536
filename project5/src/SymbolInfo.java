//Denzil Stefen Showers - student ID: 9062589552
//CS536 - Programming Assignment 4

import java.util.LinkedList;
import java.util.List;



/**************************************************
*  class used to hold information associated w/
*  Symbs (which are stored in SymbolTables)
*  Update to handle arrays and methods
*
****************************************************/

class SymbolInfo extends Symb {
 public ASTNode.Kinds kind;
 public ASTNode.Types type; 
 public arrayDeclNode array;
 public int varIndex;
 public ASTNode.Ads adr;
 public String label;
 public int intval;
 public String strVal;
 public String topLabel;
 public String bottomLabel;
 public String methodReturnCode;
 public int numberOfLocals;
 public List<ParmInfo> pList;
 
 //list of lists of ParmInfos
 public List<List<ParmInfo>> overloadLists = new LinkedList<List<ParmInfo>>();



 
 public SymbolInfo(String id, ASTNode.Kinds k, ASTNode.Types t){    
	super(id);
	kind = k; type = t;
	adr = ASTNode.Ads.none;};
	
//constructor for handling arrays in assignment
public SymbolInfo(String id, ASTNode.Kinds k, ASTNode.Types t, arrayDeclNode adn){
	super(id);
	kind = k; type = t;
	array = adn;
	adr = ASTNode.Ads.none;
};

 public String toString(){
             return "("+name()+": kind=" + kind+ ", type="+  type+")";};
};

class ParmInfo extends Symb {
	public ASTNode.Kinds kind;
	public ASTNode.Types type;
	
	public ParmInfo(String id, ASTNode.Kinds k, ASTNode.Types t){
		super(id);
		kind = k;
		type = t;
	}
}

