/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufmt.fata;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

/**
 *
 * @author raphael
 */
@FacesValidator("FileUploadValidator")
public class ValidatorFile implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        List<FacesMessage> msgs = new ArrayList<FacesMessage>();
        Part f = (Part) o;
        if (!("image/jpeg".equals(f.getContentType()) || "image/png".equals(f.getContentType()))) {
            FacesMessage msg = new FacesMessage("Formato do arquivo errado (png, jpg)!");
            msgs.add(msg);

            throw new ValidatorException(msgs);
        } else if (f.getSize() > 3145728) {
            FacesMessage msg = new FacesMessage("Tamanho excedido!");
            msgs.add(msg);

            throw new ValidatorException(msgs);
        }
    }

}
