package br.com.douglimar.surpresinha;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Douglimar Moraes on 02/12/17.
 *
 *
 */

class Surpresinha {

    private String gameName;
    private String gameDescription;
    private int iThumbnail;
    private String url;


    public String getUrl(String pGame) {
        
        switch (pGame) {

            case "MEGA-SENA": {
                url = "http://www.loterias.caixa.gov.br/wps/portal/loterias/landing/megasena";
                break;
            }
            case "QUINA": {
                url = "http://www.loterias.caixa.gov.br/wps/portal/loterias/landing/quina";
                break;
            }
            case "LOTOFÁCIL": {
                url = "http://www.loterias.caixa.gov.br/wps/portal/loterias/landing/lotofacil";
                break;
            }
            case "LOTOMANIA": {
                url = "http://www.loterias.caixa.gov.br/wps/portal/loterias/landing/lotomania";
                break;
            }
            case "DUPLA-SENA": {
                url = "http://www.loterias.caixa.gov.br/wps/portal/loterias/landing/duplasena";
                break;
            }
        }
        return url;
    }

    public int[] getBackgroundColors(String pGame) {
        
        int[] values = new int[]{0,0};

        switch (pGame) {

            case "MEGA-SENA": {
                values[0] = R.color.colorMegasena;
                values[1] = R.drawable.degrade_radial_megasena;
                break;
            }
            case "QUINA": {
                values[0] = R.color.colorQuina;
                values[1] = R.drawable.degrade_radial_quina;
                break;
            }
            case "LOTOFÁCIL": {
                values[0] = R.color.colorLotofacil;
                values[1] = R.drawable.degrade_radial_lotofacil;
                break;
            }
            case "LOTOMANIA": {
                values[0] = R.color.colorLotomania;
                values[1] = R.drawable.degrade_radial_lotomania;
                break;
            }
            case "DUPLA-SENA": {
                values[0] = R.color.colorDuplasena;
                values[1] = R.drawable.degrade_radial_duplasena;
                break;
            }
        }

        return values;
    }



    public String generateMegasenaGame() {

        /* Regra do Jogo:
	    * O apostador pode escolher 6 numeros entre 60 numeros disponiveis
	    */

        int numsMegaSena[] = new int[6];

        int indice;
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

        int indice;
        Random random = new Random();
        String Retorno = "";

        for (int i = 0 ; i < 5; i++) {

            // Gera um numero aleatorio menor ou igual a 80
            indice = random.nextInt(81);

            // Consiste o nro. gerado, para garantir que ele é unico
            for (int k = 0; k <= 80; k++) {
                if (consisteJogo(numerosQuina, indice) || indice == 0 ) {
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
        int indice;
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
        int indice;
        String Retorno = "";
        Random random = new Random();

        for (int i = 0 ; i < 50; i++) {
            indice = random.nextInt(101);

            // Consiste o nro. gerado, para garantir que ele é unico
            for (int k = 0; k <= 100; k++) {
                if (consisteJogo(numsLotomania, indice) || indice == 0 ) {
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

        int indice;
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


    private boolean consisteJogo(int pArray[], int PNumero) {

        boolean Retorno = false;

        for (int aPArray : pArray) {
            if (aPArray == PNumero) {
                Retorno = true;
                break;
            }
        }

        return Retorno;
    }

}
