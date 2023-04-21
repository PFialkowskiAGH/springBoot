package fialkowski.patryk.springBoot;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GradebookController
{
    ClassOfStudentContainer agh = new DataGenerator().container;
    @GetMapping("/message")
    public String getMessage() {return "STRINGI WYŚWIETLA, JESZCZE CAŁA RESZTA PROJEKTU I FAJRANCIK";}

    //student?className=2j&firstName=Jan&lastName=Kiepa&condition=Obecny&birthYear=2000&points=4.5&address=Kalwarysjka5
    @RequestMapping("/student")
    public String addStudent(
            @RequestParam String className,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam StudentCondition condition,
            @RequestParam int birthYear,
            @RequestParam double points,
            @RequestParam String address
    )
    {
        if(agh.mapOfClasses.containsKey(className))
        {
            return agh.mapOfClasses.get(className).addStudent(
                    new Student(firstName, lastName, condition, birthYear, points, address)
            );
        }
        else return "Klasa nie istnieje";
    }
}
