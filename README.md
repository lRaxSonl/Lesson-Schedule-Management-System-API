# Schedule Management System

## Project Description

The Schedule Management System is designed for creating, editing, and viewing schedules for students, teachers, and groups. It allows users to fetch schedules by group, student, or teacher, and manage courses, classrooms, and notifications.

## Tech Stack

- **Spring Boot** - The main framework for building the application.
- **MariaDB** - Database for storing schedules, groups, students, and teachers information.
- **Spring Data JPA** - For interacting with the database through repositories.
- **Spring Security** - For security and user authorization.
- **MapStruct** - For mapping entities to DTOs and vice versa.
- **JWT** - For user authentication via JSON Web Tokens.
- **Lombok** - To simplify Java code.

## Requirements

1. **Java 17 or higher**.
2. **MariaDB** or compatible database.
3. **Maven** for building and managing dependencies.

## Setting Up the Project

### 1. Clone the repository

```bash
git clone https://github.com/yourusername/ScheduleManagementSystem.git
cd ScheduleManagementSystem
```

# Creating database
```
CREATE DATABASE schedule_management_system;
CREATE USER 'root'@'localhost' IDENTIFIED BY 'yourpassword';
GRANT ALL PRIVILEGES ON schedule_management_system.* TO 'root'@'localhost';
```

# Configure the application
```
spring.application.name=ScheduleManagementSystem
spring.datasource.url=jdbc:mariadb://localhost:3306/schedule_management_system
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
```

# Auto-update database schema
```
spring.jpa.hibernate.ddl-auto=update
```

# Enable SQL query logging
```
spring.jpa.show-sql=true
```

# Running application
```
mvn spring-boot:run
```

<h3>Once the application starts, it will be available at http://localhost:8080</h3>

# Schedule Management System API Documentation

## Authentication API

### `POST /api/auth/login`
- **Description**: Authenticates a user and returns a JWT token for further requests.
- **Request Body**:
  - `username` (string): The username of the user.
  - `password` (string): The password of the user.
- **Response**:
  - `token` (string): JWT token used for subsequent authenticated requests.
- **Response Codes**:
  - `200 OK`: Successfully authenticated and token returned.
  - `401 Unauthorized`: Invalid credentials.

---

## Classroom API

### `GET /api/classroom`
- **Description**: Retrieves a list of all classrooms.
- **Response**:
  - A list of classrooms with details such as `id`, `name`, and `capacity`.
- **Response Codes**:
  - `200 OK`: Successfully retrieved list of classrooms.

### `GET /api/classroom/{id}`
- **Description**: Retrieves details of a specific classroom by ID.
- **Parameters**:
  - `id` (long): ID of the classroom to retrieve.
- **Response**:
  - The classroom details with `id`, `name`, and `capacity`.
- **Response Codes**:
  - `200 OK`: Classroom details retrieved successfully.
  - `404 Not Found`: Classroom with specified ID not found.

### `POST /api/classroom/create`
- **Description**: Creates a new classroom.
- **Request Body**:
  - `name` (string): The name of the classroom.
  - `capacity` (integer): The capacity of the classroom.
- **Response**:
  - The details of the newly created classroom with `id`, `name`, and `capacity`.
- **Response Codes**:
  - `201 Created`: Classroom created successfully.

### `PUT /api/classroom/update/{id}`
- **Description**: Updates an existing classroom by ID.
- **Parameters**:
  - `id` (long): ID of the classroom to update.
- **Request Body**:
  - `name` (string): The updated name of the classroom.
  - `capacity` (integer): The updated capacity of the classroom.
- **Response**:
  - The updated classroom details.
- **Response Codes**:
  - `200 OK`: Classroom updated successfully.
  - `404 Not Found`: Classroom with the specified ID not found.

### `DELETE /api/classroom/delete/{id}`
- **Description**: Deletes a classroom by ID.
- **Parameters**:
  - `id` (long): ID of the classroom to delete.
- **Response**: No content response (204).
- **Response Codes**:
  - `204 No Content`: Classroom deleted successfully.
  - `404 Not Found`: Classroom with the specified ID not found.

---

## Group API

### `GET /api/groups/{id}`
- **Description**: Retrieves details of a specific group by ID.
- **Parameters**:
  - `id` (long): ID of the group to retrieve.
- **Response**:
  - The group details with `id`, `name`, and `course`.
- **Response Codes**:
  - `200 OK`: Group details retrieved successfully.
  - `404 Not Found`: Group with the specified ID not found.

### `GET /api/groups`
- **Description**: Retrieves a list of all groups.
- **Response**:
  - A list of groups with `id`, `name`, and `course`.
- **Response Codes**:
  - `200 OK`: Successfully retrieved list of groups.

### `POST /api/groups/create`
- **Description**: Creates a new group.
- **Request Body**:
  - `name` (string): The name of the group.
  - `course` (string): The course of the group.
- **Response**:
  - The details of the newly created group with `id`, `name`, and `course`.
- **Response Codes**:
  - `201 Created`: Group created successfully.

### `PUT /api/groups/update/{id}`
- **Description**: Updates an existing group by ID.
- **Parameters**:
  - `id` (long): ID of the group to update.
- **Request Body**:
  - `name` (string): The updated name of the group.
  - `course` (string): The updated course of the group.
- **Response**:
  - The updated group details.
- **Response Codes**:
  - `200 OK`: Group updated successfully.
  - `404 Not Found`: Group with the specified ID not found.

