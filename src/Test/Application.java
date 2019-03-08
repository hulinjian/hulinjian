package Test;
abstract class Vehicle{
	String name;
	public abstract void sound();
	public abstract void getVehicleName();
} 
class Simulator {
  
    public void playSound(Vehicle vehicle) {
    	vehicle.sound();
    	vehicle.getVehicleName();
    }
}
class Bike extends Vehicle{
    Bike(String name){
    	this.name=name;
    }
	@Override
	public void sound() {
		System.out.println("自行车咔咔咔");
	}
	@Override
	public void getVehicleName() {
	System.out.println("这是自行车");;
	}   
}
class Car extends Vehicle{
    Car(String name){
    	this.name=name;
    }
	@Override
	public void sound() {
	    System.out.println("小轿车嘀嘀嘀");
	}
	@Override
	public void getVehicleName() {
		System.out.println("这是小轿车");;
	}
}
class Train extends Vehicle{

	@Override
	public void sound() {
		System.out.println("小客车噗噗噗");		
	}

	@Override
	public void getVehicleName() {
		System.out.println("这是小客车");
	}
	
}
public class Application {

	public static void main(String[] args) {
		Vehicle vehicle1=new Bike("自行车");
		Vehicle vehicle2=new Car("小轿车");
		Vehicle vehicle3 =new Bike("小客车");
	    Simulator simulator=new Simulator();
	    simulator.playSound(vehicle1);
	    simulator.playSound(vehicle2);
	    simulator.playSound(vehicle3);
	}
}
