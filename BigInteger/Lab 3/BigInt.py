import sys
import BigIntDLL

print "\n============ Long Arithmetic ============\n"
print "Enter string in such way:"
print "file1 operator file2 result_file -b mod file"
args = raw_input().split()
s = len(args)
if s < 4:
    print "Too few arguments in string"
    sys.exit(-1)
else:
    file1 = args[0]
    oper = args[1]
    file2 = args[2]
    result = args[3]
    base = 10
    mod = ""
    if s == 5:
        if args[4] == "-b":
            base = 256
        else:
            mod = args[4]
    elif s == 6:
        base = 256
        mod = args[5]

        
print "file1: " + file1
print "oper: " + oper
print "file2: " + file2
print "result: " + result
print "base: " + str(base)
print "mod: " + mod

a = BigIntDLL.BigInt()
b = BigIntDLL.BigInt()
a = a.Read(file1,base)
b = b.Read(file2,base)

if oper == "+": a = a + b
elif oper == "-": a = a - b
elif oper == "*": a = a * b
elif oper == "/": a = a / b
elif oper == "^": a = a ^ b
elif oper == "%": a = a % b

if mod != "":
    m = BigIntDLL.BigInt()
    m = m.Read(mod,base)
    a = a % m
    
a.Write(result)

