package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import vista.view;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author luisange, mariameza, romanmontgomery
 */
public class ctrl implements ActionListener{

    private view view;
    String texto;
    private String[] lineas;
    Queue<String> tabla_lexica=new LinkedList();
    Stack<String> pila = new Stack<String>(); 
    Stack<String> pila2 = new Stack<String>();
    Stack<String> pila3 = new Stack<String>();
    String tabla_sintactica[][] = new String[17][21];
    String primeros[][]= new String[22][2];
    String X = "";
    String K = "";
    String auxLinea = "";
    int lineaError = 0;
    String APUN = "";
    boolean f = true;
    private boolean comillas = false;
    public String constant = "";
    
    public ctrl (view view) {
        this.view = view;
    }
    
    public void init(){
        this.view.setVisible(true);
        this.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.view.escanear.addActionListener(this);
        tabla_sintactica[0][1] = "Q";
        tabla_sintactica[0][2] = "A";
        tabla_sintactica[0][3] = "B";
        tabla_sintactica[0][4] = "D";
        tabla_sintactica[0][5] = "C";
        tabla_sintactica[0][6] = "E";
        tabla_sintactica[0][7] = "F";
        tabla_sintactica[0][8] = "H";
        tabla_sintactica[0][9] = "G";
        tabla_sintactica[0][10] = "I";
        tabla_sintactica[0][11] = "J";
        tabla_sintactica[0][12] = "K";
        tabla_sintactica[0][13] = "V";
        tabla_sintactica[0][14] = "L";
        tabla_sintactica[0][15] = "M";
        tabla_sintactica[0][16] = "N";
        tabla_sintactica[0][17] = "O";
        tabla_sintactica[0][18] = "P";
        tabla_sintactica[0][19] = "R";
        tabla_sintactica[0][20] = "T";
        
        tabla_sintactica[1][0] = "i";
        tabla_sintactica[2][0] = "r";
        tabla_sintactica[3][0] = "s";
        tabla_sintactica[4][0] = "f";
        tabla_sintactica[5][0] = "w";
        tabla_sintactica[6][0] = "n";
        tabla_sintactica[7][0] = "y";
        tabla_sintactica[8][0] = "o";
        tabla_sintactica[9][0] = ",";
        tabla_sintactica[10][0] = ".";
        tabla_sintactica[11][0] = ")";
        tabla_sintactica[12][0] = "'";
        tabla_sintactica[13][0] = "d";
        tabla_sintactica[14][0] = "a";
        tabla_sintactica[15][0] = "*";
        tabla_sintactica[16][0] = "$";
        
        tabla_sintactica[1][2] = "B";
        tabla_sintactica[1][3] = "CD";
        tabla_sintactica[1][5] = "iE";
        tabla_sintactica[1][7] = "GH";
        tabla_sintactica[1][9] = "iI";
        tabla_sintactica[1][10] = "i";
        tabla_sintactica[1][12] = "LV";
        tabla_sintactica[1][14] = "CM";
        tabla_sintactica[1][17] = "C";
        
        tabla_sintactica[2][6] = "z";
        tabla_sintactica[2][15] = "NO";
        tabla_sintactica[2][16] = "r";
        
        tabla_sintactica[3][1] = "sAfFJ";
        
        tabla_sintactica[4][4] = "z";
        tabla_sintactica[4][6] = "z";
        
        tabla_sintactica[5][8] = "z";
        tabla_sintactica[5][10] = "z";
        tabla_sintactica[5][11] = "wK";
        
        tabla_sintactica[6][6] = "z";
        tabla_sintactica[6][15] = "n(Q)";
        
        tabla_sintactica[7][6] = "z";
        tabla_sintactica[7][13] = "PK";
        tabla_sintactica[7][18] = "y";
        
        tabla_sintactica[8][6] = "z";
        tabla_sintactica[8][13] = "PK";
        tabla_sintactica[8][18] = "o";
        
        tabla_sintactica[9][4] = ",B";
        tabla_sintactica[9][6] = "z";
        tabla_sintactica[9][8] = ",F";
        tabla_sintactica[9][10] = "z";
        
        tabla_sintactica[10][6] = ".i";
        
        tabla_sintactica[11][6] = "z";
        tabla_sintactica[11][8] = "z";
        tabla_sintactica[11][10] = "z";
        tabla_sintactica[11][11] = "z";
        tabla_sintactica[11][13] = "z";
        
        tabla_sintactica[12][17] = "'R'";
        
        tabla_sintactica[13][17] = "T";
        tabla_sintactica[13][20] = "d";
        
        tabla_sintactica[14][19] = "a";
        
        tabla_sintactica[15][2] = "*";
        
        tabla_sintactica[16][4] = "z";
        tabla_sintactica[16][6] = "z";
        tabla_sintactica[16][8] = "z";
        tabla_sintactica[16][10] = "z";
        tabla_sintactica[16][11] = "z";
        tabla_sintactica[16][13] = "z";
        
        
        primeros[0][0] = "Q";
        primeros[1][0] = "A";
        primeros[2][0] = "B";
        primeros[3][0] = "D";
        primeros[4][0] = "C";
        primeros[5][0] = "E";
        primeros[6][0] = "F";
        primeros[7][0] = "H";
        primeros[8][0] = "G";
        primeros[9][0] = "I";
        primeros[10][0] = "J";
        primeros[11][0] = "K";
        primeros[12][0] = "V";
        primeros[13][0] = "L";
        primeros[14][0] = "M";
        primeros[15][0] = "N";
        primeros[16][0] = "O";
        primeros[17][0] = "P";
        primeros[18][0] = "R";
        primeros[19][0] = "T";
        primeros[20][0] = "f";
        primeros[21][0] = "i";
        
        primeros[0][1] = "s";
        primeros[1][1] = "i";
        primeros[2][1] = "i";
        primeros[3][1] = "a";
        primeros[4][1] = "i";
        primeros[5][1] = "s";
        primeros[6][1] = "i";
        primeros[7][1] = ",";
        primeros[8][1] = "i";
        primeros[9][1] = "i";
        primeros[10][1] = "w";
        primeros[11][1] = "i";
        primeros[12][1] = "y";
        primeros[13][1] = "i";
        primeros[14][1] = "r";
        primeros[15][1] = "r";
        primeros[16][1] = "i";
        primeros[17][1] = "y";
        primeros[18][1] = ".";
        primeros[19][1] = "d";
        primeros[20][1] = "f";
        primeros[21][1] = "i";
        
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
     
        this.view.errores.setText("2:200 Sin error.");
        
        texto = this.view.entrada.getText();
        lineas = new String [50];
        
        for (int i = 0; i < lineas.length; i++) {
            lineas [i] = "";
        }
        
        pila.clear();
        pila2.clear();
        pila3.clear();
        tabla_lexica.clear();
        X="";
        K="";
        //a = 0;
        APUN = "";
        f = true;
        comillas = false;
        constant = "";
        
        tabla_lexica();
        analisis();
    }
    
    
    /*Este metodo analiza toda la entrada con REGEX sepandando por lineas e insertando en la TABLA LEXICA
    cada elemento que se encuentra, si hay algún elemento invalido, lo mostrará y terminara la ejecución*/
    public void tabla_lexica () {
        int cont = 0;
        String lineas [] = texto.split("\n");
        
        int linea = 1;
        
        Pattern p1 = Pattern.compile("(([>\\<])+([=])|[(\\)\\;\\+\\-\\–\\*\\/\\'\\’\\‘\\,\\.\\>\\<\\=]|([@0-9A-Za-z]*)+([#\\.\\%\\_\\-]*)?[0-9]+([#\\%\\_\\-\\[0-9A-Za-z]*)?([#\\%\\-\\_\\[0-9A-Za-z]*)"
                + "|([@A-Za-z]*)+([#\\%\\_\\-]*)?[A-Za-z0-9]+([#\\%\\_\\-\\[0-9A-Za-z]*)?([#\\%\\-\\_\\[0-9A-Za-z]*)|[!\\$\\%\\&\\?\\¿\\¡\\_]|[a-zA-Z])");
        
        for (int i = 0;i<lineas.length;i++) {
            Matcher m2 = p1.matcher(lineas[i]);
            while (m2.find()){
                cont++;
                if(m2.group().matches("[(\\)\\;\\'\\’\\‘\\,\\.]")){
                    
                    if (m2.group().matches("['\\’\\‘]")) {              //Delimitadores
                        if (comillas) {
                            comillas = false;
                            if (!constant.equals("") && !comillas) {
                           constant = "";
                           tabla_lexica.add("a");                       //Cosntante alfanumerica
                           cont++;
                            } 
                        } else {
                            comillas = true;
                        }
                        
                    }
                    tabla_lexica.add(m2.group());
                    
                } else if(m2.group().matches("[+\\-\\–\\*\\/]")){            //Operadores
                    tabla_lexica.add(m2.group());
                    
                } else if(m2.group().matches("[>\\<\\=]|([>\\<])+([=])")){   //Relacionales
                    tabla_lexica.add("r");
                    
                } else if(m2.group().matches("SELECT|FROM|WHERE|IN|AND|OR|CREATE|TABLE|CHAR|NUMERIC|NOT|NULL|CONSTARINT|KEY|PRIMARY|FOREIGN|REFERENCES|INSERT|INTO|VALUES|GO|CREATE|PROCEDURE|VARCHAR"
                        + "|AS|IF|EXISTS|BEGIN|PRINT|END|ELSE"))             //Palabras reservadas
                {    
                    tabla_lexica.add(m2.group());
                    
                } else if(m2.group().matches("(^[@A-Za-z0-9]*)+([s\\,\\%\\-\\#]*)?[0-9A-Za-z]+([s\\,\\#\\%\\-\\_\\[0-9A-Za-z]*)?([s\\,\\#\\%\\-\\_\\[0-9A-Za-z]*)")){   
                    if (comillas | m2.group().matches("([0-9]*)")) {         //Identificadores y constantes
                       if (comillas) {
                           constant += " " + m2.group();                    
                           cont--;
                           
                       } else {
                           tabla_lexica.add("d");                            //Constante decimal
                       }
                    } else {
                       tabla_lexica.add("i");                                //Identificador
                    }
                                                    
                    
                } else if(m2.group().matches("[!\\#\\%\\&\\?\\¿\\¡]")){
                     this.view.errores.setText("1:101 Error en Línea " + linea + ": Símbolo desconocido. ( " + m2.group() + ")");
                     i = lineas.length;
                     break;
                } else {
                    this.view.errores.setText("1:102 Error en Línea " + linea + ": Elemento inválido. ( " + m2.group() + " )");
                    i = lineas.length;
                    break;
                }
            }
            linea++;
        }
    }
    
    
    
    
    //ALGORITMO LL
    public void analisis(){
        pila.add("$");
        pila.add("Q");
        tabla_lexica.add("$");
        APUN = tabla_lexica.peek();
        
        do {
            X = pila.pop();
            System.out.print("X = " + X + "  ----  ");
            K = corrector();
            System.out.println("K = " + K);
            if (terminal(X) || X.equals("$")) {
                if ( X.equals(K)) {
                    //System.out.print("X = " + X + "  ----  ");
                    //System.out.println("K = " + K);
                    tabla_lexica.remove();
                } else {
                    //System.out.println(X + " --" + K);
                    error(X);
                    System.out.println("Error 1");
                    break;
                }
            } else {
                String produccion = Produc(X, K);
                if (produccion != null) {
                    if (produccion != "z") {
                        insertar(produccion);
                    }
                } else {
                    error(X);
                    System.out.println("Error 2");
                    break;
                }
            }
            
        } while (X != "$");
    }
    
    
    
    
    //ESTE METODO BUSCA Y DEVUELVE LA INTERSECCION DE X,K EN LA TABLA SINTACTICA
    public String Produc (String x, String k) {
        
        int ka = 0;
        for (int i = 0; i < 17; i ++) {
            if (k.equals(tabla_sintactica[i][0])) {
                ka = i;
            }
        }
        
        int equis = 0;
        for (int i = 0; i < 21; i ++) {
            if (x.equals(tabla_sintactica[0][i])) {
                equis = i;
            }
        }
        //System.out.println("Producto de x: " + equis + " k: " + ka);
        String result = tabla_sintactica[ka][equis];
        return result;
    }
    
    
    
    
    //ESTE METODO INSERTA EN LA PILA LAS PRODUCCIONES DE MANERA INVERSA
    public void insertar (String p) {
            int x = p.length();
            for (int i = x-1; i >=0; i--) {
                System.out.println(p.charAt(i));
                pila.add(p.charAt(i)+"");
            }
    }
    
    
    
    
    //DETERMINA SI EL VALOR EXTRAIDO ES UN TERMINAL, SI EL VALOR QUE RECIBE EL METODO ES IGUAL A UNA REGLA, 
    //DEVUELVE FLASE, SI NO DEVUELVE TRUE.
    public boolean terminal (String x) {
        if (x.equals("Q") || x.equals("A") || x.equals("B") || x.equals("D") || x.equals("C") || x.equals("E") || x.equals("F") || x.equals("H") || x.equals("G") || x.equals("I") || x.equals("J") || x.equals("K") || x.equals("V") || x.equals("L") || x.equals("M") || x.equals("N") || x.equals("O") || x.equals("P") || x.equals("R") || x.equals("T")) {
            return false;
        } else {
            return true;
        }
    }
    
    
    
    
    //ESTE METODO SEPARA LAS PALABRAS RESERVADAS DE TODOS LOS DEMAS ELEMENTOS, SI NO ES PALABRA RESERVADA DEVUELVE AUTOMATICAMENTE ELTOKEN, 
    //SI ES PALABRA RESERVADA, ANALIZA QUE PALABRA ES Y DEVUELVE EL TOKEN CORRESPONDIENTE
    public String corrector () {
        String aux = tabla_lexica.peek();
        
        if (aux.equals("$") || aux.equals(".") || aux.equals("(") || aux.equals(")") || aux.equals(",") || aux.equals("'") || aux.equals("+") || aux.equals("-") || aux.equals("*") || aux.equals("/") || aux.equals("r") || aux.equals("i") || aux.equals("d") || aux.equals("a")) {
            return aux;
        } 
        
        Pattern p1 = Pattern.compile("(([>\\<])+([=])|[(\\)\\;\\+\\-\\–\\*\\/\\'\\’\\‘\\,\\.\\>\\<\\=]|([@0-9A-Za-z]*)+([#\\.\\%\\_\\-]*)?[0-9]+([#\\%\\_\\-\\[0-9A-Za-z]*)?([#\\%\\-\\_\\[0-9A-Za-z]*)"
                + "|([@A-Za-z]*)+([#\\%\\_\\-]*)?[A-Za-z0-9]+([#\\%\\_\\-\\[0-9A-Za-z]*)?([#\\%\\-\\_\\[0-9A-Za-z]*)|[!\\$\\%\\&\\?\\¿\\¡\\_]|[a-zA-Z])");
        
        Matcher m2 = p1.matcher(aux);
        while (m2.find()) {
            if(m2.group().matches("SELECT|FROM|WHERE|IN|AND|OR|CREATE|TABLE|CHAR|NUMERIC|NOT|NULL|CONSTARINT|KEY|PRIMARY|FOREIGN|REFERENCES|INSERT|INTO|VALUES|GO|CREATE|PROCEDURE|VARCHAR"
                        + "|AS|IF|EXISTS|BEGIN|PRINT|END|ELSE"))            //Palabras reservadas
                {
                String pal = m2.group();
                switch (pal) {
                    case "SELECT": aux = "s";
                                    auxLinea = m2.group();
                        break;
                    case "FROM": aux = "f";
                                    auxLinea = m2.group();
                        break;
                    case "WHERE": aux = "w";
                                    auxLinea = m2.group();
                        break;
                    case "IN": aux = "n";
                                    auxLinea = m2.group();
                        break;
                    case "AND": aux = "y";
                                    auxLinea = m2.group();
                        break;
                    case "OR": aux = "o";
                                    auxLinea = m2.group();
                        break;
                    case "CREATE": aux = "c";
                                    auxLinea = m2.group();
                        break;
                    case "TABLE": aux = "t";
                                    auxLinea = m2.group();
                        break;
                    case "CHAR": aux = "h";
                                    auxLinea = m2.group();
                        break;
                    case "NUMERIC": aux = "u";
                                    auxLinea = m2.group();
                        break;
                    case "NOT": aux = "e";
                                    auxLinea = m2.group();
                        break;
                    case "NULL": aux = "g";
                                    auxLinea = m2.group();
                        break;
                    case "CONSTRAINT": aux = "b";
                                    auxLinea = m2.group();
                        break;
                    case "KEY": aux = "k";
                                    auxLinea = m2.group();
                        break;
                    case "PRIMARY": aux = "p";
                                    auxLinea = m2.group();
                        break;
                    case "FOREIGN": aux = "j";
                                    auxLinea = m2.group();
                        break;
                    case "REFERENCES": aux = "l";
                                    auxLinea = m2.group();
                        break;
                    case "INSERT": aux = "m";
                                    auxLinea = m2.group();
                        break;
                    case "INTO": aux = "q";
                                    auxLinea = m2.group();
                        break;
                    case "VALUES": aux = "v";
                                    auxLinea = m2.group();
                        break;
                }
            } else {
                
            }
        }
        return aux;
    }
    
    
    
    
    public void error(String dato) {
        error2();
        //System.out.println(dato);
            System.out.println(dato);
        String prim = "";
        
        for (int i = 0; i < 22; i++) {
            if (dato.equals(primeros[i][0])) {
                prim = primeros[i][1];
            }
        }
        
        switch(prim) {
            case "s": this.view.errores.setText("2:201 " + "Linea " + lineaError + ".Se esperaba palabra reservada.");
                break;
            case "i": this.view.errores.setText("2:204 " + "Linea " + lineaError + ".Se esperaba identificador.");
                break;
            case ",": this.view.errores.setText("2:205 " + "Linea " + lineaError + ".Se esperaba delimitador.");
                break;
            case ".": this.view.errores.setText("2:205 " + "Linea " + lineaError + ".Se esperaba delimitador.");
                break;
            case "w": this.view.errores.setText("2:201 " + "Linea " + lineaError + ".Se esperaba palabra reservada.");
                break;
            case "y": this.view.errores.setText("2:201 " + "Linea " + lineaError + ".Se esperaba palabra reservada.");
                break;
            case "r": this.view.errores.setText("2:208 " + "Linea " + lineaError + ".Se esperaba palabra operador relacional.");
                break;
            case "a": this.view.errores.setText("2:206 " + "Linea " + lineaError + ".Se esperaba constante.");
                break;
            case "d": this.view.errores.setText("2:206 " + "Linea " + lineaError + ".Se esperaba constante.");
                break;
            case "f": this.view.errores.setText("2:201 " + "Linea " + lineaError + ".Se esperaba palabra reservada.");
                break;
            default: this.view.errores.setText("2:201 " + "Linea " + lineaError + ".Error sintactico");
                break;
        }
        
    }
    
    
    
