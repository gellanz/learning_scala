/* For now, lets think of them as interfaces that gives the option of defining
the methods you declare, they can also declare and optionally define instance
fields (not just static fields), same with type values.
Trait enable true composition of behavior.
*/

class ServiceImportant(name: String) {
    def work(i: Int): Int = {
        println(s"ServiceImportance: Doing important work! $i")
    }
}

val service1 = new ServiceImportant("uno")
(1 to 3) foreach (i => println(s"Result: ${service1.work(i)}"))

/*
ServiceImportante: Doing important work! 1
    Result: 2
    ServiceImportante: Doing important work! 2
    Result: 3
    ServiceImportante: Doing important work! 3
    Result: 4
*/

// Let's mix in a logging library
trait Logging {
    def info(message: String): Unit
    def warning(message: String): Unit
    def error(message: String): Unit
}

trait StdoutLogging extends Logging {
    def info(message: String): println(s"INFO: $message")
    def warning(message: String): println(s"WARNING: $message")
    def error(message: String): println(s"ERROR: $message")
}

// service that mixes in logging

val service2 = new ServiceImportance("dos") with StdoutLogging {
    // for mix in traits we use 'with'
    override def work(i: Int): Int = {
        // we are overriding the behavior of work in order to inject logging,
        // but we are not changing its "contract" with clients
        info(s"Starting work: i = $i")
        // using the parent house method
        val result = super.work(i)
        info(s"Ending work: i = $i, result = $result")
        result
    }
}

(1 to 3) foreach (i => println(s"Result: ${service2.work(i)}"))

/*
    INFO:    Starting work: i = 1
    ServiceImportante: Doing important work! 1
    INFO:    Ending work: i = 1, result = 2
    Result: 2
    INFO:    Starting work: i = 2
    ServiceImportante: Doing important work! 2
    INFO:    Ending work: i = 2, result = 3
    Result: 3
    INFO:    Starting work: i = 3
    ServiceImportante: Doing important work! 3
    INFO:    Ending work: i = 3, result = 4
    Result: 4
*/

// If we needed multiple instances of ServiceImportante with StdoutLogging, we could declare a class:
class LoggedServiceImportance(name: String)
    extends ServiceImportance(name) with StdoutLogging {...}
// We pass the name argument to the parent class, and we should override the work method
