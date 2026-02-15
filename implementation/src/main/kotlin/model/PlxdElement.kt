package model

class PlxdElement : Element() {
    var key: String = "";
    var value: Element? = null;
    var elements: MutableList<Element> = mutableListOf();

}