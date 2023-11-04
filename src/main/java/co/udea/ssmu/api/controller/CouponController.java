package co.udea.ssmu.api.controller;

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

    @Operation(summary = "Permite actualizar los datos de un cupón")
    @PutMapping("/edit")
    public ResponseEntity<StandardResponse<CouponDTO>> updateCoupon(@Valid @RequestBody CouponDTO couponDTO) {
        try {
            CouponDTO updatedCoupon = couponFacade.update(couponDTO);
            return ResponseEntity.ok(new StandardResponse<>(
                    StandardResponse.StatusStandardResponse.OK,
                    messages.get("coupon.update.successful"),
                    updatedCoupon));
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new StandardResponse<>(
                            StandardResponse.StatusStandardResponse.ERROR,
                            messages.get("coupon.update.does.not.exist")));
        }
    }
}