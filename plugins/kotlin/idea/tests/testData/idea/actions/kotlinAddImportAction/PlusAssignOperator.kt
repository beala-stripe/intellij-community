// EXPECT_VARIANT_IN_ORDER "public operator fun <T> pack.Arr<T>.plusAssign(element: T): kotlin.Unit defined in pack in file PlusAssignOperator.dependency.kt"
// EXPECT_VARIANT_IN_ORDER "public operator fun <T> kotlin.Array<T>.plus(element: T): kotlin.Array<T> defined in kotlin.collections"
// WITH_STDLIB
package other

import pack.Arr
fun foo(arr: Arr<Int>) {
    arr<caret> += 21
}