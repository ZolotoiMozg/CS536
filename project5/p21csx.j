	.class	public  p21csx
	.super	java/lang/Object

	.method	public static  main([Ljava/lang/String;)V
	invokestatic	p21csx/main()V
	return
	.limit	stack  2
	.end	method

	.method	public static  main()V
	ldc	"Testing Program p21csx\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	1
	istore	1
	ldc	0
	istore	0
L0:
	iload	1
	ldc	1
	if_icmpeq	L2
	ldc	0
	goto	L3
L2:
	ldc	1
L3:
	ifeq	L1
	iload	0
	ldc	1
	iadd
	istore	0
L4:
	iload	1
	ldc	1
	if_icmpeq	L6
	ldc	0
	goto	L7
L6:
	ldc	1
L7:
	ifeq	L5
	iload	0
	ldc	1
	iadd
	istore	0
L8:
	iload	1
	ldc	1
	if_icmpeq	L10
	ldc	0
	goto	L11
L10:
	ldc	1
L11:
	ifeq	L9
	iload	0
	ldc	1
	iadd
	istore	0
L12:
	iload	1
	ldc	1
	if_icmpeq	L14
	ldc	0
	goto	L15
L14:
	ldc	1
L15:
	ifeq	L13
	iload	0
	ldc	1
	iadd
	istore	0
L16:
	iload	1
	ldc	1
	if_icmpeq	L18
	ldc	0
	goto	L19
L18:
	ldc	1
L19:
	ifeq	L17
	iload	0
	ldc	1
	iadd
	istore	0
L20:
	iload	1
	ldc	1
	if_icmpeq	L22
	ldc	0
	goto	L23
L22:
	ldc	1
L23:
	ifeq	L21
	iload	0
	ldc	1
	iadd
	istore	0
L24:
	iload	1
	ldc	1
	if_icmpeq	L26
	ldc	0
	goto	L27
L26:
	ldc	1
L27:
	ifeq	L25
	iload	0
	ldc	1
	iadd
	istore	0
L28:
	iload	1
	ldc	1
	if_icmpeq	L30
	ldc	0
	goto	L31
L30:
	ldc	1
L31:
	ifeq	L29
	iload	0
	ldc	1
	iadd
	istore	0
L32:
	iload	1
	ldc	1
	if_icmpeq	L34
	ldc	0
	goto	L35
L34:
	ldc	1
L35:
	ifeq	L33
	iload	0
	ldc	1
	iadd
	istore	0
L36:
	iload	1
	ldc	1
	if_icmpeq	L38
	ldc	0
	goto	L39
L38:
	ldc	1
L39:
	ifeq	L37
	iload	0
	ldc	1
	iadd
	istore	0
	ldc	0
	istore	1
	goto	L36
L37:
	goto	L32
L33:
	goto	L28
L29:
	goto	L24
L25:
	goto	L20
L21:
	goto	L16
L17:
	goto	L12
L13:
	goto	L8
L9:
	goto	L4
L5:
	goto	L0
L1:
	iload	0
	ldc	10
	if_icmpne	L40
	ldc	0
	goto	L41
L40:
	ldc	1
L41:
	ifeq	L42
	ldc	"\nERROR : Not all loops exectuted properly: "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	iload	0
	invokestatic	CSXLib/printInt(I)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	goto	L43
L42:
L43:
	ldc	"Test compeleted\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	return

	.limit	stack  25
	.limit	locals  2
	.end	method
