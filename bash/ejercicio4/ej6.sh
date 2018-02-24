#!/bin/bash
# 6. IGUAL QUE EL ANTERIOR con los que no pertenecen a ningún grupo

groups=$@
users=""
allUsers=$(cat /etc/passwd | cut -f1 -d:)

for group in $groups ; do 
	users=$users" "$(./ej4.sh "$group" | cut -f2 -d:)
done

pattern=$(echo $users | tr ' ' '|')

echo -e $allUsers | tr ' ' '\n' | egrep -v "$pattern"
