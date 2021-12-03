anz = 0
a = int(input())
b = int(input())
c = int(input())
for i in range(1997):
    d = int(input())
    if a+b+c < b+c+d:
        anz += 1

    a = b
    b = c
    c = d

print(anz)
