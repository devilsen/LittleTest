# !/bin/bash
# File by Devilsen

# 基本格式
# name(){
#     command
#     command2
# }
# 基本格式2
# [ function ] funname [()]{
#     action;
#     [return int;]
# }

function test(){
    echo "this is a function"
}

function test1(){
    # 实际上 $ 表示的是函数中的输入，可以理解为第一个形参，并非是外面的$1
    echo "this is input data : $1" 
}

test
test1 $1


# 比如：实现一个阶乘
t=1;
function factorial(){
    for((i=1;i<=$1;i++))
    do
        # t=$[ $i * $t ]
        t=`expr $i \* $t ` # 注意反斜杠
    done
    echo "$1 factorial is : $t"
}
factorial $1


# return test
function functionReturn(){
    read -p "please input a number: " num
    # 如果这里不定义返回值，如果执行成功会返回0，不成功则返回1-255之间的错误码
    # 但是：return 只能返回 0-255之间的值，如果要返回字符串或者大于255的值，也会溢出，这时你需要使用echo
    return $[ $num * 2 ]
}
functionReturn
echo "the return number is : $?"


# return test1
function functionReturn1(){
    read -p "please input a number: " num
    echo $[ $num * 2 ]
}
functionReturn1
# 这里的输出只能表示成功与否
echo "the return number is : $?" 
# 需要去函数的值
result=`functionReturn1`
echo "the return number is : $result"
