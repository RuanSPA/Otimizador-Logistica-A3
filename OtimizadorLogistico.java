import java.util.*;

public class OtimizadorLogistico {

    // Método para calcular número mínimo de armazéns ou caminhões
    public static int calcularBins(int[] volumes, int capacidadeMaxima) {
        Arrays.sort(volumes);
        List<Integer> bins = new ArrayList<>();

        for (int i = volumes.length - 1; i >= 0; i--) {
            boolean alocado = false;
            for (int j = 0; j < bins.size(); j++) {
                if (bins.get(j) + volumes[i] <= capacidadeMaxima) {
                    bins.set(j, bins.get(j) + volumes[i]);
                    alocado = true;
                    break;
                }
            }
            if (!alocado) {
                bins.add(volumes[i]);
            }
        }

        return bins.size();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Entrada
        System.out.println("Informe os volumes dos containers (separados por espaço):");
        String[] entrada = sc.nextLine().split(" ");
        int[] volumes = new int[entrada.length];
        for (int i = 0; i < entrada.length; i++) {
            volumes[i] = Integer.parseInt(entrada[i]);
        }

        System.out.print("Informe a capacidade de um armazém: ");
        int capacidadeArmazem = sc.nextInt();

        System.out.print("Informe a capacidade de um caminhão: ");
        int capacidadeCaminhao = sc.nextInt();

        // Cálculos
        int armazens = calcularBins(volumes, capacidadeArmazem);

        // Como caminhões transportam a carga total armazenada, somamos tudo e aplicamos novamente
        int totalCarga = Arrays.stream(volumes).sum();
        int caminhoes = (int) Math.ceil((double) totalCarga / capacidadeCaminhao);

        // Resultados
        System.out.println("Número mínimo de armazéns necessários: " + armazens);
        System.out.println("Número mínimo de caminhões necessários: " + caminhoes);
    }
}
