//Denzil Stefen Showers - student ID: 9062589552
//CS536 - Programming Assignment 5

import java.io.*;
/*
 *  This Visitor class generates JVM assembler code (using Jasmin's format)
 *  for CSX lite in the Printstream afile. You'll need to extend it to
 *  handle all of CSX. Note that for some AST nodes (like asgNode) code 
 *  generation for CSX is more complex than that needed for CSX lite.
 */

public class CodeGenerating extends Visitor {
	
	PrintStream afile;			// File to generate JVM code into 

	int cgErrors =  0;       	// Total number of code generation errors 

	int numberOfLocals =  0; 	// Total number of local CSX-lite vars

	int labelCnt = 0;			// counter used to generate unique labels
	
	methodDeclNode currentMethod;
	
	String CLASS;
	
	
	CodeGenerating(PrintStream f){
		afile=f;
	}

	
	static void assertCondition(boolean assertion){
		if (! assertion)
			throw new RuntimeException();
	}

	
	String error(ASTNode n) {
		return "Error (line " + n.linenum + "): ";
    }

	
	// generate a comment
	void genComment(String text){
       	gen("; "+text);
	}
	
	
	void gen() {
		afile.println();
	}

	
	// generate an instruction w/ 0 operands
	void gen(String opcode){
        afile.println("\t"+opcode);
	}

	
    // generate an instruction w/ 1 operand
	void gen(String opcode, String operand){
        afile.println("\t"+opcode+"\t"+operand);
	}

	
    // generate an instruction w/ 1 integer operand
	void gen(String opcode, int operand){
		afile.println("\t"+opcode+"\t"+operand);
	}

	
	//  generate an instruction w/ 2 operands
	void gen(String opcode, String operand1, String operand2){
        afile.println("\t"+opcode+"\t"+ operand1+"  "+ operand2);
	}

	
	//  generate an instruction w/ 2 operands (String and int)
	void gen(String opcode, String operand1, int operand2){
        afile.println("\t"+opcode+"\t"+ operand1+"  "+operand2);
	}

	
	//	Generate a new label of form Ln (e.g., L7 or L123)	
	String genLab(){
	     return "L"+labelCnt++;
	}

	
	//	Place a label in generated code
	void defineLab(String label){
		// Generate:
	    // label:
        afile.println(label+":");
	}
	
	
	void branch(String label){
		// Generate unconditional branch to label:
	     // goto label
		gen("goto",label);
	}
	
	
	void loadI(int val){
		// generate ldc call (local value)
		gen("ldc",val);
	}
	
	
	void loadIvar(int val){
		//generate iload call (local variable)
		gen("iload",val);
	}
	
	
	void loadGlobalInt(String name){
		//Generate a load of an int static field onto the stack:
		//	getstatic CLASS/name I
		gen("getstatic",CLASS+"/"+name,"I");
	}
	
	
	void loadLocalInt(int index){
		loadIvar(index);
	}
	
	
	void binOp(String op){
		//Generate a binary operation; all operands are on the stack:
		//	op
		gen(op);
	}
	
	
	void storeGlobalInt(String name){
		//Generate a store into an int static field from the stack:
		// "I" below is the type code for integer
		//	putstatic CLASS/name I
		gen("putstatic",CLASS+"/"+name,"I");
	}
	
	
	void storeLocalInt(int index){
		//Generate a store to an int local variable from the stack:
		gen("istore",index);
	}
	
	
	// Compute address associated w/ name node
	// DON'T load the value addressed onto the stack
	void computeAdr(nameNode name) { // Final version
		if (name.subscriptVal.isNull()) {
			// Simple (unsubscripted) identifier
	        if (name.varName.idinfo.kind == ASTNode.Kinds.Var ||
	            name.varName.idinfo.kind == ASTNode.Kinds.ScalarParm) {
	        	// id is a scalar variable
	            if (name.varName.idinfo.adr == ASTNode.Ads.global) {
	            	name.adr = ASTNode.Ads.global;
	                name.label = name.varName.idinfo.label;
	            } else { // varName.idinfo.adr == Local
	                name.adr = ASTNode.Ads.local;
	                name.varIndex =
	                name.varName.idinfo.varIndex;
	            }
	        } else { // Must be an array
	        	// Push ref to target array to check length
	            if (name.varName.idinfo.adr == ASTNode.Ads.global){
	            	name.label = name.varName.idinfo.label;
	                loadGlobalReference(name.label,
	                arrayTypeCode(name.varName.idinfo.type));
	            } else { // (name.varName.idinfo.adr == local)
	                name.varIndex = name.varName.idinfo.varIndex;
	                loadLocalReference(name.varIndex);
	            }
	        }
		} else { // This is subscripted variable
			// Push array reference first
			if (name.varName.idinfo.adr == ASTNode.Ads.global){
				name.label = name.varName.idinfo.label;
				loadGlobalReference(name.label,
		        arrayTypeCode(name.varName.idinfo.type));
			} else { // (name.varName.idinfo.adr == local)
				name.varIndex = name.varName.idinfo.varIndex;
				loadLocalReference(name.varIndex);
			} // Next compute subscript expression
			this.visit(name.subscriptVal);
		}
	}
	
	
	void storeId(identNode id) {
		if (id.idinfo.kind == ASTNode.Kinds.Var ||
		    id.idinfo.kind == ASTNode.Kinds.Value ) {
			// id is a scalar variable
		    if (id.idinfo.adr == ASTNode.Ads.global) // ident is a global
		    	storeGlobalInt(id.idinfo.label);
		    else // (id.idinfo.adr == local)
		        storeLocalInt(id.idinfo.varIndex);
		} //Handle arrays elsewhere
		else{}
	}

	
	void storeName(nameNode name) { // Final version
		  if (name.subscriptVal.isNull()) {
		  // Simple (unsubscripted) identifier
			  if (name.varName.idinfo.kind == ASTNode.Kinds.Var ||
		          name.varName.idinfo.kind == ASTNode.Kinds.ScalarParm) {
				  if (name.adr == ASTNode.Ads.global)
					  storeGlobalInt(name.label);
		          else // (name.adr == Local)
		        	  storeLocalInt(name.varIndex);
		      } else {// Must be an array
		    	  // Check the lengths of source & target arrays
		          switch(name.type){
		          	case Integer:
		          		genCall("CSXLib/checkIntArrayLength([I[I)[I");
		          		break;
		            case Boolean:
		                genCall("CSXLib/checkBoolArrayLength([Z[Z)[Z");
		                break;
		            case Character:
		                genCall("CSXLib/checkCharArrayLength([C[C)[C");
		                break;
		            default:
		            	break;
		          } // Now store source array in target variable
		          if (name.varName.idinfo.adr == ASTNode.Ads.global){
		        	  name.label = name.varName.idinfo.label;
		              storeGlobalReference(name.label,
		              arrayTypeCode(name.varName.idinfo.type));
		          } else { // (name.varName.idinfo.adr == local)
		        	  name.varIndex = name.varName.idinfo.varIndex;
		              storeLocalReference(name.varIndex);
		          }
		      }
		  } else { // This is a subscripted variable
		        // A reference to the target array, the
		        // subscript expression and the source expression
		        // have already been pushed.
		        // Now store the source value into the array
		        switch(name.type){
		        	case Integer:
		        		//Generate: iastore
		        		gen("iastore");
		        		break;
		        	case Boolean:
		        		//Generate: bastore
		        		gen("bastore");
		        		break;
		        	case Character:
		        		//Generate: castore
		        		gen("castore");
		        		break;
		        	default:
		        		break;
		        } 
		  }
	}
	

