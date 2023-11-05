package co.udea.ssmu.api.model.jpa.mapper;

import co.udea.ssmu.api.model.jpa.dto.CouponDTO;
import co.udea.ssmu.api.model.jpa.model.Coupon;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICouponMapper extends IEntityMapper<CouponDTO, Coupon> {
}