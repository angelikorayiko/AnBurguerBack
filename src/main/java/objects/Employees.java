package objects;

public class Employees {

    private String employeeId;
    private String firstName;
    private String surName;
    private String email;
    private String storeId;

    public Employees(String employeeId, String firstName, String surName, String email, String storeId) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.surName = surName;
        this.email = email;
        this.storeId = storeId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