    public void error2() {
        System.out.println(auxLinea);
        String lineas [] = texto.split("\n");
        int linea = 1;
        
        for (int i = 0;i<lineas.length;i++) {
            Pattern p1 = Pattern.compile("(([>\\<])+([=])|[(\\)\\;\\+\\-\\–\\*\\/\\'\\’\\‘\\,\\.\\>\\<\\=]|([@0-9A-Za-z]*)+([#\\.\\%\\_\\-]*)?[0-9]+([#\\%\\_\\-\\[0-9A-Za-z]*)?([#\\%\\-\\_\\[0-9A-Za-z]*)"
                    + "|([@A-Za-z]*)+([#\\%\\_\\-]*)?[A-Za-z0-9]+([#\\%\\_\\-\\[0-9A-Za-z]*)?([#\\%\\-\\_\\[0-9A-Za-z]*)|[!\\$\\%\\&\\?\\¿\\¡\\_]|[a-zA-Z])");
            Matcher m2 = p1.matcher(lineas[i]);
            while (m2.find()) {
                if(m2.group().matches(auxLinea)) {
                    lineaError = linea;
                }
            }
            linea++;
        }
    }
    
    public boolean evaluador (String t) {
        
        return true;
    }
    
//    public boolean delimitador(String t){
//        pila2.clear();
//        
//        char arreglo[] = t.toCharArray();
//        
//        for(int i=0; i<arreglo.length; i++){
//            
//            if (arreglo[i] == '(') {
//                pila2.push("(");
//                
//            } else if (arreglo[i] == ')') {
//                if(!pila2.empty()){
//                    pila2.pop();
//                }else{
//                    a = 1; //Falta (
//                    return false; 
//                }
//            } else if (arreglo[i] == ';') {
//                pila2.push(";");
//            }
//        }
//        
//        if (pila2.contains("(") ){
//            a = 2; // falta )
//            return false;
//        } else if (pila2.empty()) {
//            a = 3; // falta ;
//            return false;
//        } else {
//            return true;
//        }
//        
//    }
    
