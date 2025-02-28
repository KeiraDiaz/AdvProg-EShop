package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    private Car car;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        car = new Car();
        car.setCarId("1");
        car.setCarName("Toyota");
        car.setCarColor("Red");
        car.setCarQuantity("10");
    }

    @Test
    void testCreate() {
        when(carRepository.create(car)).thenReturn(car);
        Car createdCar = carService.create(car);
        assertEquals(car, createdCar);
        verify(carRepository, times(1)).create(car);
    }

    @Test
    void testFindAll() {
        when(carRepository.findAll()).thenReturn(Arrays.asList(car).iterator());
        List<Car> cars = carService.findAll();
        assertEquals(1, cars.size());
        assertEquals(car, cars.get(0));
        verify(carRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        when(carRepository.findById("1")).thenReturn(car);
        Car foundCar = carService.findById("1");
        assertEquals(car, foundCar);
        verify(carRepository, times(1)).findById("1");
    }

    @Test
    void testUpdate() {
        // Arrange
        Car updatedCar = new Car();
        updatedCar.setCarId("1");
        updatedCar.setCarName("Honda");
        updatedCar.setCarColor("Blue");
        updatedCar.setCarQuantity("5");
    
        // Stub the repository's update method to return the updated car
        when(carRepository.update("1", updatedCar)).thenReturn(updatedCar);
    
        // Act
        carService.update("1", updatedCar);
    
        // Assert
        verify(carRepository, times(1)).update("1", updatedCar);
    }

    @Test
    void testDeleteCarById() {
        doNothing().when(carRepository).delete("1");
        carService.deleteCarById("1");
        verify(carRepository, times(1)).delete("1");
    }
}