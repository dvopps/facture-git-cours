# Image de base : Java 21, environnement d'exécution seulement
FROM eclipse-temurin:21-jre

# Dossier de travail dans le conteneur
WORKDIR /app

# Copier l'artefact produit par ./mvnw clean package
COPY target/facture-1.0-SNAPSHOT-jar-with-dependencies.jar facture.jar

# Commande lancée au démarrage du conteneur
ENTRYPOINT ["java", "-Dstdout.encoding=UTF-8", "-jar", "facture.jar"]