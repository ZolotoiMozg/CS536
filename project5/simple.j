	.class	public  simple
	.super	java/lang/Object

	.method	public static  main([Ljava/lang/String;)V
	invokestatic	simple/main()V
	return
	.limit	stack  2
	.end	method

	.method	public static  main()V
	ldc	1
	istore	0
L0:
	iload	0
	ldc	5
	if_icmpne	L2
	ldc	0
	goto	L3
L2:
	ldc	1
L3:
	ifeq	L1
	ldc	"i not 5"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	0
	ldc	1
	iadd
	istore	0
	goto	L0
L1:
	return

	.limit	stack  25
	.limit	locals  1
	.end	method
