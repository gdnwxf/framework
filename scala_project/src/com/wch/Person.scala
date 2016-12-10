package com.wch

/**
  * Created by wch on 2016/12/7.
  */
class Person(private var _name :String) {
  def name = _name
  def name_= (aName :String) {_name = aName}
}

object  test{
  def main(args: Array[String]): Unit = {
    print("xxx")
  }

}
