package pooproyecto;

public class PruebaInventario {
    public static void main(String[] args) {
        InventarioLetras inv = new InventarioLetras("Hola Mundo");
        
        System.out.println("Total de letras guardadas: " + inv.size()); 
        System.out.println("¿El inventario está vacío?: " + inv.isEmpty()); 
        System.out.println("Veces que aparece la letra 'o': " + inv.get('o')); 
        System.out.println("El inventario ordenado se ve así: " + inv); 
        
        System.out.println("Si encriptamos la 'a' nos da: " + inv.encriptarCesar('a')); 
        System.out.println("La palabra 'play' encriptada queda: " + inv.encriptarPalabra("play", 3)); 
    }
}
