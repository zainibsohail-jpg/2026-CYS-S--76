binary = input("Enter a binary number: ")

decimal = 0
power = 0

# Traverse binary number from right to left
for digit in reversed(binary):
    decimal += int(digit) * (2 ** power)
    power += 1

print("Decimal Equivalent =", decimal)