#!/bin/bash

password="swarch"
path="JJJG-CoffeeMach"

function gradleBuild {
    id=$1
    newMachineId=$id
    sed -i "s|^CoffeMach.Endpoints = default -h localhost -p 9096|CoffeMach.Endpoints=default -h hgrid$newMachineId -p 9096|" ../../coffeemach/coffeeMach/src/main/resources/coffeMach.cfg

    sed -i "s/^1/$newMachineId/" ../../coffeemach/coffeeMach/codMaquina.cafe
    ../../coffeemach/./gradlew build -p ../../coffeemach/coffeeMach &
    wait

    echo "Modified conf file: coffeemach/coffeeMach/src/main/resources/coffeMach.cfg"
    echo "New CoffeMach.Endpoints line: CoffeMach.Endpoints=default -h hgrid$newMachineId -p 9096"
}

IFS=',' read -ra array_machines <<< "$1"

gradleBuild ${array_machines[0]}

server_id="swarch@xhgrid3"
echo "Deploying to $server_id"
sshpass -p $password ssh $server_id "rm -rf $path && mkdir -p $path"
sshpass -p $password scp ../../coffeemach/ServidorCentral/build/libs/ServidorCentral.jar $server_id:./$path

echo "Deploying to xhgrid5"
sshpass -p $password ssh swarch@xhgrid5 "rm -rf $path && mkdir -p $path"
sshpass -p $password scp ../../coffeemach/reliableMessage/build/libs/reliableMessage.jar swarch@xhgrid5:./$path

echo "Deploying to xhgrid7"
sshpass -p $password ssh swarch@xhgrid7 "rm -rf $path && mkdir -p $path"
sshpass -p $password scp ../../coffeemach/ProxyCache/build/libs/ProxyCache.jar swarch@xhgrid7:./$path

for ((i = 0; i < ${#array_machines[@]}; i++)); do
    machine=${array_machines[i]}
    gradleBuild $machine

    echo "Deploying to xhgrid$machine"
    sshpass -p $password ssh swarch@xhgrid"$machine" "rm -rf $path && mkdir -p $path"
    sshpass -p $password scp ../../coffeemach/coffeeMach/build/libs/coffeeMach.jar swarch@xhgrid"$machine":./$path
    sshpass -p $password scp ../../coffeemach/coffeeMach/codMaquina.cafe swarch@xhgrid"$machine":./$path

    echo "Machine $machine ready"
done
