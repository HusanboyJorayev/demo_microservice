package demo.userregistry.config;

import org.springframework.stereotype.Component;
@Component
public class ResponseConfig  {

    private static final String CUSTOM_RESPONSE = """
            {
              "id": int,
              "firstname": "string",
              "lastname": "string",
              "age": int,
              "cards": [
                {
                  "id": int,
                  "userId": int,
                  "name": "string",
                  "balance": double,
                  "expired": "String",
                  "createdAt": LocalDateTime
                },
                ..
                ..
                ..
                ..
                ]
            """;
}
