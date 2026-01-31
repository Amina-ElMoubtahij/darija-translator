
# Darija Translator Project

This project is a Darija (Moroccan Arabic) translator that allows users to select text on a web page and translate it into Darija using a Java backend and a browser extension frontend.

The project combines a REST API built with Java and a browser extension interface to provide fast and simple translations.



## Project Structure

```

translator/
├─ src/
│  ├─ main/
│  │  ├─ java/
│  │  │  └─ org/mytranslator/
│  │  │     ├─ TranslatorApplication.java
│  │  │     ├─ TranslatorResource.java
│  │  │     ├─ WelcomeResource.java
│  │  │     ├─ Message.java
│  │  │     ├─ BasicAuthFilter.java
│  │  │     └─ CorsFilter.java
│  └─ resources/
│     └─ META-INF/beans.xml
├─ extension/
│  ├─ background.js
│  ├─ content.js
│  ├─ manifest.json
│  ├─ sidepanel.js
│  ├─ sidepanel.html
│  └─ sidepanel.css
├─ pom.xml
├─ README.md
└─ .gitignore

```


## Features

- Translate selected text into Darija.
- Java REST API backend.
- Browser extension frontend.
- CORS and Basic Authentication handling.
- Simple and user-friendly interface.



## Technologies Used

- Java (Maven)
- JAX-RS / REST API
- Eclipse IDE
- JavaScript
- HTML / CSS
- Browser Extension API
- Large Language Model (Gemini API) for Darija translation

##  How to Run the Project

### Backend (Java)

1. Open the project in Eclipse.
2. Make sure Maven dependencies are installed.
3. Run the server (WildFly / Tomcat depending on your configuration).
4. The API will be available on `http://localhost`.

## Authentication

This API uses Basic Authentication for requests.

Demo credentials:

- Username: user  
- Password: password  

Example request using curl:

```bash
curl -X POST "http://localhost:8080/Translation/rest/translate" \
 -H "Content-Type: application/json" \
 -H "Authorization: Basic dXNlcjpwYXNzd29yZA==" \
 -d "{\"text\":\"How are you\"}" 
```


### Extension (Frontend)

1. Open your browser.
2. Go to Extensions page.
3. Enable **Developer Mode**.
4. Click **Load unpacked**.
5. Select the `extension` folder.



##  Usage

1. Type text manually or select text on any web page.
2. Open the translator side panel.
3. The selected text will be translated into Darija.
4. View the result instantly.

## Demo Video

The demo video is available directly on GitHub. 
Drive:https://drive.google.com/file/d/1Qf3xKhch2JB0HT--4_XnrBxyne9Lecwp/view?usp=sharing

##  Author

Amina El Moubtahij  

