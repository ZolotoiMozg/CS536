	.class	public  p43csx
	.super	java/lang/Object

	.method	public static  main([Ljava/lang/String;)V
	invokestatic	p43csx/main()V
	return
	.limit	stack  2
	.end	method

	.method	public static  main()V
	ldc	0
	istore	0
L0:
	iload	0
	ldc	10
	if_icmplt	L2
	ldc	0
	goto	L3
L2:
	ldc	1
L3:
	ifeq	L1
	iload	0
	ldc	5
	if_icmpeq	L4
	ldc	0
	goto	L5
L4:
	ldc	1
L5:
	ifeq	L6
	ldc	"break at a = "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	0
	invokestatic	CSXLib/printInt(I)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	goto	L1
	goto	L7
L6:
	iload	0
	ldc	1
	iadd
	istore	0
	goto	L0
L7:
	goto	L0
L1:
	ldc	0
	istore	0
L8:
	iload	0
	ldc	10
	if_icmplt	L10
	ldc	0
	goto	L11
L10:
	ldc	1
L11:
	ifeq	L9
	iload	0
	ldc	5
	if_icmpeq	L12
	ldc	0
	goto	L13
L12:
	ldc	1
L13:
	ifeq	L14
	iload	0
	ldc	1
	iadd
	istore	0
	goto	L8
	goto	L15
L14:
L15:
	ldc	"a = "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	0
	invokestatic	CSXLib/printInt(I)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	0
	ldc	1
	iadd
	istore	0
	goto	L8
L9:
	ldc	"a = 5 should not have been written"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"Test completed"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	return

	.limit	stack  25
	.limit	locals  1
	.end	method