	// Is this expression a trivial numeric literal?
    boolean isNumericLit(exprNodeOption e){
           return  (e instanceof intLitNode) ||
                   (e instanceof charLitNode) ||
                   (e instanceof trueNode) ||
                   (e instanceof falseNode); }
    
    
    int getLitValue(exprNode e){
        if  (e instanceof intLitNode){
        	return ((intLitNode) e).intval;
        }  
        else if (e instanceof charLitNode){
        	return ((charLitNode) e).charval;
        }  
        else if (e instanceof trueNode){
        	return 1;
        }   
        else {// if (e instanceof falseNode)
        	return 0;
        }
    }
    
    
    void declGlobalInt(String name, exprNodeOption initValue){
    	if (isNumericLit(initValue)) {
    	       int numValue = getLitValue((exprNode) initValue);
    	       // Generate a field declaration with initial value:
    	       // .field public static name I = numValue
    	       gen(".field public static",name+" I = ",numValue);
    	} 
    	else {
    	       // Gen a field declaration without an initial value:
    	       // .field public static name I
    		gen(".field public static",name,"I");
    	}

    }

    
    String arrayTypeCode(typeNode type){
    	  // Return array type code
    	  if (type instanceof intTypeNode)
    	      return "[I";
    	  else if (type instanceof charTypeNode)
    	      return "[C";
    	  else // (type instanceof boolTypeNode)
    		  return "[Z";
    }
    
    
    void declGlobalArray(String name, typeNode type){
    	// Generate a field declaration for an array:
    	// .field public static name arrayTypeCode(type)
    	gen(".field public static",name,arrayTypeCode(type));
    }
    
    
    void allocateArray(typeNode type){
    	   if (type instanceof intTypeNode) {
    		   // Generate a newarray instruction for an integer array:
     	       // newarray int
    		   gen("newarray","int");
    	   }
    	      
    	   else if (type instanceof charTypeNode)	{
    		   // Gen a newarray instruction for a character array:
     	       // newarray char
    		   gen("newarray","char");
    	   }
    	   else {
    		   // (type instanceof boolTypeNode)
    	       // Gen a newarray instruction for a boolean array:
    	       // newarray boolean
    		   gen("newarray","boolean");
    	   }
    }
    
    
    void storeGlobalReference(String name, String typeCode){
        // Generate a store of a reference from the stack into a static field:
    	//   putstatic CLASS/name typeCode
    	gen("putstatic",CLASS+"/"+name,typeCode);
    }
    
    
    void storeLocalReference(int index){
        // Generate a store of a reference from the stack into
        // a local variable:
        //    astore index
    	gen("astore",index);
    }

    
    void declField(varDeclNode n){
       String varLabel = n.varName.idname +"$";
       declGlobalInt(varLabel,n.initValue);
       n.varName.idinfo.label = varLabel;
       n.varName.idinfo.adr = ASTNode.Ads.global;
    }
    
    
    void declField(constDeclNode n){
       String constLabel = n.constName.idname +"$";
       declGlobalInt(constLabel,n.constValue);
       n.constName.idinfo.label = constLabel;
       n.constName.idinfo.adr = ASTNode.Ads.global;
    }
    
    
    void declField(arrayDeclNode n){
       String arrayLabel = n.arrayName.idname +"$";
       declGlobalArray(arrayLabel,n.elementType);
       n.arrayName.idinfo.label = arrayLabel;
       n.arrayName.idinfo.adr = ASTNode.Ads.global;
    }
    
    
    void genCall(String methodDescriptor){
        // Generate a static method call:
    	gen("invokestatic",methodDescriptor);
    }

    
    String arrayTypeCode(ASTNode.Types type){
    	// Return array type code
        switch(type){
        	case Integer: return "[I";
            case Character: return "[C";
            case Boolean: return "[Z";
            default : return ""; }
    }
    
    
    void loadGlobalReference(String name, String typeCode){
    	// Generate a load of a reference to the stack from
    	// a static field:
    	//   getstatic CLASS/name typeCode
    	gen("getstatic",CLASS+"/"+name,typeCode);
    }
    
    
    void loadLocalReference(int index){
    	// Generate a load of a reference to the stack from
    	// a local variable:
    	//    aload index
    	gen("aload",index);
    }
    
    
    void branchZ(String label){
    	// Generate branch to label if stack top contains 0:
        //    ifeq label
    	gen("ifeq",label);
    }
    
    
    void branchRelationalCompare(int tokenCode, String label){
    	// Generate a conditional branch to label based on tokenCode:
    	// Generate:
    	// "if_icmp"+relationCode(tokenCode)  label
    	gen("if_icmp"+relationCode(tokenCode),label);
    }
    
    
    void genRelationalOp(int operatorCode){
    	// Generate code to evaluate a relational operator
    	String trueLab = genLab();
    	String skip = genLab();
    	branchRelationalCompare(operatorCode, trueLab);
    	loadI(0); // Push false
    	branch(skip);
    	defineLab(trueLab);
    	loadI(1); // Push true
    	defineLab(skip);
    }
    
    
    String typeCode(typeNode type){
        // Return type code
        if (type instanceof intTypeNode)
              return "I";
        else if (type instanceof charTypeNode)
              return "C";
        else if (type instanceof boolTypeNode)
              return "Z";
        else // (type instanceof voidTypeNode)
              return "V";
    }
    
    
    String typeCode(ASTNode.Types type){
    	// Return type code
    	switch (type){
    		case Integer:
       			return "I";
            case Character:
                return "C";
            case Boolean:
                return "Z";
            case Void:
            	return "V";
            default:
            	return "";
    	}    
    }
    
    
    String buildTypeCode(argDeclNode n) {
    	if (n instanceof valArgDeclNode)
    		return typeCode(((valArgDeclNode) n).argType);
        else // must be an arrayArgDeclNode
        	return arrayTypeCode(((arrayArgDeclNode) n).elementType);
    }
   
    
    String buildTypeCode(argDeclsNode n){
        if (n.moreDecls.isNull())
              return buildTypeCode(n.thisDecl);
        else
              return buildTypeCode(n.thisDecl)+
                     buildTypeCode((argDeclsNode) n.moreDecls);
    }
    
    
    String buildTypeCode(exprNode n){
    	if (n.kind == ASTNode.Kinds.Array ||
        	n.kind == ASTNode.Kinds.ArrayParm)
              return arrayTypeCode(n.type);
        else return typeCode(n.type);
    }
   
    
    String buildTypeCode(argsNode n){
        if (n.moreArgs.isNull())
              return buildTypeCode(n.argVal);
        else return buildTypeCode(n.argVal) +
                    buildTypeCode((argsNode) n.moreArgs);
    }
   
    
    String buildTypeCode(String methodName, argsNodeOption args, 
    		String returnCode){
    	String newTypeCode = methodName;
        if (args.isNull())
              newTypeCode = newTypeCode + "()";
        else newTypeCode = newTypeCode + "(" +
                            buildTypeCode((argsNode) args) + ")";
        return newTypeCode + returnCode;
    }
    
    
	static Boolean isRelationalOp(int op) {
		switch (op) {
			case sym.EQ:
			case sym.NOTEQ:
			case sym.LT:
			case sym.LEQ:	
			case sym.GT:
			case sym.GEQ:
				return true;
			default:
				return false;
		}
	}

	
	String relationCode(int tokenCode) {
		//Determine relation code of an op based on its tokenCode:
		switch (tokenCode) {
			case sym.EQ:
				return "eq";
			case sym.NOTEQ:
				return "ne";
			case sym.LT:
				return "lt";
			case sym.LEQ:
				return "le";
			case sym.GT:
				return "gt";
			case sym.GEQ:
				return "ge";
			default:
				return "";
		}
	}
	
	
	static String selectOpCode(int op) {
		switch (op) {
			case sym.PLUS:
				return("iadd");
			case sym.MINUS:
				return("isub");
			case sym.TIMES:
				return("imul");
			case sym.SLASH:
				return("idiv");
			case sym.CAND:
				return("iand");
			case sym.COR:
				return("ior");
			default:
				assertCondition(false);
				return "";
		}
	}
	
	
	//   startCodeGen translates the AST rooted by node n
  	//      into JVM code which is written in afile.
	//   If no errors occur during code generation,
	//    TRUE is returned, and afile should contain a
    //    complete and correct JVM program. 
	//   Otherwise, FALSE is returned and afile need not
	//    contain a valid program.
	boolean startCodeGen(csxLiteNode n) {// For CSX Lite
	    this.visit(n);
	    return (cgErrors == 0);
	}
	
	
	boolean startCodeGen(classNode n) {// For CSX
	    this.visit(n);
	    return (cgErrors == 0);
	}
	
	
 	void visit(csxLiteNode n) {
 		genComment("CSX Lite program translated into Java bytecodes (Jasmin "
 				+ "format)");
		gen(".class","public","test");
    	gen(".super","java/lang/Object");
    	gen(".method"," public static","main([Ljava/lang/String;)V");
		this.visit(n.progDecls);
		if (numberOfLocals >0)
    		gen(".limit","locals",numberOfLocals);
		this.visit(n.progStmts);
    	gen("return");
    	gen(".limit","stack",10);
    	gen(".end","method");
	}

 	
 	void visit(fieldDeclsNode n){
		this.visit(n.thisField);
		this.visit(n.moreFields);
	}
	
 	
	void visit(nullFieldDeclsNode n){}
	
	
	void visit(stmtsNode n){
		 this.visit(n.thisStmt);
		 this.visit(n.moreStmts);
	}
	
	
	void visit(nullStmtsNode n){}

	
	void visit(varDeclNode n){
		if (currentMethod == null){ // A global field decl
			if (n.varName.idinfo.adr == ASTNode.Ads.none){
				// First pass; generate field declarations
	            declField(n);
			}
	        else { // 2nd pass; do field init (if needed)
	        	if (! n.initValue.isNull())       		
	        		if (! isNumericLit(n.initValue)) {
	        			// Compute init value & store in field
	                    this.visit(n.initValue);
	                    storeId(n.varName);
	        		}
	        	}
	}
		else {
			// Process local variable declaration
	        //   Give this var an index equal to numberOfLocals
	        //     and remember index in symbol table entry   
			n.varName.idinfo.varIndex = 
					currentMethod.name.idinfo.numberOfLocals;
	        n.varName.idinfo.adr = ASTNode.Ads.local;
	        //   Increment numberOfLocals used in this method
	        currentMethod.name.idinfo.numberOfLocals++;
	        // Do initialization (if necessary)
	        if (! n.initValue.isNull()){
	        	this.visit(n.initValue);
	        	storeId(n.varName);
	        }   
		}	
	}
	

