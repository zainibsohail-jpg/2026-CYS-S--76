# Program to convert Bytes into MB and GB

bytes_value = float(input("Enter the number of bytes: "))

# Conversion
megabytes = bytes_value / (1024 * 1024)
gigabytes = bytes_value / (1024 * 1024 * 1024)

# Display results
print("Megabytes (MB):", megabytes)
print("Gigabytes (GB):", gigabytes)