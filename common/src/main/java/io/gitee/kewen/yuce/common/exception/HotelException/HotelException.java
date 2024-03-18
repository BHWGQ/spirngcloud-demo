package io.gitee.kewen.yuce.common.exception.HotelException;

import io.gitee.kewen.yuce.common.exception.CommonException;

public class HotelException extends CommonException {
    public static final HotelException Add_No_Hotel = new HotelException("这个地方没有旅馆？");

    public static final HotelException Add_No_Hotel_picture = new HotelException("暂时没有该旅馆照片");

    public HotelException(String message) {
        super(message);
    }

    public HotelException(String message, Throwable cause) {
        super(message, cause);
    }

}
