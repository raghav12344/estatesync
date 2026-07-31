# EstateSync 🏢⚡

A high-performance desktop application engineered in Java for managing real estate agencies, customer portfolios, property listings, and transaction deals. 

Designed with a modular MVC-inspired architecture, **EstateSync** streamlines real estate operations by providing dynamic query execution, report export capabilities, and an advanced search engine that dramatically speeds up property discovery.

---

## 📹 Demo Video

Watch the working demo and feature walkthrough of EstateSync:

[![EstateSync Demo Video](https://img.shields.io/badge/Watch_Demo-YouTube-red?style=for-the-badge&logo=youtube)](YOUR_DEMO_VIDEO_LINK_HERE)

*(Replace `YOUR_DEMO_VIDEO_LINK_HERE` with your actual YouTube or Loom link)*

---

## 🌟 Key Features

- **⚡ High-Speed Property Discovery:** Features an optimized search and filtering algorithm that reduces property lookup time by up to 90% across large property databases.
- **💼 Complete Business CRUD Engine:** Fully manages customer records, properties, agent assignments, and real estate deals.
- **🔒 Secure Authentication:** Multi-user access control and authentication using parameterized queries to protect against SQL injection.
- **🖼️ Image & Asset Handling:** Stores and retrieves high-resolution property images directly via MySQL BLOB storage.
- **📄 Document & Report Export:** Generates custom real estate reports and document summaries using Apache POI and iText libraries.

---

## 🛠️ Tech Stack

- **Language:** Java (JDK 22)
- **IDE:** IntelliJ IDEA
- **GUI Framework:** JavaFX
- **Database:** MySQL
- **Database Connectivity:** JDBC (Prepared Statements, Transactions, Joins, BLOBs)
- **Export & Document Libraries:** Apache POI (Excel generation), iText (PDF generation)
- **Architecture:** Model-View-Controller (MVC) design pattern

---

## 🏗️ Architecture & Database Design

The application uses an **MVC-inspired design pattern** to isolate business logic, user interface components, and data layer access:
- **Model:** Handles object mapping, business logic, and transaction management.
- **View:** Built using JavaFX layouts and custom CSS for a modern desktop interface.
- **Controller / DAO:** Executes optimized MySQL queries through JDBC prepared statements, handling complex joins and blob transactions efficiently.

---

## 🚀 Getting Started

### Prerequisites

- **Java Development Kit (JDK):** Version 22
- **IDE:** [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)
- **Database Tools:**
  - [MySQL Community Server](https://dev.mysql.com/downloads/mysql/)
  - [MySQL Workbench](https://dev.mysql.com/downloads/workbench/)

---

### ⚙️ Database & Application Setup

1. **Install MySQL & MySQL Workbench:**
   - Download and install **MySQL Community Server** and **MySQL Workbench** from the official links above.
   - During MySQL server configuration, set your root user credentials:
     - **Username:** `root`
     - **Password:** `Raghav@2006`

2. **Initialize Database via SQL Script:**
   - Open **MySQL Workbench** and connect to your local MySQL instance.
   - Open a new SQL query tab.
   - Copy all contents from the project file located at `src/main/resources/sqlqueriesused.sql` (or `src/main/java/com/example/javaproject/sqlqueriesused.sql`).
   - Paste the copied queries directly into MySQL Workbench and run them all. (This script automatically creates the `javaproject` database, sets up all tables, and populates the data).

3. **Clone the Repository:**
   ```bash
   git clone [https://github.com/raghav12344/EstateSync.git](https://github.com/raghav12344/EstateSync.git)
   cd EstateSync

4. **Open and Run in IntelliJ IDEA:**
   -Launch IntelliJ IDEA and select Open.
   -Navigate to the cloned EstateSync directory and open it as a Java/Maven project.
   -Ensure the SDK is set to JDK 22 in File > Project Structure > Project.
   -Navigate to the application entry point in the Project Explorer:
   -src/main/java/com/example/javaproject/HelloApplication.java
   -Right-click HelloApplication.java (or HelloController.java) and click Run 'HelloApplication'.

## 🤝 Contributing

Contributions are welcome! Feel free to open an issue or submit a pull request if you find any bugs or have feature enhancements.

## 👤 Author

**Raghav Gupta**
-GitHub: @raghav12344
-LinkedIn: Raghav Gupta
