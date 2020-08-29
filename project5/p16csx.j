	.class	public  p16csx
	.super	java/lang/Object

	.method	public static  main([Ljava/lang/String;)V
	invokestatic	p16csx/main()V
	return
	.limit	stack  2
	.end	method

	.method	public static  main()V
	ldc	1
	istore	0
	ldc	1
	istore	1
	ldc	0
	istore	2
	ldc	"Testing program p16csx\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
L0:
	iload	0
	ldc	100
	if_icmple	L2
	ldc	0
	goto	L3
L2:
	ldc	1
L3:
	ifeq	L1
	ldc	1
	istore	1
L4:
	iload	1
	ldc	100
	if_icmple	L6
	ldc	0
	goto	L7
L6:
	ldc	1
L7:
	ifeq	L5
	iload	1
	ldc	1
	iadd
	istore	1
	iload	2
	ldc	1
	iadd
	istore	2
	goto	L4
L5:
	iload	0
	ldc	1
	iadd
	istore	0
	goto	L0
L1:
	ldc	"The value of sum is "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	2
	invokestatic	CSXLib/printInt(I)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	return

	.limit	stack  25
	.limit	locals  3
	.end	method
