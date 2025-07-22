CREATE DATABASE computer_lab_management;

USE computer_lab_management;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    role ENUM('ADMIN','USER') NOT NULL
);

CREATE TABLE computer_labs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(100) NOT NULL,
    capacity INT NOT NULL
);

CREATE TABLE computers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    status BOOLEAN DEFAULT false,
    lab_id INT,
    FOREIGN KEY (lab_id) REFERENCES computer_labs(id)
);

-- Dữ liệu mẫu
INSERT INTO users (username, password, role) VALUES 
('admin', 'admin123', 'ADMIN'),
('user1', 'user123', 'USER');

INSERT INTO computer_labs (name, location, capacity) VALUES 
('Phòng A1', 'Tầng 1', 30),
('Phòng B2', 'Tầng 2', 25),
('Phòng C3', 'Tầng 3', 40);
INSERT INTO computers (name, status, lab_id) VALUES
('PC-A1-01', true, 1),
('PC-A1-02', false, 1),
('PC-A1-03', true, 1),
('PC-A1-04', true, 1),
('PC-A1-05', false, 1);

INSERT INTO computers (name, status, lab_id) VALUES
('PC-B2-01', true, 2),
('PC-B2-02', true, 2),
('PC-B2-03', false, 2),
('PC-B2-04', true, 2);