# Coffee_Machine

<p align="justify">
  Proyecto de "Sistemas Distribuidos" para la simulación de una empresa que tiene muchas maquinas de cafe alrededor de una ciudad, 
en este caso Cali. Para el desarrollo de este sistema se utilizó el Middleware ICE, junto con Java 11 y Gradle, con los que se
crearon los sistemas de Servidor Central, Logistica, Bodega y el de la Maquina de Cafe. Adicional a estos, se crearon sistemas
que funcionaban para implementar algunos patrones de diseño, como el proxy-cache, junto con el publisher/suscriber, y el 
reliable-messaging.
</p>

<p align="justify">
  Elegimos estos patrones para solucionar tres drivers arquitectonicos que se nos presentaron durante el analisis de los escenarios
  de QAW del sistema de la maquina de cafe:
  <ul>
    <li> Actualizar nueva bebida de cafe en las maquinas desde el servidor central. </li>
    <li> No perder alarmas al momento de ser enviadas desde las maquinas al servidor central. </li>
    <li> Asegurar la resolución de las alamarmas en su ciclo de vida por bodega y logistica. </li>
  </ul>

## Miembros

<div align="center">
  <table>
    <tr>
      <td align="center">
        <a href="https://github.com/Rockthor1106">
          <img src="https://images.weserv.nl/?url=avatars.githubusercontent.com/u/71186075?v=4&h=100&w=100&fit=cover&mask=circle" width="100px;" alt="Usuario 1"/>
          <br />
          <sub><b>Jhan Carlos Carvajal</b></sub>
        </a>
      </td>
      <td align="center">
        <a href="https://github.com/Rockthor1106">
          <img src="https://images.weserv.nl/?url=https://avatars.githubusercontent.com/u/71021752?v=4&h=100&w=100&fit=cover&mask=circle" width="100px;" alt="Usuario 2"/>
          <br />
          <sub><b>Juan David Cruz</b></sub>
        </a>
      </td>
      <td align="center">
        <a href="https://github.com/GilmarAmezquita">
          <img src="https://images.weserv.nl/?url=avatars.githubusercontent.com/u/71054954?v=4&h=100&w=100&fit=cover&mask=circle" width="100px;" alt="Usuario 3"/>
          <br />
          <sub><b>Gilmar Andres Amezquita</b></sub>
        </a>
      </td>
      <td align="center">
        <a href="https://github.com/Juanperezaa">
          <img src="https://images.weserv.nl/?url=https://avatars.githubusercontent.com/u/71357770?v=4&h=100&w=100&fit=cover&mask=circle" width="100px;" alt="Usuario 4"/>
          <br />
          <sub><b>Sebastian Perez</b></sub>
        </a>
      </td>
    </tr>
  </table>
</div>

## Diagrama de Despliegue

<div>
  <img src="./Documents/README images/CoffeMachDeploy.png"/>
</div>

## Instrucciones de Despliegue

<p align="justify">
  Para el despliegue de los diferentes componentes del sistema distribuido se proporciono el acceso a las salas de computo LIASON de la
  Universidad ICESI, por lo que el despliegue aquí presente esta configurado para subirse a dichas maquinas. Sin embargo, es posible el
  cambio de ambiente simplemente modificando los nombres de los host en los archivos de configuración de cada uno de los sistemas.
</p>

### Base de Datos

<p align="justify">
  Para empezar con el despliegue, se requiere de una base de datos en la cual vamos a montar dos scripts que ya fueron proporcionados 
  por los profesores. La configuración que realizamos en nuestro proyecto se encuentra especificada a continuación:
</p>

<p align="justify">
  En el equipo <strong>hgrid2</strong> necesitamos crear un usuario en postgres. Para ello primero accedemos al <strong>usuario por 
  defecto de postgres</strong> con el comando <strong>su postrgres</strong> y la contraseña <strong>postgres</strong>
</p>
<p align="center"><img src="./Documents/README images/bd1.png" width="70%"/></p>

