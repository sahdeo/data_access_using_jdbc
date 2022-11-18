package LayeredArchitecture.service;

import LayeredArchitecture.dao.CustomerDaoImpl;
import LayeredArchitecture.entity.Customer;
import LayeredArchitecture.exception.CustomerNotFoundException;
import LayeredArchitecture.exception.InvalidArgumentException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements ICustomerService {
    CustomerDaoImpl customerDao = new CustomerDaoImpl();

    long createdId;

    long getCreatedId() {
        return ++createdId;
    }

    @Override
    public Customer register(String name, int age) throws InvalidArgumentException {

        long id = getCreatedId();
        validateId(id, "Id can't be zero or negative");
        validateName(name, "name length should not be less than chars 2 and greater than 20");
        validateAge(age, "Age should be in between 10 to 100 both inclusive");
        Customer customer = new Customer(id, name, age);
        customerDao.add(customer);
        return customer;
    }

    @Override
    public Customer changeName(int id, String newName) throws InvalidArgumentException, CustomerNotFoundException {
        validateName(newName, "name length should not be less than chars 2 and greater than 20");
        validateId(id, "Id can't be zero or negative");
        Optional<Customer> optionalCustomer = customerDao.findById(id);
        if (optionalCustomer.isEmpty())
            throw new CustomerNotFoundException("Customer not found for id " + id);
        Customer customer = optionalCustomer.get();
        customer.setName(newName);
        return customer;
    }

    @Override
    public Customer findById(long id) throws InvalidArgumentException, CustomerNotFoundException {
        validateId(id,"Id can't be zero or negative");
        Optional<Customer> optionalCustomer = customerDao.findById(id);
        if (optionalCustomer.isEmpty())
            throw new CustomerNotFoundException("Customer not found for id " + id);
        Customer customer = optionalCustomer.get();
        return customer;
    }

    @Override
    public List<Customer> findAllDescendingOrderByAge() {
        List<Customer> customers = customerDao.findAllDescendingOrderByAge();
        return customers;
    }

    @Override
    public List<Customer> findAllCustomerGreaterThanAgeDescendOrder(int age) throws InvalidArgumentException {
        validateAge(age, "Age should be in between 10 to 100 both inclusive");
        List<Customer> sortedList = customerDao.findAllDescendingOrderByAge();
        List<Customer> filteredList = sortedList.stream().filter(customer -> customer.getAge() > age).collect(Collectors.toList());
        return filteredList;
    }

    @Override
    public int countCustomersLesserThanAge(int age) throws InvalidArgumentException {
        validateAge(age, "Age should be in between 10 to 100 both inclusive");
        List<Customer> customers = customerDao.findAll();
        int c = 0;
        for (Customer customer : customers) {
            if (customer.getAge() < age)
                c++;
        }
        return c;
    }

    void validateAge(int age, String msg) throws InvalidArgumentException {
        if (age < 10 && age > 100)
            throw new InvalidArgumentException(msg);
    }

    void validateName(String name, String msg) throws InvalidArgumentException {
        if (name.trim().length() < 2 && name.trim().length() > 20)
            throw new InvalidArgumentException(msg);
    }

    void validateId(long id, String msg) throws InvalidArgumentException {
        if (id <= 0)
            throw new InvalidArgumentException(msg);

    }
}
