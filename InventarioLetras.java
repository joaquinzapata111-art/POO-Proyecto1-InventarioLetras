package pooproyecto;

 public class InventarioLetras {
    
    private int[]contadores; 
    private int totalCount; 
    private int nonZeroCount; 

    // el constructor pide recibir un string y contar las letras
    public InventarioLetras(String data) {
        contadores = new int[26]; 
        totalCount = 0;
        nonZeroCount = 0;
        
        data = data.toLowerCase(); 
        
        // recorro el texto letra por letra
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            // me salto los espacios y numeros para guardar solo letras
            if (c >= 'a' && c <= 'z') { 
                int pos = c - 'a'; 
                if (contadores[pos] == 0) {
                    nonZeroCount++; 
                }
                contadores[pos]++;
                totalCount++;
            }
        }
    }

    // me invente este constructor vacio porque me sirve mucho para el add y subtract
    private InventarioLetras() {
        contadores = new int[26];
        totalCount = 0;
        nonZeroCount = 0;
    }

    public int get(char letra) {
        letra = Character.toLowerCase(letra);
        if (letra < 'a' || letra > 'z') {
            throw new IllegalArgumentException("Error"); 
        }
        int pos = letra - 'a';
        return contadores[pos]; 
    }

    // retorno rapido usando el atributo que pide el pdf
    public int size() {
        return totalCount; 
    }

    public char encriptarCesar(char letra) {
        // muevo la letra 3 espacios. uso % 26 para dar la vuelta si me salgo del abecedario
        if (letra >= 'a' && letra <= 'z') {
            int nuevaPos = letra - 'a' + 3;
            return (char) ('a' + (nuevaPos % 26));
        } 
        if (letra >= 'A' && letra <= 'Z') {
            int nuevaPos = letra - 'A' + 3;
            return (char) ('A' + (nuevaPos % 26)); 
        }
        return letra; 
    }

    public InventarioLetras add(InventarioLetras otro) {
        InventarioLetras nuevo = new InventarioLetras(); 
        // sumo mi arreglo con el otro
        for (int i = 0; i < 26; i++) {
            char letra = (char) ('a' + i);
            int suma = this.contadores[i] + otro.contadores[i];
            nuevo.set(letra, suma); 
        }
        return nuevo;
    }

    public String encriptarPalabra(String palabra, int desplazamiento) {
        String texto = "";
        for (int i = 0; i < palabra.length(); i++) {
            char c = palabra.charAt(i);
            texto = texto + encriptarCesar(c); 
        }
        return texto;
    }

    public void set(char letra, int valor) {
        letra = Character.toLowerCase(letra);
        if (letra < 'a' || letra > 'z' || valor < 0) {
            throw new IllegalArgumentException("Error"); 
        }
        
        int pos = letra - 'a';
        int viejo = contadores[pos]; 
        
        contadores[pos] = valor;
        
        // actualizo el total restando el valor viejo que tenia antes
        totalCount = totalCount - viejo + valor; 
        
        // arreglo el contador de letras distintas si meti una nueva o borre una que existia
        if (viejo == 0 && valor > 0) {
            nonZeroCount++; 
        } 
        if (viejo > 0 && valor == 0) {
            nonZeroCount--; 
        }
    }

    public boolean isEmpty() {
        // si el contador es 0 es porque no tengo letras
        if (nonZeroCount == 0) {
            return true;
        } else {
            return false;
        }
    }

    public char desencriptarCesar(char letra) {
        // voy para atras. le sumo 26 antes para que java no me tire numeros negativos
        if (letra >= 'a' && letra <= 'z') {
            int nuevaPos = letra - 'a' - 3 + 26;
            return (char) ('a' + (nuevaPos % 26));
        } 
        if (letra >= 'A' && letra <= 'Z') {
            int nuevaPos = letra - 'A' - 3 + 26;
            return (char) ('A' + (nuevaPos % 26)); 
        }
        return letra;
    }

    public InventarioLetras subtract(InventarioLetras otro) {
        InventarioLetras nuevo = new InventarioLetras();
        for (int i = 0; i < 26; i++) {
            char letra = (char) ('a' + i);
            int resta = this.contadores[i] - otro.contadores[i]; 
            // el pdf dice que si algun resultado es negativo, retorno null [cite: 49]
            if (resta < 0) {
                return null;
            }
            nuevo.set(letra, resta);
        }
        return nuevo;
    }

    public String toString() {
        String texto = "[";
        // armo el texto igual al ejemplo [aaaablm] [cite: 45]
        for (int i = 0; i < 26; i++) {
            int cantidad = contadores[i];
            for (int j = 0; j < cantidad; j++) {
                texto = texto + (char) ('a' + i); 
            }
        }
        texto = texto + "]";
        return texto; 
    }

    public String desencriptarPalabra(String palabra, int desplazamiento) {
        String texto = "";
        for (int i = 0; i < palabra.length(); i++) {
            char c = palabra.charAt(i);
            texto = texto + desencriptarCesar(c); 
        }
        return texto;
    }

    public InventarioLetras amplifies(int n) {
        InventarioLetras nuevo = new InventarioLetras();
        // multiplico mis recuentos por n
        for (int i = 0; i < 26; i++) {
            char letra = (char) ('a' + i);
            int multiplicacion = this.contadores[i] * n; 
            nuevo.set(letra, multiplicacion);
        }
        return nuevo;
    }
}