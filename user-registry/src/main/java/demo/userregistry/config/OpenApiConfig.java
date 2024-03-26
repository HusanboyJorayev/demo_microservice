package demo.userregistry.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(
        contact = @Contact(
                name = "Husanboy",
                email = "something@gmail.com",
                url = "http://localhost:1020/api/user/getAll"
        ),
        description = "OpenApi documentation for a USER-SERVICE",
        title = "OpenApi specification for a Husanboy",
        version = "3.2.3",
        license = @License(
                name = "License name",
                url = "https://some-url"
        )

),
        servers = {
                @Server(
                        description = "USER-SERVICE",
                        url = "http://localhost:1020"
                )
        }
)
public class OpenApiConfig {
}
