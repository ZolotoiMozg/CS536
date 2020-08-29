	.class	public  testCSX
	.super	java/lang/Object

	.field public static	a$  I
	.field public static	b$  I
	.field public static	c$ I =   0
	.field public static	z$  [I
	.field public static	y$  [C
	.method	public static  main([Ljava/lang/String;)V
	ldc	12
	newarray	int
	putstatic	testCSX/z$  [I
	ldc	1
	newarray	char
	putstatic	testCSX/y$  [C
	invokestatic	testCSX/main()V
	return
	.limit	stack  2
	.end	method

	.method	public static  f()I
	getstatic	testCSX/c$  I
	ldc	1
	iadd
	putstatic	testCSX/c$  I
	getstatic	testCSX/c$  I
	ireturn
	ldc	0
	ireturn

	.limit	stack  25
	.limit	locals  0
	.end	method
	.method	public static  ppp(CI)V
	iload	0
	invokestatic	CSXLib/printChar(C)V
	iload	1
	invokestatic	CSXLib/printInt(I)V
	ldc	10
	invokestatic	CSXLib/printChar(C)V
	return
	return

	.limit	stack  25
	.limit	locals  2
	.end	method
	.method	public static  main()V
	ldc	123
	putstatic	testCSX/a$  I
	ldc	456
	putstatic	testCSX/b$  I
	getstatic	testCSX/z$  [I
	invokestatic	testCSX/f()I
	invokestatic	testCSX/f()I
	iastore
	getstatic	testCSX/z$  [I
	invokestatic	testCSX/f()I
	invokestatic	testCSX/f()I
	iastore
	getstatic	testCSX/z$  [I
	invokestatic	testCSX/f()I
	invokestatic	testCSX/f()I
	iastore
	getstatic	testCSX/z$  [I
	invokestatic	testCSX/f()I
	invokestatic	testCSX/f()I
	iastore
	getstatic	testCSX/z$  [I
	invokestatic	testCSX/f()I
	invokestatic	testCSX/f()I
	iastore
	getstatic	testCSX/z$  [I
	invokestatic	testCSX/f()I
	invokestatic	testCSX/f()I
	iastore
	ldc	0
	putstatic	testCSX/c$  I
	getstatic	testCSX/z$  [I
	invokestatic	testCSX/f()I
	dup2
	iaload
	ldc	1
	iadd
	iastore
	getstatic	testCSX/z$  [I
	invokestatic	testCSX/f()I
	dup2
	iaload
	ldc	1
	iadd
	iastore
	getstatic	testCSX/z$  [I
	invokestatic	testCSX/f()I
	dup2
	iaload
	ldc	1
	iadd
	iastore
	getstatic	testCSX/z$  [I
	invokestatic	testCSX/f()I
	dup2
	iaload
	ldc	1
	iadd
	iastore
	getstatic	testCSX/z$  [I
	invokestatic	testCSX/f()I
	dup2
	iaload
	ldc	1
	iadd
	iastore
	getstatic	testCSX/z$  [I
	invokestatic	testCSX/f()I
	dup2
	iaload
	ldc	1
	iadd
	iastore
	getstatic	testCSX/z$  [I
	ldc	0
	iaload
	invokestatic	CSXLib/printInt(I)V
	ldc	10
	invokestatic	CSXLib/printChar(C)V
	getstatic	testCSX/z$  [I
	ldc	1
	iaload
	invokestatic	CSXLib/printInt(I)V
	ldc	10
	invokestatic	CSXLib/printChar(C)V
	getstatic	testCSX/z$  [I
	ldc	2
	iaload
	invokestatic	CSXLib/printInt(I)V
	ldc	10
	invokestatic	CSXLib/printChar(C)V
	getstatic	testCSX/z$  [I
	ldc	3
	iaload
	invokestatic	CSXLib/printInt(I)V
	ldc	10
	invokestatic	CSXLib/printChar(C)V
	getstatic	testCSX/z$  [I
	ldc	4
	iaload
	invokestatic	CSXLib/printInt(I)V
	ldc	10
	invokestatic	CSXLib/printChar(C)V
	getstatic	testCSX/z$  [I
	ldc	5
	iaload
	invokestatic	CSXLib/printInt(I)V
	ldc	10
	invokestatic	CSXLib/printChar(C)V
	getstatic	testCSX/z$  [I
	ldc	6
	iaload
	invokestatic	CSXLib/printInt(I)V
	ldc	10
	invokestatic	CSXLib/printChar(C)V
	getstatic	testCSX/z$  [I
	ldc	7
	iaload
	invokestatic	CSXLib/printInt(I)V
	ldc	10
	invokestatic	CSXLib/printChar(C)V
	getstatic	testCSX/z$  [I
	ldc	8
	iaload
	invokestatic	CSXLib/printInt(I)V
	ldc	10
	invokestatic	CSXLib/printChar(C)V
	getstatic	testCSX/z$  [I
	ldc	9
	iaload
	invokestatic	CSXLib/printInt(I)V
	ldc	10
	invokestatic	CSXLib/printChar(C)V
	getstatic	testCSX/z$  [I
	ldc	10
	iaload
	invokestatic	CSXLib/printInt(I)V
	ldc	10
	invokestatic	CSXLib/printChar(C)V
	getstatic	testCSX/z$  [I
	ldc	11
	iaload
	invokestatic	CSXLib/printInt(I)V
	ldc	10
	invokestatic	CSXLib/printChar(C)V
	getstatic	testCSX/a$  I
	getstatic	testCSX/a$  I
	iadd
	putstatic	testCSX/a$  I
	ldc	100
	invokestatic	CSXLib/printInt(I)V
	ldc	10
	invokestatic	CSXLib/printChar(C)V
	getstatic	testCSX/a$  I
	getstatic	testCSX/b$  I
	iadd
	ldc	100
	isub
	invokestatic	CSXLib/printInt(I)V
	ldc	10
	invokestatic	CSXLib/printChar(C)V
	getstatic	testCSX/c$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	10
	invokestatic	CSXLib/printChar(C)V
	ldc	97
	getstatic	testCSX/a$  I
	invokestatic	testCSX/ppp(CI)V
	ldc	10
	invokestatic	CSXLib/printChar(C)V
	ldc	10
	invokestatic	CSXLib/printChar(C)V
	ldc	97
	istore	0
	iload	0
	ldc	1
	iadd
	istore	0
	iload	0
	getstatic	testCSX/a$  I
	invokestatic	testCSX/ppp(CI)V
	getstatic	testCSX/b$  I
	putstatic	testCSX/a$  I
	getstatic	testCSX/a$  I
	ldc	666
	if_icmpeq	L0
	ldc	0
	goto	L1
L0:
	ldc	1
L1:
	ifeq	L2
	ldc	105
	ldc	1
	invokestatic	testCSX/ppp(CI)V
	goto	L3
L2:
	ldc	101
	ldc	2
	invokestatic	testCSX/ppp(CI)V
L3:
	getstatic	testCSX/a$  I
	ldc	666
	if_icmplt	L4
	ldc	0
	goto	L5
L4:
	ldc	1
L5:
	ifeq	L6
	ldc	105
	ldc	10
	invokestatic	testCSX/ppp(CI)V
L6:
	return
	return

	.limit	stack  25
	.limit	locals  1
	.end	method
