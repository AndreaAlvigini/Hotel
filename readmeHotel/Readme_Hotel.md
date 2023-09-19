creazione archetipo webapp

- mvn archetype:generate -D"groupId"=com.example -D"artifactId"=GestioneClienti -D"archetypeArtifactId"=maven-archetype-webapp -D"interactiveMode"=false

compila

- mvn compile

clean

- mvn clean

install

- mvn install

fai partire jetty

- mvn jetty:run

vai alla pagina del sito

- localhost:8080/clienti