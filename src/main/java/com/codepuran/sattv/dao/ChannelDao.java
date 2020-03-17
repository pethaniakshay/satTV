package com.codepuran.sattv.dao;

import com.codepuran.sattv.DataSource;
import com.codepuran.sattv.models.Channel;

import java.util.List;
import java.util.Optional;

public class ChannelDao {

  private final DataSource dataSource;

  public ChannelDao(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public List<Channel> findAll() {
    return dataSource.getChannels();
  }

  public Optional<Channel> findByName(String name) {
    List<Channel> channels = this.dataSource.getChannels();
    return channels.stream().filter(channel -> channel.getName().equals(name)).findFirst();
  }
}