	void visit(nullTypeNode n) {}

	
	void visit(intTypeNode n) {
		// No code generation needed
	}

	
	void visit(boolTypeNode n) {
		// No code generation needed
	}

	
	void visit(charTypeNode n) {
		// No code generation needed
	}

	
	void visit(voidTypeNode n) {
		// No code generation needed
	}
	
	
	void visit(asgNode n) { // Final version
		// Compute address associated with LHS
	    computeAdr(n.target);
	    // Translate RHS (an expression)
	    this.visit(n.source);
	    // Check to see if source needs to be cloned or converted
	    if (n.source.kind == ASTNode.Kinds.Array ||
	    	n.source.kind == ASTNode.Kinds.ArrayParm){
	    	switch(n.source.type){
	        	case Integer:
	        		genCall("CSXLib/cloneIntArray([I)[I");
	                break;
	            case Boolean:
	                genCall("CSXLib/cloneBoolArray([Z)[Z");
	                break;
	            case Character:
	                genCall("CSXLib/cloneCharArray([C)[C");
	                break;
	            default:
	                break;
	    	}
	    }
	    else if (n.source.kind == ASTNode.Kinds.String){
	    	genCall("CSXLib/convertString(Ljava/lang/String;)[C");
	    }
	    // Value to be stored is now on the stack
     	// Store it into LHS
    	storeName(n.target);
	}

	
	void visit(ifThenNode n) {	
//TODO: //extra credit to suppress else part if it's null
		String endLab;   // label that will mark end of if stmt
		if (!n.elsePart.isNull()){
			String elseLab;  // label that will mark start of else part
			// translate boolean condition, pushing it onto the stack
			this.visit(n.condition);
			elseLab = genLab();
			// generate conditional branch around then stmt
			branchZ(elseLab);
			// translate then part
			this.visit(n.thenPart);
			// branch around else part
			endLab = genLab();
			branch(endLab);
			// translate else part
			defineLab(elseLab);
			this.visit(n.elsePart);
			// generate label marking end of if stmt
			defineLab(endLab);
		} else {
			this.visit(n.condition);
			endLab = genLab();
			branchZ(endLab);
			this.visit(n.thenPart);
			defineLab(endLab);
		}
	}

	
	void visit(printNode n){
		// Compute value to be printed onto stack
	     this.visit(n.outputValue);
	     if (n.outputValue.kind == ASTNode.Kinds.Array ||
	         n.outputValue.kind == ASTNode.Kinds.ArrayParm)
	           genCall("CSXLib/printCharArray([C)V");
	     else if (n.outputValue.kind == ASTNode.Kinds.String)
	           genCall("CSXLib/printString(Ljava/lang/String;)V");
	     else switch(n.outputValue.type){
	           case Integer:
	                genCall("CSXLib/printInt(I)V");
	                break;
	           case Boolean:
	                genCall("CSXLib/printBool(Z)V");
	                break;
	           case Character:
	                genCall("CSXLib/printChar(C)V");
	                break; 
	           default :
	                break;
	               
	     }
	     this.visit(n.morePrints);
	}

	
	void visit(nullPrintNode n) {}
		
	
	void visit(blockNode n) {
		// Generate code for block decls and body
		this.visit(n.decls);
		this.visit(n.stmts);
	}

	
	void visit(binaryOpNode n) {
	     // First translate the left and right operands
	     this.visit(n.leftOperand);
	     this.visit(n.rightOperand);
	     // Now the values of the operands are on the stack
	     // Is this a relational operator?
	     if (relationCode(n.operatorCode) == ""){
	           gen(selectOpCode(n.operatorCode));
	     } else { // relational operator
	           genRelationalOp(n.operatorCode);
	     }
	     n.adr = ASTNode.Ads.stack;
	}
	
	
	void visit(identNode n) {
		// In CSX-lite, we don't code generate identNode directly.
		//  Instead, we do translation in parent nodes where the
		//   context of identNode is known
		// Hence no code generation actions are defined here 
		// (though you may want/need to define some in full CSX)
	}
	
	
	void visit(intLitNode n) {
		loadI(n.intval);
		n.adr = ASTNode.Ads.literal;
	}
	
	
	void visit(nameNode n) { // Final version
		n.adr = ASTNode.Ads.stack;
		if (n.subscriptVal.isNull()) {
			// Simple (unsubscripted) identifier
		    if (n.varName.idinfo.kind == ASTNode.Kinds.Var ||
		    	n.varName.idinfo.kind == ASTNode.Kinds.Value ||
		        n.varName.idinfo.kind == ASTNode.Kinds.ScalarParm) {
		    	// id is a scalar variable, parameter or const
		    	if (n.varName.idinfo.adr == ASTNode.Ads.global){
		    		// id is a global
		            String label = n.varName.idinfo.label;
		            loadGlobalInt(label);
		        } else { // (n.varName.idinfo.adr == Local)
		        	n.varIndex = n.varName.idinfo.varIndex;
		            loadLocalInt(n.varIndex);
		        } 
		    } else { // varName is an array var or array parm
		    	if (n.varName.idinfo.adr == ASTNode.Ads.global){
		    		n.label = n.varName.idinfo.label;
		    		loadGlobalReference(n.label,
		    		arrayTypeCode(n.varName.idinfo.type));
		        } else { // (n.varName.idinfo.adr == local)
		        	n.varIndex = n.varName.idinfo.varIndex;
		            loadLocalReference(n.varIndex);
		        }
		    }
		} else { // This is a subscripted variable
			// Push array reference first
		    if (n.varName.idinfo.adr == ASTNode.Ads.global){
		    	n.label = n.varName.idinfo.label;
		    	loadGlobalReference(n.label,
		        arrayTypeCode(n.varName.idinfo.type));
		    } else { // (n.varName.idinfo.adr == local)
		    	n.varIndex = n.varName.idinfo.varIndex;
		        loadLocalReference(n.varIndex);
		    } // Next compute subscript expression
		    this.visit(n.subscriptVal);
		    // Now load the array element onto the stack
		    switch(n.type){
		    	case Integer:
				//Generate: iaload
		    		gen("iaload");
		    		break;
				case Boolean:
				//Generate: baload
					gen("baload");
				    break;
				case Character:
				//Generate: caload
				    gen("caload"); 
					break;
				default:
					break;
		    }
		}
	}


