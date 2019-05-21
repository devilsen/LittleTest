#!/bin/bash
# File by dongsen
echo "Hello World"
# 定义变量时不能有空格
A=10
echo "A = $A"

echo
# 表示当前文件
echo $0
# 表示第一个传入的参数
echo "the first arg is $1"
# 表示第二个传入的参数
echo "the second arg is $2"

echo
# ? * #
# ? 表示上一条命令是否执行成功  0 表示成功
echo $? 
# * 表示所有的参数
echo $* 
# # 表示参数的个数
echo $# 
# 当前文件的绝对路径
echo $PWD


echo
# expr （expression）表达式的缩写，通过这个可以进行算术运算
num1=2
num2=3
# num3=2 + 3 如果要进行算术运算，必须要有空格与 『+』 分开 ,注意：这样直接赋值是错误的
echo $num1+$num2
echo $num1 + $num2
echo `expr $num1+$num2`
echo `expr $num1 + $num2`

