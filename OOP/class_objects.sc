/*
Singleton objects are declared with the 'object' keyword.

To prevent creation of a derived class, use the 'final' keyword as a prefix.

Use 'abstract' to prevent instantiation of the class, such as when it contains or inherits
member declarations(fields, methods, or types) without providing concrete definitions for
them. Even when members are undefined.

An instance can refer to itself using the 'this' keyword, but it is rare in Scala, the
constructor boilerplate is absent in Scala, let's compare:
*/

// Java code
public class JPerson { private String name; private int age;
    public JPerson(String name, int age) { this.name = name;
        this.age = age;
    }

    publicvoid setName(Stringname){this.name=name;}
    public String getName() { return this.name; }

    public void setAge(int age) { this.age = age; }
    public int getAge() { return this.age; }
}

// Scala code
class Person(var name: String, var age: Int)
/*
var makes it a mutable field (attribute) of the class.
val would make it an inmmutable field, but using 'case' infers that we are using val and
also adds additional methods
*/

case class ImmutablePerson(name: String, age: Int)

/*
Scala allows overloaded methods,  >= 2 methods that share the same name as long as their
full signatures are unique (enclosing type name, method name, list of arguments)

A method and a field can have the same name, but only if the method has an argumen list:
*/

trait Foo {
    val x: Int
    def x(i: Int): Int
}

/*
Scala doesn't have static members, objects hold members that span instances, like constants

If an object and a class have the same name and are defined in the same file, they are
called companions. For case classes, the compiler automatically generates a companion object.
*/