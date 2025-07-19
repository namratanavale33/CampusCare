
## 📑 Table of Contents
Home page
<img width="1919" height="824" alt="Screenshot 2025-07-19 202358" src="https://github.com/user-attachments/assets/9b8cf9b1-0b68-40f4-935a-603cf77f8a83" />
Admin Dashboard
<img width="1914" height="703" alt="Screenshot 2025-07-19 202656" src="https://github.com/user-attachments/assets/564390bc-aeef-4fe8-b2ff-a502c7a4168e" />
<img width="1890" height="814" alt="Screenshot 2025-07-19 202829" src="https://github.com/user-attachments/assets/224c7c78-89cc-4fd1-958d-f7332178e3c8" />
<img width="1891" height="680" alt="Screenshot 2025-07-19 202804" src="https://github.com/user-attachments/assets/0f2c518b-9cc5-40b0-b9a9-5b548dc60cb8" />
Student Dashboard
<img width="1919" height="864" alt="Screenshot 2025-07-19 202924" src="https://github.com/user-attachments/assets/69ed6642-05b0-46ff-8596-b55be903c6b4" />
Technician Dashboard
<img width="1916" height="673" alt="Screenshot 2025-07-19 203300" src="https://github.com/user-attachments/assets/f7ca6ee9-98f1-4bcb-b5cb-013a7c681364" />

- [Description](#description)
- [Technologies](#technologies)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation and Setup](#installation-and-setup)
- [Usage](#usage)
- [Contact](#contact)
##  Description
CampusCare is a web-based complaint management system designed to streamline the reporting and resolution of campus-related technical issues. It provides dedicated interfaces for students, technicians and administrators to enhance campus maintenance efficiency.

##  Technologies
- Frontend: HTML5, CSS3, JavaScript, Bootstrap, Thymeleaf
- Backend: Java, Spring Boot
- Database: MySQL
- Others: JDBC, Git, GitHub, Eclipse IDE

##  Features
- Issue Reporting Made Simple: Students can easily log technical issues from classrooms, labs, or hostels using a clean and intuitive interface.
- Role-Based Dashboards: Dedicated dashboards for students, technicians, and admins ensure a streamlined workflow for each user type.
- Efficient Complaint Assignment: Admins can quickly assign registered complaints to available technicians based on the issue category.
- Real-Time Status Updates: Students receive real-time updates on the progress and resolution of their complaints.
- Complaint History Access: All users can view previously raised complaints along with their timestamps and resolution status.
- User-Friendly Interface: The system features a minimal and clean UI, allowing effortless navigation and task management.

##  Getting Started

###  Prerequisites
- Java JDK 17 or above
- MySQL Server (with credentials and a schema created)
- Eclipse IDE or IntelliJ IDEA
- Git 

###  Installation and Setup
- Clone the repository
git clone https://github.com/namratanavale33/CampusCare.git
- Import the project into Eclipse or IntelliJ
- Create a MySQL database:
      CREATE DATABASE campuscare;
- Update database credentials in src/main/resources/application.properties:
spring.datasource.username=your_username
spring.datasource.password=your_password
- Run the Spring Boot application
- Open http://localhost:8080 in your browser

##  Usage
- Launch the CampusCare application in your browser at http://localhost:8080.
- Students register or log in using their credentials to access the complaint portal.
- Raise a complaint by selecting the issue type, location, and adding relevant details.
- Track the status of submitted complaints through the student dashboard.
- Technicians log in to view complaints assigned to them by the admin.
- Update complaint status as they begin work or resolve issues.
- Admins manage the system by reviewing complaints, assigning them to technicians and overseeing the overall resolution process.

##  Contact
Feel free to contact me at mail to:namratanavale2967@gmail.com for any questions, feedback or collaboration opportunities.
