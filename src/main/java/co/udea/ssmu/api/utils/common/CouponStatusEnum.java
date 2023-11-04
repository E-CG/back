package co.udea.ssmu.api.utils.common;

public enum CouponStatusEnum {
    INACTIVO(0),
    ACTIVO(1),
    CADUCADO(2);

    private final int code;

    CouponStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
