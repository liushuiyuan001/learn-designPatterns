public class Main {
	public static void main(String[] args) {
		RemoteControlWithUndo remoteControl = new RemoteControlWithUndo();
		
		Light liveLight = new Light("Living Room");
		
		LightOnCommand lightOnCommand = new LightOnCommand(liveLight);
		LightOffCommand lightOffCommand = new LightOffCommand(liveLight);
		
		remoteControl.setCommand(0,lightOnCommand, lightOffCommand);
		
		remoteControl.onButtonWasPushed(0);
		remoteControl.offButtonWasPushed(0);
		System.out.println(remoteControl);
		
		remoteControl.undoButtonWasPushed();
		remoteControl.offButtonWasPushed(0);
		remoteControl.onButtonWasPushed(0);
		System.out.println(remoteControl);
		remoteControl.undoButtonWasPushed();
	}
}
