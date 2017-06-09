package com.github.amr.mimetypes;

import org.junit.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class MimeTypesTest {
  public static Path getResource(String path) {
    try {
      return Paths.get(MimeTypesTest.class.getClassLoader().getResource(path).toURI());
    } catch (URISyntaxException e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Test
  public void testByType() {
    // JSON
    MimeType type = MimeTypes.getInstance().getByType("application/json");
    assertEquals(type.getMimeType(), "application/json");
    assertArrayEquals(type.getExtensions(), new String[]{"json"});
    assertEquals(type.getExtension(), "json");
  }

  @Test
  public void testByExt() {
    MimeType type = MimeTypes.getInstance().getByExtension("html");
    assertEquals(type.getMimeType(), "text/html");
    assertArrayEquals(type.getExtensions(), new String[]{"html", "htm"});
    assertEquals(type.getExtension(), "html");
  }

  @Test
  public void testCustomDefinitionsFile() {
    MimeTypes mimeTypes = MimeTypes.blank().load(getResource("custom-mime-types"));

    // By Type
    MimeType type = mimeTypes.getByType("application/vnd.custom.foo");
    assertEquals(type.getMimeType(), "application/vnd.custom.foo");
    assertArrayEquals(type.getExtensions(), new String[]{"foo"});

    // By ext
    type = mimeTypes.getByExtension("foo");
    assertEquals(type.getMimeType(), "application/vnd.custom.foo");
    assertArrayEquals(type.getExtensions(), new String[]{"foo"});
  }

  @Test
  public void testCustomDefinitionsInline() {
    MimeTypes mimeTypes = MimeTypes.getInstance();
    mimeTypes.loadOne("application/vnd.custom.bar	 bar zot");

    // By Type
    MimeType type = mimeTypes.getByType("application/vnd.custom.bar");
    assertEquals(type.getMimeType(), "application/vnd.custom.bar");
    assertArrayEquals(type.getExtensions(), new String[]{"bar", "zot"});

    // By ext
    type = mimeTypes.getByExtension("bar");
    assertEquals(type.getMimeType(), "application/vnd.custom.bar");
    assertArrayEquals(type.getExtensions(), new String[]{"bar", "zot"});
  }

  @Test
  public void testCustomDefinitionsRegister() {
    MimeTypes mimeTypes = MimeTypes.getInstance();
    mimeTypes.register(new MimeType("application/vnd.custom.orbit", "orb"));

    // By Type
    MimeType type = mimeTypes.getByType("application/vnd.custom.orbit");
    assertEquals(type.getMimeType(), "application/vnd.custom.orbit");
    assertArrayEquals(type.getExtensions(), new String[]{"orb"});

    // By ext
    type = mimeTypes.getByExtension("orb");
    assertEquals(type.getMimeType(), "application/vnd.custom.orbit");
    assertArrayEquals(type.getExtensions(), new String[]{"orb"});
  }

  @Test
  public void testSameInstance() {
    MimeTypes mimeTypes = MimeTypes.getInstance();

    MimeType type1 = mimeTypes.getByType("text/css");
    MimeType type2 = mimeTypes.getByExtension("css");
    assertTrue(type1 == type2);
  }

  @Test
  public void testModel() {
    MimeType t1 = new MimeType("image/jpeg", "jpg", "jpeg");
    assertEquals(t1.getExtension(), "jpg");

    MimeType t2 = new MimeType("application/vnd.foo+json");
    assertNull(t2.getExtension());
  }

  @Test
  public void testIdentity() {
    assertNotEquals(new MimeType("text/plain"), null);
    assertNotEquals(new MimeType("text/plain", "txt"), new Object());

    MimeType json1 = new MimeType("application/json", "json");
    MimeType json2 = new MimeType("application/json", "json");
    assertEquals(json1, json2);
    assertEquals(json1.hashCode(), json2.hashCode());

    // Same ext, different mime type
    MimeType githubJson = new MimeType("application/vnd.github+json", "json");
    assertNotEquals(githubJson, json1);
    assertNotEquals(githubJson.hashCode(), json1.hashCode());

    // Same mime type, different ext
    MimeType jsonMl = new MimeType("application/json", "jsonml");
    assertNotEquals(jsonMl, json1);
    assertNotEquals(jsonMl.hashCode(), json1.hashCode());
  }

  @Test
  public void testNonExisting() {
    assertNull(MimeTypes.getInstance().getByType("application/vnd.imaginary"));
    assertNull(MimeTypes.getInstance().getByExtension("none"));
  }

  @Test(expected = RuntimeException.class)
  public void testInvalidFile() {
    MimeTypes.blank().load(Paths.get("non-existing-file"));
  }
}
