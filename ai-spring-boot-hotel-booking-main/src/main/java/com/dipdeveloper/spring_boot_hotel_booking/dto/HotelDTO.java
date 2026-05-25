package com.dipdeveloper.spring_boot_hotel_booking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * HotelDTO - Data Transfer Object for Hotel entity
 * Used for API request/response handling
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Hotel data transfer object")
public class HotelDTO {

    @JsonProperty("id")
    @Schema(description = "Unique identifier of the hotel", example = "1")
    private Long id;

    @NotBlank(message = "Hotel name is required")
    @Size(min = 2, max = 100, message = "Hotel name must be between 2 and 100 characters")
    @JsonProperty("name")
    @Schema(description = "Name of the hotel", example = "Grand Hotel", required = true)
    private String name;

    @NotBlank(message = "Hotel location is required")
    @JsonProperty("location")
    @Schema(description = "Location/city of the hotel", example = "New York", required = true)
    private String location;
}

