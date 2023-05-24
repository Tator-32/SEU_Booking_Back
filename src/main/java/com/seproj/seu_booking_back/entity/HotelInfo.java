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
 * @since 2023-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class HotelInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String hotelName;

    private String hotelAddress;

    private String hotelFeature;

    private String commentLevel;

    private String commentNumber;

    private String commentScore;

    private String imgUrl1;

    private String imgUrl2;

    private String imgUrl3;

    private String imgUrl4;

    private String imgUrl5;

    private String imgUrl6;

    private String imgUrl7;


}
