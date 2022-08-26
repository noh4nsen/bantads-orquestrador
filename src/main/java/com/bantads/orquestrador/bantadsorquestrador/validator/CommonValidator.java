package com.bantads.orquestrador.bantadsorquestrador.validator;

public final class CommonValidator {
    public static boolean validateString(String string) {
        if (string == null || string.isEmpty() || string.isBlank()) {
            return false;
        } else {
            return true;
        }
    }
}
