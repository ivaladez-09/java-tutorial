package com.platzi.functional._15_streams_intro;

import com.platzi.functional._06_reference_operator.NombresUtils;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Stream;

public class Streams {
    public static void main(String[] args) {
        List<String> courseList = NombresUtils.getList(
                "Java",
                "FrontEnd",
                "Backend",
                "FullStack"
        );
        for (String course: courseList) {
            String newCourse = course.toLowerCase().replace("!", "!!");
            System.out.println("Platzi: " + newCourse);
        }

        Stream<String> coursesStream = Stream.of(
                "Java",
                "FrontEnd",
                "Backend",
                "FullStack");
        Stream<Integer> courseLengthStream = coursesStream.map(course -> course.length());
        Optional<Integer> logngest = courseLengthStream.max((x, y) -> y - x);

        Stream<String> coursesStream2 = Stream.of(
                "Java",
                "FrontEnd",
                "Backend",
                "FullStack");
        Stream<String> emphasisCourses = coursesStream2.map(course -> course + "!");
        Stream<String> justJavaCourses = emphasisCourses.filter(course -> course.contains("Java"));
        justJavaCourses.forEach(System.out::println);

        // Chaining
        Stream<String> coursesStream3 = courseList.stream();
        addOperator(coursesStream3.map(course -> course + "!")
                .filter(course -> course.contains("Java"))
        ).forEach(System.out::println);
    }

    static <T> Stream<T> addOperator(Stream<T> stream){
        return stream.peek(data -> System.out.println("Data: " + data));
    }
}
