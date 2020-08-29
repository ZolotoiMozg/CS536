	.class	public  p33csx
	.super	java/lang/Object

	.field public static	x$  I
	.field public static	y$  I
	.field public static	li$  I
	.method	public static  main([Ljava/lang/String;)V
	invokestatic	p33csx/main()V
	return
	.limit	stack  2
	.end	method

	.method	public static  lessthanzero(II)V
	iload	0
	ldc	0
	if_icmplt	L0
	ldc	0
	goto	L1
L0:
	ldc	1
L1:
	iload	1
	ldc	0
	if_icmplt	L2
	ldc	0
	goto	L3
L2:
	ldc	1
L3:
	iand
	putstatic	p33csx/li$  I
	return

	.limit	stack  25
	.limit	locals  2
	.end	method
	.method	public static  main()V
	ldc	"Testing Program p33csx\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	-20
	putstatic	p33csx/x$  I
	ldc	-10
	putstatic	p33csx/y$  I
	ldc	" Output "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"X = "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p33csx/x$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	" * "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"Y = "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p33csx/y$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	" = "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p33csx/x$  I
	getstatic	p33csx/y$  I
	imul
	invokestatic	CSXLib/printInt(I)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p33csx/x$  I
	getstatic	p33csx/y$  I
	invokestatic	p33csx/lessthanzero(II)V
	ldc	" Output: Are both X("
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p33csx/x$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	") and Y("
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p33csx/y$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	") less than zero??"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"   Answer is : "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p33csx/li$  I
	invokestatic	CSXLib/printBool(Z)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	4
	putstatic	p33csx/y$  I
	ldc	8
	putstatic	p33csx/x$  I
	ldc	" Output "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"X = "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p33csx/x$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	" / "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"Y = "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p33csx/y$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	" = "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p33csx/x$  I
	getstatic	p33csx/y$  I
	idiv
	invokestatic	CSXLib/printInt(I)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	4
	putstatic	p33csx/y$  I
	ldc	2
	putstatic	p33csx/x$  I
	ldc	" Output "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"X = "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p33csx/x$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	" / "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"Y = "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p33csx/y$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	" = "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p33csx/x$  I
	getstatic	p33csx/y$  I
	idiv
	invokestatic	CSXLib/printInt(I)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"Test compeleted\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	return

	.limit	stack  25
	.limit	locals  0
	.end	method
