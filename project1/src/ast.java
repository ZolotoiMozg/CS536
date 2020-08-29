//Updated by: Denzil Stefen Showers 
//(implemented buildCrossReferences methods and IDList global)

import java.util.*;

/*************************************************************
 * 
 * After scanning  and parsing, the structure and content of a program is represented as an
 * Abstract Syntax Tree (AST).
 * The root of the AST represents the entire program. Subtrees represent various
 * components, like declarations and statements.
 * Program translation and analysis is done by recursively walking the AST, starting
 * at the root.
 * CSX will use a variety of AST nodes since it contains a variety of structures (declarations,
 * methods, statements, expressions, etc.).
 * The AST nodes defined here represent CSX Lite, a small subset of CSX. Hence many fewer
 * nodes are needed.
 * 
 * The analysis implemented here counts the number of identifier declarations and uses
 * on a per scope basis. The entire program is one scope. A block (rooted by a blockNode)
 * is a local scope (delimited in the source program by a "{" and "}").
 * The method countDeclsAndUses implements this analysis. Each AST node has a definition of this
 * method. It may be an explicit definition intended especially for one particular node.
 * If an AST node has no local definition of countDeclsAndUses it inherits a definition from its
 * parent class. The class ASTNode (which is the ancestor of all AST nodes) has a default definition
 * of countDeclsAndUses. The definition is null (does nothing).
 *
 */
// abstract superclass; only subclasses are actually created
abstract class ASTNode {

	public final int 	linenum;
	public final int	colnum;
	//IDList is for all valid IDs, IDUndeclared is for all undeclared IDs
	public static IDInfo IDList, IDUndeclared;
	
	ASTNode(){linenum=-1;colnum=-1;}
	ASTNode(int l,int c){linenum=l;colnum=c;}
	boolean   isNull(){return false;}; // Is this node null?

    	abstract void accept(Visitor v, int indent);// Will be defined in sub-classes    

	// default action on an AST node is to record no declarations and no identifier uses
	 void countDeclsAndUses(ScopeInfo currentScope){ 
		return;
	}
	 
	 // default action on an AST node is to record no declarations and no 
	 //identifier uses
	 void buildCrossReferences(LinkedList<Hashtable<String, IDInfo>> HashList){
		 return;
	 }

};


// This node is used to root only CSXlite programs 
class csxLiteNode extends ASTNode {
	
   	public final fieldDeclsOption	progDecls;
	public final stmtsOption 	progStmts;
	private ScopeInfo  		 scopeList;
	//public IDInfo IDList;
	
	csxLiteNode(fieldDeclsOption decls, stmtsOption stmts, int line, int col){      
		super(line,col);
		progDecls=decls;
		progStmts=stmts;
		scopeList=null;
	}; 
	
	
	void accept(Visitor u, int indent){ u.visit(this,indent); }
	
	// This method begins the count declarations and uses analysis.
	//  It first creates a ScopeInfo node for the entire program.
	//  It then passes this ScopeInfo node to the declarations subtree and then
	//   the statements subtree. Visiting these two subtrees causes all identifier uses and
	//     declarations to be recognized and recorded in the list rooted by the ScopeInfo node.
	//  Finally, the information stored in the ScopeInfo list is converted to string form
	//   and returned to the caller of the analysis.
	
	 String countDeclsAndUses(){
		 scopeList = new ScopeInfo(1,linenum);
		 progDecls.countDeclsAndUses(scopeList);
		 progStmts.countDeclsAndUses(scopeList);
		 return scopeList.toString();
	 }
	 
	 //This method creates a new ScopeInfo node for whole program.
	 //Then it finds info on all declarations in a program
	 //Then it finds info on all statements in a program
	 String buildCrossReferences(){
		 //create a hash table at the root
		 Hashtable<String, IDInfo> IDHash = new Hashtable<String,IDInfo>();

		 //Linked list for storing hash tables
		 LinkedList<Hashtable<String, IDInfo>> HashList = new LinkedList<Hashtable<String, IDInfo>>();
		 //add first Hashtable to list
		 HashList.addFirst(IDHash);
		 
		 //feed the linked list of hashtables into buildCrossReferences
		 progDecls.buildCrossReferences(HashList);
		 progStmts.buildCrossReferences(HashList);
		 
		 //print the whole IDInfo list of identifiers
		 //also prints whole IDUndeclared list of identifiers (first)
		 //return ASTNode.IDUndeclared.toString()+"\n"+ASTNode.IDList.toString();
		 return ASTNode.IDList.toString();
		 /*
		 String rtn = "";
		 if (ASTNode.IDUndeclared != null){
			 rtn = ASTNode.IDUndeclared.toString()+"\n";
		 }
		 if (ASTNode.IDList != null){
			 rtn = rtn + ASTNode.IDList.toString();
		 }
		 return rtn;
		 */
	 }
};

