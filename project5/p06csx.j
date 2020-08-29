	.class	public  p06csx
	.super	java/lang/Object

	.method	public static  main([Ljava/lang/String;)V
	invokestatic	p06csx/main()V
	return
	.limit	stack  2
	.end	method

	.method	public static  main()V
	ldc	"Testing program p06csx\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	ldc	1
	ifeq	L0
	ldc	"if then stmt works\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
L0:
	ldc	0
	ifeq	L1
	ldc	"if then stmt DOESN'T work\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
L1:
	ldc	1
	ifeq	L2
	ldc	"if then else stmt works\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	goto	L3
L2:
	ldc	"if then else stmt DOESN'T work\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
L3:
	ldc	0
	ifeq	L4
	ldc	"if then else stmt DOESN'T work\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
	goto	L5
L4:
	ldc	"if then else stmt works\n"
	invokestatic	CSXLib/printString(Ljava/lang/String;)V
L5:
	return

	.limit	stack  25
	.limit	locals  0
	.end	method
