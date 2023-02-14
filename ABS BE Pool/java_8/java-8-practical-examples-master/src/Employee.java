import java.util.List;

public class Employee {

    public Integer getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumbers=" + phoneNumbers +
                ", age=" + age +
                ", dep='" + dep + '\'' +
                ", sal=" + sal +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer id;
    private String name;
    private String lastName;

    public List<Long> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<Long> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    private List<Long> phoneNumbers;

    public Employee(Integer id, String name, String lastName, List<Long> phoneNumbers, Integer age, String dep, Double sal) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumbers = phoneNumbers;
        this.age = age;
        this.dep = dep;
        this.sal = sal;
    }

    private Integer age;
    private String dep;
    private Double sal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }


}
