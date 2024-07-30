// Exercise 6: Implementing the PROXY PATTERN

public class Exercise6_ProxyPatternExample {

    // Image Interface
    interface Image {
        // display() Method
        void display();
    }

    // RealImage Class
    static class RealImage implements Image {
        private String filename;

        public RealImage(String filename) {
            this.filename = filename;
            loadImageFromServer();
        }

        private void loadImageFromServer() {
            //Loads image from a remote server
            System.out.println("Loading image: " + filename);
        }

        @Override
        public void display() {
            System.out.println("Displaying image: " + filename);
        }
    }

    // ProxyImage class
    static class ProxyImage implements Image {
        private RealImage realImage;
        private String filename;

        public ProxyImage(String filename) {
            this.filename = filename;
        }

        @Override
        public void display() {
            if (realImage == null) {
                realImage = new RealImage(filename);
            }
            realImage.display();
        }
    }

    // Test class to demonstrate Proxy Pattern
    public static void main(String[] args) {
        Image image1 = new ProxyImage("image1.jpg");
        Image image2 = new ProxyImage("image2.jpg");

        image1.display();  
        image1.display();  

        image2.display();
    }
}



// OUTPUT:
// Loading image: image1.jpg
// Displaying image: image1.jpg
// Displaying image: image1.jpg
// Loading image: image2.jpg
// Displaying image: image2.jpg