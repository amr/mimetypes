package com.github.amr.mimetypes;

import java.util.Arrays;
import java.util.Objects;

public class MimeType {
  public MimeType(String mimeType, String... extensions) {
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
    if (!(o instanceof MimeType)) {
      return false;
    }
    MimeType mimeType1 = (MimeType) o;
    return Objects.equals(mimeType, mimeType1.mimeType) &&
      Arrays.equals(extensions, mimeType1.extensions);
  }

  @Override
  public int hashCode() {
    int result = mimeType.hashCode();
    result = 47 * result + Arrays.hashCode(extensions);
    return result;
  }
}
