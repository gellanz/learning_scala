// named arguments make code more readable
case class Point(x: Double = 0.0, y: Double = 0.0){
    def shift(deltax: Double = 0.0, deltay: Double = 0.0) = {
        copy(x + deltax, y + deltay)
    }
}

// Methods with multiple Parameters Lists

abstract class Shape() {
    // two parameters lists, each one with a single parameter 
    def draw(offset: Point = Point(0.0, 0.0))(f: String => Unit): Unit = {
        f(s"draw: offset = $offset, shape = ${this}")
    }
}

case class Circle(center: Point, radius: Double) extends Shape

case class Rectangle(lowerLeft: Point, height: Double, width: Double) extends Shape

case class Triangle(point1: Point, point2: Point, point3: Point) extends Shape

val s = Circle(Point(0.0, 0.0), 1.0)
s.draw(Point(1.0, 2.0))(str => println(str))
/*
Scala let us replace parentheses with curly brace around
a supplied argument (like a funtion literal) for a 
parameter list that has a single parameter: 

s.draw(Point(1.0, 2.0)){str => println(str)}
*/
