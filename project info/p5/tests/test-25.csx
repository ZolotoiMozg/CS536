## Test 25
 Created: Dec 2002
 
  Do a simple sort using arrays
##

class p25csx{

 void main(){
  int i[10];
  int a;
  int max;
  int temp;
 
  print("Testing program p25csx\n");
 			
   i[0]=1;
   i[1]=~10;
   i[2]=100;
   i[3]=~1000;
   i[4]=10000;
   i[5]=~100000;
   i[6]=1000000;
   i[7]=~10000000;
   i[8]=100000000;
   i[9]=~1000000000;


   max=8;
   while (max >= 0) {
	a=0;
	while (a <= max) {
		if (i[a] > i[a+1]) {
			temp = i[a];
			i[a] = i[a+1];
			i[a+1] = temp;
		}
		a = a+1;
	}
   	max = max -1;
   }
   
  print("Sorted values are:\n");
  print(i[0], "\t", i[1], "\t", i[2], "\t", i[3], "\t", i[4], "\n");
  print(i[5], "\t", i[6], "\t", i[7], "\t", i[8], "\t", i[9], "\n");


 }

}

