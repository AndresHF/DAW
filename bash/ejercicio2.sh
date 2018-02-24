#!/bin/bash
#--------------------------------EJERCICIO 2---------------------------------------------------------------------------

# 1.Cree un directorio que se llame ejercicio2
cd ~/REPASO/ej2
mkdir ejercicio2

# 2.Váyase a ejercicio2
cd ejercicio2

# 3.Compruebe donde está
pwd

# 4.Cree un directorio para cada UID de los usuarios del sistema
cat /etc/passwd | cut -f3 -d: | xargs -I{} mkdir {}

# 5. En cada directorio creado debe aparecer un archivo con el nombre del usuario que corresponda
cat /etc/passwd | cut -f1,3 -d: | xargs -I{} bash -c "echo {} | cut -f2 -d: | tr '\n' '/'; echo {} | cut -f1 -d:" | xargs -I{} touch {}
# 5.1 AWK
awk -F: '{print $3"/"$1}' /etc/passwd | xargs -I{} touch {}

# 6. El contenido de ese archivo debe ser la ruta de acceso a todos los archivos de los que es propietario dicho usuario
cat /etc/passwd | cut -f1,3 -d: | xargs -I{} bash -c "echo {} | cut -f2 -d: | tr '\n' '/'; echo {} | cut -f1 -d:" | xargs -I{} bash -c "echo {} | cut -f2 -d'/' | xargs -I[] find / -user [] 2> /dev/null > {}"
# 6.1 AWK
awk -F: '{print $3"/"$1}' /etc/passwd | xargs -I{} bash -c "echo {} | cut -f2 -d'/' | xargs -I[] find / -user [] 2> /dev/null > {}"

# 6. EXTRA --> ¿Cuántas líneas hay en cada archivo?
tree -if | egrep "^(./[0-9]{1,}/.{1,})$" | xargs -I{} bash -c "cat {} | wc -l"

# 7. Mueste el UID más alto
cat /etc/passwd | cut -f3 -d: | sort -n | tail -1

# 8. Muestre cuál es la diferencia máxima entre 2 UID
echo $(cat /etc/passwd | cut -f3 -d: | sort -n | tail -1)-$(cat /etc/passwd | cut -f3 -d: | sort -n | head -1) | bc
# 8.1 AWK
awk -F: '{printf $3-|"sort -n | tail -1";print $3 |"sort -n | head -1"}' /etc/passwd

# 9. Obtener el UID siguiente al más alto
echo $(cat /etc/passwd | cut -f3 -d: | sort -n | tail -1)+1 | bc

# 10. Nombre del GID de cada usuario del sistema
join -t: -1 2 -2 2 <(cat /etc/passwd | cut -f1,4 -d: | sort -k2 -t:) <(cat /etc/group | cut -f1,3 -d: | sort -k2 -t:) | cut -f2,3 -d:

# 11. ¿Cuántos usuarios hay en el grupo que más usuarios tiene? (sólo /etc/group)
egrep -e "(:x:.{1,}:.{1,})$" /etc/group | cut -f4 -d: | tr "," " " | xargs -I{} bash -c "echo {} | wc -w" | sort -n | tail -1

# 12. ¿Cuántos grupos no tienen usuario? (sólo /etc/group)
grep -e ":$" /etc/group | wc -l
