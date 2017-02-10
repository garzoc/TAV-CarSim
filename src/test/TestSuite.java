package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.Car;
import main.CarState;

public class TestSuite {
    private int[] parkingSpaces = new int[500];

    @Before
    public void setUp() {
        CarState carState = new CarState(0,false,0);
        Car car = new Car(carState);
    }


    @Test
    public void MoveForward() {
        
    }

    @Test
    public void isEmpty() {

    }

    @Test
    public void MoveBackward() {

    }

    @Test
    public void Park() {

    }

    @Test
    public void UnPark() {

    }


/*
    WhereIs() method test cases
 */
    @Test
    public void WhereIs1() {

    }
    @Test
    public void WhereIs2() {

    }
    @Test
    public void WhereIs3() {

    }
    @Test
    public void WhereIs4() {

    }
    @Test
    public void WhereIs5() {

    }
    @Test
    public void WhereIs6() {

    }
    @Test
    public void WhereIs7() {

    }
    @Test
    public void WhereIs8() {

    }
    @Test
    public void WhereIs9() {

    }




    @After
    public void tearDown() {

    }

}