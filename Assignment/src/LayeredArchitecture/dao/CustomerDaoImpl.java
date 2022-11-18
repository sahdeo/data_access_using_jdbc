package LayeredArchitecture.dao;

import LayeredArchitecture.datastore.CustomerData;
import LayeredArchitecture.entity.Customer;

import java.util.*;
import java.util.stream.Collectors;

public class CustomerDaoImpl implements ICustomerDao{
    @Override
    public void add(Customer customer) {
        CustomerData.map.put(customer.getId(), customer);
    }

    @Override
    public void update(Customer customer) {
        CustomerData.map.put(customer.getId(), customer);
    }

    @Override
    public Optional<Customer> findById(long id) {
        Customer customer = CustomerData.map.get(id);
        if (customer == null) {
            Optional<Customer> optional = Optional.empty();
            return optional;
        }
        return Optional.of(customer);
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        for (Map.Entry<Long, Customer> entry : CustomerData.map.entrySet()) {
            customers.add(entry.getValue());
        }
        return customers;
    }

    @Override
    public List<Customer> findAllDescendingOrderByAge() {
        List<Customer> customers = findAll();
        Comparator<Customer> comparator = Comparator.comparing(Customer::getAge).thenComparing(Customer::getName);
        List<Customer> sortedCustomers = customers.stream().sorted(comparator).collect(Collectors.toList());
        Collections.reverse(sortedCustomers);
        return sortedCustomers;

    }
}
