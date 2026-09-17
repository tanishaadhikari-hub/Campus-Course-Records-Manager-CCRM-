# Usage Instructions

## 1. Compile and Run

- Open the project in IntelliJ IDEA or VS Code with Java extension
- Ensure Java 17 (or higher) is installed
- Build the project (Build > Build Project)
- Run the main class `edu.ccrm.cli.MainMenu`
- The menu-driven CLI will appear

## 2. Student Operations

- Add Student: Enter student registration number, name, and email
- List Students: View all registered students
- Update/Deactivate/Print Profile: Available in Student Management menu

## 3. Course Operations

- Add Course: Enter course code, title, credits, semester, department
- List Courses: View all registered courses
- Assign Instructor: Provide instructor details when prompted

## 4. Enrollment & Grading

- Enroll Student: Provide student regNo and course code; max credits per semester rule enforced
- Record Grade: Enter grade (S/A/B/C/D/E/F) for a given student and course

## 5. File Import/Export

- Import Students/Courses: Enter path of CSV file as prompted (sample format in README)
- Export Students/Courses: Exports current records to CSV

## 6. Backup Operations

- Backup Data: Enter source directory (usually project or data folder) and backup destination
- Show Backup Directory Size: Enter backup folder path to get total size in bytes/MB

## 7. Exit

- Select '0' from main menu

## Sample CSV Formats

**students.csv**
regNo,fullName,email
2025001,John Smith,john.smith@email.com
2025002,Emily Johnson,emily.johnson@email.com



**courses.csv**
code,title,credits,semester,department
CS101,Introduction to Programming,3,FALL,Computer Science
MATH201,Calculus I,4,FALL,Mathematics

