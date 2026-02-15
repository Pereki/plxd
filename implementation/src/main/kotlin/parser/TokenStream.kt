package org.example.parser

import model.Token
import model.TokenData

class TokenStream(val tokens: List<TokenData>) {
    private var position: Int = 0;

    fun position() : Int = position;
    fun isAtEnd() : Boolean = position >= tokens.size;

    fun peek() : TokenData {
        if(isAtEnd()) {
            throw IllegalStateException("End of token stream reached");
        }
        return tokens[position];
    }

    fun expect(expected: Token): TokenData {
        val t = peek()
        if (t.token != expected) throw java.lang.IllegalStateException("Expected $expected but got ${t.token} at position $position")
        position++;
        return t
    }

    fun consume() : TokenData {
        val t = peek();
        position++;
        return t;
    }
}