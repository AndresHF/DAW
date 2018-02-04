#!/bin/bash
cd ~
pwd
inicialCreacion=$(date +%s)
echo kk2/{1..120000} | xargs mkdir -p
finalCreacion=$(date +%s)
sumaCreacion=${finalCreacion}-${inicialCreacion}
echo ${sumaCreacion} | bc
inicialBorrado=$(date +%s)
rm -r kk2
finalBorrado=$(date +%s)
sumaBorrado=${finalBorrado}-${inicialBorrado}
echo ${sumaBorrado} | bc
echo -n "Total en creación: ";echo ${sumaCreacion} | bc;
echo -n "Total en borrado: ";echo ${sumaBorrado} | bc;
echo -n "TOTAL: ";echo ${sumaCreacion}+${sumaBorrado} | bc 
