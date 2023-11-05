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

    public static CouponStatusEnum getByCode(int code) {
        for (CouponStatusEnum status : CouponStatusEnum.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Código de estado de cupón no válido: " + code);
    }    
}
