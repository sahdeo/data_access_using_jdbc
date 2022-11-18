package LayeredArchitecture.frontend;

import LayeredArchitecture.entity.Customer;
import LayeredArchitecture.exception.CustomerNotFoundException;
import LayeredArchitecture.exception.InvalidArgumentException;
import LayeredArchitecture.service.CustomerServiceImpl;

import java.util.List;

public class FrontEnd {
    CustomerServiceImpl customerService=new CustomerServiceImpl();

    public void runUI(){
        try {
            customerService.register("sahdeo", 24);
            customerService.register("mahendra", 28);
            customerService.register("suresh", 29);
            customerService.register("hemant", 30);
            customerService.register("ravi", 31);
            customerService.register("chandan", 32);

            //Implementation of service
            System.out.println("-------Customer of id 2----------");
            display(customerService.findById(2));
            System.out.println("\n-------Name Changed-------");
            display(customerService.changeName(2,"kamlesh"));
            System.out.println("\n-------No. of customer lesser than 29----------");
            System.out.println(customerService.countCustomersLesserThanAge(29));
            System.out.println("\n---- Customers arranged in descending order by  age ----");
            displayList(customerService.findAllDescendingOrderByAge());
            System.out.println("\n---- Customers whose age is greater than 29 ordered in descending order ----");
            displayList(customerService.findAllCustomerGreaterThanAgeDescendOrder(29));


        }
        catch (InvalidArgumentException ia){
            System.out.println("inputs you entered are invalid,"+ia.getMessage());
        }
        catch (CustomerNotFoundException cnf){
            System.out.println("Customer not found for id you provided,"+cnf.getMessage());
        }
        catch (Exception e){
            System.out.println("something went wrong, Please try again");
        }

    }
    void display(Customer customer){
        System.out.println("id="+ customer.getId()+",name="+ customer.getName()+",price="+ customer.getAge());
    }
    void displayList(List<Customer> customerList){
        for (Customer customer:customerList)
            display(customer);
    }
}

