package P2.Implementação.EstruturaDaÁrvore;

public class No {
    int chave;
    No esquerda, direita;
    int contadorDeAcessos;

    public No(int key) {
        this.chave = key;
        this.esquerda = this.direita = null;
        this.contadorDeAcessos = 0;
    }

    public int getChave() {
        return chave;
    }

    public int getContadorDeAcessos() {
        return contadorDeAcessos;
    }

    public void incrementarContadorDeAcessos() {
        this.contadorDeAcessos++;
    }


    public void setChave(int chave) {
        this.chave = chave;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public No getDireita() {
        return direita;
    }

    public void setDireita(No direita) {
        this.direita = direita;
    }



    // Definição da classe para o nó da árvore Splay
}
