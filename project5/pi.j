	.class	public  pi
	.super	java/lang/Object

	.method	public static  main([Ljava/lang/String;)V
	invokestatic	pi/main()V
	return
	.limit	stack  2
	.end	method

	.method	public static  print4d(I)V
	iload	0
	ldc	1000
	if_icmplt	L0
	ldc	0
	goto	L1
L0:
	ldc	1
L1:
	ifeq	L2
	ldc	0
	invokestatic	CSXLib/printInt(I)V
	goto	L3
L2:
L3:
	iload	0
	ldc	100
	if_icmplt	L4
	ldc	0
	goto	L5
L4:
	ldc	1
L5:
	ifeq	L6
	ldc	0
	invokestatic	CSXLib/printInt(I)V
	goto	L7
L6:
L7:
	iload	0
	ldc	10
	if_icmplt	L8
	ldc	0
	goto	L9
L8:
	ldc	1
L9:
	ifeq	L10
	ldc	0
	invokestatic	CSXLib/printInt(I)V
	goto	L11
L10:
L11:
	iload	0
	invokestatic	CSXLib/printInt(I)V
	return

	.limit	stack  25
	.limit	locals  1
	.end	method
	.method	public static  main()V
	ldc	10000
	istore	0
	ldc	0
	istore	1
	ldc	2800
	istore	2
	ldc	0
	istore	3
	ldc	0
	istore	4
	ldc	2801
	newarray	int
	astore	5
	ldc	0
	istore	6
L12:
	iload	1
	iload	2
	isub
	ldc	0
	if_icmpne	L14
	ldc	0
	goto	L15
L14:
	ldc	1
L15:
	ifeq	L13
	aload	5
	iload	1
	iload	0
	ldc	5
	idiv
	iastore
	iload	1
	ldc	1
	iadd
	istore	1
	goto	L12
L13:
L16:
	ldc	1
	ifeq	L17
	ldc	0
	istore	3
	iload	2
	ldc	2
	imul
	istore	6
	iload	6
	ldc	0
	if_icmpeq	L18
	ldc	0
	goto	L19
L18:
	ldc	1
L19:
	ifeq	L20
	goto	L17
	goto	L21
L20:
L21:
	iload	2
	istore	1
L22:
	ldc	1
	ifeq	L23
	iload	3
	aload	5
	iload	1
	iaload
	iload	0
	imul
	iadd
	istore	3
	iload	6
	ldc	1
	isub
	istore	6
	aload	5
	iload	1
	iload	3
	iload	3
	iload	6
	idiv
	iload	6
	imul
	isub
	iastore
	iload	3
	iload	6
	idiv
	istore	3
	iload	6
	ldc	1
	isub
	istore	6
	iload	1
	ldc	1
	isub
	istore	1
	iload	1
	ldc	0
	if_icmpeq	L24
	ldc	0
	goto	L25
L24:
	ldc	1
L25:
	ifeq	L26
	goto	L23
	goto	L27
L26:
L27:
	iload	3
	iload	1
	imul
	istore	3
	goto	L22
L23:
	iload	2
	ldc	14
	isub
	istore	2
	iload	4
	iload	3
	iload	0
	idiv
	iadd
	invokestatic	pi/print4d(I)V
	iload	3
	iload	3
	iload	0
	idiv
	iload	0
	imul
	isub
	istore	4
	goto	L16
L17:
	ldc	10
	invokestatic	CSXLib/printChar(C)V
	return

	.limit	stack  25
	.limit	locals  7
	.end	method
