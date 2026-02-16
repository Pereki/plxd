package org.example

import parser.Parser
import parser.Tokenizer


fun main(args: Array<String>) {
    val tokenizer = Tokenizer();
    val parser = Parser();

    if(args.isEmpty()) {
        println("Please provide an input string as a command line argument");
        return;
    }

    val res =tokenizer.tokenize(args.first())
    println(res);
    println("successfully tokenized input, now parsing...");
    parser.parse(res);
    println("successfully parsed input");
}