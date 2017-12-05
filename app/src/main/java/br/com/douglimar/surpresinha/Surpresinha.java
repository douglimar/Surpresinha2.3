package br.com.douglimar.surpresinha;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by douglimar on 02/12/17.
 */

public class Surpresinha {

    private String gameName;
    private String gameDescription;
    private int iThumbnail;


    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameDescription() {
        return gameDescription;
    }

    public void setGameDescription(String gameDescription) {
        this.gameDescription = gameDescription;
    }

    public int getiThumbnail() {
        return iThumbnail;
    }

    public void setiThumbnail(int iThumbnail) {
        this.iThumbnail = iThumbnail;
    }


    public String generateMegasenaGame() {

        /* Regra do Jogo:
	    * O apostador pode escolher 6 numeros entre 60 numeros disponiveis
	    */

        int numsMegaSena[] = new int[6];

        int indice = 0;
        Random random = new Random();
        String Retorno = "";

        for (int i = 0; i < 6; i++) {
            indice = random.nextInt(61);

            for (int k = 0; k <= 60; k++) {
                if (consisteJogo(numsMegaSena, indice) || indice == 0) {
                    indice = random.nextInt(61);
                }
            }
            numsMegaSena[i] = indice;
        }

        Arrays.sort(numsMegaSena);

        for (int i = 0; i < 6; i++) {

            if (numsMegaSena[i] < 10)
                Retorno = Retorno + " 0" + numsMegaSena[i];
            else
                Retorno = Retorno + " " + numsMegaSena[i];
        }

        return Retorno;

    }

    public String generateQuinaGame() {

        /* Regra do Jogo:
	    * O apostador pode escolher 5 numeros entre 50 numeros disponiveis
	    */

        int numerosQuina[] = new int[5] ;

        int indice = 0;
        Random random = new Random();
        String Retorno = "";

        for (int i = 0 ; i < 5; i++) {

            // Gera um numero aleatorio menor ou igual a 80
            indice = random.nextInt(81);

            // Consiste o nro. gerado, para garantir que ele é unico
            for (int k = 0; k <= 80; k++) {
                if (consisteJogo(numerosQuina, indice) == true || indice == 0 ) {
                    indice = random.nextInt(81);
                }
            }

            // Adiciona o numero gerado dentro de um Array
            numerosQuina[i] = indice;
        }

        // Ordena o Array de numeros gerados
        Arrays.sort(numerosQuina);

        for (int i = 0; i < 5; i++) {

            if (numerosQuina[i] < 10 )
                Retorno = Retorno + " 0" + numerosQuina[i];
            else
                Retorno = Retorno + " " + numerosQuina[i];
        }

        return Retorno;

    }

    public String generateLotofacilGame() {

        /* Regra do Jogo:
	    * O apostador pode escolher 15 numeros entre 25 numeros disponiveis
	    */

        int numsLotofacil[] = new int[15];

        int j = 0;
        int indice = 0;
        Random random = new Random();
        String Retorno = "";

        for (int i = 0 ; i < 15; i++) {
            indice = random.nextInt(26);

            for (int k = 0; k <= 25; k++) {
                if (consisteJogo(numsLotofacil, indice) || indice == 0 ) {
                    indice = random.nextInt(26);
                }
            }

            numsLotofacil[i] = indice;
        }

        Arrays.sort(numsLotofacil);

        for (int i = 0; i < 15; i++) {
            j++;

            if (numsLotofacil[i] < 10)
                Retorno = Retorno + " 0" + numsLotofacil[i];
            else
                Retorno = Retorno + " " + numsLotofacil[i];

            if (j == 5 )  {
                Retorno = Retorno + '\n';
                j = 0 ;
            }

        }

        return Retorno;

    }

    public String generateLotomaniaGame() {

        int numsLotomania[] = new int[50];

        int j = 0 ;
        int indice = 0;
        String Retorno = "";
        Random random = new Random();

        for (int i = 0 ; i < 50; i++) {
            indice = random.nextInt(101);

            // Consiste o nro. gerado, para garantir que ele é unico
            for (int k = 0; k <= 100; k++) {
                if (consisteJogo(numsLotomania, indice) == true || indice == 0 ) {
                    indice = random.nextInt(101);
                }
            }


            numsLotomania[i] = indice;
        }

        Arrays.sort(numsLotomania);

        for (int i = 0; i < 50; i++) {
            j++;

            if (numsLotomania[i]==100)
                Retorno = Retorno + " 00";
            else
                if (numsLotomania[i] < 10)
                    Retorno = Retorno + " 0" + numsLotomania[i];
                else
                    Retorno = Retorno + " " + numsLotomania[i];

            if (j == 8 )  {
                Retorno = Retorno + '\n';
                j = 0 ;
            }

        }

        return Retorno;
    }

    public String generateDuplaSenaGame() {

        /* Regra do Jogo:
	    * O apostador pode escolher 6 numeros entre 50 numeros disponiveis
	    */

        int numsDuplaSena[] = new int[6];

        int indice = 0;
        Random random = new Random();
        String Retorno = "";

        for (int i = 0; i < 6; i++) {
            indice = random.nextInt(51);

            for (int k = 0; k < 50; k++) {
                if (consisteJogo(numsDuplaSena, indice) || indice == 0) {
                    indice = random.nextInt(51);
                }
            }
            numsDuplaSena[i] = indice;
        }

        Arrays.sort(numsDuplaSena);

        for (int i = 0; i < 6; i++) {

            if (numsDuplaSena[i] < 10)
                Retorno = Retorno + " 0" + numsDuplaSena[i];
            else
                Retorno = Retorno + " " + numsDuplaSena[i];
        }

        return Retorno;

    }


    public boolean consisteJogo(int pArray[], int PNumero) {

        boolean Retorno = false;

        for (int i = 0; i < pArray.length; i++) {
            if (pArray[i] == PNumero ) {
                Retorno = true;
                break;
            }
        }

        return Retorno;
    }

}
