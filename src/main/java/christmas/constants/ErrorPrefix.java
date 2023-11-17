package christmas.constants;

public enum ErrorPrefix {

    PREFIX("[ERROR] ");

    private final String errorPrefix;

    ErrorPrefix(String errorPrefix) {
        this.errorPrefix = errorPrefix;
    }

    public String getErrorPrefix() {
        return errorPrefix;
    }
}
