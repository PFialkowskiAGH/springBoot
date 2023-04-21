package fialkowski.patryk.springBoot;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

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
    void getStudentsCSV(HttpServletResponse response) throws IOException
    {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=students_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
        List<Student> listStudents = agh.getAllStudents();
        getCSV(response, listStudents);
    }
//    @GetMapping("/student/csv/{className}")
//    String getStudentsCSVForChosenClass(@PathVariable String className, HttpServletResponse response) throws IOException
//    {
//        response.setContentType("text/csv");
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
//        String currentDateTime = dateFormatter.format(new Date());
//
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=students_" + currentDateTime + ".csv";
//        response.setHeader(headerKey, headerValue);
//        if(isClassExist(className))
//        {
//            List<Student> listStudents = getClass(className).getAllStudents();
//            getCSV(response, listStudents);
//            return "File is generated succesfully";
//        }
//        else return "This class of student not exist";
//
//    }

    private void getCSV(HttpServletResponse response, List<Student> listStudents) throws IOException {
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Firstname", "Lastname", "Condition", "BirthYear", "Points", "Address"};
        String[] nameMapping = {"firstName", "lastName", "studentCondition", "birthYear", "numberOfPoint", "studentAddress"};
        csvWriter.writeHeader(csvHeader);

        for (Student student : listStudents) {
            csvWriter.write(student, nameMapping);
        }
        csvWriter.close();
    }

    boolean isClassExist(String className) {
        return agh.mapOfClasses.containsKey(className);
    }
    ClassOfStudent getClass(String className) {
        return agh.mapOfClasses.get(className);
    }
}



