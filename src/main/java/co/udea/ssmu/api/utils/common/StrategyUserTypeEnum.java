package co.udea.ssmu.api.utils.common;

public enum StrategyUserTypeEnum {

    FRECUENTE(0),
    OCACIONAL(1);

    private final int code;

    StrategyUserTypeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static StrategyUserTypeEnum getByCode(int code) {
        for (StrategyUserTypeEnum userType : StrategyUserTypeEnum.values()) {
            if (userType.getCode() == code) {
                return userType;
            }
        }
        throw new IllegalArgumentException("Código de estado de cupón no válido: " + code);
    }
}