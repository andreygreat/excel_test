package com.lama.xlsmax.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Работа с файлами Excel (XLSX)",
                description = "API работы с приложением",
                contact = @Contact(name = "Andrey Levonchik", email = "andreylevonchik@gmail.com"),
                license = @License(name = "MIT Licence", url = "https://github.com/hendisantika")),
        servers = @Server(url = "http://localhost:8080")
)
@SecurityScheme(name = "api", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class OpenAPIConfiguration {
}
