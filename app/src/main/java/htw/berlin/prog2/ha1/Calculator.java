package htw.berlin.prog2.ha1;

import org.w3c.dom.ls.LSOutput;
// Der von mir bereits geänderte Code !!!

/**
 * Eine Klasse, die das Verhalten des Online Taschenrechners imitiert, welcher auf
 * https://www.online-calculator.com/ aufgerufen werden kann (ohne die Memory-Funktionen)
 * und dessen Bildschirm bis zu zehn Ziffern plus einem Dezimaltrennzeichen darstellen kann.
 * Enthält mit Absicht noch diverse Bugs oder unvollständige Funktionen.
 */
public class Calculator {

    private String screen = "0";

    private double latestValue;

    private String latestOperation = "";

    private boolean wasCleared = false; // Neues Feld von mir hinzugefügt s0582356


    /**
     * @return den aktuellen Bildschirminhalt als String
     */
    public String readScreen() {
        return screen;
    }

    /**
     * Empfängt den Wert einer gedrückten Zifferntaste. Da man nur eine Taste auf einmal
     * drücken kann muss der Wert positiv und einstellig sein und zwischen 0 und 9 liegen.
     * Führt in jedem Fall dazu, dass die gerade gedrückte Ziffer auf dem Bildschirm angezeigt
     * oder rechts an die zuvor gedrückte Ziffer angehängt angezeigt wird.
     *
     * @param digit Die Ziffer, deren Taste gedrückt wurde
     */
    public void pressDigitKey(int digit) {
        if (digit > 9 || digit < 0) throw new IllegalArgumentException();

        if (screen.equals("0") || latestValue == Double.parseDouble(screen)) screen = "";


        screen = screen + digit;

    }

    /**
     * Empfängt den Befehl der C- bzw. CE-Taste (Clear bzw. Clear Entry).
     * Einmaliges Drücken der Taste löscht die zuvor eingegebenen Ziffern auf dem Bildschirm
     * so dass "0" angezeigt wird, jedoch ohne zuvor zwischengespeicherte Werte zu löschen.
     * Wird daraufhin noch einmal die Taste gedrückt, dann werden auch zwischengespeicherte
     * Werte sowie der aktuelle Operationsmodus zurückgesetzt, so dass der Rechner wieder
     * im Ursprungszustand ist.
     */
    public void pressClearKey() {                             // (bunu göster 1)
        /*  Code vom Professor

            screen = "0";
            latestOperation = "";
            latestValue = 0.0;

         */

        /*
         Die Zeilen mit if(was Cleared) wurden hinzugefügt. Der obere auskommentierte Code ist vom Prof
         Die boolean Variable wasCleared wurde oben extra deklariert. Wenn wasCleared true ist
         dann wird der if Zweig ausgeführt(Bildschirm und alles zurückgesetzt). Wenn wasCleared
         aber falsch ist, dann wird sofort in den Else Zweig gesprungen und nur der aktuelle
         Bildschirm (screen) zurückgesetzt!!
         */

        // Der Code ab hier wurde neu hinzugefügt
        if (wasCleared) {
            // Beim zweiten Drücken: Setze alles zurück
            screen = "0";
            latestOperation = "";
            latestValue = 0.0;
            wasCleared = false; // Zustand zurücksetzen
        } else {
            // Beim ersten Drücken: Setze nur den Bildschirm zurück
            screen = "0";
            wasCleared = true; // Zustand setzen, um den nächsten Tastendruck zu verfolgen

        } // bis hierher wurde der neue Code hinzugefügt!
    }

    /**
     * Empfängt den Wert einer gedrückten binären Operationstaste, also eine der vier Operationen
     * Addition, Substraktion, Division, oder Multiplikation, welche zwei Operanden benötigen.
     * Beim ersten Drücken der Taste wird der Bildschirminhalt nicht verändert, sondern nur der
     * Rechner in den passenden Operationsmodus versetzt.
     * Beim zweiten Drücken nach Eingabe einer weiteren Zahl wird direkt des aktuelle Zwischenergebnis
     * auf dem Bildschirm angezeigt. Falls hierbei eine Division durch Null auftritt, wird "Error" angezeigt.
     *
     * @param operation "+" für Addition, "-" für Substraktion, "x" für Multiplikation, "/" für Division
     */
    public void pressBinaryOperationKey(String operation) {      // (bunu göster 3)
     /*  Im Code vom Prof arbeitet diese Methode, dass die erste Zahl nicht gespeichert wird.
        Als Ergebnis kommt dann 5 + 2 - 1 = 1 heraus. Wir haben die pressBinaryOperationKey so gefixt,
        dass somit die Rechnung 5 + 2 - 1 = 6 heraus kommt.

        latestValue = Double.parseDouble(screen);
        latestOperation = operation;
       */

        // Diese Zeilen wurden hinzugefügt
        if (!latestOperation.isEmpty()) {
            pressEqualsKey(); // Führe die vorherige Operation aus, wenn sie existiert
        } // geht bis hier

        latestValue = Double.parseDouble(screen);
        latestOperation = operation;

    }

