-- SQL Script for ERMS_DB with Sample Data

-- 1. Create the Database
CREATE DATABASE IF NOT EXISTS ERMS_DB;

-- Use the newly created database
USE ERMS_DB;

-- ---
-- 2. Create the Devices Table
-- ---
CREATE TABLE Devices (
    DeviceID INT NOT NULL AUTO_INCREMENT,
    `Type` VARCHAR(50) NOT NULL,
    SerialNumber VARCHAR(100) UNIQUE NOT NULL,
    Brand VARCHAR(50),
    `Status` VARCHAR(50) NOT NULL,
    DateReceived DATE NOT NULL,
    
    PRIMARY KEY (DeviceID)
);

-- ---
-- 3. Create the Customers Table
-- ---
CREATE TABLE Customers (
    CustomerID INT NOT NULL AUTO_INCREMENT,
    `Name` VARCHAR(100) NOT NULL,
    Email VARCHAR(100) UNIQUE,
    PhoneNumber VARCHAR(20),
    DeviceID INT, 
    
    PRIMARY KEY (CustomerID),
    
    -- Define the Foreign Key constraint
    CONSTRAINT FK_Customers_Devices
    FOREIGN KEY (DeviceID) 
    REFERENCES Devices(DeviceID)
    ON DELETE SET NULL 
);

-- ---
-- 4. Create the Jobs Table
-- ---
CREATE TABLE Jobs (
    JobID INT NOT NULL AUTO_INCREMENT,
    CustomerID INT NOT NULL,
    DeviceID INT NOT NULL, 
    Cost DECIMAL(10, 2),
    AmountPaid DECIMAL(10, 2) DEFAULT 0.00,
    TechIssues TEXT,
    TechDiagnosis TEXT,
    TechNotes TEXT,
    StoragePlace VARCHAR(50),
    
    PRIMARY KEY (JobID),
    
    -- Define Foreign Key constraint to Customers table
    CONSTRAINT FK_Jobs_Customers
    FOREIGN KEY (CustomerID) 
    REFERENCES Customers(CustomerID)
    ON DELETE CASCADE, 
    
    -- Define Foreign Key constraint to Devices table
    CONSTRAINT FK_Jobs_Devices
    FOREIGN KEY (DeviceID) 
    REFERENCES Devices(DeviceID)
    ON DELETE RESTRICT 
);


-- ************************************
-- 5. Insert Sample Data
-- ************************************

-- ---
-- 5.1. Insert 5 Records into Devices
-- ---
INSERT INTO Devices (`Type`, SerialNumber, Brand, `Status`, DateReceived) VALUES
('Laptop', 'ABC123XYZ789', 'Dell', 'IN PROGRESS', '2025-11-28'),
('Smartphone', 'DEF456GHI012', 'Samsung', 'PICKUP READY', '2025-11-29'),
('Tablet', 'JKL789MNO345', 'Apple', 'COMPLETE', '2025-11-29'),
('Desktop PC', 'PQR012STU678', 'HP', 'AWAITING PARTS', '2025-12-01'),
('Smartwatch', 'VWX345YZA901', 'Garmin', 'PENDING', '2025-12-02');

-- ---
-- 5.2. Insert 5 Records into Customers
-- ---
-- CustomerID 1 is linked to DeviceID 1, CustomerID 2 to DeviceID 2, etc.
INSERT INTO Customers (`Name`, Email, PhoneNumber, DeviceID) VALUES
('Alice Johnson', 'alice.j@mail.com', '555-1234', 1),
('Robert Smith', 'robert.s@mail.com', '555-5678', 2),
('Clara Velez', 'clara.v@live.com', '555-9012', 3),
('David Lee', 'david.l@utech.edu.jm', '555-3456', 4),
('Emily Chen', 'emily.c@gmail.com', '555-7890', 5);

-- ---
-- 5.3. Insert 5 Records into Jobs
-- ---
-- Ensure CustomerID and DeviceID match the sample data inserted above
INSERT INTO Jobs (CustomerID, DeviceID, Cost, AmountPaid, TechIssues, TechDiagnosis, TechNotes, StoragePlace) VALUES
(1, 1, 350.00, 100.00, 'Laptop won''t turn on after update.', 'Faulty motherboard detected.', 'Ordered new board. Waiting for delivery.', 'Shelf A1'),
(2, 2, 85.00, 85.00, 'Screen is cracked.', 'Screen replacement completed.', 'Tested all functions; screen is perfect.', 'Pickup Desk'),
(3, 3, 120.50, 120.50, 'Battery drains quickly.', 'Battery cycle count high; replaced battery.', 'Full charge/drain cycle test successful.', 'Completed Rack'),
(4, 4, 600.00, 0.00, 'System freezes randomly, loud fan noise.', 'Confirmed CPU cooler failure and failing hard drive.', 'Waiting for customer approval for parts purchase.', 'Bay 3C'),
(5, 5, 45.00, 0.00, 'Watch not syncing with phone.', 'Software reset required.', 'Standard software fix applied. Ready for testing.', 'Bench 1');