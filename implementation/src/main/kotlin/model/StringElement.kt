package model

open class StringElement(var value: String) : Element() {

    override fun toString(): String {
        return value;
    }
}