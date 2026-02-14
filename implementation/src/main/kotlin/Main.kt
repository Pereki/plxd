package org.example

import org.example.parser.Parser
import org.example.parser.Tokenizer

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val tokenizer = Tokenizer();
    val parser = Parser();
    val res =tokenizer.tokenize(
            "    pl{\n" +
            "Bohne: \"abc\",\n" +
            "Zug: \"abc\"\n" +
            "    }xd\n")
    println(res);
    var res2 =parser.parse(res);
}