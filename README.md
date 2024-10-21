# Lesson-Schedule-Management-System-API

https://drawsql.app/teams/raxsons-team/diagrams/lesson-schedule-management-system

<img width="569" alt="opera_yx80FDlptS" src="https://github.com/user-attachments/assets/74d88586-713e-4be9-8763-4c7c18169211">


<h1>Database documentation for the class schedule management system</h1>

<h2>Tables and their description:</h2>

<h3>students</h3>

<b>Description: Stores information about registered students.</b>
<b>Fields:</b>
  id (int, PK) - The unique identifier of the student.
  username (varchar) is the student's login to log in.
  password (varchar) is a hashed password.
  email (varchar) is the student's email address.
  fullname (varchar) is the full name of the student.
<b>Connections:</b>
  Linked to student_groups via id to identify the groups the student belongs to.
  teachers
