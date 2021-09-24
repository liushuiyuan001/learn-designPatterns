public class Main {
	public static void main(String[] args) {
		DvdPlayer dvdPlayer = new DvdPlayer();
		Tuner tuner = new Tuner();
		
		HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade(tuner, dvdPlayer);
		
		homeTheaterFacade.watchMovie("China Chung fu");
		homeTheaterFacade.endMovie();
	}
}
