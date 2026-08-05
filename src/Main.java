import java.util.ArrayList;
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

    public void removeEmployee(int id) {
        Employee employeeToRemove = null;
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                employeeToRemove = employee;
                break;
            }
        }
        if (employeeToRemove != null) {
            employeeList.remove(employeeToRemove);
        }
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
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PayrollSystem payrollSystem = new PayrollSystem();

        while (true) {
            System.out.println("\n--- Payroll System ---");
            System.out.println("1. Add Full-Time Employee");
            System.out.println("2. Add Part-Time Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. Display All Employees");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter id: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter monthly salary: ");
                    double monthlySalary = scanner.nextDouble();
                    scanner.nextLine();
                    payrollSystem.addEmployee(new FullTimeEmployee(name, id, monthlySalary));
                    System.out.println("Full-time employee added.");
                    break;

                case 2:
                    System.out.print("Enter name: ");
                    String ptName = scanner.nextLine();
                    System.out.print("Enter id: ");
                    int ptId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter hours worked: ");
                    int hoursWorked = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter hourly rate: ");
                    double hourlyRate = scanner.nextDouble();
                    scanner.nextLine();
                    payrollSystem.addEmployee(new PartTimeEmployee(ptName, ptId, hoursWorked, hourlyRate));
                    System.out.println("Part-time employee added.");
                    break;

                case 3:
                    if (payrollSystem.isEmpty()) {
                        System.out.println("No employees to remove.");
                        break;
                    }
                    System.out.print("Enter id of employee to remove: ");
                    int removeId = scanner.nextInt();
                    scanner.nextLine();
                    payrollSystem.removeEmployee(removeId);
                    System.out.println("Employee removed (if existed).");
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
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }
}
