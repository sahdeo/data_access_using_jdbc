package LayeredArchitecture.dao;

import LayeredArchitecture.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerDao {

    void add(Customer customer);

    void update(Customer customer);

    Optional<Customer> findById(long id);

    List<Customer> findAll();

    List<Customer> findAllDescendingOrderByAge();
}
