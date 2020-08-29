	.class	public  p09csx
	.super	java/lang/Object

	.method	public static  main([Ljava/lang/String;)V
	invokestatic	p09csx/main()V
	return
	.limit	stack  2
	.end	method

	.method	public static  main()V
	ldc	"Testing program p09csx\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	10
	istore	0
	ldc	-10
	istore	1
	iload	0
	iload	0
	if_icmpeq	L0
	ldc	0
	goto	L1
L0:
	ldc	1
L1:
	istore	2
	iload	0
	iload	0
	if_icmpne	L2
	ldc	0
	goto	L3
L2:
	ldc	1
L3:
	istore	3
	iload	0
	iload	0
	if_icmple	L4
	ldc	0
	goto	L5
L4:
	ldc	1
L5:
	istore	4
	iload	0
	iload	0
	if_icmplt	L6
	ldc	0
	goto	L7
L6:
	ldc	1
L7:
	istore	5
	iload	0
	iload	0
	if_icmpge	L8
	ldc	0
	goto	L9
L8:
	ldc	1
L9:
	istore	6
	iload	0
	iload	0
	if_icmpgt	L10
	ldc	0
	goto	L11
L10:
	ldc	1
L11:
	istore	7
	iload	2
	invokestatic	CSXLib/printBool(Z)V
	ldc	"\t"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	3
	invokestatic	CSXLib/printBool(Z)V
	ldc	"\t"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	4
	invokestatic	CSXLib/printBool(Z)V
	ldc	"\t"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	5
	invokestatic	CSXLib/printBool(Z)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	6
	invokestatic	CSXLib/printBool(Z)V
	ldc	"\t"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	7
	invokestatic	CSXLib/printBool(Z)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	0
	iload	1
	if_icmpeq	L12
	ldc	0
	goto	L13
L12:
	ldc	1
L13:
	istore	2
	iload	0
	iload	1
	if_icmpne	L14
	ldc	0
	goto	L15
L14:
	ldc	1
L15:
	istore	3
	iload	0
	iload	1
	if_icmple	L16
	ldc	0
	goto	L17
L16:
	ldc	1
L17:
	istore	4
	iload	0
	iload	1
	if_icmplt	L18
	ldc	0
	goto	L19
L18:
	ldc	1
L19:
	istore	5
	iload	0
	iload	1
	if_icmpge	L20
	ldc	0
	goto	L21
L20:
	ldc	1
L21:
	istore	6
	iload	0
	iload	1
	if_icmpgt	L22
	ldc	0
	goto	L23
L22:
	ldc	1
L23:
	istore	7
	iload	2
	invokestatic	CSXLib/printBool(Z)V
	ldc	"\t"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	3
	invokestatic	CSXLib/printBool(Z)V
	ldc	"\t"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	4
	invokestatic	CSXLib/printBool(Z)V
	ldc	"\t"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	5
	invokestatic	CSXLib/printBool(Z)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	6
	invokestatic	CSXLib/printBool(Z)V
	ldc	"\t"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	7
	invokestatic	CSXLib/printBool(Z)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	return

	.limit	stack  25
	.limit	locals  8
	.end	method
