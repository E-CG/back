package co.udea.ssmu.api.model.jpa.mapper.strategy;

import co.udea.ssmu.api.model.jpa.dto.StrategyDTO;
import co.udea.ssmu.api.model.jpa.mapper.EntityMapper;
import co.udea.ssmu.api.model.jpa.model.strategy.Strategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StrategyMapper extends EntityMapper<StrategyDTO, Strategy> {
}