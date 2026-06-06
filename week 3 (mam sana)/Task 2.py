# Lambda function to find the larger number
large_number = lambda a, b: a if a > b else b

# User Defined Function to print table
def print_table(num, limit):
    for i in range(1, limit + 1):
        print(f"{num} x {i} = {num * i}")

# Input from user
num1 = int(input("Enter first number: "))
num2 = int(input("Enter second number: "))
limit = int(input("Enter table range: "))

# Get larger number using lambda
big = large_number(num1, num2)

print("\nLarger number is:", big)
print("Table of larger number:\n")

# Call UDF
print_table(big, limit)