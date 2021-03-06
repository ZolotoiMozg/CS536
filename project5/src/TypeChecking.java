//Denzil Stefen Showers - student ID: 9062589552
//CS536 - Programming Assignment 4

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


// The following methods type check  AST nodes used in CSX Lite
//  You will need to complete the methods after line 238 to type check the
//   rest of CSX
//  Note that the type checking done for CSX lite may need to be extended to
//   handle full CSX (for example binaryOpNode).

public class TypeChecking extends Visitor { 

//	static int typeErrors =  0;     // Total number of type errors found 
//  public static SymbolTable st = new SymbolTable(); 	
	int typeErrors;     // Total number of type errors found 
	SymbolTable st;
	SymbolTable pt;
	SymbolTable methodSymbolTable;
	methodDeclNode currentMethod;
	Boolean atLeastOneMethod = false;
	List<ParmInfo> pList;
	
	TypeChecking(){
		typeErrors = 0;
		st = new SymbolTable(); 
	}
	
	boolean isTypeCorrect(csxLiteNode n) {
        	this.visit(n);
        	return (typeErrors == 0);
	}
	
	boolean isTypeCorrect(classNode n) {
    	this.visit(n);
    	return (typeErrors == 0);
	}
	
	boolean isScalar(ASTNode.Kinds kind){
		return (kind == ASTNode.Kinds.Var 
				||kind == ASTNode.Kinds.Value
				||kind == ASTNode.Kinds.ScalarParm);
	}
	
	boolean isArithmetic(ASTNode.Types type){
		return (type == ASTNode.Types.Character 
				||type == ASTNode.Types.Integer);
	}
	
	int countMatches(String str, String findStr){
		int lastIndex = 0;
		int count =0;

		while(lastIndex != -1){
		       lastIndex = str.indexOf(findStr,lastIndex);
		       if( lastIndex != -1){
		             count ++;
		             lastIndex+=findStr.length();
		      }
		}
		return count;
	}
	
	//helper method used to check if parameter list has already been declared
	boolean parmListExists(SymbolInfo m, List<ParmInfo> p){
		Boolean result = false;
		//if no entries in overLoadLists just return false
		if (m.overloadLists.size() > 0){
			List<ParmInfo> currList;
			ParmInfo currParm, pParm;
			Iterator<List<ParmInfo>> iter = m.overloadLists.iterator();
			//iterate through list of ParmInfo lists
			while (iter.hasNext()) {
				currList = iter.next();
				//only need to check if sizes are the same
				if (currList.size() == p.size()){
					result = true;
					Iterator<ParmInfo> iterc = currList.iterator();
					Iterator<ParmInfo> iterp = p.iterator();
					//iterate through ParmInfo lists and compare each entry
					while (iterc.hasNext() && iterp.hasNext() && result) {
						currParm = iterc.next();
						pParm = iterp.next();
						if (currParm.kind != pParm.kind || currParm.type != pParm.type){
							//mismatch found, so we can quit loop
							result = false;
						} /*else {
							result = true;
						}*/
					}
				}
			}
		}
		return result;
	}
	
	void argsCheck(SymbolInfo m, List<ParmInfo> p, ASTNode node){
		List<ParmInfo> currList;
		ParmInfo currParm, aParm;
		Boolean matchFound = false;
		
		//if overLoadLists has only one entry, we can do specific checks
		if (m.overloadLists.size() == 1){
			currList = m.overloadLists.get(0);
			//check that number of arguments is same as number of parameters
			if (currList.size() != p.size()){
				System.out.println("***TEST: currList.size()="+currList.size()+" and p.size()="+p.size());
				System.out.println(error(node)+m.name()+" requires "
						+currList.size()+" parameters.");
				typeErrors++;
			} else {
				matchFound = true;
				Iterator<ParmInfo> iterc = currList.iterator();
				Iterator<ParmInfo> iterp = p.iterator();
				//keep track of parameter number with this
				int n = 1;
				//iterate through ParmInfo lists and look for mismatch
				while (iterc.hasNext() && iterp.hasNext()) {
					currParm = iterc.next();
					aParm = iterp.next();
					//check if the kinds match
					//a variable, value, or scalarparm kind in an argument 
					//must match a scalarparm paramater in a parameter
					//an array or arrayparm kind in an argument 
					//must match an arrayparm parameter
					if (!((currParm.kind == ASTNode.Kinds.ScalarParm
						&& (aParm.kind == ASTNode.Kinds.Var || 
							aParm.kind == ASTNode.Kinds.Value || 
							aParm.kind == ASTNode.Kinds.ScalarParm))
					  || (currParm.kind == ASTNode.Kinds.ArrayParm
						&& (aParm.kind == ASTNode.Kinds.Array || 
							aParm.kind == ASTNode.Kinds.ArrayParm)))){
						//mismatch found, so we can quit loop
						System.out.println(error(node)+"In the call to "
							+m.name()+", parameter "+n+" has incorrect kind.");
						typeErrors++;
					}
					//type must be the same
					if (currParm.type != aParm.type){
						//mismatch found, so we can quit loop
						System.out.println(error(node)+"In the call to "
							+m.name()+", parameter "+n+" has incorrect type.");
						typeErrors++;
					}
					n++;
				}
			}
		}
		//otherwise there is overloading...
		else if (m.overloadLists.size() > 0){
			Iterator<List<ParmInfo>> iter = m.overloadLists.iterator();
			//iterate through list of ParmInfo lists until match found
			while (iter.hasNext() && !matchFound) {
				currList = iter.next();
	
				if (currList.size() == p.size()){
					//so far so good, so tentatively set matchFound to true
					matchFound = true;
					Iterator<ParmInfo> iterc = currList.iterator();
					Iterator<ParmInfo> iterp = p.iterator();
					//iterate through ParmInfo lists and look for mismatch
					while (iterc.hasNext() && iterp.hasNext() && matchFound) {
						currParm = iterc.next();
						aParm = iterp.next();
						//check if the kinds match
						//a variable, value, or scalarparm kind in an argument 
						//must match a scalarparm paramater in a parameter
						//an array or arrayparm kind in an argument 
						//must match an arrayparm parameter
						if (!((currParm.kind == ASTNode.Kinds.ScalarParm
							&& (aParm.kind == ASTNode.Kinds.Var || 
								aParm.kind == ASTNode.Kinds.Value || 
								aParm.kind == ASTNode.Kinds.ScalarParm))
						  || (currParm.kind == ASTNode.Kinds.ArrayParm
							&& (aParm.kind == ASTNode.Kinds.Array || 
								aParm.kind == ASTNode.Kinds.ArrayParm)))){
							//mismatch found, so we can quit loop
							matchFound = false;
						}
						//type must be the same
						if (currParm.type != aParm.type){
							//mismatch found, so we can quit loop
							matchFound = false;
						}
					}
				}
			}
			if (!matchFound){
				System.out.println(error(node)+"None of the "+m.overloadLists.size()
						+" definitions of method "+ m.name() 
						+" match the parameters in this call.");
				typeErrors++;
			}
		}
	}	
	
