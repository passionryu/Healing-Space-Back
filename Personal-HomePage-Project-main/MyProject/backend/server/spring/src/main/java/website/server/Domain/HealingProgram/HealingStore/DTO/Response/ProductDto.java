package website.server.Domain.HealingProgram.HealingStore.DTO.Response;

import lombok.Builder;

@Builder
public record ProductDto(
        String link,
        String image,
        String name,
        String price,
        String star

) {
}
