package co.udea.ssmu.api.utils.common;

import org.springframework.stereotype.Component;

import co.udea.ssmu.api.utils.exception.InconsistentDiscountException;
import co.udea.ssmu.api.utils.exception.InvalidDiscountPercentage;

@Component
public class DiscountValidator {
  public void validateDiscount(int discountPercentage, Integer discountValue) {
    if (discountPercentage < 0 || discountPercentage > 100) {
      throw new InvalidDiscountPercentage("El porcentaje de descuento debe estar entre 0 y 100");
    }

    if ((discountPercentage > 0 && discountValue != null && discountValue != 0)
        || (discountPercentage == 0 && discountValue != null && discountValue < 0)) {
      throw new InconsistentDiscountException(
          "No se puede tener un porcentaje de descuento y un valor de descuento al mismo tiempo");
    }
  }

  public void validateDiscountConsistency(int discountPercentage, Integer discountValue, int maxDiscount,
      int minValue) {
    if (discountPercentage == 0 && (discountValue == null || discountValue == 0) && minValue == 0) {
      throw new InconsistentDiscountException("Debe especificar al menos un tipo de descuento");
    }

    if ((discountPercentage > 0 && discountValue != null && discountValue != 0)
        || (discountPercentage == 0 && discountValue != null && discountValue < 0)) {
      throw new InconsistentDiscountException(
          "No se puede tener un porcentaje de descuento y un valor de descuento al mismo tiempo");
    }

    if (maxDiscount > 0 && discountPercentage <= 0) {
      throw new InconsistentDiscountException(
          "Si el descuento máximo es mayor que 0, el porcentaje de descuento debe ser mayor que 0");
    }

    if (maxDiscount > 0 && minValue > 0) {
      throw new InconsistentDiscountException("Si el descuento máximo es mayor que 0, el valor mínimo debe ser 0");
    }

    if (maxDiscount == 0 && discountPercentage > 0) {
      throw new InconsistentDiscountException("Si el descuento máximo es 0, el porcentaje de descuento debe ser 0");
    }

    if (minValue > 0 && (discountValue == null || discountValue == 0)) {
      throw new InconsistentDiscountException(
          "Si el valor mínimo es mayor que 0, el valor de descuento debe ser mayor que 0");
    }

    if (minValue > 0 && discountPercentage > 0) {
      throw new InconsistentDiscountException(
          "Si el valor mínimo es mayor que 0, el porcentaje de descuento debe ser 0");
    }
  }
}
