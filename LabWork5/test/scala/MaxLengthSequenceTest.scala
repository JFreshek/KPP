import org.scalatest.FunSuite

class MaxLengthSequenceTest extends FunSuite {

  val testList = List(1,2,2,3,3,3)
  val expected = 3

  test("testGetMaxSubseqTailRec") {
    assert(expected.equals(MaxLengthSequence.getMaxSubseqTailRec(testList)))
  }

  test("testGetMaxSubseq") {
    assert(expected.equals(MaxLengthSequence.getMaxSubseq(testList)))
  }



}
