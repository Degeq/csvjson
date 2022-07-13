package com.gorlovoy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ShortUrlServiceTest {

    @Test
    @DisplayName("Успешное создание короткой ссылки")
    public void successShortUrlCreation() {
        //given
        ShortUrlService shortUrlService= new ShortUrlDoing();
        String longUrl = "yandex.ru";

        //when
        var shortUrl = shortUrlService.createShortUrl(longUrl);
        Assertions.assertNotNull(shortUrl);

        //then
        var actualLongUrl = shortUrlService.getLongUrl(shortUrl);
        Assertions.assertNotNull(actualLongUrl);
        Assertions.assertEquals(longUrl,actualLongUrl);
    }

    @Test
    @DisplayName("Успешное удаление")
    public void succesfulDeleting() {
        //given
        ShortUrlService shortUrlService = new ShortUrlDoing();
        var longUrl = "www.youtube.com";

        //when
        var shortUrl = shortUrlService.createShortUrl(longUrl);
        Assertions.assertNotNull(shortUrl);
        shortUrlService.deletePair(shortUrl);

        //then
        var actualShortUrl = shortUrlService.getLongUrl(shortUrl);
        Assertions.assertNull(actualShortUrl);
    }

    @Test
    @DisplayName("Валидное значение")
    public void shouldThrowError() {
        ShortUrlService shortUrlService = new ShortUrlDoing();
        var notValidLongUrl = "www.goo@gle.com";

    }
}
