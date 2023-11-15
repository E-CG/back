package co.udea.ssmu.api.services.coupon.facade;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import co.udea.ssmu.api.model.jpa.dto.CouponDTO;
import co.udea.ssmu.api.model.jpa.dto.StrategyDTO;
import co.udea.ssmu.api.model.jpa.mapper.ICouponMapper;
import co.udea.ssmu.api.model.jpa.model.Coupon;
import co.udea.ssmu.api.services.coupon.service.CouponService;
import co.udea.ssmu.api.utils.common.CouponCodeBuilder;
import co.udea.ssmu.api.utils.common.CouponStatusEnum;
import co.udea.ssmu.api.utils.common.StrategyUserTypeEnum;
import co.udea.ssmu.api.utils.exception.InconsistentDiscountException;
import co.udea.ssmu.api.utils.exception.InvalidCouponAmount;
import co.udea.ssmu.api.utils.exception.InvalidDiscountPercentage;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CouponFacade {

	@Autowired
	private CouponService couponService;

	@Autowired
	private CouponCodeBuilder couponBuilder;

	@Autowired
	private ICouponMapper couponMapper;

	public CouponDTO createCoupon(CouponDTO couponDTO) {
		validateCouponCreation(couponDTO);
		updateCouponStatus(couponDTO);
		Coupon savedCoupon = couponService.saveCoupon(couponMapper.toEntity(couponDTO));
		return couponMapper.toDto(savedCoupon);
	}

	private void validateCouponCreation(CouponDTO couponDTO) {
		StrategyDTO strategyDTO = couponDTO.getStrategy();

		couponDTO.setCode(couponBuilder.buildCodeCoupon(strategyDTO.getName()));

		validateCouponAmount(couponDTO.getAmountCreated());
		validateDiscount(strategyDTO.getDiscountPercentage(), strategyDTO.getDiscountValue());
		validateDiscountConsistency(strategyDTO.getDiscountPercentage(), strategyDTO.getDiscountValue());
	}

	private void validateCouponAmount(int amountCreated) {
		if (amountCreated < 1 || amountCreated > 100) {
			throw new InvalidCouponAmount("La cantidad de cupones debe estar entre 1 y 100");
		}
	}

	private void validateDiscount(int discountPercentage, Integer discountValue) {
		if (discountPercentage < 1 || discountPercentage > 100) {
			throw new InvalidDiscountPercentage("El porcentaje de descuento debe estar entre 0 y 100");
		}
	}

	private void validateDiscountConsistency(int discountPercentage, Integer discountValue) {
		if (discountPercentage == 0 && (discountValue == null || discountValue == 0)) {
			throw new InconsistentDiscountException("Debe especificar al menos un tipo de descuento");
		}

		if ((discountPercentage > 0 && discountValue != null && discountValue != 0)
				|| (discountPercentage == 0 && discountValue != null && discountValue < 0)) {
			throw new InconsistentDiscountException(
					"No se puede tener un porcentaje de descuento y un valor de descuento al mismo tiempo");
		}
	}

	private void updateCouponStatus(CouponDTO couponDTO) {
		StrategyDTO strategyDTO = couponDTO.getStrategy();
		LocalDateTime today = LocalDateTime.now();

		if (strategyDTO.getStartDate().isAfter(today)) {
			strategyDTO.setIsActive(false);
			couponDTO.setStatus(CouponStatusEnum.INACTIVO);
		} else if (strategyDTO.getEndDate().isBefore(today)) {
			strategyDTO.setIsActive(false);
			couponDTO.setStatus(CouponStatusEnum.CADUCADO);
		} else {
			strategyDTO.setIsActive(true);
			couponDTO.setStatus(CouponStatusEnum.ACTIVO);
		}
	}

	public Page<CouponDTO> findWithFilter(Pageable pageable) {
		return couponService.findWithFilter(pageable).map(couponMapper::toDto);
	}

	public CouponDTO findByCode(String code) {
		CouponDTO couponDTO = couponMapper.toDto(couponService.findById(code));
		return couponDTO;
	}

	public List<CouponDTO> findAll() {
		return couponMapper.toDto(couponService.findAll());
	}

	public CouponDTO editCoupon(CouponDTO updatedCoupon) {
		Coupon coupon = couponMapper.toEntity(updatedCoupon);
		return couponMapper.toDto(couponService.editCoupon(coupon));
	}

	public void updateCouponFields(CouponDTO existingCoupon, Map<String, Object> updates) {
		if (updates.containsKey("status")) {
			existingCoupon.setStatus((CouponStatusEnum) updates.get("status"));
		}
		if (updates.containsKey("strategy")) {
			@SuppressWarnings("unchecked")
			Map<String, Object> strategyUpdates = (Map<String, Object>) updates.get("strategy");
			updateStrategy(existingCoupon.getStrategy(), strategyUpdates);
		}
		editCoupon(existingCoupon);
	}

	private void updateStrategy(StrategyDTO existingStrategy, Map<String, Object> strategyUpdates) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Optional.ofNullable((String) strategyUpdates.get("description"))
				.ifPresent(existingStrategy::setDescription);
		Optional.ofNullable((String) strategyUpdates.get("startDate"))
				.map(date -> LocalDateTime.parse(date, formatter)).ifPresent(existingStrategy::setStartDate);
		Optional.ofNullable((String) strategyUpdates.get("endDate"))
				.map(date -> LocalDateTime.parse(date, formatter)).ifPresent(existingStrategy::setEndDate);
		Optional.ofNullable((StrategyUserTypeEnum) strategyUpdates.get("userType"))
				.ifPresent(existingStrategy::setUserType);
		Optional.ofNullable((String) strategyUpdates.get("name")).ifPresent(existingStrategy::setName);
		Optional.ofNullable((Integer) strategyUpdates.get("maxDiscount"))
				.ifPresent(existingStrategy::setMaxDiscount);
		Optional.ofNullable((Integer) strategyUpdates.get("discountPercentage"))
				.ifPresent(discountPercentage -> {
					validateDiscountPercentage(discountPercentage);
					existingStrategy.setDiscountPercentage(discountPercentage);
				});
	}

	private void validateDiscountPercentage(int discountPercentage) {
		if (discountPercentage < 1 || discountPercentage > 50) {
			throw new InvalidDiscountPercentage("El porcentaje de descuento debe estar entre 0 y 100");
		}
	}
}
