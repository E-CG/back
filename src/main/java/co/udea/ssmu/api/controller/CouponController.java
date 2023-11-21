package co.udea.ssmu.api.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.annotations.*;
import jakarta.validation.Valid;
import co.udea.ssmu.api.model.jpa.dto.CouponDTO;
import co.udea.ssmu.api.services.coupon.facade.CouponFacade;
import co.udea.ssmu.api.utils.common.*;
import co.udea.ssmu.api.utils.exception.DataDuplicatedException;
import co.udea.ssmu.api.utils.exception.DataNotFoundException;

@RestController
@CrossOrigin("*")
@RequestMapping("/coupons")
public class CouponController {
  @Autowired
  private CouponFacade couponFacade;
  @Autowired
  private Messages messages;

  @Operation(summary = "Permite crear un cupón")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Cupón creada exitosamente"),
      @ApiResponse(code = 400, message = "Parámetros de entrada inválidos"),
      @ApiResponse(code = 409, message = "Conflicto: El cupón ya existe")
  })
  @PostMapping("/create")
  public ResponseEntity<StandardResponse<CouponDTO>> createCoupon(@Valid @RequestBody CouponDTO couponDTO) {
    try {
      CouponDTO newCoupon = couponFacade.createCoupon(couponDTO);
      return ResponseEntity.status(HttpStatus.ACCEPTED).body(new StandardResponse<>(
          StandardResponse.StatusStandardResponse.OK,
          messages.get("coupon.save.successful"),
          newCoupon));
    } catch (DataDuplicatedException e) {
      String errorMessage = "No se pudo crear el cupón. " + e.getMessage();
      return ResponseEntity.status(HttpStatus.CONFLICT).body(new StandardResponse<>(
          StandardResponse.StatusStandardResponse.ERROR,
          errorMessage));
    }
  }

  @Operation(summary = "Permite obtener todos los cupones")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Listado de cupones"),
      @ApiResponse(code = 401, message = "No estás autorizado para ver el recurso"),
      @ApiResponse(code = 403, message = "Acceso prohibido"),
      @ApiResponse(code = 404, message = "Cupón no encontrado ")
  })
  @GetMapping("/all")
  public ResponseEntity<StandardResponse<List<CouponDTO>>> getAllCoupons() {
    try {
      List<CouponDTO> coupons = couponFacade.findAll();
      return ResponseEntity.ok(new StandardResponse<>(
          StandardResponse.StatusStandardResponse.OK,
          messages.get("coupon.get.successful"),
          coupons));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new StandardResponse<>(
          StandardResponse.StatusStandardResponse.ERROR,
          messages.get("coupon.get.error")));
    }
  }

  @Operation(summary = "Permite obtener un cupón por su código")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Cupón encontrado"),
      @ApiResponse(code = 400, message = "Parámetros de entrada inválidos"),
      @ApiResponse(code = 404, message = "Cupón no encontrado")
  })
  @GetMapping("/{code}")
  public ResponseEntity<StandardResponse<CouponDTO>> getCouponByCode(@PathVariable String code) {
    CouponDTO couponDTO = couponFacade.findByCode(code);
    if (couponDTO == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardResponse<>(
          StandardResponse.StatusStandardResponse.ERROR,
          messages.get("coupon.not.found")));
    }

    return ResponseEntity.ok(new StandardResponse<>(
        StandardResponse.StatusStandardResponse.OK,
        messages.get("coupon.get.successful"),
        couponDTO));
  }

  @Operation(summary = "Permite obtener todos los cupones")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Listado de cupones"),
      @ApiResponse(code = 401, message = "No estás autorizado para ver el recurso"),
      @ApiResponse(code = 403, message = "Acceso prohibido"),
      @ApiResponse(code = 404, message = "Cupón no encontrado ")
  })
  @GetMapping("/all-filter")
  public ResponseEntity<StandardResponse<Page<CouponDTO>>> getCouponsFiltered(
      @RequestParam(required = false) Integer limit) {
    Pageable pageable = PageRequest.ofSize(limit);
    return ResponseEntity.ok(new StandardResponse<>(
        StandardResponse.StatusStandardResponse.OK,
        messages.get("coupon.get.successful"),
        couponFacade.findWithFilter(pageable)));
  }

  @Operation(summary = "Permite actualizar los datos de un cupón")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Cupón actualizado exitosamente"),
      @ApiResponse(code = 404, message = "Cupón no encontrado"),
      @ApiResponse(code = 400, message = "Parámetros de entrada inválidos"),
      @ApiResponse(code = 500, message = "Error interno del servidor")
  })
  @PatchMapping("/edit/{code}")
  public ResponseEntity<StandardResponse<CouponDTO>> editCoupon(@PathVariable String code,
      @RequestBody Map<String, Object> updates) {
    CouponDTO existingCoupon = couponFacade.findByCode(code);
    if (existingCoupon == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardResponse<>(
          StandardResponse.StatusStandardResponse.ERROR,
          messages.get("coupon.not.found"), null));
    }

    try {
      couponFacade.updateCouponFields(existingCoupon, updates);
    } catch (DataNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardResponse<>(
          StandardResponse.StatusStandardResponse.ERROR,
          messages.get("coupon.not.found")));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new StandardResponse<>(
          StandardResponse.StatusStandardResponse.ERROR,
          messages.get("coupon.update.error")));
    }

    return ResponseEntity.ok(new StandardResponse<>(
        StandardResponse.StatusStandardResponse.OK,
        messages.get("coupon.update.successful"),
        existingCoupon));
  }

  @Operation(summary = "Permite eliminar un cupón por su código")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Cupón eliminado exitosamente"),
      @ApiResponse(code = 404, message = "Cupón no encontrado"),
      @ApiResponse(code = 500, message = "Error interno del servidor")
  })
  @DeleteMapping("/delete/{code}")
  public ResponseEntity<StandardResponse<CouponDTO>> deleteCouponById(@PathVariable String code) {
    // Busca el cupón por su codigo
    CouponDTO existingCoupon = couponFacade.findByCode(code);
    try {
      // Llama al método en el facade para eliminar el cupón por su ID
      couponFacade.deleteCouponById(code);

      return ResponseEntity.ok(new StandardResponse<>(
          StandardResponse.StatusStandardResponse.OK,
          messages.get("coupon.delete.successful"),
          existingCoupon));
    } catch (DataNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardResponse<>(
          StandardResponse.StatusStandardResponse.ERROR,
          messages.get("coupon.not.found")));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new StandardResponse<>(
          StandardResponse.StatusStandardResponse.ERROR,
          messages.get("coupon.delete.error")));
    }
  }
}