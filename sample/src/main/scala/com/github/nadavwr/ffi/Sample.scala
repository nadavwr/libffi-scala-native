package com.github.nadavwr.ffi

import scala.scalanative.native._

object Sample extends App {
  type DivT = CStruct2[CInt, CInt]
  implicit val ffiTypeOfDivT = FfiTypeOf.struct[DivT](sizeof[DivT], FfiTypeOf[CInt], FfiTypeOf[CInt])

  implicit class DivOps(private val ptr: Ptr[DivT]) extends AnyVal {
    def quot: CInt = !ptr._1
    def rem: CInt = !ptr._2
  }

  val libc = Module.open("libc.dylib")
  val div = libc.prepare[CInt, CInt, DivT]("div")
  val num = stackalloc[CInt]; !num = 10
  val denom = stackalloc[CInt]; !denom = 4
  val result = stackalloc[DivT]
  div(num, denom, result)
  println(s"div(10, 4) = ${result.quot} (${result.rem})")
  assert(result.quot == 2)
  assert(result.rem == 2)
}
