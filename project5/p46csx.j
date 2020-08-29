	.class	public  p46csx
	.super	java/lang/Object

	.method	public static  main([Ljava/lang/String;)V
	invokestatic	p46csx/main()V
	return
	.limit	stack  2
	.end	method

	.method	public static  main()V
	ldc	10
	newarray	int
	astore	0
	ldc	5
	newarray	char
	astore	1
	ldc	3
	newarray	boolean
	astore	2
	ldc	0
	istore	3
L0:
	iload	3
	ldc	10
	if_icmplt	L2
	ldc	0
	goto	L3
L2:
	ldc	1
L3:
	ifeq	L1
	aload	0
	iload	3
	iload	3
	iastore
	ldc	"myIntArray["
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	3
	invokestatic	CSXLib/printInt(I)V
	ldc	"] = "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	aload	0
	iload	3
	iaload
	invokestatic	CSXLib/printInt(I)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	3
	ldc	1
	iadd
	istore	3
	goto	L0
L1:
	aload	1
	ldc	0
	ldc	97
	castore
	aload	1
	ldc	1
	ldc	98
	castore
	aload	1
	ldc	2
	ldc	99
	castore
	aload	1
	ldc	3
	ldc	100
	castore
	aload	1
	ldc	4
	ldc	101
	castore
	ldc	0
	istore	3
L4:
	iload	3
	ldc	5
	if_icmplt	L6
	ldc	0
	goto	L7
L6:
	ldc	1
L7:
	ifeq	L5
	ldc	"myCharArray["
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	3
	invokestatic	CSXLib/printInt(I)V
	ldc	"] = "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	aload	1
	iload	3
	caload
	invokestatic	CSXLib/printChar(C)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	3
	ldc	1
	iadd
	istore	3
	goto	L4
L5:
	aload	2
	ldc	0
	ldc	0
	bastore
	aload	2
	ldc	1
	ldc	1
	bastore
	aload	2
	ldc	2
	ldc	0
	bastore
	ldc	0
	istore	3
L8:
	iload	3
	ldc	3
	if_icmplt	L10
	ldc	0
	goto	L11
L10:
	ldc	1
L11:
	ifeq	L9
	aload	2
	iload	3
	baload
	ldc	1
	if_icmpeq	L12
	ldc	0
	goto	L13
L12:
	ldc	1
L13:
	ifeq	L14
	ldc	"This should only print once"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	goto	L15
L14:
L15:
	iload	3
	ldc	1
	iadd
	istore	3
	goto	L8
L9:
	return

	.limit	stack  25
	.limit	locals  4
	.end	method
