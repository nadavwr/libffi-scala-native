package com.github.nadavwr.ffi

import scala.scalanative.native._

class CallInterface(cif: FfiCif, fn: Ptr[Byte]) {
  def name: String = cif.symbol
  override def toString: String = cif.toString
  def call(args: Array[Ptr[_]])(rvalue: Ptr[_]): Unit =
    cif.call(fn, rvalue, args)
}
class CallInterface1[T1, R](val cif: FfiCif, fn: Ptr[Byte]) extends CallInterface(cif, fn) {
  def apply(p1: Ptr[T1])(r: Ptr[R]): Ptr[R] = { cif.call(fn, r, Array(p1)); r }
}

class CallInterface2[T1, T2, R](val cif: FfiCif, fn: Ptr[Byte]) extends CallInterface(cif, fn) {
  def apply(p1: Ptr[T1], p2: Ptr[T2])(r: Ptr[R]): Ptr[R] = { cif.call(fn, r, Array(p1, p2)); r }
}

class CallInterface3[T1, T2, T3, R](val cif: FfiCif, fn: Ptr[Byte]) extends CallInterface(cif, fn) {
  def apply(p1: Ptr[T1], p2: Ptr[T2], p3: Ptr[T3])(r: Ptr[R]): Ptr[R] = { cif.call(fn, r, Array(p1, p2, p3)); r }
}

class CallInterface4[T1, T2, T3, T4, R](val cif: FfiCif, fn: Ptr[Byte]) extends CallInterface(cif, fn) {
  def apply(p1: Ptr[T1], p2: Ptr[T2], p3: Ptr[T3], p4: Ptr[T4])(r: Ptr[R]): Ptr[R] = { cif.call(fn, r, Array(p1, p2, p3, p4)); r }
}

class CallInterface5[T1, T2, T3, T4, T5, R](val cif: FfiCif, fn: Ptr[Byte]) extends CallInterface(cif, fn) {
  def apply(p1: Ptr[T1], p2: Ptr[T2], p3: Ptr[T3], p4: Ptr[T4], p5: Ptr[T5])(r: Ptr[R]): Ptr[R] = { cif.call(fn, r, Array(p1, p2, p3, p4, p5)); r }
}
