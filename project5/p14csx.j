	.class	public  p14csx
	.super	java/lang/Object

	.method	public static  main([Ljava/lang/String;)V
	invokestatic	p14csx/main()V
	return
	.limit	stack  2
	.end	method

	.method	public static  main()V
	ldc	"Testing program p14csx\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	-2147483640
	istore	0
	ldc	2147483647
	istore	1
	iload	0
	iload	1
	if_icmpgt	L0
	ldc	0
	goto	L1
L0:
	ldc	1
L1:
	iload	0
	iload	1
	if_icmpeq	L2
	ldc	0
	goto	L3
L2:
	ldc	1
L3:
	ior
	iload	1
	iload	0
	if_icmplt	L4
	ldc	0
	goto	L5
L4:
	ldc	1
L5:
	ior
	iload	1
	iload	1
	if_icmpne	L6
	ldc	0
	goto	L7
L6:
	ldc	1
L7:
	ior
	ifeq	L8
	ldc	"ERROR: Error in relational operators (integer)\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
L8:
	iload	0
	ldc	0
	imul
	istore	0
	iload	1
	ldc	1
	imul
	istore	1
	ldc	0
	istore	2
	ldc	1
	istore	3
	iload	2
	ldc	1
	if_icmpeq	L9
	ldc	0
	goto	L10
L9:
	ldc	1
L10:
	iload	3
	iload	2
	if_icmpeq	L11
	ldc	0
	goto	L12
L11:
	ldc	1
L12:
	iand
	istore	3
	iload	3
	ifeq	L13
	ldc	"ERROR: In boolean expression\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
L13:
	iload	0
	iload	1
	if_icmplt	L14
	ldc	0
	goto	L15
L14:
	ldc	1
L15:
	iload	0
	ldc	0
	if_icmpeq	L16
	ldc	0
	goto	L17
L16:
	ldc	1
L17:
	iand
	ldc	0
	iand
	istore	2
	iload	2
	ifeq	L18
	ldc	"ERROR: In boolean/integer expression\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
L18:
	ldc	1
	ldc	2
	iadd
	ldc	3
	imul
	ldc	4
	ldc	5
	iadd
	idiv
	istore	0
	ldc	1
	ldc	2
	iadd
	ldc	3
	ldc	4
	ldc	5
	iadd
	idiv
	imul
	istore	1
	iload	0
	ldc	1
	if_icmpne	L19
	ldc	0
	goto	L20
L19:
	ldc	1
L20:
	iload	1
	ldc	0
	if_icmpne	L21
	ldc	0
	goto	L22
L21:
	ldc	1
L22:
	ior
	ifeq	L23
	ldc	"ERROR: Improper Integer division and associativity\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
L23:
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	return

	.limit	stack  25
	.limit	locals  4
	.end	method
