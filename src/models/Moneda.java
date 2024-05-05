package models;

import java.util.List;

public record Moneda(String result,
                     List<List<String>> supported_codes) {
}
