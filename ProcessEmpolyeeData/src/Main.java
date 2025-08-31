import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(101, "Alice Johnson", 72000, "1990-04-15", "New York, NY", "Software Engineer"));
        employees.add(new Employee(102, "Marcus Lee", 88000, "1985-11-22", "New York, NY", "Product Manager"));
        employees.add(new Employee(103, "Priya Nair", 95500, "1992-07-03", "Austin, TX", "Data Scientist"));
        employees.add(new Employee(104, "James O'Connor", 60000, "1988-01-19", "Austin, TX", "QA Engineer"));
        employees.add(new Employee(105, "Chen Wei", 78250, "1993-03-09", "Seattle, WA", "DevOps Engineer"));
        employees.add(new Employee(106, "Fatima Zahra", 67000, "1991-06-25", "Seattle, WA", "UX Designer"));
        employees.add(new Employee(107, "Diego Martínez", 83400, "1987-12-08", "Miami, FL", "Business Analyst"));
        employees.add(new Employee(108, "Elena Petrova", 90000, "1989-05-30", "Boston, MA", "Project Manager"));
        employees.add(new Employee(109, "Ravi Deshmukh", 74300, "1994-09-17", "Boston, MA", "Backend Engineer"));
        employees.add(new Employee(110, "Sarah Kim", 79900, "1990-10-12", "Los Angeles, CA", "Frontend Engineer"));
        employees.add(new Employee(111, "Tom Henderson", 65000, "1986-08-11", "Dallas, TX", "System Admin"));
        employees.add(new Employee(112, "Aisha Mohammed", 91200, "1991-02-23", "Dallas, TX", "Data Engineer"));
        employees.add(new Employee(113, "Luka Novak", 85000, "1993-11-02", "Phoenix, AZ", "Software Engineer"));
        employees.add(new Employee(114, "Naomi Tanaka", 68750, "1990-07-19", "Phoenix, AZ", "QA Analyst"));
        employees.add(new Employee(115, "Victor Alvarez", 82000, "1989-12-14", "Phoenix, AZ", "Mobile Developer"));
        employees.add(new Employee(116, "Helena Schmidt", 96500, "1987-03-27", "Houston, TX", "AI Specialist"));
        employees.add(new Employee(117, "Omar El-Sayed", 73600, "1992-05-05", "Houston, TX", "Database Admin"));
        employees.add(new Employee(118, "Isabelle Dubois", 76900, "1994-04-21", "Charlotte, NC", "Business Analyst"));
        employees.add(new Employee(119, "Raj Patel", 87300, "1991-01-18", "Tampa, FL", "Cloud Engineer"));
        employees.add(new Employee(120, "Ana Costa", 69500, "1993-09-12", "Tampa, FL", "UI Developer"));
        employees.add(new Employee(121, "Ahmed Khan", 92000, "2002-06-30", "Detroit, MI", "Full Stack Dev"));
        employees.add(new Employee(122, "Giulia Bianchi", 88700, "2003-08-09", "Detroit, MI", "Project Manager"));
        employees.add(new Employee(123, "Brian Osei", 71800, "2005-10-25", "Kansas City, MO", "DevOps Engineer"));
        employees.add(new Employee(124, "Jieun Park", 79200, "1993-11-07", "Columbus, OH", "UX Researcher"));
        employees.add(new Employee(125, "Thabo Mokoena", 84400, "1989-03-04", "Indianapolis, IN", "Frontend Engineer"));

        // 📍 Filter by location
        System.out.println("\nEmployees located in 'New York, NY':");
        employees.stream()
                .filter(emp -> "New York, NY".equals(emp.getLocation()))
                .forEach(System.out::println);
        // 🔢 Count employees by location
        Map<String, Long> locationCounts = employees.stream()
                .collect(Collectors.groupingBy(Employee::getLocation, Collectors.counting()));

        // 📋 Print employee count per location
        System.out.println("Employee count by location:");
        locationCounts.forEach((location, count) ->
                System.out.println(location + " -> " + count)
        );

        // ✅ Verify total count
        long totalCountFromLocations = locationCounts.values().stream().mapToLong(Long::longValue).sum();
        long totalEmployees = employees.size();

        System.out.println("\nTotal employees from location counts: " + totalCountFromLocations);
        System.out.println("Total employees in list: " + totalEmployees);
        // ✅ Final check
        if (totalCountFromLocations == totalEmployees) {
            System.out.println("\n✅ Counts match: Data is consistent.");
        } else {
            System.out.println("\n❌ Mismatch in counts: Check employee data.");
        }
        // 🔍 Find employee with highest salary
        Optional<Employee> highestSalaryEmp = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary));

        // ✅ Print highest salary employee
        System.out.println("Highest Salary Employee:");
        highestSalaryEmp.ifPresent(System.out::println);

        // 🔍 Find employee with lowest salary
        Optional<Employee> lowestSalaryEmp = employees.stream()
                .min(Comparator.comparingDouble(Employee::getSalary));

        // ✅ Print lowest salary employee
        System.out.println("\nLowest Salary Employee:");
        lowestSalaryEmp.ifPresent(System.out::println);

        // User input for sort field (you can take this from Scanner if needed)
        String sortBy = "salary"; // try "name", "dob", "location", "role", etc.

        Comparator<Employee> comparator;

        // Choose comparator based on input
        switch (sortBy.toLowerCase()) {
            case "id":
                comparator = Comparator.comparing(Employee::getId);
                break;
            case "name":
                comparator = Comparator.comparing(Employee::getName);
                break;
            case "salary":
                comparator = Comparator.comparing(Employee::getSalary);
                break;
            case "dob":
                comparator = Comparator.comparing(Employee::getDob);
                break;
            case "location":
                comparator = Comparator.comparing(Employee::getLocation);
                break;
            case "role":
                comparator = Comparator.comparing(Employee::getRole);
                break;
            default:
                System.out.println("Invalid sort field: " + sortBy);
                return;
        }

        // Sort and display
        System.out.println("\nEmployees sorted by " + sortBy + ":");
        employees.stream()
                .sorted(comparator)
                .forEach(System.out::println);
        // Date formatter for parsing DOB
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();

        // Filter employees age < 30
        List<Employee> under30 = employees.stream()
                .filter(emp -> {
                    LocalDate dob = LocalDate.parse(emp.getDob(), formatter);
                    int age = Period.between(dob, today).getYears();
                    return age < 30;
                })
                .collect(Collectors.toList());

        // Print result
        System.out.println("Employees younger than 30:");
        under30.forEach(System.out::println);

    }
}
