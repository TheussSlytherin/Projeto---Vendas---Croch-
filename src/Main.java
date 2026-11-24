import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProdutoService produtoService = new ProdutoService();
        Carrinho carrinho = new Carrinho();

        int opcao = 0;

        System.out.println("========================================");
        System.out.println("   LOJA ENTRELINHAS - SISTEMA DE VENDAS ");
        System.out.println("========================================");

        while (opcao != 4) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Ver Catálogo de Produtos e Kits");
            System.out.println("2. Adicionar Produto ao Carrinho");
            System.out.println("3. Ver Carrinho e Fechamento (Totais)");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
            } else {
                System.out.println("Por favor, digite um número válido!");
                scanner.next();
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.println("\n================ CATÁLOGO DE PEÇAS ================");
                    List<Produto> catalogo = produtoService.listarTodos();
                    for (Produto p : catalogo) {
                        System.out.println("ID: " + p.getId() + " | " + p.getNome() + " — R$ " + String.format("%.2f", p.getPreco()));
                        System.out.println("    Tipo: " + p.getCategoria());
                        System.out.println("    Detalhes: " + p.getDescricao());
                        System.out.println("--------------------------------------------------");
                    }
                    break;

                case 2:
                    System.out.println("\n--- ADICIONAR AO CARRINHO ---");
                    System.out.print("Digite o ID do produto desejado: ");
                    long idProduto = scanner.nextLong();

                    Produto produtoSelecionado = produtoService.buscarPorId(idProduto).orElse(null);

                    if (produtoSelecionado != null) {
                        System.out.print("Digite a quantidade: ");
                        int qtd = scanner.nextInt();
                        if (qtd > 0) {
                            carrinho.adicionarProduto(produtoSelecionado, qtd);
                            System.out.println("✔ Adicionado com sucesso: " + qtd + "x " + produtoSelecionado.getNome());
                        } else {
                            System.out.println("❌ A quantidade precisa ser maior que zero.");
                        }
                    } else {
                        System.out.println("❌ Produto não encontrado com este ID.");
                    }
                    break;

                case 3:
                    carrinho.exibirCarrinho();
                    break;

                case 4:
                    System.out.println("\nSistema encerrado. Excelente apresentação para o seu professor! 🧶✨");
                    break;

                default:
                    System.out.println("❌ Opção inválida. Escolha um número entre 1 e 4.");
            }
        }
        scanner.close();
    }
}