package com.github.nadavwr.ffi

import scala.scalanative.native._

class CallInterface(cif: FfiCif, fn: Ptr[Byte]) {
  def name: String = cif.symbol
  override def toString: String = cif.toString
  def call(args: Array[Ptr[_]])(rvalue: Ptr[_]): Unit =
    cif.call(fn, rvalue, args)
}

class CallInterface1[T1, R](val cif: FfiCif, fn: Ptr[Byte]) extends CallInterface(cif, fn) {
  def apply(p1: Ptr[T1])(r: Ptr[R]): Ptr[R] = {
    cif.call(fn, r, Array(p1)); r
  }
}

class CallInterface2[T1, T2, R](val cif: FfiCif, fn: Ptr[Byte]) extends CallInterface(cif, fn) {
  def apply(p1: Ptr[T1], p2: Ptr[T2])(r: Ptr[R]): Ptr[R] = {
    cif.call(fn, r, Array(p1, p2)); r
  }
}

class CallInterface3[T1, T2, T3, R](val cif: FfiCif, fn: Ptr[Byte]) extends CallInterface(cif, fn) {
  def apply(p1: Ptr[T1], p2: Ptr[T2], p3: Ptr[T3])(r: Ptr[R]): Ptr[R] = {
    cif.call(fn, r, Array(p1, p2, p3)); r
  }
}

class CallInterface4[T1, T2, T3, T4, R](val cif: FfiCif, fn: Ptr[Byte]) extends CallInterface(cif, fn) {
  def apply(p1: Ptr[T1], p2: Ptr[T2], p3: Ptr[T3], p4: Ptr[T4])(r: Ptr[R]): Ptr[R] = {
    cif.call(fn, r, Array(p1, p2, p3, p4)); r
  }
}

class CallInterface5[T1, T2, T3, T4, T5, R](val cif: FfiCif, fn: Ptr[Byte]) extends CallInterface(cif, fn) {
  def apply(p1: Ptr[T1], p2: Ptr[T2], p3: Ptr[T3], p4: Ptr[T4], p5: Ptr[T5])(r: Ptr[R]): Ptr[R] = {
    cif.call(fn, r, Array(p1, p2, p3, p4, p5)); r
  }
}

class CallInterface6[T1, T2, T3, T4, T5, T6, R](val cif: FfiCif, fn: Ptr[Byte]) extends CallInterface(cif, fn) {
   def apply(p1: Ptr[T1], p2: Ptr[T2], p3: Ptr[T3], p4: Ptr[T4], p5: Ptr[T5], p6: Ptr[T6])(r: Ptr[R]): Ptr[R] = {
     cif.call(fn, r, Array(p1, p2, p3, p4, p5, p6)); r
   }
}

class CallInterface7[T1, T2, T3, T4, T5, T6, T7, R](val cif: FfiCif, fn: Ptr[Byte]) extends CallInterface(cif, fn) {
  def apply(p1: Ptr[T1], p2: Ptr[T2], p3: Ptr[T3], p4: Ptr[T4], p5: Ptr[T5], p6: Ptr[T6], p7: Ptr[T7])(r: Ptr[R]): Ptr[R] = {
    cif.call(fn, r, Array(p1, p2, p3, p4, p5, p6, p7)); r
  }
}

class CallInterface8[T1, T2, T3, T4, T5, T6, T7, T8, R](val cif: FfiCif, fn: Ptr[Byte]) extends CallInterface(cif, fn) {
  def apply(p1: Ptr[T1], p2: Ptr[T2], p3: Ptr[T3], p4: Ptr[T4], p5: Ptr[T5], p6: Ptr[T6], p7: Ptr[T7], p8: Ptr[T8])(r: Ptr[R]): Ptr[R] = {
    cif.call(fn, r, Array(p1, p2, p3, p4, p5, p6, p7, p8)); r
  }
}

class CallInterface9[T1, T2, T3, T4, T5, T6, T7, T8, T9, R](val cif: FfiCif, fn: Ptr[Byte]) extends CallInterface(cif, fn) {
  def apply(p1: Ptr[T1], p2: Ptr[T2], p3: Ptr[T3], p4: Ptr[T4], p5: Ptr[T5], p6: Ptr[T6], p7: Ptr[T7], p8: Ptr[T8], p9: Ptr[T9])(r: Ptr[R]): Ptr[R] = {
    cif.call(fn, r, Array(p1, p2, p3, p4, p5, p6, p7, p8, p9)); r
  }
}

