package co.udea.ssmu.api.model.jpa.mapper;

import co.udea.ssmu.api.model.jpa.dto.StrategyDTO;
import co.udea.ssmu.api.model.jpa.model.Strategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IStrategyMapper extends IEntityMapper<StrategyDTO, Strategy> {
}