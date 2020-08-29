
class matching {
	// this is a test of paren matching, mostly
	int noparens = (((5+6)+5)+4);
	bool keepparens = (5+(6+(7+8)));
	char SomeParensKept = 4 * (5+6) * (8/9) * (5 || 4 && 3 && (4 || 5));
	int mixedops = 4*5 + 6*7;
	
	// some if-else matching
	int ifelse(char x[], int z[]) {
		if (5)
			if (ghg)
				if (true) {
					if (!true)
						return false;
				}
				else
					print (4,x,9);
			else
				return 999;
		else {
			if (4 + (5 - 9))
				read (x,y);
		}
	}
}

