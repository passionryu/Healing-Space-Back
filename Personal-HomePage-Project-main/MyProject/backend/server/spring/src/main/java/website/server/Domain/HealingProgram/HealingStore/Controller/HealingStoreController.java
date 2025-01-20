package website.server.Domain.HealingProgram.HealingStore.Controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import website.server.Domain.HealingProgram.HealingStore.DTO.Response.ProductDto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/healingstore")
@Tag(name = "Healing Store", description = "Healing Store 서비스 API")
public class HealingStoreController {

    @GetMapping("")
    public List<ProductDto> get() {

        List<ProductDto> products = new ArrayList<>();

        try {
            // 웹 페이지 URL
            String url = "https://www.coupang.com/np/search?component=&q=%ED%96%A5&channel=user"; // 적절한 URL로 변경

            // Jsoup으로 페이지 가져오기
            Document doc = Jsoup.connect(url)
                    .timeout(120000) // 60초로 타임아웃 설정
                    .get();

            // 제품 링크 추출
            Elements productElements = doc.select("a.search-product-link");

            for (Element element : productElements) {
                String productLink = element.attr("href");

                ProductDto product = ProductDto.builder()
                        .link(productLink)
                        .build();

                products.add(product);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }

}
