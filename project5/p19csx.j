	.class	public  p19csx
	.super	java/lang/Object

	.method	public static  main([Ljava/lang/String;)V
	invokestatic	p19csx/main()V
	return
	.limit	stack  2
	.end	method

	.method	public static  main()V
	ldc	1
	istore	0
	ldc	9
	istore	1
	ldc	1
	istore	2
	ldc	0
	istore	3
	ldc	"Testing Program p19csx\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	1
	istore	4
	ldc	9
	istore	5
	ldc	1
	istore	6
	ldc	0
	istore	7
	ldc	0
	istore	8
	iload	3
	ifeq	L0
	ldc	"\n====> Wrong Control Flow 1\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	goto	L1
L0:
	iload	7
	iload	6
	ior
	ifeq	L2
	iload	8
	ldc	1
	iadd
	istore	8
	iload	7
	iload	4
	ldc	1
	if_icmpne	L3
	ldc	0
	goto	L4
L3:
	ldc	1
L4:
	if_icmpne	L5
	ldc	0
	goto	L6
L5:
	ldc	1
L6:
	ifeq	L7
	ldc	"===> Wrong Control Flow 2\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	20
	ldc	10
	if_icmpgt	L8
	ldc	0
	goto	L9
L8:
	ldc	1
L9:
	ifeq	L10
	iload	3
	iload	4
	ldc	0
	if_icmpne	L11
	ldc	0
	goto	L12
L11:
	ldc	1
L12:
	iand
	ifeq	L13
	ldc	"\n ==> Wrong Control Flow 3\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	goto	L14
L13:
	iload	5
	iload	0
	iload	4
	isub
	if_icmplt	L15
	ldc	0
	goto	L16
L15:
	ldc	1
L16:
	ifeq	L17
	ldc	"\n===> Wrong Control Flow 4\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
L17:
L14:
	goto	L18
L10:
	ldc	"\n===> Wrong Control Flow 5\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
L18:
	goto	L19
L7:
	ldc	1
	ifeq	L20
	iload	5
	ldc	9
	if_icmpeq	L21
	ldc	0
	goto	L22
L21:
	ldc	1
L22:
	iload	3
	ldc	1
	if_icmpne	L23
	ldc	0
	goto	L24
L23:
	ldc	1
L24:
	iand
	ifeq	L25
	iload	8
	ldc	1
	iadd
	istore	8
	goto	L26
L25:
	ldc	"\n===> Wrong Flow Control 6\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
L26:
	goto	L27
L20:
	ldc	"\n==> Wrong Flow Control 7\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
L27:
L19:
L2:
L1:
	iload	8
	ldc	2
	if_icmpne	L28
	ldc	0
	goto	L29
L28:
	ldc	1
L29:
	ifeq	L30
	ldc	"\n ERROR : Incorrect paths followed \n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
L30:
	ldc	"Test compeleted\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	return

	.limit	stack  25
	.limit	locals  9
	.end	method
