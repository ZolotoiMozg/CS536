	.class	public  p32csx
	.super	java/lang/Object

	.field public static	cnt$  I
	.method	public static  main([Ljava/lang/String;)V
	invokestatic	p32csx/main()V
	return
	.limit	stack  2
	.end	method

	.method	public static  fac(I)I
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
	invokestatic	p32csx/fac(I)I
	iload	0
	imul
	ireturn
L3:
	ldc	0
	ireturn

	.limit	stack  25
	.limit	locals  1
	.end	method
	.method	public static  main()V
	ldc	"Testing Program p32csx"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	1
	putstatic	p32csx/cnt$  I
L4:
	getstatic	p32csx/cnt$  I
	ldc	9
	if_icmple	L6
	ldc	0
	goto	L7
L6:
	ldc	1
L7:
	ifeq	L5
	ldc	"Factorial of "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p32csx/cnt$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	" is "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p32csx/cnt$  I
	invokestatic	p32csx/fac(I)I
	invokestatic	CSXLib/printInt(I)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p32csx/cnt$  I
	ldc	1
	iadd
	putstatic	p32csx/cnt$  I
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
