#!/bin/bash
# 8 Si al script anterior no se le pasa cadena por parametro la pide por teclado, la pedirá cíclicamente hasta que introduzcas algo

word=$@

while $(echo "$word" | egrep ".{0}") 2> /dev/null; do
	echo 'Missing param, type any word:'
	read word
done

echo "$word" | grep "$(echo $word | rev)" > /dev/null && echo 'palindrome: true' || echo 'palindrome: false' 
