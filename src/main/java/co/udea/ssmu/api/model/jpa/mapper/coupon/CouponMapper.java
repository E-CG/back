package co.udea.ssmu.api.model.jpa.mapper.coupon;

import co.udea.ssmu.api.model.jpa.dto.CouponDTO;
import co.udea.ssmu.api.model.jpa.mapper.EntityMapper;
import co.udea.ssmu.api.model.jpa.mapper.strategy.StrategyMapper;
import co.udea.ssmu.api.model.jpa.model.coupon.Coupon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = StrategyMapper.class)
public interface CouponMapper extends EntityMapper<CouponDTO, Coupon> {
    @Override
    //Source modelo, target dto
    @Mapping(target = "code", source  = "code")
    @Mapping(target = "amount", source  = "amount")
    @Mapping(target = "status", source  = "status")
    Coupon toEntity(CouponDTO dto);

    @Override
    //Source dto, target modelo
    @Mapping(target = "code", source = "code")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "status", source = "status")
    CouponDTO toDto(Coupon entity);
}