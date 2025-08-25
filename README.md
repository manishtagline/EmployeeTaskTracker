# 🚀 Employee Task Tracker  
---

## 📖 Overview
**Employee Task Tracker** is a Spring Boot–based web application for managing employees and their assigned tasks.  
It provides **secure login**, **role-based access**, **task assignment & tracking**, and **email notifications**.

- 👨‍💼 **Admin** → Manage employees, assign tasks, and track progress
- 👩‍💻 **Employee** → View assigned tasks and update status

---

## ✨ Features
- 🔐 **User Authentication & Roles** – JWT + Spring Security (Admin & Employee)
- 📝 **Task Management** – Create, update, delete, assign tasks, update progress
- 📧 **Email Notifications** – Task updates & assignment emails
- 📊 **Dashboards** – Separate views for Admin and Employee
- 📘 **API Docs** – Swagger UI integrated REST API documentation
- ⚡ **Validation & Error Handling** – Centralized error handling & validations

---

## 🛠️ Tech Stack

| Layer         | Technology |
|---------------|------------|
| **Backend**   | Java 21, Spring Boot 3.x |
| **Security**  | Spring Security, JWT |
| **Database**  | MySQL, Spring Data JPA (Hibernate) |
| **Email**     | Spring Boot Mail (JavaMailSender) |
| **API Docs**  | Swagger / OpenAPI (springdoc) |
| **Utilities** | Lombok, Spring DevTools |
| **Build**     | Maven |

---

## 📦 Dependencies
- `spring-boot-starter-web` – REST + MVC
- `spring-boot-starter-data-jpa` – Hibernate ORM
- `spring-boot-starter-security` – Authentication/Authorization
- `mysql-connector-j` – MySQL JDBC driver
- `jjwt` – JWT handling
- `spring-boot-starter-mail` – Email support
- `springdoc-openapi-ui` – Swagger UI docs
- `lombok` – Reduce boilerplate
- `spring-boot-devtools` – Hot reload

---

## 📂 Project Structure  
EmployeeTaskTracker/
├── src/main/java/com/example/employeetasktracker
│ ├── controller/ # REST & MVC controllers
│ ├── model/ # Entities (Employee, Task, User, Role)
│ ├── repository/ # Spring Data JPA repositories
│ ├── service/ # Business logic
│ ├── security/ # JWT filters, config, authentication
│ └── EmployeeTaskTrackerApplication.java
│
├── src/main/resources/
│ ├── application.properties # DB, Mail, Security configs
│ ├── static/ # CSS, JS
│ └── templates/ # Thymeleaf templates
│
├── pom.xml
└── README.md


---

## ⚙️ Getting Started

### 🔹 Prerequisites
- ☕ Java 21+
- 🐬 MySQL
- 🛠️ Maven
- 📂 Git

### 🔹 Clone Repository
```bash
git clone https://github.com/manishtagline/EmployeeTaskTracker.git
cd EmployeeTaskTracker

🔹 Configure Database & Mail (application.properties)
spring.datasource.url=jdbc:mysql://localhost:3306/employeetaskdb
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Email
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_email_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

🔹 Run the Application
mvn spring-boot:run

🔹 Access

🌐 Web App → http://localhost:8080

📘 Swagger Docs → http://localhost:8080/swagger-ui.html

📊 Usage

👨‍💼 Admin → Login, manage employees, assign tasks, monitor progress

👩‍💻 Employee → Login, view tasks, update status

🧪 Running Tests
mvn test

🤝 Contributing

Fork the repo

Create feature branch → git checkout -b feature/my-feature

Commit changes → git commit -m "Add new feature"

Push branch → git push origin feature/my-feature

Open a Pull Request

👨‍💻 Author

Developed by Manish Giri

📜 License

Licensed under the MIT License.

