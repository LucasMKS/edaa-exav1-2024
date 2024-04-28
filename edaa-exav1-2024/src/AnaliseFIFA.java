import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class AnaliseFIFA {
    public static void main(String[] args) {
        InputStream inputStream = AnaliseFIFA.class.getResourceAsStream("/fifa_countries_audience.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            List<String[]> records = new ArrayList<>();
            String linha;
            while ((linha = reader.readLine()) != null) {
                records.add(linha.split(","));
            }

            int opcao;
            do {
                System.out.println("---------------");
                System.out.println("Digite a opção desejada:");
                System.out.println("[1] Ordenar por Ordem Alfabética");
                System.out.println("[2] Ordenar por audiência TV");
                System.out.println("[3] Encerrar");
                System.out.println("--------------");

                opcao = lerOpcao();

                switch (opcao) {
                    case 1:
                        ordenarPorOrdemAlfabetica(records);
                        break;
                    case 2:
                        ordenarPorAudienciaTV(records);
                        break;
                    case 3:
                        System.out.println("Encerrando o programa...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } while (opcao != 3);
            
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int lerOpcao() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static void ordenarPorOrdemAlfabetica(List<String[]> records) {
        records.sort(Comparator.comparing(record -> record[0]));
        exibirResultados(records);
    }

    private static void ordenarPorAudienciaTV(List<String[]> records) {
        records.sort(Comparator.comparingDouble(record -> {
            try {
                return Double.parseDouble(record[3]);
            } catch (NumberFormatException e) {
                return 0.0;
            }
        }));
        exibirResultados(records);
    }

    private static void exibirResultados(List<String[]> records) {
        for (String[] record : records) {
            System.out.println(record[0] + ": " + record[1] + " | Parcela da População: " + record[2] + " | TV Audiência: " + record[3] + " |Participação ponderada do PIB: " + record[4]);
        }
    }
}
