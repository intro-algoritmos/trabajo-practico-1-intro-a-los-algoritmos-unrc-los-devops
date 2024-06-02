import java.util.*;

/**
 * Clase DecodificadorMensajes: representa una componente capaz de descifrar
 * un mensaje en formato texto, dado el mensaje y el código usado para la 
 * encripción. El mensaje a descifrar/decodificar debe ser un objeto
 * de tipo Mensaje (básicamente, una lista de strings, donde cada string
 * representa una línea). Se asume que el mensaje es ASCII, es decir, todos
 * los caracteres del mensaje tienen códigos en el rango [0, 127].
 * 
 * La codificación/decodificación utiliza una variante de Cifrado Cesar, en 
 * el cual el desplazamiento se basa en una código de encripción múltiple. 
 * Véase Cifrado de Vigenère para más detalles.
 * 
 * @author N. Aguirre
 * @version 0.1
 */
public class DecodificadorMensajes
{
    /**
     * Mensaje codificado
     */
    private Mensaje mensajeADecodificar; //mensaje "lqle"
    
    /**
     * Código a utilizar
     */
    private int[] codigoEncripcion; //codigo arreglo {4, 2, 0}
    
    /**
     * Mensaje decodificado
     */
    private Mensaje mensajeDecodificado; // devuelve "hola"

    /**
     * Constructor de la clase DecodificadorMensajes.
     * Inicializa el mensaje a desencriptar/decodificar con el parámetro pasado, 
     * junto con el código de desencripción. 
     * Precondición: tanto el mensaje msg como el código codigo no pueden ser nulos
     * @param msg es el mensaje a desencriptar.
     * @param codigo es el código de desencripción.
     */
    public DecodificadorMensajes(Mensaje msg, int[] codigo)
    {
        if (msg == null)
            throw new IllegalArgumentException("Mensaje nulo");
        if (codigo == null)
            throw new IllegalArgumentException("Código inválido.");
        mensajeADecodificar = msg;
        codigoEncripcion = codigo;
        mensajeDecodificado = null;
    }

    /**
     * Desencripta el mensaje. El mensaje no debe estar desencriptado.
     * Precondición: El mensaje aún no fue descifrado (i.e., el campo 
     * mensajeDecodificado es null).
     */
    public void decodificarMensaje() 
    {
        //Precondición para chequear que el mensaje aun no fue decodificado
        /**     if(mensajeDecodificado != null)
           *        //mensaje ya decodificado
        *       throw new IllegalStateException("El mensaje aun no fue descifrado");
        */
        
        /**String cadena = mensajeADecodificar.toString();
        *String cadenaNueva = desencriptarCadena(cadena, codigoEncripcion);
        *mensajeDecodificado = new Mensaje();
        *mensajeDecodificado.agregarLinea(cadenaNueva);
        */
        mensajeDecodificado = new Mensaje();
        for(int x = 0; x < mensajeADecodificar.cantLineas(); x++){
            String cadena = mensajeADecodificar.obtenerLinea(x);
            String cadenaNueva = desencriptarCadena(cadena, codigoEncripcion);
            mensajeDecodificado.agregarLinea(cadenaNueva);
        }
        
    }
    
    /**
     * Retorna el mensaje ya decodificado/descifrado.
     * Precondición: el mensaje debe haber sido decodificado previamente (i.e., 
     * se debe haber llamado a decodificarMensaje()).
     * Postcondicion: se retorna el mensaje descifrado/decodificado.
     * @return el mensaje descifrado.
     */
    public Mensaje obtenerMensajeDecodificado() {
        if (mensajeDecodificado == null)
            throw new IllegalStateException("Mensaje aún no decodificado");
        return mensajeDecodificado;
    }
    
    /**
     * Desencripta una cadena, dado un código numérico. Se usan los dígitos del código
     * para reemplazar cada caracter de la cadena por el caracter correspondiente a 
     * "trasladar" el mismo el número de lugares que indica el código, en sentido inverso
     * al de encripción (es decir, se resta el código al caracter). El código tiene
     * múltiples valores: se usa el primero para el primer caracter, el segundo para el 
     * segundo, y así sucesivamente. Si se agota el código, se vuelve al comienzo del mismo, 
     * hasta desencriptar toda la cadena.
     * Precondición: tanto str como codigo no deben ser nulos.
     * @param str es la cadena a desencriptar
     * @param codigo es el código a utilizar para la desencripción
     */

