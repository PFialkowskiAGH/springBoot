package fialkowski.patryk.springBoot;

import java.util.ArrayList;

public class DataGenerator
{
    public static final String[] classes = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};
    public static final String[] firstNames = {"Adam", "Paweł", "Patryk", "Kamil", "Joasia", "Anna"};
    public static final String[] lastName = {"Biegała", "Kowalski", "Wiśniewski", "Chory", "Zguba", "Badziewna"};
    public static final String[] addresses = {"Siewna 12", "Pokątna 4", "Krakowskie Przedmieście 12", "Krakowska 8", "Mostowa 12/7", "Aleja Pokoju 12"};
    ClassOfStudentContainer container = new ClassOfStudentContainer();
    public DataGenerator()
    {
        int numberOfClass = getRandomInteger(10, 1);
        for (int i = 1; i <= numberOfClass; i++)
        {
            ClassOfStudent generatedClass = generateClass(i);
            container.addClass(generatedClass.className, generatedClass);
        }
    }
    ClassOfStudent generateClass(Integer i)
    {
        int max = getRandomInteger(10, 1);
        int classLetter = getRandomInteger(10, 1);
        ClassOfStudent classOfStudent = new ClassOfStudent(i.toString() + classes[classLetter], new ArrayList<>(), max);
        int numberOfStudents = max/2;
        for (int j = 0; j <= numberOfStudents; j++)
        {
            Student generatedStudent = generateStudent(j);
            classOfStudent.addStudent(generatedStudent);
        }
        return classOfStudent;
    }
    Student generateStudent(int j)
    {
        int firstname = getRandomInteger(5, 1);
        int lastname = getRandomInteger(5, 1);
        int address = getRandomInteger(5, 1);
        return new Student(firstNames[firstname], lastName[lastname], StudentCondition.Obecny, getRandomInteger(2005, 1997), getRandomInteger(6, 1), addresses[address]);
    }
    public static int getRandomInteger(int maximum, int minimum){
        return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }
}
