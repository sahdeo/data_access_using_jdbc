package LayeredArchitecture.service;

import LayeredArchitecture.entity.Customer;
import LayeredArchitecture.exception.CustomerNotFoundException;
import LayeredArchitecture.exception.InvalidArgumentException;

import java.util.List;

public interface ICustomerService {
    Customer register(String name, int age) throws InvalidArgumentException;

    Customer changeName(int id, String newName) throws InvalidArgumentException, CustomerNotFoundException;

    Customer findById(long id) throws InvalidArgumentException,CustomerNotFoundException;

    List<Customer> findAllDescendingOrderByAge();

    List<Customer> findAllCustomerGreaterThanAgeDescendOrder(int age) throws InvalidArgumentException;

    int countCustomersLesserThanAge(int age) throws InvalidArgumentException;
}
