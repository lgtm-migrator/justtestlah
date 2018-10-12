package io.github.martinschneider.yasew.locator;

import io.github.martinschneider.yasew.base.BasePage;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

@Component
public class LocatorParser {

  private static final Logger LOG = LoggerFactory.getLogger(LocatorParser.class);

  private Yaml yamlParser;

  @Autowired
  public void setYamlParser(Yaml yamlParser) {
    this.yamlParser = yamlParser;
  }

  public Map<String, Map<String, Map<String, String>>> parse(String fileName) {
    InputStream inputStream = BasePage.class.getClassLoader().getResourceAsStream(fileName);
    if (inputStream == null) {
      LOG.warn("Could not load locators from {}", fileName);
      return Collections.emptyMap();
    }
    return yamlParser.load(inputStream);
  }
}
