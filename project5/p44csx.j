	.class	public  p44csx
	.super	java/lang/Object

	.method	public static  main([Ljava/lang/String;)V
	invokestatic	p44csx/main()V
	return
	.limit	stack  2
	.end	method

	.method	public static  main()V
	ldc	97
	istore	0
	ldc	98
	istore	1
	iload	0
	iload	1
	if_icmplt	L0
	ldc	0
	goto	L1
L0:
	ldc	1
L1:
	ifeq	L2
	ldc	"This should print"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	goto	L3
L2:
L3:
	iload	0
	ldc	1
	iadd
	ldc	127
	iand
	istore	0
	iload	0
	iload	1
	if_icmpeq	L4
	ldc	0
	goto	L5
L4:
	ldc	1
L5:
	ifeq	L6
	ldc	"This should also print"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	goto	L7
L6:
L7:
	return

	.limit	stack  25
	.limit	locals  2
	.end	method
