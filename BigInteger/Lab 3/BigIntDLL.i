%module BigIntDLL
%{
#include "BigIntDLL.h"
%}

%rename(_in) operator >>;
%rename(_out) operator <<;

%include "BigIntDLL.h"