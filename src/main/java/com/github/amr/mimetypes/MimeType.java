package com.github.amr.mimetypes;

import java.util.Arrays;

public class MimeType {
  public MimeType() {
  }

  public MimeType(String mimeType) {
    this.mimeType = mimeType;
    this.extensions = new String[0];
  }

  public MimeType(String mimeType, String[] extensions) {
    this.mimeType = mimeType;
    this.extensions = extensions;
  }

  private String mimeType;

  private String[] extensions;

  public String getMimeType() {
    return mimeType;
  }

  public String[] getExtensions() {
    return extensions;
  }

  public String getExtension() {
    if (extensions != null && extensions.length > 0) {
      return extensions[0];
    }

    return null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    MimeType mimeType1 = (MimeType) o;

    if (!Arrays.equals(extensions, mimeType1.extensions)) {
      return false;
    }
    if (!mimeType.equals(mimeType1.mimeType)) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = mimeType.hashCode();
    result = 47 * result + Arrays.hashCode(extensions);
    return result;
  }
}
