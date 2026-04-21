# Resume Analyzer Backend (Spring Boot)

## Overview

This project is a backend REST API for analyzing resumes against job descriptions. It allows users to upload resumes in PDF or DOCX format, extracts text, compares it with a given job description, and generates insights such as ATS score, missing keywords, and improvement suggestions using OpenAI.

The application is built using Spring Boot, MongoDB, and Docker, and includes authentication and testing support via Postman.

---

## Features

* Upload resume files (PDF, DOCX)
* Extract text using Apache Tika
* Compare resume with job description
* Generate:

  * ATS score
  * Missing keywords
  * Improvement suggestions
* Store analysis history in MongoDB
* JWT-based authentication
* RESTful API design
* Docker support for containerized deployment
* Postman collection for testing

---

## Tech Stack

* Java 17
* Spring Boot
* MongoDB
* Apache Tika
* OpenAI API
* JWT (JSON Web Token)
* Maven
* Docker

---

## Project Structure

```
src/
 ├── main/
 │   ├── java/com/resumeanalyzer/
 │   │   ├── config/        # Configuration classes (security, beans, etc.)
 │   │   ├── controller/    # REST controllers handling API requests
 │   │   ├── model/         # Data models and entities
 │   │   ├── repository/    # MongoDB repositories for data access
 │   │   ├── service/       # Business logic and processing
 │   │   ├── util/          # Utility/helper classes
 │   │   └── ResumeAnalyzerApplication.java  # Main entry point of the application
 │   └── resources/
 │       └── application.properties  # Application configuration (uses environment variables)
 └── test/
     └── postman/          # Postman collection for API testing
```

---

## Configuration

The application uses environment variables for sensitive data.

Update `application.properties`:

```
server.port=6969
spring.data.mongodb.uri=${MONGODB_URI}

jwt.secret=${JWT_SECRET}
jwt.expiration=86400000

openai.api.key=${OPENAI_API_KEY}
```

### Environment Variables

Set the following:

* `MONGODB_URI`
* `JWT_SECRET`
* `OPENAI_API_KEY`

---

## Running the Application

### Prerequisites

* Java 17
* Maven
* MongoDB running locally or remotely

### Steps

1. Clone the repository:

```
git clone https://github.com/Spark1805/resume_analyzer.git
cd resume_analyzer
```

2. Install dependencies:

```
mvn clean install
```

3. Run the application:

```
mvn spring-boot:run
```

4. Access the API:

```
http://localhost:6969
```

---

## API Endpoints

### Authentication

POST `/api/auth/login`

Request:

```
{
  "username": "test",
  "password": "123"
}
```

Response:

* Returns JWT token

---

### Resume Analysis

POST `/api/resume/analyze`

Form Data:

* `file` (PDF/DOCX)
* `jd` (job description text)

Headers:

```
Authorization: Bearer <token>
```

Response:

* Analysis result (ATS score, keywords, suggestions)

---

## Docker Setup

### Build Image

```
docker build -t resume-analyzer .
```

### Run Container

```
docker run -p 6969:6969 \
-e MONGODB_URI=mongodb://host.docker.internal:27017/resumeDB \
-e JWT_SECRET=your_secret \
-e OPENAI_API_KEY=your_key \
resume-analyzer
```

---

## Testing

Postman collection is available in:

```
tests/postman/ResumeAnalyzer.postman_collection.json
```

Import it into Postman and test the endpoints.

---

## Important Notes

* Do not commit API keys or secrets to the repository
* Ensure MongoDB is running before starting the application
* OpenAI API usage may incur costs

---

## Future Improvements

* Better response parsing from OpenAI
* Role-based authentication
* Rate limiting and validation
* UI integration (frontend)
* Enhanced error handling

---

## License

This project is licensed under the MIT License.
