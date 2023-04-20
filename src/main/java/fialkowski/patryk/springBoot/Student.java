package fialkowski.patryk.springBoot;

public class Student implements Comparable{
    String firstName;
    String lastName;
    StudentCondition studentCondition;
    int birthYear;
    double numberOfPoint;
    String studentAddress;

    public Student (String firstName, String lastName, StudentCondition studentCondition, int birthYear, double numberOfPoint, String studentAddress)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentCondition = studentCondition;
        this.birthYear = birthYear;
        this.numberOfPoint = numberOfPoint;
        this.studentAddress = studentAddress;
    }

    public Student(String lastname)
    {
        this.lastName = lastname;
    }

    @Override
    public String toString()
    {
        if(studentCondition != null) return "Student:\n Imie: %s\n Nazwisko: %s\n Status: %s\n Rok urodzenia: %d\n Punkty: %.2f\n Adres: %s\n".formatted(firstName, lastName, studentCondition.toString(), birthYear, numberOfPoint, studentAddress);
        else return "";
    }

    String print()
    {
        return this.toString();
    }

    @Override
    public boolean compare(Student comparedStudent) {
        if (this.lastName.equals(comparedStudent.lastName)) return true;
        else return false;
    }
}

