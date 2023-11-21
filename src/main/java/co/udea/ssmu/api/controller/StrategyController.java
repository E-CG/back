package co.udea.ssmu.api.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import co.udea.ssmu.api.model.jpa.dto.StrategyDTO;
import co.udea.ssmu.api.services.strategy.facade.StrategyFacade;
import co.udea.ssmu.api.utils.common.*;
import co.udea.ssmu.api.utils.exception.DataDuplicatedException;
import co.udea.ssmu.api.utils.exception.DataNotFoundException;

@RestController
@CrossOrigin("*")
@RequestMapping("/discounts")
public class StrategyController {

  @Autowired
  private StrategyFacade strategyFacade;
  @Autowired
  private Messages messages;

  @Operation(summary = "Permite crear una estrategia")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Estrategia creada exitosamente"),
      @ApiResponse(code = 400, message = "Parámetros de entrada inválidos"),
      @ApiResponse(code = 409, message = "Conflicto: La estrategia ya existe")
  })
  @PostMapping("/create")
  public ResponseEntity<StandardResponse<StrategyDTO>> createStrategy(@Valid @RequestBody StrategyDTO strategyDTO) {
    try {
      StrategyDTO newStrategy = strategyFacade.createStrategy(strategyDTO);
      return ResponseEntity.ok(new StandardResponse<>(
          StandardResponse.StatusStandardResponse.OK,
          messages.get("strategy.save.successful"), newStrategy));
    } catch (DataDuplicatedException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(new StandardResponse<>(
          StandardResponse.StatusStandardResponse.ERROR,
          messages.get("strategy.save.error")));
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StandardResponse<>(
          StandardResponse.StatusStandardResponse.ERROR,
          messages.get("strategy.invalid.data")));
    }
  }

  @Operation(summary = "Permite obtener todas las estrategias")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Listado de estrategias"),
      @ApiResponse(code = 401, message = "No estás autorizado para ver el recurso"),
      @ApiResponse(code = 403, message = "Acceso prohibido"),
      @ApiResponse(code = 404, message = "Estrategia no encontrada ")
  })
  @GetMapping("/all")
  public ResponseEntity<StandardResponse<List<StrategyDTO>>> getAllStrategies() {
    try {
      List<StrategyDTO> strategies = strategyFacade.findAll();
      return ResponseEntity.ok(new StandardResponse<>(
          StandardResponse.StatusStandardResponse.OK,
          messages.get("strategy.get.successful"),
          strategies));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new StandardResponse<>(
          StandardResponse.StatusStandardResponse.ERROR,
          messages.get("strategy.get.strategies.error")));
    }
  }

  @Operation(summary = "Permite obtener una estrategia por su id")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Estrategia encontrada"),
      @ApiResponse(code = 400, message = "Parámetros de entrada inválidos"),
      @ApiResponse(code = 404, message = "Estrategia no encontrada")
  })
  @GetMapping("/{id}")
  public ResponseEntity<StandardResponse<StrategyDTO>> getStrategyById(@PathVariable Long id) {
    StrategyDTO strategyDTO = strategyFacade.findById(id);
    if (strategyDTO == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardResponse<>(
          StandardResponse.StatusStandardResponse.ERROR,
          messages.get("strategy.not.found")));
    }

    return ResponseEntity.ok(new StandardResponse<>(
        StandardResponse.StatusStandardResponse.OK,
        messages.get("strategy.get.successful"),
        strategyDTO));
  }

  @Operation(summary = "Permite obtener todas las estrategias")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Listado de estrategias"),
      @ApiResponse(code = 401, message = "No estás autorizado para ver el recurso"),
      @ApiResponse(code = 403, message = "Acceso prohibido"),
      @ApiResponse(code = 404, message = "Estrategia no encontrada ")
  })
  @GetMapping("/all-filter")
  public ResponseEntity<StandardResponse<Page<StrategyDTO>>> getStrategiesFiltered(
      @RequestParam(required = false) Integer limit) {
    Pageable pageable = PageRequest.ofSize(limit);
    try {
      Page<StrategyDTO> strategyPage = strategyFacade.findWithFilter(pageable);
      return ResponseEntity.ok(new StandardResponse<>(
          StandardResponse.StatusStandardResponse.OK,
          messages.get("strategy.get.successful"),
          strategyPage));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new StandardResponse<>(
          StandardResponse.StatusStandardResponse.ERROR,
          messages.get("strategy.get.strategies.error")));
    }
  }

  @Operation(summary = "Permite actualizar los datos de una estrategia")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Estrategia actualizada exitosamente"),
      @ApiResponse(code = 404, message = "Estrategia no encontrada"),
      @ApiResponse(code = 400, message = "Parámetros de entrada inválidos"),
      @ApiResponse(code = 500, message = "Error interno del servidor")
  })
  @PatchMapping("/edit/{id}")
  public ResponseEntity<StandardResponse<StrategyDTO>> editStrategy(@PathVariable Long id,
      @RequestBody Map<String, Object> updates) {
    StrategyDTO existingStrategy = strategyFacade.findById(id);
    if (existingStrategy == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardResponse<>(
          StandardResponse.StatusStandardResponse.ERROR,
          messages.get("strategy.not.found")));
    }

    try {
      strategyFacade.updateStrategyFields(existingStrategy, updates);
    } catch (DataNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardResponse<>(
          StandardResponse.StatusStandardResponse.ERROR,
          messages.get("strategy.update.error")));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new StandardResponse<>(
          StandardResponse.StatusStandardResponse.ERROR,
          messages.get("strategy.update.data.invalid")));
    }

    return ResponseEntity.ok(new StandardResponse<>(
        StandardResponse.StatusStandardResponse.OK,
        messages.get("strategy.update.successful"),
        existingStrategy));
  }

  @Operation(summary = "Permite eliminar una estrategia")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Estrategia eliminada exitosamente"),
      @ApiResponse(code = 404, message = "Estrategia no encontrada"),
      @ApiResponse(code = 500, message = "Error interno del servidor")
  })
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<StandardResponse<StrategyDTO>> deletePromo(@PathVariable Long id) {
    StrategyDTO existingStrategy = strategyFacade.findById(id);
    try {
      strategyFacade.deletePromoById(id);

      return ResponseEntity.ok(new StandardResponse<>(
          StandardResponse.StatusStandardResponse.OK,
          messages.get("strategy.delete.successful"),
          existingStrategy));
    } catch (DataNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardResponse<>(
          StandardResponse.StatusStandardResponse.ERROR,
          messages.get("strategy.not.found")));
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StandardResponse<>(
          StandardResponse.StatusStandardResponse.ERROR,
          messages.get("strategy.delete.error")));
    }
  }
}