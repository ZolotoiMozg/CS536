	.class	public  p20csx
	.super	java/lang/Object

	.method	public static  main([Ljava/lang/String;)V
	invokestatic	p20csx/main()V
	return
	.limit	stack  2
	.end	method

	.method	public static  main()V
	ldc	"Testing Program p20csx\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	1
	istore	0
	ldc	2
	istore	1
	ldc	1
	istore	2
	iload	0
	iload	1
	if_icmplt	L0
	ldc	0
	goto	L1
L0:
	ldc	1
L1:
	ifeq	L2
	iload	2
	ldc	1
	iand
	istore	2
	goto	L3
L2:
	ldc	"ERROR: Less Than operator not working\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	0
	istore	2
L3:
	iload	1
	iload	0
	if_icmpgt	L4
	ldc	0
	goto	L5
L4:
	ldc	1
L5:
	ifeq	L6
	iload	2
	ldc	1
	iand
	istore	2
	goto	L7
L6:
	ldc	"ERROR: Greater Than operator not working\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	0
	istore	2
L7:
	iload	0
	iload	1
	if_icmpeq	L8
	ldc	0
	goto	L9
L8:
	ldc	1
L9:
	ifeq	L10
	ldc	"ERROR: Equal operator not working\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	0
	istore	2
	goto	L11
L10:
	iload	2
	ldc	1
	iand
	istore	2
L11:
	iload	0
	iload	1
	if_icmpne	L12
	ldc	0
	goto	L13
L12:
	ldc	1
L13:
	ifeq	L14
	iload	2
	ldc	1
	iand
	istore	2
	goto	L15
L14:
	ldc	"ERROR: Less Than operator not working\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	0
	istore	2
L15:
	iload	2
	ldc	0
	if_icmpeq	L16
	ldc	0
	goto	L17
L16:
	ldc	1
L17:
	ifeq	L18
	ldc	"ERRORS found in program\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	goto	L19
L18:
L19:
	ldc	"Test compeleted\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	return

	.limit	stack  25
	.limit	locals  3
	.end	method
