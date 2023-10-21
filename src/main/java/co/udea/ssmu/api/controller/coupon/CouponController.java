package co.udea.ssmu.api.controller.coupon;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.udea.ssmu.api.model.jpa.dto.CouponDTO;
import co.udea.ssmu.api.services.coupon.facade.CouponFacade;
import co.udea.ssmu.api.utils.common.Messages;
import co.udea.ssmu.api.utils.common.StandardResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/coupons")
public class CouponController {
    private final CouponFacade couponFacade;
    private final Messages messages;

    public CouponController(CouponFacade couponFacade, Messages messages) {
        this.couponFacade = couponFacade;
        this.messages = messages;
    }

    // Crear cupón
    @PostMapping("/create")
    @Operation(summary = "Permite crear un cupón")
    public ResponseEntity<StandardResponse<CouponDTO>> crearCupon(@Valid @RequestBody CouponDTO newCoupon) {
        return ResponseEntity.ok(new StandardResponse<>(StandardResponse.StatusStandardResponse.OK,
                messages.get("Solicitud éxitosa"), couponFacade.saveCoupon(newCoupon)));
    }
    
    // Editar cupón
    @PutMapping("/update")
    @Operation(summary = "Permite actualizar los datos de un cupón")
    public ResponseEntity<StandardResponse<CouponDTO>> update(@Valid @RequestBody CouponDTO coupon) {
        return ResponseEntity.ok(new StandardResponse<>(StandardResponse.StatusStandardResponse.OK,
                messages.get("Cupón actualizado correctamente"),
                couponFacade.update(coupon)));
    }
}
