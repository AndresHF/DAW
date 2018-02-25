#!/bin/bash
# 4. Cree un script que reciba un nombre de grupo y devuelva todos los usuarios que pertenecen a ese grupo

group=$1
gid=$(egrep -e "^$1:" /etc/group | cut -f3 -d:)
collateral=$(egrep -e "^$1:" /etc/group | cut -f4 -d: | tr ',' ' ')

echo -n $group: $collateral" "
IFS=$'\n'
for line in $(cat /etc/passwd); do
	echo $line | egrep ".+:.+:.+:$gid:.+" | cut -f1 -d: | tr '\n' ' '
done

echo 
