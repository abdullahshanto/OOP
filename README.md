# Payroll System

A simple Java console application that demonstrates OOP concepts (abstraction, inheritance, encapsulation, polymorphism) through an employee payroll management system.
## Classes

- **Employee** — abstract base class with name, id, and abstract `calculateSalary()` method
- **FullTimeEmployee** — subclass; salary = fixed monthly salary
- **PartTimeEmployee** — subclass; salary = hoursWorked × hourlyRate
- **PayrollSystem** — manages a list of employees (add, remove, display)
- **Main** — entry point with a menu-driven interface

## How to Run


cd src
javac Main.java
java Main

## Menu


1. Add Full-Time Employee
2. Add Part-Time Employee
3. Remove Employee
4. Display All Employees
5. Exit

## Notes

- Error handling for invalid/non-numeric input, negative values, empty names, and removing non-existent employees.
- Polymorphism: both employee types are stored in a single `List<Employee>` and processed via overridden methods.
