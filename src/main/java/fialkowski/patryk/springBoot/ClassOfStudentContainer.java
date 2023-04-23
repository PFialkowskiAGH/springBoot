package fialkowski.patryk.springBoot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClassOfStudentContainer {
    String nameContainer;
    HashMap<String, ClassOfStudent> mapOfClasses = new HashMap<String, ClassOfStudent>() {
    };

    String addClass(String className, ClassOfStudent classOfStudent) {
        String message;
        if (mapOfClasses.containsKey(className)) {
            System.out.println("Taka klasa jest już w kontenerze");
            message = "This class is already in container";
        } else {
            mapOfClasses.put(className, classOfStudent);
            System.out.println("Dodano klasę %s do konetenera".formatted(className));
            message = "";
        }
        return message;
    }
    String addEmptyClass(String className, int maxNumberOfStudent)
    {
        String message;
        if (mapOfClasses.containsKey(className)) {
            System.out.println("Taka klasa jest już w kontenerze");
            message = "This class is already in container";
        } else {
            ClassOfStudent newClassOfStudent = new ClassOfStudent(className, new ArrayList<Student>(),maxNumberOfStudent);
            mapOfClasses.put(className, newClassOfStudent);
            System.out.printf("Dodano klasę %s do konetenera%n", className);
            message = "Class addded succesfully";
        }
        return message;
    }

    String removeClass(String className) {
        String message;
        if (mapOfClasses.containsKey(className)) {
            mapOfClasses.remove(className);
            System.out.println("Usunięto klasę z konetenra");
            message = "Class removed sucessfully";
        } else {
            System.out.println("Taka klasa nie jest w kontenerze");
            message = "This class not exist in container";
        }
        return message;
    }

    ArrayList findEmptyGroups() {
        ArrayList emptyClasses = new ArrayList<>();
        mapOfClasses.forEach((key, value) ->
        {
            if (value.students.size() == 0) {
                System.out.println("Klasa %s jest pusta".formatted(key));
                emptyClasses.add(value);
            }
        });
        return emptyClasses;
    }

    void summary() {
        mapOfClasses.forEach((key, value) ->
        {
            double percentage = ((float) value.students.size()) / value.maxNumberOfStudents;
            System.out.println("Nazwa klasy: %s".formatted(key));
            System.out.println("Zapełnienie klasy: %.2f\n".formatted(percentage));
        });
    }

    void isEmpty() {
        if (mapOfClasses.isEmpty()) System.out.println("Ten konetner jest pusty");
        else System.out.println("Ten kontener nie jest pusty");
    }

    ArrayList searchStudent(String lastname) {
        ArrayList searchedStudent = new ArrayList<Student>();
        mapOfClasses.forEach((key, value) ->
        {
            searchedStudent.add(value.search(lastname));
        });
        return searchedStudent;
    }

    String changeClass(String searchedClassName, String className, Integer maxNumberOfPoints) {
        ClassOfStudent classOfStudent = new ClassOfStudent(className, new ArrayList<>(), maxNumberOfPoints);
        final boolean[] isExist = {false};
        mapOfClasses.forEach((key, value) ->
        {
            if (className.equals(key)) isExist[0] = true;
        });
        if (isExist[0] && !className.equals(searchedClassName))
            return "You cant change name of class, class with this name already exist";
        else {
            mapOfClasses.forEach((key, value) ->
            {
                if (searchedClassName.equals(key)) {
                    classOfStudent.students = value.students;
                }
            });
            removeClass(searchedClassName);
            addClass(classOfStudent.className, classOfStudent);
            return "";
        }
    }
    List<Student> getAllStudents()
    {
        List<Student> students = new ArrayList<Student>();
        mapOfClasses.forEach((key, value) ->
        {
            for (Student student : value.students)
            {
                students.add(student);
            }
        });
        return students;
    }
    StringBuilder getAllClassNames()
    {
        StringBuilder classNames= new StringBuilder();
        mapOfClasses.forEach((key, value) ->
        {
            classNames.append(key + "\n");
        });
        return classNames;
    }
}
