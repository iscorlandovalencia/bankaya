# Stop and Elimiate pokeapi containers
docker stop pokeapicontainer
docker rm pokeapicontainer

#Eliminate pokeapi image
docker rmi -f pokeapi

docker start mysqlcontainer

# build pokeapi application
mvn clean package compile install -DskipTests

# create the pokeapi image
docker build -t pokeapi .

# run pokeapi application container using mysql conection (Should be already created, check init.sh to verify network connection)
docker run -p 8090:8080 --name pokeapicontainer --net networkmysql -e MYSQL_HOST=mysqlcontainer -e MYSQL_PORT=3306 -e MYSQL_DBNAME=pokeapi -e MYSQL_USER=root -e MYSQL_PASSWORD=root pokeapi