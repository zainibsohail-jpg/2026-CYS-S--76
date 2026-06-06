n = 4  # maximum stars in middle row

# Increasing pattern
for i in range(1, n + 1):
    for j in range(i):
        print("*", end=" ")
    print()

# Decreasing pattern
for i in range(n - 1, 0, -1):
    for j in range(i):
        print("*", end=" ")
    print()