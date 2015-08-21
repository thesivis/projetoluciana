/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ufmt.fata.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author vicentejr
 */
public class ArquivoController {
    public static List<String> listaArquivos(File path) { 
  
        List<String> listaVolta = new ArrayList();  
        File[] files = path.listFiles();  


        for (int i = 0; i < files.length; i++) {  
          File arq = files[i];  

          if (arq.isDirectory()) {   
              Collection lista = listaArquivos(arq);  
              if (lista.size() > 0) listaVolta.addAll(lista);  
          }else{
               listaVolta.add((arq.getName()));
          } 
          
        }  
    return listaVolta;  
    }  
}
