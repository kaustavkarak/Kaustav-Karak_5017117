// Exercise 9: Implementing the COMMAND PATTERN

// Command interface
interface Command {
    // execute() Method
    void execute();
}

// Light class
class Light {
    public void turnOn() {
        System.out.println("The light is ON.");
    }

    public void turnOff() {
        System.out.println("The light is OFF.");
    }
}

// LightOnCommand class implementing Command: For turning the light on
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

// LightOffCommand class implementing Command: For turning the light on
class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

// RemoteControl class (Invoker class)
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}

// Test Class to demonstrate Command Pattern
public class Exercise9_CommandPatternExample {
    public static void main(String[] args) {

        Light livingRoomLight = new Light();

        Command lightOn = new LightOnCommand(livingRoomLight);
        Command lightOff = new LightOffCommand(livingRoomLight);

        RemoteControl remote = new RemoteControl();

        // Turning the light on
        remote.setCommand(lightOn);
        System.out.println("Pressing the button to turn on the light:");
        remote.pressButton();

        // Turning the light off
        remote.setCommand(lightOff);
        System.out.println("Pressing the button to turn off the light:");
        remote.pressButton();
    }
}



// OUTPUT:
// Pressing the button to turn on the light:
// The light is ON.
// Pressing the button to turn off the light:
// The light is OFF.