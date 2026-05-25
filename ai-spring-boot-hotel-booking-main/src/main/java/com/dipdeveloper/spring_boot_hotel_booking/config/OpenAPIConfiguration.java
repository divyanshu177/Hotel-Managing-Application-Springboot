package com.dipdeveloper.spring_boot_hotel_booking.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addServersItem(new Server()
                        .url("http://localhost:8080")
                        .description("Development Server"))
                .addServersItem(new Server()
                        .url("http://api.hotelbooking.com")
                        .description("Production Server"))
                .addTagsItem(new Tag()
                        .name("Hotel Management")
                        .description("APIs for managing hotels"))
                .addTagsItem(new Tag()
                        .name("Room Management")
                        .description("APIs for managing hotel rooms"))
                .addTagsItem(new Tag()
                        .name("Booking Management")
                        .description("APIs for managing room bookings"))
                .info(new Info()
                        .title("Hotel Booking System API")
                        .version("1.0.0")
                        .description("# Hotel Booking System API\n\n" +
                                "A comprehensive REST API for managing hotels, rooms, and bookings built with Spring Boot 4.\n\n" +
                                "## ðŸš€ Features\n\n" +
                                "### Hotel Management\n" +
                                "- Create and manage hotels\n" +
                                "- Search hotels by location\n" +
                                "- Retrieve hotel details\n\n" +
                                "### Room Management\n" +
                                "- Add rooms to hotels\n" +
                                "- Configure room pricing\n" +
                                "- Track room availability\n\n" +
                                "### Booking System\n" +
                                "- Create room bookings\n" +
                                "- Automatic conflict detection\n" +
                                "- Date validation and business rules\n\n" +
                                "## ðŸ—ï¸ Architecture\n\n" +
                                "- **Layered Architecture**: Controller â†’ Service â†’ Repository â†’ Entity\n" +
                                "- **Spring Data JPA** with MySQL database\n" +
                                "- **DTO Pattern** for API contracts\n" +
                                "- **Global Exception Handling** with structured error responses\n" +
                                "- **Input Validation** using Jakarta Validation\n\n" +
                                "## ðŸ“Š API Endpoints\n\n" +
                                "| Resource | Operations |\n" +
                                "|----------|------------|\n" +
                                "| Hotels | `POST /hotels`, `GET /hotels`, `GET /hotels/{id}` |\n" +
                                "| Rooms | `POST /rooms`, `GET /rooms` |\n" +
                                "| Bookings | `POST /bookings`, `GET /bookings` |\n\n" +
                                "## ðŸ” Error Handling\n\n" +
                                "All endpoints return consistent error responses:\n\n" +
                                "```json\n" +
                                "{\n" +
                                "  \"timestamp\": \"2026-03-16T10:30:00\",\n" +
                                "  \"status\": 400,\n" +
                                "  \"error\": \"Validation Failed\",\n" +
                                "  \"message\": \"Input validation failed\",\n" +
                                "  \"validationErrors\": {\n" +
                                "    \"field\": \"error message\"\n" +
                                "  },\n" +
                                "  \"path\": \"/api/v1/hotels\"\n" +
                                "}\n" +
                                "```\n\n" +
                                "## ðŸ“ Getting Started\n\n" +
                                "1. **Create a Hotel**\n" +
                                "   ```bash\n" +
                                "   POST /api/v1/hotels\n" +
                                "   {\n" +
                                "     \"name\": \"Grand Hotel\",\n" +
                                "     \"location\": \"New York\"\n" +
                                "   }\n" +
                                "   ```\n\n" +
                                "2. **Add a Room**\n" +
                                "   ```bash\n" +
                                "   POST /api/v1/rooms?hotelId=1\n" +
                                "   {\n" +
                                "     \"roomNumber\": \"101\",\n" +
                                "     \"price\": 150.00\n" +
                                "   }\n" +
                                "   ```\n\n" +
                                "3. **Create a Booking**\n" +
                                "   ```bash\n" +
                                "   POST /api/v1/bookings\n" +
                                "   {\n" +
                                "     \"guestName\": \"John Doe\",\n" +
                                "     \"checkInDate\": \"2026-03-20\",\n" +
                                "     \"checkOutDate\": \"2026-03-25\",\n" +
                                "     \"roomId\": 1\n" +
                                "   }\n" +
                                "   ```")
                        .contact(new Contact()
                                .name("Hotel Booking System Team")
                                .email("dipdeveloper.connect@gmail.com")
                                .url("https://github.com/TheDipDeveloper"))
                        .license(new License()
                                .name("Apache License 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}

