	.class	public  p23csx
	.super	java/lang/Object

	.method	public static  main([Ljava/lang/String;)V
	invokestatic	p23csx/main()V
	return
	.limit	stack  2
	.end	method

	.method	public static  main()V
	ldc	"Testing Program p23csx\n"
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
	ldc	"ERROR: Less Than operator not working"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	0
	istore	2
L3:
	iload	0
	iload	1
	if_icmple	L4
	ldc	0
	goto	L5
L4:
	ldc	1
L5:
	iload	0
	ldc	1
	if_icmple	L6
	ldc	0
	goto	L7
L6:
	ldc	1
L7:
	iand
	iload	1
	ldc	2
	if_icmple	L8
	ldc	0
	goto	L9
L8:
	ldc	1
L9:
	iand
	iload	0
	ldc	1000000
	if_icmple	L10
	ldc	0
	goto	L11
L10:
	ldc	1
L11:
	iand
	ldc	-123456
	ldc	123456
	if_icmple	L12
	ldc	0
	goto	L13
L12:
	ldc	1
L13:
	iand
	ifeq	L14
	iload	2
	ldc	1
	iand
	istore	2
	goto	L15
L14:
	ldc	"ERROR: <= operator not working"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	0
	istore	2
L15:
	iload	1
	iload	0
	if_icmpgt	L16
	ldc	0
	goto	L17
L16:
	ldc	1
L17:
	ifeq	L18
	iload	2
	ldc	1
	iand
	istore	2
	goto	L19
L18:
	ldc	"ERROR: Greater Than operator not working"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	0
	istore	2
L19:
	iload	1
	iload	0
	if_icmpge	L20
	ldc	0
	goto	L21
L20:
	ldc	1
L21:
	iload	1
	ldc	2
	if_icmpge	L22
	ldc	0
	goto	L23
L22:
	ldc	1
L23:
	iand
	ldc	1
	iload	0
	if_icmpge	L24
	ldc	0
	goto	L25
L24:
	ldc	1
L25:
	iand
	iload	1
	ldc	-123456
	if_icmpge	L26
	ldc	0
	goto	L27
L26:
	ldc	1
L27:
	iand
	ldc	0
	ldc	-111111
	if_icmpge	L28
	ldc	0
	goto	L29
L28:
	ldc	1
L29:
	iand
	ifeq	L30
	iload	2
	ldc	1
	iand
	istore	2
	goto	L31
L30:
	ldc	"ERROR: >= operator not working"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	0
	istore	2
L31:
	iload	0
	iload	1
	if_icmpeq	L32
	ldc	0
	goto	L33
L32:
	ldc	1
L33:
	ifeq	L34
	ldc	"ERROR: Equal operator not working"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	0
	istore	2
	goto	L35
L34:
	iload	2
	ldc	1
	iand
	istore	2
L35:
	iload	0
	iload	1
	if_icmpne	L36
	ldc	0
	goto	L37
L36:
	ldc	1
L37:
	ifeq	L38
	iload	2
	ldc	1
	iand
	istore	2
	goto	L39
L38:
	ldc	"ERROR: Less Than operator not working"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	0
	istore	2
L39:
	iload	2
	ldc	0
	if_icmpeq	L40
	ldc	0
	goto	L41
L40:
	ldc	1
L41:
	ifeq	L42
	ldc	"ERRORS found in program"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	goto	L43
L42:
L43:
	ldc	"Test compeleted\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	return

	.limit	stack  25
	.limit	locals  3
	.end	method
