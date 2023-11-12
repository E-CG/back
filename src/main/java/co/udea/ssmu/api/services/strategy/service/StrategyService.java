package co.udea.ssmu.api.services.strategy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.udea.ssmu.api.model.jpa.model.Strategy;
import co.udea.ssmu.api.model.jpa.repository.strategy.IStrategyRepository;

@Service
@Transactional
public class StrategyService {
    @Autowired
    private IStrategyRepository strategyRepository;

    public Strategy saveStrategy(Strategy strategy){
        return strategyRepository.save(strategy);
    }

    public List<Strategy> findAll(){
        return strategyRepository.findAll();
    }

    public Page<Strategy> findWithFilter(Pageable pageable) {
        return strategyRepository.findAll(pageable);
    }

    public Strategy editStrategy(Strategy strategy){
        return strategyRepository.save(strategy);
    }

    public Strategy findById(Long id){
        return strategyRepository.findById(id).orElse(null);
    }
}
