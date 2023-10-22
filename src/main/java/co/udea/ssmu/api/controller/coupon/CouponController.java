package co.udea.ssmu.api.controller.coupon;

import org.springframework.http.ResponseEntity;
import co.udea.ssmu.api.model.jpa.dto.CouponDTO;
import co.udea.ssmu.api.services.coupon.facade.CouponFacade;
import co.udea.ssmu.api.utils.common.Messages;
import co.udea.ssmu.api.utils.common.StandardResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Coupon", description = "Gestiona los cupones")
@RestController
@RequestMapping(path = "/coupons")
public class CouponController {
    private final CouponFacade couponFacade;
    private final Messages messages;

    public CouponController(CouponFacade couponFacade, Messages messages) {
        this.couponFacade = couponFacade;
        this.messages = messages;
    }

    // Crear cupón. ES LA FECHA
    @PostMapping(path = "/create")
    @Operation(summary = "Permite crear un cupón")
    public ResponseEntity<StandardResponse<CouponDTO>> save(@Valid @RequestBody CouponDTO couponDTO) {
        return ResponseEntity.ok(new StandardResponse<>(
                StandardResponse.StatusStandardResponse.OK,
                messages.get("coupon.save.successful"),
                couponFacade.save(couponDTO)));
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