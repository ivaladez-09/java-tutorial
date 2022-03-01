package com.healthycoderapp;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class BMICalculatorTest {

    @Nested
    class IsDietRecommendedTests {
        @ParameterizedTest(name = "weight={0}, height={1}")
        //@ValueSource(doubles = {89.0, 95.0, 110.0})
        //@CsvFileSource(resources = "/fileName.csv", numLinesToSkip = 1)
        @CsvSource(value = {"89.0, 1.72", "95.0, 1.75", "110.0, 1.78"})
        void should_return_true_when_diet_recommended(Double coderWeight, Double coderHeight) {
            // given
            double weight = coderWeight;
            double height = coderHeight;

            // when
            boolean recommended = BMICalculator.isDietRecommended(weight, height);

            // then
            assertTrue(recommended);
        }

        @Test
        void should_return_false_when_diet_not_recommended() {
            // given
            double weight = 50.0;
            double height = 1.92;

            // when
            boolean recommended = BMICalculator.isDietRecommended(weight, height);

            // then
            assertFalse(recommended);
        }

        @Test
        void should_throw_arithmetic_exception_when_height_zero() {
            // given
            double weight = 50.0;
            double height = 0;

            // when
            Executable executable = () -> BMICalculator.isDietRecommended(weight, height);

            // then
            assertThrows(ArithmeticException.class, executable);
        }
    }

    @Nested
    class FindCoderWIthWorstBMITest{

        private String environment = "dev";

        @Test
        @DisplayName("This is the display name when running the test case")
        void should_return_coder_with_worst_BMI_when_coder_list_not_empty() {
            // Given
            List<Coder> coders = new ArrayList<>();
            coders.add(new Coder(1.80, 60.0));
            coders.add(new Coder(1.82, 98.0));
            coders.add(new Coder(1.82, 64.7));

            // When
            Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

            // Then
            assertAll(
                    () -> assertEquals(1.82, coderWorstBMI.getHeight()),
                    () -> assertEquals(98.0, coderWorstBMI.getWeight())
            );
        }

        // PERFORMANCE Unit Test
        @Test
        void should_return_coder_with_worst_BMI_in_1ms_when_coder_list_has_10000_elements(){
            // Given
            assumeTrue(this.environment.equals("prod"));  // Test skipped when env is not "prod"
            List<Coder> coders = new ArrayList<>();
            for (int i=0; i < 10000; i++){
                coders.add(new Coder(1.0 + i, 10.0 + i));
            }

            // When
            Executable executable = () -> BMICalculator.findCoderWithWorstBMI(coders);

            // Then
            assertTimeout(Duration.ofMillis(10), executable);
        }

        @Test
        //@Disabled -> To skip the test
        void should_return_null_worst_BMI_when_coder_list_empty() {
            // Given
            List<Coder> coders = new ArrayList<>();

            // When
            Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);

            // Then
            assertNull(coderWorstBMI);
        }
    }

    @Nested
    class GetBMIScoresTest{
        @Test
        void should_return_correct_BMI_score_array_when_coder_list_not_empty() {
            // Given
            List<Coder> coders = new ArrayList<>();
            coders.add(new Coder(1.80, 60.0));
            coders.add(new Coder(1.82, 98.0));
            coders.add(new Coder(1.82, 64.7));
            double [] expected = {18.52, 29.59, 19.53};

            // When
            double [] bmiScores = BMICalculator.getBMIScores(coders);

            // Then
            assertArrayEquals(expected, bmiScores);
        }
    }
}