# Lesson-Schedule-Management-System-API

https://drawsql.app/teams/raxsons-team/diagrams/lesson-schedule-management-system

<img width="568" alt="opera_iYhwY2IpOV" src="https://github.com/user-attachments/assets/7d4581fd-0413-4c14-a525-5083a30c7c1b">



<h1>Database documentation for the class schedule management system</h1>

<h2>Tables and their description:</h2>

<h3>students</h3>

<b>Description: Stores information about registered students.</b>
<b>Fields:</b><br>
  id (int, PK) - The unique identifier of the student.<br>
  username (varchar) is the student's login to log in.<br>
  password (varchar) is a hashed password.<br>
  email (varchar) is the student's email address.<br>
  fullname (varchar) is the full name of the student.<br>
<b>Connections:</b><br>
  Linked to student_groups via id to identify the groups the student belongs to.
  teachers<br>

<b>Description: Stores information about teachers.</b><br>
<b>Fields:</b><br>
  id (int, PK) - The unique identifier of the teacher.<br>
  username (varchar) - The teacher's login to log in.<br>
  fullname (varchar) is the full name of the teacher.<br>
  email (varchar) is the teacher's email address.<br>
<b>Links:</b><br>
  Linked to subject_teachers to display which subjects a teacher can teach.<br>

  
<h3>groups</h3>

<b>Description: Stores information about groups of students.</b><br>
<b>Fields:</b><br>
  id (int, PK) - The unique identifier of the group.<br>
  name (varchar) - The name of the group (for example, "101").<br>
  <br>
  <b>Links:</b><br>
  Linked to student_groups to track students in groups.<br>
  Linked to group_subjects to determine the available items for the group.<br>
  student_groups (Students in groups)<br>

<b>Description: Stores connections between students and their groups.</b><br>
<br>
<b>Fields:</b><br>
  id (int, PK) is the unique identifier of the record.<br>
  group_id (int, FK) - Link to groups.id .<br>
  student_id (int, FK) - Link to students.id .<br>
  <b>Connections:</b><br>
  Allows you to find students belonging to a certain group.<br>

  
<h3>subjects</h3>

<b>Description: Stores information about items.</b><br>
<b>Fields:</b><br>
  id (int, PK) - The unique identifier of the item.<br>
  name (varchar) - The name of the subject (for example, "Mathematics").<br>
  <b>Links:</b><br>
  Linked to subject_teachers to display which teachers teach this subject.<br>
  Linked to group_subjects to indicate the availability of subjects for groups.<br>
  subject_teachers (Subjects and teachers)<br>
<br>
<b>Description: Displays the relationship between subjects and teachers.</b><br>
<br>
<br>
<b>Fields:</b><br>
  id (int, PK) - The unique identifier of the record.<br>
  teacher_id (int, FK) - Link to teachers.id.<br>
  subject_id (int, FK) - Link to subjects.id.<br>
  <b>Connections:</b><br>
  Allows you to find which subjects a particular teacher can teach.<br>
  group_subjects (Subjects and groups)<br>
<br>
<b>Description: Stores information about which items are available for each group.<b><br>
<br>
<br>
<b>Fields:</b><br>
  id (int, PK) - The unique identifier of the record.<br>
  group_id (int, FK) - Link to groups.id.<br>
  subject_id (int, FK) - Link to subjects.id.<br>
  <br>
  <b>Communications:</b><br>
  Allows you to find the subjects available to the students of the group before creating a schedule.<br>
  classes (Audiences)<br>
<br>
<b>Description: Stores information about classrooms.</b><br>
<br>
<b>Fields:</b><br>
  id (int, PK) is a unique identifier of the audience.<br>
  name (varchar) - The name or number of the audience.<br>
  capacity (int) - The capacity of the audience.<br>
  location (varchar) - The location of the audience.<br>
  <b>Connections:</b><br>
  Linked to schedule to determine the location of lessons.<br>
  <br>
  <h3>schedule</h3>

  <b>Description: Stores information about the class schedule.</b><br>
  <b>Fields:</b><br>
  id (int, PK) - The unique identifier of the schedule entry.<br>
  subject_id (int, FK) - Link to subjects.id.<br>
  teacher_id (int, FK) - Link to teachers.id.<br>
  classes_id (int, FK) - Link to classes.id.<br>
  group_id (int, FK) - Link to groups.id.<br>
  start_time (datetime) - The start time of the lesson.<br>
  end_time (datetime) - The end time of the lesson.<br>
  <b>Connections:</b><br>
  Allows you to track class schedules for groups and teachers in specific classrooms.<br>

  <h3>notifications</h3>
<br>
<b>Description: Stores notifications for students and teachers.</b><br>
<b>Fields:<b><br>
  id (int, PK) - The unique identifier of the notification.<br>
  user_id (int, FK) - Link to students.id or teachers.id (generalized field for users).<br>
  message (text) - The text of the notification.<br>
  is_read (boolean) - The status of reading the notification (true/false).<br>
  created_at (timestamp) - The date and time the notification was created.<br>
<b>Communications:</b><br>
Allows you to send notifications about schedule changes or other important events.<br>
  <br>
<b>Description of the relationships between tables:</b><br>
students <-> student_groups <-> groups: Allows you to determine which group each student belongs to.<br>
groups <-> group_subjects <-> subjects: Allows you to determine which subjects are available to the students of the group before creating a schedule.<br>
teachers <-> subject_teachers <-> subjects: Allows you to determine which subjects each teacher can teach.<br>
schedule: Connects subjects, teachers, classes, and groups to create a specific schedule.<br>
notifications: Informs users about schedule changes or other events.<br>
