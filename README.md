# API JAVA 

Projet de BUT 2ème : réalisation d'une API JAVA qui gère des plats et des utlisateurs.

Dépendances xml à rajouter au Projet Jakarta pour faire fonctionner l'API : 

```
<dependency>
  <groupId>org.mariadb.jdbc</groupId>
  <artifactId>mariadb-java-client</artifactId>
  <version>LATEST</version>
</dependency>
<dependency>
  <groupId>org.json</groupId>
  <artifactId>json</artifactId>
  <version>20240303</version>
</dependency>```

# Fonctionnalités Plats et Utilisateurs  

Mathieu Leroux - BUT2-A1

## Route /user

 - Lien : [http://51.15.238.170:8080/rapidamanger-1.0-SNAPSHOT-moi/api/user]

### GET
 Permet de récupérer la liste des utilisateurs sous formes d'un array json. 
 (voir get /user/{id})

```curl --request "GET" http://51.15.238.170:8080/rapidamanger-1.0-SNAPSHOT-moi/api/user```


### POST
Permet de créer un utilisateur.
Entrée :
```json
{
	"name": "...",
	"password": "...",
	"address": "..."
}
```
Sortie, renvoie l'id de l'utilisateur crée :
```json
{
	"id": 1
}
```	

```curl --request POST --header "Content-Type: application/json" --data '{"login":"testName", "password":"testPwd", "address":"testAddress"}' http://51.15.238.170:8080/rapidamanger-1.0-SNAPSHOT-moi/api/user```
  
## Route /user/{id}

### GET
 Permet de récupérer un utilisateur selon id .
 Le mot de passe n'est volontairement pas renvoyé pour des raisons de sécurité.
Sortie :
```json
{
	"id":1,
	"login":"Mathieu",
	"address":"La Bouilladisse"
}
```	

```curl --request "GET" http://51.15.238.170:8080/rapidamanger-1.0-SNAPSHOT-moi/api/user/4```

### PUT
Permet de modifier un utilisateur.
Il est possible de modifier le nom, le mot de passe et l'adresse.
Entrée :
```json
{
	"name": "newName",
	"password": "newPassword"
}
```
Sortie, renvoie l'id de l'utilisateur modifié :
```json
{
	"id": 1
}
```	
```curl --request PUT --header "Content-Type: application/json" --data '{"address":"Une Adresse"}' http://51.15.238.170:8080/rapidamanger-1.0-SNAPSHOT-moi/api/user/3```

### DELETE
 Permet de supprimer un utilisateur selon id .
Sortie, renvoie l'id de l'utilisateur supprimé :
```json
{
	"id":1,
}
```	
```curl --request "DELETE" http://51.15.238.170:8080/rapidamanger-1.0-SNAPSHOT-moi/api/user/8```

## Route /user/{name}

### POST
 Permet de vérifier l'authentification d'un utilisateur .
 Entrée :
```json
{
	"password": "...",
}
```
Sortie, si l'authentification a réussie, l'id de l'utilisateur connecté est renvoyé :
```json
{
	"id":1,
}
```	
Sortie, si l'authentification a échoué :
```json
{
	"id":false,
}
```	
```curl --request POST --header "Content-Type: application/json" --data '{"password":"***"}' http://51.15.238.170:8080/rapidamanger-1.0-SNAPSHOT-moi/api/user/Mathieu```

## Route /dish

 - Lien : [http://51.15.238.170:8080/rapidamanger-1.0-SNAPSHOT-moi/api/dish]

### GET
 Permet de récupérer la liste des plats sous formes d'un array json. 
 (voir get /dish/{id})
 ```curl --request "GET" http://51.15.238.170:8080/rapidamanger-1.0-SNAPSHOT-moi/api/dish```

### POST
Permet de créer un plat.
Entrée :
```json
{
	"name": "...",
	"description": "...",
	"price": "..."
}
```
Sortie, renvoie l'id du plat crée :
```json
{
	"id": 1
}
```	
```curl --request POST --header "Content-Type: application/json" --data '{"name":"***", "description":"***","price":"4"}' http://51.15.238.170:8080/rapidamanger-1.0-SNAPSHOT-moi/api/dish```
  
## Route /dish/{id}

### GET
 Permet de récupérer un plat selon id .
Sortie :
```json
{
	"id":1,
	"name":"Carbonara",
	"description":"des pâtes",
	"price": 8.0
}
```	
```curl --request "GET" http://51.15.238.170:8080/rapidamanger-1.0-SNAPSHOT-moi/api/dish/4```

### PUT
Permet de modifier un plat.
Il est possible de modifier le nom, la description et le prix du plat.
Entrée :
```json
{
	"description": "newDescription"
}
```
Sortie, renvoie l'id du plat modifié :
```json
{
	"id": 1
}
```	
```curl --request PUT --header "Content-Type: application/json" --data '{"price":"12"}' http://51.15.238.170:8080/rapidamanger-1.0-SNAPSHOT-moi/api/dish/10```

### DELETE
 Permet de supprimer un plat selon id .
Sortie, renvoie l'id du plat supprimé :
```json
{
	"id":1,
}
```	
```curl --request "DELETE" http://51.15.238.170:8080/rapidamanger-1.0-SNAPSHOT-moi/api/dish/26```
