package org.example.model.model

class TokenData(val token: Token, val value: String) {

    override fun toString(): String {
        return "{ token: ${token.name}, value: $value }"
    }
}