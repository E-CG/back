package co.udea.ssmu.api.utils;

public enum CouponStatus {
    INACTIVO(0),
    ACTIVO(1),
    CADUCADO(2);

    private final int code;

    CouponStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
