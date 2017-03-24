package com.github.nadavwr.ffi

import com.github.nadavwr.mempool._

import scala.scalanative.native._
import scala.scalanative.runtime.{GC, Type}
import dl._

object Sample extends App {
  type cpVect = CStruct2[CDouble, CDouble]
  implicit class cpVectOps(val ptr: Ptr[cpVect]) extends AnyVal {
    def x: CDouble = !ptr._1
    def x_=(x: CDouble): Unit = !ptr._1 = x
    def y: CDouble = !ptr._2
    def y_=(y: CDouble): Unit = !ptr._2 = y
    def :=(other: Ptr[cpVect]): Unit = {
      x = other.x
      y = other.y
    }
    override def toString: String = s"cpVect(x:$x,y:$y)"
  }
  object cpVect {
    implicit lazy val sizedCpVect: Sized[cpVect] = Sized.instance[cpVect](sizeof[cpVect])
    def apply(x: CDouble, y: CDouble)(implicit pool: Pool): Ptr[cpVect] = {
      val instance = pool.malloc[cpVect]
      instance.x = x
      instance.y = y
      instance
    }
    def unapply(ptr: Ptr[cpVect]): Option[(CDouble, CDouble)] =
      Some(ptr.x, ptr.y)
  }

  val moduleHandle: Ptr[Byte] = dlopen(c"libchipmunk.dylib", 0)
  assert(moduleHandle != null)

  val ffi_type_cpVect: FfiType =
    FfiType.struct(ffi_type_double, ffi_type_double)

  object cpAreaForSegment /*extends ((Ptr[cpVect], Ptr[cpVect], CDouble) => CDouble)*/ {
    private val handle: Ptr[Byte] = dlsym(moduleHandle, c"cpAreaForSegment")
    private val cif = CallInterface.prepare(FFI_DEFAULT_ABI, ffi_type_double,
      Array(ffi_type_cpVect, ffi_type_cpVect, ffi_type_double))


    def apply(a: Ptr[cpVect], b: Ptr[cpVect], radius: CDouble)
             (implicit pool: Pool = Pool.defaultHeap): CDouble = {
      val radiusPtr = stackalloc[CDouble]; !radiusPtr = radius
      !cif.call(handle, pool.malloc[CDouble], Array(a, b, radiusPtr))
    }
  }

  object cpAreaForPoly /*extends ((CInt, Ptr[cpVect], CDouble) => CDouble)*/ {
    private val handle: Ptr[Byte] = dlsym(moduleHandle, c"cpAreaForPoly")
    private val cif = CallInterface.prepare(FFI_DEFAULT_ABI, ffi_type_double,
      Array(ffi_type_sint32, ffi_type_pointer, ffi_type_double))

    def apply(count: CInt, verts: Ptr[cpVect], radius: CDouble)
             (implicit pool: Pool = Pool.defaultHeap): CDouble = {
      val countPtr = stackalloc[CInt]; !countPtr = count
      val vertsPtr = stackalloc[Ptr[cpVect]]; !vertsPtr = verts
      val radiusPtr = stackalloc[CDouble]; !radiusPtr = radius
      !cif.call(handle, pool.malloc[CDouble], Array(countPtr, vertsPtr, radiusPtr))
    }
  }

  implicit lazy val pool = Pool.managed(10240)
  val polygon = stackalloc[cpVect](4)
  (polygon + 0) := cpVect(0, 0)
  (polygon + 1) := cpVect(2, 0)
  (polygon + 2) := cpVect(1, 1)
  (polygon + 3) := cpVect(0, 1)

  val result = cpAreaForPoly(4, polygon, radius = 0)
  pool.dispose()

  println("result = " + result)
}
