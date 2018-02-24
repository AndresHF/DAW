#!/bin/bash
# 1. Cree un programa que reciba una lista de números y que devuelva aquellos que sean múltiplos del primer número pasado

num=$1
nums=$@
for i in $nums ; do
	if [ $(( $i % $num )) -eq 0 ] && [ $i -ge $num ] ; then
		echo $i
	fi
done