    public boolean constante(String t){
        
        char arreglo[] = t.toCharArray();
        
        for (int i = 0; i < arreglo.length; i++) {
            if (arreglo[i] == '+' || arreglo[i] == '-' || arreglo[i] == '*' || arreglo[i] == '/') {
                if (arreglo[i+2] == '+' || arreglo[i+2] == '-' || arreglo[i+2] == '*' || arreglo[i+2] == '/' || arreglo[i+2] == ';' || arreglo[i+2] == ')') {
                    return false;
                } else if (arreglo[i-2] == '+' || arreglo[i-2] == '-' || arreglo[i-2] == '*' || arreglo[i-2] == '/' || arreglo[i-2] == '(' ) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean operador (String t) {
        
        char arreglo[] = t.toCharArray();
        
        for (int i = 0; i < arreglo.length; i++) {
            if (arreglo[i] == ' ') {
                if (arreglo[i+1] == '(' && arreglo[i-1] == ')' ) {
                    return false;
                } else if (arreglo[i+1] != '+' && arreglo[i+1] != '-'  && arreglo[i+1] != '&' && arreglo[i+1] != '*' && arreglo[i+1] != '/' && arreglo[i+1] != ';'  && arreglo[i+1] != ')' && arreglo[i+1] != ' ' && arreglo[i-1] != '+' && arreglo[i-1] != '-' && arreglo[i-1] != '*' && arreglo[i-1] != '/' && arreglo[i-1] != ';' && arreglo[i-1] != '(' && arreglo[i-1] != ' ' && arreglo[i-1] != '&'){
                    return false;
                }
            }
        }
        return true;
    }
}

