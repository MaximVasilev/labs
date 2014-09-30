all:
	g++ -c BigIntDLL.cpp
	g++ -shared -o BigInt.dll BigIntDLL.o
	g++ BigIntApp.cpp BigInt.dll -I. -Wl,-rpath,. -L. -o bigint.exe