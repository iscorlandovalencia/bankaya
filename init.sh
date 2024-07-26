#Elimiate all containers
docker rm -vf $(docker ps -a -q)

#Eliminate all images
docker rmi -f $(docker images -a -q)

# Pull mysql
docker pull mysql

# Run mysql container
docker run -p 3307:3306 --name mysqlcontainer -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=pokeapi -d mysql

# docker network create ${network_name}
docker network create networkmysql

# docker network connect ${network_name} ${container_name}
docker network connect networkmysql mysqlcontainer

# build application
mvn clean package compile install -DskipTests

# create the image
docker build -t pokeapi .

# run application container using mysql conection
docker run -p 8090:8080 --name pokeapicontainer --net networkmysql -e MYSQL_HOST=mysqlcontainer -e MYSQL_PORT=3306 -e MYSQL_DBNAME=pokeapi -e MYSQL_USER=root -e MYSQL_PASSWORD=root pokeapi