package com.github.nadavwr.ffi

import scala.scalanative.native._

private case class PtrWrapper(ptr: Ptr[Byte])

class Module(private val moduleHandle: Ptr[Byte]) {
  def fn(symbol: String): Ptr[Byte] = {
    val PtrWrapper(ptr) =
      Zone { implicit zone =>
        PtrWrapper(dl.dlsym(moduleHandle, toCString(symbol)))
      }
    ptr
  }
  def prepare[T1 : FfiType, R : FfiType]
             (symbol: String): CallInterface1[T1, R] = {
    val cif = new FfiCif(symbol, FfiType[R], FfiType[T1])
    new CallInterface1[T1, R](cif, fn(symbol))
  }
  def prepare[T1 : FfiType, T2 : FfiType, R : FfiType]
             (symbol: String): CallInterface2[T1, T2, R] = {
    val cif = new FfiCif(symbol, FfiType[R], FfiType[T1], FfiType[T2])
    new CallInterface2[T1, T2, R](cif, fn(symbol))
  }
  def prepare[T1 : FfiType, T2 : FfiType, T3 : FfiType, R : FfiType]
             (symbol: String): CallInterface3[T1, T2, T3, R] = {
    val cif = new FfiCif(symbol, FfiType[R], FfiType[T1], FfiType[T2], FfiType[T3])
    new CallInterface3[T1, T2, T3, R](cif, fn(symbol))
  }
  def prepare[T1 : FfiType, T2 : FfiType, T3 : FfiType, T4 : FfiType, R : FfiType]
             (symbol: String): CallInterface4[T1, T2, T3, T4, R] = {
    val cif = new FfiCif(symbol, FfiType[R], FfiType[T1], FfiType[T2], FfiType[T3], FfiType[T4])
    new CallInterface4[T1, T2, T3, T4, R](cif, fn(symbol))
  }
  def prepare[T1 : FfiType, T2 : FfiType, T3 : FfiType, T4 : FfiType, T5 : FfiType, R : FfiType]
             (symbol: String): CallInterface5[T1, T2, T3, T4, T5, R] = {
    val cif = new FfiCif(symbol, FfiType[R], FfiType[T1], FfiType[T2], FfiType[T3], FfiType[T4], FfiType[T5])
    new CallInterface5[T1, T2, T3, T4, T5, R](cif, fn(symbol))
  }
  def prepare[T1 : FfiType, T2 : FfiType, T3 : FfiType, T4 : FfiType, T5 : FfiType, T6 : FfiType, R : FfiType]
             (symbol: String): CallInterface6[T1, T2, T3, T4, T5, T6, R] = {
    val cif = new FfiCif(symbol, FfiType[R], FfiType[T1], FfiType[T2], FfiType[T3], FfiType[T4], FfiType[T5], FfiType[T6])
    new CallInterface6[T1, T2, T3, T4, T5, T6, R](cif, fn(symbol))
  }
  def prepare[T1 : FfiType, T2 : FfiType, T3 : FfiType, T4 : FfiType, T5 : FfiType, T6 : FfiType, T7 : FfiType, R : FfiType]
  (symbol: String): CallInterface7[T1, T2, T3, T4, T5, T6, T7, R] = {
    val cif = new FfiCif(symbol, FfiType[R], FfiType[T1], FfiType[T2], FfiType[T3], FfiType[T4], FfiType[T5], FfiType[T6], FfiType[T7])
    new CallInterface7[T1, T2, T3, T4, T5, T6, T7, R](cif, fn(symbol))
  }
  def prepare[T1 : FfiType, T2 : FfiType, T3 : FfiType, T4 : FfiType, T5 : FfiType, T6 : FfiType, T7 : FfiType, T8 : FfiType, R : FfiType]
  (symbol: String): CallInterface8[T1, T2, T3, T4, T5, T6, T7, T8, R] = {
    val cif = new FfiCif(symbol, FfiType[R], FfiType[T1], FfiType[T2], FfiType[T3], FfiType[T4], FfiType[T5], FfiType[T6], FfiType[T7], FfiType[T8])
    new CallInterface8[T1, T2, T3, T4, T5, T6, T7, T8, R](cif, fn(symbol))
  }
  def prepare[T1 : FfiType, T2 : FfiType, T3 : FfiType, T4 : FfiType, T5 : FfiType, T6 : FfiType, T7 : FfiType, T8 : FfiType, T9 : FfiType, R : FfiType]
  (symbol: String): CallInterface9[T1, T2, T3, T4, T5, T6, T7, T8, T9, R] = {
    val cif = new FfiCif(symbol, FfiType[R], FfiType[T1], FfiType[T2], FfiType[T3], FfiType[T4], FfiType[T5], FfiType[T6], FfiType[T7], FfiType[T8], FfiType[T9])
    new CallInterface9[T1, T2, T3, T4, T5, T6, T7, T8, T9, R](cif, fn(symbol))
  }
  def prepare[T1 : FfiType, T2 : FfiType, T3 : FfiType, T4 : FfiType, T5 : FfiType, T6 : FfiType, T7 : FfiType, T8 : FfiType, T9 : FfiType, T10 : FfiType, R : FfiType]
  (symbol: String): CallInterface10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R] = {
    val cif = new FfiCif(symbol, FfiType[R], FfiType[T1], FfiType[T2], FfiType[T3], FfiType[T4], FfiType[T5], FfiType[T6], FfiType[T7], FfiType[T8], FfiType[T9], FfiType[T10])
    new CallInterface10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R](cif, fn(symbol))
  }
  def prepare[T1 : FfiType, T2 : FfiType, T3 : FfiType, T4 : FfiType, T5 : FfiType, T6 : FfiType, T7 : FfiType, T8 : FfiType, T9 : FfiType, T10 : FfiType, T11 : FfiType, R : FfiType]
  (symbol: String): CallInterface11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R] = {
    val cif = new FfiCif(symbol, FfiType[R], FfiType[T1], FfiType[T2], FfiType[T3], FfiType[T4], FfiType[T5], FfiType[T6], FfiType[T7], FfiType[T8], FfiType[T9], FfiType[T10], FfiType[T11])
    new CallInterface11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R](cif, fn(symbol))
  }
  def prepare[T1 : FfiType, T2 : FfiType, T3 : FfiType, T4 : FfiType, T5 : FfiType, T6 : FfiType, T7 : FfiType, T8 : FfiType, T9 : FfiType, T10 : FfiType, T11 : FfiType, T12 : FfiType, R : FfiType]
  (symbol: String): CallInterface12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R] = {
    val cif = new FfiCif(symbol, FfiType[R], FfiType[T1], FfiType[T2], FfiType[T3], FfiType[T4], FfiType[T5], FfiType[T6], FfiType[T7], FfiType[T8], FfiType[T9], FfiType[T10], FfiType[T11], FfiType[T12])
    new CallInterface12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R](cif, fn(symbol))
  }
  def prepare[T1 : FfiType, T2 : FfiType, T3 : FfiType, T4 : FfiType, T5 : FfiType, T6 : FfiType, T7 : FfiType, T8 : FfiType, T9 : FfiType, T10 : FfiType, T11 : FfiType, T12 : FfiType, T13 : FfiType, R : FfiType]
  (symbol: String): CallInterface13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R] = {
    val cif = new FfiCif(symbol, FfiType[R], FfiType[T1], FfiType[T2], FfiType[T3], FfiType[T4], FfiType[T5], FfiType[T6], FfiType[T7], FfiType[T8], FfiType[T9], FfiType[T10], FfiType[T11], FfiType[T12], FfiType[T13])
    new CallInterface13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R](cif, fn(symbol))
  }
  def prepare[T1 : FfiType, T2 : FfiType, T3 : FfiType, T4 : FfiType, T5 : FfiType, T6 : FfiType, T7 : FfiType, T8 : FfiType, T9 : FfiType, T10 : FfiType, T11 : FfiType, T12 : FfiType, T13 : FfiType, T14 : FfiType, R : FfiType]
  (symbol: String): CallInterface14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R] = {
    val cif = new FfiCif(symbol, FfiType[R], FfiType[T1], FfiType[T2], FfiType[T3], FfiType[T4], FfiType[T5], FfiType[T6], FfiType[T7], FfiType[T8], FfiType[T9], FfiType[T10], FfiType[T11], FfiType[T12], FfiType[T13], FfiType[T14])
    new CallInterface14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R](cif, fn(symbol))
  }
  def prepare[T1 : FfiType, T2 : FfiType, T3 : FfiType, T4 : FfiType, T5 : FfiType, T6 : FfiType, T7 : FfiType, T8 : FfiType, T9 : FfiType, T10 : FfiType, T11 : FfiType, T12 : FfiType, T13 : FfiType, T14 : FfiType, T15 : FfiType, R : FfiType]
  (symbol: String): CallInterface15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R] = {
    val cif = new FfiCif(symbol, FfiType[R], FfiType[T1], FfiType[T2], FfiType[T3], FfiType[T4], FfiType[T5], FfiType[T6], FfiType[T7], FfiType[T8], FfiType[T9], FfiType[T10], FfiType[T11], FfiType[T12], FfiType[T13], FfiType[T14], FfiType[T15])
    new CallInterface15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R](cif, fn(symbol))
  }
  def prepare[T1 : FfiType, T2 : FfiType, T3 : FfiType, T4 : FfiType, T5 : FfiType, T6 : FfiType, T7 : FfiType, T8 : FfiType, T9 : FfiType, T10 : FfiType, T11 : FfiType, T12 : FfiType, T13 : FfiType, T14 : FfiType, T15 : FfiType, T16 : FfiType, R : FfiType]
  (symbol: String): CallInterface16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R] = {
    val cif = new FfiCif(symbol, FfiType[R], FfiType[T1], FfiType[T2], FfiType[T3], FfiType[T4], FfiType[T5], FfiType[T6], FfiType[T7], FfiType[T8], FfiType[T9], FfiType[T10], FfiType[T11], FfiType[T12], FfiType[T13], FfiType[T14], FfiType[T15], FfiType[T16])
    new CallInterface16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R](cif, fn(symbol))
  }
  def prepare[T1 : FfiType, T2 : FfiType, T3 : FfiType, T4 : FfiType, T5 : FfiType, T6 : FfiType, T7 : FfiType, T8 : FfiType, T9 : FfiType, T10 : FfiType, T11 : FfiType, T12 : FfiType, T13 : FfiType, T14 : FfiType, T15 : FfiType, T16 : FfiType, T17 : FfiType, R : FfiType]
  (symbol: String): CallInterface17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R] = {
    val cif = new FfiCif(symbol, FfiType[R], FfiType[T1], FfiType[T2], FfiType[T3], FfiType[T4], FfiType[T5], FfiType[T6], FfiType[T7], FfiType[T8], FfiType[T9], FfiType[T10], FfiType[T11], FfiType[T12], FfiType[T13], FfiType[T14], FfiType[T15], FfiType[T16], FfiType[T17])
    new CallInterface17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R](cif, fn(symbol))
  }
  def prepare[T1 : FfiType, T2 : FfiType, T3 : FfiType, T4 : FfiType, T5 : FfiType, T6 : FfiType, T7 : FfiType, T8 : FfiType, T9 : FfiType, T10 : FfiType, T11 : FfiType, T12 : FfiType, T13 : FfiType, T14 : FfiType, T15 : FfiType, T16 : FfiType, T17 : FfiType, T18 : FfiType, R : FfiType]
  (symbol: String): CallInterface18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R] = {
    val cif = new FfiCif(symbol, FfiType[R], FfiType[T1], FfiType[T2], FfiType[T3], FfiType[T4], FfiType[T5], FfiType[T6], FfiType[T7], FfiType[T8], FfiType[T9], FfiType[T10], FfiType[T11], FfiType[T12], FfiType[T13], FfiType[T14], FfiType[T15], FfiType[T16], FfiType[T17], FfiType[T18])
    new CallInterface18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R](cif, fn(symbol))
  }
  def prepare[T1 : FfiType, T2 : FfiType, T3 : FfiType, T4 : FfiType, T5 : FfiType, T6 : FfiType, T7 : FfiType, T8 : FfiType, T9 : FfiType, T10 : FfiType, T11 : FfiType, T12 : FfiType, T13 : FfiType, T14 : FfiType, T15 : FfiType, T16 : FfiType, T17 : FfiType, T18 : FfiType, T19 : FfiType, R : FfiType]
  (symbol: String): CallInterface19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R] = {
    val cif = new FfiCif(symbol, FfiType[R], FfiType[T1], FfiType[T2], FfiType[T3], FfiType[T4], FfiType[T5], FfiType[T6], FfiType[T7], FfiType[T8], FfiType[T9], FfiType[T10], FfiType[T11], FfiType[T12], FfiType[T13], FfiType[T14], FfiType[T15], FfiType[T16], FfiType[T17], FfiType[T18], FfiType[T19])
    new CallInterface19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R](cif, fn(symbol))
  }
  def prepare[T1 : FfiType, T2 : FfiType, T3 : FfiType, T4 : FfiType, T5 : FfiType, T6 : FfiType, T7 : FfiType, T8 : FfiType, T9 : FfiType, T10 : FfiType, T11 : FfiType, T12 : FfiType, T13 : FfiType, T14 : FfiType, T15 : FfiType, T16 : FfiType, T17 : FfiType, T18 : FfiType, T19 : FfiType, T20 : FfiType, R : FfiType]
  (symbol: String): CallInterface20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, R] = {
    val cif = new FfiCif(symbol, FfiType[R], FfiType[T1], FfiType[T2], FfiType[T3], FfiType[T4], FfiType[T5], FfiType[T6], FfiType[T7], FfiType[T8], FfiType[T9], FfiType[T10], FfiType[T11], FfiType[T12], FfiType[T13], FfiType[T14], FfiType[T15], FfiType[T16], FfiType[T17], FfiType[T18], FfiType[T19], FfiType[T20])
    new CallInterface20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, R](cif, fn(symbol))
  }
  def prepare[T1 : FfiType, T2 : FfiType, T3 : FfiType, T4 : FfiType, T5 : FfiType, T6 : FfiType, T7 : FfiType, T8 : FfiType, T9 : FfiType, T10 : FfiType, T11 : FfiType, T12 : FfiType, T13 : FfiType, T14 : FfiType, T15 : FfiType, T16 : FfiType, T17 : FfiType, T18 : FfiType, T19 : FfiType, T20 : FfiType, T21 : FfiType, R : FfiType]
  (symbol: String): CallInterface21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, R] = {
    val cif = new FfiCif(symbol, FfiType[R], FfiType[T1], FfiType[T2], FfiType[T3], FfiType[T4], FfiType[T5], FfiType[T6], FfiType[T7], FfiType[T8], FfiType[T9], FfiType[T10], FfiType[T11], FfiType[T12], FfiType[T13], FfiType[T14], FfiType[T15], FfiType[T16], FfiType[T17], FfiType[T18], FfiType[T19], FfiType[T20], FfiType[T21])
    new CallInterface21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, R](cif, fn(symbol))
  }
  def prepare[T1 : FfiType, T2 : FfiType, T3 : FfiType, T4 : FfiType, T5 : FfiType, T6 : FfiType, T7 : FfiType, T8 : FfiType, T9 : FfiType, T10 : FfiType, T11 : FfiType, T12 : FfiType, T13 : FfiType, T14 : FfiType, T15 : FfiType, T16 : FfiType, T17 : FfiType, T18 : FfiType, T19 : FfiType, T20 : FfiType, T21 : FfiType, T22 : FfiType, R : FfiType]
  (symbol: String): CallInterface22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R] = {
    val cif = new FfiCif(symbol, FfiType[R], FfiType[T1], FfiType[T2], FfiType[T3], FfiType[T4], FfiType[T5], FfiType[T6], FfiType[T7], FfiType[T8], FfiType[T9], FfiType[T10], FfiType[T11], FfiType[T12], FfiType[T13], FfiType[T14], FfiType[T15], FfiType[T16], FfiType[T17], FfiType[T18], FfiType[T19], FfiType[T20], FfiType[T21], FfiType[T22])
    new CallInterface22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R](cif, fn(symbol))
  }
}

object Module {
  val RTLD_LAZY     = 0x1
  val RTLD_NOW      = 0x2
  val RTLD_LOCAL    = 0x4
  val RTLD_GLOBAL   = 0x8
  val RTLD_NOLOAD   = 0x10
  val RTLD_NODELETE = 0x80

  def open(path: String = "", mode: CInt = RTLD_LAZY): Module = {
    val PtrWrapper(ptr) =
      Zone { implicit zone =>
        val cstr: CString =
          if (path.nonEmpty) toCString(path)
          else null
        PtrWrapper(dl.dlopen(cstr, mode))
      }
    assert(ptr != null, s"dlopen $path: ${dl.dlerror()}")
    new Module(ptr)
  }
}
