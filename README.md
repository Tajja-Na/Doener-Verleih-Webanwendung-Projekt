# Döner-Verleih Webbasierte Anwendung

Diese Webanwendung dient der Verwaltung und Verleih von „Döner“-Objekten und wurde im Rahmen des Moduls Webbasierte Anwendungen im Studiengang Medieninformatik entwickelt. Im Fokus standen die Umsetzung einer Full-Stack-Architektur mit klar getrennten Backend- und Frontend-Komponenten, inklusive Datenpersistenz, Authentifizierung und interaktiver Benutzeroberfläche.

---

## Projektübersicht

- **Name:** Döner-Verleih 
- **Modul:** Webbasierte Anwendungen
- **Studiensemester:** 4. Semester Medieninformatik  
- **Umfang:** Full-Stack Webanwendung  
- **Ziel:** Realisierung einer webbasierten Verleihplattform mit sicherer API-Kommunikation, Nutzerverwaltung und Echtzeit-Funktionalität

---

## Architektur

### Backend

Das Backend basiert auf **Spring Boot** und stellt eine REST-API zur Verfügung.

- **Spring Boot** als Framework  
- **Spring Web** zur Bereitstellung von HTTP-Ressourcen  
- **Spring Security** zur Absicherung und Authentifizierung  
- **JWT (JSON Web Token)** für tokenbasierte Sessions  
- Persistenz über **JPA** und **H2** In-Memory-Datenbank  
- **WebSocket + STOMP** für bidirektionale Echtzeit-Kommunikation  
- Serverseitige Templates via **Thymeleaf**

### Frontend

Das Frontend ist eine moderne Single-Page-Application:

- **Vue 3** als Framework  
- **Vite** als Entwicklungs- und Build-Tool  
- **Pinia** für State-Management  
- **Vue Router** für Navigation  
- **STOMPJS** für WebSocket-basierte Echtzeit-Interaktionen  
- **TypeScript** zur Typensicherheit im Frontend  

---

## Hauptfunktionen

- REST-API zur Verwaltung von Döner-Objekten und Nutzern  
- Nutzerregistrierung und Login mit JWT-Authentifizierung  
- Rollenkonzept für unterschiedliche Berechtigungen  
- Persistente Datenhaltung über JPA (H2-Datenbank)  
- Dynamisches, clientseitiges Frontend mit Vue 3  
- Echtzeit-Funktionen via WebSocket & STOMP  
- Fehler- und Validierungslogik im Front- und Backend  
- Übersetzungsworkflows für mehrsprachige Oberfläche  

---

## Verwendete Technologien

### Backend

- Java 21  
- Spring Boot 3.4  
- Spring Web  
- Spring Security  
- JWT / JSON Web Tokens  
- Spring Data JPA  
- H2 In-Memory-Datenbank  
- Spring Boot WebSocket + STOMP  
- MapStruct für DTO-Mapping

### Frontend

- Vue 3  
- Vite  
- Pinia  
- Vue Router  
- STOMPJS  
- TypeScript  
- ESLint & Prettier

---

# Contributors

- Nadia Tajja
