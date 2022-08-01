// For
val dogBreeds = List("Doberman", "Yorkshire Terrier", "Dachshund",
                     "Scottish Terrier", "Great Dane", "Portuguese Water Dog")
// like Python
for (breed <- dogBreeds)
    println(breed)

// Generator expressions, generates individual values from a collection
for (i <- 1 to 10) println(i)

// Guards
for (breed <- dogBreeds if breed.contains("Terrier")) println(breed)
// two or more guards
for (breed <- dogBreeds
    if breed.contains("Terrier") && !breed.startsWith("Yorkshire")
) println(breed)

// Yielding: generate new collections with for expressions

val filteredBreeds = for {
    breed <- dogBreeds
    breed.contains("Terrier") && !breed.startsWith("Yorkshire")
} yield breed
// filteredBreeds is of type List[String], because it is derived from dogBreeds

// parentheses with only one expression, curly crackets with multiple expressions

// Expanded scope and value definitions
for {
    // define and use values in later expressions
    breed <- dogBreeds
    upcasedBreed = breed.toUpperCase()
} println(upcasedBreed)

/*
There is no 'continue' nor 'break' statement, they are rarely used when writting
idiomatic Scala code. Use conditional expressions to test is a loop should
continue or recursion, or filter the collection ahead of time to avoid complexity.
*/
