import scala.annotation.tailrec

object MaxLengthSequence {

  /*def main(args: Array[String]): Unit = {
    val sequence = List(1,2,3,3,2,2,2,3)
    println(sequence);
    val resStr = "Max length subsequence: "
    print(resStr)
    println(getMaxSubseq(sequence))
    print(resStr)
    println(getMaxSubseqTailRec(sequence))
  }*/

  def getMaxSubseq(sequence: List[Int], i:Int = 0, res:Int = 1, counter:Int = 1, tmp:Int = 0):Int = {
    if (i == sequence.length) {
      if (counter > res) counter + 1 - res
      else 1
    }
    else if (i != 0 && sequence(i - 1).equals(sequence(i))) {
      if (counter + 1 - res > 0) {
        getMaxSubseq(sequence, i + 1, res, counter + 1, counter + 1 - res)
      } else {
        getMaxSubseq(sequence, i + 1, res, counter + 1, 0)
      }
    }
    else {
      if (counter > res) {
        tmp + getMaxSubseq(sequence, i + 1, counter, 1, 0)
      } else {
        getMaxSubseq(sequence, i + 1, res, 1, 0)
      }
    }
  }

  @tailrec
  def getMaxSubseqTailRec(sequence: List[Int], i:Int = 0, res:Int = 1, counter:Int = 1): Int = {
    if (i == sequence.length) {
      if (counter > res) counter
      else res
    }
    else if (i != 0 && sequence(i - 1).equals(sequence(i))) {
      getMaxSubseqTailRec(sequence, i + 1, res, counter + 1)
    }
    else {
      if (counter > res) {
        getMaxSubseqTailRec(sequence, i + 1, counter, 1)
      } else {
        getMaxSubseqTailRec(sequence, i + 1, res, 1)
      }
    }
  }
}