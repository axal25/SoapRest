package rest.api.my.utils;

import rest.api.TestRestWS;

public class Utils {
    public static String addHtmlNewLineToLocalNewLine( String stringContainingOnlyNR ) {
        String stringContainingNRandBR = stringContainingOnlyNR.replaceAll("\n\r", "<br/> \n\r");
        return stringContainingNRandBR;
    }

    public static String wrapInHtml( String pageTitle, String response ) {

        response = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\t <head>\n" +
                "\t\t <title>" + pageTitle + "</title>\n" +
                "\t </head>\n" +
                "\t <body>\n" + response + "</body>\n" +
                "</html>\n";

        return response;
    }

    /**[0]: method, [1]: params, [2]: description, [3]: url, [4]: exampleParam  **/
    public static String wrapInAHref( String[][] sArray ) {
        String wrapped = "";
        String method = "";
        String params = "";
        String description = "";
        String url = "";
        String exampleParam = "";

        for ( int i = 0; i < sArray.length; i++ ) {
            if( sArray[i].length == 5 ) {
                method = sArray[i][0];
                params = sArray[i][1];
                description = sArray[i][2];
                url = sArray[i][3];
                exampleParam = sArray[i][4];

                wrapped = wrapped + "<a href=\"" + url + exampleParam + "\"> [" + method + "]";
                if( params.compareTo(TestRestWS.noParams) != 0 ) {

                    wrapped = wrapped + " ( " + params + " ) == { " + exampleParam + " } ";
                }
                wrapped = wrapped + description + "</a> </br>\n";
            }
            else {
                if( sArray[i].length == 1 ) {
                    wrapped = wrapped + sArray[i][0] + "</br> \n\r";
                }
                else {
                    wrapped = wrapped + "#" + i + ") Has different length = " + sArray[i].length + "</br> \n\r";
                }
            }
        }
        return wrapped;
    }
}
