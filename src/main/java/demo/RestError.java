////////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) 2015, Suncorp Metway Limited. All rights reserved.
//
// This is unpublished proprietary source code of Suncorp Metway Limited.
// The copyright notice above does not evidence any actual or intended
// publication of such source code.
//
////////////////////////////////////////////////////////////////////////////////

package demo;

import java.net.URI;
import java.util.Date;
import java.util.UUID;

import org.springframework.http.HttpStatus;

/**
 * Class for creating REST errors... taken from recommendations, from the json-api spec - http://jsonapi.org/format/
 * <p/>
 * Error objects provide additional information about problems encountered while performing an operation.
 * Error objects MUST be returned as an array keyed by "errors" in the top level of a JSON API document,
 * and SHOULD NOT be returned with any primary data.
 */
public class RestError {

    public static final String REL = "error";

    /**
     * Error timestamp
     */
    private final Date errorDate = new Date();

    /**
     * A URI that MAY yield further details about this particular occurrence of the problem.
     */
    private final URI href;

    /**
     * The HTTP status code applicable to this problem.
     */
    private final HttpStatus httpStatus;

    /**
     * A unique identifier for this particular occurrence of the problem.
     * //TODO - this should be a proper correlation id
     */
    private final String errorId = UUID.randomUUID().toString();

    /**
     * A short, human-readable summary of the problem.
     * It SHOULD NOT change from occurrence to occurrence of the problem, except for purposes of localization.
     */
    private final String title;

    /**
     * A human-readable explanation specific to this occurrence of the problem.
     */
    private final String detail;

    /**
     * Optional, the name of the parameter that is in error.
     */
    private final String parameterInError;

    /**
     * An array of JSON Pointers [RFC6901] to the associated resource(s) within the request document.
     * [e.g. ["/data"] for a primary data object].
     */
//TODO - is this useful?
//    private List<Link> links;

    /**
     * An array of JSON Pointers to the relevant attribute(s) within the associated resource(s) in the request document.
     * Each path MUST be relative to the resource path(s) expressed in the error object's "links" member
     * [e.g. ["/first-name", "/last-name"] to reference a couple attributes].
     * Additional members MAY be specified within error objects.
     */
//TODO - is this useful?
//    private List<String> paths;

    /**
     * ctr.
     *
     * @param href
     * @param httpStatus
     * @param title
     * @param detail
     */
    public RestError(URI href, HttpStatus httpStatus, String title, String detail, String parameterInError) {
        this.href = href;
        this.httpStatus = httpStatus;
        this.title = title;
        this.detail = detail;
        this.parameterInError = parameterInError;
    }

    public Date getErrorDate() {
        return errorDate;
    }

    public static String getREL() {
        return REL;
    }

    public URI getHref() {
        return href;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getErrorId() {
        return errorId;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public String getParameterInError() {
        return parameterInError;
    }
}

