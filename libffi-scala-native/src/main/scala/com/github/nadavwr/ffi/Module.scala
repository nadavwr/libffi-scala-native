package com.github.nadavwr.ffi

import scala.scalanative.native._

class Module(private val moduleHandle: Ptr[Byte]) {
  def prepare[T1 : FfiType, R : FfiType]
             (symbol: String): CallInterface1[T1, R] = {
    val fn = dl.dlsym(moduleHandle, toCString(symbol))
    val cif = new FfiCif(symbol, FfiType[R], FfiType[T1])
    new CallInterface1[T1, R](cif, fn)
  }

  def prepare[T1 : FfiType, T2 : FfiType, R : FfiType]
             (symbol: String): CallInterface2[T1, T2, R] = {
    val fn = dl.dlsym(moduleHandle, toCString(symbol))
    val cif = new FfiCif(symbol, FfiType[R], FfiType[T1], FfiType[T2])
    new CallInterface2[T1, T2, R](cif, fn)
  }

  def prepare[T1 : FfiType, T2 : FfiType, T3 : FfiType, R : FfiType]
             (symbol: String): CallInterface3[T1, T2, T3, R] = {
    val fn = dl.dlsym(moduleHandle, toCString(symbol))
    val cif = new FfiCif(symbol, FfiType[R], FfiType[T1], FfiType[T2], FfiType[T3])
    new CallInterface3[T1, T2, T3, R](cif, fn)
  }

  def prepare[T1 : FfiType, T2 : FfiType, T3 : FfiType, T4 : FfiType, R : FfiType]
             (symbol: String): CallInterface4[T1, T2, T3, T4, R] = {
    val fn = dl.dlsym(moduleHandle, toCString(symbol))
    val cif = new FfiCif(symbol, FfiType[R], FfiType[T1], FfiType[T2], FfiType[T3], FfiType[T4])
    new CallInterface4[T1, T2, T3, T4, R](cif, fn)
  }

  def prepare[T1 : FfiType, T2 : FfiType, T3 : FfiType, T4 : FfiType, T5 : FfiType, R : FfiType]
             (symbol: String): CallInterface5[T1, T2, T3, T4, T5, R] = {
    val fn = dl.dlsym(moduleHandle, toCString(symbol))
    val cif = new FfiCif(symbol, FfiType[R], FfiType[T1], FfiType[T2], FfiType[T3], FfiType[T4], FfiType[T5])
    new CallInterface5[T1, T2, T3, T4, T5, R](cif, fn)
  }
}

object Module {
  def open(path: String, mode: CInt = 0): Module = {
    val ptr = dl.dlopen(toCString(path), mode)
    assert(ptr != null, s"dlopen $path: ${dl.dlerror()}")
    new Module(ptr)
  }
}
