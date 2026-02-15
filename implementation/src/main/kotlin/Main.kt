package org.example

import parser.Parser
import parser.Tokenizer


fun main() {
    val tokenizer = Tokenizer();
    val parser = Parser();
    val res =tokenizer.tokenize(
            "    pl{\n" +
            "Bohne: \"abc\",\n" +
            "Zug: pl{ Bohne: \"cde\", Zug: \"cde\" }xd\n" +
            "    }xd\n")
    println(res);
    var res2 =parser.parse(res);
}