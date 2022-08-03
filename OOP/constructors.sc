// Scala only supports single inheritance, only one parent class

// We can have auxiliary constructors:

case class Address(street: String, city: String, state: String, zip: String) {
    // secondary constructor that calls helper methods to infer city and state
    def this(zip: String) =
        this("[unknown]", Address.zipToCity(zip), Address.zipToState(zip), zip)
}

object Address {
    // helper functions that are supposedly to find the city and state based on zip code
    def zipToCity(zip: String) = "Anytown"
    def zipToState(zip: String) = "CA"
}

case class Person(
    name: String, age: Option[Int], address: Option[Address]) {
        // the age and address are optional
        // auxialiary constructor depending on the specified arguments:
        def this(name: String) = this(name, None, None)
        def this(name: String, age: Int) = this(name, Some(age), None)
        def this(name: String, age: Int, address: Address) =
            this(name, Some(age), some(address))
        def this(name: String, address: Address) = this(name, None, some(address))
        // bad thing: lots of boilerplate
    }

/*
The axialiry constructor is named 'this' and it must be the primary constructor or another
auxiliary as its first expression. The compiler requieres that the constructor called is one
that appears arlier in the source code, we must order secondary constructors carefully.
*/

new Person("Buck Trends1")
// Result: Person(Buck Trends1,None,None)
new Person("Buck Trends2", Some(20), Some(a1))
// Result: Person(Buck Trends2,Some(20),
// Some(Address(1 Scala Lane,Anytown,CA,98765)))
new Person("Buck Trends3", 20, a2)
// Result: Person(Buck Trends3,Some(20),
// Some(Address([unknown],Anytown,CA,98765)))

// We also can initialize the values from the constructor:

case class Person2(
    name: String,
    age: Option[Int] = None,
    address: Option[Address] = None
    // but the maintenance burden rises this way
)

new Person2("Buck Trends1")
// Result: Person2 = Person2(Buck Trends1,None,None)
new Person2("Buck Trends2", Some(20), Some(a1))
// Result: Person2(Buck Trends2,Some(20),
// Some(Address(1 Scala Lane,Anytown,CA,98765)))
new Person2("Buck Trends3", Some(20))
// Result: Person2(Buck Trends3,Some(20),None)

// If we overload Person.apply in the companion object, we can have convenient constructor and
// avoid the requierement to use 'new'

case class Person3(
    name: String,
    age: Option[Int] = None,
    address: Option[Address] = None
)

object Person3 {
    def apply(name: String): Person3 = new Person3(name)

    def apply(name: String, age: Int): Person3 = new Person3(name, Some(age))

    def apply(name: String, age: Int, address: Address): Person3 = new Person3(name, Some(age), Some(address))

    def apply(name: String, address: Address): Person3 = new Person3(name, address = Some(address))
}

// overloaded methods like apply that aren't constructors must have an explicit
// return type anotation

Person3("Buck Trends2", Some(20), Some(a1))
// primary: get the base apply method generated as part of the case class
// Result: Person3(Buck Trends2,Some(20),
// Some(Address(1 Scala Lane,Anytown,CA,98765)))
Person3("Buck Trends3", 20, Address("1 Scala Lane", "Anytown", "CA", "98765"))
// Result: Person3(Buck Trends3,Some(20),
// Some(Address(1 Scala Lane,Anytown,CA,98765)))
Person3("Buck Trends4", Some(20))
// primary: get the base apply method generated as part of the case class
// Result: Person3(Buck Trends4,Some(20),None)
Person3("Buck Trends5", 20)
// Result: Person3(Buck Trends5,Some(20),None)