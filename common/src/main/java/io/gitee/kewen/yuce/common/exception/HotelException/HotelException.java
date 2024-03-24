package io.gitee.kewen.yuce.common.exception.HotelException;

import io.gitee.kewen.yuce.common.exception.CommonException;

public class HotelException extends CommonException {
    public static final HotelException Add_No_Hotel = new HotelException("这个地方没有旅馆？");

    public static final HotelException Add_No_Hotel_ = new HotelException("查询出错");

    public static final HotelException Add_Error_And_No_Hotel_ = new HotelException("查询出错,这里目前没有查询到其他酒店信息");
    public static final HotelException Add_No_Hotel_picture = new HotelException("暂时没有该旅馆照片");

    public HotelException(String message) {
        super(message);
    }

    public HotelException(String message, Throwable cause) {
        super(message, cause);
    }

}