	static void assertCondition(boolean assertion){  
		if (! assertion)
			 throw new RuntimeException();
	}
	 void typeMustBe(ASTNode.Types testType,ASTNode.Types requiredType,String errorMsg) {
		 if ((testType != ASTNode.Types.Error) && (testType != requiredType)) {
                        System.out.println(errorMsg);
                        typeErrors++;
                }
        }
	 void typesMustBeEqual(ASTNode.Types type1,ASTNode.Types type2,String errorMsg) {
		 if ((type1 != ASTNode.Types.Error) && (type2 != ASTNode.Types.Error) &&
                     (type1 != type2)) {
                        System.out.println(errorMsg);
                        typeErrors++;
                }
        }
	 
	 void kindsMustBeEqual(ASTNode.Kinds kind1,ASTNode.Kinds kind2,String errorMsg) {
		 if (kind1 != kind2) {
                        System.out.println(errorMsg);
                        typeErrors++;
                }
        }
	 
	String error(ASTNode n) {
		return "Error (line " + n.linenum + "): ";
        }

	static String opToString(int op) {
		switch (op) {
			case sym.PLUS:
				return(" + ");
			case sym.MINUS:
				return(" - ");
			case sym.EQ:
				return(" == ");
			case sym.NOTEQ:
				return(" != ");			
			case sym.SLASH:
				return(" / ");
			case sym.TIMES:
				return(" * ");
			case sym.GT:
				return(" > ");
			case sym.LT:
				return(" < ");
			case sym.GEQ:
				return(" >= ");
			case sym.LEQ:
				return(" <= ");
			case sym.CAND:
				return(" && ");
			case sym.COR:
				return(" || ");
			case sym.NOT:
				return(" ! ");

			default:
				assertCondition(false);
				return "";
		}
	}

// Extend this to handle all CSX binary operators
	static void printOp(int op) {
		switch (op) {
			case sym.PLUS:
				System.out.print(" + ");
				break;
			case sym.MINUS:
				System.out.print(" - ");
				break;
			case sym.EQ:
				System.out.print(" == ");
				break;
			case sym.NOTEQ:
				System.out.print(" != ");
				break;
			case sym.SLASH:
				System.out.println(" / ");
				break;
			case sym.TIMES:
				System.out.println(" * ");
				break;
			case sym.GT:
				System.out.println(" > ");
			case sym.LT:
				System.out.println(" < ");
			case sym.GEQ:
				System.out.println(" >= ");
			case sym.LEQ:
				System.out.println(" <= ");
			case sym.CAND:
				System.out.println(" && ");
			case sym.COR:
				System.out.println(" || ");

			default:
				throw new Error();
		}
	}

	
	 void visit(csxLiteNode n){
		this.visit(n.progDecls);
		this.visit(n.progStmts);
	}
	
	void visit(fieldDeclsNode n){
			this.visit(n.thisField);
			this.visit(n.moreFields);
	}
	void visit(nullFieldDeclsNode n){}

	void visit(stmtsNode n){
		  //System.out.println ("In stmtsNode\n");
		  this.visit(n.thisStmt);
		  this.visit(n.moreStmts);

	}
	void visit(nullStmtsNode n){}

	//Extended varDeclNode's method from CSX lite to handle initialization
	void visit(varDeclNode n){

		SymbolInfo     id;
        	id = (SymbolInfo) st.localLookup(n.varName.idname);
        	if (id != null) {
               		 System.out.println(error(n) + id.name()+
                                     " is already declared.");
                	typeErrors++;
                	n.varName.type = ASTNode.Types.Error;

        	} else {
                	id = new SymbolInfo(n.varName.idname,
                                         ASTNode.Kinds.Var, n.varType.type);
                	n.varName.type = n.varType.type;
                	//check that initvalue type is correct if initValue exists
                	if (!n.initValue.isNull()){
                		this.visit(n.initValue);
                		//case n.initValue to exprNode to access type
                		if (((exprNode) n.initValue).type != n.varType.type){
                    		System.out.println(error(n) + "The initializer must"
                    			+ " be of type " + n.varType.type.toString());
                    		typeErrors++;
                        	n.varName.type = ASTNode.Types.Error;
                    	}
                		if (!isScalar(((exprNode) n.initValue).kind)){
                			System.out.println(error(n) + "The initializer must"
                        		+ " be scalar.");
                        	typeErrors++;
                            n.varName.type = ASTNode.Types.Error;
                		}
                	}
                	
			try {
                		st.insert(id);
			} catch (DuplicateException d) 
                              { /* can't happen */ }
			  catch (EmptySTException e) 
                              { /* can't happen */ }
                	n.varName.idinfo=id;
        	}

	};
	
	void visit(nullTypeNode n){}
	
	void visit(intTypeNode n){
		//no type checking needed
	}
	
	void visit(boolTypeNode n){
		//no type checking needed
	}
	
	void visit(identNode n){
		SymbolInfo    id;
        	assertCondition(n.kind == ASTNode.Kinds.Var
        			||n.kind == ASTNode.Kinds.Method  //added method
        			||n.kind == ASTNode.Kinds.Array); //added array
        	id = (SymbolInfo) st.globalLookup(n.idname);
        	if (id == null) {
               	 	System.out.println(error(n) +  n.idname +
                             " is not declared.");
                typeErrors++;
                n.type = ASTNode.Types.Error;
        } else {
                n.type = id.type;
                n.kind = id.kind;
                n.idinfo = id; // Save ptr to correct symbol table entry
        	}
	}

