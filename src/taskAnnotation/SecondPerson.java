package taskAnnotation;

public class SecondPerson {

    private String name;

    public SecondPerson() {
    }

    SecondPerson(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SecondPerson{" +
                "name='" + name + '\'' +
                '}';
    }
}