	//visit fields twice (first time to generate field declarations and then 
	//to generate non-trivial field declarations...)
	//Then visit methods
	void visit(classNode n) {
	     currentMethod = null; // We're not in any method body (yet)
	     CLASS = n.className.idname;
	     gen(".class","public",CLASS);
	     gen(".super", "java/lang/Object");
	     gen();
	     // Generate field declarations for the class (i.e. no values... int i)
	     this.visit(n.members.fields);
	     gen(".method", "public static", "main([Ljava/lang/String;)V");
	     
	     // Generate non-trivial field declarations (i.e. with 
	     // values... int i = 123+x) ... need to do something like this for 
	     // non-trivial fields gen(".limit","stack",5);
	     this.visit(n.members.fields);
	     gen("invokestatic",CLASS+"/main()V");
	     gen("return");
	     // stack stuff...
	     gen(".limit","stack",2);
	     gen(".end","method");
	     gen();
	     // Finally translate methods
	     this.visit(n.members.methods);
	     
	}
	

	void visit(memberDeclsNode n) {
		// this node check does NOTHING... classNode skips it
		this.visit(n.fields);
		this.visit(n.methods);
	}

	
	void visit(argDeclsNode n) {
	     // Label each method argument with its address info
	     this.visit(n.thisDecl);
	     this.visit(n.moreDecls);
	}
	
	
	void visit(valArgDeclNode n) {
	     // Label method argument with its address info
	     n.argName.idinfo.adr = ASTNode.Ads.local;
	     n.argName.idinfo.varIndex = currentMethod.name.idinfo.numberOfLocals++;
	}
	
	
	void visit(arrayArgDeclNode n) {
		// Label method argument with its address info
	    n.argName.idinfo.adr = ASTNode.Ads.local;
	    n.argName.idinfo.varIndex = currentMethod.name.idinfo.numberOfLocals++;
	}

	
	void visit(nullArgDeclsNode n) {}

	
	void visit(methodDeclsNode n) {
		this.visit(n.thisDecl);
		this.visit(n.moreDecls);
	}

	
	void visit(nullMethodDeclsNode n) {}

	
	void visit(methodDeclNode n) {
		currentMethod = n; // We're in a method now!
	    n.name.idinfo.numberOfLocals = 0;
	    String newTypeCode = n.name.idname;
	    if (n.args.isNull())
	    	newTypeCode = newTypeCode + "()";
	    else newTypeCode = newTypeCode + "(" + 
	    	buildTypeCode((argDeclsNode) n.args) + ")";
	    newTypeCode = newTypeCode + typeCode(n.returnType);
	    n.name.idinfo.methodReturnCode = typeCode(n.returnType);
	    //generate:
	    // .method public static newTypeCode
	    gen(".method", "public static", newTypeCode);
	    this.visit(n.args); // Assign local variable indices to args
	    // Generate code for local decls and method body
	    this.visit(n.decls);
	    this.visit(n.stmts);
	    // generate default return at end of method body
	    if (n.returnType instanceof voidTypeNode){
	    //generate: return
	    gen("return");
	    }     
	    else { // Push a default return value of 0
	    	loadI(0);
	    	// generate: ireturn
	    	gen("ireturn");
	    }
	    // Generate end of method data;
	    // we'll guestimate stack depth needed at 25
	    //  (almost certainly way too big!)
	    gen();
//TODO: Extra credit to have better stack limit
	    // generate: .limit stack 25
	    gen(".limit","stack",25);
	    // generate: .limit locals n.name.idinfo.numberOfLocals
	    gen(".limit","locals",n.name.idinfo.numberOfLocals);
	    // generate: .end method
	    gen(".end","method");
	}
	
	
	void visit(trueNode n) {
		loadI(1);
	    n.adr = ASTNode.Ads.literal;
	    n.intval = 1;
	}

	
	void visit(falseNode n) {
		loadI(0);
	    n.adr = ASTNode.Ads.literal;
	    n.intval = 0;
	}

	
	void visit(constDeclNode n) {
		if (currentMethod == null) // A global const decl
		{
			if (n.constName.idinfo.adr == ASTNode.Ads.none){
				// First pass; generate field declarations
		        declField(n);
			}
		    else { // 2nd pass; do field initialization (if needed)
		    	if (! isNumericLit(n.constValue)) {
		           // Compute const val onto stack and store in field
		           this.visit(n.constValue);
		           storeId(n.constName);
		        } 
		    }
		}
		else { // Process local const declaration
			// Give this variable an index equal to numberOfLocals
		   	// and remember index in symbol table entry
		   	n.constName.idinfo.varIndex =
		   			currentMethod.name.idinfo.numberOfLocals;
		   	n.constName.idinfo.adr = ASTNode.Ads.local;
		   	//   Increment numberOfLocals used in this method
		   	currentMethod.name.idinfo.numberOfLocals++;
		   	// compute and store const value
		   	this.visit(n.constValue);
		   	storeId(n.constName);
		}
	}

	
	void visit(arrayDeclNode n) {
	    // Create a new array and store resulting reference
		if (currentMethod == null) { // A global array decl
			if (n.arrayName.idinfo.adr == ASTNode.Ads.none)  {
				// First pass; generate field declarations
	            declField(n);
	            return;
			} 
		} else {   
			// Process local array declaration
	        // Give this variable an index equal to numberOfLocals
	        //     and remember index in symbol table entry
	        n.arrayName.idinfo.varIndex =
	        		currentMethod.name.idinfo.numberOfLocals;
	        n.arrayName.idinfo.adr = ASTNode.Ads.local;
	        //   Increment numberOfLocals used in this method
	        currentMethod.name.idinfo.numberOfLocals++;
		} 
		// Now create the array & store a reference to it
	    loadI(n.arraySize.intval); // Push size of array
	    allocateArray(n.elementType);
	    if (n.arrayName.idinfo.adr == ASTNode.Ads.global)
	    	storeGlobalReference(n.arrayName.idinfo.label,
	 	           arrayTypeCode(n.elementType));
	 	else storeLocalReference(n.arrayName.idinfo.varIndex);
	}
	     
	
	void visit(readNode n) {
        // Compute address associated with target variable
        computeAdr(n.targetVar);
        // Call library routine to do the read
        if (n.targetVar.varName.idinfo.type == ASTNode.Types.Integer)
             genCall("CSXLib/readInt()I");
        else // targetVar.varName.idinfo.type == Character
             genCall("CSXLib/readChar()C");
        storeName(n.targetVar);
        this.visit(n.moreReads);
	}


