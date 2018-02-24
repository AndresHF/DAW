#!/bin/bash
# 5. Cree un script que reciba una lista de grupos y devuelva que usuarios pertenecen a todos los grupos pasados

groups=$@
users=""
result=""

for group in $groups ; do 
	users=$users" "$(./ej4.sh "$group" | cut -f2 -d:)
done

for user in "$users" ; do
	
	if [ $(echo "$users" | grep -o "$user" | wc -l) -eq "$#" ] ; then
		result="$result"' '"$user"
	fi
	users=$(echo "$users" | grep -v "$user") 
done

echo $result


