package com.github.nadavwr

import scala.scalanative.native._
import scala.scalanative.runtime.GC
import com.github.nadavwr.mempool._

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
    @inlinehint def elements: Ptr[Ptr[FfiTypeStruct]] = {
      val x: Ptr[Ptr[Byte]] = !ptr._4
      x.cast[Ptr[Ptr[FfiTypeStruct]]]
    }
    @inlinehint def elements_=(elements: Ptr[Ptr[FfiTypeStruct]]): Unit = {
      val x: Ptr[Ptr[Byte]] = elements.cast[Ptr[Ptr[Byte]]]
      !ptr._4 = x
    }
  }
  object FfiType {
    implicit lazy val sizedFfiTypeStruct: Sized[FfiTypeStruct] =
      Sized.instance[FfiTypeStruct](sizeof[FfiTypeStruct])

    def struct(elements: FfiType*)
              (implicit pool: Pool = Pool.defaultHeap): FfiType =
      apply(0, 0.toUShort, FFI_TYPE_STRUCT, elements.toArray)

    def apply(size: CSize, alignment: UShort, `type`: UShort, elements: Array[FfiType] = Array.empty[FfiType])
             (implicit pool: Pool = Pool.defaultHeap): FfiType = {
      val instance = pool.malloc[FfiTypeStruct]
      val elementsPtrSize = (elements.length+1) * sizeof[Ptr[FfiTypeStruct]]
      val elementsPtrBytes = pool.malloc[Byte](elementsPtrSize)
      string.memset(elementsPtrBytes, 0, elementsPtrSize)
      val elementsPtrPtr = elementsPtrBytes.cast[Ptr[Ptr[FfiTypeStruct]]]
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

    def unapply(ptr: Ptr[FfiTypeStruct]): Option[(CSize, UShort, UShort, Ptr[Ptr[FfiTypeStruct]])] =
      Some(ptr.size, ptr.alignment, ptr.`type`, ptr.elements)
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

  lazy val ffi_type_pointer: Ptr[FfiTypeStruct] = FfiType(8, 8.toUShort, FFI_TYPE_POINTER)
  lazy val ffi_type_sint32: Ptr[FfiTypeStruct] = FfiType(4, 4.toUShort, FFI_TYPE_SINT32)
  lazy val ffi_type_double: Ptr[FfiTypeStruct] = FfiType(8, 8.toUShort, FFI_TYPE_DOUBLE)




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

  type CallInterfaceStruct = CStruct6[ffi_abi, UInt, Ptr[Ptr[FfiTypeStruct]], Ptr[FfiTypeStruct], UInt, UInt]
  type CallInterface = Ptr[CallInterfaceStruct]
  implicit class CallInterfaceOps(val cif: CallInterface) extends AnyVal {
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

    def call(fn: Ptr[Byte], rvalue: Ptr[_], args: Array[Ptr[_]])
            (implicit pool: Pool = Pool.defaultHeap): rvalue.type = {
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
      rvalue
    }
  }
  object CallInterface {
    implicit lazy val sizedCif: Sized[CallInterfaceStruct] =
      Sized.instance[CallInterfaceStruct](sizeof[CallInterfaceStruct])

    def prepare(abi: ffi_abi, rtype: FfiType, atypes: Array[FfiType])
               (implicit pool: Pool = Pool.defaultHeap): CallInterface = {
      val cif = pool.malloc[CallInterfaceStruct]
      val nargs = atypes.length
      val atypesPtr = pool.malloc((nargs+1) * sizeof[Ptr[FfiTypeStruct]]).cast[Ptr[Ptr[FfiTypeStruct]]]
      var i = 0
      while (i < nargs) {
        val x: Ptr[FfiTypeStruct] = atypes(i)
        atypesPtr.update(i, x)
        i += 1
      }
      atypesPtr(atypes.length) = null
      ffi.ffi_prep_cif(cif, abi, nargs.toUInt, rtype, atypesPtr).ensuring(_ == FFI_OK)
      cif
    }

    def unapply(ptr: Ptr[CallInterfaceStruct]): Option[(ffi_abi, UInt, Ptr[FfiType], FfiType, UInt, UInt)] =
      Some(ptr.abi, ptr.nargs, ptr.arg_types, ptr.rtype, ptr.bytes, ptr.flags)
  }

  @extern
  @link("ffi")
  private object ffi {
    def ffi_prep_cif(cif: Ptr[CallInterfaceStruct], abi: ffi_abi, nargs: UInt, rtype: Ptr[FfiTypeStruct], atypes: Ptr[Ptr[FfiTypeStruct]]): ffi_status = extern
    def ffi_call(cif: Ptr[CallInterfaceStruct], fn: Ptr[_], rvalue: Ptr[_], avalue: Ptr[Ptr[_]]): Unit = extern
  }

  @extern
  @link("dl")
  object dl {
    def dlopen(path: CString, mode: CInt): Ptr[Byte] = extern
    def dlsym(handle: Ptr[Byte], symbol: CString): Ptr[Byte] = extern
    def dlerror(): CString = extern
  }

  object Module {
    def apply(path: CString, mode: CInt = 0): Ptr[Byte] = {
      val ptr = dl.dlopen(path, mode)
      if (ptr == null) throw new RuntimeException(s"dlopen $path: ${dl.dlerror()}")
      ptr
    }
  }

}
