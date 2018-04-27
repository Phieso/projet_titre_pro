package com.siemens.android.calculateurautonomie;

public class AppConfig {

    /*
    public static String myInetAdress (){

        InetAddress ip = null;
        try {

            ip = InetAddress.getLocalHost();

        } catch (UnknownHostException e) {

            e.printStackTrace();

        }
        String myIP = ip.toString();
        String[] parts = myIP.split("/");
        String part1 = parts[0]; // pc
        String part2 = parts[1]; // ip

        return part2;

        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
}

        InetAddress ip = null;
        try {

            ip = InetAddress.getLocalHost();


        } catch (UnknownHostException e) {

            e.printStackTrace();

        }
        return ip.toString();
    }

        static String myIP = myInetAdress();
        static String[] parts = myIP.split("/");
        static String part1 = parts[0]; // partie avant le slash
        static String part2 = parts[1]; // partie après le slash
        // System.out.println(part2);

    // Server user login url
    public static String URL_LOGIN = "http://"+ part2 +":80/android_login_api/login.php";

    // Server user register url
    public static String URL_REGISTER = "http://"+ part2 +":80/android_login_api/register.php";

    */

    // Server user login url
    public static String URL_LOGIN = "http://192.168.0.16:80/android_login_api/login.php";

    // Server user register url
    public static String URL_REGISTER = "http://192.168.0.16:80/android_login_api/register.php";

    // Server save entry system 1 url
    public static String URL_STORE_RESULT1 = "http://192.168.0.16:80/android_login_api/systeme1/sauvegarde_resultats1.php";

}