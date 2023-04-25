create user carvicoffeemach with password 'carvi';
create database carvi_coffeemachine owner carvicoffeemach;
grant connect on database carvi_coffeemachine to carvicoffeemach;

-- Connection
-- psql -d coffeemachine -U p09713_1_2
