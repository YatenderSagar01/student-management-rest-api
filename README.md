# Student Management REST API

A simple Spring Boot REST API for managing student records. It supports create, read, update, and delete operations, plus searches by branch, year, and minimum CGPA.

## Features

- List all students or retrieve one by ID
- Add, update, and delete student records
- Search students by branch and year
- Find students with a minimum CGPA
- IntelliJ HTTP request examples in [student-api.http](student-api.http)

## Requirements

- Java 21

## Run the application

From the project directory, run the Maven wrapper.

**Windows PowerShell:**

```powershell
.\mvnw.cmd spring-boot:run
```

**macOS or Linux:**

```sh
./mvnw spring-boot:run
```

The API starts at `http://localhost:8080`.

## API endpoints

| Method | Endpoint | Description |
| --- | --- | --- |
| GET | `/students` | Get all students |
| GET | `/students/{studentId}` | Get a student by ID |
| POST | `/students` | Add a student |
| PUT | `/students/{studentId}` | Update a student |
| DELETE | `/students/{studentId}` | Delete a student |
| GET | `/students/search?branch=CSE&year=2` | Search by branch and year |
| GET | `/students/topper?cgpa=9.0` | Find students at or above a CGPA |

### Example: add a student

```sh
curl -X POST http://localhost:8080/students \
  -H "Content-Type: application/json" \
  -d '{
    "studentId": 106,
    "name": "Amit Kumar",
    "email": "amit@kiet.edu",
    "branch": "CSE",
    "year": 2,
    "cgpa": 8.75
  }'
```

The API returns `201 Created` when a student is added. A duplicate student ID returns `400 Bad Request`; a missing student returns `404 Not Found`.

## Sample data

The application starts with hard-coded sample records held in memory. Changes made through the API are lost when the application restarts. No database is configured.
