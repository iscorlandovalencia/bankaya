# Bankaya Collect Pokemon Api 

## General Instructions

This application consumes the API rest from [Pokeapi](https://pokeapi.co/) and create a SOAP endpoint, and rest APi are created also for the full functionality.

### Prerequisites

Please ensure you have the following installed:

- Java 17
- Maven 3
- Git
- Docker
- Lombok
- An IDE IntelliJ IDEA or Eclipse

Make sure your IDE has Lombok installed for proper functionality

- [Lombok for Eclipse](https://projectlombok.org/setup/eclipse)
- [Lombok for Intelij](https://projectlombok.org/setup/intellij)

### Instructions

- To run the project go to the folder location and open a cmd 
- Take the ini.sh file and execute it 

```
cd %project_path%/. init.sh

```

This file contain the full configuration for init the application in docker.

There is an option to rebuild the project that removes the application container from docker and create it again with the new changes.

For this option use 

```
cd %project_path%/. start.sh

```

### URL APIs

To run swagger please use this URL once the project is running
[http://localhost:8090/swagger-ui/index.html#/](http://localhost:8090/swagger-ui/index.html#/)

There are all api information for controller

- GET /api/pokemon/{id}
	- This URL get a pokemon from the id


- GET /api/pokemon/pokeapi
	- This URL get all the list of pokemon from pokeapi
	
	
- GET /api/pokemon/location/{name}
	- This URL get the area location encounters from a pokemon by name
	
	
- GET /api/pokemon/items/{name}
	- This URL get the list of held items from the pokemon name
	
	
- GET /api/pokemon/id/{name}
	- This URL get the Id from a pokemon name
	
	
- GET /api/pokemon/experience/{name}
	- This URL get the experience from a pokemon name
	
	
- GET /api/pokemon/ability/{name}
	- This URL get the abilities from a pokemon name
	
	

