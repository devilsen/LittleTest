#!/bin/bash
# File by Devilsen

# 算术运算方式
# 1. 使用$(())  $只是取值符
# 2. 使用$[]
# 3. 使用let命令
# 4. 使用expr外部程式
a=$(( 2 + 4 ))
echo $a
b=$[ 3 + 5 ]
echo $b
let c=7+8
echo $c
# 注意空格，推荐使用这种，多数环境是支持的
d=`expr 9 + 9`
echo $d

# 算术运算符
# + - * / % = == !=
# 关系型
# -eq 相等      -ne 不相等      
# -gt 大于（greater than）      -lt 小于（less than）
# -ge 大于等于（greater equal）     -le 小于等于（less equal）
# 布尔型
# ! 非      -o 或（or）     -a 与（and）
# 逻辑型
# && ||
# 字符串
# = 检测是否相等      != 检测是否不相等
# -z 检测字符串长度是否为0        -n 检测字符串长度是否 不 为0
# $ 检测字符串是否为空，不为空返回true
s1=""
s2="efg"
if (( $s1 = $s2 )) 
then
    echo "s1 equal s2"
else
    echo "s1 not equal s2"
fi

if [ -z $s1 ]
then
    echo "the length is 0"
else 
    echo "the length is not 0"
fi