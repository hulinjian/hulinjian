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
		System.out.println("���г�������");
	}
	@Override
	public void getVehicleName() {
	System.out.println("�������г�");;
	}   
}
class Car extends Vehicle{
    Car(String name){
    	this.name=name;
    }
	@Override
	public void sound() {
	    System.out.println("С�γ�������");
	}
	@Override
	public void getVehicleName() {
		System.out.println("����С�γ�");;
	}
}
class Train extends Vehicle{

	@Override
	public void sound() {
		System.out.println("С�ͳ�������");		
	}

	@Override
	public void getVehicleName() {
		System.out.println("����С�ͳ�");
	}
	
}
public class Application {

	public static void main(String[] args) {
		Vehicle vehicle1=new Bike("���г�");
		Vehicle vehicle2=new Car("С�γ�");
		Vehicle vehicle3 =new Bike("С�ͳ�");
	    Simulator simulator=new Simulator();
	    simulator.playSound(vehicle1);
	    simulator.playSound(vehicle2);
	    simulator.playSound(vehicle3);
	}
}
