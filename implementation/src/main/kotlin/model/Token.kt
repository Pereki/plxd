package model

enum class Token(val regex: String) {
    PLXD_START("pl\\{"),
    PLXD_END("\\}xd"),
    KEY_IDENTIFIER("[b|B]ohne|🫘"),
    VALUE_IDENTIFIER("[z|Z]ug|🚂"),
    SEPARATOR(":"),
    KOMMA(","),
    VALUE_STRING("\"[^\"]*\"")
}
