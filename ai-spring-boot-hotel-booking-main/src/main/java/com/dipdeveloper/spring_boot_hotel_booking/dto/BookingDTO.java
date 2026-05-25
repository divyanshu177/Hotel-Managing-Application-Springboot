package com.dipdeveloper.spring_boot_hotel_booking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * BookingDTO - Data Transfer Object for Booking entity
 * Used for API request/response handling
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Booking data transfer object")
public class BookingDTO {

    @JsonProperty("id")
    @Schema(description = "Unique identifier of the booking", example = "1")
    private Long id;

    @NotBlank(message = "Guest name is required")
    @JsonProperty("guestName")
    @Schema(description = "Name of the guest making the booking", example = "John Doe", required = true)
    private String guestName;

    @NotNull(message = "Check-in date is required")
    @JsonProperty("checkInDate")
    @Schema(description = "Check-in date for the booking", example = "2026-03-20", required = true)
    private LocalDate checkInDate;

    @NotNull(message = "Check-out date is required")
    @JsonProperty("checkOutDate")
    @Schema(description = "Check-out date for the booking", example = "2026-03-25", required = true)
    private LocalDate checkOutDate;

    @NotNull(message = "Room ID is required")
    @JsonProperty("roomId")
    @Schema(description = "ID of the room being booked", example = "1", required = true)
    private Long roomId;

    @JsonProperty("roomNumber")
    @Schema(description = "Room number of the booked room", example = "101")
    private String roomNumber;
}

