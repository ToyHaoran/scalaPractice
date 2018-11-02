package scalademo

import utils.BaseUtil.int2boolen

// 序列是继承自特质Seq的类，可以处理一组线性分布的数据。元素是有序的。
object ArrayDemo extends App {

    // 数组能够让你保留一组元素序列并可以用基于0的索引高效访问（无论是获取还是添加）处于任意位置的元素。
    val 不可变数组 = 0
    if (1){
        println("不可变（定长）数组===========")
        println("初始化数组=======")
        val nums = new Array[Int](10) //初始化为0
        val nums1 = new Array[String](10) //初始化为null
        val nums2 = Array(1, 2, 4, 3, 2, 4) //调用apply方法
        val nums3 = Array("hello", "world")
        println("返回长度为0的数组=====")
        Array.empty[Int]
        println("创建区间数组======")
        Array.range(0, 10) // 不包括最后一个，Array[Int] = Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        Array.range(0, 10, 2) //间隔为2   Array[Int] = Array(0, 2, 4, 6, 8)
        println("填充数组======")
        //返回数组，长度为第一个参数指定，同时每个元素使用第二个参数进行填充。
        Array.fill(3)(math.random) // Array[Double] = Array(0.47204059555458355, 0.4619077488315301, 0.02781911462725528)
        Array.fill(2, 3)("11") // Array[Array[String]] = Array(Array(11, 11, 11), Array(11, 11, 11))

        println("利用函数创建数组======")
        //返回：start, f(start), f(f(start)), ... 初始值可以自定义
        Array.iterate(0, 3)(a => a + 5) //Array[Int] = Array(0, 5, 10)   初始值为 0，长度为 3，计算函数为a=>a+5:
        Array.iterate(2, 3)(a => a * 5) // Array[Int] = Array(2, 10, 50)
        //返回：f(0),f(1), ..., f(n - 1)       a可以当做下标。
        Array.tabulate(3)(a => a + 5) //Array[Int] = Array(5, 6, 7)
        Array.tabulate(3)(a => a * 5) //Array[Int] = Array(0, 5, 10)
        //多维的更复杂，不常用（可以看源码）
        Array.tabulate(3, 4)((a, b) => a + b) //下标相加
        Array.tabulate(3, 4)(_ + _) //Array[Array[Int]] = Array(Array(0, 1, 2, 3), Array(1, 2, 3, 4), Array(2, 3, 4, 5))

        println("访问数组========")
        //(使用小括号访问)
        nums3(0)
        nums3.apply(1)
        println(nums3)

        println("修改数组=======")
        nums3(1) = "every one"
        nums3.update(1, "every one")

        println("数组长度=====")
        nums.length//数组长度

        println("复制数组=====")
        nums.clone() //克隆数组
        //复制一个数组到另一个数组：从num2的下标2开始复制3个到nums的下标4开始（注意不要越界）
        Array.copy(nums2, 2, nums, 4, 3)

        println("合并同类型数组=========")
        Array.concat(nums, nums2)

        println("多维数组======")
        //创建规则三维数组,赋值1-27
        val x = Array.ofDim[Int](3, 3, 3)
        var idx = 1
        for (i <- x.indices) {
            for (j <- x(i).indices) {
                for (k <- x(i)(j).indices) {
                    x(i)(j)(k) = idx
                    idx += 1
                }
            }
        }

        println("遍历三维数组====")
        for (i <- x) {
            //x是三维数组，i是二维数组
            for (j <- i) {
                //j是一维数组，只有j可以用mkString
                println(j.mkString(" "))
                j.foreach(arg => print(arg * 10 + " "))
            }
        }
        //创建不规则多维数组
        val x2 = new Array[Array[Int]](10)
        for (i <- x2.indices) {
            x2(i) = new Array[Int](i + 1)
        }
    }