    /**
     * Empfängt den Wert einer gedrückten unären Operationstaste, also eine der drei Operationen
     * Quadratwurzel, Prozent, Inversion, welche nur einen Operanden benötigen.
     * Beim Drücken der Taste wird direkt die Operation auf den aktuellen Zahlenwert angewendet und
     * der Bildschirminhalt mit dem Ergebnis aktualisiert.
     *
     * @param operation "√" für Quadratwurzel, "%" für Prozent, "1/x" für Inversion
     */
    public void pressUnaryOperationKey(String operation) {          // bunu göster 2)
        latestValue = Double.parseDouble(screen);
        // Hinzugefügte Änderung beinhaltet das beim drücken der Inverstaste
        // ein Error ausgegeben wird. Beim Code vom Professor wurde Infinity ausgegeben!
        if (operation.equals("1/x") && Double.parseDouble(screen) == 0.0) {
            screen = "Error";
            return;
        }//-----------------bis hier

        latestOperation = operation;
        var result = switch (operation) {
            case "√" -> Math.sqrt(Double.parseDouble(screen));
            case "%" -> Double.parseDouble(screen) / 100;
            case "1/x" -> 1 / Double.parseDouble(screen);
            default -> throw new IllegalArgumentException();
        };
        screen = Double.toString(result);

        if (screen.equals("NaN")) screen = "Error";
        if (screen.contains(".") && screen.length() > 11) screen = screen.substring(0, 10);

    }

    /**
     * Empfängt den Befehl der gedrückten Dezimaltrennzeichentaste, im Englischen üblicherweise "."
     * Fügt beim ersten Mal Drücken dem aktuellen Bildschirminhalt das Trennzeichen auf der rechten
     * Seite hinzu und aktualisiert den Bildschirm. Daraufhin eingegebene Zahlen werden rechts vom
     * Trennzeichen angegeben und daher als Dezimalziffern interpretiert.
     * Beim zweimaligem Drücken, oder wenn bereits ein Trennzeichen angezeigt wird, passiert nichts.
     */
    public void pressDotKey() {
        if (!screen.contains(".")) screen = screen + ".";
    }

    /**
     * Empfängt den Befehl der gedrückten Vorzeichenumkehrstaste ("+/-").
     * Zeigt der Bildschirm einen positiven Wert an, so wird ein "-" links angehängt, der Bildschirm
     * aktualisiert und die Inhalt fortan als negativ interpretiert.
     * Zeigt der Bildschirm bereits einen negativen Wert mit führendem Minus an, dann wird dieses
     * entfernt und der Inhalt fortan als positiv interpretiert.
     */
    public void pressNegativeKey() {
        screen = screen.startsWith("-") ? screen.substring(1) : "-" + screen;


    }

    /**
     * Empfängt den Befehl der gedrückten "="-Taste.
     * Wurde zuvor keine Operationstaste gedrückt, passiert nichts.
     * Wurde zuvor eine binäre Operationstaste gedrückt und zwei Operanden eingegeben, wird das
     * Ergebnis der Operation angezeigt. Falls hierbei eine Division durch Null auftritt, wird "Error" angezeigt.
     * Wird die Taste weitere Male gedrückt (ohne andere Tasten dazwischen), so wird die letzte
     * Operation (ggf. inklusive letztem Operand) erneut auf den aktuellen Bildschirminhalt angewandt
     * und das Ergebnis direkt angezeigt.
     */
    public void pressEqualsKey() {
        var result = switch (latestOperation) {
            case "+" -> latestValue + Double.parseDouble(screen);
            case "-" -> latestValue - Double.parseDouble(screen);
            case "x" -> latestValue * Double.parseDouble(screen);
            case "/" -> latestValue / Double.parseDouble(screen);
            default -> throw new IllegalArgumentException();
        };

        screen = Double.toString(result);
        if (screen.equals("Infinity")) screen = "Error";
        if (screen.endsWith(".0")) screen = screen.substring(0, screen.length() - 2);
        if (screen.contains(".") && screen.length() > 11) screen = screen.substring(0, 10);
    }


    // Die Methoden die implementiert wurden habe ich extra nochmal in der Main simuliert!

    public static void main(String[] args) {

        Calculator calculator = new Calculator();

        // Verwendung der pressClearKey Methode in Zusammenspiel mit testSingleClearPress().
        /*
        Bei 1 Mal verwenden Methode pressClearKey wird einfach die 4 entfernt
        und die Addition dann mit der 1 fortgesetzt (2 + 4 dann 4 entfernt und 1 addiert = 3)
         */
        calculator.pressDigitKey(2);
        calculator.pressBinaryOperationKey("+");
        calculator.pressDigitKey(4);
        calculator.pressClearKey();
        calculator.pressDigitKey(1);
        calculator.pressEqualsKey();
        System.out.println(calculator.readScreen());

        /*
        Bei 2 maligen drücken der pressClearKey Methode wird der gesamte
        mathematische Ausdruck gelöscht. Die neue Berechnung kann
        dann durchgeführt werden. In dem Fall wird 3 + 7 = 10 berechnet.
         */
        calculator.pressClearKey();
        calculator.pressClearKey();
        calculator.pressDigitKey(3);
        calculator.pressBinaryOperationKey("+");
        calculator.pressDigitKey(7);

        calculator.pressEqualsKey();
        System.out.println(calculator.readScreen());

        calculator.pressClearKey();
        calculator.pressClearKey();

        // ----------------------------------------------------------------------------------

        // Division durch 0 mit 1/x soll Error ausgeben
        System.out.println("Hier startet die Division mit Inverse 1/x Taste");
        Calculator calc = new Calculator();

        calc.pressDigitKey(0);

        // Versuch, die Inversion zu berechnen
        calc.pressUnaryOperationKey("1/x");

        //  calc.pressEqualsKey();
        System.out.println(calc.readScreen());

        //---------------------------------------------------------------------------------------------

        // Überprüfen der pressBinaryOperationKey in Zusammenspiel mit testConsecutiveBinaryOperations()
        System.out.println("Hier beginnt die Rechnung nacheinander ohne auf = zu drücken");
        calculator.pressDigitKey(5);
        calculator.pressBinaryOperationKey("+");
        calculator.pressDigitKey(3);
        calculator.pressBinaryOperationKey("-");
        calculator.pressDigitKey(2);
        calculator.pressEqualsKey();
        System.out.println(calculator.readScreen());
    }
}
