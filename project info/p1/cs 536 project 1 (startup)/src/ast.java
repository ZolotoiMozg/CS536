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
	
	ASTNode(){linenum=-1;colnum=-1;}
	ASTNode(int l,int c){linenum=l;colnum=c;}
	boolean   isNull(){return false;}; // Is this node null?

    	abstract void accept(Visitor v, int indent);// Will be defined in sub-classes    

	// default action on an AST node is to record no declarations and no identifier uses
	 void countDeclsAndUses(ScopeInfo currentScope){ 
		return;
	}

};



// This node is used to root only CSXlite programs 
class csxLiteNode extends ASTNode {
	
   	public final fieldDeclsOption	progDecls;
	public final stmtsOption 	progStmts;
	private ScopeInfo  		 scopeList;
	
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
};

class nullFieldDeclsNode extends fieldDeclsOption {
	
	nullFieldDeclsNode(){};

	boolean   isNull(){return true;};

	void accept(Visitor u, int indent){ u.visit(this,indent);}

	void countDeclsAndUses(ScopeInfo currentScope){
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

	// This node represents a variable declaration, so we increment the declarations
	//  count by 1
	void countDeclsAndUses(ScopeInfo currentScope){
		currentScope.declsCount+=1;
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
	//static nullStmtNode NULL = new nullStmtNode();
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
};


class nullStmtsNode extends stmtsOption {
	nullStmtsNode(){};
	boolean   isNull(){return true;};

	void accept(Visitor u, int indent){ u.visit(this,indent);}
	
	void countDeclsAndUses(ScopeInfo currentScope){return;}

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
		   The new scope is used to record local declarations and uses in the block
		*/ 
		 ScopeInfo  localScope = new ScopeInfo(linenum);
		 ScopeInfo.append(currentScope,localScope);
		 decls.countDeclsAndUses(localScope);
		 stmts.countDeclsAndUses(localScope);
	}
};


//abstract superclass; only subclasses are actually created
abstract class exprOption extends ASTNode {
	exprOption(){super();};
	exprOption(int l,int c){super(l,c);};
	//static nullStmtNode NULL = new nullStmtNode();
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
};


class intLitNode extends exprNode {
	public final int 	intval;
	intLitNode(int val, int line, int col){
		super(line,col);
		intval=val;
	}

	void accept(Visitor u, int indent){ u.visit(this,indent);}
};
