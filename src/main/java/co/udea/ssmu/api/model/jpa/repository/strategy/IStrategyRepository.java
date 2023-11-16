package co.udea.ssmu.api.model.jpa.repository.strategy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.udea.ssmu.api.model.jpa.model.Strategy;

@Repository
public interface IStrategyRepository extends JpaRepository<Strategy, Long>{
    @Query(value = "SELECT s FROM Strategy s ORDER BY s.name DESC")
    List<Strategy> findAllWithQueryParam(@Param("limit") int limit, @Param("offset") int offset);

    //Retornar todas las estrategias excluyendo las estrategias que están relacionadas con la tabla cupon
    @Query(value = "SELECT s FROM Strategy s WHERE s.id NOT IN (SELECT c.strategy FROM Coupon c) ORDER BY s.name DESC")
    List<Strategy> findAllStrategies();
}
