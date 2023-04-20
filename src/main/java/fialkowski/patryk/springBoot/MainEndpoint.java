package fialkowski.patryk.springBoot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainEndpoint
{
    Student test = new Student("Józef", "Babinicz", StudentCondition.Chory, 2000, 3.0, "Krakowska 15" );
    @GetMapping("/message")
    public String getMessage() {return "STRINGI WYŚWIETLA, JESZCZE CAŁA RESZTA PROJEKTU I FAJRANCIK";}

    @GetMapping("/testStudent")
    public String getStudent() {return test.print();}
}
