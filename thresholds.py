import math
f=lambda x: int((1/50 + .03 * math.exp(-.001*x))*x)
t = 0
print("public final class Threshold {\n\
    public static final int slandererThreshold(int inf) {\n\
        switch(inf) {")
for i in range(950):
    if(f(i)>f(i-1)):
        t = i
    print("            case {}: return {};".format(i,t))
print("            default: return 949;")
print("        }\n    }")
print("\n\
    public static final int slandererIncome(int inf) {\n\
        switch(inf) {")
for i in range(950):
    print("            case {}: return {};".format(i,f(i)))
print("            default: return 30;")
print("        }\n    }\n}")
