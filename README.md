# Resume Analyzer (Spring Boot)

Features:
- Upload PDF/DOCX
- Extract text (Apache Tika)
- Compare with Job Description via OpenAI
- ATS score, missing keywords, suggestions
- MongoDB storage
- JWT Auth
- Postman tests included

## Run
1. Install Java 17, Maven, MongoDB
2. Update `openai.api.key` in application.properties
3. mvn clean install
4. mvn spring-boot:run

## Endpoints
- POST /api/auth/login
- POST /api/resume/analyze (multipart: file, jd)

## Notes
- JWT filter is basic; for production, harden validation & error handling
- OpenAI response is returned raw JSON; parse as needed
