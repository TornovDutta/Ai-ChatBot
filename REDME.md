
# Ai-ChatBot

Ai-ChatBot is a REST API that utilizes Ollama AI to answer questions based on the content extracted from uploaded PDF files. It allows users to interact with PDF documents by querying their contents and receiving summarized answers or plain text extracts.

## Features:
- **Answer Questions**: Ask questions about PDF content.
- **Extract Text**: Extract raw text from a PDF document.
- **Summary**: Generate a summary of the PDF content.
- **Status Check**: Verify the API is running.

## API Endpoints:

| HTTP Method | Endpoint             | Description                                                                 | Parameters                                                   | Response                     |
|-------------|----------------------|-----------------------------------------------------------------------------|--------------------------------------------------------------|------------------------------|
| **POST**    | `/api/ask`            | Ask a question about the content in an uploaded PDF                         | `question` (String), `file` (MultipartFile)                  | Answer based on PDF content  |
| **POST**    | `/api/extract-text`   | Extract raw text from the provided PDF                                      | `file` (MultipartFile)                                        | Raw text extracted from PDF  |
| **GET**     | `/api/status`         | Check if the API is running                                                | None                                                         | Confirmation message         |
| **POST**    | `/api/ask-without-pdf`| Ask a question without uploading a PDF                                      | `question` (String)                                           | AI-generated answer          |
| **POST**    | `/api/summary`        | Generate a summary of the uploaded PDF content                              | `file` (MultipartFile)                                        | Summary of PDF content       |

## Authors
- **Tornov Dutta**
- **Rohit Majhumder**

## How to Run the Project

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/TornovDutta/Ai-ChatBot.git
   ```

2. **Navigate to the Project Directory**:
   ```bash
   cd Ai-ChatBot
   ```

3. **Install Dependencies**:
   Ensure you have Java and Maven installed. Add the following dependencies to your `pom.xml` file:

   ```xml
   <dependencies>
       <!-- Apache PDFBox for PDF handling -->
       <dependency>
           <groupId>org.apache.pdfbox</groupId>
           <artifactId>pdfbox</artifactId>
           <version>2.0.29</version>
       </dependency>

       <!-- Spring Boot Starter Web for web functionality -->
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-web</artifactId>
           <version>2.6.6</version>
       </dependency>

       <!-- Ollama AI Spring Boot Starter -->
       <dependency>
           <groupId>org.springframework.ai</groupId>
           <artifactId>spring-ai-starter-model-ollama</artifactId>
           <version>1.0.0</version>
       </dependency>
   </dependencies>
   ```

4. **Run the Application**:
   Start the Spring Boot application using the following command:
   ```bash
   mvn spring-boot:run
   ```

5. **Access the API**:
   The API will be available at `http://localhost:8080`.


