package org.example.model

class PlxdElement : Element() {
    var startsWith: String = "pl{";
    var endsWith: String = "}xd";
    var key: String = "";
    var value: Element? = null;
    var elements: List<Element>? = null;
}