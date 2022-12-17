public class Main {
	public static void main(String[] args){
		WeatherData weatherData = new WeatherData();

		CurrentConditionDisplay currentConditionDisplay = new CurrentConditionDisplay(weatherData);
		
		CurrentConditionDisplay currentConditionDisplay1 = new CurrentConditionDisplay(weatherData);

		weatherData.setMeasurements(80, 65, 30.4f);
		weatherData.setMeasurements(81, 66, 31.4f);
		weatherData.setMeasurements(82, 67, 32.4f);

	}
}
