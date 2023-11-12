package co.udea.ssmu.api.services.strategy.facade;

import java.time.LocalDateTime;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.udea.ssmu.api.model.jpa.dto.StrategyDTO;
import co.udea.ssmu.api.model.jpa.mapper.IStrategyMapper;
import co.udea.ssmu.api.model.jpa.model.Strategy;
import co.udea.ssmu.api.services.strategy.service.StrategyService;
import co.udea.ssmu.api.utils.common.StrategyUserTypeEnum;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class StrategyFacade {

    @Autowired
    private StrategyService strategyService;
    @Autowired
    private IStrategyMapper strategyMapper;

    public StrategyDTO createStrategy(StrategyDTO strategyDTO) {
        LocalDateTime today = LocalDateTime.now();
    
        // Verificar y establecer isActive
        if (strategyDTO.getStartDate().isAfter(today) || strategyDTO.getEndDate().isBefore(today)) {
            strategyDTO.setIsActive(false);
        } else {
            strategyDTO.setIsActive(true);
        }

        int userTypeEnum = strategyDTO.getUserType().getCode();
        if(userTypeEnum == 0){
            strategyDTO.setUserType(StrategyUserTypeEnum.FRECUENTE);
        }else if(userTypeEnum == 1){
            strategyDTO.setUserType(StrategyUserTypeEnum.OCACIONAL);
        }
        
        Strategy strategy = strategyMapper.toEntity(strategyDTO);
        return strategyMapper.toDto(strategyService.saveStrategy(strategy));
    }

    public StrategyDTO findById(Long id){
        StrategyDTO strategyDTO = strategyMapper.toDto(strategyService.findById(id));
        return strategyDTO;
    }

    public List<StrategyDTO> findAll(){
        return strategyMapper.toDto(strategyService.findAll());
    }


    public Page<StrategyDTO> findWithFilter(Pageable pageable) {
        return strategyService.findWithFilter(pageable).map(strategyMapper::toDto);
    }

}