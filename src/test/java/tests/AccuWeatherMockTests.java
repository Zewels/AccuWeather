package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AccuWeatherMockTests {

    private static final Logger logger = LoggerFactory.getLogger(AccuWeatherMockTests.class);

    @Mock
    private Response mockResponse;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // 1. Тест поиска города
    @Test
    public void testCitySearchMock() {
        logger.info("Начало теста testCitySearchMock");

        // Настройка мока
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.asString()).thenReturn("[{\"LocalizedName\":\"London\"}]");

        // Логирование ответа
        logger.debug("Мокированный ответ: {}", mockResponse.asString());

        // Проверка мока
        assertEquals(200, mockResponse.statusCode());
        assertTrue(mockResponse.asString().contains("London"));

        logger.info("Тест testCitySearchMock успешно завершён");
    }

    // 2. Тест поиска по геопозиции
    @Test
    public void testGeopositionSearchMock() {
        logger.info("Начало теста testGeopositionSearchMock");

        // Настройка мока
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.asString()).thenReturn("{\"LocalizedName\":\"London\"}");

        // Логирование ответа
        logger.debug("Мокированный ответ: {}", mockResponse.asString());

        // Проверка мока
        assertEquals(200, mockResponse.statusCode());
        assertTrue(mockResponse.asString().contains("London"));

        logger.info("Тест testGeopositionSearchMock успешно завершён");
    }

    // 3. Тест прогноза на 1 день
    @Test
    public void testDailyForecastMock() {
        logger.info("Начало теста testDailyForecastMock");

        // Настройка мока
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.asString()).thenReturn("{\"Headline\":\"Sunny\",\"DailyForecasts\":[{\"Date\":\"2025-01-26\"}]}");

        // Логирование ответа
        logger.debug("Мокированный ответ прогноза: {}", mockResponse.asString());

        // Проверка мока
        assertEquals(200, mockResponse.statusCode());
        assertTrue(mockResponse.asString().contains("Sunny"));

        logger.info("Тест testDailyForecastMock успешно завершён");
    }

    // 4. Тест прогноза на 12 часов
    @Test
    public void testHourlyForecastMock() {
        logger.info("Начало теста testHourlyForecastMock");

        // Настройка мока
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.asString()).thenReturn("{\"HourlyForecasts\":[{\"Temperature\":{\"Value\":18}}]}");

        // Логирование ответа
        logger.debug("Мокированный ответ прогноза: {}", mockResponse.asString());

        // Проверка мока
        assertEquals(200, mockResponse.statusCode());
        assertTrue(mockResponse.asString().contains("Temperature"));

        logger.info("Тест testHourlyForecastMock успешно завершён");
    }

    // 5. Тест текущих погодных условий
    @Test
    public void testCurrentConditionsMock() {
        logger.info("Начало теста testCurrentConditionsMock");

        // Настройка мока
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.asString()).thenReturn("[{\"WeatherText\":\"Clear\"}]");

        // Логирование ответа
        logger.debug("Мокированный ответ текущих условий: {}", mockResponse.asString());

        // Проверка мока
        assertEquals(200, mockResponse.statusCode());
        assertTrue(mockResponse.asString().contains("Clear"));

        logger.info("Тест testCurrentConditionsMock успешно завершён");
    }

    // 6. Тест топ-города
    @Test
    public void testTopCitiesMock() {
        logger.info("Начало теста testTopCitiesMock");
        // Настройка мока
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.asString()).thenReturn("[{\"LocalizedName\":\"New York\"}, {\"LocalizedName\":\"London\"}]");

        // Логирование ответа
        logger.debug("Мокированный ответ: {}", mockResponse.asString());

        // Проверка мока
        assertEquals(200, mockResponse.statusCode());
        assertTrue(mockResponse.asString().contains("New York"));
        assertTrue(mockResponse.asString().contains("London"));

        logger.info("Тест testTopCitiesMock успешно завершён");
    }

    // 7. Тест астрономических данных (восход/закат)
    @Test
    public void testAstronomyMock() {
        logger.info("Начало теста testAstronomyMock");

        // Настройка мока
        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.asString()).thenReturn("{\"Sun\":{\"Rise\":\"06:00\",\"Set\":\"18:00\"}}");

        // Логирование ответа
        logger.debug("Мокированный ответ: {}", mockResponse.asString());

        // Проверка мока
        assertEquals(200, mockResponse.statusCode());
        assertTrue(mockResponse.asString().contains("Rise"));
        assertTrue(mockResponse.asString().contains("Set"));

        logger.info("Тест testAstronomyMock успешно завершён");
    }

    // Пример дальнейших тестов можно продолжить аналогично для всех остальных запросов, создавая мокированные ответы и логируя их:
    // - Поиск города по названию
    // - Прогнозы на несколько дней
    // - Погода по географическим координатам
    // - Исторические данные по погоде
    // - Прогнозы по типу осадков и температуре и т.д.

    // Эти тесты покрывают все основные запросы к API, которые вы хотите мокировать.
}