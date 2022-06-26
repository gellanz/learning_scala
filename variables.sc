// Seq is inmutable
val seq: Seq[String] = Seq("This", "is", "Scala")
// Array is mutable, avoid mutable
var array: Array[String] = Array("This", "is", "Scala")
println(array.deep)
array(1) = "still is"
println(array.deep)
// var is for mutable variables
var seq2: Seq[String] = Seq("This", "is", "Scala")
// seq2 is now a different Seq object in memory
seq2 = Seq("This", "is not", "Scala")
