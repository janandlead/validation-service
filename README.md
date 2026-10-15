# Validation Service

Spring Boot employee API using Jakarta Bean Validation, Java records, Spring Data JPA, and PostgreSQL.

## Run

Use JDK 17 or later and Maven. Create a PostgreSQL database and configure the published application's environment variables:

- `DB_URL`: JDBC URL, such as `jdbc:postgresql://localhost:5432/validation_db`
- `DB_USERNAME`: your database username
- `DB_PASSWORD`: your database password

```sh
mvn spring-boot:run
```

The GitHub configuration uses environment variables; the original development workspace retains its local database settings. Do not commit database credentials.

## API

`POST /api/v1/employees` with `Content-Type: application/json`:

```json
{
  "firstName": "Ravi",
  "lastName": "Kumar",
  "email": "ravi@example.com"
}
```

On successful persistence, the API returns HTTP 200 with the three employee fields. Invalid input returns HTTP 400. First name must be nonblank and 3–10 characters; last name must be nonblank; email must be nonblank and pass email validation.

## Study notes

See [PROJECT_NOTES.md](PROJECT_NOTES.md) for records, validation timing, execution flow, persistence, and practice requests.

See [Exception handling and validation notes](EXCEPTION_HANDLING_AND_VALIDATION_NOTES.md) for custom exceptions, global handlers, validation rules, and architecture and request-flow diagrams.

## Verification status

The existing test checks application context loading. The latest local compilation attempt failed with an error referencing the cached Spring Context dependency; successful compilation and live API behavior have not yet been verified.
