package org.example

import org.example.model.Token
import org.example.parser.Tokenizer

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val tokenizer = Tokenizer();
    val res =tokenizer.tokenize("pl{\n" +
            "    pl{\n" +
            "        \uD83E\uDED8: \"abc\"\n" +
            "        \uD83D\uDE82: \"abc\"\n" +
            "    }xd,\n" +
            "    pl{\n" +
            "        Bohne: \"abc\"\n" +
            "        Zug: \"abc\"\n" +
            "    }xd\n" +
            "}xd")
    println(res);
}