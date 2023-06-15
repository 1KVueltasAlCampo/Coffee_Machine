#!/bin/bash

# Ruta al archivo que deseas modificar
rutaArchivo="src/main/resources/coffeMach.cfg"

# Ruta al directorio donde se encuentra el proyecto
rutaProyecto=$(pwd)

# Inicializar el número
numero=9097
numero2=9096

# Realizar iteración cinco veces
for i in {1..1}
do
    gradle build    

    nuevaLinea=$(sed "s/-p $numero/-p $((numero+2))/" $rutaArchivo)
    echo "$nuevaLinea" > $rutaArchivo

    # Reemplazar el puerto en la línea correspondiente
    nuevaLinea=$(sed "s/-p $numero2/-p $((numero2+2))/" $rutaArchivo)
    echo "$nuevaLinea" > $rutaArchivo

    # Aumentar el número
    numero=$((numero+2))
    numero2=$((numero2+2))

    # Ejecutar los comandos en una nueva ventana de Command Prompt usando PowerShell
    #gnome-terminal -x bash -c "cd $rutaProyecto && java -jar build/libs/coffeeMach.jar\"'"

    gnome-terminal --window --command "bash -c 'java -jar build/libs/coffeeMach.jar;exec bash'"    

    # Esperar 5 segundos antes de continuar a la siguiente iteración
    sleep 5
done

# Restaurar el valor original en el archivo
nuevaLinea=$(sed "s/-p $numero/-p 9097/" $rutaArchivo)
echo "$nuevaLinea" > $rutaArchivo
nuevaLinea=$(sed "s/-p $numero2/-p 9096/" $rutaArchivo)
echo "$nuevaLinea" > $rutaArchivo