	//Extended nameNode's method from CSX lite to handle subscripts
	void visit(nameNode n){
		this.visit(n.varName);
		
		n.type=n.varName.type;
        n.kind=n.varName.kind;
        	
        if (!n.subscriptVal.isNull()){
        	this.visit(n.subscriptVal);
        	if (n.varName.kind != ASTNode.Kinds.Array && n.varName.kind != ASTNode.Kinds.ArrayParm){
        		System.out.println(error(n) + "Only arrays can be subscripted.");
        		n.type = ASTNode.Types.Error;
        		typeErrors++;
        	} else{
        		if (!isScalar(((exprNode)n.subscriptVal).kind)){
        			System.out.println(error(n) + "Subscripts must be a scalar.");
                	n.type = ASTNode.Types.Error;
                	typeErrors++;
        		}
                	
                if (((exprNode)n.subscriptVal).type != ASTNode.Types.Character
                    &&((exprNode)n.subscriptVal).type != ASTNode.Types.Integer){
                	System.out.println(error(n) + "Array subscripts must be integer or character expressions.");
                	n.type = ASTNode.Types.Error;
                	typeErrors++;
                }
        	}
        	n.kind = ASTNode.Kinds.Var;
        }
	}

	void visit(asgNode n){
		/*
		 * The types of an assignment statement's left- and right- hand sides 
		 * must be identical. Entire arrays may be assigned if they are have 
		 * the same size and component type. A string literal may be assigned 
		 * to a character array if both contain the same number of characters.
		 */
	
		this.visit(n.target);
		this.visit(n.source);
		
		//check that target's kind is assignable
		if (n.target.kind != ASTNode.Kinds.Var
			&&n.target.kind != ASTNode.Kinds.Array
			&&n.target.kind != ASTNode.Kinds.ScalarParm
			&&n.target.kind != ASTNode.Kinds.ArrayParm){
			System.out.println(error(n)+"Target of assignment can't be changed.");
			typeErrors++;
		}	
		
		//check that target's kind is scalar
		if (isScalar(n.target.kind) && isScalar(n.source.kind) 
				&& (n.target.type == n.source.type)){
			//all is well so do nothing
		} else {
			
			//check that the types match
			typesMustBeEqual(n.source.type, n.target.type,error(n) + "Right hand "
					+ "side of an assignment is not assignable to left hand side.");
			
			if (n.target.type == n.source.type &&
			    n.target.kind != n.source.kind &&
		     !((n.target.kind == ASTNode.Kinds.Array || 
		        n.target.kind == ASTNode.Kinds.ArrayParm) &&
		       (n.source.kind == ASTNode.Kinds.String ||
		        n.source.kind == ASTNode.Kinds.ArrayParm ||
		        n.source.kind == ASTNode.Kinds.Array)) &&
		      !(n.target.kind == ASTNode.Kinds.Var &&
		        n.source.kind == ASTNode.Kinds.ScalarParm)&&
		     !((n.source.kind == ASTNode.Kinds.Array || 
		        n.source.kind == ASTNode.Kinds.ArrayParm) &&
		       (n.target.kind == ASTNode.Kinds.String ||
		        n.target.kind == ASTNode.Kinds.ArrayParm ||
		        n.target.kind == ASTNode.Kinds.Array)) &&
			  !(n.source.kind == ASTNode.Kinds.Var &&
			    n.target.kind == ASTNode.Kinds.ScalarParm)){
				System.out.println(error(n) + "Right hand side of an assignment"
						+" is not assignable to left hand side.");
			}
			
			//check that arrays of same type have same length
			if (n.target.kind == ASTNode.Kinds.Array 
					&& n.source.kind == ASTNode.Kinds.Array
					&& n.target.type== n.source.type){
				//look up both the target and source in the symbol table
				SymbolInfo idT;

				idT = (SymbolInfo) st.globalLookup(n.target.varName.idname);
				SymbolInfo idS;
				idS = (SymbolInfo) st.globalLookup(((nameNode)n.source).varName.idname);
				if (idT.array.arraySize.intval != idS.array.arraySize.intval){
					System.out.println(error(n)+"Source and target of the "
							+ "assignment must have the same length.");
					typeErrors++;
				}
			}
			
			//check that char arrays have appropriate length if source is string
			if (n.target.kind == ASTNode.Kinds.Array
					&& n.target.type == ASTNode.Types.Character
					&& n.source.kind == ASTNode.Kinds.String
					&& n.source.type == ASTNode.Types.Character){
				SymbolInfo idT;

				idT = (SymbolInfo) st.globalLookup(n.target.varName.idname);
				
				//count up all the escaped characters in the source string
				int totalquotes = countMatches(((strLitNode)n.source).strval, "\"");
				int newlines = countMatches(((strLitNode)n.source).strval, "\\n");
				int tabs = countMatches(((strLitNode)n.source).strval, "\\t");
				int dquotes = countMatches(((strLitNode)n.source).strval, "\\\"");
				int squotes = countMatches(((strLitNode)n.source).strval, "\\\'");
				int backspaces = countMatches(((strLitNode)n.source).strval, "\\b");
				int returns = countMatches(((strLitNode)n.source).strval, "\\r");
				int formfeeds = countMatches(((strLitNode)n.source).strval, "\\f");
				String slashString = ((strLitNode)n.source).strval.replace("\\n","x");
				slashString = slashString.replace("\\t", "x");
				if (totalquotes > 2){slashString = slashString.replace("\\\"", "x");}
				slashString = slashString.replace("\\\'", "x");
				slashString = slashString.replace("\\b", "x");
				slashString = slashString.replace("\\r", "x");
				slashString = slashString.replace("\\f", "x");
				int slashes = countMatches(slashString, "\\\\");
				int subtract = newlines+tabs+slashes+dquotes+squotes
						+returns+formfeeds+backspaces;
				if (totalquotes == 2){subtract -= dquotes;}

				if (idT.array.arraySize.intval != ((strLitNode)n.source).strval.length()-subtract-2){
					System.out.println(error(n)+"Source and target of the "
							+ "assignment must have the same length.");
					typeErrors++;
				}
			}
		}
	}

