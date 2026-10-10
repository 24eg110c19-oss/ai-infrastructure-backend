# ⚙️ AI Infrastructure Learning & Evolution Platform — Backend

### Spring Boot REST API | PostgreSQL | Infrastructure Monitoring | AI-Assisted Analysis

The backend provides REST APIs for the AI Infrastructure Learning & Evolution Platform. It supports user management, infrastructure monitoring, resource simulation, AI-assisted analysis, alerts, and learning progress tracking.

## 🌟 Features

- 👤 **User Management** — User registration, login, search, update, and deletion.
- 📊 **Infrastructure Monitoring** — CPU, RAM, disk, and GPU usage metrics.
- 🖥️ **Simulation Engine** — Simulate normal and high-resource usage scenarios.
- 🤖 **AI-Assisted Analysis** — Analyze infrastructure metrics and provide recommendations.
- 🔔 **Alert Management** — Retrieve, delete, and clear infrastructure alerts.
- 📈 **Alert Statistics** — View generated alert statistics.
- 📚 **Learning Progress** — Save, retrieve, and remove learning progress.
- 🗄️ **Database Integration** — Store application data using PostgreSQL.

## 🛠️ Technology Stack

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate ORM
- PostgreSQL
- Maven
- Docker
- REST APIs

## 🏗️ System Architecture

```text
React + Vite Frontend
          |
          | HTTP / REST API
          v
Spring Boot Backend
          |
          +---- User Management
          |
          +---- Infrastructure Monitoring
          |
          +---- Simulation Engine
          |
          +---- AI-Assisted Analysis
          |
          +---- Alert Management
          |
          +---- Learning Progress
          |
          v
     PostgreSQL Database
```

## 🔗 Project Links

- **Backend Repository:** https://github.com/24eg110c19-oss/ai-infrastructure-backend
- **Frontend Repository:** https://github.com/24eg110c19-oss/ai-infrastructure-frontend
- **Frontend Deployment:** https://ai-infrastructure-frontend.vercel.app/
- **Backend Deployment:** https://ai-infrastructure-backend.onrender.com

> **Deployment note:** The backend deployment requires a valid hosted PostgreSQL connection. Successful online operation has not yet been confirmed.

## 📡 API Reference

| Method | Endpoint | Description |
|---|---|---|
| GET | `/users` | Retrieve users |
| POST | `/users/register` | Register a user |
| POST | `/users/login` | Log in a user |
| GET | `/monitoring` | Retrieve infrastructure metrics |
| GET | `/simulation?type=normal` | Run a normal simulation |
| GET | `/ai/analyze?cpu=26&ram=86&disk=39&gpu=2` | Analyze infrastructure metrics |
| GET | `/alerts` | Retrieve alerts |
| GET | `/alert-statistics` | Retrieve alert statistics |
| GET | `/learning-progress/{userId}` | Retrieve learning progress |
| POST | `/learning-progress` | Save learning progress |
| DELETE | `/learning-progress/{userId}/{moduleId}` | Remove learning progress |

*Verify endpoint paths against the current source code before relying on this API reference.*

## ⚙️ Run Locally

### Prerequisites

- Java compatible with the project configuration
- Maven
- PostgreSQL

### 1. Clone the repository

```bash
git clone https://github.com/24eg110c19-oss/ai-infrastructure-backend.git
cd ai-infrastructure-backend
```

### 2. Configure the database

Create a PostgreSQL database named `aiinfra`, or configure your own database.

Example local JDBC URL:

```text
jdbc:postgresql://localhost:5432/aiinfra
```

Configure the database URL, username, and password in your local Spring Boot configuration.

**Security:** Never commit database passwords, API keys, or other secrets to GitHub.

### 3. Run the backend

```bash
mvn spring-boot:run
```

Use the port configured in your application. The local backend has previously used port `8081`.

### 4. Test the API

After starting the backend, open:

```text
http://localhost:8081/monitoring
```

You can also test endpoints using Postman or Thunder Client.

## ☁️ Deployment

The backend is configured for deployment on Render.

1. Connect the backend GitHub repository to Render.
2. Configure the hosted PostgreSQL database connection.
3. Set the required datasource environment variables.
4. Configure CORS to allow requests from the Vercel frontend.
5. Deploy the service and check the application logs.
6. Test the monitoring endpoint before connecting the frontend.

Common datasource environment variables:

- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`

## 🎯 Project Objective

The backend provides the APIs needed to teach infrastructure concepts through monitoring, simulations, alerts, learning modules, and AI-assisted explanations.

**Learning approach:** Learn → Understand → Experiment → Use AI Assistance → Make Human Decisions → Understand the Next Technology.

## 🔮 Future Enhancements

- Historical infrastructure metrics
- Predictive resource analysis
- Kubernetes and container simulations
- Advanced AI troubleshooting
- Learning analytics and achievement tracking

## 📄 License

Add a LICENSE file if you intend to distribute this project under an explicit open-source license.
