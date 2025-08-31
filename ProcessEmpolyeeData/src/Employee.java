public class Employee {
    private int id;
    private String name;
    private double salary;
    private String dob;
    private String location;
    private String role;

    public Employee(int id, String name, double salary, String dob, String location, String role) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.dob = dob;
        this.location = location;
        this.role = role;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getSalary() { return salary; }
    public String getDob() { return dob; }
    public String getLocation() { return location; }
    public String getRole() { return role; }

    @Override
    public String toString() {
        return id + " | " + name + " | $" + salary + " | " + dob + " | " + location + " | " + role;
    }
}