	void visit(ifThenNode n){
		  this.visit(n.condition);
        	  typeMustBe(n.condition.type, ASTNode.Types.Boolean,
        		error(n) + "The control expression of an if must be a bool.");

		  this.visit(n.thenPart);
		  
		  //visit else part if not null
		  if (!n.elsePart.isNull()){
			  this.visit(n.elsePart);
		  }
	}

	//Extended to print bool and char types as well
	 void visit(printNode n){
		this.visit(n.outputValue);
        if (!((n.outputValue.type == ASTNode.Types.Boolean 
        		&& (n.outputValue.kind == ASTNode.Kinds.Value 
        		|| n.outputValue.kind == ASTNode.Kinds.Var
        		|| n.outputValue.kind == ASTNode.Kinds.ScalarParm))
        	||(n.outputValue.type == ASTNode.Types.Integer 
        		&& (n.outputValue.kind == ASTNode.Kinds.Value 
        		|| n.outputValue.kind == ASTNode.Kinds.Var
        		|| n.outputValue.kind == ASTNode.Kinds.ScalarParm))
        	||(n.outputValue.type == ASTNode.Types.Character 
        		&& (n.outputValue.kind == ASTNode.Kinds.Array 
        		|| n.outputValue.kind == ASTNode.Kinds.String 
        		|| n.outputValue.kind == ASTNode.Kinds.Value 
        		|| n.outputValue.kind == ASTNode.Kinds.Var)
        		|| n.outputValue.kind == ASTNode.Kinds.ScalarParm))){
        	System.out.println(error(n) + "Only integers, booleans, strings, "
        			+ "characters and character arrays may be written.");
        	typeErrors++;
        }
        this.visit(n.morePrints);
	  }
	  
	  void visit(blockNode n){
		// open a new local scope for the block body
			st.openScope();
			this.visit(n.decls);
			this.visit(n.stmts);
			// close this block's local scope
			try { st.closeScope();
			}  catch (EmptySTException e) 
	                      { /* can't happen */ }
	  }

	 void visit(binaryOpNode n){
		//Extended from CSX lite to handle all binary ops
		assertCondition(n.operatorCode==sym.PLUS||n.operatorCode==sym.MINUS 
        			|| n.operatorCode==sym.EQ||n.operatorCode==sym.NOTEQ			
        			|| n.operatorCode==sym.SLASH|| n.operatorCode==sym.TIMES
        			|| n.operatorCode==sym.GT || n.operatorCode==sym.LT
        			|| n.operatorCode==sym.GEQ || n.operatorCode==sym.LEQ
        			|| n.operatorCode==sym.CAND || n.operatorCode==sym.COR
        			
        			);
		this.visit(n.leftOperand);
		this.visit(n.rightOperand);
		
		n.kind = ASTNode.Kinds.Value;
		
		//both left and right operands must be a scalar
		if (!isScalar(n.leftOperand.kind)){
			System.out.println(error(n) + "Left operand of" 
					+ opToString(n.operatorCode) + "must be a scalar.");
			typeErrors++;
		}
		if (!isScalar(n.rightOperand.kind)){
			System.out.println(error(n) + "Right operand of" 
					+ opToString(n.operatorCode) + "must be a scalar.");
			typeErrors++;
		}
		
		//if +, -, *, or / check that left and right are arithmetic
        if (n.operatorCode== sym.PLUS||n.operatorCode==sym.MINUS    		
 					||n.operatorCode==sym.SLASH||n.operatorCode==sym.TIMES){
        	//set type to Integer if not already Error
        	if (n.type != ASTNode.Types.Error){n.type = ASTNode.Types.Integer;}
        	//check left operand is arithmetic
        	if (!isArithmetic(n.leftOperand.type)){
        		System.out.println(error(n) + "Left operand of" + 
        			opToString(n.operatorCode) + "must be arithmetic.");
        		typeErrors++;
        	}
        	//check right operand is arithmetic
        	if (!isArithmetic(n.rightOperand.type)){
        		System.out.println(error(n) + "Right operand of" + 
        			opToString(n.operatorCode) + "must be arithmetic.");
        		typeErrors++;
        	}
        }//if AND or OR check that left and right are both boolean type
        else if(n.operatorCode==sym.CAND||n.operatorCode==sym.COR){
        	//set type to Boolean if not already Error
        	if (n.type != ASTNode.Types.Error){n.type = ASTNode.Types.Boolean;}
        	//check left operand is bool
        	typeMustBe(n.leftOperand.type, ASTNode.Types.Boolean, error(n) 
            	    + "Left operand of" + opToString(n.operatorCode) 
            	    + "must be a bool.");
        	//check right operand is bool
        	typeMustBe(n.rightOperand.type, ASTNode.Types.Boolean, error(n) 
            	    + "Right operand of" + opToString(n.operatorCode) 
            	    + "must be a bool.");
        }//if rel op, check that both left and right are arithmetic or boolean

        else { // Must be a comparison operator
        	//set type to Boolean if not already Error
        	if (n.type != ASTNode.Types.Error){n.type = ASTNode.Types.Boolean;}

        	if (!((n.leftOperand.type == ASTNode.Types.Boolean 
        			&& n.rightOperand.type == ASTNode.Types.Boolean)
        			||(isArithmetic(n.leftOperand.type) 
        					&& isArithmetic(n.rightOperand.type)))){
        		System.out.println(error(n) + "Operands of" + 
        			opToString(n.operatorCode) + 
        			"must both be arithmetic or both must be boolean.");
        		typeErrors++;
        	}	
        }
	 }

	
	
	void visit(intLitNode n){
	//      All intLits are automatically type-correct
	}

 
// Extended these unparsing methods to correctly unparse CSX AST nodes
	 
	 void visit(classNode n){
		this.visit(n.members);
	 }

	 void  visit(memberDeclsNode n){
		this.visit(n.fields);
		this.visit(n.methods);
	 }
	 
