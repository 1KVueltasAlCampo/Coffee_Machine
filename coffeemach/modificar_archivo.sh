#!/bin/bash

# Ruta al archivo que deseas modificar
rutaArchivo="src/main/resources/coffeMach.cfg"

# Ruta al directorio donde se encuentra el proyecto
rutaProyecto="D:/Usuarios/semillero/Desktop/Coffee_Machine/coffeemach/coffeeMach"

# Inicializar el número
numero=9097

# Realizar iteración cinco veces
for i in {1..5}
do
    # Reemplazar el puerto en la línea correspondiente
    nuevaLinea=$(sed "s/-p $numero/-p $((numero+1))/" $rutaArchivo)

    # Sobrescribir el archivo con la línea modificada
    echo "$nuevaLinea" > $rutaArchivo

    # Aumentar el número
    numero=$((numero+1))

    # Ejecutar los comandos en una nueva ventana de Command Prompt usando PowerShell
    powershell.exe -Command "Start-Process cmd.exe -ArgumentList '/c \"cd /d \"$rutaProyecto\" && gradle build && java -jar build/libs/coffeeMach.jar\"'"

    # Esperar 5 segundos antes de continuar a la siguiente iteración
    sleep 5
done

# Restaurar el valor original en el archivo
nuevaLinea=$(sed "s/-p $numero/-p 9097/" $rutaArchivo)
echo "$nuevaLinea" > $rutaArchivo
