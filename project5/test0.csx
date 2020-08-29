## test0.correct ##

class test0 {
  int x = 0;
  int y = 5;
  char xx = 'x';
  bool k = true;
  char sy[11];
  const z = 4;  

  void f(int a, char b[], bool c, int d) { return; }

  void g(int x) {char y[3];
	return; }

  void foo() {
    int a = 0;
    int b = 0;
    bool c = false;
    char d[5];

    while (true) {return;}
    a = a + b;
    a = a - b;
    a = a * b;
    a = a / b;
    c = ! c;
    c = a == b;
    c = a != b;
    c = a < b;
    c = a > b;
    c = a <= b;
    c = a >= b;
    a = b;
    a = 50;
    d = "Hello";
    c = true;
    c = false;
    a = ~50;
    a = b;	

    if (a >= b) {
      int a = 1;
      while (a > 0) {
        int a = b;
	a = b;
      }
    }
    a = a;
##    if (b == ~1) {
  	x = 5;
  	while (c) {y = y + 1;}
    }
    else {
//        char test[5];
//	print(test);
	a = 0;
    }
    a = 2147483647;		
    g(a);
    f(a,d,c,b);
//    read(a, b);
//    print(c, d);
    L: while (a > b) {
	if (a == b)
		break L;
	else {
		a = a - 1;
		continue L;
	}
      }##
  } 

	void main() {
		print(1);
		return;
	}
}
