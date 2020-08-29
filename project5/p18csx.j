	.class	public  p18csx
	.super	java/lang/Object

	.method	public static  main([Ljava/lang/String;)V
	invokestatic	p18csx/main()V
	return
	.limit	stack  2
	.end	method

	.method	public static  main()V
	ldc	"Testing Program p18csx\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	invokestatic	CSXLib/readInt()I
	istore	0
	invokestatic	CSXLib/readInt()I
	istore	1
	invokestatic	CSXLib/readInt()I
	istore	2
	iload	0
	ldc	-5
	if_icmpne	L0
	ldc	0
	goto	L1
L0:
	ldc	1
L1:
	iload	1
	ldc	-13
	if_icmpne	L2
	ldc	0
	goto	L3
L2:
	ldc	1
L3:
	ior
	iload	2
	ldc	20
	if_icmpne	L4
	ldc	0
	goto	L5
L4:
	ldc	1
L5:
	ior
	ifeq	L6
	ldc	"\nERROR: Read error\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	goto	L7
L6:
L7:
	ldc	1000
	istore	0
	ldc	2000
	istore	1
	ldc	3000
	istore	2
	ldc	"Testing print:  Ouput is "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	0
	invokestatic	CSXLib/printInt(I)V
	iload	2
	invokestatic	CSXLib/printInt(I)V
	iload	1
	invokestatic	CSXLib/printInt(I)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"Test compeleted\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	return

	.limit	stack  25
	.limit	locals  3
	.end	method
