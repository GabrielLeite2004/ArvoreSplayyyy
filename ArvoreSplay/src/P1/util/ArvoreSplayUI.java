package P1.util;

import P2.Implementação.EstruturaDaÁrvore.ArvoreSplay;
import P2.Implementação.EstruturaDaÁrvore.No;

import java.util.Scanner;

public class ArvoreSplayUI {
    private ArvoreSplay arvore;
    private Scanner scanner;

    public ArvoreSplayUI() {
        arvore = new ArvoreSplay();
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();  // Consome a linha para evitar problemas de leitura
            switch (opcao) {
                case 1:
                    inserirElemento();
                    break;
                case 2:
                    buscarElemento();
                    break;
                case 3:
                    removerElemento();
                    break;
                case 4:
                    imprimirEstado();
                    break;
                case 5:
                    exibirCatalogo();
                    break;
                case 6:
                    exibirCatalogoPorAcessos();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void exibirMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Inserir elemento");
        System.out.println("2. Buscar elemento");
        System.out.println("3. Remover elemento");
        System.out.println("4. Imprimir estado atual");
        System.out.println("5. Exibir catálogo (em ordem)");
        System.out.println("6. Exibir catálogo por acessos");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void inserirElemento() {
        System.out.print("Digite o valor do elemento a ser inserido: ");
        int chave = scanner.nextInt();
        arvore.Inserir(chave);
        System.out.println("Elemento inserido.");
    }

    private void buscarElemento() {
        System.out.print("Digite o valor do elemento a ser buscado: ");
        int chave = scanner.nextInt();
        No resultado = arvore.Buscar(chave);
        if (resultado != null) {
            System.out.println("Elemento encontrado: " + resultado.getChave());
        } else {
            System.out.println("Elemento não encontrado.");
        }
    }

    private void removerElemento() {
        System.out.print("Digite o valor do elemento a ser removido: ");
        int chave = scanner.nextInt();
        boolean removido = arvore.Deletar(chave);
        if (removido) {
            System.out.println("Elemento removido.");
        } else {
            System.out.println("Elemento não encontrado.");
        }
    }

    private void imprimirEstado() {
        arvore.ImprimirRaiz();
    }

    private void exibirCatalogo() {
        arvore.ExibirCatalogoNaOrdem();
    }

    private void exibirCatalogoPorAcessos() {
        arvore.ExibirCatalogoPorAcessos();
    }
}