abstract class fieldDeclsOption extends ASTNode{
	fieldDeclsOption(int line,int column){
		super(line,column);
	}
	fieldDeclsOption(){ super(); }
};

class fieldDeclsNode extends fieldDeclsOption {

	public final declNode		thisField;
	public final fieldDeclsOption 	moreFields;
	
	fieldDeclsNode(declNode d, fieldDeclsOption f, int line, int col){
		super(line,col);
		thisField=d;
		moreFields=f;
	}
	
	static nullFieldDeclsNode NULL = new nullFieldDeclsNode();

	void accept(Visitor u, int indent){ u.visit(this,indent);}

	void countDeclsAndUses(ScopeInfo currentScope){
		thisField.countDeclsAndUses(currentScope);
		moreFields.countDeclsAndUses(currentScope);
		return;
	}
	
	void buildCrossReferences(LinkedList<Hashtable<String, IDInfo>> HashList){
		thisField.buildCrossReferences(HashList);
		moreFields.buildCrossReferences(HashList);
		return;
	}
};

class nullFieldDeclsNode extends fieldDeclsOption {
	
	nullFieldDeclsNode(){};

	boolean   isNull(){return true;};

	void accept(Visitor u, int indent){ u.visit(this,indent);}

	void countDeclsAndUses(ScopeInfo currentScope){
			return;
		}
	
	void buildCrossReferences(LinkedList<Hashtable<String, IDInfo>> HashList){
		return;
	}
};

// abstract superclass; only subclasses are actually created
abstract class declNode extends ASTNode {
	declNode(){super();};
	declNode(int l,int c){super(l,c);};
};


class varDeclNode extends declNode { 
	
	public final	identNode	varName;
	public final	typeNode 	varType;
	public final	exprOption 	initValue;
	
	varDeclNode(identNode id, typeNode t, exprOption e,
			int line, int col){
		super(line,col);
		varName=id;
		varType=t;
		initValue=e;
	}
	
	void accept(Visitor u, int indent){ u.visit(this,indent);}

	// This node represents a variable declaration, so we increment the 
	// declarations count by 1
	void countDeclsAndUses(ScopeInfo currentScope){
		currentScope.declsCount+=1;
	}
	
	//This method is responsible for handling declaration of IDs and creating
	//IDInfo objects.  It also checks for illegal redeclarations within the
	//current scope.
	void buildCrossReferences(LinkedList<Hashtable<String, IDInfo>> HashList){
		//string for storing data type
		String dt;
		//string for storing name
		String name = varName.idname.toLowerCase();
		//check head of HashList (current scope) for illegal redeclaration
		//if this is an illegal redeclaration, set type to "illegal"
		if (HashList.peekFirst().containsKey(name)){ 
			dt = "illegal";
			name = "[Redeclared Identifier] "+name;
		}
		else{
			//strip out the "TypeNode" portion of the varType class names 
			//"boolTypeNode" and "intTypeNode"
			dt = varType.getClass().getName();
			dt = dt.substring(0, dt.length() - 8);
		}
		//create new IDInfo object using varName.idname as the string
		//use toLowerCase so IDs are effectively case insensitive
		IDInfo nextID = new IDInfo(name, linenum, ASTNode.IDList, dt);
		//create new entry in current hashtable; name as key and nextID as value
		HashList.peekFirst().put(name, nextID);
		//replace current IDList with nextID
		ASTNode.IDList = nextID;
	}
};

abstract class typeNode extends ASTNode {
// abstract superclass; only subclasses are actually created
	typeNode(){super();};
	typeNode(int l,int c){super(l,c);};
	static nullTypeNode NULL = new nullTypeNode();
};

class nullTypeNode extends typeNode {

	nullTypeNode(){};

	boolean   isNull(){return true;};

	void accept(Visitor u, int indent){ u.visit(this,indent); }
};


class intTypeNode extends typeNode {
	intTypeNode(int line, int col){
		super(line,col);
	}

	void accept(Visitor u, int indent){ u.visit(this,indent); }
};


class boolTypeNode extends typeNode {
	boolTypeNode(int line, int col){
		super(line,col);
	}

	void accept(Visitor u, int indent){ u.visit(this,indent); }
};

