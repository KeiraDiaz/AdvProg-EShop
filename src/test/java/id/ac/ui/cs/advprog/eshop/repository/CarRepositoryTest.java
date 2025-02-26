package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

class CarRepositoryTest {

    private CarRepository carRepository;
    private Car car;

    @BeforeEach
    void setUp() {
        carRepository = new CarRepository();
        car = new Car();
        car.setCarName("Toyota");
        car.setCarColor("Red");
        car.setCarQuantity("10");
    }

    @Test
    void testCreateAndFind() {
        carRepository.create(car);
        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();
        assertEquals(car.getCarId(), savedCar.getCarId());
        assertEquals(car.getCarName(), savedCar.getCarName());
        assertEquals(car.getCarColor(), savedCar.getCarColor());
        assertEquals(car.getCarQuantity(), savedCar.getCarQuantity());
    }

    @Test
    void testFindByIdIfIdNotExist() {
        Car foundCar = carRepository.findById("nonexistent");
        assertNull(foundCar);
    }

    @Test
    void testUpdate() {
        carRepository.create(car);
        Car updatedCar = new Car();
        updatedCar.setCarName("Honda");
        updatedCar.setCarColor("Blue");
        updatedCar.setCarQuantity("5");

        Car result = carRepository.update(car.getCarId(), updatedCar);
        assertEquals(updatedCar.getCarName(), result.getCarName());
        assertEquals(updatedCar.getCarColor(), result.getCarColor());
        assertEquals(updatedCar.getCarQuantity(), result.getCarQuantity());
    }

    @Test
    void testDelete() {
        carRepository.create(car);
        carRepository.delete(car.getCarId());
        Car foundCar = carRepository.findById(car.getCarId());
        assertNull(foundCar);
    }
}