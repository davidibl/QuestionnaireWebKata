Spring Boot basierter Server für das Questionnaire Kata.

Diese Anwendung bietet einen einfachen Spring Boot basierten Server mit REST API
für den Angular2 Client unter:
https://github.com/davidibl/questionnaire-web-kata-client

Voraussetzung für die Ausführung des Servers ist installiertes Java 8 und Maven.
Mit folgenden Befehlen kann der Server gebaut und gestartet werden:

- In das Projekt Verzeichnis wechseln
- mvn clean install
- in das Verzeichnis target wechseln
- java -jar QuestionnaireWebKata-1.0.0-SNAPSHOT.jar

Der Server wird Standardmäßig unter localhost:9002 ausgeführt. Um einen anderen Port zu
nutzen muss beim Startbefehl die Option: -Dserver.port=xxxx angegeben werden.

Der Angular2 Client muss dann entsprechend Konfiguriert werden.

Viel Spass
David Ibl