<p align="justify">
  Una vez ya en el usuario por defecto, utilizamos el comando <strong>psql postgres</strong> para poder ejecutar scripts dentro de la 
  instancia de postgres. De ahi, ejecutamos el <a href="./scripts/postgres/coffeemach-user.sql"><strong>script para crear usuario</strong></a>
  que tiene el siguiente contenido:
</p>
<p align="center"><img src="./Documents/README images/scriptUser.png" width="70%"/></p>
<p align="justify">
  El resultado de ejecutar este script se puede ver en las siguientes imagenes de la consola de <strong>hgrid2</strong>:
</p>
<p align="center"><img src="./Documents/README images/bd2.png" width="70%"/></p>
<p align="center"><img src="./Documents/README images/bd3.png" width="70%"/></p>

<p align="justify">
  Después salimos del usuario de postgres y entramos en el usuario que acabamos de crear con el script anterior. Para ello, usamos el
  comando <strong>psql -U postgres -h localhost carvi_coffeemachine</strong>
</p>
<p align="center"><img src="./Documents/README images/bd4.png" width="70%"/></p>

<p align="justify">
  Una vez logeado con las credenciales del script, ahora podemos proceder con la <strong>creación de las tablas</strong> en las que se van a
  almacenar diferentes objetos que utilizamos en el sistema, como los <strong>ingredientes, recetas y alarmas</strong>, esto lo hacemos con el
  <a href="./scripts/postgres/coffeemach-ddl.sql"><strong>script para crear tablas</strong></a>. Las tablas del script son:
</p>
<p align="center"><img src="./Documents/README images/scriptDDL.png" width="70%"/></p>
<p align="justify">
  El resultado final de esta creacion lo podemos ver asi:
</p>
<p align="center"><img src="./Documents/README images/bd5.png" width="70%"/></p>

<p align="justify">
  Después de la creacion de las tablas, ejecutamos el <a href="./scripts/postgres/coffeemach-inserts.sql"><strong>script para insertar datos
  en tablas</strong></a>. En este script podemos ver datos importantes para las maquinas como lo son los siguientes:
</p>
<p align="center"><img src="./Documents/README images/scriptInsert1.png" width="70%"/></p>

### Servidor Central
<p align="justify">
  Para el despliegue del <strong>Servidor Central</strong> decidimos utilizar la maquina <strong>hgrid3</strong>, con el puerto 9096. Ya que 
  este necesita conexión directa con la base de datos, entonces, en el <a hred="./coffeemach/ServidorCentral/src/main/resources/server.cfg">
  <strong>archivo de configuración del servidor</strong></a> añadimos lo necesario para que pueda acceder, esto es:
</p>
<p align="center"><img src="./Documents/README images/servidorCentralCfg.png" width="70%"/></p>

<p align="justify">
  Una vez realizada esta configuración, para desplegar el <strong>Servidor Central</strong> podemos realizar de forma local, es decir, desde
  el mismo computador de lal laboratorio, o por el contrario lo hacemos pos medio de <strong>PuTTy</strong>, activando el X11 y conectandonos
  al usuario <strong>swarch</strong> de la maquina.
</p>
<p align="center"><img src="./Documents/README images/putty1.png" size="70%"/></p>

<p align="justify">
  Ahora, para levantar el Servidor Central podemos compilar el proyecto en la maquina o pasarle directamente a esta el <strong>.jar</strong>
  y solo ejecutarlo. En nuestro caso, clonamos este respositorio y compilabamos en la maquina, por lo que, parandonos en el directorio del 
  Servidor Central: <a href="./coffeemach/ServidorCentral"><strong>./coffeemach/ServidorCentral</strong></a> desde la raiz de este proyecto,
  ejecutamos el comando <strong>gradle build</strong> y posteriormente ejecutamos el jar con <strong>java -jar build/libs/ServidorCentral.jar
  </strong>, luego se ejecutara la interfaz de usuario:
</p>
<p align="center"><img src="./Documents/README images/servidorCentralnterfaz.png" size="70%"/></p>

### Proxy-Cache
