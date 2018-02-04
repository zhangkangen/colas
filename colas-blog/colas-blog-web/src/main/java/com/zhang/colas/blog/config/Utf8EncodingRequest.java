package com.zhang.colas.blog.config;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class Utf8EncodingRequest extends HttpServletRequestWrapper {

    private final String DEFAULT_ENCODING = "UTF-8";

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request
     * @throws IllegalArgumentException if the request is null
     */
    public Utf8EncodingRequest(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getCharacterEncoding() {

        String characterEncoding = this.getRequest().getCharacterEncoding();
        return StringUtils.isBlank(characterEncoding) ? DEFAULT_ENCODING : characterEncoding;
    }
}