    val 可变数组 = 0
    if(1){
        //———————————————————————可变数组：数组缓冲—————————————————————————————————
        println("创建数组缓冲，增删改查操作=========")
        import scala.collection.mutable.ArrayBuffer
        val b = ArrayBuffer[Int]()
        b += 1 //在末尾添加元素
        b +=(2, 3, 4, 5) //添加集合
        b ++= Array(6, 7, 8, 9) //必须用++=追加任何集合
        b.trimEnd(2) //移除后面2个元素，高效操作
        b.insert(2, 11) //在下标2位置添加11
        b.remove(5) //移除下标5
        b -= 7 //移除元素7（一个一个删除，从前往后）
        b.remove(3, 2) //从下标3开始移除2个

        println("查看和修改元素========")
        b(1)
        b(2) = 3

        b.toArray.toBuffer//相互转化

        println("常用算法,重点=======")
        b.sum
        b.count(a=>a+3==7)
        b.reverse
        b.sorted
        b.foreach(println(_))
    }
}

object ListDemo extends App{
    //列表支持在头部快速添加和删除，但是不能提供对任意索引值的快速引用，因为这种实现需要线性枚举整个列表
    val 不可变列表 = 0
    if(0){
        println("创建列表===========")
        // (注意列表是不可变的，只能用::来添加元素)
        val site: List[String] = List("Runoob", "Google", "Baidu")
        val site1 = "Runoob" :: ("Google" :: ("Baidu" :: Nil)) //构造列表，底层代码，::右结合
        val nums: List[Int] = List(1, 2, 3, 4)
        val nums2: List[Int] = List(5, 6, 7, 8)
        val empty: List[Nothing] = List()
        val dim: List[List[Int]] = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1)) //二维列表
        //以下操作参考Array
        List.range(0, 10, 2)
        List.fill(10)("hello")
        List.iterate(2, 5)(a => a * 2) //List[Int] = List(2, 4, 8, 16, 32)
        List.tabulate(5)(a => a * 2) // List[Int] = List(0, 2, 4, 6, 8)
        List.tabulate(2, 3)(_ + _) //下标相加： List[List[Int]] = List(List(0, 1, 2), List(1, 2, 3))

        //列表的基本操作

        //添加元素到头部
        5 +: nums //同下
        5 :: nums //同下
        nums.+:(5) // List[Int] = List(5, 1, 2, 3, 4)  注意是放到前面的,而且不能去掉.号，也不能去掉()
        nums.::(5)
        nums.+:(5, 6) // List[Any] = List((5,6), 1, 2, 3, 4)  注意是一个元素
        nums.::(5, 6) //类似于+:在开头添加元素，但是::可以用于pattern match ，而+:则不行
        //添加元素到尾部，产生新集合
        nums.:+(5) // List[Int] = List(1, 2, 3, 4, 5) 注意是放到后面的
        nums :+ 5 //可以去掉.号和()   同上
        nums :+ (5) //同上
        //连接两个集合
        site ++ site1
        //连接两个列表(以下等价)
        site ::: site1
        site.:::(site1)
        site ::: nums //List[Any] = List(Runoob, Google, Baidu, 1, 2, 3, 4)
        List.concat(site, site1)

        //模式匹配：求和
        def sum(lst: List[Int]): Int = {
            lst match {
                case Nil => 0
                //::用于pattern match，在Scala中列表要么为空（Nil表示空列表）要么是一个head元素加上一个tail列表
                case h :: t => h + sum(t) //h是lst.head，而t是lst.tail
            }
        }
        nums.sum //简化方法


        //获取列表的元素（只能作用于非空列表）
        site.head //返回第一个元素     String = Runoob
        site.tail //返回除了第一个元素的列表    List[String] = List(Google, Baidu)
        site.tail.tail.tail //Nil

        //与head和tail不同之处是：head和tail运行时间是常量，但init和last需要遍历整个列表，时间较长。
        site.last //返回最后一个元素
        site.init //返回除了最后一个元素的列表

        site.take(2) //返回前两个元素
        site.takeRight(2) //返回后两个元素

        site.drop(2) //扔掉前两个元素

        site.splitAt(1) //在下标1处切分，返回pair列表 (List[String], List[String]) = (List(Runoob),List(Google, Baidu))

        site.indices //返回列表的索引值组成的序列：  scala.collection.immutable.Range = Range(0, 1, 2)
        //咬合操作
        site.indices.toList.zip(site) //List[(Int, String)] = List((0,Runoob), (1,Google), (2,Baidu))
        site.zipWithIndex // List[(String, Int)] = List((Runoob,0), (Google,1), (Baidu,2))

        //显示字符串
        site.toString() // String = List(Runoob, Google, Baidu)
        site.mkString(",") //String = Runoob,Google,Baidu
        site.mkString(">>>", "—", "<<<") //String = >>>Runoob—Google—Baidu<<<

        //连续存放的数组和递归存放的列表之间进行转换
        site.toArray
        site.toArray.toList
        val arr = new Array[String](10)
        site.copyToArray(arr, 3) //从下标3开始

        //枚举器访问元素列表
        val it = site.iterator //Iterator[String] = non-empty iterator
        it.next()
        it.next()
        it.hasNext

        site.isEmpty //判断是否为空（不要用site.length==0）
        site.length //列表长度（遍历整个列表，比较费时）
        site.reverse //反转列表

        //高级操作
        //列表间映射
        nums.map(x => x + 1)
        site.map(_.length)
        site.map(_.toList.reverse.mkString) //List[String] = List(boonuR, elgooG, udiaB)
        site.flatMap(_.toList) // List[Char] = List(R, u, n, o, o, b, G, o, o, g, l, e, B, a, i, d, u)
        site.foreach(println(_))
        //列表过滤
        nums.filter(_ + 1 < 4) //符合条件的过滤出来   List[Int] = List(1, 2)
        nums.find(_ + 1 < 4) // 返回第一个满足的元素，后不满足，返回None   Option[Int] = Some(1)
        nums.partition(_ % 2 == 0) //满足条件的在前，不满足的在后    (List[Int], List[Int]) = (List(2, 4),List(1, 3))
        //以下没有filter好用，因为只是获取前缀，一旦在后面就获取不到。
        site.takeWhile(str => str.length == 6) //返回满足条件的前缀，一旦不满足就返回空
        site.takeWhile(str => str.length == 5) //返回Nil，因为第一个就不满足，有Baidu也没用。
        site.dropWhile(str => str.length == 6) //扔掉满足条件的前缀
        site.dropWhile(str => str.startsWith("B")) //返回全部，Baidu是第三个，不会被扔掉
        site.span(_.length == 6) //将前两者结合     (List[String], List[String]) = (List(Runoob, Google),List(Baidu))
        //列表的论断
        site.forall(_.length >= 5) //所有元素必须全部满足才返回true
        site.exists(_.length == 5) //只要有一个元素满足就返回true

        //折叠操作(不懂，跳过)
        site.foldRight("lll")(_ + "--" + _) //String = Runoob--Google--Baidu--lll
        site.foldLeft("lll")(_ + "--" + _) // String = lll--Runoob--Google--Baidu

        nums.sum //求和    10
        nums.product //相乘    24

        //列表排序
        List(3, 2, 4, 5, 2, 5, 7).sorted //按大小排序
        List(3, 2, 4, 5, 2, 5, 7).sortWith(_ > _) //从大到小排序
        site.sortWith(_.length < _.length) //按长度排序
    }




    val 可变列表_列表缓存 = 0
    if(1){
        import scala.collection.mutable.ListBuffer
        val buf = new ListBuffer[Int]
        buf += 1
        buf += (2,3)//buf.type = ListBuffer(1, 2, 3)
        (4 +: buf).toList
    }
}