	void visit(nullReadNode n) {}


	void visit(charLitNode n) {
		loadI(n.charval);
	    n.adr = ASTNode.Ads.literal;
	    n.intval = n.charval;
	}


	void visit(strLitNode n) {
		// generate:
		//  ldc n.strval
		gen("ldc",n.strval);
	}
	
	
	void visit(argsNode n) {
	     // Evaluate arguments and load them onto stack
	     this.visit(n.argVal);
	     this.visit(n.moreArgs);
	}


	void visit(nullArgsNode n) {}

	
	void visit(unaryOpNode n) {
		this.visit(n.operand);
		
		// Generate code to evaluate unary operator ! (NOT)
	    String trueLab = genLab();
	    String skip = genLab();
	    branchZ(trueLab);
	    loadI(0); // Push false
	    branch(skip);
	    defineLab(trueLab);
	    loadI(1); // Push true
	    defineLab(skip);
	    
	    n.adr = ASTNode.Ads.stack;
    
	}


	void visit(nullStmtNode n) {}


	void visit(nullExprNode n) {}


	void visit(whileNode n) {
	     String top = genLab();
	     String bottom = genLab();
	     if (! n.label.isNull()){
	    	 ((identNode) n.label).idinfo.topLabel = top;
	         ((identNode) n.label).idinfo.bottomLabel = bottom;}
	     defineLab(top);
	     this.visit(n.condition);
	     branchZ(bottom);
	     this.visit(n.loopBody);
	     branch(top);
	     defineLab(bottom);
	}
	

