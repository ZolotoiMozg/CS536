	.class	public  p47csx
	.super	java/lang/Object

	.method	public static  main([Ljava/lang/String;)V
	invokestatic	p47csx/main()V
	return
	.limit	stack  2
	.end	method

	.method	public static  main()V
	ldc	10
	newarray	int
	astore	0
	ldc	10
	newarray	int
	astore	1
	ldc	0
	istore	2
L0:
	iload	2
	ldc	10
	if_icmplt	L2
	ldc	0
	goto	L3
L2:
	ldc	1
L3:
	ifeq	L1
	aload	0
	iload	2
	iload	2
	iastore
	iload	2
	ldc	1
	iadd
	istore	2
	goto	L0
L1:
	aload	1
	aload	0
	invokestatic	CSXLib/cloneIntArray([I)[I
	invokestatic	CSXLib/checkIntArrayLength([I[I)[I
	astore	1
	ldc	0
	istore	2
L4:
	iload	2
	ldc	10
	if_icmplt	L6
	ldc	0
	goto	L7
L6:
	ldc	1
L7:
	ifeq	L5
	ldc	"myIntArray2["
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	2
	invokestatic	CSXLib/printInt(I)V
	ldc	"] = "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	aload	1
	iload	2
	iaload
	invokestatic	CSXLib/printInt(I)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	2
	ldc	1
	iadd
	istore	2
	goto	L4
L5:
	return

	.limit	stack  25
	.limit	locals  3
	.end	method
