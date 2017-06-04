package com.github.nadavwr.ffi

import scala.scalanative.native._

class FfiType[A] private[ffi](val name: String, size: Long,
                              val alignment: Int, val `type`: FFI_TYPE,
                              val elements: FfiType[_]*) {
  private[ffi] val ptr: FfiTypePtr = {
    val elementsPtrSize = (elements.length+1) * sizeof[Ptr[FfiTypeStruct]]
    val allocated = stdlib.malloc(sizeof[FfiTypeStruct] + elementsPtrSize)
    val ffiTypePtr = allocated.cast[FfiTypePtr]
    val elementsPtrBytes = allocated + sizeof[FfiTypeStruct]
    string.memset(elementsPtrBytes, 0, elementsPtrSize)
    val elementsPtrPtr = elementsPtrBytes.cast[Ptr[FfiTypePtr]]
    var i = 0
    while (i < elements.length) {
      elementsPtrPtr(i) = elements(i).ptr
      i += 1
    }
    !ffiTypePtr._1 = size
    !ffiTypePtr._2 = alignment.toUShort
    !ffiTypePtr._3 = `type`
    !ffiTypePtr._4 = elementsPtrPtr.cast[Ptr[Ptr[Byte]]]
    ffiTypePtr
  }

  private[ffi] def isStruct: Boolean = `type` == FFI_TYPE_STRUCT

  override def toString: String = {
    if (!isStruct) name
    else s"$name " + elements.map(_.name).mkString("{ ", ", ", " }")
  }

  def dispose(): Unit =
    stdlib.free(ptr.cast[Ptr[Byte]])
}

object FfiType {
  def struct[A](name: String, elements: FfiType[_]*): FfiType[A] =
    new FfiType[A](name, 0, 0, FFI_TYPE_STRUCT, elements: _*)

  def apply[A : FfiType]: FfiType[A] = implicitly[FfiType[A]]
}
