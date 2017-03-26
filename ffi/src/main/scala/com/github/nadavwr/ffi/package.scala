package com.github.nadavwr

import scala.scalanative.native._
import scala.scalanative.runtime.GC

package object ffi {

  type FfiTypeStruct = CStruct4[CSize, UShort, UShort, Ptr[Ptr[Byte]]]
  type FfiType = Ptr[FfiTypeStruct]
  implicit class FfiTypeOps(val ptr: FfiType) extends AnyVal {
    @inlinehint def size: CSize = !ptr._1
    @inlinehint def size_=(size: CSize): Unit = !ptr._1 = size
    @inlinehint def alignment: UShort = !ptr._2
    @inlinehint def alignment_=(alignment: UShort): Unit  = !ptr._2 = alignment
    @inlinehint def `type`: UShort = !ptr._3
    @inlinehint def type_=(`type`: UShort): Unit = !ptr._3 = `type`
    @inlinehint def elements: Ptr[FfiType] = {
      (!ptr._4).cast[Ptr[FfiType]]
    }
    @inlinehint def elements_=(elements: Ptr[FfiType]): Unit = {
      !ptr._4 = elements.cast[Ptr[Ptr[Byte]]]
    }
  }
  object FfiType {
    def struct(size: CSize, elements: FfiType*): FfiType =
      apply(size, 0.toUShort, FFI_TYPE_STRUCT, elements: _*)

    def apply(size: CSize, alignment: UShort, `type`: UShort, elements: FfiType*): FfiType = {
      val elementsPtrSize = (elements.length+1) * sizeof[Ptr[FfiTypeStruct]]
      val allocated = GC.malloc(sizeof[FfiTypeStruct] + elementsPtrSize)
      val instance = allocated.cast[FfiType]
      val elementsPtrBytes = allocated + sizeof[FfiTypeStruct]
      string.memset(elementsPtrBytes, 0, elementsPtrSize)
      val elementsPtrPtr = elementsPtrBytes.cast[Ptr[FfiType]]
      var i = 0
      while (i < elements.length) {
        elementsPtrPtr(i) = elements(i)
        i += 1
      }
      instance.elements = elementsPtrPtr
      instance.size = size
      instance.alignment = alignment
      instance.`type` = `type`
      instance
    }
  }
  type FFI_TYPE = UShort
  val FFI_TYPE_VOID       : FFI_TYPE = 0.toUShort
  val FFI_TYPE_INT        : FFI_TYPE = 1.toUShort
  val FFI_TYPE_FLOAT      : FFI_TYPE = 2.toUShort
  val FFI_TYPE_DOUBLE     : FFI_TYPE = 3.toUShort
  val FFI_TYPE_LONGDOUBLE : FFI_TYPE = FFI_TYPE_DOUBLE
  val FFI_TYPE_UINT8      : FFI_TYPE = 5.toUShort
  val FFI_TYPE_SINT8      : FFI_TYPE = 6.toUShort
  val FFI_TYPE_UINT16     : FFI_TYPE = 7.toUShort
  val FFI_TYPE_SINT16     : FFI_TYPE = 8.toUShort
  val FFI_TYPE_UINT32     : FFI_TYPE = 9.toUShort
  val FFI_TYPE_SINT32     : FFI_TYPE = 10.toUShort
  val FFI_TYPE_UINT64     : FFI_TYPE = 11.toUShort
  val FFI_TYPE_SINT64     : FFI_TYPE = 12.toUShort
  val FFI_TYPE_STRUCT     : FFI_TYPE = 13.toUShort
  val FFI_TYPE_POINTER    : FFI_TYPE = 14.toUShort
  val FFI_TYPE_LAST       : FFI_TYPE = FFI_TYPE_POINTER

  class FfiTypeOf[T](val ffiType: FfiType)
  object FfiTypeOf {
    def apply[A : FfiTypeOf]: FfiType = implicitly[FfiTypeOf[A]].ffiType
    def instance[A](ffiType: FfiType): FfiTypeOf[A] = new FfiTypeOf[A](ffiType)
    def struct[A](size: CSize, elements: FfiType*): FfiTypeOf[A] = new FfiTypeOf[A](FfiType.struct(size, elements: _*))
  }

  implicit val ffiTypeOfPointer: FfiTypeOf[Ptr[_]] =
    FfiTypeOf.instance[Ptr[_]](FfiType(8, 8.toUShort, FFI_TYPE_POINTER))
  implicit val ffiTypeOfDouble: FfiTypeOf[CDouble] =
    FfiTypeOf.instance[CDouble](FfiType(8, 8.toUShort, FFI_TYPE_DOUBLE))
  implicit val ffiTypeOfInt: FfiTypeOf[CInt] =
    FfiTypeOf.instance[CInt](FfiType(4, 4.toUShort, FFI_TYPE_SINT32))

  type ffi_status = CInt
  val FFI_OK: ffi_status = 0
  val FFI_BAD_TYPEDEF: ffi_status = 1
  val FFI_BAD_ABI: ffi_status = 2

  type ffi_abi = CInt
  val FFI_FIRST_ABI: ffi_abi = 0
  val FFI_SYSV: ffi_abi = 1
  val FFI_UNIX64: ffi_abi = 2
  val FFI_DEFAULT_ABI: ffi_abi = FFI_UNIX64
  val FFI_LAST_ABI: ffi_abi = FFI_DEFAULT_ABI + 1

  private type FfiCif = CStruct6[ffi_abi, UInt, Ptr[Ptr[FfiTypeStruct]], Ptr[FfiTypeStruct], UInt, UInt]
  private implicit class FfiCifOps(val cif: Ptr[FfiCif]) extends AnyVal {
    @inlinehint def abi: ffi_abi = !cif._1
    @inlinehint def abi_=(abi: ffi_abi): Unit = !cif._1 = abi
    @inlinehint def nargs: UInt = !cif._2
    @inlinehint def nargs_=(nargs: UInt): Unit = !cif._2 = nargs
    @inlinehint def arg_types: Ptr[Ptr[FfiTypeStruct]] = !cif._3
    @inlinehint def arg_types_=(arg_types: Ptr[Ptr[FfiTypeStruct]]): Unit = !cif._3 = arg_types
    @inlinehint def rtype: Ptr[FfiTypeStruct] = !cif._4
    @inlinehint def rtype_=(rtype: Ptr[FfiTypeStruct]): Unit = !cif._4 = rtype
    @inlinehint def bytes: UInt = !cif._5
    @inlinehint def bytes_=(bytes: UInt): Unit = !cif._5 = bytes
    @inlinehint def flags: UInt = !cif._6
    @inlinehint def flags_=(flags: UInt): Unit = !cif._6 = flags

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
  }

  @extern
  @link("ffi")
  private object ffi {
    def ffi_prep_cif(cif: Ptr[FfiCif], abi: ffi_abi, nargs: UInt, rtype: Ptr[FfiTypeStruct], atypes: Ptr[Ptr[FfiTypeStruct]]): ffi_status = extern
    def ffi_call(cif: Ptr[FfiCif], fn: Ptr[_], rvalue: Ptr[_], avalue: Ptr[Ptr[_]]): Unit = extern
  }

  @extern
  @link("dl")
  object dl {
    def dlopen(path: CString, mode: CInt): Ptr[Byte] = extern
    def dlsym(handle: Ptr[Byte], symbol: CString): Ptr[Byte] = extern
    def dlerror(): CString = extern
  }

  class CallInterface[T1, T2, R](private val cif: Ptr[FfiCif],
                                 private val fn: Ptr[Byte]) {
    def apply(p1: Ptr[T1], p2: Ptr[T2], r: Ptr[R]): Unit = {
      cif.call(fn, r, Array(p1, p2))
    }
  }

  class Module(private val moduleHandle: Ptr[Byte], abi: ffi_abi) {
    def prepare[T1 : FfiTypeOf, T2 : FfiTypeOf, R : FfiTypeOf]
               (symbol: String): CallInterface[T1, T2, R] = {
      val fn: Ptr[Byte] = dl.dlsym(moduleHandle, toCString(symbol))
      val rtype = FfiTypeOf[R]
      val atypes = Array(FfiTypeOf[T1], FfiTypeOf[T2])
      val nargs = atypes.length
      val atypesSize: CInt = ((nargs+1) * sizeof[FfiType]).toInt
      val allocated = GC.malloc(sizeof[FfiCif] + atypesSize)
      val cif = allocated.cast[Ptr[FfiCif]]
      val atypesPtr = (allocated + sizeof[FfiCif]).cast[Ptr[FfiType]]
      var i = 0
      while (i < nargs) {
        val x: Ptr[FfiTypeStruct] = atypes(i)
        atypesPtr.update(i, x)
        i += 1
      }
      atypesPtr(atypes.length) = null
      ffi.ffi_prep_cif(cif, abi, nargs.toUInt, rtype, atypesPtr).ensuring(_ == FFI_OK)
      new CallInterface[T1, T2, R](cif, fn)
    }
  }
  object Module {
    def open(path: String, mode: CInt = 0, abi: ffi_abi = FFI_DEFAULT_ABI): Module = {
      val ptr = dl.dlopen(toCString(path), mode)
      if (ptr == null) throw new RuntimeException(s"dlopen $path: ${dl.dlerror()}")
      new Module(ptr, abi)
    }
  }

}
