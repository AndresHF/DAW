#!/bin/bash
#-------------------------EJERCICIO 3-------------------------------------------

# 1. Cree un directorio que se llame ejercicio3
cd ~/REPASO/ej3
mkdir ejercicio3

# 2. Vaya a ejercicio3
cd ejercicio3

# 3. Compruebe donde está
pwd

# 4. Cuente cuantas letras tiene cada usuario
cat /etc/passwd | cut -f1 -d: | xargs -I{} bash -c "echo {} | tr -d '\n' | wc -c"

# 5. Muestre el usuario que más letras tiene
cat /etc/passwd | cut -f1 -d: | xargs -I{} bash -c "echo -n {}' ' ; echo {} | tr -d '\n' | wc -c" | sort -k2 -n | tail -1 | cut -f1 -d" "

# 6. ¿Cuáles son los usuarios que más letras tienen?
cat /etc/passwd | cut -f1 -d: | xargs -I{} bash -c "echo {} | tr -d '\n' | wc -c" | sort -n | tail -1 | xargs -I{} egrep ".{{}}" <(cat /etc/passwd | cut -f1 -d:)
# 6.1 AWK
awk -F: '{print length($1)|"sort -n|tail -1"}' /etc/passwd | xargs -I{} egrep ".{{}}" <(cat /etc/passwd | cut -f1 -d:)

# 7. Mostrar cuántas letras diferentes tiene cada usuario
cat /etc/passwd | cut -f1 -d: | xargs -I{} bash -c 'echo {} | tr "\n" ":";echo {} | egrep -o "." | sort | uniq | wc -l'

# 8. Mostrar cuál es el número máximo de letras diferentes que tienen los usuarios
cat /etc/passwd | cut -f1 -d: | xargs -I{} bash -c 'echo {} | tr "\n" " ";echo {} | egrep -o "." | sort | uniq | wc -l' | sort -k2 -n | tail -1 | cut -f2 -d" "

# 9. Contruir tu propio groups para todos los usuarios
cat /etc/passwd | cut -f1,4 -d: | xargs -I{} bash -c 'echo {} | cut -f1 -d: | tr "\n" ":";egrep -e "(:$(echo {} | cut -f2 -d:):|(,|)$(echo {} | cut -f1 -d:)$)" /etc/group | cut -f1 -d: | tr "\n" " "; echo'

# 10. Mostrar aquellos grupos que no tienen usuario (sólo /etc/group)
egrep -e ":$" /etc/group

# 11. Mostrar los grupos que más usuarios tienen (sólo /etc/group)
egrep -e ":x:.{0,}:.{1,}" /etc/group | cut -f4 -d: | tr "," " " | xargs -I{} bash -c "echo {} | wc -w" | sort -n | tail -1 | xargs -I{} echo {}-1 | bc | xargs -I{} egrep -e ":.{1,}(,.{1,}){{}}" /etc/group | cut -f1 -d:

# 12. ¿Cuál es el proceso con el PID más alto?
ps -e | sort -k1 -n | tail -1 | tr -s " " " " | cut -f4 -d" "

# 13. Cuente los procesos bash que se están ejecutando
ps -eo comm | grep "bash" | wc -l

# 14. Para todos los pid y sus ppid muestre -> ppid: pid pid pid pid.....
ps -eo ppid | sort -n | uniq | tr -d " " | grep -v "[A-Z]" | xargs -I{} bash -c 'echo --{} | tr "\n" ":"; ps -eo pid,ppid | tr -s " " " " |egrep "{}$" | cut -f1,2 -d" " | tr "\n" " ";echo'




