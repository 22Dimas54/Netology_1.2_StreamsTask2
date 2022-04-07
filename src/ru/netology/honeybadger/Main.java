package ru.netology.honeybadger;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        //1.Найти количество несовершеннолетних (т.е. людей младше 18 лет).
        long numberOfMinors = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних (т.е. людей младше 18 лет): " + numberOfMinors);

        //2.Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет).
        List<String> surnamesOfConscripts = persons.stream()
                .filter(person -> person.getAge() > 17)
                .filter(person -> person.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(surnamesOfConscripts);

        //3.Получить отсортированный по фамилии список потенциально работоспособных людей с высшим образованием в выборке
        // (т.е. людей с высшим образованием от 18 до 60 лет для женщин и до 65 лет для мужчин).
        List<Person> peopleWithHigherEducation = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() > 17)
                .filter(person -> (person.getAge() < 60 && person.getSex() == Sex.WOMAN) || (person.getAge() < 65 && person.getSex() == Sex.MAN))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        //System.out.println(peopleWithHigherEducation);
        for (Person person : peopleWithHigherEducation) {
            System.out.println(person);
        }
    }
}
