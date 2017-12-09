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

import br.ufmt.ic.fata.ent.Sujeito;

/**
 *
 * @author vicentejr
 */
@FacesConverter(value = "sujeito", forClass = Sujeito.class)
public class SujeitoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string != null && !string.isEmpty()) {
            return (Sujeito) uic.getAttributes().get(string);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
         if (o instanceof Sujeito) {
            Sujeito entity= (Sujeito) o;
            if (entity != null && entity instanceof Sujeito && entity.getSujeitoId() != null) {
                uic.getAttributes().put( entity.getSujeitoId().toString(), entity);
                return entity.getSujeitoId().toString();
            }
        }
        return "";
    }
    
    
    
}
