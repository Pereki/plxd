package org.example.parser

import org.example.model.model.Token
import org.example.model.model.TokenData

class Tokenizer {
        fun tokenize(input: String): List<TokenData> {
            var inputSplice = input;
            val tokens = mutableListOf<TokenData>();
            var lookup = "";
            var longestMatchIndex = 0;
            var lookupNumber = 0;
            var longestMatch : Token? = null;

            while (!inputSplice.isEmpty()){
                if(inputSplice.get(lookupNumber) == '\n' || inputSplice.get(lookupNumber) == ' ') {

                    if(longestMatch != null) {
                        inputSplice = inputSplice.substring(longestMatchIndex);
                        tokens.add(TokenData(longestMatch, lookup.replace("\n", "").replace(" ", "").replace(",", "").replace("\"", "")));
                    }
                    longestMatchIndex = 0;
                    lookupNumber = 0;
                    lookup = "";
                    longestMatch = null;
                    inputSplice = inputSplice.substring(1);
                    continue;
                }

                lookup += inputSplice.get(lookupNumber);

                for(token in Token.entries) {
                    if(lookup.matches(Regex(token.regex))) {
                        longestMatchIndex = lookupNumber;
                        longestMatch = token;
                    }
                }

                lookupNumber++;
            }

            if(lookup.isNotEmpty()) {
                throw IllegalArgumentException("Unexpected token: $lookup");
            }
            return tokens;

        }
}