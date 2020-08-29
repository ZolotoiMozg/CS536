	.class	public  p29csx
	.super	java/lang/Object

	.field public static	x$  I
	.field public static	y$  I
	.field public static	z$  I
	.method	public static  main([Ljava/lang/String;)V
	invokestatic	p29csx/main()V
	return
	.limit	stack  2
	.end	method

	.method	public static  local1()V
	ldc	10
	istore	0
	ldc	20
	istore	1
	ldc	"In procedure local1: Expecting 10 and 20\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"   X = "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	0
	invokestatic	CSXLib/printInt(I)V
	ldc	"   Y = "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	1
	invokestatic	CSXLib/printInt(I)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	10000
	istore	2
	return

	.limit	stack  25
	.limit	locals  3
	.end	method
	.method	public static  main()V
	ldc	"Testing Program p29csx\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	100
	putstatic	p29csx/x$  I
	ldc	200
	putstatic	p29csx/y$  I
	ldc	-1
	putstatic	p29csx/z$  I
	ldc	" X = "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p29csx/x$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	" Y = "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p29csx/y$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	" Z = "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p29csx/z$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	invokestatic	p29csx/local1()V
	ldc	" X = "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p29csx/x$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	" Y = "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p29csx/y$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	" Z = "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p29csx/z$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"Test compeleted\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	return

	.limit	stack  25
	.limit	locals  0
	.end	method