class CallInterface10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R](val cif: FfiCif, fn: Ptr[Byte]) extends CallInterface(cif, fn) {
  def apply(p1: Ptr[T1], p2: Ptr[T2], p3: Ptr[T3], p4: Ptr[T4], p5: Ptr[T5], p6: Ptr[T6], p7: Ptr[T7], p8: Ptr[T8], p9: Ptr[T9], p10: Ptr[T10])(r: Ptr[R]): Ptr[R] = {
    cif.call(fn, r, Array(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10)); r
  }
}

class CallInterface11[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R](val cif: FfiCif, fn: Ptr[Byte]) extends CallInterface(cif, fn) {
  def apply(p1: Ptr[T1], p2: Ptr[T2], p3: Ptr[T3], p4: Ptr[T4], p5: Ptr[T5], p6: Ptr[T6], p7: Ptr[T7], p8: Ptr[T8], p9: Ptr[T9], p10: Ptr[T10], p11: Ptr[T11])(r: Ptr[R]): Ptr[R] = {
    cif.call(fn, r, Array(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11)); r
  }
}

class CallInterface12[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R](val cif: FfiCif, fn: Ptr[Byte]) extends CallInterface(cif, fn) {
  def apply(p1: Ptr[T1], p2: Ptr[T2], p3: Ptr[T3], p4: Ptr[T4], p5: Ptr[T5], p6: Ptr[T6], p7: Ptr[T7], p8: Ptr[T8], p9: Ptr[T9], p10: Ptr[T10], p11: Ptr[T11], p12: Ptr[T12])(r: Ptr[R]): Ptr[R] = {
    cif.call(fn, r, Array(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12)); r
  }
}

class CallInterface13[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R](val cif: FfiCif, fn: Ptr[Byte]) extends CallInterface(cif, fn) {
  def apply(p1: Ptr[T1], p2: Ptr[T2], p3: Ptr[T3], p4: Ptr[T4], p5: Ptr[T5], p6: Ptr[T6], p7: Ptr[T7], p8: Ptr[T8], p9: Ptr[T9], p10: Ptr[T10], p11: Ptr[T11], p12: Ptr[T12], p13: Ptr[T13])(r: Ptr[R]): Ptr[R] = {
    cif.call(fn, r, Array(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13)); r
  }
}

class CallInterface14[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R](val cif: FfiCif, fn: Ptr[Byte]) extends CallInterface(cif, fn) {
  def apply(p1: Ptr[T1], p2: Ptr[T2], p3: Ptr[T3], p4: Ptr[T4], p5: Ptr[T5], p6: Ptr[T6], p7: Ptr[T7], p8: Ptr[T8], p9: Ptr[T9], p10: Ptr[T10], p11: Ptr[T11], p12: Ptr[T12], p13: Ptr[T13], p14: Ptr[T14])(r: Ptr[R]): Ptr[R] = {
    cif.call(fn, r, Array(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14)); r
  }
}

class CallInterface15[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R](val cif: FfiCif, fn: Ptr[Byte]) extends CallInterface(cif, fn) {
  def apply(p1: Ptr[T1], p2: Ptr[T2], p3: Ptr[T3], p4: Ptr[T4], p5: Ptr[T5], p6: Ptr[T6], p7: Ptr[T7], p8: Ptr[T8], p9: Ptr[T9], p10: Ptr[T10], p11: Ptr[T11], p12: Ptr[T12], p13: Ptr[T13], p14: Ptr[T14], p15: Ptr[T15])(r: Ptr[R]): Ptr[R] = {
    cif.call(fn, r, Array(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15)); r
  }
}

class CallInterface16[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R](val cif: FfiCif, fn: Ptr[Byte]) extends CallInterface(cif, fn) {
  def apply(p1: Ptr[T1], p2: Ptr[T2], p3: Ptr[T3], p4: Ptr[T4], p5: Ptr[T5], p6: Ptr[T6], p7: Ptr[T7], p8: Ptr[T8], p9: Ptr[T9], p10: Ptr[T10], p11: Ptr[T11], p12: Ptr[T12], p13: Ptr[T13], p14: Ptr[T14], p15: Ptr[T15], p16: Ptr[T16])(r: Ptr[R]): Ptr[R] = {
    cif.call(fn, r, Array(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16)); r
  }
}

