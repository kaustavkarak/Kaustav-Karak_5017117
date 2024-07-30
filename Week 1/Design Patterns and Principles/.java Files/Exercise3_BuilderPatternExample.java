// Exercise 3: Implementing the BUILDER PATTERN

public class Exercise3_BuilderPatternExample {

    // Computer class 
    public static class Computer {
        private String CPU;
        private String RAM;
        private String storage;
        private String GPU;
        private String motherboard;

        // Private Constructor that takes the Builder as a parameter
        private Computer(Builder builder) {
            this.CPU = builder.CPU;
            this.RAM = builder.RAM;
            this.storage = builder.storage;
            this.GPU = builder.GPU;
            this.motherboard = builder.motherboard;
        }

        @Override
        public String toString() {
            return "Computer {" +
                    "CPU='" + CPU + '\'' +
                    ", RAM='" + RAM + '\'' +
                    ", Storage='" + storage + '\'' +
                    ", GPU='" + GPU + '\'' +
                    ", Motherboard='" + motherboard + '\'' +
                    '}';
        }

        // Static nested Builder class
        public static class Builder {
            private String CPU;
            private String RAM;
            private String storage;
            private String GPU;
            private String motherboard;

            public Builder setCPU(String CPU) {
                this.CPU = CPU;
                return this;
            }

            public Builder setRAM(String RAM) {
                this.RAM = RAM;
                return this;
            }

            public Builder setStorage(String storage) {
                this.storage = storage;
                return this;
            }

            public Builder setGPU(String GPU) {
                this.GPU = GPU;
                return this;
            }

            public Builder setMotherboard(String motherboard) {
                this.motherboard = motherboard;
                return this;
            }

            // build() method in the Builder class that returns an instance of Computer
            public Computer build() {
                return new Computer(this);
            }
        }
    }

    // Test class to demonstrate The Builder Pattern
    public static void main(String[] args) {
        //Computer with some specific configurations
        Computer gamingComputer = new Computer.Builder()
                .setCPU("AMD Ryzen 7")
                .setRAM("16GB")
                .setStorage("1TB SSD")
                .setGPU("NVIDIA GTX 1650")
                .setMotherboard("ASUS ROG")
                .build();

        // Another Computer with some different configurations
        Computer officeComputer = new Computer.Builder()
                .setCPU("Intel i5")
                .setRAM("8GB")
                .setStorage("512GB HDD")
                .build();

        // Display the Computer configurations
        System.out.println("Gaming Computer: " + gamingComputer);
        System.out.println("Office Computer: " + officeComputer);
    }
}


// OUTPUT:
// Gaming Computer: Computer {CPU='AMD Ryzen 7', RAM='16GB', Storage='1TB SSD', GPU='NVIDIA GTX 1650', Motherboard='ASUS ROG'}
// Office Computer: Computer {CPU='Intel i5', RAM='8GB', Storage='512GB HDD', GPU='null', Motherboard='null'}
