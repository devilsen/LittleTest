#!/bin/bash
# for 变量 in 字符串
# do
#   语句
# done
for i in `seq 1 10`
do
    echo "$i"
done

# 累加从1到100
echo
j=0
for((i=1;i<=100;i++))
do
    j=`expr $j + $i`
done
echo "total is $j"

# 打印当前目录下的所有文件
echo $0
for i in `find $PWD -name "*.sh"`
do
    echo $i
done


# while 条件语句
# do
#   语句
# done
echo
i=0
while((i<10))
do
    i=`expr $i + 1`
    echo $i
done

echo
i=0
# 注意while当前行空格的位置，空格错一个都不能通过
while [[ $i -lt 10 ]]
do
    i=`expr $i + 1`
    echo "lt $i"
done