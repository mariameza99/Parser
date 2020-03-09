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
 * @author luisangel
 */
public class ctrl implements ActionListener{

    private view view;
    String texto;
    private String[] lineas;
    Queue<String> tabla_lexica=new LinkedList();
    Stack<String> pila = new Stack<String>(); 
    Stack<String> pila2 = new Stack<String>();
    Stack<String> pila3 = new Stack<String>();
    String tabla_sintactica[][] = new String[16][21];
    String X = "";
    String K = "";
    int a = 0;
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
        tabla_sintactica[0][15] = "N";
        tabla_sintactica[0][16] = "O";
        tabla_sintactica[0][17] = "P";
        tabla_sintactica[0][18] = "R";
        tabla_sintactica[0][19] = "T";
        
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
        tabla_sintactica[14][0] = "*";
        tabla_sintactica[15][0] = "$";
        
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
        tabla_sintactica[2][16] = "G";
        
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
        
        tabla_sintactica[14][2] = "*";
        tabla_sintactica[14][19] = "a";
        
        tabla_sintactica[15][4] = "z";
        tabla_sintactica[15][6] = "z";
        tabla_sintactica[15][8] = "z";
        tabla_sintactica[15][10] = "z";
        tabla_sintactica[15][11] = "z";
        tabla_sintactica[15][13] = "z";
        
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
     
        this.view.errores.setText("2:200 Sin error.");
        
        texto = this.view.entrada.getText();
        lineas = new String [30];
        
        for (int i = 0; i < lineas.length; i++) {
            lineas [i] = "";
        }
        
        pila.clear();
        pila2.clear();
        pila3.clear();
        tabla_lexica.clear();
        X="";
        K="";
        a = 0;
        
        tabla_lexica();
        analisis();
    }
    
    
    /*Este metodo analziar toda la entrada con REGEX sepandando por lineas e insertando en la TABLA LEXICA
    cada elemento que se encuentra, si hay algún elemento invalido, lo mostrará y terminara la ejecución*/
    public void tabla_lexica () {
        int cont = 0;
        String lineas [] = texto.split("\n");
        
        int linea = 1;
        
        //Pattern p1 = Pattern.compile("([(\\)\\;\\+\\-\\–\\*\\/]|([0-9A-Za-z]*)+([,\\.\\%\\_\\-]*)?[0-9]+([,\\.\\%\\_\\-\\[0-9A-Za-z]*)?([,\\.\\%\\-\\_\\[0-9A-Za-z]*)|([A-Za-z]*)+([,\\.\\%\\_\\-]*)?[A-Z0-9]+([,\\.\\%\\_\\-\\[0-9A-Za-z]*)?([,\\.\\%\\-\\_\\[0-9A-Za-z]*)|[!\\#\\$\\%\\&\\?\\¿\\¡\\_]|[a-zA-Z])");
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
                    tabla_lexica.add(m2.group()); //tal vez necesita el algoritmo de comentarios de varias palabras
                    
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
        System.out.println(APUN);
        System.out.println(tabla_sintactica[2][15]);
        
        do {
            X = pila.pop();
            System.out.print("X = " + X + "  ----  ");
            K = corrector();
            System.out.println("K = " + K);
            if (terminal(X) || X.equals("$")) {
                if ( X.equals(K)) {
//                    System.out.print("X = " + X + "  ----  ");
//                    System.out.println("K = " + K);
                    tabla_lexica.remove();
                } else {
                    error();
                    System.out.println("Error 1");
                    break;
                }
            } else {
                String produccion = Produc(X, K);
                if (produccion != null) {
                    if (produccion != "z") {
                        insertar(produccion);
                    } else {
                    }
                } else {
                    error();
                    System.out.println("Error 2");
                    break;
                }
            }
            
        } while (X != "$");
    }
    
    
    
    
    //ESTE METODO BUSCA Y DEVUELVE LA INTERSECCION DE X,K EN LA TABLA SINTACTICA
    public String Produc (String x, String k) {
        
        int ka = 0;
        for (int i = 0; i < 16; i ++) {
            if (k.equals(tabla_sintactica[i][0])) {
                ka = i;
            }
        }
        
        int equis = 0;
        for (int i = 0; i < 20; i ++) {
            if (x.equals(tabla_sintactica[0][i])) {
                equis = i;
            }
        }
        
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
    
    
    
    
    //ESTE METODO SEPARA LAS PALABRAS RESERVADAS DE TODOS LOS DEMAS ELEMENTOS, SI NO ES PALABRA RESERVADA DEVUELVE AUTOMATICAMENTE E LTOKEN, 
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
                        break;
                    case "FROM": aux = "f";
                        break;
                    case "WHERE": aux = "w";
                        break;
                    case "IN": aux = "n";
                        break;
                    case "AND": aux = "y";
                        break;
                    case "OR": aux = "o";
                        break;
                    case "CREATE": aux = "c";
                        break;
                    case "TABLE": aux = "t";
                        break;
                    case "CHAR": aux = "h";
                        break;
                    case "NUMERIC": aux = "u";
                        break;
                    case "NOT": aux = "e";
                        break;
                    case "NULL": aux = "g";
                        break;
                    case "CONSTRAINT": aux = "b";
                        break;
                    case "KEY": aux = "k";
                        break;
                    case "PRIMARY": aux = "p";
                        break;
                    case "FOREIGN": aux = "j";
                        break;
                    case "REFERENCES": aux = "l";
                        break;
                    case "INSERT": aux = "m";
                        break;
                    case "INTO": aux = "q";
                        break;
                    case "VALUES": aux = "v";
                        break;
                }
            } else {
                
            }
        }
        return aux;
    }
    
    
    
    
    
    public void error() {
        String lineas [] = texto.split("\n");
        int linea = 1;
        for (int i = 0; i < lineas.length; i ++) {
            if (delimitador(lineas[i]) == false) {
                if (a == 1) {
                    this.view.errores.setText("2:201 Error Línea " + (i+1) + ". Falta delimitador. * ( * Expected.");
                    break;
                } else if (a == 2) {
                    this.view.errores.setText("2:201 Error Línea " + (i+1) + ". Falta delimitador. * ) * Expected.");
                    break;
                } else {
                    this.view.errores.setText("2:201 Error Línea " + (i+1) + ". Falta delimitador. * ; * Expected.");
                    break;
                } 
            }
            if (constante(lineas[i]) == false ) {
                this.view.errores.setText("2:203 Error Línea " + (i+1) + ". Falta identificador / constante.");
                break;
            }
            if (operador(lineas[i]) == false ) {
                this.view.errores.setText("2:202 Error Línea " + (i+1) + ". Falta operador.");
                break;
            }
        }
    }
    
    public boolean delimitador(String t){
        pila2.clear();
        
        char arreglo[] = t.toCharArray();
        
        for(int i=0; i<arreglo.length; i++){
            
            if (arreglo[i] == '(') {
                pila2.push("(");
                
            } else if (arreglo[i] == ')') {
                if(!pila2.empty()){
                    pila2.pop();
                }else{
                    a = 1; //Falta (
                    return false; 
                }
            } else if (arreglo[i] == ';') {
                pila2.push(";");
            }
        }
        
        if (pila2.contains("(") ){
            a = 2; // falta )
            return false;
        } else if (pila2.empty()) {
            a = 3; // falta ;
            return false;
        } else {
            return true;
        }
        
    }
    
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
