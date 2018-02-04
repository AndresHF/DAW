cd ~
# 1-Cree un directorio que se llama ejercicio2
mkdir ejercicio2
# 2-Váyase a ejercicio2
cd ejercicio2
# 3-Compruebe donde está
pwd
# 4-Cree un directorio para cada UID de /etc/passwd
cat /etc/passwd | cut -f3 -d: | xargs mkdir
# 5-En cada directorio creado debe aparecer un archivo con el nombre del usuario que corresponda
# 5.1-Forma 1
cat /etc/passwd | xargs -I{} bash -c "echo {} | cut -f3 -d: | tr -d '\n'; echo /{} | cut -f1 -d:" | xargs touch 
# 5.2-Forma awk
cat /etc/passwd | cut -f1,3 -d: | tr ':' ' ' | awk '{print $2"/" $1}'| xargs touch
# 5.3 COMANDO PASTE
paste -d/ <(cat /etc/passwd | cut -f3 -d:) <(cat /etc/passwd | cut -f1 -d:)
# 6- El contenido de ese archivo debe ser la ruta de acceso a todos loas archivos de los que es propietario dicho usuario
cat /etc/passwd | awk -F: '{print $3"/"$1}' | xargs -I{} bash -c "echo {} | cut -f2 -d/ | xargs -I[] find / -user [] 2> /dev/null > {}"
# 6- EXTRA-> Cuantas líneas hay en cada archivo
find . -type f | xargs -I{} bash -c "(echo -n '{} '; cat {} | wc -l)"

# 7- Muestre el UID más alto
cat /etc/passwd | cut -f3 -d: | sort -n | tail -1
# 7.1 - AWK
awk -F: '{print $3+1|"sort -n|tail -1"}'
# 8- Muestre cuál es la diferencia máxima entre 2 UID
echo $(cat /etc/passwd | cut -f3 -d: | sort -n | tail -1)-$(cat /etc/passwd | cut -f3 -d: | sort -n | head -1) | bc
# 9- Obtener el UID siguiente al más alto
echo $(cat /etc/passwd | cut -f3 -d: | sort -n | tail -1)+1 | bc
# 10- Nombre del GID de cada usuario del sistema
cat /etc/group | awk -F: '{print $1,$3}' | sort -k2 > group_ordenado;cat /etc/passwd | awk -F: '{print $1,$4}' | sort -k2 > passwd_ordenado;join -1 2 -2 2 passwd_ordenado group_ordenado |  awk '{print "USUARIO->"$2,"\tGRUPO->" $3}' | tr -d ':'; rm group_ordenado; rm passwd_ordenado
# 10.1 SIN ARCHIVOS TEMPORALES
join -t: -j 2 <(cat /etc/passwd | cut -f1,4 -d: | sort -k2 -t: -n) <(cat /etc/group | cut -f1,3 -d: | sort -k2 -t: -n) | cut -f2,3 -d:
# 11- Cuantos usuarios hay en el grupo que más usuarios tiene
awk -F: '{print ":"$4":"|"sort -n"}' /etc/passwd | uniq | xargs -I{} bash -c "grep "{}[A-Za-z]*" /etc/passwd | wc -l" | sort -n | tail -1
# 11.1 TOMA CASTAÑA DE PASSWD A PASSWD
cat /etc/passwd | cut -f4 -d: | sort | uniq -cd | tr -s ' ' | sort -k1 -n -t' ' | tail -1 | cut -f2 -d ' '
# 11.2 DE PASSWD A GROUP
cat /etc/group | cut -f1,4 -d: | xargs -I{} bash -c "(echo -n $(echo {} | cut -f1 -d:))"
# 12- Cuantos grupos no tienen usuarios
echo $(cat /etc/group | wc -l)-$(cat /etc/passwd | wc -l) | bc

# 13- Nombre del directorio/archivo y el tamaño que ocupa en unidades amigables (KB, MB, GB)
ls -l /etc | awk -F" " '{print "^[[1;33mNOMBRE: "$9}{print "^[[1;35mTAMAÑO: "$5/1000 " KB"}'
