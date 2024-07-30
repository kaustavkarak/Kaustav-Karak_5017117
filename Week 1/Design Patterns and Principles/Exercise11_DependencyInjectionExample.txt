// Exercise 11: Implementing DEPENDENCY INJECTION

// CustomerRepository Interface
interface CustomerRepository {
    // findCustomerById() Method
    Customer findCustomerById(String id);
}

// Concrete Repository: CustomerRepositoryImpl class implementing CustomerRepository
class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public Customer findCustomerById(String id) {
        if ("101".equals(id)) {
            return new Customer("101", "Harry Wood", "harry.wood@email.com");
        }
        return null;
    }
}

// CustomerService class that depends on CustomerRepository
class CustomerService {
    private CustomerRepository customerRepository;

    // Constructor Injection to inject CustomerRepository into CustomerService
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerById(String id) {
        return customerRepository.findCustomerById(id);
    }
}

class Customer {
    private String id;
    private String name;
    private String email;

    public Customer(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Customer ID: " + id + ", Name: " + name + ", Email: " + email;
    }
}

// Main class to demonstrate Dependency Injection
public class Exercise11_DependencyInjectionExample {
    public static void main(String[] args) {

        CustomerRepository customerRepository = new CustomerRepositoryImpl();

        CustomerService customerService = new CustomerService(customerRepository);

        Customer customer = customerService.getCustomerById("101");
        if (customer != null) {
            System.out.println(customer);
        } else {
            System.out.println("Customer not found!");
        }
    }
}



// OUTPUT:
// Customer ID: 101, Name: Harry Wood, Email: harry.wood@email.com