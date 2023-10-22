package co.udea.ssmu.api.utils;

import java.util.HashSet;
import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class CouponBuilder {
    Random random = new Random();

    public String buildCodeCoupon(String name){
        String prefix = buildPrefix(name);
        StringBuilder code = new StringBuilder(prefix);
        int sufix = 3;  // Cambiado a 3 para tener un total de 8 caracteres

        for (int i = 0; i < sufix; i++) {
            if(random.nextBoolean()){
                code.append(random.nextInt(10));
            } else {
                code.append((char) (random.nextInt(26) + 'A'));
            }
        }

        return code.toString();
    }

    private String buildPrefix(String nombre){
        HashSet<Character> letterImportant = new HashSet<>();
        for (char letter: nombre.toUpperCase().toCharArray()){
            if (Character.isLetter(letter)){
                letterImportant.add(letter);
            }
        }

        StringBuilder prefix = new StringBuilder();
        for (Character letter : letterImportant) {
            prefix.append(letter);
        }

        return prefix.toString();
    }
}