//abstract superclass; only subclasses are actually created
abstract class stmtOption extends ASTNode {
	stmtOption(){super();};
	stmtOption(int l,int c){super(l,c);};
};

// abstract superclass; only subclasses are actually created
abstract class stmtNode extends stmtOption {
	stmtNode(){super();};
	stmtNode(int l,int c){super(l,c);};
	static nullStmtNode NULL = new nullStmtNode();
};

class nullStmtNode extends stmtOption {
	nullStmtNode(){};
	boolean   isNull(){return true;};
	void accept(Visitor u, int indent){ u.visit(this,indent);}
	void countDeclsAndUses(ScopeInfo currentScope){return;}
	void buildCrossReferences(LinkedList<Hashtable<String, IDInfo>> 
		HashList){return;}
};

abstract class stmtsOption extends ASTNode{
	stmtsOption(int line,int column){
		super(line,column);
	}
	stmtsOption(){ super(); }
};

class stmtsNode extends stmtsOption { 
	public final stmtNode	    	thisStmt;
	public final stmtsOption 	moreStmts;

	stmtsNode(stmtNode stmt, stmtsOption stmts, int line, int col){
		super(line,col);
		thisStmt=stmt;
		moreStmts=stmts;
	};

	static nullStmtsNode NULL = new nullStmtsNode();
	
	void accept(Visitor u, int indent){ u.visit(this,indent);}

	void countDeclsAndUses(ScopeInfo currentScope){
	 // Count decls and uses in both subtrees:
		 thisStmt.countDeclsAndUses(currentScope);
		 moreStmts.countDeclsAndUses(currentScope);
		}
	void buildCrossReferences(LinkedList<Hashtable<String, IDInfo>> HashList){
		thisStmt.buildCrossReferences(HashList);
		moreStmts.buildCrossReferences(HashList);
	 }
};

class nullStmtsNode extends stmtsOption {
	nullStmtsNode(){};
	boolean   isNull(){return true;};

	void accept(Visitor u, int indent){ u.visit(this,indent);}
	
	void countDeclsAndUses(ScopeInfo currentScope){return;}
	
	void buildCrossReferences(LinkedList<Hashtable<String, IDInfo>> 
		HashList){return;}
};

class asgNode extends stmtNode {    

	public final identNode	target;
	public final exprNode 	source;
	
	asgNode(identNode n, exprNode e, int line, int col){       
		super(line,col);
		target=n;
		source=e;
	};
	
	void accept(Visitor u, int indent){ u.visit(this,indent);}

	void countDeclsAndUses(ScopeInfo currentScope){
		// The target of the assign counts as 1 use
		currentScope.usesCount +=1;
		// Visit the source expression to include the identifiers in it
		source.countDeclsAndUses(currentScope);
		}
	
	void buildCrossReferences(LinkedList<Hashtable<String, IDInfo>> HashList){		
		// Visit the source expression
		source.buildCrossReferences(HashList);
		// Visit the target expression
		target.buildCrossReferences(HashList);
	 }
};

class ifThenNode extends stmtNode {
	
	public final exprNode 		condition;
	public final stmtNode 		thenPart;
	public final stmtOption 	elsePart;
	
	ifThenNode(exprNode e, stmtNode s1, stmtOption s2, int line, int col){
		super(line,col);
		condition=e;
		thenPart=s1;
		elsePart=s2;
	};
	
	void accept(Visitor u, int indent){ u.visit(this,indent);}

	void countDeclsAndUses(ScopeInfo currentScope){
		// Count identifier uses in control expression and then statement.
		// In CSX Lite the else statement is always null
		condition.countDeclsAndUses(currentScope);
		thenPart.countDeclsAndUses(currentScope);
		}
	
	void buildCrossReferences(LinkedList<Hashtable<String, IDInfo>> HashList){
		condition.buildCrossReferences(HashList);
		thenPart.buildCrossReferences(HashList);
	}
};

class blockNode extends stmtNode {
	
	public final fieldDeclsOption 	decls;  
	public final stmtsOption 	stmts;
	
	blockNode(fieldDeclsOption f, stmtsOption s, int line, int col){
		super(line,col);
		decls=f;
		stmts=s;
	}
	
	 void accept(Visitor u, int indent){ u.visit(this,indent);}

	 void countDeclsAndUses(ScopeInfo currentScope){
		/* A block opens a new scope, so a new ScopeInfo node is created.
		   It is appended to the end of the ScopeInfo list.
		   The new scope is used to record local declarations and uses in the 
		   block
		*/ 
		 ScopeInfo  localScope = new ScopeInfo(linenum);
		 ScopeInfo.append(currentScope,localScope);
		 decls.countDeclsAndUses(localScope);
		 stmts.countDeclsAndUses(localScope);
	}
	 
