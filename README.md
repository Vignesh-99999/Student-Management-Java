# VS Library Management System

A comprehensive Java-based Library Management System developed using Java Swing for the GUI and MySQL for database management. This system allows students, librarians, and administrators to manage books, track book issues, and handle book requests efficiently.

## 📋 Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Database Setup](#database-setup)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [Project Structure](#project-structure)
- [User Roles](#user-roles)
- [Usage Guide](#usage-guide)
- [Troubleshooting](#troubleshooting)

## ✨ Features

### Student Features
- **Login System**: Secure authentication using enrollment number and password
- **Book Browsing**: View all available books in the library
- **Book Requests**: Request books for issue
- **My Books**: View currently issued books
- **Book Return**: Submit books back to the library
- **Search Functionality**: Search for specific books

### Librarian Features
- **Login System**: Secure authentication using user ID and password
- **Book Management**: 
  - Add new books with categories
  - Update book information
  - Delete books
  - Delete entire book categories
- **Request Management**: View and accept/reject book requests from students
- **Issue Management**: 
  - View all issued books
  - Search issued books
  - Manage book returns
- **Book Search**: Search books by ID or name

### Super Admin Features
- **Student Management**: 
  - Add new students
  - View all students
  - Search students
- **Librarian Management**: 
  - Add new librarians
  - View all librarians
  - Search librarians
- **Full System Access**: Complete control over the library management system

## 🔧 Prerequisites

Before running this application, ensure you have the following installed:

1. **Java Development Kit (JDK)**
   - Version 8 or higher
   - Download from: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)

2. **MySQL Server**
   - Version 5.7 or higher (or MySQL 8.0+)
   - Download from: [MySQL Downloads](https://dev.mysql.com/downloads/mysql/)

3. **MySQL Workbench** (Optional but recommended)
   - For easier database management
   - Download from: [MySQL Workbench](https://dev.mysql.com/downloads/workbench/)

4. **Command Line Access**
   - Windows: Command Prompt or PowerShell
   - Linux/Mac: Terminal

## 📦 Installation

1. **Clone or Download the Project**
   ```bash
   git clone <repository-url>
   cd java_sem_4_project
   ```

2. **Verify Java Installation**
   ```bash
   java -version
   javac -version
   ```

3. **Verify MySQL Installation**
   ```bash
   mysql --version
   ```

## 🗄️ Database Setup

### Step 1: Start MySQL Server

Make sure your MySQL server is running:
- **Windows**: Start MySQL service from Services or use XAMPP/WAMP
- **Linux**: `sudo systemctl start mysql`
- **Mac**: Start MySQL from System Preferences

### Step 2: Create Database and Tables

1. **Open MySQL Command Line or MySQL Workbench**

2. **Run the Database Setup Script**
   ```bash
   mysql -u root -p < database_setup.sql
   ```
   
   Or manually:
   ```sql
   mysql -u root -p
   ```
   Then in MySQL prompt:
   ```sql
   source database_setup.sql;
   ```

3. **Verify Database Creation**
   ```sql
   USE VSLIBRARY;
   SHOW TABLES;
   ```
   
   You should see the following tables:
   - `Book`
   - `student`
   - `librarian`
   - `request`
   - `issued`
   - `Log_table`

### Step 3: Verify Sample Data

The setup script includes sample data for testing:
- **Librarian**: 
  - UserId: `admin`
  - Password: `admin123`
- **Student**: 
  - Enrollment: `STU001`
  - Password: `password123`
- **Books**: 4 sample books in different categories

## ⚙️ Configuration

### Database Connection Settings

If you need to change database connection settings, edit `ConnectionString.java`:

```java
con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/VSLIBRARY", "root", "");
```

**Parameters to modify:**
- `127.0.0.1:3306` - MySQL server address and port
- `VSLIBRARY` - Database name
- `root` - MySQL username
- `""` - MySQL password (empty by default)

**Example for custom settings:**
```java
con = DriverManager.getConnection("jdbc:mysql://localhost:3306/VSLIBRARY", "your_username", "your_password");
```

### Compile the Project

After modifying `ConnectionString.java`, recompile:
```bash
javac -cp "jars/*" *.java
```

## 🚀 Running the Application

### Method 1: Using Run.bat (Windows)

Simply double-click `Run.bat` or run from command prompt:
```bash
Run.bat
```

### Method 2: Manual Execution

1. **Compile Java Files**
   ```bash
   javac -cp "jars/*" *.java
   javac -cp "jars/*" MainCrud/*.java
   javac -cp "jars/*" SuperAdCrud/*.java
   javac -cp "jars/*" PanelCode/*.java
   javac -cp "jars/*" RunQuery/*.java
   javac -cp "jars/*" ConnectionString/*.java
   ```

2. **Run the Application**
   ```bash
   java -cp "jars/*;." Main
   ```

   **For Linux/Mac:**
   ```bash
   java -cp "jars/*:." Main
   ```

### Method 3: Using IDE (NetBeans/Eclipse/IntelliJ)

1. Import the project into your IDE
2. Add all JAR files from the `jars` folder to the project's classpath
3. Ensure MySQL Connector JAR is included
4. Run `Main.java` as the main class

## 📁 Project Structure

```
java_sem_4_project/
│
├── Main.java                 # Main entry point
├── Login.java                # Login interface
├── ConnectionString.java     # Database connection configuration
├── RunQuery.java             # SQL query execution utility
│
├── MainCrud/
│   └── MainCrudOperation.java  # Main CRUD operations for books
│
├── SuperAdCrud/
│   └── SuperAdCrud.java       # Super admin CRUD operations
│
├── PanelCode/
│   └── PanelCode.java          # Panel management utilities
│
├── Student Modules/
│   ├── StuMain.java           # Student main dashboard
│   ├── NewStu.java            # Add new student (Admin)
│   ├── AllStu.java            # View all students (Admin)
│   ├── StuBooks.java          # Student book operations
│   └── StuBooksView.java      # Student books view
│
├── Librarian Modules/
│   ├── LibrarianMain.java     # Librarian main dashboard
│   ├── LibrarianView.java     # Librarian view interface
│   ├── NewLibrarian.java      # Add new librarian (Admin)
│   ├── AllLibrarian.java      # View all librarians (Admin)
│   ├── AddBookCat.java        # Add books with categories
│   ├── UpdateBook.java        # Update book information
│   ├── DeleteBooks.java       # Delete books
│   ├── DeleteCat.java         # Delete book categories
│   ├── SearchBook.java        # Search books
│   ├── IssueBookView.java     # View issued books
│   └── SearchIssueBook.java   # Search issued books
│
├── Request Management/
│   ├── SendRequest.java       # Send book request (Student)
│   ├── RequestView.java       # View requests (Librarian)
│   └── AcceptRequest.java     # Accept/reject requests
│
├── Book Operations/
│   ├── BookSubmit.java        # Book return functionality
│   └── SearchBook.java        # Book search
│
├── Session Management/
│   ├── SessionManager.java    # Session handling
│   └── session.java           # Session utilities
│
├── jars/                      # External JAR dependencies
│   ├── mysql-connector-java-8.0.29.jar
│   ├── rs2xml.jar
│   └── ... (other JDBC drivers)
│
├── database_setup.sql         # Database setup script
├── Run.bat                    # Windows batch file to run the app
└── README.md                  # This file
```

## 👥 User Roles

### 1. Student
- **Login Credentials**: Enrollment Number + Password
- **Access**: View books, request books, view issued books, return books

### 2. Librarian
- **Login Credentials**: User ID + Password
- **Access**: Manage books, handle requests, issue/return books, view reports

### 3. Super Admin
- **Access**: All librarian features + manage students and librarians

## 📖 Usage Guide

### For Students

1. **Login**
   - Enter your enrollment number and password
   - Click "Log In"

2. **Request a Book**
   - Browse available books
   - Click "Request" on desired book
   - Wait for librarian approval

3. **View My Books**
   - Navigate to "My Books" section
   - View all currently issued books

4. **Return a Book**
   - Go to "My Books"
   - Select the book to return
   - Click "Return"

### For Librarians

1. **Login**
   - Enter your User ID and password
   - Click "Log In"

2. **Add a Book**
   - Go to "Add Book" section
   - Enter book name, category, and quantity
   - Click "Add"

3. **Manage Requests**
   - Go to "Requests" section
   - View pending requests
   - Accept or reject requests

4. **View Issued Books**
   - Navigate to "Issued Books"
   - View all currently issued books
   - Search by book ID or student enrollment

5. **Update/Delete Books**
   - Use "Update Book" or "Delete Book" options
   - Search by book ID first

### For Super Admin

1. **Add Student**
   - Navigate to "Add Student"
   - Enter student details (Name, Enrollment, Password, Course)
   - Click "Add"

2. **Add Librarian**
   - Navigate to "Add Librarian"
   - Enter librarian details (Name, User ID, Password)
   - Click "Add"

3. **View All Users**
   - Use "All Students" or "All Librarians" options
   - Search by ID if needed

## 🔍 Troubleshooting

### Common Issues

1. **Database Connection Error**
   - **Problem**: "Error Occur: Communications link failure"
   - **Solution**: 
     - Ensure MySQL server is running
     - Check database name, username, and password in `ConnectionString.java`
     - Verify MySQL is listening on port 3306

2. **ClassNotFoundException**
   - **Problem**: "java.lang.ClassNotFoundException: com.mysql.cj.jdbc.Driver"
   - **Solution**: 
     - Ensure `mysql-connector-java-*.jar` is in the `jars` folder
     - Check classpath includes all JAR files

3. **Table Not Found Error**
   - **Problem**: "Table 'VSLIBRARY.Book' doesn't exist"
   - **Solution**: 
     - Run `database_setup.sql` to create all tables
     - Verify database name is correct

4. **Access Denied Error**
   - **Problem**: "Access denied for user 'root'@'localhost'"
   - **Solution**: 
     - Update MySQL password in `ConnectionString.java`
     - Or create a new MySQL user with proper permissions

5. **Compilation Errors**
   - **Problem**: "package does not exist" errors
   - **Solution**: 
     - Ensure all package directories exist
     - Compile with correct classpath: `javac -cp "jars/*" *.java`

### Database Connection Test

Test your database connection:
```sql
mysql -u root -p
USE VSLIBRARY;
SELECT * FROM Book;
```

### Reset Database

To reset the database:
```sql
DROP DATABASE IF EXISTS VSLIBRARY;
CREATE DATABASE VSLIBRARY;
USE VSLIBRARY;
source database_setup.sql;
```

## 📝 Notes

- **Password Security**: Currently, passwords are stored in plain text. For production use, implement password hashing (e.g., BCrypt, SHA-256).
- **Database Backup**: Regularly backup your database to prevent data loss.
- **Port Configuration**: Default MySQL port is 3306. Change if using a different port.
- **Case Sensitivity**: Table and column names are case-sensitive in some MySQL configurations.

## 🤝 Contributing

This is a semester 4 project. Contributions and improvements are welcome!

## 📄 License

This project is created for educational purposes.

## 👨‍💻 Author

**VIGNESH MUDALIYAR**

## 📧 Support

For issues or questions, please check the troubleshooting section or create an issue in the repository.

---

**Happy Coding! 📚**

