#!/bin/bash
#-------------------------------- EJERCICIO 1 ---------------------------------------------------------------------------
# 1. Cree un directorio que se llame ejercicio1
cd ~/REPASO/ej1
mkdir ejercicio1
# 2. Situese en ejercicio1
cd ejercicio1
# 3. Compruebe que está en ejercicio1
pwd
# 4. Cree 52 directorios 1 por cada semana del año y que cada semana tenga un directorio para cada dia de la semana
mkdir -p {01..52}/{1..7}
# 5. Situese en la semana 30 día 4, desde allí cree un archivo en cada directorio día que se llame kk
cd 30/4; touch ../../{01..52}/{1..7}/kk
# 6. Cree un directorio que se llame 53 (a nivel de las semanas), cree el archivo kk2 con la fecha y hora actual con comando date, 
# formato(20 14:09:09) y lo lleve a kk2 directorio 53
mkdir ../../53; date +%d" "%k:%M:%S > kk2; mv kk2 ../../53
# 7. Busque todos los archivos con extensión java y guarde las rutas de acceso en 02/3/java
find / -name *.java 2> /dev/null > ../../02/3/java
# 8. Cree en el directorio actual un enlace simbólico blando al directorio 01/7 con el nombre 7
ln -s ../../01/7 7
# 9. Copie todos las semanas menores a 10 en el directorio kk2, kk2 creado en el que estoy
mkdir kk2; cp -r ../../{01..09} kk2
# 10. Con trayectoria relativa sitúese en el directorio de trabajo y borre ejercicio1
cd ~/REPASO/ej1; #rm -r ejercicio1





