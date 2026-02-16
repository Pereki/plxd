package parser

import model.Token
import model.TokenData

class Tokenizer {
        fun tokenize(input: String): List<TokenData> {
            var inputSplice = input;
            val tokens = mutableListOf<TokenData>();
            var lookup = "";

            for(char in inputSplice) {

                if(char.isWhitespace() || char == '\n' || char == '\r') {
                    continue;
                }

                lookup += char;

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