import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main {
    static final String[] OPERACOES = {"+", "-", "*", "/", "**"};
    static final Integer ESPACAMENTOPADRAO = 100;

private static Integer tentaConverterStringParaNumeroBase(Scanner scanner, Integer base, String texto) {
        Integer numeroConvertido = null;
        String digito = null;
        if (texto == null  || texto.isEmpty()) texto = "";
        while (numeroConvertido == null ){
            try {
                System.out.printf("Digite o valor %s: ", texto);
                digito = scanner.nextLine().trim();
                numeroConvertido = Integer.parseInt(digito, base);
            } catch (NumberFormatException nfe){
                System.out.printf("O conteúdo %s extrapola o limite de 32 bits ou houve digitos inválidos, tente novamente! \n", digito);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return numeroConvertido;
    }

    public static Integer tentaConverterStringParaBinario(Scanner scanner) {
        return tentaConverterStringParaNumeroBase(scanner, 2, "binário");
    }

    @Deprecated
    public static Integer tentaConverterStringParaDecimal(Scanner scanner) {
        return tentaConverterStringParaNumeroBase(scanner, 10, "decimal");
    }

    public static void imrpimirOperacao(final Integer valor1, final Integer valor2, final String operacao, final Integer resultado) throws InterruptedException {
        final String VALOR1BINARIO = Integer.toBinaryString(valor1);
        final String VALOR2BINARIO = Integer.toBinaryString(valor2);
        final Integer TAMANHOOPERACAO = operacao.length() + 1;
        final String RESULTADOBINARIO = Integer.toBinaryString(resultado);
        final int MAIORCARACTERE = Integer.max(VALOR1BINARIO.length(), Integer.max(VALOR2BINARIO.length(),
                RESULTADOBINARIO.length()));

        preencherEspacoComCaractere(" ", (MAIORCARACTERE - VALOR1BINARIO.length() + TAMANHOOPERACAO), Boolean.FALSE);
        System.out.println(VALOR1BINARIO);

        System.out.print(operacao + " ");
        preencherEspacoComCaractere(" ", (MAIORCARACTERE - VALOR2BINARIO.length()), Boolean.FALSE);
        System.out.println(VALOR2BINARIO);

        preencherEspacoComCaractere("-", (MAIORCARACTERE + TAMANHOOPERACAO), Boolean.TRUE);
        preencherEspacoComCaractere(" ", (MAIORCARACTERE - RESULTADOBINARIO.length() + TAMANHOOPERACAO), Boolean.FALSE);
        System.out.println(RESULTADOBINARIO);
        sleep(2000);
    }

    public static void preencherEspacoComCaractere(final String simbolo, final Integer quantidade, final Boolean pularEspaco){
        for (int i = 0; i < quantidade; i++) System.out.print(simbolo);
        if (pularEspaco) System.out.println();
    }

    public static String escolhaOperacao(Scanner scanner) {
        String operacao;
        boolean testeQuebra;
        do {
            testeQuebra = Boolean.FALSE;
            preencherEspacoComCaractere("-", ESPACAMENTOPADRAO, true);
            System.out.print("Operações disponíveis: ");
            for (int i = 0; i < OPERACOES.length - 1; i++) System.out.printf("%s, ", OPERACOES[i]);
            System.out.printf("%s \n", OPERACOES[OPERACOES.length - 1]);
            preencherEspacoComCaractere("-", ESPACAMENTOPADRAO, true);
            System.out.print("Escolha a sua operação pelo simbolo representante. (Digite 0 para sair) ");
            operacao = scanner.nextLine().trim();
            if (!operacao.equals("0") && verificaDentroArray(operacao, OPERACOES) == -1) {
                testeQuebra = Boolean.TRUE;
                System.out.println("Operação inválida! Tente novamente. ");
            }
        } while (testeQuebra);
        return operacao;
    }

    public static Integer verificaDentroArray(String string, String[] array) {
        int posicao = -1;
        for (int i = 0; i < array.length; i++) {
            if (string.equals(array[i])) posicao = i;
        }
        return posicao;
    }

    public static void boasVindas() {
        preencherEspacoComTexto(" Bem vindo a Binaladora! ", "-", ESPACAMENTOPADRAO, Boolean.TRUE);
        System.out.println("Digite os valores em binário e escolha a operação desejada.");
        System.out.println("A calculadora vai continuar rolando até que você desejar parar no campo de operação.");
        preencherEspacoComCaractere("-", ESPACAMENTOPADRAO, true);
    }

    public static void despedida() {
        System.out.println("Agradecemos por testar nosso produto e esperamos que tenha gostado. Tenha um bom dia ^_^");
        preencherEspacoComTexto(" Obrigado por usar a Binaladora! ", "-", ESPACAMENTOPADRAO, Boolean.TRUE);
    }

    public static void limparTela() {
        for (int i = 0; i < 50; i++) System.out.println();
    }

    public static void preencherEspacoComTexto(final String texto, final String simbolo, final Integer quantidade, final Boolean pularEspaco){
        Integer quantidadeInicial = (quantidade - texto.length()) / 2 + 1;
        Integer quantidadeFinal = (quantidade - texto.length()) / 2;
        for (int i = 0; i < quantidadeInicial; i++) System.out.print(simbolo);
        System.out.print(texto);
        for (int i = 0; i < quantidadeFinal; i++) System.out.print(simbolo);
        if (pularEspaco) System.out.println();
    }

    public static void binaladora() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> listaNumeros = new ArrayList<>();
        Integer resultado = 0;

        try {
            listaNumeros.add(tentaConverterStringParaBinario(scanner));
            resultado = listaNumeros.get(0);

            while (true) {
                String operacao = escolhaOperacao(scanner);
                if (operacao.equals("0")) break;

                preencherEspacoComCaractere("-", ESPACAMENTOPADRAO, true);
                listaNumeros.add(tentaConverterStringParaBinario(scanner));
                preencherEspacoComCaractere("-", ESPACAMENTOPADRAO, true);

                switch (operacao) {
                    case "+" -> resultado += listaNumeros.get(listaNumeros.size() - 1);
                    case "-" -> resultado -= listaNumeros.get(listaNumeros.size() - 1);
                    case "*" -> resultado *= listaNumeros.get(listaNumeros.size() - 1);
                    case "/" -> resultado /= listaNumeros.get(listaNumeros.size() - 1);
                    case "**" -> resultado = (int) Math.pow(resultado, listaNumeros.get(listaNumeros.size() - 1));
                    default -> System.out.println("Operação não identidicada!");
                }
                imrpimirOperacao(listaNumeros.get(listaNumeros.size() -2), listaNumeros.get(listaNumeros.size() -1),
                        operacao, resultado);
                listaNumeros.add(resultado);

            }
        } catch (NumberFormatException nfe)  {
            System.out.println("O número extrapola o limite de 32 bits, tente novamente!");
        } catch (ArithmeticException ae) {
            System.out.println("Impossível dividir por 0!");
        } catch (InterruptedException ie) {
            System.out.println("Problemas com Interrupções!");
        }
        preencherEspacoComCaractere("-", ESPACAMENTOPADRAO, true);
        System.out.println("Resultado final: " + Integer.toBinaryString(resultado));
        System.out.println("Quantidade de operações: " + (listaNumeros.size() - 1));
        preencherEspacoComCaractere("-", ESPACAMENTOPADRAO, true);
    }

    public static void main(String[] args) {
        boasVindas();
        binaladora();
        despedida();
    }
}