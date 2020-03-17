package com.codepuran.sattv.dao;

import com.codepuran.sattv.DataSource;
import com.codepuran.sattv.models.SpecialService;

import java.util.List;
import java.util.Optional;

public class SpecialServiceDao {

  private final DataSource dataSource;

  public SpecialServiceDao(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public List<SpecialService> findAll() {
    return this.dataSource.getSpecialServices();
  }

  public Optional<SpecialService> findByName(String name) {
    List<SpecialService> specialServices = this.dataSource.getSpecialServices();
    return specialServices.stream().filter(specialService -> specialService.getName().equals(name)).findFirst();
  }
}
