package enums.manifest;

public enum JsonField {
    UUID(JsonFieldConstant.UUID);

    private final String field;

    JsonField(String field) {
        this.field = field;
    }

    public String field() {
        return field;
    }

    public static class JsonFieldConstant {
        public static final String UUID = "UUID";
    }
}