	 void  visit(methodDeclsNode n){
		this.visit(n.thisDecl);
		this.visit(n.moreDecls);
		//check if this is main
		if (n.thisDecl.name.idname.equalsIgnoreCase("main")){
	    	//return type must be void
	    	if (!(n.thisDecl.returnType.type.name().equalsIgnoreCase("void"))){
	    		System.out.println(error(n)+"Main must have void return type.");
	    		typeErrors++;
	    	}
	    	//main can't have arguments
	    	if (!n.thisDecl.args.isNull()){
	    		System.out.println(error(n)+"Main cannot have arguments.");
	    		typeErrors++;
	    	}
	    	//can't have methods following
	    	if (!n.moreDecls.isNull()){
	    		System.out.println(error(n)+"Main must be last method.");
	    		typeErrors++;
	    	}
	    }
	    
	    //check if this is last but not main
	    if (n.moreDecls.isNull() 
	    		&& !(n.thisDecl.name.idname.equalsIgnoreCase("main"))){
	    	//must be main
	    	System.out.println(error(n)+"Main method must be declared.");
	    	typeErrors++;
	    }
	 }
	 
	 void visit(nullStmtNode n){}
	 
	 void visit(nullReadNode n){}

	 void visit(nullPrintNode n){}

	 void visit(nullExprNode n){}

	 void visit(nullMethodDeclsNode n){
		 if(atLeastOneMethod == false){
			//check that there is a main declared 
			//if current method is still null, we know main was never declared
			 System.out.println("Error (line 0): Main method must be declared.");
			 typeErrors++;
		 }
	 }

	 void visit(methodDeclNode n){
		 
		//set atLeastOneMethod, used by main check in nullMethodDeclsNode
		atLeastOneMethod = true;
		 
		SymbolInfo     m;
		m = (SymbolInfo) st.localLookup(n.name.idname);

		//check to see if another non-method id already exists with this name
	    if (m != null && m.kind != ASTNode.Kinds.Method){
	    	System.out.println(error(n)+m.name()+" is already declared.");
	    	typeErrors++;
	    } else {
	    	if (m == null){
	    		//add new 
		    	m = new SymbolInfo(n.name.idname,ASTNode.Kinds.Method, n.returnType.type);
		        
		        try {
		        	st.insert(m);
		        } 
		        catch (DuplicateException d) 
		    		{ /* can't happen */ }
		        catch (EmptySTException e) 
		      		{ /* can't happen */ }
	    	}
	    	
	    	//even if m was found, it may be OK b/c overloading is allowed
	    
	    	//update n.name type and idinfo
	    	n.name.type = n.returnType.type;
	    	n.name.idinfo=m;
	    
	    	//open new scope
	    	st.openScope();
	    
	    	//set currentMethod
	    	currentMethod = n;
	    
	    	//reset parameter list
	    	pList = new LinkedList<ParmInfo>();
	    
	    	//reset the parameter symbol table
	    	pt = new SymbolTable();
	    
	    	//visit argDeclsNode and build list of symbol table nodes 
	    	//corresponding to args and store in m
	    	this.visit(n.args);
	    
	    	//after visiting argDeclsNode(s), pList should be built out

	    	//if a match is found, print an error
	    	if (parmListExists(m,pList)){
	    		//if (m.overloadLists.contains(pList)){
	    		System.out.println(error(n)+m.name()+" is already declared with"
	    			+ " these parameters.");
	    		typeErrors++;
	    		n.name.type = ASTNode.Types.Error;
	    	} else {
	    		//else add ParmInfo List to overloadList
	    		m.overloadLists.add(pList);
	    	}
	    	//continue type checking decls and stmts either way  
	    	this.visit(n.decls);
	    	this.visit(n.stmts);
			
	    	//close scope
	    	try { st.closeScope();
	    	}  catch (EmptySTException e) 
	        	{ /* can't happen */ }

	    	//reset currentMethod
	    	currentMethod = null;
	    	
	    }
	 }
	 
	 void visit(incrementNode n){
		 	this.visit(n.target);
	        assertCondition(n.target.kind == ASTNode.Kinds.Var 
	        		|| n.target.kind == ASTNode.Kinds.Method 
	        		|| n.target.kind == ASTNode.Kinds.Value
	        		|| n.target.kind == ASTNode.Kinds.Array);
//TODO: can be Integer or Char ...?
			//check that target is arithmetic
			if ((n.target.type != ASTNode.Types.Integer) && 
			    (n.target.type != ASTNode.Types.Character)){
				System.out.println(error(n)+"Operand of ++ must be arithmetic.");
				typeErrors++;
			}
			
			//check to see if target isn't scalar
			if (!isScalar(n.target.kind)){
				System.out.println(error(n)+"Operand of ++ must be a scalar.");
				typeErrors++;
			}

			//check that the type is not const... i.e. kind==value (or method?)
			if (n.target.kind == ASTNode.Kinds.Value 
					|| n.target.kind == ASTNode.Kinds.Method){
				System.out.println(error(n)+"Target of ++ can't be changed.");
		        typeErrors++;
			}
	 }
	 
	 void visit(decrementNode n){
		 	this.visit(n.target);
	        assertCondition(n.target.kind == ASTNode.Kinds.Var 
	        		|| n.target.kind == ASTNode.Kinds.Method 
	        		|| n.target.kind == ASTNode.Kinds.Value
	        		|| n.target.kind == ASTNode.Kinds.Array);

			//check that target is arithmetic
			if (n.target.type != ASTNode.Types.Integer){
				System.out.println(error(n)+"Operand of -- must be arithmetic.");
				typeErrors++;
			}
			
			//check to see if target isn't scalar
			if (!isScalar(n.target.kind)){
				System.out.println(error(n)+"Operand of -- must be a scalar.");
				typeErrors++;
			}

			//check that the type is not const... i.e. kind == value
			if (n.target.kind == ASTNode.Kinds.Value 
					|| n.target.kind == ASTNode.Kinds.Method){
				System.out.println(error(n)+"Target of -- can't be changed.");
		        typeErrors++;
			}
	 }

	//called when visiting methodDeclNode
	//contains call to either valArgDeclNode or arrayArgDeclNode 
	//and call to either argDeclsNode or nullArgDeclsNode
	void visit(argDeclsNode n){
		this.visit(n.thisDecl);
		this.visit(n.moreDecls);
	}

	void visit(nullArgDeclsNode n){}

