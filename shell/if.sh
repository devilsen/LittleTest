#! /bin/bash
# File by Devilsen
# if(表达式); then
#     echo " < "
# else
#     echo " > "
# fi
if((100 > 200)); then
    echo " < "
else
    echo " > "
fi

# 一些常用的判断操作
# -f 判断文件是否存在
# -d 判断文件夹是否存在
# -eg 等于（整形比较）
# -ne 不等于比较
# -le 小于比较
# -ge 大于比较
# -a 双方都成立
# -o 单方成立

echo
DIR=$PWD/createDir
if [ -d $DIR ]
then
    echo "dir already exists"
else
    mkdir -p $DIR
    if(($? == 0)); then # 注意：括号是括号，表达式是表达式，表达式的一部分就是括号
        echo "create successd"
    else
        echo "create fail"
    fi
fi