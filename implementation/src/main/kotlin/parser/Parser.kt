package parser

import model.PlxdElement
import model.StringElement
import model.Token
import model.TokenData
import org.example.parser.TokenStream

class Parser {


    fun parse(input: List<TokenData>): PlxdElement? {
        val tokenStream = TokenStream(input);
        return parsePlxdElement(tokenStream);
    }

    private fun parsePlxdElement(tokenStream: TokenStream): PlxdElement {

        val plxdElement = PlxdElement();


        tokenStream.expect(Token.PLXD_START);
        tokenStream.expect(Token.KEY_IDENTIFIER);
        tokenStream.expect(Token.SEPARATOR);
        plxdElement.key = tokenStream.expect(Token.VALUE_STRING).value;
        tokenStream.expect(Token.KOMMA);
        tokenStream.expect(Token.VALUE_IDENTIFIER);
        tokenStream.expect(Token.SEPARATOR);

        val startOrElement = tokenStream.peek();

        when (startOrElement.token) {
            Token.PLXD_START -> plxdElement.value = parsePlxdElement(tokenStream);
            Token.VALUE_STRING -> plxdElement.value = StringElement(tokenStream.consume().value);
            else -> throw IllegalArgumentException("Expected PLXD_START or VALUE_STRING token for element name, got ${startOrElement.token.name}");
        }

        tokenStream.expect(Token.PLXD_END);
        return plxdElement;
    }
}