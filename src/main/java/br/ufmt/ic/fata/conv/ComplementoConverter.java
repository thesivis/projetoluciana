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

import br.ufmt.ic.fata.ent.Complemento;

/**
 *
 * @author vicentejr
 */
@FacesConverter(value = "complemento", forClass = Complemento.class)
public class ComplementoConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string != null && !string.isEmpty()) {
            return (Complemento) uic.getAttributes().get(string);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
         if (o instanceof Complemento) {
            Complemento entity= (Complemento) o;
            if (entity != null && entity instanceof Complemento && entity.getComplementoId() != null) {
                uic.getAttributes().put( entity.getComplementoId().toString(), entity);
                return entity.getComplementoId().toString();
            }
        }
        return "";
    }
    
}
