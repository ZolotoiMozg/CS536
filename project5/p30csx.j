	.class	public  p30csx
	.super	java/lang/Object

	.field public static	n$  I
	.field public static	fact$  I
	.method	public static  main([Ljava/lang/String;)V
	invokestatic	p30csx/main()V
	return
	.limit	stack  2
	.end	method

	.method	public static  factorial()V
	getstatic	p30csx/n$  I
	ldc	0
	if_icmpeq	L0
	ldc	0
	goto	L1
L0:
	ldc	1
L1:
	ifeq	L2
	ldc	1
	putstatic	p30csx/fact$  I
	goto	L3
L2:
	getstatic	p30csx/n$  I
	ldc	1
	isub
	putstatic	p30csx/n$  I
	invokestatic	p30csx/factorial()V
	getstatic	p30csx/n$  I
	ldc	1
	iadd
	putstatic	p30csx/n$  I
	getstatic	p30csx/fact$  I
	getstatic	p30csx/n$  I
	imul
	putstatic	p30csx/fact$  I
L3:
	return

	.limit	stack  25
	.limit	locals  0
	.end	method
	.method	public static  main()V
	ldc	"Testing Program p30csx"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	6
	putstatic	p30csx/n$  I
	invokestatic	p30csx/factorial()V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"Factorial of "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p30csx/n$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	" is "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p30csx/fact$  I
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
