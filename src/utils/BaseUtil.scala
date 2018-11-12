package utils

import org.apache.spark.sql._

//导入对应的规则类，以免出现警告
import scala.language.implicitConversions

/**
  * Created with IntelliJ IDEA.
  * User: lihaoran 
  * Date: 2018/10/29
  * Time: 14:52 
  * Description:
  */
object BaseUtil {
    /**
      * 用来快捷控制代码的开关，将0，1隐式转换为false和true
      *
      * @param a
      * @return
      */
    implicit def int2boolen(a: Int): Boolean = if (a == 0) false else true

    /**
      * DF的装饰类
      *
      * @param dataFrame
      */
    class RichDataFrame(dataFrame: DataFrame){

        /**
          * 用来统计相同key的记录数，常用于调整数据倾斜
          *
          * @param column
          */
        def getKeyNums(column: Column): Unit ={
            val map = dataFrame.select(column).rdd.countByValue()
            println(s"一共${map.size}个key====================")
            for ((key, num) <- map) {
                println(key + "共有" + num + "条记录")
            }
        }

        def getKeyNums(column: String): Unit ={
            getKeyNums(dataFrame.col(column))
        }
    }

     /**
      * 扩展df的方法，隐式转换
      *
      * @param src
      * @return
      */
    implicit def df2RichDF(src: DataFrame): RichDataFrame = new RichDataFrame(src)

    /**
      * 打印map信息
      *
      * 注意泛型
      * Any是abstract类，它是Scala类继承结构中最底层的。
      * 所有运行环境中的Scala类都是直接或间接继承自Any这个类，它就是其它语言（.Net，Java等）中的Object。
      *
      * AnyRef是所有引用类型的基类。除了值类型，所有类型都继承自AnyRef
      * AnyVal所有值类型的基类， 它描述的是值，而不是代表一个对象。
      * @param map
      */
    def printMap(map: Map[_ <: Any, _ <: Any]): Unit = {
        for ((key, value) <- map) {
            println(key.toString + " : " + value.toString)
        }
        println("===================")
    }



}