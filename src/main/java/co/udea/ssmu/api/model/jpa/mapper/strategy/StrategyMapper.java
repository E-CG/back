package co.udea.ssmu.api.model.jpa.mapper.strategy;

import co.udea.ssmu.api.model.jpa.dto.StrategyDTO;
import co.udea.ssmu.api.model.jpa.mapper.EntityMapper;
import co.udea.ssmu.api.model.jpa.model.strategy.Strategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StrategyMapper extends EntityMapper<StrategyDTO, Strategy> {

    @Override
    //targe modelo, source dto
    @Mapping(target = "idStrategy", source = "idStrategy")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "discountPercentage", source = "discountPercentage")
    @Mapping(target = "isActive", source = "isActive")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "minValue", source = "minValue")
    @Mapping(target = "maxDiscount", source = "maxDiscount")
    Strategy toEntity(StrategyDTO dto);

    @Override
    //Source modelo, target dto
    @Mapping(target = "idStrategy", source = "idStrategy")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "discountPercentage", source = "discountPercentage")
    @Mapping(target = "isActive", source = "isActive")
    @Mapping(target = "city", source = "city")
    @Mapping(target = "startDate", source = "startDate")
    @Mapping(target = "endDate", source = "endDate")
    @Mapping(target = "minValue", source = "minValue")
    @Mapping(target = "maxDiscount", source = "maxDiscount")
    StrategyDTO toDto(Strategy entity);
}