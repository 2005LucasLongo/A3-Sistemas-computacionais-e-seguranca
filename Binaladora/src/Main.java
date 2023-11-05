import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main {
    static final String[] OPERACOES = {"Somar -> +", "Subtrair -> -", "Multiplicar -> *", "Dividir -> /"};
    static final Integer ESPACAMENTOPADRAO = 60;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> listaNumeros = new ArrayList<>();
        Integer resultado = 0;

        try {
            listaNumeros.add(tentaConverterStringParaBinario(scanner));
            resultado = listaNumeros.get(0);

            while (true) {
                Integer operacao = escolhaOperacao(scanner);
                if (operacao == 0) break;

                preencherEspacoComCaractere("-", ESPACAMENTOPADRAO, true);
                listaNumeros.add(tentaConverterStringParaBinario(scanner));
                preencherEspacoComCaractere("-", ESPACAMENTOPADRAO, true);

                switch (operacao) {
                    case 1: resultado += listaNumeros.get(listaNumeros.size() - 1); break;
                    case 2: resultado -= listaNumeros.get(listaNumeros.size() - 1); break;
                    case 3: resultado *= listaNumeros.get(listaNumeros.size() - 1); break;
                    case 4: resultado /= listaNumeros.get(listaNumeros.size() - 1); break;
                }

                imrpimirOperacao(listaNumeros.get(listaNumeros.size() -2), listaNumeros.get(listaNumeros.size() -1),
                        OPERACOES[operacao - 1], resultado);
                listaNumeros.add(resultado);
            }
        } catch (NumberFormatException nfe) {
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
        String digito = "";
        try {
            System.out.print("Digite o valor binário: ");
            digito = scanner.next();
            return Integer.parseInt(digito, 2);
        } catch (NumberFormatException nfe){
            System.out.printf("O número %s extrapola o limite de 32 bits ou houveram digitos inválidos, tente novamente!", digito);
        } catch (Exception e){
            System.out.println("Digito inválido, tente novamente!");
            System.out.println(e);
        }
        return tentaConverterStringParaBinario(scanner);
    }

    public static Integer tentaConverterStringParaDecimal(Scanner scanner) {
        String digito = "";
        try {
            System.out.print("Digite o valor: ");
            digito = scanner.next();
            return Integer.parseInt(digito);
        } catch (NumberFormatException nfe){
            System.out.printf("O número %s extrapola o limite de 32 bits ou houveram digitos inválidos, tente novamente!\n", digito);
        } catch (Exception e){
            System.out.println("Digito inválido, tente novamente!");
        }
        return tentaConverterStringParaDecimal(scanner);
    }

    public static void imrpimirOperacao(final Integer valor1, final Integer valor2, final String operacao, final Integer resultado) throws InterruptedException {
        final String VALOR1BINARIO = Integer.toBinaryString(valor1);
        final String VALOR2BINARIO = Integer.toBinaryString(valor2);
        final String RESULTADOBINARIO = Integer.toBinaryString(resultado);
        final Integer MAIORCARACTERE = Integer.max(VALOR1BINARIO.length(), Integer.max(VALOR2BINARIO.length(),
                RESULTADOBINARIO.length()));

        preencherEspacoComCaractere(" ", (MAIORCARACTERE - VALOR1BINARIO.length() + 2), Boolean.FALSE);
        System.out.println(VALOR1BINARIO);

        System.out.print(operacao.charAt(operacao.length() - 1) + " ");
        preencherEspacoComCaractere(" ", (MAIORCARACTERE - VALOR2BINARIO.length()), Boolean.FALSE);
        System.out.println(VALOR2BINARIO);

        preencherEspacoComCaractere("-", (MAIORCARACTERE + 2), Boolean.TRUE);

        preencherEspacoComCaractere(" ", (MAIORCARACTERE - RESULTADOBINARIO.length() + 2), Boolean.FALSE);
        System.out.println(RESULTADOBINARIO);

        sleep(2000);

    }

    public static void preencherEspacoComCaractere(final String simbolo, final Integer quantidade, final Boolean pularEspaco){
        for (int i = 0; i < quantidade; i++) {
            System.out.print(simbolo);
        }
        if(pularEspaco){
            System.out.println();
        }
    }

     public static Integer escolhaOperacao(Scanner scanner) {
        Integer operacao = -1;
         do {
             preencherEspacoComCaractere("-", ESPACAMENTOPADRAO, true);
             for (int i = 0; i < OPERACOES.length; i++) {
                 System.out.printf("%d -> %s \n", i + 1, OPERACOES[i]);
             }
             preencherEspacoComCaractere("-", ESPACAMENTOPADRAO, true);
             System.out.print("Escolha a sua operação pelo número que aparece. (0 para sair) ");
             operacao = tentaConverterStringParaDecimal(scanner);

         } while (operacao < 0 || operacao > OPERACOES.length);
         return operacao;
     }
}