/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmt.ic.fata.conv;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ufmt.ic.fata.ent.PastaDeComunicacao;

/**
 *
 * @author vicentejr
 */
@FacesConverter(value = "pastaDeComunicacao", forClass = PastaDeComunicacao.class)
public class PastaDeComunicacaoConverter implements Converter{
    
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string != null && !string.isEmpty()) {
            return (PastaDeComunicacao) uic.getAttributes().get(string);
        }
        return null;
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
         if (o instanceof PastaDeComunicacao) {
            PastaDeComunicacao entity= (PastaDeComunicacao) o;
            if (entity != null && entity instanceof PastaDeComunicacao && entity.getPastaComunicacaoId() != null) {
                uic.getAttributes().put( entity.getPastaComunicacaoId().toString(), entity);
                return entity.getPastaComunicacaoId().toString();
            }
        }
        return "";
    }
    
}
