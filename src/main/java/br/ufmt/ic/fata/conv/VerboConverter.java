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

import br.ufmt.ic.fata.ent.Verbo;

/**
 *
 * @author vicentejr
 */
@FacesConverter(value = "verbo", forClass = Verbo.class)
public class VerboConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string != null && !string.isEmpty()) {
            return (Verbo) uic.getAttributes().get(string);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
         if (o instanceof Verbo) {
            Verbo entity= (Verbo) o;
            if (entity != null && entity instanceof Verbo && entity.getVerboId() != null) {
                uic.getAttributes().put( entity.getVerboId().toString(), entity);
                return entity.getVerboId().toString();
            }
        }
        return "";
    }
    
}
