package amanet.enums;

public enum LanguagesEnum {
    English("English"),
    Deutsch("Deutsch"),
    Suomi("Suomi");

    private String language;

    LanguagesEnum(String language) {
        this.language = language;
    }

    public String getLanguage(){
        return this.language;
    }
}
