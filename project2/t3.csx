##
Following piece of code is used to exhaustively test the CSXScanner.
//Checking if single line commnets cause any problems when nested in multiline comments

##

const int SIZE = 100; //size of the array

class ExhaustiveTest{
 int value; //checking for reserved word int
 char signal; //checking for reserved word char
 char name[SIZE];
 bool status; ##checking for reserved word bool
##
 ExhaustiveTest()
 {
  int i=0;
  int negVal =                          ~654; //checking for negative numbers
  int posVal =               4234; //checking if spaces are ignored
  int hugePosVal = 123123123123123123123123123123123123123123123; //testing if check for values > maxval work. Should get a Warning
  int hugeNegVal = ~12312312315412412315124151231251561231512511;  //Should get another warning
  
  while(i!=SIZE)
  {
   if(i==20);
    print("\ti = %d\n",20);
   else if(i>20)
    print("\ti > 20\n");
   else if(i<20)
    print("\ti<20\n");
   else if(i>=20)
    print("\ti>=20\n");
   else if(i<=20)
    print("\ti<=20\n");
   else
    print("\ti!=20\n");
 
   signal = '\n';
   signal = '\t';
   signal = '\'';
   signal = '\\';
   signal = 'a';
   signal = 'aa'; //this line should give out 2 errors line number is 43, col number = 13 and an identifier aa
   status = false;
   if(i==50)
    continue;
   if(i==90)
    break;
   
   j = read(name[i]);
   if(j!=0)
    print("Read Error");

   if(j==2||i==4)
    a = 1:4;
   
   i=i+1;
   j=j-1;
   i++;
   j--;
   int f = i*j;
   int g = i/j;
   
   if(f==0&&g==1)
    int c = 1;
   
   name = "A             i\n";
   print(name);
   //Following line should produce an error saying String Literal contains EOL line numeber = 68 
   name = "A
   ";
   //


   //checking for multiple EOLS


   
   \ ##Should give an invalid token error, line number = 77##
   @ ##Should give an invalid token error, line number = 78##
   # ##Should give an invalid token error##
   % ##Should give an invalid token error##
   ^ ##Should give an invalid token error##
   & ##Should give an invalid token error##
   ? ##Should give an invalid token error##
   | ##Should give an invalid token error line number = 84##
   
   
  }
  status = true;
  
 }
 const int GetValue()
 {
  return value;
 }
 void SetValue(int val)
 {
  if(val>SIZE);
   value = SIZE;
  else if(val<0)
   value = 0;
  else
   value = val;
 }
}
##Exhaustive Test Ends Here##
//Exhaustive Test Ends Here
