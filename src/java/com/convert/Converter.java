package com.convert;

import com.model.entity.Entity;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author thiago
 */
public interface Converter {

    public Entity convertEntity(HttpServletRequest request);
}
