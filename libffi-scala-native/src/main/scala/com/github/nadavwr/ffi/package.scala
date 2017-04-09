package com.github.nadavwr

import scala.language.implicitConversions
import scala.scalanative.native._
import scala.scalanative.runtime.GC

package object ffi {

  private[ffi] type FfiTypeStruct = CStruct4[CSize, UShort, FFI_TYPE, Ptr[Ptr[Byte]]]
  private[ffi] type FfiTypePtr = Ptr[FfiTypeStruct]

  private[ffi] type FFI_TYPE = UShort
  private[ffi] val FFI_TYPE_VOID       : FFI_TYPE = 0.toUShort
  //noinspection ScalaUnusedSymbol
  private[ffi] val FFI_TYPE_INT        : FFI_TYPE = 1.toUShort
  private[ffi] val FFI_TYPE_FLOAT      : FFI_TYPE = 2.toUShort
  private[ffi] val FFI_TYPE_DOUBLE     : FFI_TYPE = 3.toUShort
  private[ffi] val FFI_TYPE_UINT8      : FFI_TYPE = 5.toUShort
  private[ffi] val FFI_TYPE_SINT8      : FFI_TYPE = 6.toUShort
  private[ffi] val FFI_TYPE_UINT16     : FFI_TYPE = 7.toUShort
  private[ffi] val FFI_TYPE_SINT16     : FFI_TYPE = 8.toUShort
  private[ffi] val FFI_TYPE_UINT32     : FFI_TYPE = 9.toUShort
  private[ffi] val FFI_TYPE_SINT32     : FFI_TYPE = 10.toUShort
  private[ffi] val FFI_TYPE_UINT64     : FFI_TYPE = 11.toUShort
  private[ffi] val FFI_TYPE_SINT64     : FFI_TYPE = 12.toUShort
  private[ffi] val FFI_TYPE_STRUCT     : FFI_TYPE = 13.toUShort
  private[ffi] val FFI_TYPE_POINTER    : FFI_TYPE = 14.toUShort

  implicit val ffiTypeOfUnit: FfiType[Unit] = new FfiType[Unit]("Unit", 1, 1, FFI_TYPE_VOID)
  implicit def ffiTypeOfPointer[X]: FfiType[Ptr[X]] = new FfiType[Ptr[X]]("Ptr[_]", sizeof[Ptr[_]], sizeof[Ptr[_]].toInt, FFI_TYPE_POINTER)
  implicit val ffiTypeOfCFloat: FfiType[CFloat] = new FfiType[CFloat]("CFloat", 4, 4, FFI_TYPE_FLOAT)
  implicit val ffiTypeOfCDouble: FfiType[CDouble] = new FfiType[CDouble]("CDouble", 8, 8, FFI_TYPE_DOUBLE)
  implicit val ffiTypeOfUByte: FfiType[UByte] = new FfiType[UByte]("UByte", 2, 2, FFI_TYPE_UINT8)
  implicit val ffiTypeOfByte: FfiType[Byte] = new FfiType[Byte]("Byte", 2, 2, FFI_TYPE_SINT8)
  implicit val ffiTypeOfUShort: FfiType[UShort] = new FfiType[UShort]("UShort", 2, 2, FFI_TYPE_UINT16)
  implicit val ffiTypeOfCShort: FfiType[CShort] = new FfiType[CShort]("CShort", 2, 2, FFI_TYPE_SINT16)
  implicit val ffiTypeOfUInt: FfiType[UInt] = new FfiType[UInt]("UInt", 4, 4, FFI_TYPE_UINT32)
  implicit val ffiTypeOfCInt: FfiType[CInt] = new FfiType[CInt]("CInt", 4, 4, FFI_TYPE_SINT32)
  implicit val ffiTypeOfULong: FfiType[ULong] = new FfiType[ULong]("ULong", 8, 8, FFI_TYPE_UINT64)
  implicit val ffiTypeOfCLong: FfiType[CLong] = new FfiType[CLong]("CLong", 8, 8, FFI_TYPE_SINT64)

  private[ffi] type ffi_status = CInt
  private[ffi] val FFI_OK: ffi_status = 0
  private[ffi] val FFI_BAD_TYPEDEF: ffi_status = 1
  private[ffi] val FFI_BAD_ABI: ffi_status = 2

  private[ffi] type ffi_abi = CInt
  private[ffi] val FFI_SYSV: ffi_abi = 1
  private[ffi] val FFI_UNIX64: ffi_abi = 2
  private[ffi] val FFI_DEFAULT_ABI: ffi_abi = FFI_UNIX64

  private[ffi] type FfiCifStruct = CStruct6[ffi_abi, UInt, Ptr[Ptr[FfiTypeStruct]], Ptr[FfiTypeStruct], UInt, UInt]
  private[ffi] type FfiCifPtr = Ptr[FfiCifStruct]

  @extern
  @link("ffi")
  object ffi {
    def ffi_prep_cif(cif: Ptr[FfiCifStruct], abi: ffi_abi, nargs: UInt, rtype: Ptr[FfiTypeStruct], atypes: Ptr[Ptr[FfiTypeStruct]]): ffi_status = extern
    def ffi_call(cif: Ptr[FfiCifStruct], fn: Ptr[_], rvalue: Ptr[_], avalue: Ptr[Ptr[_]]): Unit = extern
  }

  @extern
  @link("dl")
  object dl {
    def dlopen(path: CString, mode: CInt): Ptr[Byte] = extern
    def dlsym(handle: Ptr[Byte], symbol: CString): Ptr[Byte] = extern
    def dlerror(): CString = extern
  }

}
