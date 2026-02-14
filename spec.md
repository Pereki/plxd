# PLXD (PL eXtended Dataformat) Specification
The PLXD (PL eXtended Dataformat) is a data format designed for structured data with a focus on trains and beans. 
It provides a way to represent complex data structures in a human-readable format. The PLXD format is based on a hierarchical structure, allowing for nested data and flexible organization.

## Basic Structure

The PLXD format consists of key-value pairs, where keys and values are saved in their respective fields.
a PLXD Element is opened with `pl{` and closed with `}xd`. A PLXD Element may contain multiple PLXD Elements or a single key-value pair.
Each key is represented as a string and saved in the respective "bohnen" field, while the corresponding value is saved in the "zug" field.
The key may only contain letters, numbers, and underscores, and must not be empty. The value can be any valid data type, including strings, numbers, lists, or nested PLXD structures.
The key- and value-identifiers may be replaced by their emojis. The "bohnen" field is represented by the "🫘" emoji, while the "zug" field is represented by the "🚂" emoji.

If you want to store specific singelton values, you can save the value with the key "Bohn". The respective implementation will handle this field as singelton and will not allow multiple entries with the same key.

## Example
Here is an example of a PLXD structure representing a train with its properties:
```
pl{
    pl{
        🫘: <Key>
        🚂: <Value>
    }xd,
    pl{
        Bohne: <Key>
        Zug: <Value>
    }xd
}xd
```

If you want to save a PLXD Element inside a Value, you can do so by nesting the PLXD structure within the value field. For example:
```
pl{
    🫘: train
    🚂: pl{
        🫘: name
        🚂: "Express"
    }xd
}xd
```
