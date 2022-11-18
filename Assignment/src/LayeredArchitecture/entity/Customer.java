package LayeredArchitecture.entity;

import java.util.Objects;

public class Customer {
        private long id;

        private String name;

        private int age;
        ;

        public Customer() {
        }

        public Customer(long id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public void setId(long id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public long getId() {
            return id;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null && getClass() != obj.getClass())
                return false;
            Customer customer = (Customer) obj;
            return customer.id == id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
}
