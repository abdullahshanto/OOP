import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

abstract class Employee {
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public abstract double calculateSalary();

    @Override
    public String toString() {
        return "Employee [name=" + name + ", id=" + id + ", salary=" + calculateSalary() + "]";
    }
}

class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}

class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate) {
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}

class PayrollSystem {
    private List<Employee> employeeList;

    public PayrollSystem() {
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public boolean removeEmployee(int id) {
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                employeeList.remove(employee);
                return true;
            }
        }
        return false;
    }

    public void displayEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees to display.");
            return;
        }
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }

    public boolean isEmpty() {
        return employeeList.isEmpty();
    }
}

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        PayrollSystem payrollSystem = new PayrollSystem();

        while (true) {
            System.out.println("\n--- Payroll System ---");
            System.out.println("1. Add Full-Time Employee");
            System.out.println("2. Add Part-Time Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. Display All Employees");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = readInt();
            if (choice == -1) {
                continue;
            }

            switch (choice) {
                case 1:
                    addFullTimeEmployee(payrollSystem);
                    break;
                case 2:
                    addPartTimeEmployee(payrollSystem);
                    break;
                case 3:
                    removeEmployee(payrollSystem);
                    break;
                case 4:
                    System.out.println("\nEmployee Details:");
                    payrollSystem.displayEmployees();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                    break;
            }
        }
    }

    private static int readInt() {
        try {
            int value = scanner.nextInt();
            scanner.nextLine();
            return value;
        } catch (InputMismatchException e) {
            System.out.println("Error: Please enter a valid integer.");
            scanner.nextLine();
            return -1;
        }
    }

    private static double readDouble() {
        try {
            double value = scanner.nextDouble();
            scanner.nextLine();
            return value;
        } catch (InputMismatchException e) {
            System.out.println("Error: Please enter a valid number.");
            scanner.nextLine();
            return Double.NaN;
        }
    }

    private static void addFullTimeEmployee(PayrollSystem payrollSystem) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Error: Name cannot be empty.");
            return;
        }

        System.out.print("Enter id: ");
        int id = readInt();
        if (id == -1) {
            return;
        }
        if (id < 0) {
            System.out.println("Error: ID cannot be negative.");
            return;
        }

        System.out.print("Enter monthly salary: ");
        double monthlySalary = readDouble();
        if (Double.isNaN(monthlySalary)) {
            return;
        }
        if (monthlySalary < 0) {
            System.out.println("Error: Salary cannot be negative.");
            return;
        }

        payrollSystem.addEmployee(new FullTimeEmployee(name, id, monthlySalary));
        System.out.println("Full-time employee added.");
    }

 