	void buildCrossReferences(LinkedList<Hashtable<String,IDInfo>> HashList){		
		//create new Hashtable to add to front of HashList
		Hashtable<String, IDInfo> nextBlock = new Hashtable<String,IDInfo>();
		//place new Hashtable at the head of HashList
		HashList.addFirst(nextBlock);
		decls.buildCrossReferences(HashList);
		stmts.buildCrossReferences(HashList);
		
		//close this scope by removing the head
		HashList.removeFirst();
	}
};

//abstract superclass; only subclasses are actually created
abstract class exprOption extends ASTNode {
	exprOption(){super();};
	exprOption(int l,int c){super(l,c);};
};

// abstract superclass; only subclasses are actually created
abstract class exprNode extends exprOption {
	exprNode(){super();};
	exprNode(int l,int c){super(l,c);};
	static nullExprNode NULL = new nullExprNode();
};

class nullExprNode extends exprOption {
	nullExprNode(){super();};
	boolean   isNull(){return true;};
	void accept(Visitor u, int indent){}
};

class binaryOpNode extends exprNode {
	
	public final exprNode 	leftOperand;
	public final exprNode 	rightOperand;
	public final int	operatorCode; // Token code of the operator
	
	binaryOpNode(exprNode e1, int op, exprNode e2, int line, int col){
		super(line,col);
		operatorCode=op;
		leftOperand=e1;
		rightOperand=e2;
	};

	void accept(Visitor u, int indent){ u.visit(this,indent);}

	 // Count identifier uses in left and right operands
	 void countDeclsAndUses(ScopeInfo currentScope){
			leftOperand.countDeclsAndUses(currentScope);
			rightOperand.countDeclsAndUses(currentScope);
		}
	 
	 // Count identifier uses in left and right operands
	 void buildCrossReferences(LinkedList<Hashtable<String,IDInfo>> HashList){
		 leftOperand.buildCrossReferences(HashList);
		 rightOperand.buildCrossReferences(HashList);
	 }
};

class identNode extends exprNode {
	
	public final String 	idname;
	
	identNode(String identname, int line, int col){
		super(line,col);
		idname   = identname;
	};

	void accept(Visitor u, int indent){ u.visit(this,indent);}

	//One identifier used here:
	void countDeclsAndUses(ScopeInfo currentScope){
			currentScope.usesCount+=1;
		}
	
	//This method handles updating ID uses per line. It also handles undeclared 
	//identifiers by collecting them in the IDUndeclared global object
	void buildCrossReferences(LinkedList<Hashtable<String,IDInfo>> HashList){
		//look up the IDInfo per idname
		//use location 0 in usesarr to track total uses count
		
		//iterate through HashList, looking for first occurrence of idname
		ListIterator<Hashtable<String,IDInfo>> listIterator = 
				HashList.listIterator();
        boolean done = false;
        IDInfo ii = null;
		while (listIterator.hasNext() && !done) {
        	ii = listIterator.next().get(idname.toLowerCase());
        	//ignore if nothing was found or if type is "illegal"
        	if (ii != null && ii.type!="illegal"){
        		ii.usesarr[0] +=1;
        		done = true;
        	}
        }
		
		//if id wasn't found, add new IDInfo to IDUndeclared
		if (done == false){
			IDInfo newUndeclaredID = new IDInfo("[Undeclared Identifier] "+
					idname.toLowerCase(), 0, ASTNode.IDUndeclared, "illegal");
			newUndeclaredID.usesarr = new int[this.linenum + 1];
			newUndeclaredID.usesarr[this.linenum] += 1;
			ASTNode.IDUndeclared = newUndeclaredID;
		}
		
		//id was found so update usesarr
		else{
			//create a new array with the same size as the current line number+1
			int[] updateduses = new int[this.linenum + 1];
			//copy usesarr values into new array
			System.arraycopy(ii.usesarr, 0, updateduses, 0, ii.usesarr.length);
			//replace usesarr with the new, larger array
			ii.usesarr = updateduses;
			//increment the count for this ID at this line number
			ii.usesarr[this.linenum] += 1;
		}
	}
};


class intLitNode extends exprNode {
	public final int 	intval;
	intLitNode(int val, int line, int col){
		super(line,col);
		intval=val;
	}

	void accept(Visitor u, int indent){ u.visit(this,indent);}
};
