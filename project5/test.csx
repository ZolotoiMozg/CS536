class testCSX{
	int a;
	int b;
	int c = 0;
	int z[12];
	char y[1];


	int f(){
		c++;
		return c;
	}
	
	void ppp(char c, int i){
		print(c);
		print(i);
		print('\n');
		return;
	}

	void main() {
		char x;
		
		a = 123;
		b  = 456;
		//c = c*2-1;
		
		z[f()] = f();
		z[f()] = f();
		z[f()] = f();
		z[f()] = f();
		z[f()] = f();
		z[f()] = f();
		
		c=0;
		
		z[f()]++;
		z[f()]++;
		z[f()]++;
		z[f()]++;
		z[f()]++;
		z[f()]++;
		print(z[0]);
		print('\n');
		print(z[1]);
		print('\n');
		print(z[2]);
		print('\n');
		print(z[3]);
		print('\n');
		print(z[4]);
		print('\n');
		print(z[5]);
		print('\n');
		print(z[6]);
		print('\n');
		print(z[7]);
		print('\n');
		print(z[8]);
		print('\n');
		print(z[9]);
		print('\n');
		print(z[10]);
		print('\n');
		print(z[11]);
		print('\n');
	
		a= a+a;
		print(100);
		print('\n');
		print( a+b-100);
		print('\n');
		print( c);
		
		print('\n');
		ppp('a',a);
		print('\n');
		print('\n');
		x = 'a';
		x++;
		ppp(x,a);
		
		a=b;
		
		if (a==666){
			ppp('i',1);
		} else {
			ppp('e',2);
		}
		
		if(a<666){
			ppp('i',10);
		}
		
		return;
	}

}