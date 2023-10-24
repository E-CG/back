package co.udea.ssmu.api.utils;

import java.util.HashSet;
import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class CouponBuilder {
    Random random = new Random();

    public String buildCodeCoupon(String name) {
        String prefix = buildPrefix(name);
        StringBuilder code = new StringBuilder(prefix);
        int sufix = 4;

        for (int i = 0; i < sufix; i++) {
            code.append(random.nextInt(10));
        }

        return code.toString();
    }

    private String buildPrefix(String nombre) {
        HashSet<Character> letterImportant = new HashSet<>();
        for (char letter : nombre.toUpperCase().toCharArray()) {
            if (Character.isLetter(letter)) {
                letterImportant.add(letter);
            }
        }

        StringBuilder prefix = new StringBuilder();
        int limit = Math.min(letterImportant.size(), 4);

        for (Character letter : letterImportant) {
            prefix.append(letter);
            if (--limit == 0) {
                break; // Salir del bucle después de los primeros 4 caracteres
            }
        }

        return prefix.toString();
    }

}
