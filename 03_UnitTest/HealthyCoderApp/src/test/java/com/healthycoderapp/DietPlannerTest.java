package com.healthycoderapp;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class DietPlannerTest {

    private DietPlanner dietPlanner;

    @BeforeAll
    static void beforeAll(){
        System.out.println("Before all unit tests.");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("After all unit tests.");
    }

    @BeforeEach
    void setup(){
        dietPlanner = new DietPlanner(20, 30, 50);
    }

    @AfterEach
    void afterEach() {
        System.out.println("A unit test was finished.");
    }

    @RepeatedTest(value = 10, name = RepeatedTest.SHORT_DISPLAY_NAME)
    void should_return_correct_diet_plan_when_correct_coder() {
        // Given
        Coder coder = new Coder(1.82, 75.0, 26, Gender.MALE);
        DietPlan dietPlanExpected = new DietPlan(2202, 110, 73, 275);


        // When
        DietPlan dietPlanActual = dietPlanner.calculateDiet(coder);

        // Then
        assertAll(
                () -> assertEquals(dietPlanExpected.getCalories(), dietPlanActual.getCalories()),
                () -> assertEquals(dietPlanExpected.getProtein(), dietPlanActual.getProtein()),
                () -> assertEquals(dietPlanExpected.getFat(), dietPlanActual.getFat()),
                () -> assertEquals(dietPlanExpected.getCarbohydrate(), dietPlanActual.getCarbohydrate())
        );
    }
}