    private String desencriptarCadena(String str, int[] codigo){
       
    // index para recorrer la posicion en el arreglo
    // i para recorrer la posicion en la cadena
    int index = 0;
    // arreglo para guardar el mensaje desencriptado
    //char[] nuevoMensaje = new char[str.length()];
    //variable para guardar cadenas
    String mensajeDesencriptado = "";
    for(int i = 0; i < str.length(); i++){
        // conseguir el ascii de cada caracter en la cadena
        //int numAscii = (int) str.charAt(i);
        
        //variable para guardar el caracter en la posicion i
        char charAsciiEncriptado = str.charAt(i);
        //
        int esMenor = (int) ((charAsciiEncriptado - codigo[index]) % 128);
        char charAsciiDesencriptado;
        if(esMenor == 0){
            charAsciiDesencriptado = 127;
            //charAsciiDesencriptado = (char) (((charAsciiEncriptado - codigo[index]) % 128) + 128);
        }else{
            charAsciiDesencriptado = (char) ((charAsciiEncriptado - codigo[index]) % 128);
        }
        System.out.println(charAsciiDesencriptado);
        // variable para guardar el tamaño del arreglo
        int tamañoArreglo = codigo.length;
        //concatenacion de caracteres
        mensajeDesencriptado += charAsciiDesencriptado;
        // condicional para movernos dentro de cada posicion del arreglo una y otra vez
        index = (index + 1) % tamañoArreglo;
        // condicional para manejarnos dentro del rango 0-127 en ascii
        /**if(index >= 0 && index < tamañoArreglo){
            
            // desencriptar el carácter restando el valor del código de encriptación
            
            // almacenar el carácter desencriptado en el nuevo mensaje
            nuevoMensaje[i] = (char) charAsciiDesencriptado;
            // incrementar el índice
            index++;
            if(index >= tamañoArreglo){
            index = 0;
            }
        }
        */
        
        // reiniciar el índice si se llega al final del arreglo de encriptación
        
    }
       
    // convertir el arreglo de caracteres a una cadena y devolver el mensaje desencriptado
    //String mensajeDecodificado1 = new String(nuevoMensaje);
    System.out.println(mensajeDesencriptado);//sacar""""
    System.out.println();
    return mensajeDesencriptado;
    
    }
    
    private String desencriptarCadenaV2(String str, int[] codigo){
        String result = "";
        int indiceCodigo = 0;
        for (int i = 0; i < str.length(); i++) {
            char curr = str.charAt(i);
            char currEncriptado = (char) ((curr - codigo[indiceCodigo]) % 128);
            result = result + currEncriptado;
            indiceCodigo = (indiceCodigo + 1) % (codigo.length);
        }
        return result;
    }
    
    private String desencriptarCadenaV3(String str, int[] codigo){
        // Precondición para comprobar que la cadena a encriptar no sea nula
        if (str == null) {
            throw new IllegalArgumentException("Cadena nula");
        }
        // Precondición para asegurar que los caracteres del @param str pertenecen a 0<=ASCII<=127
        for (char i : str.toCharArray()) {
            if (i < 0 || i > 127) {
                throw new IllegalArgumentException("Existen caracteres no pertenecientes a ASCII");
            }
        }
        //Inicializacion del método
        //Vuelve @param str en lista, recorriendolo y sumando sus valores ASCII
        int sumaAscii = 0;
        for (char i : str.toCharArray()) {
            sumaAscii -= (int) i;
        }
        //Crea un código de encripción en formato de arreglo
        int resto = sumaAscii % 99991;
        String restoStr = String.valueOf(resto);
        // Postcondición comprueba que el largo del arreglo que nos devuelve, sea igual al
        // largo de la cadena que le ingresamos como parámetro real
        /**if (codigo.length != restoStr.length()) {
        *    throw new IllegalStateException("Generación del código de encripción fallida");
        */
        String mensajeDesencriptado = "";
        return mensajeDesencriptado;
        
    }

}

