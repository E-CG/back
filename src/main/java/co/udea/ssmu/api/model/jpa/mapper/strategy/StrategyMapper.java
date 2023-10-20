package co.udea.ssmu.api.model.jpa.mapper.strategy;

import org.mapstruct.Mapper;
import co.udea.ssmu.api.model.jpa.dto.StrategyDTO;
import co.udea.ssmu.api.model.jpa.mapper.EntityMapper;
import co.udea.ssmu.api.model.jpa.model.strategy.Strategy;

@Mapper(componentModel = "spring")
public interface StrategyMapper extends EntityMapper<StrategyDTO, Strategy> {
}
