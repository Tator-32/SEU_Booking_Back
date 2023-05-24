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
public class UserComments implements Serializable {

    private static final long serialVersionUID = 1L;

    private String hotelName;

    private String comment;

    private String userName;

    private String url;


}
