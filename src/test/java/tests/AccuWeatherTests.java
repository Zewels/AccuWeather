package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class AccuWeatherTests {

    // Константы для базовых параметров
    private final String BASE_URL = "https://dataservice.accuweather.com";
    private final String API_KEY = "j51jsHhKnXfPBNMyhnx4Fto0C1c6qEn6";

    @Test
    public void testCitySearch() {
        String cityName = "London";

        given()
                .baseUri(BASE_URL)
                .queryParam("apikey", API_KEY)
                .queryParam("q", cityName)
                .when()
                .get("/locations/v1/cities/search")
                .then()
                .statusCode(200) // Проверка статуса ответа
                .body("size()", greaterThan(0)) // Убедимся, что результат не пустой
                .body("[0].LocalizedName", equalTo(cityName)); // Проверка названия города
    }

    @Test
    public void testGeopositionSearch() {
        String coordinates = "51.5074,-0.1278"; // Координаты Лондона

        given()
                .baseUri(BASE_URL)
                .queryParam("apikey", API_KEY)
                .queryParam("q", coordinates)
                .when()
                .get("/locations/v1/cities/geoposition/search")
                .then()
                .statusCode(200)
                .body("LocalizedName", equalTo("London")); // Проверка, что найден Лондон
    }

    @Test
    public void testDailyForecast1Day() {
        String locationKey = "328328"; // Код локации для Лондона

        given()
                .baseUri(BASE_URL)
                .queryParam("apikey", API_KEY)
                .when()
                .get("/forecasts/v1/daily/1day/" + locationKey)
                .then()
                .statusCode(200)
                .body("Headline", notNullValue()) // Проверяем, что есть данные прогноза
                .body("DailyForecasts.size()", equalTo(1)); // Проверяем, что прогноз на 1 день
    }

    @Test
    public void testHourlyForecast12Hours() {
        String locationKey = "328328"; // Код локации для Лондона

        given()
                .baseUri(BASE_URL)
                .queryParam("apikey", API_KEY)
                .when()
                .get("/forecasts/v1/hourly/12hour/" + locationKey)
                .then()
                .statusCode(200)
                .body("size()", equalTo(12)) // Прогноз должен быть на 12 часов
                .body("[0].Temperature", notNullValue()); // Проверяем, что есть температура
    }

    @Test
    public void testCurrentConditions() {
        String locationKey = "328328"; // Код локации для Лондона

        given()
                .baseUri(BASE_URL)
                .queryParam("apikey", API_KEY)
                .when()
                .get("/currentconditions/v1/" + locationKey)
                .then()
                .statusCode(200)
                .body("[0].WeatherText", notNullValue()); // Проверка текста погодных условий
    }

    @Test
    public void testTopCities() {
        int count = 5;

        given()
                .baseUri(BASE_URL)
                .queryParam("apikey", API_KEY)
                .when()
                .get("/locations/v1/topcities/" + count)
                .then()
                .statusCode(200)
                .body("size()", equalTo(count)); // Убедимся, что результат содержит 5 городов
    }
    
    @Test
    public void testAstronomy() {
        String locationKey = "328328"; // Код Лондона

        given()
                .baseUri(BASE_URL)
                .queryParam("apikey", API_KEY)
                .when()
                .get("/astronomy/v1/sunmoon/" + locationKey)
                .then()
                .statusCode(200)
                .body("Sun.Rise", notNullValue()) // Проверяем, что есть данные о восходе
                .body("Sun.Set", notNullValue()); // Проверяем, что есть данные о закате
    }

}
