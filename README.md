# Drones
Drone dispatch:
spring boot maven project

```
git clone https://github.com/yrosales/Drones.git
cd Drones
mvnw package
java -jar target/*.jar
```
or
```
mvnw spring-boot:run
```
## Database configuration

In its default configuration, Drones uses an in-memory database (H2) which
gets populated at startup with data. The h2 console is exposed at `http://localhost:8080/h2-console`,
and it is possible to inspect the content of the database using the `jdbc:h2:mem:dronesdb` url.

You can then access drones at http://localhost:8080/

API

## fetch drone list
Method GET: 
```
http://localhost:8080/drones
```

## register a drone
Method POST: 
```
http://localhost:8080/drones/
```
body:
```
{
    "id": 26,
    "serial": "102026",
    "model": "Cruiserweight",
    "weigthLimit": 150,
    "batteryCapacity": 100,
    "state": "IDLE"
}
```

## update drone
Method PUT: 
```
http://localhost:8080/drones/26
```
body:
```
{
    "weigthLimit": 500,
    "batteryCapacity": 100
}
```

## battery level
Method GET: 
```
http://localhost:8080/drones/battery-level/26
```

## available drones
Method GET: 
```
http://localhost:8080/drones/availables
```

## get medications load on a dron
Method GET: 
```
http://localhost:8080/drones/medications/21
```

## load medication on a dron
Method PUT: 
```
http://localhost:8080/drones/load/22/3
```

## update dron state
Method PUT: 
```
http://localhost:8080/drones/state/21
```
body:
```
{
    "state": "LOADED"
}
```
