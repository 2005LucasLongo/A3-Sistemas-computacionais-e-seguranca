import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main {
    static final String[] OPERACOES = {"+", "-", "*", "/", "**"};
    static final Integer ESPACAMENTOPADRAO = 60;

    public static void main(String[] args) {
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
                    case default -> System.out.println("Operação não identidicada!");
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

    public static Integer tentaConverterStringParaBinario(Scanner scanner) {
        String digito = null;
        try {
            System.out.print("Digite o valor binário: ");
            digito = scanner.nextLine();
            return Integer.parseInt(digito, 2);
        } catch (NumberFormatException nfe){
            System.out.printf("O número %s extrapola o limite de 32 bits ou houveram digitos inválidos, tente novamente! \n", digito);
        } catch (Exception e){
            System.out.println("Digito inválido, tente novamente!");
            System.out.println(e.getMessage());
        }
        return tentaConverterStringParaBinario(scanner);
    }

    @Deprecated
    public static Integer tentaConverterStringParaDecimal(Scanner scanner) {
        String digito = null;
        try {
            System.out.println("Digite o valor: ");
            digito = scanner.nextLine().trim();
            if (digito.isEmpty()) System.out.println("O conteúdo está vazio!"); else return Integer.parseInt(digito);
        } catch (NumberFormatException nfe){
            System.out.printf("O contúdo %s extrapola o limite de 32 bits ou houveram digitos inválidos, tente novamente!\n", digito);
        } catch (Exception e){
            System.out.println("Digito inválido, tente novamente!");
        }
        return tentaConverterStringParaDecimal(scanner);
    }

    public static void imrpimirOperacao(final Integer valor1, final Integer valor2, final String operacao, final Integer resultado) throws InterruptedException {
        final String VALOR1BINARIO = Integer.toBinaryString(valor1);
        final String VALOR2BINARIO = Integer.toBinaryString(valor2);
        final String RESULTADOBINARIO = Integer.toBinaryString(resultado);
        final int MAIORCARACTERE = Integer.max(VALOR1BINARIO.length(), Integer.max(VALOR2BINARIO.length(),
                RESULTADOBINARIO.length()));

        preencherEspacoComCaractere(" ", (MAIORCARACTERE - VALOR1BINARIO.length() + 2), Boolean.FALSE);
        System.out.println(VALOR1BINARIO);

        System.out.print(operacao + " ");
        preencherEspacoComCaractere(" ", (MAIORCARACTERE - VALOR2BINARIO.length()), Boolean.FALSE);
        System.out.println(VALOR2BINARIO);

        preencherEspacoComCaractere("-", (MAIORCARACTERE + 2), Boolean.TRUE);
        preencherEspacoComCaractere(" ", (MAIORCARACTERE - RESULTADOBINARIO.length() + 2), Boolean.FALSE);
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
}