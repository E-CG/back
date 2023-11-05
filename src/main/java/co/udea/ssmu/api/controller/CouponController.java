package co.udea.ssmu.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import co.udea.ssmu.api.model.jpa.dto.CouponDTO;
import co.udea.ssmu.api.services.coupon.facade.CouponFacade;
import co.udea.ssmu.api.utils.common.*;
import co.udea.ssmu.api.utils.exception.DataDuplicatedException;
import co.udea.ssmu.api.utils.exception.DataNotFoundException;

@RestController
@RequestMapping("/coupons")
public class CouponController {
    @Autowired
    private CouponFacade couponFacade;
    @Autowired
    private Messages messages;

    @Operation(summary = "Permite crear un cupón")
    @PostMapping("/create")
    public ResponseEntity<StandardResponse<String>> createCoupon(@Valid @RequestBody CouponDTO couponDTO) {
        try {
            couponFacade.createCoupon(couponDTO);
            return ResponseEntity.ok(new StandardResponse<>(
                    StandardResponse.StatusStandardResponse.OK,
                    messages.get("coupon.create.successful")));
        } catch (DataDuplicatedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new StandardResponse<>(
                            StandardResponse.StatusStandardResponse.ERROR,
                            messages.get("coupon.save.duplicate.code")));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new StandardResponse<>(
                            StandardResponse.StatusStandardResponse.ERROR,
                            messages.get("coupon.save.data.invalid")));
        }
    }

    @Operation(summary = "Permite obtener todos los cupones")
    @GetMapping("/all")
    public ResponseEntity<StandardResponse<List<CouponDTO>>> getAllCoupons(
            @RequestParam(required = false) Integer limit,
            @RequestParam(required = false) Integer offset) {

        List<CouponDTO> coupons = couponFacade.findAllCoupons(limit, offset);
        return ResponseEntity.ok(new StandardResponse<>(
                StandardResponse.StatusStandardResponse.OK,
                messages.get("coupon.get.successful"),
                coupons));
    }

    @Operation(summary = "Permite obtener un cupón por su código")
    @GetMapping("/{code}")
    public ResponseEntity<StandardResponse<CouponDTO>> getCouponByCode(@PathVariable String code) {
        CouponDTO coupon = couponFacade.findByCode(code);
        if (coupon == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardResponse<>(
                    StandardResponse.StatusStandardResponse.ERROR,
                    messages.get("coupon.not.found")));
        }

        return ResponseEntity.ok(new StandardResponse<>(
                StandardResponse.StatusStandardResponse.OK,
                messages.get("coupon.get.successful"),
                coupon));
    }

    @Operation(summary = "Permite actualizar los datos de un cupón")
    @PatchMapping("/edit/{code}")
    public ResponseEntity<StandardResponse<String>> editCoupon(@PathVariable String code,
            @RequestBody Map<String, Object> updates) {
        CouponDTO existingCoupon = couponFacade.findByCode(code);
        if (existingCoupon == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StandardResponse<>(
                    StandardResponse.StatusStandardResponse.ERROR,
                    messages.get("coupon.not.found")));
        }

        try {
            couponFacade.updateCouponFields(existingCoupon, updates);
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardResponse<>(
                    StandardResponse.StatusStandardResponse.ERROR,
                    messages.get("coupon.update.data.invalid")));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new StandardResponse<>(
                    StandardResponse.StatusStandardResponse.ERROR,
                    messages.get("coupon.update.error")));
        }

        return ResponseEntity.ok(new StandardResponse<>(
                StandardResponse.StatusStandardResponse.OK,
                messages.get("coupon.update.successful")));
    }
}