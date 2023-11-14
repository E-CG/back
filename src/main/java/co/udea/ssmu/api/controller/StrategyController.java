package co.udea.ssmu.api.controller;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
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
    public ResponseEntity<StandardResponse<String>> createStrategy(@Valid @RequestBody StrategyDTO strategyDTO) {
        StrategyDTO newStrategy = strategyFacade.createStrategy(strategyDTO);
        try {
            return ResponseEntity.ok(new StandardResponse<>(
                    StandardResponse.StatusStandardResponse.OK,
                    messages.get("strategy.save.successful"), newStrategy.toString()));
        } catch (DataDuplicatedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new StandardResponse<>(
                    messages.get("strategy.save.duplicate.code"),
                    StandardResponse.StatusStandardResponse.ERROR));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StandardResponse<>(
                    messages.get("strategy.save.data.invalid"),
                    StandardResponse.StatusStandardResponse.ERROR));
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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new StandardResponse<>(
                    StandardResponse.StatusStandardResponse.ERROR,
                    messages.get("strategy.get.error")));
        }
    }

    @Operation(summary = "Permite obtener una estrategia por su id")
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
    @GetMapping("/all-filter")
    public ResponseEntity<StandardResponse<Page<StrategyDTO>>> getStrategiesFiltered(
            @RequestParam(required = false) Integer limit) {
        Pageable pageable = PageRequest.ofSize(limit);
        return ResponseEntity.ok(new StandardResponse<>(
                StandardResponse.StatusStandardResponse.OK,
                messages.get("strategy.get.successful"),
                strategyFacade.findWithFilter(pageable)));
    }

    @Operation(summary = "Permite actualizar los datos de una estrategia")
    @PatchMapping("/edit/{id}")
    public ResponseEntity<StandardResponse<Long>> editStrategy(@PathVariable Long id,
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
                    messages.get("strategy.update.data.invalid")));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new StandardResponse<>(
                    StandardResponse.StatusStandardResponse.ERROR,
                    messages.get("strategy.update.error")));
        }

        return ResponseEntity.ok(new StandardResponse<>(
                StandardResponse.StatusStandardResponse.OK,
                messages.get("strategy.update.successful")));
    }
}
