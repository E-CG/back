package co.udea.ssmu.api.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
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
    @PostMapping("/create")
    public ResponseEntity<StandardResponse<StrategyDTO>> createStrategy(@Valid @RequestBody StrategyDTO strategyDTO) {
        try {
            StrategyDTO newStrategy = strategyFacade.createStrategy(strategyDTO);
            return ResponseEntity.ok(new StandardResponse<>(
                    StandardResponse.StatusStandardResponse.OK,
                    messages.get("strategy.save.successful"), newStrategy));
        } catch (DataDuplicatedException e) {
            String errorMessage = "No se pudo crear la estrategia. " + e.getMessage();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new StandardResponse<>(
                    StandardResponse.StatusStandardResponse.ERROR,
                    errorMessage));
        } catch (IllegalArgumentException e) {
            String errorMessage = "No se pudo crear la estrategia. Datos inválidos. " + e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StandardResponse<>(
                    StandardResponse.StatusStandardResponse.ERROR,
                    errorMessage));
        }
    }

    @Operation(summary = "Permite obtener todas las estrategias")
    @GetMapping("/all")
    public ResponseEntity<StandardResponse<List<StrategyDTO>>> getAllStrategies() {
        try {
            List<StrategyDTO> strategies = strategyFacade.findAll();
            return ResponseEntity.ok(new StandardResponse<>(
                    StandardResponse.StatusStandardResponse.OK,
                    messages.get("strategy.get.successful"),
                    strategies));
        } catch (Exception e) {
            String errorMessage = "Error al obtener las estrategias. " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new StandardResponse<>(
                    StandardResponse.StatusStandardResponse.ERROR,
                    errorMessage));
        }
    }

    @Operation(summary = "Permite obtener una estrategia por su id")
    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse<StrategyDTO>> getStrategyById(@PathVariable Long id) {
        StrategyDTO strategyDTO = strategyFacade.findById(id);
        if (strategyDTO == null) {
            String errorMessage = "No se encontró la estrategia con el ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardResponse<>(
                    StandardResponse.StatusStandardResponse.ERROR,
                    errorMessage));
        }

        return ResponseEntity.ok(new StandardResponse<>(
                StandardResponse.StatusStandardResponse.OK,
                messages.get("strategy.get.successful"),
                strategyDTO));
    }

    @Operation(summary = "Permite obtener todas las estrategias")
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
            String errorMessage = "Error al obtener las estrategias filtradas. " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new StandardResponse<>(
                    StandardResponse.StatusStandardResponse.ERROR,
                    errorMessage));
        }
    }

    @Operation(summary = "Permite actualizar los datos de una estrategia")
    @PatchMapping("/edit/{id}")
    public ResponseEntity<StandardResponse<Long>> editStrategy(@PathVariable Long id,
            @RequestBody Map<String, Object> updates) {
        StrategyDTO existingStrategy = strategyFacade.findById(id);
        if (existingStrategy == null) {
            String errorMessage = "No se encontró la estrategia con el ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardResponse<>(
                    StandardResponse.StatusStandardResponse.ERROR,
                    errorMessage));
        }

        try {
            strategyFacade.updateStrategyFields(existingStrategy, updates);
        } catch (DataNotFoundException e) {
            String errorMessage = "No se pudo actualizar la estrategia. " + e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StandardResponse<>(
                    StandardResponse.StatusStandardResponse.ERROR,
                    errorMessage));
        } catch (Exception e) {
            String errorMessage = "Error al actualizar la estrategia. " + e.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new StandardResponse<>(
                    StandardResponse.StatusStandardResponse.ERROR,
                    errorMessage));
        }

        return ResponseEntity.ok(new StandardResponse<>(
                StandardResponse.StatusStandardResponse.OK,
                messages.get("strategy.update.successful")));
    }
}