object QueueDemo extends App {
    println("先进后出的序列========")
    val 不可变序列 = 0
    if (1) {
        import scala.collection.immutable.Queue
        val que = Queue[Int]() //不能加new，不知道为什么
        val que2 = Queue(1, 4, 5, 2, 3, 4, 6)

        println("添加元素=======")
        que.enqueue(1).enqueue(List(2, 3, 4))
        //scala.collection.immutable.Queue[Int] = Queue(1, 2, 3, 4)


        println("从头部移除元素========")
        val (elemaen, que3) = que2.dequeue //返回对偶Tuple2
        //elemaen: Int = 1
        //que3: scala.collection.immutable.Queue[Int] = Queue(4, 5, 2, 3, 4, 6)

    }

    val 可变序列 = 0
    if (1) {
        import scala.collection.mutable.Queue
        val que4 = new scala.collection.mutable.Queue[String]()
        que4 += "a"
        que4 ++= List("b", "c", "d") //que4.type = Queue(a, b, c, d)
        que4.dequeue() // String = a
    }
}

object StackDemo extends App{
    println("先进先出的序列===========")
    val 栈 = 0
    if(1){
        import scala.collection.mutable.Stack
        val stack = new Stack[Int]
        stack.push(1).push(2,3,4)//元素的推入    stack.type = Stack(4, 3, 2, 1)
        stack.pop//元素的弹出    Int = 4
        stack.top//只获取，不移除
    }
}

