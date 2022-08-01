// Precedence rules:
// letters => bitwise operations => comparators => (right) associative methods => operators
// same level operators are computed left to right

2.0 * 4.0 / 3.0 * 5.0 = ((2.0 * 4.0) / 3.0) * 5.0

// (right) associative methods
val list = List('b', 'c', 'd')
'a' :: list // list = List('a', 'b', 'c', 'd')
// or:
list.::('a')

// If statement
if (2 + 2 == 5) {
    println("Hey")
} else if (2 + 2 == 3) {
    println("Nah")
} else {
    println("mmmmm")
}

// assign the result of an if expression
val configFile = new java.io.File("random.txt")
val configFilePath = if (configFile.exists()) {
    configFile.getAbsolutePath()
} else {
    configFile.createNewFile()
    configFile.getAbsolutePath()
}