### `DELETE /api/groups/delete/{id}`
- **Description**: Deletes a group by ID.
- **Parameters**:
  - `id` (long): ID of the group to delete.
- **Response**: No content response (204).
- **Response Codes**:
  - `204 No Content`: Group deleted successfully.
  - `404 Not Found`: Group with the specified ID not found.

---

## Lesson API

### `GET /api/lesson/`
- **Description**: Retrieves a list of all lessons.
- **Response**:
  - A list of lessons with `id`, `name`, `teacher`, and `time`.
- **Response Codes**:
  - `200 OK`: Successfully retrieved list of lessons.

### `GET /api/lesson/{id}`
- **Description**: Retrieves details of a specific lesson by ID.
- **Parameters**:
  - `id` (long): ID of the lesson to retrieve.
- **Response**:
  - The lesson details with `id`, `name`, `teacher`, and `time`.
- **Response Codes**:
  - `200 OK`: Lesson details retrieved successfully.
  - `404 Not Found`: Lesson with the specified ID not found.

### `POST /api/lesson/create`
- **Description**: Creates a new lesson.
- **Request Body**:
  - `name` (string): The name of the lesson.
  - `teacher` (string): The teacher's name.
  - `time` (string): The time of the lesson (in `YYYY-MM-DD HH:mm:ss` format).
- **Response**:
  - The details of the newly created lesson with `id`, `name`, `teacher`, and `time`.
- **Response Codes**:
  - `201 Created`: Lesson created successfully.

### `PUT /api/lesson/update/{id}`
- **Description**: Updates an existing lesson by ID.
- **Parameters**:
  - `id` (long): ID of the lesson to update.
- **Request Body**:
  - `name` (string): The updated name of the lesson.
  - `teacher` (string): The updated teacher name.
  - `time` (string): The updated time of the lesson.
- **Response**:
  - The updated lesson details.
- **Response Codes**:
  - `200 OK`: Lesson updated successfully.
  - `404 Not Found`: Lesson with the specified ID not found.

### `DELETE /api/lesson/delete/{id}`
- **Description**: Deletes a lesson by ID.
- **Parameters**:
  - `id` (long): ID of the lesson to delete.
- **Response**: No content response (204).
- **Response Codes**:
  - `204 No Content`: Lesson deleted successfully.
  - `404 Not Found`: Lesson with the specified ID not found.

---

## Schedule API

### `GET /api/schedule/{id}`
- **Description**: Retrieves details of a specific schedule by ID.
- **Parameters**:
  - `id` (long): ID of the schedule to retrieve.
- **Response**:
  - The schedule details with `id`, `lesson`, `teacher`, and `time`.
- **Response Codes**:
  - `200 OK`: Schedule retrieved successfully.
  - `404 Not Found`: Schedule with the specified ID not found.

### `POST /api/schedule/byGroup`
- **Description**: Retrieves the schedule for a specific group.
- **Request Body**:
  - `groupName` (string): The name of the group.
- **Response**:
  - A list of schedules associated with the group.
- **Response Codes**:
  - `200 OK`: Schedules for the group retrieved successfully.

### `POST /api/schedule/byStudent`
- **Description**: Retrieves the schedule for a specific student.
- **Request Body**:
  - `studentUsername` (string): The username of the student.
- **Response**:
  - A list of schedules associated with the student.
- **Response Codes**:
  - `200 OK`: Schedules for the student retrieved successfully.

### `POST /api/schedule/byTeacher`
- **Description**: Retrieves the schedule for a specific teacher.
- **Request Body**:
  - `teacherUsername` (string): The username of the teacher.
- **Response**:
  - A list of schedules associated with the teacher.
- **Response Codes**:
  - `200 OK`: Schedules for the teacher retrieved successfully.

### `GET /api/schedule`
- **Description**: Retrieves a list of all schedules.
- **Response**:
  - A list of all schedules with lesson details.
- **Response Codes**:
  - `200 OK`: Successfully retrieved list of schedules.

### `POST /api/schedule/create`
- **Description**: Creates a new schedule.
- **Request Body**:
  - `lessonId` (long): The lesson ID to be scheduled.
  - `classRoomId` (long): The classroom ID where the lesson will take place.
  - `startTime` (string): The start time of the lesson.
  - `endTime` (string): The end time of the lesson.
- **Response**:
  - The details of the newly created schedule.
- **Response Codes**:
  - `201 Created`: Schedule created successfully.

### `PUT /api/schedule/update/{id}`
- **Description**: Updates an existing schedule by ID.
- **Parameters**:
  - `id` (long): ID of the schedule to update.
- **Request Body**:
  - `lessonId` (long): Updated lesson ID.
  - `classRoomId` (long): Updated classroom ID.
  - `startTime` (string): Updated start time.
  - `endTime` (string): Updated end time.
- **Response**:
  - The updated schedule details.
- **Response Codes**:
  - `200 OK`: Schedule updated successfully.
  - `404 Not Found`: Schedule with the specified ID not found.

### `DELETE /api/schedule/delete/{id}`
- **Description**: Deletes a schedule by ID.
- **Parameters**:
  - `id` (long): ID of the schedule to delete.
- **Response**: No content response (204).
- **Response Codes**:
  - `204 No Content`: Schedule deleted successfully.
  - `404 Not Found`: Schedule with the specified ID not found.
