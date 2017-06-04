package com.github.nadavwr.ffi

import scala.scalanative.native._

class FfiCif(val symbol: String, returnType: FfiType[_], parameterTypes: FfiType[_]*) {

  override def toString: String =
    s"${returnType.name} $symbol" + parameterTypes.map(_.name).mkString("(", ", ", ")")

  private[ffi] val cif: FfiCifPtr = {
    val nargs = parameterTypes.length
    val atypesSize: CInt = ((nargs+1) * sizeof[FfiTypePtr]).toInt
    val allocated = stdlib.malloc(sizeof[FfiCifStruct] + atypesSize)
    val cif = allocated.cast[Ptr[FfiCifStruct]]
    val atypesPtr = (allocated + sizeof[FfiCifStruct]).cast[Ptr[FfiTypePtr]]
    var i = 0
    while (i < nargs) {
      val x: Ptr[FfiTypeStruct] = parameterTypes(i).ptr
      atypesPtr.update(i, x)
      i += 1
    }
    atypesPtr(parameterTypes.length) = null
    val status = ffi.ffi_prep_cif(cif, FFI_DEFAULT_ABI, nargs.toUInt, returnType.ptr, atypesPtr)
    assert(status == FFI_OK, {
      val description = status match {
        case FFI_BAD_ABI => "FFI_BAD_ABI"
        case FFI_BAD_TYPEDEF => "FFI_BAD_TYPEDEF"
        case other => s"unrecognized ($other)"
      }
      s"ffi_prep_cif: error code $description encountered while preparing $this"
    })
    cif
  }

  def call(fn: Ptr[Byte], rvalue: Ptr[_], args: Array[Ptr[_]]): Unit = {
    val ptrSize = sizeof[Ptr[_]].toInt
    val argsPtr: Ptr[Ptr[_]] = stackalloc[Ptr[_]]((args.length+1)*ptrSize)
    var i = 0
    while (i < args.length) {
      val arg: Ptr[_] = args(i)
      argsPtr.update(i, arg)
      i += 1
    }
    argsPtr(args.length) = null
    ffi.ffi_call(cif, fn, rvalue, argsPtr)
  }

  def dispose(): Unit =
    stdlib.free(cif.cast[Ptr[Byte]])
}
