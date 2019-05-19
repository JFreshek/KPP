import org.scalatest.FunSuite

class SortingByLengthTest extends FunSuite {

  test("testGetShortestStr") {
    val testList = List("not your result", "shortest", "some big string")
    val expectedStr = "shortest"
    assert(expectedStr.equals(SortingByLength.getShortestStr(testList)))
  }

  test("testSorting") {
    val testList = List("a", "ccc", "bb", "dddd")
    val expectedList = List("a", "bb", "ccc", "dddd")
    assert(expectedList.equals(SortingByLength.sorting(testList)))
  }

}
