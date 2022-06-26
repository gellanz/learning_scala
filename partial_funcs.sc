// A PartialFunction[A, B] is a special kind of function with its
// own literal sintax, A is the single parameter and B the return type

val pfs: PartialFunction[Any, String] = {
    case s:String => "YES"}
val pfd: PartialFunction[Any, String] = {
    case d:Double => "YES"}

val pfsd = pfs.orElse(pfd)

def tryPF(x: Any, f:PartialFunction[Any, String]): String = {
    try{
        f(x).toString
    } catch { 
        case _: MatchError => "ERROR!" 
    }
}

assert(tryPF("str", pfs)  == "YES")
assert(tryPF("str", pfd)  == "ERROR!")
assert(tryPF("str", pfsd) == "YES")
assert(tryPF(3.142, pfs)  == "ERROR!")
assert(tryPF(3.142, pfd)  == "YES")
assert(tryPF(3.142, pfsd) == "YES")
assert(tryPF(2, pfs) == "ERROR!")
assert(tryPF(2, pfd) == "ERROR!")
assert(tryPF(2, pfsd) == "ERROR!")