	//called when visiting argDeclsNode
	void visit(valArgDeclNode n){
		this.visit(n.argType);
		
		SymbolInfo     id;
		id = (SymbolInfo) pt.localLookup(n.argName.idname);
	    if (id != null) {
	    	System.out.println(error(n)+id.name()+" is already a parameter of " + currentMethod.name.idname + ".");
	        typeErrors++;

	        n.argType.type = ASTNode.Types.Error;
	    } else {
	    	id = new SymbolInfo(n.argName.idname,ASTNode.Kinds.ScalarParm, 
	    			n.argType.type);

	        n.argName.type = n.argType.type;
        
	        //argType must be Boolean, Integer, or Character and kind must be a scalar
	        if (!((n.argType.type == ASTNode.Types.Boolean 
	        		|| n.argType.type == ASTNode.Types.Character 
	        		|| n.argType.type == ASTNode.Types.Integer)
	        		&& (isScalar(id.kind)||id.kind == ASTNode.Kinds.ArrayParm))){
	        	System.out.println(error(n) + "Parameter " + n.argName.idname + 
	        			" has wrong type and/or kind.");
	        	typeErrors++;
	        }
//TODO	figure out how to add to pList (valArgDeclNode)
	        /*
	        SymbolInfo     m;
			m = (SymbolInfo) st.globalLookup(currentMethod.name.idname);
	        
	      //if this doesn't already exist in the current parameter symbol table
		  m.pList.add(new ParmInfo(n.argName.idname,id.kind,n.argType.type));
		  */
//controls how many arguments a method will expect...	
	//	  currentMethod.pList.add(new ParmInfo(n.argName.idname,id.kind,n.argType.type));
        
	        pList.add(new ParmInfo(n.argName.idname,id.kind,n.argType.type));
	    }
 	
		try {
			pt.insert(id);
		} 
		catch (DuplicateException d) 
	    	{ /* can't happen */ }
		catch (EmptySTException e) 
	      	{ /* can't happen */ }

		n.argName.idinfo=id;
		
		//insert in symbol table as well
		try {
			st.insert(id);
		} 
		catch (DuplicateException d) 
	    	{ /* can't happen */ }
		catch (EmptySTException e) 
	      	{ /* can't happen */ }
		
	    
	}
	
	//called when visiting argDeclsNode
	void visit(arrayArgDeclNode n){
		this.visit(n.elementType);
		
		SymbolInfo     id;
		id = (SymbolInfo) pt.localLookup(n.argName.idname);
	    if (id != null) {
	    	System.out.println(error(n)+id.name()+" is already a parameter of " + currentMethod.name.idname + ".");
	        typeErrors++;

	        n.elementType.type = ASTNode.Types.Error;
	    } else {
	    	id = new SymbolInfo(n.argName.idname,ASTNode.Kinds.ArrayParm, 
	    			n.elementType.type);

	        n.argName.type = n.elementType.type;
        
	        //argType must be Boolean, Integer, or Character and kind must be a scalar
	        if (!((n.elementType.type == ASTNode.Types.Boolean 
	        		|| n.elementType.type == ASTNode.Types.Character 
	        		|| n.elementType.type == ASTNode.Types.Integer)
	        		&& (isScalar(id.kind)||id.kind == ASTNode.Kinds.ArrayParm))){
	        	System.out.println(error(n) + "Parameter " + n.argName.idname + 
	        			" has wrong type and/or kind.");
	        	typeErrors++;
	        }
//TODO	figure out how to add to pList (arrayArgDeclNode)
	        /*
	        SymbolInfo     m;
			m = (SymbolInfo) st.globalLookup(currentMethod.name.idname);
	        
	      //if this doesn't already exist in the current parameter symbol table
		  m.pList.add(new ParmInfo(n.argName.idname,id.kind,n.elementType.type));
	       */
//controls how many arguments a method will expect...
	//	  currentMethod.pList.add(new ParmInfo(n.argName.idname,id.kind,n.elementType.type));
		  
	        pList.add(new ParmInfo(n.argName.idname,id.kind,n.elementType.type));
	    
	    }
 	
		try {
			pt.insert(id);
		} 
		catch (DuplicateException d) 
	    	{ /* can't happen */ }
		catch (EmptySTException e) 
	      	{ /* can't happen */ }

		n.argName.idinfo=id;
		
		
		//insert in symbol table as well
		try {
			st.insert(id);
		} 
		catch (DuplicateException d) 
			{ /* can't happen */ }
		catch (EmptySTException e) 
			{ /* can't happen */ }
		
	}
	
	void visit(constDeclNode n){
		SymbolInfo     id;
			id = (SymbolInfo) st.localLookup(n.constName.idname);
		    if (id != null) {
		    	System.out.println(error(n)+id.name()+" is already declared.");
		        typeErrors++;
		        n.constName.type = ASTNode.Types.Error;
		    } else {
		    	id = new SymbolInfo(n.constName.idname,ASTNode.Kinds.Value, 
		    			n.constValue.type);
		        n.constName.type = n.constValue.type;
		        //check that initvalue type is correct if initValue exists
		        if (!n.constValue.isNull()){
		        	this.visit(n.constValue);
		        	//case n.initValue to exprNode to access type
		        	if (((exprNode) n.constValue).type != n.constValue.type){
		        	System.out.println(error(n) + "The initializer must"
		        			+ " be of type " + n.constValue.type.toString());
		        	
		        	typeErrors++;
                	n.constName.type = ASTNode.Types.Error;
		        	}
		        	
		    }
     	
			try {
				st.insert(id);
			} 
			catch (DuplicateException d) 
		    	{ /* can't happen */ }
			catch (EmptySTException e) 
		      	{ /* can't happen */ }
		    n.constName.idinfo=id;
		    }
	}
	 
	void visit(arrayDeclNode n){	 
		SymbolInfo     id;
		id = (SymbolInfo) st.localLookup(n.arrayName.idname);
		if (id != null) {
			System.out.println(error(n)+id.name()+" is already declared.");
			typeErrors++;
			n.arrayName.type = ASTNode.Types.Error;
		} else {
			//added a pointer field for the arrayDeclNode itself
			id = new SymbolInfo(n.arrayName.idname,ASTNode.Kinds.Array, 
				n.elementType.type,n);
			n.arrayName.type = n.elementType.type;
			n.arrayName.kind = ASTNode.Kinds.Array;

			//check that there are more than 0 elements
			if (n.arraySize.intval < 1){
			System.out.println(error(n) + n.arrayName.idname.toString() 
				+ " must have more than 0 elements.");       
			typeErrors++;
	        n.arrayName.type = ASTNode.Types.Error;
			}
			
			try {
				st.insert(id);
			} 
			catch (DuplicateException d) 
		    	{ /* can't happen */ }
			catch (EmptySTException e) 
		      	{ /* can't happen */ }
		    n.arrayName.idinfo=id;
		}
	 }
	
