package com.seproj.seu_booking_back.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Tator
 * @since 2023-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class HotelInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID for hotel.
     */
    private String hotelId;

    /**
     * Name of hotel.
     */
    private String hotelName;

    /**
     * Address of hotel.
     */
    private String hotelAddress;


}
