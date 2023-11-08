package co.udea.ssmu.api;

import org.springframework.boot.test.context.SpringBootTest;
/* import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import co.udea.ssmu.api.controller.CouponController;
import co.udea.ssmu.api.model.jpa.dto.CouponDTO;
import co.udea.ssmu.api.model.jpa.dto.StrategyDTO;
import co.udea.ssmu.api.utils.common.StandardResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.LocalDateTime; */

@SpringBootTest
class SsmuApiApplicationTests {
/* 
    @Autowired
    private CouponController couponController;

    @Test
    public void testCreateCoupon() {
        // Crear un CouponDTO de prueba, no se envia codigo ni estado strategy
        CouponDTO couponDTO = new CouponDTO();
        couponDTO.setStrategy(new StrategyDTO());
        couponDTO.setAmount(80);
        couponDTO.getStrategy().setName("Amor y amistad");
        couponDTO.getStrategy().setCity("Medellín");
        couponDTO.getStrategy().setDescription("Cupón de descuento Amor y Amistad");
        couponDTO.getStrategy().setDiscountPercentage(20);
        couponDTO.getStrategy().setStartDate(LocalDateTime.parse("2021-09-01T00:00:00"));
        couponDTO.getStrategy().setEndDate(LocalDateTime.parse("2021-09-30T23:59:59"));
        couponDTO.getStrategy().setMaxDiscount(6000);
        couponDTO.getStrategy().setMinValue(12000);

        // Llamar al método que se está probando
        ResponseEntity<StandardResponse<String>> response = couponController.createCoupon(couponDTO);

        // Verificar el resultado
        StandardResponse<String> responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("El cupón fue creado exitosamente", responseBody.getMessage());
    } */
}