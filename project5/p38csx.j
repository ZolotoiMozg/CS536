	.class	public  p38csx
	.super	java/lang/Object

	.field public static	dim$ I =   8
	.field public static	a1$ I =   1
	.field public static	b1$ I =   2
	.field public static	c1$ I =   3
	.field public static	d1$ I =   4
	.field public static	e1$ I =   5
	.field public static	f1$ I =   6
	.field public static	g1$ I =   7
	.field public static	h1$ I =   8
	.field public static	a2$  I
	.field public static	b2$  I
	.field public static	c2$  I
	.field public static	d2$  I
	.field public static	e2$  I
	.field public static	f2$  I
	.field public static	g2$  I
	.field public static	h2$  I
	.field public static	count$ I =   0
	.field public static	flag1$  I
	.field public static	flag2$  I
	.field public static	flag3$  I
	.field public static	flag4$  I
	.field public static	flag5$  I
	.field public static	flag6$  I
	.field public static	flag7$  I
	.method	public static  main([Ljava/lang/String;)V
	invokestatic	p38csx/main()V
	return
	.limit	stack  2
	.end	method

	.method	public static  compatible(IIII)Z
	iload	1
	iload	3
	if_icmpeq	L0
	ldc	0
	goto	L1
L0:
	ldc	1
L1:
	ifeq	L2
	ldc	0
	ireturn
	goto	L3
L2:
L3:
	iload	3
	iload	1
	isub
	iload	2
	iload	0
	isub
	if_icmpeq	L4
	ldc	0
	goto	L5
L4:
	ldc	1
L5:
	iload	1
	iload	3
	isub
	iload	2
	iload	0
	isub
	if_icmpeq	L6
	ldc	0
	goto	L7
L6:
	ldc	1
L7:
	ior
	ifeq	L8
	ldc	0
	ireturn
	goto	L9
L8:
	ldc	1
	ireturn
L9:
	ldc	0
	ireturn

	.limit	stack  25
	.limit	locals  4
	.end	method
	.method	public static  main()V
	ldc	"Testing Program p38csx (8 queens problem)"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	1
	putstatic	p38csx/a2$  I
	ldc	1
	putstatic	p38csx/b2$  I
	ldc	1
	putstatic	p38csx/c2$  I
	ldc	1
	putstatic	p38csx/d2$  I
	ldc	1
	putstatic	p38csx/e2$  I
	ldc	1
	putstatic	p38csx/f2$  I
	ldc	1
	putstatic	p38csx/g2$  I
	ldc	1
	putstatic	p38csx/h2$  I
L10:
	getstatic	p38csx/a2$  I
	ldc	8
	if_icmple	L12
	ldc	0
	goto	L13
L12:
	ldc	1
L13:
	ifeq	L11
L14:
	getstatic	p38csx/b2$  I
	ldc	8
	if_icmple	L16
	ldc	0
	goto	L17
L16:
	ldc	1
L17:
	ifeq	L15
	getstatic	p38csx/a1$  I
	getstatic	p38csx/a2$  I
	getstatic	p38csx/b1$  I
	getstatic	p38csx/b2$  I
	invokestatic	p38csx/compatible(IIII)Z
	putstatic	p38csx/flag1$  I
	getstatic	p38csx/flag1$  I
	ifeq	L18
L19:
	getstatic	p38csx/c2$  I
	ldc	8
	if_icmple	L21
	ldc	0
	goto	L22
L21:
	ldc	1
L22:
	ifeq	L20
	getstatic	p38csx/a1$  I
	getstatic	p38csx/a2$  I
	getstatic	p38csx/c1$  I
	getstatic	p38csx/c2$  I
	invokestatic	p38csx/compatible(IIII)Z
	putstatic	p38csx/flag1$  I
	getstatic	p38csx/b1$  I
	getstatic	p38csx/b2$  I
	getstatic	p38csx/c1$  I
	getstatic	p38csx/c2$  I
	invokestatic	p38csx/compatible(IIII)Z
	putstatic	p38csx/flag2$  I
	getstatic	p38csx/flag1$  I
	getstatic	p38csx/flag2$  I
	iand
	ifeq	L23
L24:
	getstatic	p38csx/d2$  I
	ldc	8
	if_icmple	L26
	ldc	0
	goto	L27
L26:
	ldc	1
L27:
	ifeq	L25
	getstatic	p38csx/a1$  I
	getstatic	p38csx/a2$  I
	getstatic	p38csx/d1$  I
	getstatic	p38csx/d2$  I
	invokestatic	p38csx/compatible(IIII)Z
	putstatic	p38csx/flag1$  I
	getstatic	p38csx/b1$  I
	getstatic	p38csx/b2$  I
	getstatic	p38csx/d1$  I
	getstatic	p38csx/d2$  I
	invokestatic	p38csx/compatible(IIII)Z
	putstatic	p38csx/flag2$  I
	getstatic	p38csx/c1$  I
	getstatic	p38csx/c2$  I
	getstatic	p38csx/d1$  I
	getstatic	p38csx/d2$  I
	invokestatic	p38csx/compatible(IIII)Z
	putstatic	p38csx/flag3$  I
	getstatic	p38csx/flag1$  I
	getstatic	p38csx/flag2$  I
	iand
	getstatic	p38csx/flag3$  I
	iand
	ifeq	L28
L29:
	getstatic	p38csx/e2$  I
	ldc	8
	if_icmple	L31
	ldc	0
	goto	L32
L31:
	ldc	1
L32:
	ifeq	L30
	getstatic	p38csx/a1$  I
	getstatic	p38csx/a2$  I
	getstatic	p38csx/e1$  I
	getstatic	p38csx/e2$  I
	invokestatic	p38csx/compatible(IIII)Z
	putstatic	p38csx/flag1$  I
	getstatic	p38csx/b1$  I
	getstatic	p38csx/b2$  I
	getstatic	p38csx/e1$  I
	getstatic	p38csx/e2$  I
	invokestatic	p38csx/compatible(IIII)Z
	putstatic	p38csx/flag2$  I
	getstatic	p38csx/c1$  I
	getstatic	p38csx/c2$  I
	getstatic	p38csx/e1$  I
	getstatic	p38csx/e2$  I
	invokestatic	p38csx/compatible(IIII)Z
	putstatic	p38csx/flag3$  I
	getstatic	p38csx/d1$  I
	getstatic	p38csx/d2$  I
	getstatic	p38csx/e1$  I
	getstatic	p38csx/e2$  I
	invokestatic	p38csx/compatible(IIII)Z
	putstatic	p38csx/flag4$  I
	getstatic	p38csx/flag1$  I
	getstatic	p38csx/flag2$  I
	iand
	getstatic	p38csx/flag3$  I
	iand
	getstatic	p38csx/flag4$  I
	iand
	ifeq	L33
L34:
	getstatic	p38csx/f2$  I
	ldc	8
	if_icmple	L36
	ldc	0
	goto	L37
L36:
	ldc	1
L37:
	ifeq	L35
	getstatic	p38csx/a1$  I
	getstatic	p38csx/a2$  I
	getstatic	p38csx/f1$  I
	getstatic	p38csx/f2$  I
	invokestatic	p38csx/compatible(IIII)Z
	putstatic	p38csx/flag1$  I
	getstatic	p38csx/b1$  I
	getstatic	p38csx/b2$  I
	getstatic	p38csx/f1$  I
	getstatic	p38csx/f2$  I
	invokestatic	p38csx/compatible(IIII)Z
	putstatic	p38csx/flag2$  I
	getstatic	p38csx/c1$  I
	getstatic	p38csx/c2$  I
	getstatic	p38csx/f1$  I
	getstatic	p38csx/f2$  I
	invokestatic	p38csx/compatible(IIII)Z
	putstatic	p38csx/flag3$  I
	getstatic	p38csx/d1$  I
	getstatic	p38csx/d2$  I
	getstatic	p38csx/f1$  I
	getstatic	p38csx/f2$  I
	invokestatic	p38csx/compatible(IIII)Z
	putstatic	p38csx/flag4$  I
	getstatic	p38csx/e1$  I
	getstatic	p38csx/e2$  I
	getstatic	p38csx/f1$  I
	getstatic	p38csx/f2$  I
	invokestatic	p38csx/compatible(IIII)Z
	putstatic	p38csx/flag5$  I
	getstatic	p38csx/flag1$  I
	getstatic	p38csx/flag2$  I
	iand
	getstatic	p38csx/flag3$  I
	iand
	getstatic	p38csx/flag4$  I
	iand
	getstatic	p38csx/flag5$  I
	iand
	ifeq	L38
L39:
	getstatic	p38csx/g2$  I
	ldc	8
	if_icmple	L41
	ldc	0
	goto	L42
L41:
	ldc	1
L42:
	ifeq	L40
	getstatic	p38csx/a1$  I
	getstatic	p38csx/a2$  I
	getstatic	p38csx/g1$  I
	getstatic	p38csx/g2$  I
	invokestatic	p38csx/compatible(IIII)Z
	putstatic	p38csx/flag1$  I
	getstatic	p38csx/b1$  I
	getstatic	p38csx/b2$  I
	getstatic	p38csx/g1$  I
	getstatic	p38csx/g2$  I
	invokestatic	p38csx/compatible(IIII)Z
	putstatic	p38csx/flag2$  I
	getstatic	p38csx/c1$  I
	getstatic	p38csx/c2$  I
	getstatic	p38csx/g1$  I
	getstatic	p38csx/g2$  I
	invokestatic	p38csx/compatible(IIII)Z
	putstatic	p38csx/flag3$  I
	getstatic	p38csx/d1$  I
	getstatic	p38csx/d2$  I
	getstatic	p38csx/g1$  I
	getstatic	p38csx/g2$  I
	invokestatic	p38csx/compatible(IIII)Z
	putstatic	p38csx/flag4$  I
	getstatic	p38csx/e1$  I
	getstatic	p38csx/e2$  I
	getstatic	p38csx/g1$  I
	getstatic	p38csx/g2$  I
	invokestatic	p38csx/compatible(IIII)Z
	putstatic	p38csx/flag5$  I
	getstatic	p38csx/f1$  I
	getstatic	p38csx/f2$  I
	getstatic	p38csx/g1$  I
	getstatic	p38csx/g2$  I
	invokestatic	p38csx/compatible(IIII)Z
	putstatic	p38csx/flag6$  I
	getstatic	p38csx/flag1$  I
	getstatic	p38csx/flag2$  I
	iand
	getstatic	p38csx/flag3$  I
	iand
	getstatic	p38csx/flag4$  I
	iand
	getstatic	p38csx/flag5$  I
	iand
	getstatic	p38csx/flag6$  I
	iand
	ifeq	L43
L44:
	getstatic	p38csx/h2$  I
	ldc	8
	if_icmple	L46
	ldc	0
	goto	L47
L46:
	ldc	1
L47:
	ifeq	L45
	getstatic	p38csx/a1$  I
	getstatic	p38csx/a2$  I
	getstatic	p38csx/h1$  I
	getstatic	p38csx/h2$  I
	invokestatic	p38csx/compatible(IIII)Z
	putstatic	p38csx/flag1$  I
	getstatic	p38csx/b1$  I
	getstatic	p38csx/b2$  I
	getstatic	p38csx/h1$  I
	getstatic	p38csx/h2$  I
	invokestatic	p38csx/compatible(IIII)Z
	putstatic	p38csx/flag2$  I
	getstatic	p38csx/c1$  I
	getstatic	p38csx/c2$  I
	getstatic	p38csx/h1$  I
	getstatic	p38csx/h2$  I
	invokestatic	p38csx/compatible(IIII)Z
	putstatic	p38csx/flag3$  I
	getstatic	p38csx/d1$  I
	getstatic	p38csx/d2$  I
	getstatic	p38csx/h1$  I
	getstatic	p38csx/h2$  I
	invokestatic	p38csx/compatible(IIII)Z
	putstatic	p38csx/flag4$  I
	getstatic	p38csx/e1$  I
	getstatic	p38csx/e2$  I
	getstatic	p38csx/h1$  I
	getstatic	p38csx/h2$  I
	invokestatic	p38csx/compatible(IIII)Z
	putstatic	p38csx/flag5$  I
	getstatic	p38csx/f1$  I
	getstatic	p38csx/f2$  I
	getstatic	p38csx/h1$  I
	getstatic	p38csx/h2$  I
	invokestatic	p38csx/compatible(IIII)Z
	putstatic	p38csx/flag6$  I
	getstatic	p38csx/g1$  I
	getstatic	p38csx/g2$  I
	getstatic	p38csx/h1$  I
	getstatic	p38csx/h2$  I
	invokestatic	p38csx/compatible(IIII)Z
	putstatic	p38csx/flag7$  I
	getstatic	p38csx/flag1$  I
	getstatic	p38csx/flag2$  I
	iand
	getstatic	p38csx/flag3$  I
	iand
	getstatic	p38csx/flag4$  I
	iand
	getstatic	p38csx/flag5$  I
	iand
	getstatic	p38csx/flag6$  I
	iand
	getstatic	p38csx/flag7$  I
	iand
	ifeq	L48
	getstatic	p38csx/count$  I
	ldc	1
	iadd
	putstatic	p38csx/count$  I
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"Solution#"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p38csx/count$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	" is:"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	" "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p38csx/a2$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	" "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p38csx/b2$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	" "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p38csx/c2$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	" "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p38csx/d2$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	" "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p38csx/e2$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	" "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p38csx/f2$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	" "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p38csx/g2$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	" "
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	getstatic	p38csx/h2$  I
	invokestatic	CSXLib/printInt(I)V
	ldc	"\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	goto	L49
L48:
L49:
	getstatic	p38csx/h2$  I
	ldc	1
	iadd
	putstatic	p38csx/h2$  I
	goto	L44
L45:
	goto	L50
L43:
L50:
	getstatic	p38csx/g2$  I
	ldc	1
	iadd
	putstatic	p38csx/g2$  I
	ldc	1
	putstatic	p38csx/h2$  I
	goto	L39
L40:
	goto	L51
L38:
L51:
	getstatic	p38csx/f2$  I
	ldc	1
	iadd
	putstatic	p38csx/f2$  I
	ldc	1
	putstatic	p38csx/g2$  I
	ldc	1
	putstatic	p38csx/h2$  I
	goto	L34
L35:
	goto	L52
L33:
L52:
	getstatic	p38csx/e2$  I
	ldc	1
	iadd
	putstatic	p38csx/e2$  I
	ldc	1
	putstatic	p38csx/f2$  I
	ldc	1
	putstatic	p38csx/g2$  I
	ldc	1
	putstatic	p38csx/h2$  I
	goto	L29
L30:
	goto	L53
L28:
L53:
	getstatic	p38csx/d2$  I
	ldc	1
	iadd
	putstatic	p38csx/d2$  I
	ldc	1
	putstatic	p38csx/e2$  I
	ldc	1
	putstatic	p38csx/f2$  I
	ldc	1
	putstatic	p38csx/g2$  I
	ldc	1
	putstatic	p38csx/h2$  I
	goto	L24
L25:
	goto	L54
L23:
L54:
	getstatic	p38csx/c2$  I
	ldc	1
	iadd
	putstatic	p38csx/c2$  I
	ldc	1
	putstatic	p38csx/d2$  I
	ldc	1
	putstatic	p38csx/e2$  I
	ldc	1
	putstatic	p38csx/f2$  I
	ldc	1
	putstatic	p38csx/g2$  I
	ldc	1
	putstatic	p38csx/h2$  I
	goto	L19
L20:
	goto	L55
L18:
L55:
	getstatic	p38csx/b2$  I
	ldc	1
	iadd
	putstatic	p38csx/b2$  I
	ldc	1
	putstatic	p38csx/c2$  I
	ldc	1
	putstatic	p38csx/d2$  I
	ldc	1
	putstatic	p38csx/e2$  I
	ldc	1
	putstatic	p38csx/f2$  I
	ldc	1
	putstatic	p38csx/g2$  I
	ldc	1
	putstatic	p38csx/h2$  I
	goto	L14
L15:
	getstatic	p38csx/a2$  I
	ldc	1
	iadd
	putstatic	p38csx/a2$  I
	ldc	1
	putstatic	p38csx/b2$  I
	ldc	1
	putstatic	p38csx/c2$  I
	ldc	1
	putstatic	p38csx/d2$  I
	ldc	1
	putstatic	p38csx/e2$  I
	ldc	1
	putstatic	p38csx/f2$  I
	ldc	1
	putstatic	p38csx/g2$  I
	ldc	1
	putstatic	p38csx/h2$  I
	goto	L10
L11:
	return

	.limit	stack  25
	.limit	locals  0
	.end	method
