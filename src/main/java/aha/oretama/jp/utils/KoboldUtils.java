package aha.oretama.jp.utils;

import aha.oretama.jp.configuration.properties.ApplicationProperties;
import aha.oretama.jp.model.Kobold;

import java.util.List;
import java.util.stream.Collectors;

public class KoboldUtils {

  private ApplicationProperties properties;

  private static String getPathWithPng(String path) {
    return path.endsWith(".png") ? path : path + ".png";
  }

  public static Kobold getKoboldWithPath(Kobold kobold, String path, String buildPath,
      String approvedPath, String hightlighPath) {

    List additions = kobold.getAdditions().stream()
        .map(addition -> path + "/" + buildPath + "/" + getPathWithPng(addition))
        .collect(Collectors.toList());
    List orphans = kobold.getOrphans().stream()
        .map(orphan -> path + "/" + approvedPath + "/" + getPathWithPng(orphan))
        .collect(Collectors.toList());
    List differences = kobold.getDifferences().stream()
        .map(difference -> path + "/" + hightlighPath + "/" + getPathWithPng(difference))
        .collect(Collectors.toList());

    return new Kobold(additions, orphans, differences);
  }
}
