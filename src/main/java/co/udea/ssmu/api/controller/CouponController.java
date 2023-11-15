package co.udea.ssmu.api.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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
@CrossOrigin("*")
@RequestMapping("/coupons")
public class CouponController {
  @Autowired
  private CouponFacade couponFacade;
  @Autowired
  private Messages messages;

  @Operation(summary = "Permite crear un cupón")
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
  @PatchMapping("/edit/{code}")
  public ResponseEntity<StandardResponse<CouponDTO>> editCoupon(@PathVariable String code,
      @RequestBody Map<String, Object> updates) {
    CouponDTO existingCoupon = couponFacade.findByCode(code);
    if (existingCoupon == null) {
      String errorMessage = "No se encontró el cupón con el código: " + code;
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardResponse<>(
          StandardResponse.StatusStandardResponse.ERROR,
          errorMessage, null));
    }

    try {
      couponFacade.updateCouponFields(existingCoupon, updates);
    } catch (DataNotFoundException e) {
      String errorMessage = "No se pudo actualizar el cupón. " + e.getMessage();
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardResponse<>(
          StandardResponse.StatusStandardResponse.ERROR,
          errorMessage));
    } catch (Exception e) {
      String errorMessage = "Error al actualizar el cupón. Contacte al administrador.";
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new StandardResponse<>(
          StandardResponse.StatusStandardResponse.ERROR,
          errorMessage));
    }

    return ResponseEntity.ok(new StandardResponse<>(
        StandardResponse.StatusStandardResponse.OK,
        messages.get("coupon.update.successful"), 
        existingCoupon));
  }
}