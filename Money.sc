case class Money(value: BigDecimal)
// constructor expects a BigDecimal
case object Money{
    // we want the user to be able to provide a string or a double
    def apply(s: String): Money = {
        apply(BigDecimal(s.toDouble))
    }

    def apply(d: Double): Money = {
        apply(BigDecimal(d))
    }
}

/* 
Explicit type annotations are requiered when:
- abstract var or val declarations in an abstract class or trait
- all method parameters
- explicitly call return in a method
- recursive methods
- two or more methods are overloaded
- the inferred return type would be more general than you intended
*/