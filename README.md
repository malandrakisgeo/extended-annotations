# extended-annotations

## Motivation

It all begun when working on DELD-httpclient.

The annotations for HTTP requests (@GET, @POST, etc) were all related, and had several fields in common.

I needed an abstraction to handle them as children of a parental annotation (e.g. @HttpRequest) and get the values of their fields,
but as of 2023, Java does not provide a straightforward way to achieve this (e.g., extending or implementing annotations).

This project demonstrates that one can achieve something similar to extending an annotation (i.e. annotating and getting
the values of the child via the parent) using proxies and reflections.

Examples of use may be found under /test.
