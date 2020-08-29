	.class	public  p22csx
	.super	java/lang/Object

	.method	public static  main([Ljava/lang/String;)V
	invokestatic	p22csx/main()V
	return
	.limit	stack  2
	.end	method

	.method	public static  main()V
	ldc	"Testing Program p22csx\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	1
	istore	0
L0:
	iload	0
	ldc	5
	if_icmplt	L2
	ldc	0
	goto	L3
L2:
	ldc	1
L3:
	ifeq	L1
	ldc	"Iteration "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	0
	invokestatic	CSXLib/printInt(I)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	0
	ldc	1
	iadd
	istore	0
	goto	L0
L1:
	ldc	1
	istore	0
L4:
	iload	0
	ldc	3
	if_icmplt	L6
	ldc	0
	goto	L7
L6:
	ldc	1
L7:
	ifeq	L5
	ldc	1
	istore	1
L8:
	iload	1
	ldc	3
	if_icmplt	L10
	ldc	0
	goto	L11
L10:
	ldc	1
L11:
	ifeq	L9
	ldc	"( "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	0
	invokestatic	CSXLib/printInt(I)V
	iload	1
	invokestatic	CSXLib/printInt(I)V
	ldc	" )"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	1
	ldc	1
	iadd
	istore	1
	goto	L8
L9:
	iload	0
	ldc	1
	iadd
	istore	0
	goto	L4
L5:
	ldc	1
	istore	0
	ldc	1
	istore	1
L12:
	iload	0
	ldc	3
	if_icmplt	L14
	ldc	0
	goto	L15
L14:
	ldc	1
L15:
	ifeq	L13
	iload	0
	ldc	1
	iadd
	istore	0
	iload	0
	ldc	2
	if_icmpeq	L16
	ldc	0
	goto	L17
L16:
	ldc	1
L17:
	ifeq	L18
	iload	1
	ldc	1
	iadd
	istore	1
	goto	L19
L18:
L19:
	goto	L12
L13:
	ldc	"i = "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	0
	invokestatic	CSXLib/printInt(I)V
	ldc	" j = "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	1
	invokestatic	CSXLib/printInt(I)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"Test compeleted"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	return

	.limit	stack  25
	.limit	locals  3
	.end	method
