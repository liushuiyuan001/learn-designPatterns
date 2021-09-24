public class HomeTheaterFacade {
	Tuner tuner;
	DvdPlayer dvdPlayer;
	
	public HomeTheaterFacade(Tuner tuner, DvdPlayer dvdPlayer) {
		this.tuner = tuner;
		this.dvdPlayer = dvdPlayer;
	}
	
	public void watchMovie(String movie) {
		System.out.println("Watching " + movie);
		tuner.on();
		dvdPlayer.on();
	}
	
	public void endMovie() {
		System.out.println("endMovie...");
		tuner.off();
		dvdPlayer.off();
	}
}
