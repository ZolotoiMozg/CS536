	.class	public  p15csx
	.super	java/lang/Object

	.method	public static  main([Ljava/lang/String;)V
	invokestatic	p15csx/main()V
	return
	.limit	stack  2
	.end	method

	.method	public static  main()V
	ldc	0
	istore	0
	ldc	"Testing program p15csx\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
L0:
	iload	0
	ldc	100
	if_icmplt	L2
	ldc	0
	goto	L3
L2:
	ldc	1
L3:
	ifeq	L1
	iload	0
	ldc	1
	iadd
	istore	0
	goto	L0
L1:
	ldc	"The value of i is "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	0
	invokestatic	CSXLib/printInt(I)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	return

	.limit	stack  25
	.limit	locals  1
	.end	method
