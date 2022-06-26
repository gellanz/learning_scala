//  This is a comment
/*
Multiline comment
*/

class Upper1{
    // method(**params): return type = 
    //     body, return
    def convert(strings: Seq[String]): Seq[String] =
        // accepts a Sequence of Strings
        strings.map((s: String) => s.toUpperCase)
        // map iterates over the Seq and apply the transformation
        // of the anonymous function. The result of this call is 
        // automatically returned by the function literal
}

// NOTE: In Scala, the last expression in a function, method or other
// block is the return value. Btw, the return keyword exists.

val up = new Upper1()
val uppers = up.convert(List("Hello", "World!"))
println(uppers)