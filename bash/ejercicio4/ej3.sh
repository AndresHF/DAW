#!/bin/bash
# 3. Cree un script que llame al script anterior pasándole una lista de números

list=$@

for num in $list; do
	echo TABLA DEL $num
	echo
	./ej2.sh $num
	echo
	
done
