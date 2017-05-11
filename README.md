# `libffi` Bindings for Scala Native
![circleci shield](https://circleci.com/gh/nadavwr/libffi-scala-native.png?style=shield&circle-token=05e7c0a5b707342701902277cfc15db0e8176c87)


Scala Native's C foreign function interface, while very simple to use,
still lacks a few key features. This library provides an alternative to
the built-in FFI by using [libffi](https://sourceware.org/libffi/) — 
the same underlying library used by JNA.

The only reason you might want to use this is if you need to bind to
library functions that receive `struct` arguments by value, 
or return a `struct` by value. Presumably, the need for this library
will eventually go away.

## Usage

In a Scala Native project, add a resolver to the repository where
this library is published, and add a `%%%` dependency:

```scala
resolvers += Resolver.bintrayRepo("nadavwr", "maven"),
libraryDependencies += "com.github.nadavwr" %%% "libffi-scala-native" % "0.3.3"
```

Every parameter type must have an instance of `FfiType[_]`
in implicit scope. Primitive instances are already defined, so in
practice you will only need to do this for `struct`s.

For example, to match `libc`'s `div_t` `struct`
 
```c
typedef struct {
	int quot;		/* quotient */
	int rem;		/* remainder */
} div_t;
```

we will need to define `FfiType[DivT]` as follows:

```scala
import com.github.nadavwr.ffi._
  
type DivT = CStruct2[CInt, CInt]
implicit class DivOps(private val ptr: Ptr[DivT]) extends AnyVal {
  def quot: CInt = !ptr._1
  def rem: CInt = !ptr._2
}
  
implicit val ffiTypeOfDivT = FfiType.struct[DivT]("div_t", FfiType[CInt], FfiType[CInt])
```

We can then define bindings for `libc`'s `div` function, which returns
a `struct`:

```scala
val libc = Module.open("libc.dylib") // "libc.so" for Linux
val div = libc.prepare[CInt, CInt, DivT]("div")
val num = stackalloc[CInt]; !num = 10
val denom = stackalloc[CInt]; !denom = 4
val result = stackalloc[DivT]
div(num, denom)(result) // last argument points to result
  
println(s"div(10, 4) = ${result.quot} (${result.rem})")
assert(result.quot == 2)
assert(result.rem == 2)
  
// alternative "inline" allocation style
val result2 = div(num, denom)(stackalloc[DivT])
```

## Limitations

* The return value and all arguments must be preallocated, and 
provided to bound functions as pointers.
* There is no facility to unload shared libraries once they've been loaded.
* Expect a performance drop typical of `libffi`. 
Depending on your needs, this may be negligible.
