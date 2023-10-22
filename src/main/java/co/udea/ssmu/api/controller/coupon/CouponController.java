package co.udea.ssmu.api.controller.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/coupons")
public class CouponController {
    @Autowired
    private CouponFacade couponFacade;
    @Autowired
    private Messages messages;

    // Crear cupón
    @PostMapping(path = "/create")
    @Operation(summary = "Permite crear un cupón")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = CouponDTO.class), mediaType = MediaType.APPLICATION_JSON_VALUE)
            }, description = "La petición se ha procesado correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno al procesar la respuesta") })
    public ResponseEntity<StandardResponse<CouponDTO>> crearCupon(@Valid @RequestBody CouponDTO newCoupon) {
        return ResponseEntity.ok(new StandardResponse<>(StandardResponse.StatusStandardResponse.OK,
                messages.get("coupon.save.successful"), couponFacade.saveCoupon(newCoupon)));
    }

    // Editar cupón
    @PutMapping(path = "/update")
    @Operation(summary = "Permite actualizar los datos de un cupón")
    public ResponseEntity<StandardResponse<CouponDTO>> update(@Valid @RequestBody CouponDTO coupon) {
        return ResponseEntity.ok(new StandardResponse<>(StandardResponse.StatusStandardResponse.OK,
                messages.get("coupon.update.successful"),
                couponFacade.update(coupon)));
    }
}
