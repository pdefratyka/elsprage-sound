package com.elsprage.sound.model.enumeration;

public enum Language {
    PL("pl"),
    EN("en"),
    DE("de");

    private final String value;

    Language(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Language fromValue(String value) {
        for (Language language : Language.values()) {
            if (language.value.equals(value)) {
                return language;
            }
        }
        throw new IllegalArgumentException("Unknown language: " + value);
    }
}
