package com.codepuran.d2h.dao;

import com.codepuran.d2h.DataSource;
import com.codepuran.d2h.models.Package;

import java.util.List;
import java.util.Optional;

public class PackageDao {

  private final DataSource dataSource;

  public PackageDao(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public List<Package> findAll() {
    List<Package> packages = this.dataSource.getPackages();
    return packages;
  }

  public Optional<Package> findBySlug(String slug) {
    List<Package> packages = this.dataSource.getPackages();
    return packages.stream().filter(pack -> pack.getSlug().equals(slug)).findFirst();
  }
}
