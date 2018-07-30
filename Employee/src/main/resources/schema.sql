DROP TABLE IF EXISTS employees;

CREATE TABLE employees(
  employee_number INT NOT NULL PRIMARY KEY,
  first_name varchar(500) DEFAULT NULL,
  last_name varchar(500) DEFAULT NULL,
  office varchar(500) DEFAULT NULL,
  title varchar(500) DEFAULT NULL,
  email varchar(500) DEFAULT NULL,
  image_url varchar(500) DEFAULT NULL
);