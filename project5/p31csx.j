	.class	public  p31csx
	.super	java/lang/Object

	.field public static	x$  I
	.field public static	y$  I
	.field public static	n$  I
	.field public static	fct$  I
	.method	public static  main([Ljava/lang/String;)V
	invokestatic	p31csx/main()V
	return
	.limit	stack  2
	.end	method

	.method	public static  proc1(I)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"Procedure 1 entered"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"X1 = (10?) "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	0
	invokestatic	CSXLib/printInt(I)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"Procedure 1 exited"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	return

	.limit	stack  25
	.limit	locals  1
	.end	method
	.method	public static  proc2(I)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"Procedure 2 entered"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	" Y1 = (15?) "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	0
	invokestatic	CSXLib/printInt(I)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"Procedure 2 exited"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	return

	.limit	stack  25
	.limit	locals  1
	.end	method
	.method	public static  main()V
	ldc	"Testing Program p31csx"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	10
	putstatic	p31csx/x$  I
	getstatic	p31csx/x$  I
	invokestatic	p31csx/proc1(I)V
	ldc	15
	putstatic	p31csx/y$  I
	getstatic	p31csx/y$  I
	invokestatic	p31csx/proc2(I)V
	ldc	" X = (10?)"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p31csx/x$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	" Y = (15?) "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p31csx/y$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"Test compeleted"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	return

	.limit	stack  25
	.limit	locals  0
	.end	method
