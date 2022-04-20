package domain;

public enum TypesOfTranslation {
    TXT("Текст"), DOC("Документы"), WEB("Сайты");

    public final String rusName;

    TypesOfTranslation(String rusName) {
        this.rusName = rusName;
    }
}
