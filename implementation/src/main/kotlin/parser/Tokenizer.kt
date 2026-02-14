package org.example.parser

import org.example.model.Token
import org.example.model.TokenData

class Tokenizer {
        fun tokenize(input: String): List<TokenData> {
            val tokens = mutableListOf<TokenData>();
            var lookup = "";

            for(inputLetter in input) {
                if (inputLetter == ' ' || inputLetter == '\n') {
                    continue
                }

                lookup += inputLetter
                for(token in Token.entries) {
                    if(lookup.matches(Regex(token.regex))) {
                        tokens.add(TokenData(token, lookup));
                        lookup = "";
                        break;
                    }
                }
            }

            if(lookup.isNotEmpty()) {
                throw IllegalArgumentException("Unexpected token: $lookup");
            }
            return tokens;

        }
}