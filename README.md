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
