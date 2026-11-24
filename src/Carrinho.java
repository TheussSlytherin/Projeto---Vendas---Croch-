import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private List<ItemCarrinho> itens = new ArrayList<>();
    private final double FRETE_FIXO = 15.00; // Valor fixo de frete alinhado com a loja

    // Adiciona um produto ou aumenta a quantidade se ele já estiver no carrinho
    public void adicionarProduto(Produto produto, int quantidade) {
        for (ItemCarrinho item : itens) {
            if (item.getProduto().getId().equals(produto.getId())) {
                item.setQuantidade(item.getQuantidade() + quantidade);
                return;
            }
        }
        itens.add(new ItemCarrinho(produto, quantidade));
    }

    // Calcula o subtotal de todos os itens no carrinho
    public double calcularSubtotal() {
        double subtotal = 0.0;
        for (ItemCarrinho item : itens) {
            subtotal += item.getSubtotalItem();
        }
        return subtotal;
    }

    // Calcula o valor total final (Subtotal + Frete)
    public double calcularTotalGeral() {
        if (itens.isEmpty()) {
            return 0.0;
        }
        return calcularSubtotal() + FRETE_FIXO;
    }

    // Exibe os itens do carrinho no console para apresentação
    public void exibirCarrinho() {
        System.out.println("\n===== 🛍️ SEU CARRINHO DE COMPRAS =====");
        if (itens.isEmpty()) {
            System.out.println("O carrinho está vazio.");
        } else {
            for (ItemCarrinho item : itens) {
                System.out.println("- " + item.getProduto().getNome() +
                        " | Qtd: " + item.getQuantidade() +
                        " | Subtotal: R$ " + String.format("%.2f", item.getSubtotalItem()));
            }
            System.out.println("----------------------------------------");
            System.out.println("Subtotal dos Produtos: R$ " + String.format("%.2f", calcularSubtotal()));
            System.out.println("Frete Fixo: R$ " + String.format("%.2f", FRETE_FIXO));
            System.out.println("VALOR TOTAL GERAL: R$ " + String.format("%.2f", calcularTotalGeral()));
        }
        System.out.println("========================================\n");
    }

    public List<ItemCarrinho> getItens() {
        return itens;
    }
}
