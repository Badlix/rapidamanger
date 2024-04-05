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

# Description des fonctionnalités

route /user

GET : permet de récupérer la liste des utilisateurs, les requêtes GET ne revoient pas le champs "password" de l'utilisateur

POST : permet de créer un utilisateur, le corps de la requete doit comporter, le login, le mot de passe et l'adresse de l'utilisateur. La requete renvoit l'id de l'utilisateur crée.

route /user/{id}

GET : permet de récupérer un utilisateur selon un id, pour des raison de sécurité, les requêtes GET ne revoient pas le champs "password" de l'utilisateur

DELETE : permet de supprimer un utilisateur selon un id.  La requete renvoit l'id de l'utilisateur supprimer.

PUT : permet de modifier certains champs de l'utilisateir selon un id, les champs à modifier ainsi que leurs nouvelles valeurs devront être précisé dans le corps de la requête. La requete renvoit l'id de l'utilisateur modifié.

route /user/{login}

GET : cette requête prend un paramètre un mot de passe. Si le mot de passe correspond au login présent dans la requête, alors la requête renvoit l'id de l'utilisateur, sinon la requête renvoit false.


