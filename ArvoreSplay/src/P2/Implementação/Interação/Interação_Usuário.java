package P2.Implementação.Interação;

import P2.Implementação.EstruturaDaÁrvore.*;
import java.util.Scanner;

public class Interação_Usuário {

    private static ArvoreSplay arvore;

    public static void InteraçãoMenu() {
        arvore = new ArvoreSplay();
        Scanner scanner = new Scanner(System.in);
        int Aux;

        do {
            exibirMenu();
            Aux = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (Aux) {
                case 1:
                    System.out.println("Digite o código do produto a ser adicionado:");
                    int Código = scanner.nextInt();
                    arvore.Inserir(Código);
                    System.out.println("Produto adicionado com sucesso: " + Código);
                    break;
                case 2:
                    System.out.println("Digite o código do produto a ser removido:");
                    int CódigoParaDeletar = scanner.nextInt();
                    boolean Confirmação = arvore.Deletar(CódigoParaDeletar);
                    if(Confirmação) {
                        System.out.println("Produto removido com sucesso: " + CódigoParaDeletar);
                    }
                    else{
                        System.out.println("Produto não pode ser removido");
                    }
                    break;
                case 3:
                    System.out.println("Digite o código do produto a ser buscado:");
                    int CódigoParaBuscar = scanner.nextInt();
                    No Resultado = arvore.Buscar(CódigoParaBuscar);
                    if (Resultado != null) {
                        System.out.println("Produto encontrado: " + Resultado.getChave());
                    } else {
                        System.out.println("Produto não encontrado.");
                    }
                    break;
                case 4:
                    System.out.println("Catálogo de Produtos:");
                    arvore.ExibirCatalogoPorAcessos(); // Método para exibir o catálogo na ArvoreSplay
                    break;
                case 5:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                    break;
            }
        } while (Aux != 5);

        scanner.close();
    }

    // Método para exibir o menu de opções
    private static void exibirMenu() {
        System.out.println("\n=== Sistema de E-commerce ===");
        System.out.println("1. Adicionar Produto");
        System.out.println("2. Remover Produto");
        System.out.println("3. Buscar Produto");
        System.out.println("4. Exibir Catálogo");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }
}
