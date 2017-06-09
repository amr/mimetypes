# MimeTypes

[![Build Status](https://travis-ci.org/amr/mimetypes.svg?branch=master)](https://travis-ci.org/amr/mimetypes)
[![Test Coverage](https://codecov.io/github/amr/mimetypes/coverage.svg?branch=master)](https://codecov.io/github/amr/mimetypes?branch=master)

Java (JVM) lookup table for standard mime types and their extensions.

# API Usage

Get the extension for a mime type:

    MimeTypes.getInstance().getByType("image/png").getExtension(); // returns "png"

Get all possible extensions for a mime type:

    MimeTypes.getInstance().getByType("image/png").getExtensions(); // returns String[]{"png"}
    MimeTypes.getInstance().getByType("text/html").getExtensions(); // returns String[]{"html", "htm"}

Get mime type for an extension:

    MimeTypes.getInstance().getByExtension("png").getMimeType(); // returns "image/png"

Register a custom mimetype and query it up later:

    MimeTypes.getInstance().register(new MimeType("application/vnd.awesome", new String[]{"wsm"}));
    MimeTypes.getInstance().getByType("application/vnd.awesome").getExtension(); // returns "wsm"

Initialize with a custom "mime.types" definitions file:

    new MimeTypes(Paths.get("/path/to/custom/mime.types"));

Alternatively:

    MimeTypes.blank().load(Paths.get("/path/to/custom/mime.types"));

# Build

Using maven:

    mvn package

If you want the sources and javadocs too:

    mvn package -P release

# Maven central coordinates

New releases are submitted to central, to use:

    <dependencies>
      <dependency>
        <groupId>com.github.amr</groupId>
        <artifactId>mimetypes</artifactId>
        <version>0.0.3</version>
      </dependency>
    </dependencies>

# Included mime types

`MimeTypes.getInstance()` or the empty constructor `new MimeTypes()` will load
the included `mime.types` definitions, which is downloaded from:

    http://svn.apache.org/repos/asf/httpd/httpd/trunk/docs/conf/mime.types

# JVM

JVM 7 or later is required.

# License

* [MIT License](http://www.opensource.org/licenses/mit-license.php)
