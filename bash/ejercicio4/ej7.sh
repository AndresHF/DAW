#!/bin/bash
# 7. Cree un script que reciba una cadena de texto y devuelva si es palíndroma

word=$@
if $(echo "$word" | egrep ".{0}") 2> /dev/null ; then
	echo 'Missing param. Usage ej7.sh word [word]... (case sensitive)'
else
	echo $word | grep "$(echo $word | rev)" >/dev/null && echo "palindrome: true" || echo "palindrome: false"
fi
