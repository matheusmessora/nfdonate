package br.com.pandox.nfdonate.boot;

import br.com.pandox.nfdonate.boot.server.ApplicationServer;
import br.com.pandox.nfdonate.boot.server.WebServer;

public class Main {


    public static void main(String[] args) throws Exception {
        WebServer server = new ApplicationServer();
        server.start(15081);
    }

}