package parser

import model.PlxdElement
import model.StringElement
import model.Token
import model.TokenData

class Parser {

    fun parse(input: List<TokenData>): PlxdElement? {
        var parent : PlxdElement? = null;
        var initial = true;
        val tokenStack = ArrayDeque(listOf<TokenData>())
        val elementStack = ArrayDeque(listOf<PlxdElement>());

        for (tokenData in input) {
            if (tokenStack.isEmpty()) {
                when (tokenData.token) {
                    Token.PLXD_START -> {
                        val parentCreated = PlxdElement();
                        if(initial){
                            parent = parentCreated;
                            initial = false;
                        }
                        elementStack.add(parentCreated);
                        tokenStack.addLast(tokenData);
                    }

                    else -> throw IllegalArgumentException("Unexpected token: ${tokenData.token.name}")
                }
            } else {
                val currentToken = tokenStack.last()
                when (currentToken.token) {
                    Token.PLXD_START -> {
                        when (tokenData.token) {
                            Token.KEY_IDENTIFIER -> {
                                tokenStack.addLast(tokenData);
                            }
                            Token.PLXD_START -> {
                                tokenStack.addLast(tokenData);
                            }

                            else -> throw IllegalArgumentException("Unexpected token: ${tokenData.token.name}")
                        }
                    }

                    Token.KEY_IDENTIFIER -> {
                        when (tokenData.token) {
                            Token.KEY_VALUE_STRING -> {
                                tokenStack.removeLast();
                                tokenStack.addLast(tokenData)
                            }

                            else -> throw IllegalArgumentException("Unexpected token: ${tokenData.token.name}")
                        }
                    }

                    Token.KEY_VALUE_STRING -> {
                        when (tokenData.token) {
                            Token.VALUE_IDENTIFIER -> {
                                tokenStack.removeLast();
                                elementStack.last().key = currentToken.value;
                                tokenStack.addLast(tokenData);
                            }

                            else -> throw IllegalArgumentException("Unexpected token: ${tokenData.token.name}")
                        }
                    }

                    Token.VALUE_IDENTIFIER -> {
                        when (tokenData.token) {
                            Token.VALUE_VALUE_STRING -> {
                                tokenStack.removeLast();
                                elementStack.last().value = StringElement(tokenData.value);
                                tokenStack.addLast(tokenData);
                            }

                            Token.PLXD_START -> {
                                tokenStack.removeLast();
                                val newElement = PlxdElement();
                                elementStack.last().elements.add(newElement);
                                elementStack.addLast(newElement);
                                tokenStack.addLast(tokenData);
                            }

                            else -> throw IllegalArgumentException("Unexpected token: ${tokenData.token.name}")
                        }
                    }

                    Token.VALUE_VALUE_STRING -> {
                        when (tokenData.token) {
                            Token.PLXD_END -> {
                                tokenStack.removeLast();
                                tokenStack.addLast(tokenData);
                            }

                            else -> throw IllegalArgumentException("Unexpected token: ${tokenData.token.name}")
                        }
                    }

                    Token.PLXD_END -> {
                        tokenStack.removeLast()
                        if (tokenStack.last().token == Token.PLXD_START) {
                            tokenStack.removeLast();
                        }

                    }

                }
            }
        }

        return parent;
    }
}