/* evaluate an expression once to initialize a value, by defering the invocation
Use cases:
- The expression is expensive
- Improve startup times for modules by deferring work that isn't needed immediatly
- For other initializations to happen first

lazy means that evaluation should be deferred until the value is needed

Lazy values are implemented with a guard, when referenced the guard checks if
initialization is requiered.
*/

object ExpensiveResource {
    lazy val resource: Int = init()
    def init(): Int = {
        // sth expensive
        0
    }
}
