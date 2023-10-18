package co.udea.ssmu.api.model.jpa.mapper.coupon;

import org.mapstruct.Mapper;

import co.udea.ssmu.api.model.jpa.dto.CouponDTO;
import co.udea.ssmu.api.model.jpa.mapper.EntityMapper;
import co.udea.ssmu.api.model.jpa.model.coupon.Coupon;

@Mapper(componentModel = "spring")
public interface CouponMapper extends EntityMapper<CouponDTO, Coupon>{
}
