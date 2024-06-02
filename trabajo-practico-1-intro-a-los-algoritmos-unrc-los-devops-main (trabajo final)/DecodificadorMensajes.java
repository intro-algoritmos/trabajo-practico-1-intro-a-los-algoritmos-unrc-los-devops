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
    private Mensaje mensajeADecodificar;
    
    /**
     * Código a utilizar
     */
    private int[] codigoEncripcion;
    
    /**
     * Mensaje decodificado
     */
    private Mensaje mensajeDecodificado;

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
        //Precondición para chequear si el mensaje fue decodificado
        if(mensajeDecodificado != null)
        throw new IllegalStateException("El mensaje ya fue decodificado");
        //Crea y le asigna un objeto al campo mensajeDecodificado.
        //Obtiene cada linea encriptada y la decodifica guardandola en el objeto mensajeDecodificado
        mensajeDecodificado = new Mensaje();
        for(int x = 0; x < mensajeADecodificar.cantLineas(); x++){
            String cadena = mensajeADecodificar.obtenerLinea(x);
            String cadenaNueva = desencriptarCadena(cadena, codigoEncripcion);
            mensajeDecodificado.agregarLinea(cadenaNueva);
        }
        //postcondición para chequear que mensajeDecodificado fue modificado
        assert (mensajeDecodificado != null):"El mensaje no se decodificó";
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
    // index para recorrer la posicion en el arreglo codigo
    int index = 0;
    //variable para guardar la cadena desencriptada
    String mensajeDesencriptado = "";
    for(int i = 0; i < str.length(); i++){
        //variable para guardar el codigo ascii del caracter en la posicion i
        char charAsciiEncriptado = str.charAt(i);
        //variable utilizada para condicional if
        int esMenor = (int) ((charAsciiEncriptado - codigo[index]) % 128);
        char charAsciiDesencriptado;
        //condicional para no salirse del rango 0--127 en ascii
        if(esMenor == 0){
            charAsciiDesencriptado = 127;
        }else{
            charAsciiDesencriptado = (char) ((charAsciiEncriptado - codigo[index]) % 128);
        }
        /** Print utilizado en la realizacion del código para comprobar si desencriptaba correctamente */
        //System.out.println(charAsciiDesencriptado);
        
        // variable para guardar el tamaño del arreglo
        int tamañoArreglo = codigo.length;
        //concatenacion de caracteres desencriptados a la cadena mensajeDesencriptado
        mensajeDesencriptado += charAsciiDesencriptado;
        //variable utilizada para no salirnos del rango maximo del arreglo y desencripte con el codigo necesario en cada posición
        index = (index + 1) % tamañoArreglo;
        //postcondición para corroborar que index no se sale de rango
        if(index < 0 && index >= tamañoArreglo)
        throw new IllegalStateException("El index se fue de rango");
    }
    /** Print utilizado al final del bucle para comprobar por pantalla si la linea fue desencriptada correctamente*/
    //System.out.println(mensajeDesencriptado);
    assert(mensajeDesencriptado != null):"La cadena no fue modificada correctamente";
    return mensajeDesencriptado;
    }
}