object SetDemo extends App{
    println("集没有重复值=========")

    val 不可变集 = 0
    if(1){
        val set = Set(1,1,2,3,4,5,5)//scala.collection.immutable.Set[Int] = Set(5, 1, 2, 3, 4)
        val set2 = Set("aaa","aaa","dfg","abc")// scala.collection.immutable.Set[String] = Set(aaa, dfg, abc)
        set + 5//添加元素
        set - 3//删除元素
        set ++ List(5,7,8)//scala.collection.immutable.Set[Int] = Set(5, 1, 2, 7, 3, 8, 4)
        set -- List(1,2,3)
        set -- List(1,2,9)//scala.collection.immutable.Set[Int] = Set(5, 3, 4)
        set.size
        set.contains(5)

        set.head
        set.tail
        set.isEmpty
        set.++(set2)
        set ++ set2

        set.min
        set.max
        set.&(Set(3,4,7,8))  //取交集 3，4
        set.intersect(Set(3,4,7,8))
        //以上方法都是traversableonce的方法，集合通用的

        //有序的集,具体的顺序取决于Order特质
        import scala.collection.immutable.TreeSet
        val ts = TreeSet(34,5,5,6,3,3,5,6,4,3,3,6,7,8,1)// scala.collection.immutable.TreeSet[Int] = TreeSet(1, 3, 4, 5, 6, 7, 8, 34)
        val cs = TreeSet('f','u','n')//scala.collection.immutable.TreeSet[Char] = TreeSet(f, n, u)
    }

    val 可变集 = 0
    if(1){
        val words = scala.collection.mutable.Set.empty[String]
        words += "the"
        words -= "the"
        words ++= List("do","re","mi")
        words --= List("do","re")
        words.clear()//删除所有元素
    }
}


object MapDemo extends App{
    //键值对
    val 不可变映射 = 0
    if(1){
        val map = Map("i"->1,"j"->2)
        val map2 = Map(("i",1),("j",2))
        map + ("k"->3)
        map - "j"
        //合并集合
        map ++ List("m"->2,"l"->7)//scala.collection.immutable.Map[String,Int] = Map(i -> 1, j -> 2, m -> 2, l -> 7)
        map -- List("m","l")
        map.size
        map.contains("i")
        map.keys// Iterable[String] = Set(i, j)
        map.values//Iterable[Int] = MapLike(1, 2)
        map.keySet//scala.collection.immutable.Set[String] = Set(i, j)
        map.isEmpty

        //有序的映射
        import scala.collection.immutable.TreeMap
        val tm = TreeMap(3->"x",4->"y",5->"z")
        tm + (4->"g")// scala.collection.immutable.TreeMap[Int,String] = Map(3 -> x, 4 -> g, 5 -> z)
    }


    val 可变映射 = 0
    if(1){
        val words = collection.mutable.Map.empty[String,Int]
        words += ("one"->1)
        words -= "one"
        words ++= List("one"->1,"two"->2)
        words --= List("one","two")
    }
}

