object SortingByLength {

  /*def main(args: Array[String]): Unit = {
    val list = List("1","333","22","4444", "a", "ddd", "bb")
    println(list)
    println(sorting(list))
  }*/

  def sorting(list: List[String], sortedList: List[String] = List()): List[String] = {
    if (list.isEmpty)  {
      sortedList
    } else {
      val shortestStr = getShortestStr(list)
      sorting(list diff List(shortestStr), sortedList ::: List(shortestStr))
    }
  }

  def getShortestStr(list: List[String]):String = {
    list.reduce{(str1:String, str2:String) => if (str1.length > str2.length) str2 else str1}
  }
}