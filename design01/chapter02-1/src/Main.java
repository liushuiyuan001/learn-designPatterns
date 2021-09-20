import java.util.Observable;

public class Main {
	public static void main(String[] args){
		WeatherData weatherData = new WeatherData();

		CurrentConditionsDisplay currentConditionDisplay = new CurrentConditionsDisplay((Observable) weatherData);


		weatherData.setMeasurements(80, 65, 30.4f);
		weatherData.setMeasurements(81, 66, 31.4f);
		weatherData.setMeasurements(82, 67, 32.4f);

	}
}
