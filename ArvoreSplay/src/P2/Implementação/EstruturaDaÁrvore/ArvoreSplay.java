package P2.Implementação.EstruturaDaÁrvore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ArvoreSplay {

    private No raiz;

    public ArvoreSplay() {
        this.raiz = null;
    }


    // Método auxiliar para rotação à direita
    private No RodarDireita(No x) {
        No y = x.esquerda;
        x.esquerda = y.direita;
        y.direita = x;
        return y;
    }

    // Método auxiliar para rotação à esquerda
    private No RodarEsquerda(No x) {
        No y = x.direita;
        x.direita = y.esquerda;
        y.esquerda = x;
        return y;
    }

    // Método principal para realizar o splay de um nó com a chave k
    private No OrganizadorSplay(No raiz, int chave) {
        // Caso base: se o nó é nulo ou a chave está na raiz
        if (raiz == null || raiz.chave == chave) {
            return raiz;
        }

        // Caso a chave esteja na subárvore esquerda
        if (raiz.chave > chave) {
            // A chave não está na árvore, retorna o nó mais à esquerda
            if (raiz.esquerda == null)
                return raiz;

            // Zig-Zig (rotação à direita duas vezes)
            if (raiz.esquerda.chave > chave) {
                raiz.esquerda.esquerda = OrganizadorSplay(raiz.esquerda.esquerda, chave);
                raiz = RodarDireita(raiz);
            } else if (raiz.esquerda.chave < chave) { // Zig-Zag (primeiro rotação à esquerda, depois à direita)
                raiz.esquerda.direita = OrganizadorSplay(raiz.esquerda.direita, chave);
                if (raiz.esquerda.direita != null)
                    raiz.esquerda = RodarEsquerda(raiz.esquerda);
            }

            // Rotação final à direita para trazer o nó para a raiz
            return (raiz.esquerda == null) ? raiz : RodarDireita(raiz);
        } else {
            // Caso a chave esteja na subárvore direita
            // A chave não está na árvore, retorna o nó mais à direita
            if (raiz.direita == null) {
                return raiz;
            }
            // Zig-Zag (primeiro rotação à direita, depois à esquerda)
            if (raiz.direita.chave > chave) {
                raiz.direita.esquerda = OrganizadorSplay(raiz.direita.esquerda, chave);
                if (raiz.direita.esquerda != null)
                    raiz.direita = RodarDireita(raiz.direita);
            } else if (raiz.direita.chave < chave) { // Zig-Zig (rotação à esquerda duas vezes)
                raiz.direita.direita = OrganizadorSplay(raiz.direita.direita, chave);
                raiz = RodarEsquerda(raiz);
            }

            // Rotação final à esquerda para trazer o nó para a raiz
            if (raiz.direita == null) {
                return raiz;
            } else {
                return RodarEsquerda(raiz);
            }
        }
    }

    // Método público para inserir uma chave na árvore
    public void Inserir(int chave) {
        raiz = InserirAux(raiz, chave);
    }

    // Método auxiliar para inserção recursiva
    private No InserirAux(No no, int chave) {
        // Caso base: se a árvore está vazia, cria um novo nó
        if (no == null)
            return new No(chave);

        // Realiza o splay para trazer o nó chave para a raiz
        no = OrganizadorSplay(no, chave);

        // Insere um novo nó na raiz
        No novoNo = new No(chave);
        if (chave < no.chave) {
            novoNo.direita = no;
            novoNo.esquerda = no.esquerda;
            no.esquerda = null;
            return novoNo;
        } else if (chave > no.chave) {
            novoNo.esquerda = no;
            novoNo.direita = no.direita;
            no.direita = null;
            return novoNo;
        } else {
            // Se a chave já existe na árvore, apenas retorna o nó
            return no;
        }
    }

    // Método público para buscar uma chave na árvore
    public No Buscar(int chave) {
        raiz = OrganizadorSplay(raiz, chave);
        if (raiz != null && raiz.chave == chave) {
            raiz.incrementarContadorDeAcessos();  // Incrementa o contador de acessos aqui
            return raiz;
        }
        return null;
    }

    // Método público para remover uma chave da árvore
    public boolean Deletar(int chave) {
        if (raiz == null){
            System.out.println("a árvore não existe, por favor tentar novamente");
            return false;
        }else{
            raiz = OrganizadorSplay(raiz, chave);

            if (raiz.chave != chave){
                return false;
            }

            No SubArvEsquerda = raiz.esquerda;
            No SubArvDireita = raiz.direita;

            if (SubArvEsquerda != null) {
                No max = getMax(SubArvEsquerda);
                raiz = OrganizadorSplay(SubArvEsquerda, max.chave);
                raiz.direita = SubArvDireita;
            } else {
                raiz = SubArvDireita;
            }
            return true;
        }
    }

    // Método auxiliar para encontrar o máximo na subárvore
    private No getMax(No node) {
        while (node.direita != null)
            node = node.direita;
        return node;
    }
    public void ImprimirRaiz() {
        if (raiz != null) {
            System.out.println("Elemento mais recentemente acessado ou inserido: " + raiz.getChave());
        } else {
            System.out.println("A árvore está vazia.");
        }
    }

    // Método para percorrer a árvore em ordem e exibir os produtos
    public void NaOrdem() {
        if (raiz == null) {
            System.out.println("Catálogo vazio. Nenhum produto para exibir.");
        } else {
            NaOrdemAUX(raiz);
        }
    }

    private void NaOrdemAUX(No no) {
        if (no != null) {
            NaOrdemAUX(no.esquerda);
            System.out.println("Produto: " + no.chave);
            NaOrdemAUX(no.direita);
        }
    }

    public void ExibirCatalogoNaOrdem() {
        NaOrdem(); // Chama o método inorder para exibir os produtos em ordem
    }

    public No getRaiz() {
        return raiz;
    }

    public void setRaiz(No raiz) {
        this.raiz = raiz;
    }


    public void ExibirCatalogoPorAcessos() {
        if (raiz == null) {
            System.out.println("Catálogo vazio. Nenhum produto para exibir.");
            return;
        }

        List<No> nos = new ArrayList<>();
        ObterNos(raiz, nos);

        Collections.sort(nos, new Comparator<No>() {
            public int compare(No o1, No o2) {
                return o2.getContadorDeAcessos() - o1.getContadorDeAcessos();
            }
        });

        System.out.println("Produtos ordenados por número de acessos:");
        for (No no : nos) {
            System.out.println("Produto: " + no.getChave() + ", Acessos: " + no.getContadorDeAcessos());
        }
    }

    private void ObterNos(No no, List<No> nos) {
        if (no != null) {
            ObterNos(no.esquerda, nos);
            nos.add(no);
            ObterNos(no.direita, nos);
        }
    }


}