class CallInterface17[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R](val cif: FfiCif, fn: Ptr[Byte]) extends CallInterface(cif, fn) {
  def apply(p1: Ptr[T1], p2: Ptr[T2], p3: Ptr[T3], p4: Ptr[T4], p5: Ptr[T5], p6: Ptr[T6], p7: Ptr[T7], p8: Ptr[T8], p9: Ptr[T9], p10: Ptr[T10], p11: Ptr[T11], p12: Ptr[T12], p13: Ptr[T13], p14: Ptr[T14], p15: Ptr[T15], p16: Ptr[T16], p17: Ptr[T17])(r: Ptr[R]): Ptr[R] = {
    cif.call(fn, r, Array(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17)); r
  }
}

class CallInterface18[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R](val cif: FfiCif, fn: Ptr[Byte]) extends CallInterface(cif, fn) {
  def apply(p1: Ptr[T1], p2: Ptr[T2], p3: Ptr[T3], p4: Ptr[T4], p5: Ptr[T5], p6: Ptr[T6], p7: Ptr[T7], p8: Ptr[T8], p9: Ptr[T9], p10: Ptr[T10], p11: Ptr[T11], p12: Ptr[T12], p13: Ptr[T13], p14: Ptr[T14], p15: Ptr[T15], p16: Ptr[T16], p17: Ptr[T17], p18: Ptr[T18])(r: Ptr[R]): Ptr[R] = {
    cif.call(fn, r, Array(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18)); r
  }
}

class CallInterface19[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R](val cif: FfiCif, fn: Ptr[Byte]) extends CallInterface(cif, fn) {
  def apply(p1: Ptr[T1], p2: Ptr[T2], p3: Ptr[T3], p4: Ptr[T4], p5: Ptr[T5], p6: Ptr[T6], p7: Ptr[T7], p8: Ptr[T8], p9: Ptr[T9], p10: Ptr[T10], p11: Ptr[T11], p12: Ptr[T12], p13: Ptr[T13], p14: Ptr[T14], p15: Ptr[T15], p16: Ptr[T16], p17: Ptr[T17], p18: Ptr[T18], p19: Ptr[T19])(r: Ptr[R]): Ptr[R] = {
    cif.call(fn, r, Array(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19)); r
  }
}

class CallInterface20[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, R](val cif: FfiCif, fn: Ptr[Byte]) extends CallInterface(cif, fn) {
  def apply(p1: Ptr[T1], p2: Ptr[T2], p3: Ptr[T3], p4: Ptr[T4], p5: Ptr[T5], p6: Ptr[T6], p7: Ptr[T7], p8: Ptr[T8], p9: Ptr[T9], p10: Ptr[T10], p11: Ptr[T11], p12: Ptr[T12], p13: Ptr[T13], p14: Ptr[T14], p15: Ptr[T15], p16: Ptr[T16], p17: Ptr[T17], p18: Ptr[T18], p19: Ptr[T19], p20: Ptr[T20])(r: Ptr[R]): Ptr[R] = {
    cif.call(fn, r, Array(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20)); r
  }
}

class CallInterface21[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, R](val cif: FfiCif, fn: Ptr[Byte]) extends CallInterface(cif, fn) {
  def apply(p1: Ptr[T1], p2: Ptr[T2], p3: Ptr[T3], p4: Ptr[T4], p5: Ptr[T5], p6: Ptr[T6], p7: Ptr[T7], p8: Ptr[T8], p9: Ptr[T9], p10: Ptr[T10], p11: Ptr[T11], p12: Ptr[T12], p13: Ptr[T13], p14: Ptr[T14], p15: Ptr[T15], p16: Ptr[T16], p17: Ptr[T17], p18: Ptr[T18], p19: Ptr[T19], p20: Ptr[T20], p21: Ptr[T21])(r: Ptr[R]): Ptr[R] = {
    cif.call(fn, r, Array(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21)); r
  }
}

class CallInterface22[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R](val cif: FfiCif, fn: Ptr[Byte]) extends CallInterface(cif, fn) {
  def apply(p1: Ptr[T1], p2: Ptr[T2], p3: Ptr[T3], p4: Ptr[T4], p5: Ptr[T5], p6: Ptr[T6], p7: Ptr[T7], p8: Ptr[T8], p9: Ptr[T9], p10: Ptr[T10], p11: Ptr[T11], p12: Ptr[T12], p13: Ptr[T13], p14: Ptr[T14], p15: Ptr[T15], p16: Ptr[T16], p17: Ptr[T17], p18: Ptr[T18], p19: Ptr[T19], p20: Ptr[T20], p21: Ptr[T21], p22: Ptr[T22])(r: Ptr[R]): Ptr[R] = {
    cif.call(fn, r, Array(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22)); r
  }
}
