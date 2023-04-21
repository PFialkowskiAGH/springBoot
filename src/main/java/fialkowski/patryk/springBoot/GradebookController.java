package fialkowski.patryk.springBoot;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GradebookController
{
    ClassOfStudentContainer agh = new DataGenerator().container;
    @GetMapping("/message")
    public String getMessage() {return "STRINGI WYŚWIETLA, JESZCZE CAŁA RESZTA PROJEKTU I FAJRANCIK";}

    //student?className=2j&firstname=Jan&lastname=Kiepa&condition=Obecny&birthYear=2000&points=4.5&address=Kalwarysjka5
    @RequestMapping("/student")
    public String addStudent(
            @RequestParam String className,
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam StudentCondition condition,
            @RequestParam int birthYear,
            @RequestParam double points,
            @RequestParam String address
    )
    {
        if(isClassExist(className))
        {
            return getClass(className).addStudent(
                    new Student(firstname, lastname, condition, birthYear, points, address)
            );
        }
        else return "This class of student not exist";
    }
    @RequestMapping("/student/{className}/{lastname}")
    public String deleteStudent(
            @PathVariable String className,
            @PathVariable String lastname
    )
    {
        if(isClassExist(className))
        {
            return getClass(className).removeStudent(lastname);
        }
        else return "This class of student not exist";
    }
    @GetMapping("/student/{className}/{lastname}/grade")
    public String getPointsOfStudent(
            @PathVariable String className,
            @PathVariable String lastname
    )
    {
        if(isClassExist(className))
        {
            return getClass(className).getStudentPoints(lastname);
        }
        else return "This class of student not exist";
    }
    @GetMapping("/student/csv")
    String getStudentsCSV(String className)
    {
        StringBuilder students = (new StringBuilder());
        agh.mapOfClasses.forEach((key, value) ->
        {
            students.append(key);
            students.append(value.summary());
        });
        return String.valueOf(students);
    }
    boolean isClassExist(String className) {
        return agh.mapOfClasses.containsKey(className);
    }
    ClassOfStudent getClass(String className) {
        return agh.mapOfClasses.get(className);
    }
}



