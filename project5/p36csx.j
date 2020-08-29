	.class	public  p36csx
	.super	java/lang/Object

	.field public static	cnt$  I
	.field public static	lim$ I =   15
	.method	public static  main([Ljava/lang/String;)V
	invokestatic	p36csx/main()V
	return
	.limit	stack  2
	.end	method

	.method	public static  fib(I)I
	iload	0
	ldc	1
	if_icmple	L0
	ldc	0
	goto	L1
L0:
	ldc	1
L1:
	ifeq	L2
	ldc	1
	ireturn
	goto	L3
L2:
	iload	0
	ldc	1
	isub
	invokestatic	p36csx/fib(I)I
	iload	0
	ldc	2
	isub
	invokestatic	p36csx/fib(I)I
	iadd
	ireturn
L3:
	ldc	0
	ireturn

	.limit	stack  25
	.limit	locals  1
	.end	method
	.method	public static  main()V
	ldc	"Testing Program p36csx"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	0
	putstatic	p36csx/cnt$  I
L4:
	getstatic	p36csx/cnt$  I
	getstatic	p36csx/lim$  I
	if_icmple	L6
	ldc	0
	goto	L7
L6:
	ldc	1
L7:
	ifeq	L5
	ldc	"Fibonacci of "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p36csx/cnt$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	" is "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p36csx/cnt$  I
	invokestatic	p36csx/fib(I)I
	invokestatic	CSXLib/printInt(I)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p36csx/cnt$  I
	ldc	1
	iadd
	putstatic	p36csx/cnt$  I
	goto	L4
L5:
	ldc	"Test compeleted"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	return

	.limit	stack  25
	.limit	locals  0
	.end	method