	void visit(callNode n) {
		// Evaluate args and push them onto the stack
	    this.visit(n.args);
	    // Generate call to method, using its type code
	    String typeCode = buildTypeCode(n.methodName.idname, n.args, 
	    		n.methodName.idinfo.methodReturnCode);
	    genCall(CLASS+ "/" + typeCode);
	}


	void visit(fctCallNode n) {
		// This should resemble callNode above but with visit to return...
		// Evaluate args and push them onto the stack
	    this.visit(n.methodArgs);
	    // Generate call to method, using its type code
	    String typeCode = buildTypeCode(n.methodName.idname, n.methodArgs, 
	    		n.methodName.idinfo.methodReturnCode);
	    genCall(CLASS+ "/" + typeCode);
	}


	void visit(returnNode n) {
		if (n.returnVal.isNull()){
			//generate: return
			gen("return");
		}
	    else { // Evaluate return value
	    	this.visit(n.returnVal);
	        //generate: ireturn
	    	gen("ireturn");
	    }
	}

	
	void visit(breakNode n) {
	     branch(n.label.idinfo.bottomLabel);
	}

	
	void visit(continueNode n) {
	     branch(n.label.idinfo.topLabel);
	}


	void visit(castNode n) {
		// First translate the operand
	    this.visit(n.operand);
	    // Is the operand an int or char and the resultType a bool?
	    if (((n.operand.type == ASTNode.Types.Integer) ||
	    	 (n.operand.type == ASTNode.Types.Character)) &&
	         (n.resultType instanceof boolTypeNode)){
	    	loadI(0);
	        genRelationalOp(sym.NOTEQ);
	    } else if ((n.operand.type == ASTNode.Types.Integer) && 
	    	       (n.resultType instanceof charTypeNode)){
	    	loadI(127); // Equal to 1111111B
	        gen("iand");
		} 
	}
	
	
	void visit(incrementNode n) {
		if (n.target.subscriptVal.isNull()){
			// Simple (unsubscripted) identifier
	        this.visit(n.target); //Evaluate ident onto stack
	        loadI(1);
	        gen("iadd"); //incremented ident now on stack
	        computeAdr(n.target);
	        storeName(n.target);
	    } else { // Subscripted array element
	    	computeAdr(n.target); //Push array ref and index
	        gen("dup2"); // Duplicate array ref and index
	        // (one pair for load, 2nd pair for store)
	        // Now load the array element onto the stack
	        switch(n.target.type){
	        	case Integer:
	        		gen("iaload");
	        		break;
	            case Boolean:
	                gen("baload");
	                break;
	            case Character:
	                gen("caload");
	                break;
	            default:
	            	break;
	         }
	         loadI(1);
	         gen("iadd"); // incremented identifier now on stack
	         storeName(n.target);
	     }
	}
	
	
	void visit(decrementNode n){
		if (n.target.subscriptVal.isNull()){
			// Simple (unsubscripted) identifier
	        this.visit(n.target); //Evaluate ident onto stack
	        loadI(1);
	        gen("isub"); //decremented ident now on stack
	        computeAdr(n.target);
	        storeName(n.target);
	    } else { // Subscripted array element
	    	computeAdr(n.target); //Push array ref and index
	        gen("dup2"); // Duplicate array ref and index
	        // (one pair for load, 2nd pair for store)
	        // Now load the array element onto the stack
	        switch(n.target.type){
	        	case Integer:
	        		gen("iaload");
	        		break;
	            case Boolean:
	                gen("baload");
	                break;
	            case Character:
	                gen("caload");
	                break;
	            default:
	            	break;
	         }
	         loadI(1);
	         gen("isub"); // decremented identifier now on stack
	         storeName(n.target);
	     }
	}
}
