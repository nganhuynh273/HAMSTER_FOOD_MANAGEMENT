package com.codegym.hpn.utils;

public class ErrorMessage {
    public static final String VALID_MATERIAL_NAME = "Tên thức ăn chỉ chứa các chữ cái và chữ số.";
    public static final String MAX_MATERIAL_NAME_LENGTH = "Ký tự tối đa của tên vật liệu là 100.";

    public static final String SERVER_ERROR = "Không thành công, vui lòng liên hệ với quản lý hệ thống.";

    public static final String MATERIAL_EXISTS = "Tên thức ăn không tồn tại. Thử một tên vật liệu khác.";

    public static final String PRODUCT_TYPE_NOT_EXIST = "ID loại sản phẩm không tồn tại!";

    public static String getNotEmptyMessage(String string) {
        return string + " không được để trống.";
    }

    public static String getNotNumberMessage(String string) {
        return string + " chỉ chứa các chữ số.";
    }

    public static String getMinValue(String string, String min) {
        if (min.equals("0")) {
            return string + " không được bằng hoặc nhỏ hơn 0.";
        }
        return string + " không được nhỏ hơn " + min + ".";
    }

    public static String getMaxValue(String string, String max) {
        return string + " không được lớn hơn " + max + ".";
    }

    public static String getProductionDateLimit(String date) {
        return "Ngày sản xuất phải giữa " + date + " và một ngày trước hiện tại.";
    }

    public static String getExpirationDateLimit(String date) {
        return "Ngày hết hạn phải sau ngày hiện tại và " + date + ".";
    }
}
