package com.dipdeveloper.spring_boot_hotel_booking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RoomDTO - Data Transfer Object for Room entity
 * Used for API request/response handling
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Room data transfer object")
public class RoomDTO {

    @JsonProperty("id")
    @Schema(description = "Unique identifier of the room", example = "1")
    private Long id;

    @NotBlank(message = "Room number is required")
    @JsonProperty("roomNumber")
    @Schema(description = "Room number/identifier", example = "101", required = true)
    private String roomNumber;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    @JsonProperty("price")
    @Schema(description = "Price per night for the room", example = "150.00", required = true)
    private Double price;

    @NotNull(message = "Hotel ID is required")
    @JsonProperty("hotelId")
    @Schema(description = "ID of the hotel this room belongs to", example = "1", required = true)
    private Long hotelId;

    @JsonProperty("hotelName")
    @Schema(description = "Name of the hotel this room belongs to", example = "Grand Hotel")
    private String hotelName;
}

