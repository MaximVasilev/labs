all:
	swig -c++ -python BigIntDLL.i
	g++ -fPIC -c BigIntDLL.cpp
	g++ -fPIC -c BigIntDLL_wrap.cxx -I/usr/include/python2.7
	g++ -shared BigIntDLL.o BigIntDLL_wrap.o -o _BigIntDLL.so

windows32:
	swig -c++ -python BigIntDLL.i
	g++ -fPIC -c BigIntDLL.cpp
	g++ -c BigIntDLL_wrap.cxx -IC:\Python27\include
	g++ BigIntDLL.o BigIntDLL_wrap.o -Ic:\python27\include -Lc:\python27\libs -lpython27 -shared -o _BigIntDLL.pyd