	void visit(charTypeNode n){
		//no type checking needed
	}
	void visit(voidTypeNode n){
		//no type checking needed
	}

	void visit(whileNode n){
		//visit condition
		this.visit(n.condition);
		
		//check that condition type is boolean
		if(n.condition.type != ASTNode.Types.Boolean){
			System.out.println(error(n)+"Condition must be boolean.");
			typeErrors++;
		}
		
		//check that condition kind is scalar
		if(!isScalar(n.condition.kind)){
			System.out.println(error(n)+"Condition must be a scalar.");
			typeErrors++;
		}
		
		//if label is non-null...
		if(!n.label.isNull()){
			
			//check to see if label is already present in symbol table
			SymbolInfo     id;
			id = (SymbolInfo) st.localLookup(((identNode)n.label).idname);
		    if (id == null) {
		    	//if it isn't, enter label in symbol table
		    	id = new SymbolInfo(((identNode)n.label).idname,
                      ASTNode.Kinds.VisibleLabel, ASTNode.Types.Void);
		    	
		    	//insert id
		    	try {
					st.insert(id);
				} 
				catch (DuplicateException d) 
			    	{ /* can't happen */ }
				catch (EmptySTException e) 
			      	{ /* can't happen */ }
		    	
		    	//save pointer to id in the label
		    	((identNode)n.label).idinfo = id;
		    }
			
			//type check stmtNode
			this.visit(n.loopBody);
			
			//change label's kind=HiddenLabel
			id.kind = ASTNode.Kinds.HiddenLabel;

			//insert id again with updated kind
	    	try {
				st.insert(id);
			} 
			catch (DuplicateException d) 
		    	{ /* can't happen */ }
			catch (EmptySTException e) 
		      	{ /* can't happen */ }
	    	
		} else {
			//else just check stmtNode
			this.visit(n.loopBody);
		}
		
		
	}


	void visit(breakNode n){
		
		//look up identNode in symbol table
		SymbolInfo     id;
		id = (SymbolInfo) st.globalLookup(((identNode)n.label).idname);
		
		if (id == null){
			//if doesn't exisit, print error
			System.out.println(error(n) + n.label.idname + " is not declared.");
			typeErrors++;
		} else if (id.kind == ASTNode.Kinds.HiddenLabel){
			//if identNode's kind is HiddenLabel, print error
			System.out.println(error(n) + n.label.idname + " doesn't label an "
					+ "enclosing while loop.");
			typeErrors++;
		} else if (id.kind != ASTNode.Kinds.VisibleLabel){
			//identNode isn't a label
			System.out.println(error(n) + n.label.idname + " isn't a label.");
			typeErrors++;
		}
		//identNode's kind is VisibleLable
		//store pointer to label's st entry
		n.label.idinfo = id;
	}
	
	void visit(continueNode n){
		
		//look up identNode in symbol table
		SymbolInfo     id;
		id = (SymbolInfo) st.globalLookup(((identNode)n.label).idname);

		if (id == null){
			//if doesn't exisit, print error
			System.out.println(error(n) + n.label.idname + " is not declared.");
			typeErrors++;
		} else if (id.kind == ASTNode.Kinds.HiddenLabel){
			//if identNode's kind is HiddenLabel, print error
			System.out.println(error(n) + n.label.idname + " doesn't label an "
					+ "enclosing while loop.");
			typeErrors++;
		} else if (id.kind != ASTNode.Kinds.VisibleLabel){
			//identNode isn't a label
			System.out.println(error(n) + n.label.idname + " isn't a label.");
			typeErrors++;
		}
		//identNode's kind is VisibleLable
		//store pointer to label's st entry
		n.label.idinfo = id;
	}
	  
	void visit(callNode n){
		//check that identNode.idname is declared in symbol tree
		SymbolInfo     m;

		m = (SymbolInfo) st.globalLookup(n.methodName.idname);

		if (m == null){
			//if doesn't exisit, print error
			System.out.println(error(n) + n.methodName.idname + 
					" is not declared.");
			typeErrors++;
		} else {
			//its type should be void and kind should be method
			if (m.kind != ASTNode.Kinds.Method){
				System.out.println(error(n) + n.methodName.idname + " isn't"
				  +" a method.");
				typeErrors++;
			} else if (m.type != ASTNode.Types.Void){
				System.out.println(error(n) + n.methodName.idname 
				  +" is called as a procedure and must therefore return void.");
				typeErrors++;
			}
			
			//reset parameter list
		    pList = new LinkedList<ParmInfo>();
		    
		    m.pList = new LinkedList<ParmInfo>();
		    
		    currentMethod.pList = new LinkedList<ParmInfo>();
			
			//type check the args subtree
			this.visit(n.args);

			//check that the arguments list matches one of the parameter lists
//TODO: fix pList stuff so it is local to m...  for codegen test-37 and test-41
			//argsCheck(m,pList,n);
			//argsCheck(m,m.pList,n);
			//argsCheck(m,currentMethod.pList,n);
			
		}
		
		//store m to methodName's idinfo field
		n.methodName.idinfo = m;
	}

