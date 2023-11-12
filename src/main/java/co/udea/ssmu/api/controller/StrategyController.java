package co.udea.ssmu.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import co.udea.ssmu.api.model.jpa.dto.StrategyDTO;
import co.udea.ssmu.api.services.strategy.facade.StrategyFacade;
import co.udea.ssmu.api.utils.common.*;
import co.udea.ssmu.api.utils.exception.DataDuplicatedException;

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
}
