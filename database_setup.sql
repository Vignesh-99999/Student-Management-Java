-- =====================================================
-- Database Setup Script for VSLIBRARY
-- Library Management System
-- =====================================================

-- Create Database
CREATE DATABASE IF NOT EXISTS VSLIBRARY;
USE VSLIBRARY;

-- =====================================================
-- Table: Book
-- Stores book information
-- =====================================================
CREATE TABLE IF NOT EXISTS Book (
    Book_id INT AUTO_INCREMENT PRIMARY KEY,
    Book_Name VARCHAR(255) NOT NULL,
    Category VARCHAR(100) NOT NULL,
    Quantity INT NOT NULL DEFAULT 0,
    INDEX idx_category (Category),
    INDEX idx_book_name (Book_Name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =====================================================
-- Table: student
-- Stores student information
-- =====================================================
CREATE TABLE IF NOT EXISTS student (
    stu_id INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    Enroll VARCHAR(100) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL,
    Course VARCHAR(100) NOT NULL,
    INDEX idx_enroll (Enroll)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =====================================================
-- Table: librarian
-- Stores librarian information
-- =====================================================
CREATE TABLE IF NOT EXISTS librarian (
    Librarian_id INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    UserId VARCHAR(100) NOT NULL UNIQUE,
    Password VARCHAR(255) NOT NULL,
    INDEX idx_userid (UserId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =====================================================
-- Table: request
-- Stores book request information from students
-- =====================================================
CREATE TABLE IF NOT EXISTS request (
    Request_id INT AUTO_INCREMENT PRIMARY KEY,
    Book_id INT NOT NULL,
    stu_id INT NOT NULL,
    FOREIGN KEY (Book_id) REFERENCES Book(Book_id) ON DELETE CASCADE,
    FOREIGN KEY (stu_id) REFERENCES student(stu_id) ON DELETE CASCADE,
    INDEX idx_book_id (Book_id),
    INDEX idx_stu_id (stu_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =====================================================
-- Table: issued
-- Stores information about issued books
-- =====================================================
CREATE TABLE IF NOT EXISTS issued (
    stu_id INT NOT NULL,
    book_id INT NOT NULL,
    date DATE NOT NULL,
    PRIMARY KEY (stu_id, book_id),
    FOREIGN KEY (stu_id) REFERENCES student(stu_id) ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES Book(Book_id) ON DELETE CASCADE,
    INDEX idx_date (date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =====================================================
-- Table: Log_table
-- Stores login/logout session information
-- =====================================================
CREATE TABLE IF NOT EXISTS Log_table (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    INDEX idx_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =====================================================
-- Sample Data (Optional - for testing)
-- =====================================================

-- Insert sample librarian (for testing)
-- Password should be hashed in production
INSERT INTO librarian (Librarian_id, Name, UserId, Password) VALUES 
(1, 'Admin Librarian', 'admin', 'admin123')
ON DUPLICATE KEY UPDATE Name=Name;

-- Insert sample student (for testing)
-- Password should be hashed in production
INSERT INTO student (stu_id, Name, Enroll, Password, Course) VALUES 
(1, 'John Doe', 'STU001', 'password123', 'Computer Science')
ON DUPLICATE KEY UPDATE Name=Name;

-- Insert sample books (for testing)
INSERT INTO Book (Book_id, Book_Name, Category, Quantity) VALUES 
(1, 'Introduction to Java Programming', 'Programming', 10),
(2, 'Database Management Systems', 'Computer Science', 8),
(3, 'Data Structures and Algorithms', 'Programming', 12),
(4, 'Operating Systems Concepts', 'Computer Science', 6)
ON DUPLICATE KEY UPDATE Book_Name=Book_Name;

-- =====================================================
-- End of Database Setup Script
-- =====================================================

