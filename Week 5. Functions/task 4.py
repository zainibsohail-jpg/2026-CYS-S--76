def celsius_to_fahrenheit(c):
    return (c * 9/5) + 32

def fahrenheit_to_celsius(f):
    return (f - 32) * 5/9

print("Temperature Conversion Program")
print("1. Celsius to Fahrenheit")
print("2. Fahrenheit to Celsius")

choice = int(input("Enter your choice (1 or 2): "))

if choice == 1:
    c = float(input("Enter temperature in Celsius: "))
    print("Fahrenheit =", celsius_to_fahrenheit(c))

elif choice == 2:
    f = float(input("Enter temperature in Fahrenheit: "))
    print("Celsius =", fahrenheit_to_celsius(f))

else:
    print("Invalid choice!")