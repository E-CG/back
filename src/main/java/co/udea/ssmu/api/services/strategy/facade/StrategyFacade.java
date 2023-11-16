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
import co.udea.ssmu.api.utils.exception.InconsistentDiscountException;
import co.udea.ssmu.api.utils.exception.InvalidDiscountPercentage;
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
        validateDiscount(strategyDTO.getDiscountPercentage(), strategyDTO.getDiscountValue());
        validateDateRange(strategyDTO.getStartDate(), strategyDTO.getEndDate());
        validateUserType(strategyDTO.getUserType());
    }

    private void validateDiscount(int discountPercentage, Integer discountValue) {
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new InvalidDiscountPercentage("El porcentaje de descuento debe estar entre 0 y 100");
        }

        if ((discountPercentage > 0 && discountValue != null && discountValue != 0)
                || (discountPercentage == 0 && discountValue != null && discountValue < 0)) {
            throw new InconsistentDiscountException(
                    "No se puede tener un porcentaje de descuento y un valor de descuento al mismo tiempo");
        }
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
                    discountValidator.validateDiscount((int) value, existingStrategy.getDiscountPercentage());
                    existingStrategy.setDiscountValue((int) value);
                    break;
                case "minValue":
                    existingStrategy.setMinValue((int) value);
                    break;
                case "maxDiscount":
                    existingStrategy.setMaxDiscount((int) value);
                    break;
                case "discountPercentage":
                    discountValidator.validateDiscount((int) value, existingStrategy.getDiscountValue());
                    existingStrategy.setDiscountPercentage((int) value);
                    break;
                case "userType":
                    existingStrategy.setUserType(mapToUserType((int) value));
                    break;
            }
        });
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

    private StrategyUserTypeEnum mapToUserType(int userType) {
        return userType == 0 ? StrategyUserTypeEnum.FRECUENTE : StrategyUserTypeEnum.OCASIONAL;
    }
}
