#!/bin/bash

password="swarch"
path="JJJG-CoffeeMach"

function gradleBuild {
    id=$1
    newMachineId=$id
    sed -i "s|^CoffeMach.Endpoints=default -h hgrid7 -p 9097|CoffeMach.Endpoints=default -h hgrid$newMachineId -p 9097|" ../../coffeemach/coffeeMach/src/main/resources/coffeMach.cfg

    sed -i "s|^ProxyRM.Endpoints = tcp -h hgrid7 -p 9096|ProxyRM.Endpoints = tcp -h hgrid$newMachineId -p 9096|" ../../coffeemach/reliableMessage/src/main/resources/ReliableMessage.cfg

    sed -i "s/^1/$newMachineId/" ../../coffeemach/coffeeMach/codMaquina.cafe
    ../../coffeemach/./gradlew build -p ../../coffeemach/coffeeMach &
    wait

    echo "Modified conf file: coffeemach/coffeeMach/src/main/resources/coffeMach.cfg"
    echo "New CoffeMach.Endpoints line"
    echo "Modified conf file: coffeemach/reliableMessage/src/main/resources/ReliableMessage.cfg"
    echo "New ProxyRM.Endpoints"
}

IFS=',' read -ra array_machines <<< "$1"

gradleBuild ${array_machines[0]}

for ((i = 0; i < ${#array_machines[@]}; i++)); do
    machine=${array_machines[i]}
    gradleBuild $machine

    echo "Deploying to xhgrid$machine"
    sshpass -p $password ssh swarch@xhgrid"$machine" "rm -rf $path && mkdir -p $path"
    sshpass -p $password scp ../../coffeemach/coffeeMach/build/libs/coffeeMach.jar swarch@xhgrid"$machine":./$path
    sshpass -p $password scp ../../coffeemach/coffeeMach/codMaquina.cafe swarch@xhgrid"$machine":./$path

    echo "Machine $machine ready"
done
