Informations sur l'application WebCafe_backEnd:

Base de donnee :

-base utilisée actuellement : H2 
 (voir dans le dossier Sqlite les resultats obtnues avec Sqlite)

-Configuration de migration vers une bd MySQL Server :

 Etape 1: fichier pom.xml

 -Supression de la dependence H2 et ajout de la dependence MySQL
Etape 2: fichier configuration.properties

- Remplacer sonn contenu par la configuration suivante:
server.port=8080
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/bdtest
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect

Test de l'API:

L'api-Rest communique avec le client via les differentes methode HTTP , on utilisera 4 methodes principalement des ce projets:

-GET :pour recuperer des donnees
-Post: pour envoyer des donnees
-Delete: pour suprimer des donnees
-Update: pour mettre ajour des donnees existante

Ainsi pour pour pouvoir tester le bon fonctionnement de notre API , on aura besoin d'un outil qui genere ces differentes requetes HTTP .
On a testé deux outils : Postman , Insomnia. les deux sont efficaces.Pour la suite on va travailler avec Insomnia.












































