package co.udea.ssmu.api.controller.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.udea.ssmu.api.model.jpa.dto.CouponDTO;
import co.udea.ssmu.api.services.coupon.facade.CouponFacade;
import co.udea.ssmu.api.utils.common.Messages;
import co.udea.ssmu.api.utils.common.StandardResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/coupons")
public class CouponController {
    @Autowired
    private CouponFacade couponFacade;
    @Autowired
    private Messages messages;

    @PostMapping(path = "/create")
    @Operation(summary = "Permite crear un cupón")
    public ResponseEntity<String> createCoupon(@RequestBody CouponDTO couponDTO) {
        try {
            couponFacade.createCoupon(couponDTO);
            return ResponseEntity.ok(messages.get("coupon.create.successful"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Editar cupón
    @PutMapping(path = "/update")
    @Operation(summary = "Permite actualizar los datos de un cupón")
    public ResponseEntity<StandardResponse<CouponDTO>> update(@Valid @RequestBody CouponDTO coupon) {
        return ResponseEntity.ok(new StandardResponse<>(
                StandardResponse.StatusStandardResponse.OK,
                messages.get("coupon.update.successful"),
                couponFacade.update(coupon)));
    }
}