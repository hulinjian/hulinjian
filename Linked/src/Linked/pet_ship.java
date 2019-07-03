package Linked;

import Linked.LinkImpl.Node;


interface Pet{
	public String getName();
	public String getColor();
}

class PetShop{
	private ILink<Pet> allPets=new LinkImpl<Pet>();
	public void add(Pet pet) {
		this.allPets.add(pet);
	}
	public void delete(Pet pet) {
		this.allPets.remove(pet);
	}
	public ILink<Pet> search(String keyword) {
		ILink<Pet> searchResult =new LinkImpl<Pet>();
		Object result[]=this.allPets.toArray();
		if(result!=null)
			for(Object obj:result) {
				Pet pet=(Pet) obj;//向下转型
				if(pet.getName().contains(keyword)||pet.getColor().contains(keyword)) {
					searchResult.add(pet);
				}
			}
		return searchResult;
	}
}

class Cat implements Pet{
	private String name;
	private String color;
	public Cat(String name,String color) {
		this.name=name;
		this.color=color;
	}
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getColor() {
		return this.color;
	}
	public boolean equals(Object obj) {
		if(obj==null) {
			return false;
		}
		if(!(obj instanceof Cat)) {
			return false;
		}
		if(this==obj) {
			return true;
		}
		Cat cat=(Cat) obj;
		return this.name.equals(cat.name)&&this.color.equals(cat.color);
	}
	public String toString() {
		return "【宠物猫】名字："+this.name+"、颜色:"+this.color;
	}
}
class Dog implements Pet{
	private String name;
	private String color;
	public Dog(String name,String color) {
		this.name=name;
		this.color=color;
	}
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getColor() {
		return this.color;
	}
	public boolean equals(Object obj) {
		if(obj==null) {
			return false;
		}
		if(!(obj instanceof Dog)) {
			return false;
		}
		if(this==obj) {
			return true;
		}
		Dog dog=(Dog) obj;
		return this.name.equals(dog.name)&&this.color.equals(dog.color);
	}
	public String toString() {
		return "【宠物狗】名字："+this.name+"、颜色:"+this.color;
	}
}
public class pet_ship {
	public static void main(String[] args) {
		PetShop shop=new PetShop();  //开店
		shop.add(new Dog("黄斑狗","绿色"));
		shop.add(new Dog("小强猫","深绿色"));
		shop.add(new Cat("黄猫","黄色"));
		shop.add(new Dog("斑点狗","灰色"));
		Object result[]=shop.search("黄").toArray();
		for(Object obj:result) {
			System.out.println(obj);
		}
	}
}