	  void visit(readNode n){
		//Only integers and characters may be read.
		this.visit(n.targetVar);
		if (n.targetVar.type != ASTNode.Types.Character &&
			n.targetVar.type != ASTNode.Types.Integer){
			System.out.println(error(n) + "Only integers and characters may be "
					+ "read.");
			typeErrors++;
		}
		if (n.targetVar.kind != ASTNode.Kinds.Var){
			System.out.println(error(n) + n.targetVar.varName.idname 
					+" may not be assigned to.");
			typeErrors++;
		}
		
		this.visit(n.moreReads);
	  
	  }
	  
	  
	  void visit(returnNode n){
		this.visit(n.returnVal);
		
		//if return val is null check that current method has type Void
		if(n.returnVal.isNull()){
			typeMustBe(currentMethod.returnType.type, ASTNode.Types.Void, 
					error(n)+"Return type of "+currentMethod.name.idname
					+" is not void.");	
		} else {
			//check that kind is scalar
			if (!isScalar(((exprNode)n.returnVal).kind)){
				System.out.println(error(n) + "Return value must be a scalar.");
				typeErrors++;
			}
			//check that return val type matche's current method
			typesMustBeEqual(((exprNode)n.returnVal).type,
					currentMethod.returnType.type,error(n)+"Return type of "+
							currentMethod.name.idname+" is "+
							currentMethod.returnType.type.name()+".");
		}
	  }

	  //called when visiting callNode or fctCallNode
	  //contains expr and either another argsNode or nullArgsNode
	  void visit(argsNode n){
		//build a list of the expression nodes found in the args subtree
		this.visit(n.argVal);
//TODO	figure out how to add to pList (argsNode)
		/*
		SymbolInfo     m;
		m = (SymbolInfo) st.globalLookup(currentMethod.name.idname);
		
		//save this as pList
		m.pList.add(new ParmInfo(n.argVal.toString(),n.argVal.kind,n.argVal.type));
		*/
		
	//	currentMethod.pList.add(new ParmInfo(n.argVal.toString(),n.argVal.kind,n.argVal.type));

//controls how many parameters a call to a method has		

		pList.add(new ParmInfo(n.argVal.toString(),n.argVal.kind,n.argVal.type));

		this.visit(n.moreArgs);
	  }
	  	
	  void visit(nullArgsNode n){}
		
	  void visit(castNode n){

		this.visit(n.resultType);
		this.visit(n.operand);
		if ((n.operand.type != ASTNode.Types.Boolean
				&& n.operand.type != ASTNode.Types.Character
				&& n.operand.type != ASTNode.Types.Integer)
				|| !isScalar(n.operand.kind)){
			System.out.println(error(n) + "Operand of cast must be an integer, "
					+ "character or boolean.");
			typeErrors++;
		} else {
			n.type = n.resultType.type;
			n.kind = n.operand.kind;
		}		
	  }

	  void visit(fctCallNode n){
		//check that identNode.idname is declared in symbol tree
		SymbolInfo     m;

		m = (SymbolInfo) st.globalLookup(n.methodName.idname);

		if (m == null){
			//if doesn't exisit, print error
			System.out.println(error(n) + n.methodName.idname + 
					" is not declared.");
			typeErrors++;
		} else {
			//its type should not be void and its kind should be method			
			
			//need to check the method's returnType not the fctCallNode type
			
			if (m.kind != ASTNode.Kinds.Method){
				System.out.println(error(n) + n.methodName.idname + " isn't"
				  + " a method.");
				typeErrors++;
			} else if (!(m.type == ASTNode.Types.Boolean
				|| m.type == ASTNode.Types.Character
				|| m.type == ASTNode.Types.Integer)){
				System.out.println(error(n) + n.methodName.idname 
				  +" is called as a function and must therefore return boolean,"
				  + " character or integer.");				
				typeErrors++;
			}
			
			//reset parameter list
		    pList = new LinkedList<ParmInfo>();
		    
		    m.pList = new LinkedList<ParmInfo>();
		    
		    currentMethod.pList = new LinkedList<ParmInfo>();
			
			//type check the args subtree
			this.visit(n.methodArgs);

			//check that the arguments list matches one of the parameter lists
//TODO: fix the pList stuff so it's locla to m (m.pList instead)... for codegen test-37 and test-41
			//argsCheck(m,pList,n);
			//argsCheck(m,m.pList,n);
			//argsCheck(m,currentMethod.pList,n);
			
			//set the type and kind of this node
			n.type = m.type;
			n.kind = ASTNode.Kinds.Value;
		}
		
		//store m to methodName's idinfo field
		n.methodName.idinfo = m;
	  }

	  void visit(unaryOpNode n){
		  
		  assertCondition(n.operatorCode==sym.NOT);

		  this.visit(n.operand);
		  n.type = ASTNode.Types.Boolean;
		  n.kind = ASTNode.Kinds.Value;
		  typeMustBe(n.operand.type, ASTNode.Types.Boolean, error(n)+
				 "Operand of"+opToString(n.operatorCode)+"must be boolean.");
	  }

	void visit(charLitNode n){
		SymbolInfo     id;
		id = (SymbolInfo) st.localLookup(n.toString());
	    if (id == null) {
	    	id = new SymbolInfo(n.toString(),ASTNode.Kinds.Value, ASTNode.Types.Character);
	    	n.type = ASTNode.Types.Character;
	    	n.kind = ASTNode.Kinds.Value;
		try {
			st.insert(id);
		} 
		catch (DuplicateException d) 
	    	{ /* can't happen */ }
		catch (EmptySTException e) 
	      	{ /* can't happen */ }
	    }
	    n.type = id.type;
	    n.kind = id.kind;
	}
	  
	void visit(strLitNode n){
		SymbolInfo     id;
		id = (SymbolInfo) st.localLookup(n.strval);
	    if (id == null) {
	    	id = new SymbolInfo(n.strval,ASTNode.Kinds.String, ASTNode.Types.Character);
	    	n.type = ASTNode.Types.Character;
	    	n.kind = ASTNode.Kinds.String;
		try {
			st.insert(id);
		} 
		catch (DuplicateException d) 
	    	{/* can't happen */ }
		catch (EmptySTException e) 
	      	{ /* can't happen */ }
	    }
	    n.type = id.type;
	    n.kind = id.kind;
	}

	
	void visit(trueNode n){
		assertCondition(n.kind == ASTNode.Kinds.Value 
				&& n.type == ASTNode.Types.Boolean);
		n.type = ASTNode.Types.Boolean;
		n.kind = ASTNode.Kinds.Value;
	}

	void visit(falseNode n){
		assertCondition(n.kind == ASTNode.Kinds.Value 
				&& n.type == ASTNode.Types.Boolean);
		n.type = ASTNode.Types.Boolean;
		n.kind = ASTNode.Kinds.Value;
	}


}
