import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProdutoService {

    private final List<Produto> catalogoProdutos = Arrays.asList(
            new Produto(
                    1L,
                    "Chaveiro / Mini Amigurumi",
                    "Mini peça de crochê artesanal ideal para bolsas e chaves, feita com fio 100% algodão.",
                    25.00,
                    "Mais vendido"
            ),
            new Produto(
                    2L,
                    "Bichinho Pronta Entrega",
                    "Amigurumi de tamanho médio padrão, macio, seguro e antialérgico para crianças.",
                    45.00,
                    "Pronta entrega"
            ),
            new Produto(
                    3L,
                    "Encomenda Personalizada",
                    "Peça exclusiva feita sob medida conforme as cores, o animal e o tamanho escolhido pelo cliente.",
                    70.00,
                    "Sob medida"
            ),
            new Produto(
                    4L,
                    "Kit Presente Dueto (Amigurumi + Chaveiro)",
                    "Conjunto especial contendo um bichinho médio e um chaveiro combinando, com embalagem de presente inclusa.",
                    110.00,
                    "Kit Promocional"
            ),
            new Produto(
                    5L,
                    "Kit Colecionável (3 Mini Amigurumis)",
                    "Família de mini bichinhos artesanais em miniatura para colecionar ou decorar.",
                    95.00,
                    "Kit Especial"
            )
    );

    public List<Produto> listarTodos() {
        return catalogoProdutos;
    }

    public Optional<Produto> buscarPorId(Long id) {
        return catalogoProdutos.stream()
                .filter(produto -> produto.getId().equals(id))
                .findFirst();
    }
}