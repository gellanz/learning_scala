/*
Wrappers around value types effectively turn them into reference types, defeating the
performance optimization of using primitives. Scala introduced "value classes" and a tandem
feature called "universal traits". These types impose limits on what can be declared, but
they don't result in heap allocations for the wrappers
*/

class Dollar(val value: Float) extends AnyVal {
    override def toString = "$%.2f".format(value)
}

val benjamin = new Dollar(100)

/*
Rules:
1.- The value class has one and only one public val argument
2.- The type of the argument must not be a value class itself
3.- If the value class is parametrized, the @specialized annotation can't be used
4.- The value class doesn't define secondary constructors
5.- The value class defines methods only, no new vars nor vals
6.- It can't override equals and hashCode
7.- It defines no nested traits, classes, or objects
8.- The value class cannot be subclassed
9.- It can only inherit from universal traits
10.- It must be a top-level type or a member of an object that can be referenced

In the above example, in compile time the type is the outer type Dollar, in runtime
the type is the wrapped type Float.

A value class can be a case class, but it would waste space.

Let's see a good example:
*/

trait Digitizer extends Any {
    def digits(s: String): String = s.replaceAll("""\D""", "")
}

trait Formatter extends Any {
    def format(areaCode: String, exchange: String, subnumber: String): String =
        s"($areaCode) $exchange-$subnumber"
}

class USPhoneNumber(val s: String) extends AnyVal
    with Digitizer with Formatter {

    override def toString = {
        val digs = digits(s)
        val areaCode = digs.substring(0,3)
        val exchange = digs.substring(3,6)
        val subnumber = digs.substring(6,10)
        format(areaCode, exchange, subnumber)
    }
}

val number = new USPhoneNumber("987-654-2341")
// Resukt: number: USPhoneNumber = (987) 654-3241

Formatter solves a design problem, we might like to specify a second argument
to USPhoneNumber for a format string or other formatting mechanism, but we can't
because we are only allowed to pass one argument to the constructor, but we can
mix in universal traits to do the configuration we want.

The term value type refers to the Short, Int, Long, Float, Double, Boolean, Char,
Byte, and Unit types Scala has had for a long time. The term value class refers
to the new construct for defining custom classes that derive from AnyVal.