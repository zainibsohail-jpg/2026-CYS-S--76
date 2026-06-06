import time

questions = [
    {
        "question": "Python is a ____ language?",
        "A": "Programming",
        "B": "Cooking",
        "C": "Gaming",
        "D": "Drawing",
        "answer": "A"
    },

    {
        "question": "2 + 2 = ?",
        "A": "3",
        "B": "4",
        "C": "5",
        "D": "6",
        "answer": "B"
    },

    {
        "question": "Which one is input function?",
        "A": "print()",
        "B": "type()",
        "C": "input()",
        "D": "len()",
        "answer": "C"
    }
]

all_results = []

admin_username = "ecat_admin"
admin_password = "ecat@2024"

student_username = "student"
student_password = "student123"


def admin_portal():

    user = input("Enter admin username: ")
    pas = input("Enter admin password: ")

    if user == admin_username and pas == admin_password:

        print("Login Successful")

        while True:

            print("\n1. View Questions")
            print("2. View Results")
            print("3. Exit")

            choice = input("Enter choice: ")

            if choice == "1":

                for q in questions:

                    print("\nQuestion:", q["question"])
                    print("A.", q["A"])
                    print("B.", q["B"])
                    print("C.", q["C"])
                    print("D.", q["D"])
                    print("Correct Answer:", q["answer"])

            elif choice == "2":

                for r in all_results:

                    print("\nName:", r["name"])
                    print("Roll No:", r["roll"])
                    print("Score:", r["score"])
                    print("Percentage:", r["percentage"])
                    print("Grade:", r["grade"])

            elif choice == "3":
                break

            else:
                print("Wrong Choice")

    else:
        print("Wrong Username or Password")


def student_portal():

    user = input("Enter student username: ")
    pas = input("Enter student password: ")

    if user == student_username and pas == student_password:

        print("Login Successful")

        name = input("Enter your name: ")
        roll = input("Enter your roll no: ")

        score = 0
        correct = 0
        wrong = 0
        skip = 0

        answers = {}

        for i in range(len(questions)):

            q = questions[i]

            print("\nQuestion", i + 1)
            print(q["question"])

            print("A.", q["A"])
            print("B.", q["B"])
            print("C.", q["C"])
            print("D.", q["D"])

            ans = input("Enter answer A/B/C/D or S to skip: ").upper()

            answers[i] = ans

            if ans == q["answer"]:

                score = score + 4
                correct = correct + 1

            elif ans == "S":

                skip = skip + 1

            else:

                score = score - 1
                wrong = wrong + 1

        percentage = (score / (len(questions) * 4)) * 100

        if percentage >= 80:
            grade = "EXCELLENT"

        elif percentage >= 65:
            grade = "GOOD"

        elif percentage >= 50:
            grade = "AVERAGE"

        else:
            grade = "BELOW AVERAGE"

        print("\nExam Finished")
        print("Score:", score)
        print("Percentage:", percentage)
        print("Grade:", grade)

        result = {
            "name": name,
            "roll": roll,
            "score": score,
            "percentage": percentage,
            "grade": grade
        }

        all_results.append(result)

    else:
        print("Wrong Username or Password")


while True:

    print("\n--- ECAT SYSTEM ---")
    print("1. Admin Portal")
    print("2. Student Portal")
    print("3. Exit")

    option = input("Enter option: ")

    if option == "1":
        admin_portal()

    elif option == "2":
        student_portal()

    elif option == "3":
        print("Program Ended")
        break

    else:
        print("Invalid Option")