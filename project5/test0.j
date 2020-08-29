	.class	public  test0
	.super	java/lang/Object

	.field public static	x$ I =   0
	.field public static	y$ I =   5
	.field public static	xx$ I =   120
	.field public static	k$ I =   1
	.field public static	sy$  [C
	.field public static	z$ I =   4
	.method	public static  main([Ljava/lang/String;)V
	ldc	11
	newarray	char
	putstatic	test0/sy$  [C
	invokestatic	test0/main()V
	return
	.limit	stack  2
	.end	method

	.method	public static  f(I[CZI)V
	return
	return

	.limit	stack  25
	.limit	locals  4
	.end	method
	.method	public static  g(I)V
	ldc	3
	newarray	char
	astore	1
	return
	return

	.limit	stack  25
	.limit	locals  2
	.end	method
	.method	public static  foo()V
	ldc	0
	istore	0
	ldc	0
	istore	1
	ldc	0
	istore	2
	ldc	5
	newarray	char
	astore	3
L0:
	ldc	1
	ifeq	L1
	return
	goto	L0
L1:
	iload	0
	iload	1
	iadd
	istore	0
	iload	0
	iload	1
	isub
	istore	0
	iload	0
	iload	1
	imul
	istore	0
	iload	0
	iload	1
	idiv
	istore	0
	iload	2
	ifeq	L2
	ldc	0
	goto	L3
L2:
	ldc	1
L3:
	istore	2
	iload	0
	iload	1
	if_icmpeq	L4
	ldc	0
	goto	L5
L4:
	ldc	1
L5:
	istore	2
	iload	0
	iload	1
	if_icmpne	L6
	ldc	0
	goto	L7
L6:
	ldc	1
L7:
	istore	2
	iload	0
	iload	1
	if_icmplt	L8
	ldc	0
	goto	L9
L8:
	ldc	1
L9:
	istore	2
	iload	0
	iload	1
	if_icmpgt	L10
	ldc	0
	goto	L11
L10:
	ldc	1
L11:
	istore	2
	iload	0
	iload	1
	if_icmple	L12
	ldc	0
	goto	L13
L12:
	ldc	1
L13:
	istore	2
	iload	0
	iload	1
	if_icmpge	L14
	ldc	0
	goto	L15
L14:
	ldc	1
L15:
	istore	2
	iload	1
	istore	0
	ldc	50
	istore	0
	aload	3
	ldc	"Hello"
	invokestatic	CSXLib/convertString(Ljava/lang/String;)[C
	invokestatic	CSXLib/checkCharArrayLength([C[C)[C
	astore	3
	ldc	1
	istore	2
	ldc	0
	istore	2
	ldc	-50
	istore	0
	iload	1
	istore	0
	iload	0
	iload	1
	if_icmpge	L16
	ldc	0
	goto	L17
L16:
	ldc	1
L17:
	ifeq	L18
	ldc	1
	istore	4
L19:
	iload	4
	ldc	0
	if_icmpgt	L21
	ldc	0
	goto	L22
L21:
	ldc	1
L22:
	ifeq	L20
	iload	1
	istore	5
	iload	1
	istore	5
	goto	L19
L20:
	goto	L23
L18:
L23:
	iload	0
	istore	0
	return

	.limit	stack  25
	.limit	locals  6
	.end	method
	.method	public static  main()V
	ldc	1
	invokestatic	CSXLib/printInt(I)V
	return
	return

	.limit	stack  25
	.limit	locals  0
	.end	method
