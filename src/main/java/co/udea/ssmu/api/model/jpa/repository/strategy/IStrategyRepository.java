package co.udea.ssmu.api.model.jpa.repository.strategy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.udea.ssmu.api.model.jpa.model.Strategy;

@Repository
public interface IStrategyRepository extends JpaRepository<Strategy, Long>{
    
}
