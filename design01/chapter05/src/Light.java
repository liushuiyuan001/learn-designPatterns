public class Light {
	public String name;
	public Light(String name) {
		this.name = name;
	}
	
	public void off() {
		System.out.println(this.name + " off");
	}
	
	public void on() {
		System.out.println(this.name + " on");
	}
}
