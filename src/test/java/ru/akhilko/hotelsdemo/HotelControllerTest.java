package ru.akhilko.hotelsdemo;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Test;
import ru.akhilko.hotelsdemo.dto.HotelResponse;
import ru.akhilko.hotelsdemo.service.HotelService;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@QuarkusTest
class HotelControllerTest {
    @InjectMock
    HotelService hotelService;

    @Test
    @TestSecurity(authorizationEnabled=false)
    void list() {
        String expected = "[{\"id\":1,\"title\":\"title\",\"address\":{\"id\":10,\"index\":\"10\",\"city\":\"city\",\"street\":\"street\",\"building\":\"bld\"},\"rating\":\"***\",\"notes\":\"\"}]";
        HotelResponse.Address address = new HotelResponse.Address(10L, "10", "city", "street", "bld");
        HotelResponse response = new HotelResponse(1L, "title", address, "***", "");

        when(hotelService.list(1, 2, true))
                .thenReturn(Collections.singletonList(response));

        given()
                .when().get("/hotel?offset=1&limit=2&sortByTitle=true")
                .then()
                .statusCode(200)
                .body(is(expected));
    }
}