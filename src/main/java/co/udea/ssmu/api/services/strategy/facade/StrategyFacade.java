package co.udea.ssmu.api.services.strategy.facade;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.udea.ssmu.api.model.jpa.dto.StrategyDTO;
import co.udea.ssmu.api.model.jpa.mapper.IStrategyMapper;
import co.udea.ssmu.api.model.jpa.model.Strategy;
import co.udea.ssmu.api.services.strategy.service.StrategyService;
import co.udea.ssmu.api.utils.common.DiscountValidator;
import co.udea.ssmu.api.utils.common.StrategyUserTypeEnum;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class StrategyFacade {

    @Autowired
    private StrategyService strategyService;

    @Autowired
    private DiscountValidator discountValidator;

    @Autowired
    private IStrategyMapper strategyMapper;

    public StrategyDTO createStrategy(StrategyDTO strategyDTO) {
        validateStrategy(strategyDTO);
        updateStrategyStatus(strategyDTO);
        return strategyMapper.toDto(strategyService.saveStrategy(strategyMapper.toEntity(strategyDTO)));
    }

    private void updateStrategyStatus(StrategyDTO strategyDTO) {
        if (strategyDTO.getStartDate().isAfter(LocalDateTime.now())) {
            strategyDTO.setIsActive(false);
        } else if (strategyDTO.getEndDate().isBefore(LocalDateTime.now())) {
            strategyDTO.setIsActive(false);
        } else {
            strategyDTO.setIsActive(true);
        }
    }

    private void validateStrategy(StrategyDTO strategyDTO) {
        discountValidator.validateDiscount(strategyDTO.getDiscountPercentage(), strategyDTO.getDiscountValue());
        validateDateRange(strategyDTO.getStartDate(), strategyDTO.getEndDate());
        validateUserType(strategyDTO.getUserType());
    }

    private void validateDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        LocalDateTime today = LocalDateTime.now();
        if (startDate.isAfter(today) && endDate.isBefore(today)) {
            throw new IllegalArgumentException("Las fechas de inicio y fin no son válidas");
        }
    }

    private void validateUserType(StrategyUserTypeEnum userType) {
        if (userType == null) {
            throw new IllegalArgumentException("El tipo de usuario no puede ser nulo");
        }
    }

    public StrategyDTO findById(Long id) {
        return strategyMapper.toDto(strategyService.findById(id));
    }

    public List<StrategyDTO> findAll() {
        return strategyMapper.toDto(strategyService.findAll());
    }

    public Page<StrategyDTO> findWithFilter(Pageable pageable) {
        return strategyService.findWithFilter(pageable).map(strategyMapper::toDto);
    }

    public StrategyDTO editStrategy(StrategyDTO updatedStrategy) {
        Strategy strategy = strategyMapper.toEntity(updatedStrategy);
        return strategyMapper.toDto(strategyService.editStrategy(strategy));
    }

    public void updateStrategyFields(StrategyDTO existingStrategy, Map<String, Object> updates) {
        updates.forEach((key, value) -> {
            switch (key) {
                case "description":
                    existingStrategy.setDescription((String) value);
                    break;
                case "startDate":
                    existingStrategy.setStartDate(parseDateTime((String) value));
                    break;
                case "endDate":
                    existingStrategy.setEndDate(parseDateTime((String) value));
                    break;
                case "name":
                    existingStrategy.setName((String) value);
                    break;
                case "city":
                    existingStrategy.setCity((String) value);
                    break;
                case "discountValue":
                    existingStrategy.setDiscountValue((Integer) value);
                    break;
                case "minValue":
                    existingStrategy.setMinValue((Integer) value);
                    break;
                case "maxDiscount":
                    existingStrategy.setMaxDiscount((Integer) value);
                    break;
                case "discountPercentage":
                    existingStrategy.setDiscountPercentage((Integer) value);
                    break;
                case "userType":
                    existingStrategy.setUserType(mapToUserType((Integer) value));
                    break;
            }
        });
        discountValidator.validateDiscount(existingStrategy.getDiscountPercentage(),
                existingStrategy.getDiscountValue());

        discountValidator.validateDiscountConsistency(
                existingStrategy.getDiscountPercentage(),
                existingStrategy.getDiscountValue(),
                existingStrategy.getMaxDiscount(),
                existingStrategy.getMinValue());

        editStrategy(existingStrategy);
    }

    private LocalDateTime parseDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateTime, formatter);
    }

    private StrategyUserTypeEnum mapToUserType(Integer userType) {
        return userType == 0 ? StrategyUserTypeEnum.FRECUENTE : StrategyUserTypeEnum.OCASIONAL;
    }

    public void deletePromoById(Long id) {
        StrategyDTO strategyDTO = strategyMapper.toDto(strategyService.findById(id));
        if (strategyDTO != null) {
            strategyService.deleteStrategyById(id);
        } else {
            throw new IllegalArgumentException("La estrategia no existe");
        }
    }
}
