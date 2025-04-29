# Employee Management App

A simple CRUD web application built using **Jakarta EE 10**, **PrimeFaces 12**, and **Payara 6**. This application allows you to add, view, update, and delete employee records stored in a MySQL database.

## 🛠 Technologies Used
- Java 21
- Jakarta EE 10
- PrimeFaces 12
- Payara Server 6
- MySQL 8
- Maven
- NetBeans 24

## 📁 Project Structure
```
EmployeeApplication/
├── src/main/java/com/employeeapp/   # Java EE code (beans, model, DAO)
├── src/main/webapp/                # JSF XHTML pages (views)
│   ├── index.xhtml
│   └── employee-form.xhtml
├── pom.xml                         # Maven configuration
└── README.md
```

## ⚙️ Setup Instructions

1. **Clone the repository:**
```bash
git clone https://github.com/MiruJacob/employeeapp.git
cd EmployeeApplication
```

2. **Configure MySQL database:**
Create a database and user:
```sql
CREATE DATABASE employeedb;
CREATE USER 'employeeuser'@'localhost' IDENTIFIED BY 'employeepass';
GRANT ALL PRIVILEGES ON employeedb.* TO 'employeeuser'@'localhost';

CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100),
    phone_number VARCHAR(20),  
    department VARCHAR(50),
    salary DOUBLE
);

```

3. **Run the app on Payara Server:**
- Deploy the WAR via NetBeans or Payara Admin Console.
- Access at: `http://localhost:8080/EmployeeApplication/index.xhtml`

## 🧪 Features
- Add new employee
- View all employees
- Edit existing employee
- Delete employee
- Navigation between form and listing

## 🧹 TODO
- Add pagination and sorting
- Form validation improvements
- Dockerize app
- Add unit tests

## 👨‍💻 Author
Jake Miru

---
Feel free to contribute or fork the project!
