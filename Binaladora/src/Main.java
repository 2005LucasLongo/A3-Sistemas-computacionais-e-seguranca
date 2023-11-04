import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static final String[] OPERACOES = {"Somar -> +", "Subtrair -> -", "Multiplicar -> *", "Dividir -> /"};
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> listaNumeros = new ArrayList<>();
        Integer resultado = 0;

        try {
            listaNumeros.add(tentaConverterStringParaBinario(scanner));
            resultado = listaNumeros.get(0);

            while (true) {
                Integer operacao = -1;
                do {
                    preencherEspacoComCaractere("-", 30, true);
                    for (int i = 0; i < OPERACOES.length; i++) {
                        System.out.printf("%d -> %s \n", i + 1, OPERACOES[i]);
                    }
                    preencherEspacoComCaractere("-", 30, true);
                    System.out.print("Escolha a sua operação. (0 para sair) ");
                    operacao = tentaConverterStringParaDecimal(scanner);

                } while (operacao < 0 || operacao > OPERACOES.length);

                if (operacao == 0) {
                    break;
                }

                preencherEspacoComCaractere("-", 30, true);

                listaNumeros.add(tentaConverterStringParaBinario(scanner));
                preencherEspacoComCaractere("-", 30, true);

                switch (operacao) {
                    case 1:
                        resultado += listaNumeros.get(listaNumeros.size() - 1);
                        break;
                    case 2:
                        resultado -= listaNumeros.get(listaNumeros.size() - 1);
                        break;
                    case 3:
                        resultado *= listaNumeros.get(listaNumeros.size() - 1);
                        break;
                    case 4:
                        resultado /= listaNumeros.get(listaNumeros.size() - 1);
                        break;
                }
                imrpimirOperacao(listaNumeros.get(listaNumeros.size() -2), listaNumeros.get(listaNumeros.size() -1),
                        OPERACOES[operacao - 1], resultado);
                listaNumeros.add(resultado);
                System.out.println("Resultado binário: " + Integer.toBinaryString(resultado));
            }
        } catch (NumberFormatException nfe) {
            System.out.println("O número extrapola o limite de 32 bits, tente novamente!");
        } catch (ArithmeticException ae) {
            System.out.println("Impossível dividir por 0!");

        }
        preencherEspacoComCaractere("-", 30, true);
        System.out.println("Resultado final: " + Integer.toBinaryString(resultado));
        System.out.println("Quantidade de operações: " + (listaNumeros.size() - 1));
        preencherEspacoComCaractere("-", 30, true);
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
            System.out.printf("O número %s extrapola o limite de 32 bits ou houveram digitos inválidos, tente novamente!", digito);
        } catch (Exception e){
            System.out.println("Digito inválido, tente novamente!");
        }
        return tentaConverterStringParaDecimal(scanner);
    }

    public static void imrpimirOperacao(final Integer valor1, final Integer valor2, final String operacao, final Integer resultado){
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

    }

    public static void preencherEspacoComCaractere(String simbolo, Integer quantidade, Boolean pularEspaco){
        for (int i = 0; i < quantidade; i++) {
            System.out.print(simbolo);
        }
        if(pularEspaco){
            System.out.println();
        }
    }
}