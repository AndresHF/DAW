#!/bin/bash
# 1. Cree un directorio que se llame ej3
mkdir ej3
# 2. Vaya a ej3
cd ej3
# 3. Compruebe donde está
pwd
# 4. Cuente cuatas letras tiene cada usuario
awk -F: '{print $1}' /etc/passwd | xargs -I{} bash -c "echo {} | wc -c"

# 5. Muestre el usuario que más letras tiene
awk -F: '{print $1}' /etc/passwd | xargs -I{} bash -c "echo {} | grep -o '.' | wc -l | tr '\n' ' '; echo {}" | sort -k1 -n | tail -1

# 6. Cuáles son los usuarios que más letras tienen
awk -F: '{print $1}' /etc/passwd | xargs -I{} bash -c 'echo {} | grep -o "." | wc -l' | sort -n | tail -1 | xargs -I{} bash -c 'cat /etc/passwd | cut -f1 -d: | egrep ".{{}}"'
# 6. OTRA FORMA
cat /etc/passwd | cut -f1 -d: | egrep ".{$(awk -F: '{print length($1)|"sort -n | tail -1"}' /etc/passwd)}"

# 7. Mostrar cuántas letras diferentes  tiene cada usuario
awk -F: '{print $1}' /etc/passwd | xargs -I{} bash -c "echo USUARIO: {}; echo {} | grep -o '.' | sort | uniq -c"

# 8. Mostrar cuál es el número máximo de letras diferentes que tienen los usuarios
awk -F: '{print $1}' /etc/passwd | xargs -I{} bash -c "echo USUARIO: {}' ';echo {} | grep -o '.' | sort | uniq | wc -l" | sort -n | tail -1

# 9. Construir tu propio groups para todos los user
awk -F: '{print $1" "$4}' /etc/passwd | xargs -I{} bash -c 'echo "--"{} | cut -f1 -d" " | tr "\n" ":"; echo {} | egrep -e "([,:]$(echo {} | cut -f1 -d" ")(,|$))|(:$(echo {} | cut -f2 -d" "))" /etc/group | cut -f1 -d: | tr "\n" " ";echo'

# 10. Mostrar aquellos grupos que no tienen usuarios(sólo /etc/group)
grep -e ":$" /etc/group

# 11. Mostrar los grupos que más usuarios tienen
egrep -ve ":$" /etc/group | xargs -I{} bash -c 'echo {} | cut -f1 -d: | tr "\n" " ";echo {} | cut -f4 -d: | tr "," " " | wc -w' | xargs -I{} bash -c 'echo {} | egrep "$(echo {} | cut -f2 -d" " | sort -n | tail -1)$"'
# 12. Saque el proceso con PID más alto (ps)
ps -e | sort -k4 -n | tail -1 | tr -s ' ' ' ' | cut -f4 -d' '

# 13. Cuente cuantos procesos bash se están ejecutando
ps -e | grep bash | wc -l #MAL
pgrep -c -x "bash"
pgrep -c "^bash$"

# 14. Para todos los pid y sus ppid muestre -> ppid: pid pid pid pid pid ...
ps -e -o ppid | sort | uniq | xargs -I{} bash -c 'echo {}: | tr "\n" " "; ps -e -o pid,ppid | tr -s " " " " | grep " {}$" | cut -f2 -d" " | tr "\n" " ";echo'
#OTRA
ps -eo ppid --no-headers | tr -d " " | sort -n | uniq | xargs -I{} bash -c 'echo -n {}: ; ps -eo pid,ppid | egrep " {}$" | tr -s " " | egrep -o "(^| )[0-9]+ " | tr -d " " | tr "\n" " ";echo'









