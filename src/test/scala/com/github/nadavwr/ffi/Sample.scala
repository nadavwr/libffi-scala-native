package com.github.nadavwr.ffi

import scala.scalanative.native._
import utest._

object Sample extends TestSuite {
  type DivT = CStruct2[CInt, CInt]
  implicit val ffiTypeOfDivT = FfiType.struct[DivT]("div_t", FfiType[CInt], FfiType[CInt])

  implicit class DivOps(private val ptr: Ptr[DivT]) extends AnyVal {
    def quot: CInt = !ptr._1
    def rem: CInt = !ptr._2
  }

  val tests = this {
    "div() in libc returns struct by value" - {
      val libc = Module.open() // look at all dynamically linked libraries
      val div = libc.prepare[CInt, CInt, DivT]("div")
      println(s"${div.name}() prepared as '$div'")
      val num = stackalloc[CInt]; !num = 10
      val denom = stackalloc[CInt]; !denom = 4
      val result = div(num, denom)(stackalloc[DivT]) // last argument allocates return value

      println(s"div(10, 4) = ${result.quot} (${result.rem})")

      result.quot ==> 2
      result.rem ==> 2
    }
  }
}
