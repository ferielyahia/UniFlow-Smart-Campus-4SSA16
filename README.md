# UniFlow – Smart Campus

Plateforme de gestion universitaire basée sur une architecture microservices.
Projet du module AWD (ESPRIT, 2026-2027) – classe 4SSA16.

## Présentation

UniFlow Smart Campus coordonne les activités d'un campus : étudiants, cours, emplois du temps, présences, salles et vie universitaire. Chaque domaine est un microservice indépendant avec sa propre base de données.

## Objectifs

- Centraliser la gestion des étudiants
- Gérer les cours et les emplois du temps
- Suivre les présences et les absences
- Gérer les salles et les réservations
- Organiser les événements de la vie universitaire

## Équipe

| Membre | Microservice | Base de données |
|---|---|---|
| Feriel | Student Service | MySQL |
| Haifa | Course Service | H2 |
| Lina | Timetable Service | H2 |
| Ferdaws | Attendance Service | H2 |
| Syrine | Room Service | MySQL |
| Farah | Campus Life Service | MySQL |

## Architecture

![Architecture globale](Documentation/Diagrams/architecture_globale.png)

- **Angular** : application cliente
- **API Gateway** (Spring Cloud Gateway) : point d'entrée unique, contrôle du JWT
- **Eureka** : découverte des services
- **Config Server** : configuration centralisée
- **Keycloak** : authentification et rôles (STUDENT, TEACHER, ADMIN, MANAGER)
- **RabbitMQ** : communication asynchrone
- **Docker / Docker Compose** : conteneurisation

Une base de données par service, aucune base partagée. Communication synchrone via REST / OpenFeign, asynchrone via RabbitMQ.

## Technologies

- Java / Spring Boot, Spring Data JPA
- Spring Cloud (Gateway, Eureka, Config Server, OpenFeign)
- Keycloak
- RabbitMQ
- MySQL / H2
- Angular
- Docker / Docker Compose
- Swagger / OpenAPI
- Git / GitHub, Trello

## Structure du projet

```
UniFlow-Smart-Campus-4SSA16/
├── Backend/            un projet Spring Boot par microservice
├── Frontend/           application Angular
├── Documentation/
│   ├── Presentation/   présentation de la séance 4
│   └── Diagrams/       diagrammes d'architecture et de conception
└── README.md
```

Le dossier technique se trouve dans `Documentation/`.

## Lancement

Chaque microservice se lance depuis son dossier :

```bash
cd Backend/<nom-du-service>
mvn spring-boot:run
```

La documentation Swagger de chaque service est disponible sur `/swagger-ui.html`.
Le lancement de l'ensemble avec Docker Compose sera ajouté pendant la phase de développement.

## Organisation du travail

- Gestion de projet : Trello (lien :https://trello.com/invite/b/6af50b2212803533adfffa06/ATTIf6a460b42bc3480937d2542dbf77dfb0F99FCFAA/mon-tableau-trello)
- Branche `main` et une branche `feature/<service>` par membre, Pull Request vers `main`


