#include <iostream>
using namespace std;

class BigInt
{
private:
	int* value;
	int base;
	int* DigitToArray(int d,int base);
	int* DeleteNull(int* c);
	int* MinMax(int* a,int* b,char type);
	void PrintMass(int* a);
	int* CopyArray(int* arr, int* source, bool Memory);
	bool CheckNegative(int* mas);
	int* ReadFile(char* fileName,int base);
	void WriteFile(int *mas,char *fileName,int base);
// Arithmetic Operations
	int* Addition(int *a, int *b,int *c,int base);
	int* Subtraction(int *a,int *b,int *c,int base);
	int* Multiplication(int *a,int *b,int *c,int base);
	int* DivideOnDigit(int* mas, int digit, int* answer,bool retR,int base);
	int* Dividing(int* u,int* v,int* q,bool retR,int base);
	int* Pow(int* a,int* b,int* c,int base);
	int* Mod(int* a,int* b,int base);
public:
//Constructor
	BigInt(){};
	BigInt(int num);
	BigInt(const BigInt &a);
	
//Operators Overloading
	BigInt operator+(const BigInt& rv);
	BigInt operator-(const BigInt& rv);
	BigInt operator*(const BigInt& rv);
	BigInt operator/(const BigInt& rv);
	BigInt operator^(const BigInt& rv);
	BigInt operator%(const BigInt& rv);
	bool operator==(const BigInt& rv);
	bool operator!=(const BigInt& rv);
	bool operator>=(const BigInt& rv);
	bool operator<=(const BigInt& rv);
	bool operator<(const BigInt& rv);	
	bool operator>(const BigInt& rv);
		
//Other funcs
	BigInt Print();
	BigInt Read(char* fileName,int b);
	BigInt Write(char* fileName);
	BigInt Convert(int n);
	BigInt Copy(BigInt a);
};
    
