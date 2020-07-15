# Informations sur l'application WebCafe_backEnd:

## Présentation

Ce travail a pour objectif de reproduire le TP14 sous une architecture de Service web Rest tout en utilisant SpringBoot pour le backEnd et VueJs pour le FrontEnd.
Dans ce repertoire ,on notera de la configuration et des outils  utilisée dans la partie BackEnd.


### SpringBoot


-Création de projet SpringBoot: (Sur Eclipse)
  
   methode 1: Allez sur le Site https://start.spring.io/
             -Ce site genere des projets Spring boot en fonction de la configuration et le dependence qu'on lui demande

   Methode 2: A partir d'un projet Maven
             -Creer un projet Maven et ajouter manuellement, les dependences et les annotations de springboot.
   Methode 3 :installer Spring Tool Suite qui est un IDE basée sur eclipse 
   Methode 4 :(la plus facile) installer le plugin Spring Tool Suite dans Eclipse 
              

-Structure du projet:Tous les packages crées doivent etre des sub-packages du package contenant l'annotation @SpringBootApplication
(Voir explication en bas de ce document Section:Erreurs Frequentes)

-Configuration de projet SpringBoot:
la configuration se fait au niveau du fichier application.properties dans le dossier ressource.Pour connaitre quelles sont les possibilités de configuration , allez sur le site https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html .Vous y trouverez egalement la configuration par default

-Execution de l'application:
   methode 1 :(usuel) depuis votre IDE
   methode 2 :(IDE fermé) avec ligne de commande cmd
               se place dans le repertoire du projet avec la commande cd , puis exeuter la commande mvnw install (si vous etes sur windows) ,un dossier target va etre creer , placez vous dans target avec la commande cd target et executer ce fichier jar avec la commade java -jar nomfichier.jar

-Etat de l'application: Spring Boot Actuator 

(En cours de redaction

### Base de données :

-base utilisée actuellement : H2 
 (voir dans le dossier Sqlite les resultats obtnues avec Sqlite)

-Configuration de migration vers une bd MySQL Server :

 Etape 1: fichier pom.xml

 -Supression de la dependence H2 et ajout de la dependence MySQL
Etape 2: fichier application.properties

- Remplacer son contenu par la configuration suivante:

```
server.port=8080
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/bdtest
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
```

Remarque:Hibernate cree les colonnes de la table suivant un ordre alphabetique et non pas dans l'ordre que on a mentionné dans la classe.

### Test de l'API:

L'api-Rest communique avec le client via les differentes methode HTTP , on utilisera 4 methodes principalement des ce projets:

-GET :pour recuperer des donnees
-Post: pour envoyer des donnees
-Delete: pour suprimer des donnees
-PUT: pour mettre a jour des donnees existante

Etant donnee que la partie client n'est pas encore developpée on aura besoin d'un outil qui joue le role du client et nous qui genere ces differentes requetes HTTP .
On a testé deux outils : Postman , Insomnia. les deux sont efficaces.Pour la suite on va travailler avec Insomnia.

Pour envoyer des donnees depuis Insomnia ou postman , il est necessaire de configurer sur ces deux outils dans la section "Header" le "Content Type " à la valeur "Application/JSON".

Outils pour faciliter le developement:

-la dependence "DevTools" dans POM.xml permet de rafraichir automatiquement la page du navigateur à chaque modification du code, mais pour cela il faut aussi installer le plugin liveReload sur chrome


-la dependance "Lombok" dans POM.xml . Lombok donne acces à des annotations qui permettent au developpeur d'ecrire moins de code
exemple .@AllArgsConstructor cree un constructeur avec tout les parametere, @Getter cree tout les getters , @Data cree tout les getters et setters et les methodes tostring, Equals ,HashCode
Remarque: Ajouter lombok necessite ajouter sa dependance dans pom.xml et de l'installer sur la machinedepuis https://projectlombok.org/download 
(J'ai supprimé ces deux dependance "Lombok" et "DevTools" à partir des commit du 15/06/2020 pour faciliter l'execution de l'application sur d'autes machines)

### Erreurs Frequentes:

- Serveur already in use .  Explication : c'est une application Jar , du coup l'application possede un embedded tomcat server . A chaque execusion du programme l'application essaye de demarrer une nouvelle instance de tomcat et non pas redemarer l'ancienne instance. Il arrive donc que l'application essaye de demarer une nouvelle instance de tomcat sur le port 8081 alors que l'ancienne instance est encore en cours d'execution sur ce meme port.
Donc arreter l'ancien serveur avant de reexecuter l application. 

-[WARNING] The requested profile "pom.xml" could not be activated because it does not exist. (cette erreur ce produit souvent apres l'excution de maven clean)

click droit sur le projet ----->properties---->Maven-->Changez l'etat de la case (Resolve dependencies from WorkSpace Projects) puis--->Applyand Close-->Executer l'application.


-Erreur STACKOVERFLOWError :ajouter l'annotation  @JsonManagedReference  dans l'entité pere et @JsonBackReference  dans l'entite fils. 

-Erreur liée à la structure du projet: il faut que tous les package  que l'on souhaite creer soient des fils du package contenant la classe annotée @SpringBootApplication.
explication: au demarrage de l'application spring scan tout les sub-packages pour voir quels sont les beans que le conteneur IoC de spring doit instancier et gerer.Ainsi les packages siblings du package main ne sont pas scanés et par consequent leurs objets beans ne seront pas instancier.Cela est le fonctionnement par defaut.Pour Scanner les packages siblinges du package main il va falloir ajouter l'annotation @ComponentScan.

###Authentification

Les dependances POM.XML necessairent pour mettre en place l'authetification sont:
```
        <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-api</artifactId>
			<version>0.11.2</version>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-impl</artifactId>
			<version>0.11.2</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-jackson</artifactId> <!-- or jjwt-gson if Gson is preferred -->
			<version>0.11.2</version>
			<scope>runtime</scope>
		</dependency>
```

Pour une meilleure clarete du code , on a rassembler tout le code d'authetification dans le package auth.

Le path d'authentification utilise n'a pas été definie dans le code ,on a utilisé definie  par defaut par spring ,soit "localhost:8081/login" .


