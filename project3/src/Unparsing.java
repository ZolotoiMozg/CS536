/*  
 * 	Updated by: Denzil Stefen Showers
 *  Student #9062589552 
 * 	
 * 	The following methods unparse AST nodes used in CSX.
 *  Handles complete CSX grammar, as defined by the csx.cup file.
 * 
 * 	This file was modified from a simpler version that unparsed
 *  AST nodes used in CSX Lite.
 */

public class Unparsing extends Visitor {
	
	static void genIndent(int indent){
		for (int i=1;i<=indent;i++)
			System.out.print("   ");
	}
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
			case sym.LT:
				System.out.print(" < ");
				break;
			case sym.GT:
				System.out.print(" > ");
				break;
			case sym.GEQ:
				System.out.print(" >= ");
				break;
			case sym.LEQ:
				System.out.print(" <= ");
				break;
			case sym.CAND:
				System.out.print(" && ");
				break;
			case sym.COR:
				System.out.print(" || ");
				break;
			case sym.TIMES:
				System.out.print(" * ");
				break;
			case sym.SLASH:
				System.out.print(" / ");
				break;
			default:
				throw new Error();
		}
	}

	void visit(csxLiteNode n,int indent){
		//System.out.println ("\n\nStart 2nd unparsing:\n"); 
		System.out.println(n.linenum + ":\t" + " {");
		this.visit(n.progDecls,1);
		this.visit(n.progStmts,1);
		System.out.println(n.linenum + ":\t" + " } EOF");
	}
	
	void visit(fieldDeclsNode n,int indent){
			this.visit(n.thisField,indent);
			this.visit(n.moreFields,indent);
	}
	void visit(nullFieldDeclsNode n,int indent){}

	void visit(stmtsNode n,int indent){
		System.out.print(n.linenum + ":\t");
		genIndent(indent);
		this.visit(n.thisStmt,indent);
		this.visit(n.moreStmts,indent);

	}
	void visit(nullStmtsNode n,int indent){}

	void visit(varDeclNode n,int indent){
		System.out.print(n.linenum + ":\t");
		genIndent(indent);
	    this.visit(n.varType,0);
		System.out.print(" ");
		this.visit(n.varName,0);
		//return init value if it exists
		if (!n.initValue.isNull()){
			System.out.print(" = ");
			this.visit(n.initValue,indent);
		}
		System.out.println(";");
	}
	
	void visit(nullTypeNode n,int ident){}
	
	void visit(intTypeNode n,int ident){
		System.out.print("int");
	}
	
	void visit(boolTypeNode n,int ident){
		System.out.print("bool");
	}
	
	void visit(identNode n,int indent){
		System.out.print(n.idname);
	}
	
	void visit(nameNode n,int indent){
		System.out.print(n.varName.idname);
		//print subscripts if available
		if (!n.subscriptVal.isNull()){
			System.out.print("[");
			this.visit(n.subscriptVal, indent);
			System.out.print("]");
		}
	}
	
	void visit(asgNode n,int indent){
		this.visit(n.target,indent);
		System.out.print(" = ");
		this.visit(n.source,indent);
		System.out.println(";");
	}

	void visit(incrementNode n,int indent){
		this.visit(n.target, indent);
		System.out.println("++;");
	}

	void visit(decrementNode n,int indent){
		this.visit(n.target, indent);
		System.out.println("--;");
	 }
	
	 void visit(ifThenNode n,int indent){
		System.out.print("if (");
		this.visit(n.condition,0);
		System.out.println(")");
		System.out.print(n.thenPart.linenum + ":\t");
		genIndent(indent+1);
		this.visit(n.thenPart,indent);		
		if (!n.elsePart.isNull()){
			System.out.print(n.thenPart.linenum + ":\t");
			genIndent(indent);
			System.out.println("else ");
			System.out.print(n.elsePart.linenum + ":\t");
			indent++;
			genIndent(indent);
			this.visit(n.elsePart, indent);
		}
	 }

	 void visit(blockNode n,int indent){
		System.out.println("{");
		this.visit(n.decls,indent+2);
		this.visit(n.stmts,indent+2);
		System.out.print(n.linenum + ":\t");
		genIndent(indent+1);
		System.out.println("}");
	 }

	 void visit(binaryOpNode n,int indent){
		System.out.print("(");
		this.visit(n.leftOperand,0);
		printOp(n.operatorCode);
		this.visit(n.rightOperand,0);
		System.out.print(")");
	 }

	 void visit(intLitNode n,int indent){
		if (n.intval>=0)
			System.out.print(n.intval);
		else	System.out.print("~"+-n.intval);
	 }

	 void visit(classNode n,int indent){
		System.out.print(n.linenum + ":");
		System.out.print(" class ");
		this.visit(n.className, indent);
		System.out.println(" {");
		indent++;
		this.visit(n.members, indent);
		System.out.print(n.linenum + ":");
	 	System.out.println(" } EOF");
	 }

	 void  visit(memberDeclsNode n,int indent){
		this.visit(n.fields, indent);
		this.visit(n.methods, indent);
	 }
	 
	 void  visit(methodDeclsNode n,int indent){
		this.visit(n.thisDecl, indent);
		this.visit(n.moreDecls, indent);
	 }
	 
	 void visit(nullStmtNode n,int indent){}
	 
	 void visit(nullReadNode n,int indent){}

	 void visit(nullPrintNode n,int indent){}

	 void visit(nullExprNode n,int indent){}

	 void visit(nullMethodDeclsNode n,int indent){}

	 void visit(methodDeclNode n,int indent){
		System.out.print(n.linenum + ":\t");
		genIndent(indent);
		if (n.returnType.isNull()){
			System.out.print("void");
		}
		else {
			this.visit(n.returnType, indent);
		}
		System.out.print(" ");
		this.visit(n.name, indent);
		System.out.print(" (");
		//handle null args
		if (!n.args.isNull()){
			this.visit(n.args, indent);
		}
		System.out.println(") {");
		//handle null decls
		if (!n.decls.isNull()){
			this.visit(n.decls, indent+1);
		}
		this.visit(n.stmts,indent+1);
		System.out.print(n.linenum + ":\t");
		genIndent(indent);
		System.out.println("}");
	 }
 
	 void visit(argDeclsNode n,int indent){
		this.visit(n.thisDecl, indent);
		if (!n.moreDecls.isNull()){
			System.out.print(", ");
			this.visit(n.moreDecls, indent);
		}
	 }

	 void visit(nullArgDeclsNode n,int indent){}

	 void visit(valArgDeclNode n,int indent){
		this.visit(n.argType, 0);
		System.out.print(" ");
		this.visit(n.argName,0);
	 }
	
	 void visit(arrayArgDeclNode n,int indent){
		this.visit(n.elementType, 0);
		System.out.print(" ");
		this.visit(n.argName,0);
		System.out.print(" [ ]");
	 }
	 
	 void visit(constDeclNode n,int indent){
		System.out.print(n.linenum + ":\t");
		genIndent(indent);
		System.out.print("const");
		System.out.print(" ");
		this.visit(n.constName,0);
		//return value if it exists
		if (!n.constValue.isNull()){
			System.out.print(" = ");
			this.visit(n.constValue,indent);
		}
		System.out.println(";");
	 }
	 
	 void visit(arrayDeclNode n,int indent){
		System.out.print(n.linenum + ":\t");
		genIndent(indent);
		this.visit(n.elementType,0);
		System.out.print(" ");
		this.visit(n.arrayName,0);
		System.out.print(" [ ");
		this.visit(n.arraySize,indent);
		System.out.println(" ];");
	 }
	
	 void visit(charTypeNode n,int ident){
		System.out.print("char");
	 }

	 void visit(voidTypeNode n,int ident){
		System.out.print("void");
	 }

	 void visit(whileNode n,int indent){
		if (!n.label.isNull()){
			this.visit(n.label, indent);
			System.out.print(": ");
		}
		System.out.print("while (");
		this.visit(n.condition, indent);
		System.out.println(")");
		System.out.print(n.linenum + ":\t");
		genIndent(indent+1);
		this.visit(n.loopBody, indent);
	 }

	 void visit(breakNode n,int indent){
		System.out.print("break ");
		this.visit(n.label, indent);
		System.out.println(";");
	 }
	
	 void visit(continueNode n,int indent){
		System.out.print("continue ");
		this.visit(n.label, indent);
		System.out.println(";");
	 }
	  
	 void visit(callNode n,int indent){
		this.visit(n.methodName, indent);
		System.out.print("(");
		if (!n.args.isNull()){
			this.visit(n.args, indent);
		}
		System.out.println(");");
	 }

	 void visit(printNode n,int indent){
		//to handle "internal" nodes, check for -1 indent value
		if (indent != -1){
			System.out.print("print(");
		}
		this.visit(n.outputValue, indent);
		if (!n.morePrints.isNull()){
			System.out.print(", ");
			//pass indent=-1 to signify this is an "internal" node
			this.visit(n.morePrints, -1);
		}
		else {
			System.out.println(");");
		}
	 }
	  
	 void visit(readNode n,int indent){
		 //to handle "internal" nodes, check for -1 indent value
		 if (indent != -1){
				System.out.print("read(");
		 }
		 this.visit(n.targetVar, indent);
		 if (!n.moreReads.isNull()){
			 System.out.print(", ");
			 //pass indent=-1 to signify this is an "internal" node
			 this.visit(n.moreReads, -1);
		 }
		 else {
			 System.out.println(");");
		 }
	 }
	  
	 void visit(returnNode n,int indent){
		System.out.print("return");
		if (!n.returnVal.isNull()){
			System.out.print(" ");
			this.visit(n.returnVal, indent);
		}
		System.out.println(";");
	 }

	 void visit(argsNode n,int indent){
		this.visit(n.argVal, indent);
		if (!n.moreArgs.isNull()){
			System.out.print(", ");
			this.visit(n.moreArgs, indent);
		}
	 }
	
	 //null returns nothing
	 void visit(nullArgsNode n,int indent){}
		
	 void visit(castNode n,int indent){
		System.out.print("((");
		this.visit(n.resultType, indent);
		System.out.print(") ");
		this.visit(n.operand, indent);
		System.out.print(")");
	 }

	 void visit(fctCallNode n,int indent){
		this.visit(n.methodName, indent);
		System.out.print("(");
		if (!n.methodArgs.isNull()){
			this.visit(n.methodArgs,indent);
		}
		System.out.print(")");
	 }
	 
	 //only handles the NOT unary operation
	 void visit(unaryOpNode n,int indent){
		if (n.operatorCode == sym.NOT){
			System.out.print("(! ");
			this.visit(n.operand, indent);
			System.out.print(")");
		}
	 }

	 //return character
	 void visit(charLitNode n,int indent){
		if (n.charval == '\n'){
			System.out.print("'\\n'");
		}
		else if (n.charval == '\t'){
			System.out.print("'\\t'");
		}
		else if (n.charval == '\\'){
			System.out.print("'\\\\'");
		}
		else if (n.charval == '\''){
			System.out.print("'\\\''");
		}
		else{
			System.out.print("'"+n.charval+"'");
		}
	 }
	 
	 //return string
	 void visit(strLitNode n,int indent){
		System.out.print(n.strval);
	 }

	 //return TRUE
	 void visit(trueNode n,int indent){
		System.out.print("true");
	 }

	 //return FALSE
	 void visit(falseNode n,int indent){
		System.out.print("false");
	 }

}
