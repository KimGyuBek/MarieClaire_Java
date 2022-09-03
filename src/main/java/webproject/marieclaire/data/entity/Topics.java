package webproject.marieclaire.data.entity;

public enum Topics {
    BEAUTY("beauty"),
    CELEB("celeb"),
    CULTURE("culture"),
    FASHION("fashion"),
    LIFE("life");

    private final String description;

    Topics(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
