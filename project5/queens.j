	.class	public  queens
	.super	java/lang/Object

	.field public static	solCount$  I
	.method	public static  main([Ljava/lang/String;)V
	invokestatic	queens/main()V
	return
	.limit	stack  2
	.end	method

	.method	public static  printSolution(I[I)V
	ldc	0
	istore	2
L0:
	iload	2
	iload	0
	if_icmplt	L2
	ldc	0
	goto	L3
L2:
	ldc	1
L3:
	ifeq	L1
	ldc	0
	istore	3
L4:
	iload	3
	iload	0
	if_icmplt	L6
	ldc	0
	goto	L7
L6:
	ldc	1
L7:
	ifeq	L5
	iload	3
	aload	1
	iload	2
	iaload
	if_icmpeq	L8
	ldc	0
	goto	L9
L8:
	ldc	1
L9:
	ifeq	L10
	ldc	120
	invokestatic	CSXLib/printChar(C)V
	goto	L11
L10:
	ldc	46
	invokestatic	CSXLib/printChar(C)V
L11:
	iload	3
	ldc	1
	iadd
	istore	3
	goto	L4
L5:
	ldc	10
	invokestatic	CSXLib/printChar(C)V
	iload	2
	ldc	1
	iadd
	istore	2
	goto	L0
L1:
	ldc	10
	invokestatic	CSXLib/printChar(C)V
	return

	.limit	stack  25
	.limit	locals  4
	.end	method
	.method	public static  solve(II[Z[Z[Z[I)V
	iload	1
	iload	0
	if_icmpeq	L12
	ldc	0
	goto	L13
L12:
	ldc	1
L13:
	ifeq	L14
	getstatic	queens/solCount$  I
	ldc	1
	iadd
	putstatic	queens/solCount$  I
	iload	0
	aload	5
	invokestatic	queens/printSolution(I[I)V
	return
	goto	L15
L14:
L15:
	ldc	0
	istore	6
L16:
	iload	6
	iload	0
	if_icmplt	L18
	ldc	0
	goto	L19
L18:
	ldc	1
L19:
	ifeq	L17
	aload	2
	iload	6
	baload
	ifeq	L20
	ldc	0
	goto	L21
L20:
	ldc	1
L21:
	aload	3
	iload	1
	iload	6
	iadd
	baload
	ifeq	L22
	ldc	0
	goto	L23
L22:
	ldc	1
L23:
	iand
	aload	4
	iload	1
	iload	6
	isub
	iload	0
	iadd
	ldc	1
	isub
	baload
	ifeq	L24
	ldc	0
	goto	L25
L24:
	ldc	1
L25:
	iand
	ifeq	L26
	aload	2
	iload	6
	ldc	1
	bastore
	aload	3
	iload	1
	iload	6
	iadd
	ldc	1
	bastore
	aload	4
	iload	1
	iload	6
	isub
	iload	0
	iadd
	ldc	1
	isub
	ldc	1
	bastore
	aload	5
	iload	1
	iload	6
	iastore
	iload	0
	iload	1
	ldc	1
	iadd
	aload	2
	aload	3
	aload	4
	aload	5
	invokestatic	queens/solve(II[Z[Z[Z[I)V
	aload	4
	iload	1
	iload	6
	isub
	iload	0
	iadd
	ldc	1
	isub
	ldc	0
	bastore
	aload	3
	iload	1
	iload	6
	iadd
	ldc	0
	bastore
	aload	2
	iload	6
	ldc	0
	bastore
	goto	L27
L26:
L27:
	iload	6
	ldc	1
	iadd
	istore	6
	goto	L16
L17:
	return

	.limit	stack  25
	.limit	locals  7
	.end	method
	.method	public static  main()V
	ldc	8
	newarray	boolean
	astore	0
	ldc	15
	newarray	boolean
	astore	1
	ldc	15
	newarray	boolean
	astore	2
	ldc	8
	newarray	int
	astore	3
	ldc	0
	putstatic	queens/solCount$  I
	ldc	8
	ldc	0
	aload	0
	aload	1
	aload	2
	aload	3
	invokestatic	queens/solve(II[Z[Z[Z[I)V
	ldc	"Number of solutions: "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	queens/solCount$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	return

	.limit	stack  25
	.limit	locals  4
	.end	method
