/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmt.fata.entities;

import java.io.Serializable;

/**
 *
 * @author raphael
 */
public interface Entity extends Serializable {

    public Integer getId();
    public String getUrl();
    public String getPalavra();

}
