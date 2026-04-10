# WhatsApp Chatbot Backend Simulation

This project is a simple Java Spring Boot backend that simulates a WhatsApp chatbot webhook.

## Features

- `POST /webhook` endpoint to receive WhatsApp-style JSON messages
- Predefined chatbot replies:
  - `Hi` -> `Hello`
  - `Bye` -> `Goodbye`
- Logs every incoming message in the application console
- Validation for missing or blank fields
- Health endpoint at `GET /health`
- Includes basic automated tests

## Tech Stack

- Java 21
- Spring Boot
- Maven Wrapper

## Project Structure

```text
whatsapp-chatbot-assignment/
|-- mvnw
|-- mvnw.cmd
|-- pom.xml
|-- README.md
`-- src/
    |-- main/
    |   |-- java/com/ns1903372dot/whatsapp/
    |   |   |-- controller/
    |   |   |-- exception/
    |   |   |-- model/
    |   |   `-- service/
    |   `-- resources/application.properties
    `-- test/java/com/ns1903372dot/whatsapp/
```

## How to Run Locally

1. Open a terminal in the project folder:

```powershell
cd C:\Users\ns190\Downloads\CongnitiveLoad-Detection-main\whatsapp-chatbot-assignment
```

2. Start the application:

```powershell
.\mvnw.cmd spring-boot:run
```

3. The application will run at:

```text
http://localhost:8080
```

## API Endpoint

### `POST /webhook`

Sample request:

```json
{
  "from": "919999999999",
  "message": "Hi"
}
```

Sample response:

```json
{
  "from": "919999999999",
  "receivedMessage": "Hi",
  "reply": "Hello"
}
```

Another example:

Request:

```json
{
  "from": "919999999999",
  "message": "Bye"
}
```

Response:

```json
{
  "from": "919999999999",
  "receivedMessage": "Bye",
  "reply": "Goodbye"
}
```

Fallback example:

```json
{
  "from": "919999999999",
  "message": "How are you?"
}
```

Response:

```json
{
  "from": "919999999999",
  "receivedMessage": "How are you?",
  "reply": "Sorry, I only understand messages like Hi or Bye."
}
```

## Test the API with cURL

```powershell
curl -X POST http://localhost:8080/webhook `
  -H "Content-Type: application/json" `
  -d "{\"from\":\"919999999999\",\"message\":\"Hi\"}"
```

## Run Tests

```powershell
.\mvnw.cmd test
```

## Deploy on Render

This project is ready for Render deployment using the included `Dockerfile` and `render.yaml`.

Option 1: Deploy from Docker

1. Log in to Render
2. Click `New` -> `Web Service`
3. Connect your GitHub repository
4. Select this project repository
5. Render will detect the `Dockerfile`
6. Deploy the service

Option 2: Deploy as a Blueprint

1. Log in to Render
2. Click `New` -> `Blueprint`
3. Select the repository
4. Render will read `render.yaml`
5. Confirm and deploy

Expected live endpoints after deployment:

- `GET /health`
- `POST /webhook`

## Assumptions and Trade-offs

- This project focuses only on backend simulation and does not connect to the actual WhatsApp Business API.
- Replies are intentionally hardcoded because the assignment asks for predefined chatbot behavior.
- The solution is kept intentionally small and readable to match the assignment scope.
