




## 常见Bug
- 明明没有发现错误，但是某个变量他就是标红报错（常见于从网上复制代码的时候）：一般是你在下面又定义了一次这个变量
- forward reference extends over definition of value
  myAcc:尝试先定义类、方法，然后再调用，而不是把定义放在调用后面。
-

注意：
- 有个测试文件大小为12M，下载可能缓慢
- [src/log4j](src/log4j.properties)
  这个文件是用来控制Console打印信息的，去掉INFO信息(第19行将INFO改为了WARN)，
  后来又改回来了。
-
