package org.example.homeworkstub.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Система выдачи микрокредитов.",
                description = "Система процессинга (в сфере финансов процессинг" +
                        "– это процесс обработки и подтверждения платежей, совершаемых с использованием банковских карт, электронных кошельков и других платежных инструментов)",
                contact = @Contact(
                        name = "David",
                        email = "d-gvalia@yandex.ru"
                )
        )
)
public class SwaggerConfig {

}
