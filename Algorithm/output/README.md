#1253. 重构 2 行二进制矩阵
##总结
我写的代码逻辑上是没有问题的，但是在测试到第65个用例的时候超时了，
主要是因为我是通过求和来对比是否满足upper和lower的，所以一旦涉及到求和，就需要遍历
所以可以通过对upper和lower进行减法操作来去除遍历。

#收获
通过这道题的收获就是，当存在数字类型的标准时，如个数限制、大小限制等，通过对标准进行
减操作的时间复杂度远比通过循环进行累加的时间复杂度高。

