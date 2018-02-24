#!/bin/bash
# 2. Cree un script que pida por linea de comandos un número y que muestre la tabla de multiplicar de ese número

num=$1
counter=1

while [ $counter -le 10 ] ; do

	echo "$counter" '*' "$num" '=' $(( $counter*$num ))
	let counter++

done 
