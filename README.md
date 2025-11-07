# spring-ai-workshop

Demo project showing Spring Boot + Spring AI (OpenAI model) usage.

## Summary

A minimal Spring Boot application that demonstrates using the Spring AI chat client to call OpenAI models from a `ChatController` exposing a few REST endpoints.

## Key files

- `pom.xml` - Maven build file and dependencies
- `src/main/java/com/denisarruda/spring_ai_workshop/SpringAiWorkshopApplication.java` - Spring Boot application entry
- `src/main/java/com/denisarruda/spring_ai_workshop/chat/ChatController.java` - REST endpoints that use the `ChatClient`
- `src/main/resources/application.properties` - configuration (API key, model)
- `RestClient.http` - example HTTP requests for local testing

## Dependencies (from `pom.xml`)

Notable dependencies declared in the project:

- org.springframework.boot:spring-boot-starter-parent (parent POM) - version 3.5.7
- org.springframework.boot:spring-boot-starter-web - web server and MVC
- org.springframework.ai:spring-ai-starter-model-openai - Spring AI OpenAI model starter
- org.springframework.boot:spring-boot-devtools (runtime, optional) - developer tooling
- org.springframework.boot:spring-boot-starter-test (test scope)

Also the project uses the Spring AI BOM with property `spring-ai.version=1.0.3` and Java version set to `25` in `pom.xml`.

## Required configuration

The application requires an OpenAI API key to call the model. Configuration is read from `application.properties`:

```
spring.application.name=spring-ai-workshop
spring.ai.openai.api-key=${OPENAI_API_KEY}
spring.ai.openai.model=gpt-4o
```

You can provide the API key via environment variable or directly set it in `application.properties` (not recommended for secrets). Example (Linux/macOS):

```bash
export OPENAI_API_KEY="sk_..."
# then run the app (see next section)
```

The configured model in the repo is `gpt-4o`. Change `spring.ai.openai.model` if you want another model supported by Spring AI.

## Declared REST endpoints

All endpoints are in `ChatController` class:

- GET /chat
  - Returns a single String containing the model's reply to the prompt: "Tell me an interesting fact about Java"
  - Response: plain text (String)

- GET /stream
  - Returns a reactive stream (`Flux<String>`) of content lines produced by a streaming prompt: "I will visit Paris soon. can you give me 10 places to visit?"
  - Useful for SSE/WebFlux clients or reactive consumption.

- GET /joke
  - Returns a `ChatResponse` object (from Spring AI) with the model's reply to: "Tell me a dad joke about programming"
  - Response: JSON representation of `ChatResponse`

Example local requests (also included in `RestClient.http`):

```
http://localhost:8080/chat
http://localhost:8080/stream
http://localhost:8080/joke
```

You can curl the endpoints, for example:

```bash
curl http://localhost:8080/chat
curl http://localhost:8080/joke
# For /stream use a client that supports streaming or visit in a browser
```

## How to build and run

Requirements:
- Java 25 (as declared in `pom.xml`) — ensure your JDK matches the declared version or change the `java.version` property to your installed JDK.
- Maven 3.x
- An OpenAI API key

Build and run with Maven:

```bash
# set API key in env
export OPENAI_API_KEY="sk_..."
# run via Spring Boot plugin
./mvnw spring-boot:run

# or build a jar and run
./mvnw package
java -jar target/spring-ai-workshop-0.0.1-SNAPSHOT.jar
```

Notes:
- `spring-boot-devtools` is included as an optional runtime dependency to speed development (automatic restarts). It is not intended for production.
- If you don't want to expose your key via environment variables, you can place it in `src/main/resources/application.properties` but avoid committing secrets.

## Next steps / Suggestions

- Add example unit/integration tests that mock the `ChatClient` or use a test stub.
- Add runtime checks and error handling for missing API key.
- Add documentation around supported models and rate limits.

---

Generated from project sources on 2025-11-06.
