package P1.util;
import P1.util.No;

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
    private No direitaRodar(No x) {
        No y = x.esquerda;
        x.esquerda = y.direita;
        y.direita = x;
        return y;
    }

    // Método auxiliar para rotação à esquerda
    private No esquerdaRodar(No x) {
        No y = x.direita;
        x.direita = y.esquerda;
        y.esquerda = x;
        return y;
    }

    // Método principal para realizar o splay de um nó com a chave k
    private No splay(No raiz, int chave) {
        // Caso base: se o nó é nulo ou a chave está na raiz
        if (raiz == null || raiz.chave == chave) {
            if (raiz != null) {
            }
            return raiz;
        }

        // Caso a chave esteja na subárvore esquerda
        if (raiz.chave > chave) {
            // A chave não está na árvore, retorna o nó mais à esquerda
            if (raiz.esquerda == null)
                return raiz;

            // Zig-Zig (rotação à direita duas vezes)
            if (raiz.esquerda.chave > chave) {
                raiz.esquerda.esquerda = splay(raiz.esquerda.esquerda, chave);
                raiz = direitaRodar(raiz);
            } else if (raiz.esquerda.chave < chave) { // Zig-Zag (primeiro rotação à esquerda, depois à direita)
                raiz.esquerda.direita = splay(raiz.esquerda.direita, chave);
                if (raiz.esquerda.direita != null)
                    raiz.esquerda = esquerdaRodar(raiz.esquerda);
            }

            // Rotação final à direita para trazer o nó para a raiz
            return (raiz.esquerda == null) ? raiz : direitaRodar(raiz);
        } else { // Caso a chave esteja na subárvore direita
            // A chave não está na árvore, retorna o nó mais à direita
            if (raiz.direita == null) {
                return raiz;
            }
            // Zig-Zag (primeiro rotação à direita, depois à esquerda)
            if (raiz.direita.chave > chave) {
                raiz.direita.esquerda = splay(raiz.direita.esquerda, chave);
                if (raiz.direita.esquerda != null)
                    raiz.direita = direitaRodar(raiz.direita);
            } else if (raiz.direita.chave < chave) { // Zig-Zig (rotação à esquerda duas vezes)
                raiz.direita.direita = splay(raiz.direita.direita, chave);
                raiz = esquerdaRodar(raiz);
            }

            // Rotação final à esquerda para trazer o nó para a raiz
            return (raiz.direita == null) ? raiz : esquerdaRodar(raiz);
        }
    }

    // Método público para inserir uma chave na árvore
    public void inserir(int chave) {
        raiz = inserirAux(raiz, chave);
    }

    // Método auxiliar para inserção recursiva
    private No inserirAux(No no, int chave) {
        // Caso base: se a árvore está vazia, cria um novo nó
        if (no == null)
            return new No(chave);

        // Realiza o splay para trazer o nó chave para a raiz
        no = splay(no, chave);

        // Insere um novo nó na raiz
        if (chave < no.chave) {
            No novoNo = new No(chave);
            novoNo.direita = no;
            novoNo.esquerda = no.esquerda;
            no.esquerda = null;
            return novoNo;
        } else if (chave > no.chave) {
            No newNode = new No(chave);
            newNode.esquerda = no;
            newNode.direita = no.direita;
            no.direita = null;
            return newNode;
        } else {
            // Se a chave já existe na árvore, apenas retorna o nó
            return no;
        }
    }

    // Método público para buscar uma chave na árvore
    public No buscar(int chave) {
        raiz = splay(raiz, chave);
        if (raiz != null && raiz.chave == chave) {
            raiz.incrementarContadorDeAcessos();  // Incrementa o contador de acessos aqui
            return raiz;
        }
        return null;
    }

        // Método público para remover uma chave da árvore
    public boolean remove(int chave) {
        if (raiz == null){
            System.out.println("a arvore nao existe, favor tentar novamente");
            return false;
        }else{
            raiz = splay(raiz, chave);

            if (raiz.chave != chave)
                return false;

            No subEsquerda = raiz.esquerda;
            No rightSubtree = raiz.direita;

            if (subEsquerda != null) {
                No max = acharMax(subEsquerda);
                raiz = splay(subEsquerda, max.chave);
                raiz.direita = rightSubtree;
            } else {
                raiz = rightSubtree;
            }
            return true;
        }
    }

    // Método auxiliar para encontrar o máximo na subárvore
    private No acharMax(No node) {
        while (node.direita != null)
            node = node.direita;
        return node;
    }
    public void imprimirEstado() {
        if (raiz != null) {
            System.out.println("Elemento mais recentemente acessado ou inserido: " + raiz.getChave());
        } else {
            System.out.println("A árvore está vazia.");
        }
    }

    // Método para percorrer a árvore em ordem e exibir os produtos
    public void inorder() {
        if (raiz == null) {
            System.out.println("Catálogo vazio. Nenhum produto para exibir.");
        } else {
            inorderHelper(raiz);
        }
    }

    private void inorderHelper(No no) {
        if (no != null) {
            inorderHelper(no.esquerda);
            System.out.println("Produto: " + no.chave);
            inorderHelper(no.direita);
        }
    }

    public void exibirCatalogo() {
        inorder(); // Chama o método inorder para exibir os produtos em ordem
    }

    public No getRaiz() {
        return raiz;
    }

    public void setRaiz(No raiz) {
        this.raiz = raiz;
    }


    public void exibirCatalogoPorAcessos() {
        if (raiz == null) {
            System.out.println("Catálogo vazio. Nenhum produto para exibir.");
            return;
        }

        List<No> nos = new ArrayList<>();
        obterNos(raiz, nos);

        Collections.sort(nos, new Comparator<P1.util.No>() {
            @Override
            public int compare(No o1, No o2) {
                return o2.getContadorDeAcessos() - o1.getContadorDeAcessos();
            }
        });

        System.out.println("Produtos ordenados por número de acessos:");
        for (No no : nos) {
            System.out.println("Produto: " + no.getChave() + ", Acessos: " + no.getContadorDeAcessos());
        }
    }

    private void obterNos(No no, List<No> nos) {
        if (no != null) {
            obterNos(no.esquerda, nos);
            nos.add(no);
            obterNos(no.direita, nos);
        }
    }


}
