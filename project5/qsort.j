	.class	public  qsort
	.super	java/lang/Object

	.method	public static  main([Ljava/lang/String;)V
	invokestatic	qsort/main()V
	return
	.limit	stack  2
	.end	method

	.method	public static  qsort([III)V
	iload	1
	iload	2
	iadd
	ldc	2
	idiv
	istore	3
	iload	1
	iload	2
	if_icmpge	L0
	ldc	0
	goto	L1
L0:
	ldc	1
L1:
	ifeq	L2
	return
L2:
	aload	0
	iload	3
	iaload
	istore	6
	iload	1
	istore	4
	iload	2
	istore	5
L3:
	iload	4
	iload	5
	if_icmple	L5
	ldc	0
	goto	L6
L5:
	ldc	1
L6:
	ifeq	L4
L7:
	aload	0
	iload	4
	iaload
	iload	6
	if_icmplt	L9
	ldc	0
	goto	L10
L9:
	ldc	1
L10:
	ifeq	L8
	iload	4
	ldc	1
	iadd
	istore	4
	goto	L7
L8:
L11:
	aload	0
	iload	5
	iaload
	iload	6
	if_icmpgt	L13
	ldc	0
	goto	L14
L13:
	ldc	1
L14:
	ifeq	L12
	iload	5
	ldc	1
	isub
	istore	5
	goto	L11
L12:
	iload	4
	iload	5
	if_icmple	L15
	ldc	0
	goto	L16
L15:
	ldc	1
L16:
	ifeq	L17
	aload	0
	iload	4
	iaload
	istore	7
	aload	0
	iload	4
	aload	0
	iload	5
	iaload
	iastore
	aload	0
	iload	5
	iload	7
	iastore
	iload	4
	ldc	1
	iadd
	istore	4
	iload	5
	ldc	1
	isub
	istore	5
L17:
	goto	L3
L4:
	aload	0
	iload	1
	iload	5
	invokestatic	qsort/qsort([III)V
	aload	0
	iload	4
	iload	2
	invokestatic	qsort/qsort([III)V
	return

	.limit	stack  25
	.limit	locals  8
	.end	method
	.method	public static  main()V
	ldc	10000
	istore	0
	ldc	10000
	newarray	int
	astore	1
	aload	1
	ldc	0
	ldc	97
	iastore
	ldc	1
	istore	2
L18:
	iload	2
	iload	0
	if_icmplt	L20
	ldc	0
	goto	L21
L20:
	ldc	1
L21:
	ifeq	L19
	aload	1
	iload	2
	ldc	1
	isub
	iaload
	ldc	32719
	imul
	ldc	997
	iadd
	istore	3
	iload	3
	iload	3
	ldc	16301
	idiv
	ldc	16301
	imul
	isub
	istore	3
	aload	1
	iload	2
	iload	3
	iastore
	aload	1
	iload	2
	iaload
	invokestatic	CSXLib/printInt(I)V
	ldc	32
	invokestatic	CSXLib/printChar(C)V
	iload	2
	ldc	1
	iadd
	istore	2
	goto	L18
L19:
	ldc	10
	invokestatic	CSXLib/printChar(C)V
	aload	1
	ldc	0
	iload	0
	ldc	1
	isub
	invokestatic	qsort/qsort([III)V
	ldc	0
	istore	2
L22:
	iload	2
	iload	0
	if_icmplt	L24
	ldc	0
	goto	L25
L24:
	ldc	1
L25:
	ifeq	L23
	aload	1
	iload	2
	iaload
	invokestatic	CSXLib/printInt(I)V
	ldc	32
	invokestatic	CSXLib/printChar(C)V
	iload	2
	ldc	1
	iadd
	istore	2
	goto	L22
L23:
	ldc	10
	invokestatic	CSXLib/printChar(C)V
	return

	.limit	stack  25
	.limit	locals  4
	.end	method
