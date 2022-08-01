// track the number of types => enumerated type that contains enumerations

object Breed extends Enumeration {
    type Breed = Value
    val doberman = Value("Doberman")
    val yorkie = Value("Yorkshire Terrier")
    val scottie = Value("Scottish Terrier")
    val dane = Value("Great Dane")
    val portie = Value("Portuguese Water Dog")
}

import Breed._

// print a list of breeds and their IDs
println("ID\tBreed")
for (breed <- Breed.values) println(s"${breed.id}\t$breed")

// print a list of Terrier breeds
println("\nJust Terriers:")
Breed.values filter (_.toString.endsWith("Terrier")) foreach println

def isTerrier(b: Breed) = b.toString.endsWith(Terrier)
println("\nTerriers Again??")
Breed.values filter isTerrier foreach println


/* Each declaration of Value is calling a method named Value.
We use this method to assign a long-form breed name to each enumeration value,
that why we need to us .toString() method, it is not an out of the box String

The type Breed is an alias that lets is reference Breed instead of Value, like
in the isTerrier method.

The vlaues are incremented and assigned automatically starting at 0, in declaration order.
*/

// Human readable names in the enumeration

object WeekDay extends Enumeration {
    type WeekDay = Value
    val Mon, Tue, Wed, Thu, Fri, Sat, Sun = Value
}

import WeekDay._

def isWorkingDay(d: WeekDay) = (d == Sat || d == Sun)

WeekDay.values filter isWorkingDay foreach println

/* Enumerations are limited to the case where you know in advance
the exact set of values to define. Clients can't add more values

case classes are often used when an "enumeration of values" is needed.
They are a bit more heavyweight but they have two advantages:
1.- Offer great flexibility to add methods and fields, and to use
pattern matching on them
2.- They aren't limited to a fix set of known values. Client code can
more case classes to a base set that your library defines.
*/