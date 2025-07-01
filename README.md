# Secure Password Generator API

This project is a simple and secure **password generation API** built entirely in raw **Java 17** (no frameworks) with a functional **HTML+CSS+JS frontend**.  

Passwords are generated based on user preferences and saved locally in a JSON log.

The project demonstrates solid backend skills with RESTful design, JSON serialization, HTTP server handling, and full frontend integration — all without frameworks.

---

## Features

### Backend
- Built in pure Java with `com.sun.net.httpserver`
- Accepts `POST /generate` requests with password options
- Returns a randomly generated secure password
- Stores each generated password in a local `passwords.json` file
- Supports CORS for frontend communication

### Frontend
- Modern HTML5 + CSS3 + Vanilla JS interface
- Allows users to choose:
  - Password length
  - Uppercase letters
  - Numbers
  - Symbols
- Displays password result dynamically
- Consumes backend API via `fetch()`

---

## Technologies Used

| Layer     | Tech Used                          |
|-----------|------------------------------------|
| Backend   | Java 17, Maven, Jackson            |
| Webserver | com.sun.net.httpserver (built-in) |
| Frontend  | HTML5, CSS3, Vanilla JavaScript    |
| Storage   | Local JSON file (`passwords.json`) |

---

## Clone the Project

```bash
git clone https://github.com/NicolauAlfredo/secure-password-generator.git
cd secure-password-generator
```

## Run the Backend Server
```bash
mvn clean compile exec:java
```
- You should see:

```arduino
Password Generator API running at http://localhost:8000
```
- Make sure the following is in your pom.xml:

```xml
<exec.mainClass>com.nicolaualfredo.password.PasswordApp</exec.mainClass>
```

## Run the Frontend
- You need a local static server to serve index.html.
- If you have Python installed:
```bash
cd frontend
python -m http.server 5500
```

- Then open:

```arduino
http://localhost:5500
```

## Example Request (POST)
- You can test it with Postman or curl:

```bash
curl -X POST http://localhost:8000/generate \
  -H "Content-Type: application/json" \
  -d '{
    "length": 12,
    "includeUppercase": true,
    "includeNumbers": true,
    "includeSymbols": false
}'
```

## License
This project is open source under the MIT License.

## Demo
